package com.example.libraryproject.LibProject;

import java.util.Date;

public class User {

    public String Name;
    public String Surname;
    public String Role;
    public int PNumber;
    public int Id;
    public int LoanCounter;
    public int ViolationCounter;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public int getPNumber() {
        return PNumber;
    }

    public void setPNumber(int PNumber) {
        this.PNumber = PNumber;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getLoanCounter() {
        return LoanCounter;
    }

    public void setLoanCounter(int loanCounter) {
        LoanCounter = loanCounter;
    }

    public int getViolationCounter() {
        return ViolationCounter;
    }

    public void setViolationCounter(int violationCounter) {
        ViolationCounter = violationCounter;
    }


    public Book searchTitle(String Title, int id){

        Book temp = new Book();

        return temp;
    }

    public void requestDelete(int id){

    }

    public void requestLoan(String name){

    }

    public void requestTime (Date time){

    }

    public void returnBook(Book bookTitle, int id){

    }

}