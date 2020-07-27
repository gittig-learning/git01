package org.example.entity;

public class Admin {
    private Integer id;

    private String loginAcct;

    private String userPswd;

    private String userName;

    private String emial;

    private String creatTime;

    public Admin(Integer id, String loginAcct, String userPswd, String userName, String emial, String creatTime) {
        this.id = id;
        this.loginAcct = loginAcct;
        this.userPswd = userPswd;
        this.userName = userName;
        this.emial = emial;
        this.creatTime = creatTime;
    }

    public Admin() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginAcct() {
        return loginAcct;
    }

    public void setLoginAcct(String loginAcct) {
        this.loginAcct = loginAcct == null ? null : loginAcct.trim();
    }

    public String getUserPswd() {
        return userPswd;
    }

    public void setUserPswd(String userPswd) {
        this.userPswd = userPswd == null ? null : userPswd.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getEmial() {
        return emial;
    }

    public void setEmial(String emial) {
        this.emial = emial == null ? null : emial.trim();
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime == null ? null : creatTime.trim();
    }
}