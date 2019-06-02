package Domain;

public class Vote {

    int voteID;
    String userID;
    int questionID;
    int answerID;
    String mcQuestion;
    String mcAnswer;

    public Vote(){

    }
    public int getVoteID() {
        return voteID;
    }

    public void setVoteID(int voteID) {
        this.voteID = voteID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public int getAnswerID() {
        return answerID;
    }

    public void setAnswerID(int answerID) {
        this.answerID = answerID;
    }

    public String getMcQuestion() {
        return mcQuestion;
    }

    public void setMcQuestion(String mcQuestion) {
        this.mcQuestion = mcQuestion;
    }

    public String getMcAnswer() {
        return mcAnswer;
    }

    public void setMcAnswer(String mcAnswer) {
        this.mcAnswer = mcAnswer;
    }


}
