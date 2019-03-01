package com.londonappbrewery.quizzler;

public class TrueFalse {

    private int questionID;
    private boolean answer;

    public TrueFalse(int questionID, boolean answer)
    {
        this.setAnswer(answer);
        this.setQuestionID(questionID);
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public boolean isAnswer() {
        return answer;
    }

    public int getQuestionID() {
        return questionID;
    }

}
