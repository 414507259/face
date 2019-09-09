package com.tap2up.pojo;

public class Group {
    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", groupname='" + groupname + '\'' +
                ", type='" + type + '\'' +
                ", ctime=" + ctime +
                ", updatetime=" + updatetime +
                ", isdelete=" + isdelete +
                '}';
    }

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column group._id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column group.groupName
     *
     * @mbg.generated
     */
    private String groupname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column group.type
     *
     * @mbg.generated
     */
    private String type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column group.ctime
     *
     * @mbg.generated
     */
    private Long ctime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column group.updateTime
     *
     * @mbg.generated
     */
    private Long updatetime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column group.isdelete
     *
     * @mbg.generated
     */
    private Integer isdelete;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column group._id
     *
     * @return the value of group._id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column group._id
     *
     * @param id the value for group._id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column group.groupName
     *
     * @return the value of group.groupName
     *
     * @mbg.generated
     */
    public String getGroupname() {
        return groupname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column group.groupName
     *
     * @param groupname the value for group.groupName
     *
     * @mbg.generated
     */
    public void setGroupname(String groupname) {
        this.groupname = groupname == null ? null : groupname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column group.type
     *
     * @return the value of group.type
     *
     * @mbg.generated
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column group.type
     *
     * @param type the value for group.type
     *
     * @mbg.generated
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column group.ctime
     *
     * @return the value of group.ctime
     *
     * @mbg.generated
     */
    public Long getCtime() {
        return ctime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column group.ctime
     *
     * @param ctime the value for group.ctime
     *
     * @mbg.generated
     */
    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column group.updateTime
     *
     * @return the value of group.updateTime
     *
     * @mbg.generated
     */
    public Long getUpdatetime() {
        return updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column group.updateTime
     *
     * @param updatetime the value for group.updateTime
     *
     * @mbg.generated
     */
    public void setUpdatetime(Long updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column group.isdelete
     *
     * @return the value of group.isdelete
     *
     * @mbg.generated
     */
    public Integer getIsdelete() {
        return isdelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column group.isdelete
     *
     * @param isdelete the value for group.isdelete
     *
     * @mbg.generated
     */
    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }
}