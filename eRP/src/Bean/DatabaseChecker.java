package Bean;

import Domain.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DatabaseChecker {

    //TODO - Enter your details
    final String username = "";
    final String password = "";
    //jdbc string
    final String host = "";
    public Connection toConnect() throws SQLException, ClassNotFoundException {


        Class.forName("com.mysql.jdbc.Driver");
        Connection connect = (Connection) DriverManager.getConnection(host,
                username, password);
        return connect;

    }

    public User validateUser(String userEmail, String userPassword) throws SQLException, ClassNotFoundException {

        User user = null;
        String sqlCommand = "SELECT * FROM eRP_USERS WHERE email=? AND password=?";

        try (Connection connection = toConnect(); PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            preparedStatement.setString(1, userEmail);
            preparedStatement.setString(2, HashGenerator.getSHA256(userPassword));


            //go through the table, create user?
            try (ResultSet ruleSet = preparedStatement.executeQuery()) {
                while (ruleSet.next()) {

                    int role = ruleSet.getInt("role");
                    String email = ruleSet.getString("email");
                    String fullName = ruleSet.getString("fullName");
                    String dob = ruleSet.getString("dob");
                    String homeAddress = ruleSet.getString("homeAddress");
                    boolean hasVoted = ruleSet.getBoolean("hasVoted");
                    String bic = ruleSet.getString("BIC");

                    user = new User(role,email, fullName, dob, userPassword, homeAddress, bic);
                    user.setHasVoted(hasVoted);

                    break;
                }
            }
        }
        return user;
    }


    public boolean isBICAvailable(String userBic){


        boolean availability = true;

        String sqlCommand = "SELECT * FROM BIC_RECORD WHERE BIC=?";

        try(Connection connect = toConnect();PreparedStatement preparedStatement = (PreparedStatement)connect.prepareStatement(sqlCommand);){

            preparedStatement.setString(1,userBic);


            BIC bic = new BIC();

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    resultSet.beforeFirst();

                    while(resultSet.next()){
                        bic.setBic(resultSet.getString("BIC"));
                        bic.setUsed(resultSet.getBoolean("USED"));

                        if (bic.isUsed()){
                            availability = false;
                        }
                    }
                }
            }catch (SQLException exception){
                exception.printStackTrace();
            }

        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return availability;

    }

    public McQuestion getMcQuestion(){
        String sqlCommand = "SELECT * FROM REFERENDUM";
        McQuestion mcQ = null;


        try(Connection connect = toConnect();
            PreparedStatement preparedStatement = (PreparedStatement)connect.prepareStatement(sqlCommand);
            ResultSet resultSet = preparedStatement.executeQuery()){
            while(resultSet.next()){

                int refID = resultSet.getInt("REF_ID");
                String refStr = resultSet.getString("REFERENDUM_TITLE");
                int status = resultSet.getInt("OPEN");
                mcQ = new McQuestion(refID,refStr,status);

                List<McOptions> options = getOptions(refID);


                mcQ.setChoices(options);
            }
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return mcQ;
    }

    public boolean registeredCheck() throws SQLException{
        String sqlCommand ="SELECT COUNT(*) FROM eRP_USERS WHERE role=1";
        String sqlCount1 ="SELECT COUNT(*) FROM DONE_VOTES";

        int numVoted = 0;
        int totalPopulation =0;

        double percentage;
        try(Connection connection = toConnect();PreparedStatement ps= connection.prepareStatement(sqlCommand);ResultSet rs = ps.executeQuery()){

            while(rs.next()){
                totalPopulation = Integer.parseInt(rs.getString(1));
                break;
            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        try(Connection connection = toConnect();PreparedStatement preparedStatement = connection.prepareStatement(sqlCount1);
            ResultSet resultSet = preparedStatement.executeQuery()){

                while(resultSet.next()){
                    numVoted = Integer.parseInt(resultSet.getString(1));
                    break;
                }
        }catch(ClassNotFoundException e){
                e.printStackTrace();
        }
        percentage = (double) totalPopulation *80/100;

        return numVoted >= percentage;
    }
    public List<McOptions> getOptions(int refID){

        String sqlCommand ="SELECT * FROM REFERENDUM_OPTIONS WHERE REF_ID=?";
        List<McOptions> options = new ArrayList<>();


        try(Connection connect = toConnect();
            PreparedStatement preparedStatement = (PreparedStatement)connect.prepareStatement(sqlCommand);){

            McOptions mcOptions = null;
            preparedStatement.setInt(1,refID);

            try(ResultSet resultSet = preparedStatement.executeQuery()){

                while (resultSet.next()){

                    int optID = resultSet.getInt("OPT_ID");
                    String option = resultSet.getString("OPTION");
                    int voteCount = resultSet.getInt("VOTE_COUNT");


                    mcOptions = new McOptions(optID,option,refID);

                    options.add(mcOptions);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }


        return options;
    }
    public void  voteSubmit(String userID, int refID,int choiceID, String choiceStr){

        String sqlCommand = "INSERT INTO DONE_VOTES(userID,refID,choiceID) VALUES(?,?,?)";
        String sqlUpdate ="UPDATE eRP_USERS SET hasVoted=? WHERE email=?";
        try(Connection connection = toConnect();PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(sqlCommand)){


            preparedStatement.setString(1,userID);
            preparedStatement.setInt(2,refID);
            preparedStatement.setInt(3,choiceID);
//            preparedStatement.setString(4,choiceStr);
            preparedStatement.executeUpdate();


            PreparedStatement  updateStatement = (PreparedStatement)connection.prepareStatement(sqlUpdate);
            updateStatement.setBoolean(1,true);
            updateStatement.setString(2,userID);
            updateStatement.executeUpdate();


        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    // only Voters can sign up. Election Coms are predefined in the schema.
    public void signup(User user) throws SQLException{


        String sqlCommand = "INSERT INTO eRP_USERS (role,email,fullName,password,hasVoted,dob,homeAddress,BIC)"
                + " VALUES (?, ?, ?, ?, ?,?,?,?)";

        try(Connection connection = toConnect();PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {


            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getFullName());
            preparedStatement.setString(4, HashGenerator.getSHA256(user.getPassword()));
            preparedStatement.setBoolean(5, user.getHasVoted());
            preparedStatement.setString(6, user.getDateOfBirth());
            preparedStatement.setString(7, user.getHomeAddress());
            preparedStatement.setString(8, user.getBIC());

            preparedStatement.executeUpdate();


            String sqlUpdate = "UPDATE BIC_RECORD SET USED=? WHERE BIC=?";

            PreparedStatement prepStatement = connection.prepareStatement(sqlUpdate);
            prepStatement.setInt(1, 1);
            prepStatement.setString(2, user.getBIC());
            prepStatement.executeUpdate();

            System.out.println("Registered!");
        }catch(ClassNotFoundException e){
            e.printStackTrace();

        }
    }


    public boolean existingEmail(String email){

        String sqlCommand ="SELECT * FROM eRP_USERS WHERE email=?";
        boolean availablility = false;

        try{

            try(Connection connect =toConnect();
                PreparedStatement ps = (PreparedStatement)connect.prepareStatement(sqlCommand)){
                ps.setString(1, email);

                try(ResultSet rs = ps.executeQuery()){
                    while(rs.next()){
                        availablility=true;
                    }
                }
            }

        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return availablility;
    }


    public void startRef(){
        String sqlCommand ="UPDATE REFERENDUM SET OPEN=? WHERE OPEN=?";

        try(Connection connection = toConnect();PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(sqlCommand)){

            preparedStatement.setInt(1,1);
            preparedStatement.setInt(2,0);
            preparedStatement.executeUpdate();

        }catch(SQLException  |ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    public void stopRef(){
        String sqlCommand = "UPDATE REFERENDUM SET OPEN=? WHERE OPEN=?";


        try(Connection connection = toConnect();PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(sqlCommand)){

        preparedStatement.setInt(1,0);
        preparedStatement.setInt(2,1);
        preparedStatement.executeUpdate();

        }catch(SQLException  |ClassNotFoundException e){
            e.printStackTrace();
        }

    }
    public void setRefQuestion(McQuestion refQuestion){
        String sqlCommand = "UPDATE REFERENDUM SET REFERENDUM_TITLE=? WHERE REF_ID=?";

        deleteteVotesAndChoices();

        try(Connection connection = toConnect();PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sqlCommand)){

            System.out.println(refQuestion.getQuestionStr());
            preparedStatement.setString(1,refQuestion.getQuestionStr());
            preparedStatement.setInt(2,1);

            preparedStatement.execute();
            List<McOptions> options = refQuestion.getChoices();

            for(int j=0;j<options.size();j++){
                setChoice(options.get(j),j);
            }
        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }


    }

    public void setChoice(McOptions mcOptions,int optCounter){
        String sqlCommand ="INSERT INTO REFERENDUM_OPTIONS (OPT_ID,REF_ID,VOTE_COUNT)" + " VALUES (?,?,?)";

        String sqlUpdate = "UPDATE  REFERENDUM_OPTIONS SET `OPTION` =? WHERE OPT_ID=?";
        try(Connection connection = toConnect();PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(sqlCommand)){
            preparedStatement.setInt(1,optCounter+1);
            preparedStatement.setInt(2,1);
            preparedStatement.setInt(3,0);
            preparedStatement.execute();

            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sqlUpdate);


            ps.setString(1,mcOptions.getOption());
            ps.setInt(2,optCounter+1);
            ps.execute();


        }catch (SQLException |ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void deleteteVotesAndChoices(){

        String sqlDeleteVotes = "DELETE FROM DONE_VOTES";
        String sqlDeleteChoices = "DELETE FROM REFERENDUM_OPTIONS";

        String updateUsers = "UPDATE eRP_USERS SET hasVoted=?";


        String resetCounter = "ALTER TABLE DONE_VOTES auto_increment=1;";
        String resetCounterChoices = "ALTER TABLE REFERENDUM_OPTIONS auto_increment=1;";
        try(Connection connection = toConnect();PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sqlDeleteVotes)){
            //delete votes
            preparedStatement.execute();

            PreparedStatement ps = (PreparedStatement)connection.prepareStatement(updateUsers);
            //update user hasVoted
            ps.setBoolean(1,false);
            ps.execute();
            PreparedStatement ps1 = (PreparedStatement) connection.prepareStatement(resetCounter);
            ps1.execute();

            PreparedStatement ps2 =(PreparedStatement) connection.prepareStatement(sqlDeleteChoices);
            ps2.execute();

            PreparedStatement ps3 = (PreparedStatement) connection.prepareStatement(resetCounterChoices);
            ps3.execute();


        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }


    public List<Vote> collectVotes(){
        String sqlCommand ="Select * FROM DONE_VOTES";

        List<Vote> allVotes = new ArrayList<>();
        try(Connection connection = toConnect();PreparedStatement ps =(PreparedStatement) connection.prepareStatement(sqlCommand)){

            try(ResultSet resultSet = ps.executeQuery()){
                while(resultSet.next()){
                    Vote vote = new Vote();
                    vote.setAnswerID(resultSet.getInt("choiceID"));
                    allVotes.add(vote);
                }
            }
        }catch(SQLException  | ClassNotFoundException e){
            e.printStackTrace();
        }

        return allVotes;
    }


}