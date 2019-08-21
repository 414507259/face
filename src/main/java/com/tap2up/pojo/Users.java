package com.tap2up.pojo;

import java.io.Serializable;
import java.util.Date;

public class Users implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column users.userId
     *
     * @mbg.generated
     */
    private Integer userid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column users.username
     *
     * @mbg.generated
     */
    private String username;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column users.password
     *
     * @mbg.generated
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column users.logintime
     *
     * @mbg.generated
     */
    private Date logintime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column users.endtime
     *
     * @mbg.generated
     */
    private Date endtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column users.auth
     *
     * @mbg.generated
     */
    private String auth;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column users.userId
     *
     * @return the value of users.userId
     *
     * @mbg.generated
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column users.userId
     *
     * @param userid the value for users.userId
     *
     * @mbg.generated
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column users.username
     *
     * @return the value of users.username
     *
     * @mbg.generated
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column users.username
     *
     * @param username the value for users.username
     *
     * @mbg.generated
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column users.password
     *
     * @return the value of users.password
     *
     * @mbg.generated
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column users.password
     *
     * @param password the value for users.password
     *
     * @mbg.generated
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column users.logintime
     *
     * @return the value of users.logintime
     *
     * @mbg.generated
     */
    public Date getLogintime() {
        return logintime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column users.logintime
     *
     * @param logintime the value for users.logintime
     *
     * @mbg.generated
     */
    public void setLogintime(Date logintime) {
        this.logintime = logintime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column users.endtime
     *
     * @return the value of users.endtime
     *
     * @mbg.generated
     */
    public Date getEndtime() {
        return endtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column users.endtime
     *
     * @param endtime the value for users.endtime
     *
     * @mbg.generated
     */
    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column users.auth
     *
     * @return the value of users.auth
     *
     * @mbg.generated
     */
    public String getAuth() {
        return auth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column users.auth
     *
     * @param auth the value for users.auth
     *
     * @mbg.generated
     */
    public void setAuth(String auth) {
        this.auth = auth == null ? null : auth.trim();
    }

    @Override
    public String toString() {
        return "Users{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", logintime=" + logintime +
                ", endtime=" + endtime +
                ", auth='" + auth + '\'' +
                '}';
    }
}