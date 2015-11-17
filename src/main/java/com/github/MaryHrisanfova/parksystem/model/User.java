package com.github.MaryHrisanfova.parksystem.model;

/**
 * Created by Маша on 12.11.2015.
 */
public class User {
    private String login;
    private String password;
    private String firstname;
    private String lasttname;
    private String email;

    public User(String login,String password,String firstname, String lasttname,  String email) {
        this.login=login;
        this.password=password;
        this.firstname = firstname;
        this.lasttname = lasttname;
        this.email = email;
    }

    public User(){

    }

    public boolean isUserCorrect(){
        
    return (true);}

    public void setLogin(String login) {this.login = login;}

    public void setFirstname(String firstname) {this.firstname = firstname;}

    public void setLasttname(String lasttname) {
        this.lasttname = lasttname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {this.password = password;}

    public String getLogin() {return login;}

    public String getPassword() {
        return password;
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

    @Override
    public String toString() {
        return (this.login+" "+this.firstname+" "+this.lasttname+", "+this.email);
    }
}
