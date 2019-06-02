package Domain;

import java.util.List;

public class McQuestion {
    private int questionID;
    private String questionStr;
    private List<McOptions> choices;

    public boolean getStatus() {
        boolean toRet = false;
        if(status == 0){
            toRet= false;
        }else if(status ==1){
            toRet = true;
        }

        return toRet;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private int status;
    public McQuestion(int id,String question,int status){

        this.questionID = id;
        this.questionStr = question;
        this.status = status;

    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public String getQuestionStr() {
        return questionStr;
    }

    public void setQuestionStr(String questionStr) {
        this.questionStr = questionStr;
    }

    public List<McOptions> getChoices() {
        return choices;
    }

    public void setChoices(List<McOptions> choices) {
        this.choices = choices;
    }

    public String specificChoiceStr(List<McOptions> choices, int id){

        return choices.get(id).getOption();
    }



}
