package tech.gamesupport.center.inner.account.model;

public class UserIdentity {

    private String productId;
    private String universalUserId;
    private String realName;
    private Integer universalDepartmentId;
    private Integer universalCompanyId;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUniversalUserId() {
        return universalUserId;
    }

    public void setUniversalUserId(String universalUserId) {
        this.universalUserId = universalUserId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getUniversalDepartmentId() {
        return universalDepartmentId;
    }

    public void setUniversalDepartmentId(Integer universalDepartmentId) {
        this.universalDepartmentId = universalDepartmentId;
    }

    public Integer getUniversalCompanyId() {
        return universalCompanyId;
    }

    public void setUniversalCompanyId(Integer universalCompanyId) {
        this.universalCompanyId = universalCompanyId;
    }
}
