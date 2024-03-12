package tech.gamesupport.center.inner.account.model;

import java.time.ZonedDateTime;

public class InternalUserInfo {

    private String universalUserId;
    private String realName;
    private String userHandle;

    private String countryCode;

    private Integer universalCompanyId;
    private Integer universalDepartmentId;
    private String userEmail;

    private String jobTitle;
    private ZonedDateTime joinDate;

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

    public String getUserHandle() {
        return userHandle;
    }

    public void setUserHandle(String userHandle) {
        this.userHandle = userHandle;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Integer getUniversalCompanyId() {
        return universalCompanyId;
    }

    public void setUniversalCompanyId(Integer universalCompanyId) {
        this.universalCompanyId = universalCompanyId;
    }

    public Integer getUniversalDepartmentId() {
        return universalDepartmentId;
    }

    public void setUniversalDepartmentId(Integer universalDepartmentId) {
        this.universalDepartmentId = universalDepartmentId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public ZonedDateTime getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(ZonedDateTime joinDate) {
        this.joinDate = joinDate;
    }
}
