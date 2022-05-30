package com.example.libraryproject.LibProject;

import org.apache.logging.log4j.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Librarian {

    public static Logger logger = LogManager.getLogger(User.class.getName());
    public String Name;
    public int PNumber;
    public int Id;

    public Storage storage = new Storage();

    public Librarian() throws ExceptionInInitializerError{
    }
    public Librarian(Storage obj) throws ExceptionInInitializerError{
        this.storage = obj;
    }

    public ArrayList<Book> addBook(int bookId, String titel, int isbn, int quantity, String author) throws IOException {
        logger.debug("Trying to add " + titel);
        ArrayList<Book>bookList = storage.getBooks();
        int sizeOfBookList = bookList.size();

        Book newBook = new Book(bookId,titel,isbn,quantity,author);
        bookList.add(newBook);

        for (int i = 0; i < bookList.size(); i++) {
            for (int j = i+1; j < bookList.size(); j++)
            if (bookList.get(i).Title.equalsIgnoreCase(bookList.get(j).Title)){
                bookList.remove(i);
                logger.info("duplicate was found");
            }
        }
        if (sizeOfBookList+1 == bookList.size()){
            logger.info("A new book was successfuly added");
        }
        storage.updateBookFile(bookList);
        return bookList;
    }


   public ArrayList<User> addUser(String name, int pNumber, String role) throws IOException{
       logger.info("Trying to add User");
       ArrayList<User> pendingWork = storage.getPendingList();
       ArrayList<User>userList = storage.getUserList();
       ArrayList<User>blackList = storage.getBlackList();
       int sizeOfUserList = userList.size();

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

       User newUser = new User(name,pNumber,id,loanCounter,violationCounter,role);
       userList.add(newUser);

       for (int i = 0; i < userList.size(); i++) {
           for (int j = i+1; j < userList.size(); j++){
               if (userList.get(i).PNumber == (userList.get(j).PNumber)){
                   logger.debug(userList.get(j).PNumber +" was a duplicate found");
                   userList.remove(j);
               }
           }
       }

       for (int i = 0; i < userList.size(); i++) {
           for (int j = 0; j < blackList.size(); j++)
               if (userList.get(i).PNumber == blackList.get(j).PNumber){
                   userList.remove(i);
                   logger.debug(userList.get(i).PNumber + " was found in blacklist and was not added.");
                   break;
               }
       }
       ArrayList<User>timeoutList = storage.getTimeoutList();

       for (int i = 0; i < userList.size(); i++) {
           for (int j = 0; j < timeoutList.size(); j++)
               if (userList.get(i).PNumber == timeoutList.get(j).PNumber){
                   userList.remove(i);
                   logger.debug(userList.get(i).PNumber + " was found in timeoutlist and was not added.");
                   break;
               }
       }

       storage.updateUserFile(userList);
       if (sizeOfUserList+1==userList.size()){
           logger.info("User was successfuly added");
       }
       for (User item: pendingWork){
           if (item.Name.equalsIgnoreCase(name) && item.PNumber==pNumber && item.Request.contains("AddMe: ")){
               pendingWork.remove(item);
               break;

           }
       }
       storage.updatePendingFile(pendingWork);

       return userList;
   }

   public void deleteUser(int id, boolean request) throws IOException {
       logger.debug("Deleting user with id:" + id + " was this requested? " + request);
       ArrayList<User> pendingWork = storage.getPendingList();
       ArrayList<User>userList = storage.getUserList();
       ArrayList<User>blackList = storage.getBlackList();
       ArrayList<Book>bookList = storage.getBooks();

       boolean hasLoan = false;

       if (request) {

           for (User user : userList) {
               if (user.Id == id) {
                   if (user.Id > 999 && user.Id <= 1999) {
                       if (user.LoanCounter < 3) {
                           logger.debug(user.Name + " med id:" + user.Id + " har inte lämnat tillbaka alla böcker ännu");
                           hasLoan = true;
                       }
                   } else if (user.Id > 1999 && user.Id <= 2999) {
                       if (user.LoanCounter < 5) {
                           logger.debug(user.Name + " med id:" + user.Id + " har inte lämnat tillbaka alla böcker ännu");
                           hasLoan = true;
                       }
                   } else if (user.Id > 2999 && user.Id <= 3999) {
                       if (user.LoanCounter < 7) {
                           logger.debug(user.Name + " med id:" + user.Id + " har inte lämnat tillbaka alla böcker ännu");
                           hasLoan = true;
                       }
                   } else {
                       if (user.LoanCounter < 10) {
                           logger.debug(user.Name + " med id:" + user.Id + " har inte lämnat tillbaka alla böcker ännu");
                           hasLoan = true;
                       }
                   }
               }
           }

           if (!hasLoan) {

               for (User user : userList) {
                   if (id == user.Id) {
                       userList.remove(user);
                       logger.debug(user.Name + " was deleted");
                       break;
                   }
               }

               storage.updateUserFile(userList);
           }

       }else {

           for (User user : userList) {
               if (id == user.Id) {
                   blackList.add(user);
                   logger.debug(user.Name + " was deleted and added to blacklist");
               }
           }

           for (int i = 0; i < blackList.size(); i++) {
               for (int j = i+1; j < blackList.size(); j++)
                   if (blackList.get(i).PNumber == blackList.get(j).PNumber) {
                       blackList.remove(i);
                       logger.debug(blackList.get(i).PNumber + "was found in blackList and was removed");
                       break;
                   }
           }
           storage.updateBlackFile(blackList);

           for (User user : userList) {
               if (id == user.Id) {
                   userList.remove(user);
                   logger.debug(user.Id + " was deleted");
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
                   logger.debug(textId + "was found in userLoanList");
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
       for (User item: pendingWork){
           if (item.Id == id && item.Request.contains("Delete")){
               pendingWork.remove(item);
               break;

           }
       }
       storage.updatePendingFile(pendingWork);
   }

   public void giveTimeout(int id)throws IOException{
        logger.info("Giving " + id + " a timeout");
       ArrayList<User>timeoutList = storage.getTimeoutList();
       ArrayList<User>userList = storage.getUserList();

       for (User user:timeoutList) {
           if (id == user.Id) {
               logger.info(user.Name +" Has timeout already");
           }
       }

       LocalDate timer = LocalDate.now();

       for (User users:userList) {
           if (id == users.Id){
               timeoutList.add(new User(users.Name,users.PNumber,users.Id,users.LoanCounter,users.ViolationCounter,users.Role,timer));
               userList.remove(users);
               break;
           }
       }

       storage.updateUserFile(userList);
       storage.updatetimeoutFile(timeoutList);
   }

    public void controlTimeouts()throws IOException{
        logger.info("Controlling if anyone is getting out from timedout");
        ArrayList<User>timeoutList = storage.getTimeoutList();
        ArrayList<User>userList = storage.getUserList();
        ArrayList<String>userLoanList = storage.getUserLoanList();

        boolean control = false;
        ArrayList<User> tempUserList = new ArrayList<User>();

        for (User user:timeoutList) {

            LocalDate expireDate = user.Timer.plusDays(15);
            LocalDate today = LocalDate.now();

            if (today.isAfter(expireDate)){
                for (String item:userLoanList) {
                    if (!item.contains(user.Name)&&!item.contains((String.valueOf(user.Id)))) {
                        userList.add(user);
                        logger.info("A user has been taken out of the timeoutList");
                        control = true;
                    }
                }

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
        logger.info("Trying to get a book by ISBN");
       ArrayList<Book>bookList = storage.getBooks();

       Book theBook = null;

       for (Book books: bookList) {
           if (books.ISBN == ISBN){
               theBook = books;
           }
       }
       if (theBook==null){
           logger.info("Could not find the book");
       }
        return theBook;
   }

   public void lendBook(Book theBook, int userId)throws IOException {
       logger.info("Lending:"+ theBook.Title + " to " + userId);
       ArrayList<User>userList = storage.getUserList();
       ArrayList<User> pendingWork = storage.getPendingList();

       User tempUser = new User();

       for (User user:userList) {
           if (userId == user.getId()){
               tempUser = user;
           }
       }

       ArrayList<Book> bookList = storage.getBooks();

       if (tempUser.LoanCounter > 0){
           for (Book book:bookList) {
               if (book.Title.equalsIgnoreCase(theBook.Title)){
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
                        logger.info("Loaned successfuly");
                   }else {
                       logger.info("Couldnt loan to the user because the book was not available");
                   }
               }
           }

       } else {
           logger.info("Couldnt loan to the user because the user can not loan anymore books");
       }

       for (User item: pendingWork){
           if (item.Id == userId && item.Request.contains(theBook.Title)){
               pendingWork.remove(item);
               break;
           }
       }
      storage.updatePendingFile(pendingWork);
      storage.updateBookFile(bookList);
   }

    public boolean loginLibrarian (String name, int id)throws IOException{
        logger.info("Trying to login as a Librarian.");
        boolean verify = false;

        ArrayList<User>userList = storage.getUserList();

        for (User user:userList) {
            if (user.Name.equalsIgnoreCase(name) && user.Id == id && id > 4999){
                verify = true;
                logger.debug(name + id + " Login successful");
            }
        }
        if (!verify){
            logger.debug(name + id + "Login Unsuccessful");
        }

        return verify;
    }

}