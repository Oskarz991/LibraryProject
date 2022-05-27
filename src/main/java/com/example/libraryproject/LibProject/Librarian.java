package com.example.libraryproject.LibProject;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Librarian {

    public String Name;
    public String SurName;
    public int PNumber;
    public int Id;

    public Storage storage = new Storage();

    public Librarian() throws ExceptionInInitializerError{
    }
    public Librarian(Storage obj) throws ExceptionInInitializerError{
        this.storage = obj;
    }

    public ArrayList<Book> addBook(int bookId, String titel, int isbn, int quantity, String author) throws IOException {

        ArrayList<Book>bookList = storage.getBooks();

        Book newBook = new Book(bookId,titel,isbn,quantity,author);
        bookList.add(newBook);

        for (int i = 0; i < bookList.size(); i++) {
            for (int j = i+1; j < bookList.size(); j++)
            if (bookList.get(i).Title.equals(bookList.get(j).Title)){
                bookList.remove(i);
            }
        }
        storage.updateBookFile(bookList);
        return bookList;
    }


   public ArrayList<User> addUser(String name, String surName, int pNumber, String role) throws IOException{

       ArrayList<User>userList = storage.getUserList();
       ArrayList<User>blackList = storage.getBlackList();

       int id = (int) (Math.random()*999) +1;
       int loanCounter;
       int violationCounter = 0;

       if (role.equalsIgnoreCase("Undergraduate Student")){
           loanCounter = 3;
           id = id+1000;
       }else if (role.equalsIgnoreCase("Postgraduate student")){
           loanCounter = 5;
           id = id+2000;
       }else if (role.equalsIgnoreCase("PhD Student")){
           loanCounter = 7;
           id = id+3000;
       }else if (role.equalsIgnoreCase("Teacher")){
           loanCounter = 10;
           id = id+4000;
       }else{
           id = id+5000;
           loanCounter = 9999;
       }

       User newUser = new User(name,surName,pNumber,id,loanCounter,violationCounter,role);
       userList.add(newUser);

       for (int i = 0; i < userList.size(); i++) {
           for (int j = i+1; j < userList.size(); j++){
               if (userList.get(i).PNumber == (userList.get(j).PNumber)){
                   userList.remove(j);
               }
           }
       }

       for (int i = 0; i < userList.size(); i++) {
           for (int j = 0; j < blackList.size(); j++)
               if (userList.get(i).PNumber == blackList.get(j).PNumber){
                   userList.remove(i);
                   break;
               }
       }
       ArrayList<User>timeoutList = storage.getTimeoutList();

       for (int i = 0; i < userList.size(); i++) {
           for (int j = 0; j < timeoutList.size(); j++)
               if (userList.get(i).PNumber == timeoutList.get(j).PNumber){
                   userList.remove(i);
                   break;
               }
       }

       storage.updateUserFile(userList);

       return userList;
   }

   public void deleteUser(int id, boolean request) throws IOException {
       ArrayList<User>userList = storage.getUserList();
       ArrayList<User>blackList = storage.getBlackList();
       ArrayList<Book>bookList = storage.getBooks();

       boolean hasLoan = false;

       if (request) {

           for (User user : userList) {
               if (user.Id == id) {
                   if (user.Id > 999 && user.Id <= 1999) {
                       if (user.LoanCounter < 3) {
                           System.out.println(user.Name + " med id:" + user.Id + " har inte lämnat tillbaka alla böcker ännu");
                           hasLoan = true;
                       }
                   } else if (user.Id > 1999 && user.Id <= 2999) {
                       if (user.LoanCounter < 5) {
                           System.out.println(user.Name + " med id:" + user.Id + " har inte lämnat tillbaka alla böcker ännu");
                           hasLoan = true;
                       }
                   } else if (user.Id > 2999 && user.Id <= 3999) {
                       if (user.LoanCounter < 7) {
                           System.out.println(user.Name + " med id:" + user.Id + " har inte lämnat tillbaka alla böcker ännu");
                           hasLoan = true;
                       }
                   } else {
                       if (user.LoanCounter < 10) {
                           System.out.println(user.Name + " med id:" + user.Id + " har inte lämnat tillbaka alla böcker ännu");
                           hasLoan = true;
                       }
                   }
               }
           }

           if (!hasLoan) {

               for (User user : userList) {
                   if (id == user.Id) {
                       userList.remove(user);
                       break;
                   }
               }

               storage.updateUserFile(userList);

           }

       }else {

           for (User user : userList) {
               if (id == user.Id) {
                   blackList.add(user);
               }
           }

           for (int i = 0; i < blackList.size(); i++) {
               for (int j = i+1; j < blackList.size(); j++)
                   if (blackList.get(i).PNumber == blackList.get(j).PNumber) {
                       blackList.remove(i);
                       break;
                   }
           }
           storage.updateBlackFile(blackList);

           for (User user : userList) {
               if (id == user.Id) {
                   userList.remove(user);
                   break;
               }
           }

           storage.updateUserFile(userList);

           ArrayList<String> userLoanList = storage.getUserLoanList();

           boolean stopStart = false;
           String textId = String.valueOf(id);
           for (String row : userLoanList) {
               if (row.contains(textId)) {
                   stopStart = true;
                   break;
               }
           }
           while(stopStart) {
               for (String row : userLoanList) {
                   for (Book book : bookList) {
                       if (row.contains(String.valueOf(book.ISBN))) {
                           userLoanList.remove(row);
                           stopStart = userLoanList.contains(textId);
                           book.Quantity++;
                           break;
                       }

                   }
                   break;
               }
           }
           storage.updateBookFile(bookList);
           storage.updateUserFile(userList);
           storage.updateUserLoanFile(userLoanList);
       }
   }

   public void giveTimeout(int id)throws IOException{

       ArrayList<User>timeoutList = storage.getTimeoutList();
       ArrayList<User>userList = storage.getUserList();

       for (User user:timeoutList) {
           if (id == user.Id) {
               System.out.println("Already on a timeout");
           }
       }

       LocalDate timer = LocalDate.now();

       for (User users:userList) {
           if (id == users.Id){
               timeoutList.add(new User(users.Name,users.Surname,users.PNumber,users.Id,users.LoanCounter,users.ViolationCounter,users.Role,timer));
               userList.remove(users);
               break;
           }
       }

       storage.updateUserFile(userList);
       storage.updatetimeoutFile(timeoutList);
   }

    public void controlTimeouts()throws IOException{

        ArrayList<User>timeoutList = storage.getTimeoutList();
        ArrayList<User>userList = storage.getUserList();

        boolean control = false;
        ArrayList<User> tempUserList = new ArrayList<User>();

        for (User user:timeoutList) {

            LocalDate expireDate = user.Timer.plusDays(15);
            LocalDate today = LocalDate.now();

            if (today.isAfter(expireDate)){
                userList.add(user);

                control = true;

            }else{
                tempUserList.add(user);
            }

        } if (control){

          timeoutList.clear();

            for (User addUser:tempUserList) {
                timeoutList.add(addUser);
            }

           storage.updatetimeoutFile(timeoutList);
            storage.updateUserFile(userList);
        }

    }


   public Book getBookByISBN(int ISBN)throws IOException{

       ArrayList<Book>bookList = storage.getBooks();

       Book theBook = null;

       for (Book books: bookList) {
           if (books.ISBN == ISBN){
               theBook = books;
           }
       }
        return theBook;
   }

   public void lendBook(Book theBook, int userId)throws IOException {

       ArrayList<User>userList = storage.getUserList();

       User tempUser = new User();

       for (User user:userList) {
           if (userId == user.getId()){
               tempUser = user;
           }
       }


       ArrayList<Book> bookList = storage.getBooks();

       if (tempUser.LoanCounter > 0){
           for (Book book:bookList) {
               if (book.Title.equals(theBook.Title)){
                   if (book.Quantity > 0){

                       ArrayList<String> userLoanList = storage.getUserLoanList();

                       String tempLoanString = tempUser.loanExport(tempUser) + book.loanExport(book);

                       userLoanList.add(tempLoanString);

                       storage.updateUserLoanFile(userLoanList);

                       book.Quantity--;

                       tempUser.LoanCounter--;

                       for (User user:userList) {
                           if (userId == user.Id){
                               user = tempUser;
                           }
                       }

                       storage.updateUserFile(userList);

                   }else {
                       System.out.println("The title is not available right now");
                   }
               }
           }

       } else {
           System.out.println("You have too many loaned books");
       }

      storage.updateBookFile(bookList);

   }

    public boolean loginLibrarian (String name, int id)throws IOException{

        boolean verify = false;

        ArrayList<User>userList = storage.getUserList();

        for (User user:userList) {
            if (user.Name.equalsIgnoreCase(name) && user.Id == id && id > 4999){
                verify = true;
            }
        }

        return verify;
    }
}