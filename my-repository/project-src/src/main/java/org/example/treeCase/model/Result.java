package org.example.treeCase.model;

public class Result {
    private String signInfo;
    private String token;

    @Override
    public String toString() {
        return "Result{" +
                "signInfo='" + signInfo + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

    public String getSignInfo() {
        return signInfo;
    }

    public void setSignInfo(String signInfo) {
        this.signInfo = signInfo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
