package com.github.MaryHrisanfova.parksystem.model;

/**Класс, отвечающий за пользователей.
 * Содержит только getters и setters для полей класса
 * @author Маша
 * @since 14.11.2015.
 */
public class User {

    private Integer id;
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

    public User(Integer id, String lasttname, String firstname){
        this.id = id;
        this.lasttname = lasttname;
        this.firstname = firstname;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return (this.firstname+" "+this.lasttname+", "+this.login+", "+this.email);
    }
}
