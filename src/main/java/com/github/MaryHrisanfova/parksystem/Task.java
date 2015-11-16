package com.github.MaryHrisanfova.parksystem;

/**
 * Created by Маша on 14.11.2015.
 */
public class Task {
    private String lastnameOfSender;
    private String firstnameOfSender;
    private String tasktype;
    private String tasktext;
    private String lastnameOfRecipient;
    private String firstnameOfRecipient;
    private String isdone;
    private String isconfirmed;

    public Task(){}

    public Task(String lastnameOfSender, String firstnameOfSender, String tasktype, String tasktext,String lastnameOfRecipient, String firstnameOfRecipient, boolean isdone, boolean isconfirmed) {
        this.lastnameOfSender = lastnameOfSender;
        this.firstnameOfSender = firstnameOfSender;
        this.tasktype = tasktype;
        this.tasktext = tasktext;
        this.lastnameOfRecipient = lastnameOfRecipient;
        this.firstnameOfRecipient = firstnameOfRecipient;
        this.isdone = setIsdone(isdone);
        this.isconfirmed = setIsconfirmed(isconfirmed);
    }

    public void setLastnameOfSender(String lastnameOfSender) {
        this.lastnameOfSender = lastnameOfSender;
    }

    public void setFirstnameOfSender(String firstnameOfSender) {
        this.firstnameOfSender = firstnameOfSender;
    }

    public void setTasktype(String tasktype) {
        this.tasktype = tasktype;
    }

    public void setTasktext(String tasktext) {
        this.tasktext = tasktext;
    }
    public String setIsdone(boolean isdone) {
        if (isdone==true)
        {
            this.isdone="Выполнено";
        }
        else  this.isdone="Не выполнено";
        return this.isdone;
    }

    public String setIsconfirmed(boolean isconfirmed) {

        if (isconfirmed==true)
        {
            this.isconfirmed="Подтверждено";
        }
        else this.isconfirmed="Не подтверждено";
        return this.isconfirmed;
    }

    public void setLastnameOfRecipient(String lastnameOfRecipient) {
        this.lastnameOfRecipient = lastnameOfRecipient;
    }

    public void setFirstnameOfRecipient(String firstnameOfRecipient) {
        this.firstnameOfRecipient = firstnameOfRecipient;
    }







    public String getTasktext() {
        return tasktext;
    }






    public String getLastnameOfSender() {
        return lastnameOfSender;
    }



    public String getFirstnameOfSender() {
        return firstnameOfSender;
    }



    public String getTasktype() {
        return tasktype;
    }


    public String getLastnameOfRecipient() {
        return lastnameOfRecipient;
    }


    public String getFirstnameOfRecipient() {
        return firstnameOfRecipient;
    }



    public String getIsdone() {
        return isdone;
    }

    public String getIsconfirmed() {
        return isconfirmed;
    }



}

