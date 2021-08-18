package com.od.sharemarketmarathi;

public class questionModel {
        // User
    String que,ans;  // same as in firebase

    public questionModel(){}

    public questionModel(String que, String ans) {
        this.que = que;
        this.ans = ans;
    }

    public String getQue() {
        return que;
    }

    public void setQue(String que) {
        this.que = que;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }
}
