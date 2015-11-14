package com.github.MaryHrisanfova.parksystem;

/**
 * Created by Маша on 12.11.2015.
 */
public class User {
    private int id;
    private String firstname;
    private String lasttname;
    private String email;


    public void setId(int id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLasttname(String lasttname) {
        this.lasttname = lasttname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLasttname() {
        return lasttname;
    }

    public String getEmail() {
        return email;
    }

    public int getGroupId() {
        return groupId;
    }

    private int groupId;

    public User(int id, String firstname, String lasttname, String email, int groupId) {
        this.id = id;
        this.firstname = firstname;
        this.lasttname = lasttname;
        this.email = email;
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return (this.firstname+" "+this.lasttname+", "+this.email);
    }
}
