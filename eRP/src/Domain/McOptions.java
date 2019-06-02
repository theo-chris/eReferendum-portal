package Domain;

public class McOptions {


    private String option;
    private int optionID;

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    private int questionID;


    public McOptions(){

    }
    public McOptions(int optionID,String option,int questionID){

        this.option = option;
        this.optionID = optionID;
        this.questionID = questionID;

    }
    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public int getOptionID() {
        return optionID;
    }

    public void setOptionID(int optionID) {
        this.optionID = optionID;
    }




}
