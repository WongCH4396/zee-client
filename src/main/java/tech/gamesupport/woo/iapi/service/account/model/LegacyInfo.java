package tech.gamesupport.woo.iapi.service.account.model;

public class LegacyInfo {
    private String userName;
    private String testOpenId;
    private String prodOpenId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTestOpenId() {
        return testOpenId;
    }

    public void setTestOpenId(String testOpenId) {
        this.testOpenId = testOpenId;
    }

    public String getProdOpenId() {
        return prodOpenId;
    }

    public void setProdOpenId(String prodOpenId) {
        this.prodOpenId = prodOpenId;
    }
}
