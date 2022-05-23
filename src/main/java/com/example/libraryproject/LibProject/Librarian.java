package com.example.libraryproject.LibProject;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Librarian {

    public String Name;
    public String SurName;
    public int PNumber;
    public int Id;
    public File AllBooksFile = new File("src/main/java/com/example/libraryproject/LibProject/AllBooks.txt");
    public File UserLoanFile = new File("src/main/java/com/example/libraryproject/LibProject/LoanedBooks.txt");
    public File UserFile = new File("src/main/java/com/example/libraryproject/LibProject/WhiteList.txt");
    public File blackFile = new File("src/main/java/com/example/libraryproject/LibProject/BlackList.txt");
    public File timeoutFile = new File("src/main/java/com/example/libraryproject/LibProject/TimeoutList.txt");

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurName() {
        return SurName;
    }

    public void setSurName(String surName) {
        SurName = surName;
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

    // new methods _____________________________

    public void addBook(int bookId,String titel,int isbn,int quantity,String author) throws IOException {

        Scanner bookScan = new Scanner(AllBooksFile).useDelimiter(",");

        ArrayList<Book>bookList = new ArrayList<Book>();

        while (bookScan.hasNext()){
            int Id = Integer.parseInt(bookScan.next());
            String Name = bookScan.next();
            int ISBN = Integer.parseInt(bookScan.next());
            int Quantity = Integer.parseInt(bookScan.next());
            String Author = bookScan.nextLine();
            Author = Author.replace(",","");
            bookList.add(new Book(Id,Name,ISBN,Quantity,Author));
        }

        Book newBook = new Book(bookId,titel,isbn,quantity,author);

        bookList.add(newBook);

        for (int i = 0; i < bookList.size(); i++) {
            for (int j = i+1; j < bookList.size(); j++)
            if (bookList.get(i).Title.equals(bookList.get(j).Title)){
                bookList.remove(i);
                System.out.println("Duplicate removed");
            }
        }

        PrintWriter printWriter = new PrintWriter(AllBooksFile);

        for (Book books:bookList) {
            printWriter.println(newBook.export(books));
        }

        printWriter.close();

    }

   public void addUser(String name, String surName, int pNumber, String role) throws IOException{
       Scanner userScan = new Scanner(UserFile).useDelimiter(",");

       ArrayList<User>userList = new ArrayList<User>();
       ArrayList<User>blackList = new ArrayList<User>();

       while (userScan.hasNext()){
           String Name = userScan.next();
           String SurName = userScan.next();
           int PNumber = Integer.parseInt(userScan.next());
           int Id = Integer.parseInt(userScan.next());
           int LoanCounter = Integer.parseInt(userScan.next());
           int ViolationCounter = Integer.parseInt(userScan.next());
           String Role = userScan.nextLine();
           Role = Role.replace(",","");

           userList.add(new User(Name,SurName,PNumber,Id,LoanCounter,ViolationCounter,Role));
       }

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
                   System.out.println("Duplicate removed");
               }
           }
       }

       Scanner blacklisted = new Scanner(blackFile).useDelimiter(",");

       while (blacklisted.hasNext()){
           String Name = blacklisted.next();
           String SurName = blacklisted.next();
           int PNumber = Integer.parseInt(blacklisted.next());
           int Id = Integer.parseInt(blacklisted.next());
           int LoanCounter = Integer.parseInt(blacklisted.next());
           int ViolationCounter = Integer.parseInt(blacklisted.next());
           String Role = blacklisted.nextLine();
           Role = Role.replace(",","");

           blackList.add(new User(Name,SurName,PNumber,Id,LoanCounter,ViolationCounter,Role));
       }

       for (int i = 0; i < userList.size(); i++) {
           for (int j = 0; j < blackList.size(); j++)
               if (userList.get(i).PNumber == blackList.get(j).PNumber){
                   userList.remove(i);
                   System.out.println("Whitelisted removed because of blacklist");
                   break;
               }
       }

       Scanner timeoutListScan = new Scanner(timeoutFile).useDelimiter(",");

       ArrayList<User>timeoutList = new ArrayList<User>();

       while (timeoutListScan.hasNext()){
           String Name = timeoutListScan.next();
           String SurName = timeoutListScan.next();
           int PNumber = Integer.parseInt(timeoutListScan.next());
           int Id = Integer.parseInt(timeoutListScan.next());
           int LoanCounter = Integer.parseInt(timeoutListScan.next());
           int ViolationCounter = Integer.parseInt(timeoutListScan.next());

           LocalDate timer = LocalDate.parse(timeoutListScan.next());

           String Role = timeoutListScan.nextLine();
           Role = Role.replace(",","");

           timeoutList.add(new User(Name,SurName,PNumber,Id,LoanCounter,ViolationCounter,Role,timer));
       }

       for (int i = 0; i < userList.size(); i++) {
           for (int j = 0; j < timeoutList.size(); j++)
               if (userList.get(i).PNumber == timeoutList.get(j).PNumber){
                   userList.remove(i);
                   System.out.println("Whitelisted removed because of timeout");
                   break;
               }
       }


       PrintWriter printWriter = new PrintWriter(UserFile);

       for (User user:userList) {
           printWriter.println(user.export(user));
       }

       printWriter.close();

   }

   public void deleteUser(int id, boolean request) throws Exception {
       Scanner userScan = new Scanner(UserFile).useDelimiter(",");
       Scanner bookScan = new Scanner(AllBooksFile).useDelimiter(",");

       ArrayList<User>userList = new ArrayList<User>();
       ArrayList<User>blackList = new ArrayList<User>();
       ArrayList<Book>bookList = new ArrayList<Book>();

       while (userScan.hasNext()){
           String Name = userScan.next();
           String SurName = userScan.next();
           int PNumber = Integer.parseInt(userScan.next());
           int Id = Integer.parseInt(userScan.next());
           int LoanCounter = Integer.parseInt(userScan.next());
           int ViolationCounter = Integer.parseInt(userScan.next());
           String Role = userScan.nextLine();
           Role = Role.replace(",","");

           userList.add(new User(Name,SurName,PNumber,Id,LoanCounter,ViolationCounter,Role));
       }

       while (bookScan.hasNext()){
           int Id = Integer.parseInt(bookScan.next());
           String Name = bookScan.next();
           int ISBN = Integer.parseInt(bookScan.next());
           int Quantity = Integer.parseInt(bookScan.next());
           String Author = bookScan.nextLine();
           Author = Author.replace(",","");
           bookList.add(new Book(Id,Name,ISBN,Quantity,Author));
       }

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

               PrintWriter printWriterUserList = new PrintWriter(UserFile);

               for (User user : userList) {
                   printWriterUserList.println(user.export(user));
               }

               printWriterUserList.close();

           } else {

               Scanner blacklisted = new Scanner(blackFile).useDelimiter(",");

               while (blacklisted.hasNext()) {
                   String Name = blacklisted.next();
                   String SurName = blacklisted.next();
                   int PNumber = Integer.parseInt(blacklisted.next());
                   int Id = Integer.parseInt(blacklisted.next());
                   int LoanCounter = Integer.parseInt(blacklisted.next());
                   int ViolationCounter = Integer.parseInt(blacklisted.next());
                   String Role = blacklisted.nextLine();
                   Role = Role.replace(",", "");

                   blackList.add(new User(Name, SurName, PNumber, Id, LoanCounter, ViolationCounter, Role));
               }

               for (User user : userList) {
                   if (id == user.Id) {
                       blackList.add(user);
                   }
               }

               PrintWriter printWriterBlackList = new PrintWriter(blackFile);

               for (User user : blackList) {
                   printWriterBlackList.println(user.export(user));
               }

               printWriterBlackList.close();

               for (User user : userList) {
                   if (id == user.Id) {
                       userList.remove(user);
                       break;
                   }
               }

               PrintWriter printWriterUserList = new PrintWriter(UserFile);

               for (User user : userList) {
                   printWriterUserList.println(user.export(user));
               }

               printWriterUserList.close();


           }

       }else {
           Scanner userLoanScan = new Scanner(UserLoanFile).useDelimiter(",");

           ArrayList<String> userLoanList = new ArrayList<String>();
           ArrayList<Book> loanedBook = new ArrayList<>();

           while (userLoanScan.hasNext()) {
               String Name = userLoanScan.next();
               String Surname = userLoanScan.next();
               String Id = userLoanScan.next();

               String Title = userLoanScan.next();
               String ISBN = userLoanScan.next();
               String Author = userLoanScan.nextLine();
               Author = Author.replace(",", "");
               if (Integer.parseInt(Id)==id) {
                   Book book = new Book(0, Title, Integer.parseInt(ISBN), 0, Author);
                   loanedBook.add(book);
               }
               userLoanList.add(Name + "," + Surname + "," + Id + "," + Title + "," + ISBN + "," + Author);
           }
           for (User user: userList) {
                if (user.Id==id){
                    for (String loan: userLoanList) {
                        if (loan.contains(Integer.toString(user.Id))){
                            for (Book book:loanedBook) {

//HÄR VILL VI HA EN RETURNERA BOK METOD FRÅN USER

                            }
                            userLoanList.remove(loan);
                        }
                    }
                }
           }
           PrintWriter users = new PrintWriter(UserFile);
           PrintWriter loanUsers = new PrintWriter(UserLoanFile);
           for (User user:userList) {
               users.println(user.export(user));
           }
           for (String userLoaner: userLoanList) {
               loanUsers.println(userLoaner);
           }
           users.close();
           loanUsers.close();
       }
   }

   public void giveTimeout(int id)throws IOException{
       Scanner timeoutListScan = new Scanner(timeoutFile).useDelimiter(",");
       Scanner userListScan = new Scanner(UserFile).useDelimiter(",");

       ArrayList<User>timeoutList = new ArrayList<User>();
       ArrayList<User>userList = new ArrayList<User>();

       while (timeoutListScan.hasNext()){
           String Name = timeoutListScan.next();
           String SurName = timeoutListScan.next();
           int PNumber = Integer.parseInt(timeoutListScan.next());
           int Id = Integer.parseInt(timeoutListScan.next());
           int LoanCounter = Integer.parseInt(timeoutListScan.next());
           int ViolationCounter = Integer.parseInt(timeoutListScan.next());

           LocalDate timer = LocalDate.parse(timeoutListScan.next());

           String Role = timeoutListScan.nextLine();
           Role = Role.replace(",","");

           timeoutList.add(new User(Name,SurName,PNumber,Id,LoanCounter,ViolationCounter,Role,timer));
       }

       for (User user:timeoutList) {
           if (id == user.Id) {
               System.out.println("Already on a timeout");
           }
       }

       while (userListScan.hasNext()){
           String Name = userListScan.next();
           String SurName = userListScan.next();
           int PNumber = Integer.parseInt(userListScan.next());
           int Id = Integer.parseInt(userListScan.next());
           int LoanCounter = Integer.parseInt(userListScan.next());
           int ViolationCounter = Integer.parseInt(userListScan.next());
           String Role = userListScan.nextLine();
           Role = Role.replace(",","");

           userList.add(new User(Name,SurName,PNumber,Id,LoanCounter,ViolationCounter,Role));
       }

       LocalDate timer = LocalDate.now();

       for (User users:userList) {
           if (id == users.Id){
               timeoutList.add(new User(users.Name,users.Surname,users.PNumber,users.Id,users.LoanCounter,users.ViolationCounter,users.Role,timer));
               userList.remove(users);
               break;
           }
       }

       PrintWriter printWriterUserList = new PrintWriter(UserFile);

       for (User use:userList) {
           printWriterUserList.println(use.export(use));
       }
       printWriterUserList.close();

       PrintWriter printWriterTimeoutList = new PrintWriter(timeoutFile);

       for (User us : timeoutList) {
           printWriterTimeoutList.println(us.timeoutExport(us));
       }

       printWriterTimeoutList.close();

   }

    public void controlTimeouts()throws IOException{

        Scanner timeoutListScan = new Scanner(timeoutFile).useDelimiter(",");
        Scanner userListScan = new Scanner(UserFile).useDelimiter(",");

        ArrayList<User>timeoutList = new ArrayList<User>();
        ArrayList<User>userList = new ArrayList<User>();

        while (timeoutListScan.hasNext()){
            String Name = timeoutListScan.next();
            String SurName = timeoutListScan.next();
            int PNumber = Integer.parseInt(timeoutListScan.next());
            int Id = Integer.parseInt(timeoutListScan.next());
            int LoanCounter = Integer.parseInt(timeoutListScan.next());
            int ViolationCounter = Integer.parseInt(timeoutListScan.next());

            LocalDate timer = LocalDate.parse(timeoutListScan.next());

            String Role = timeoutListScan.nextLine();
            Role = Role.replace(",","");

            timeoutList.add(new User(Name,SurName,PNumber,Id,LoanCounter,ViolationCounter,Role,timer));
        }

        while (userListScan.hasNext()){
            String Name = userListScan.next();
            String SurName = userListScan.next();
            int PNumber = Integer.parseInt(userListScan.next());
            int Id = Integer.parseInt(userListScan.next());
            int LoanCounter = Integer.parseInt(userListScan.next());
            int ViolationCounter = Integer.parseInt(userListScan.next());
            String Role = userListScan.nextLine();
            Role = Role.replace(",","");

            userList.add(new User(Name,SurName,PNumber,Id,LoanCounter,ViolationCounter,Role));
        }

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

            PrintWriter printWriterTimeoutList = new PrintWriter(timeoutFile);

            PrintWriter printWriterUserList = new PrintWriter(UserFile);

            for (User users : timeoutList) {
                printWriterTimeoutList.println(users.timeoutExport(users));
            }
            printWriterTimeoutList.close();

            for (User use : userList) {
                printWriterUserList.println(use.export(use));
            }
            printWriterUserList.close();

        }

    }


   public Book getBookByISBN(int ISBN)throws IOException{

       Scanner bookScanByISBN = new Scanner(AllBooksFile).useDelimiter(",");

       ArrayList<Book>bookList = new ArrayList<Book>();

       while (bookScanByISBN.hasNext()){
           int Id = Integer.parseInt(bookScanByISBN.next());
           String Name = bookScanByISBN.next();
           int ISBNScan = Integer.parseInt(bookScanByISBN.next());
           int Quantity = Integer.parseInt(bookScanByISBN.next());
           String Author = bookScanByISBN.nextLine();
           Author = Author.replace(",","");
           bookList.add(new Book(Id,Name,ISBNScan,Quantity,Author));
       }

       Book theBook = new Book();

       for (Book books: bookList) {
           if (books.ISBN == ISBN){
               theBook = books;
           }
       }
        return theBook;
   }

   public void lendBook(Book theBook, int userId)throws IOException {

       Scanner userScan = new Scanner(UserFile).useDelimiter(",");

       ArrayList<User>userList = new ArrayList<User>();

       while (userScan.hasNext()){
           String Name = userScan.next();
           String SurName = userScan.next();
           int PNumber = Integer.parseInt(userScan.next());
           int Id = Integer.parseInt(userScan.next());
           int LoanCounter = Integer.parseInt(userScan.next());
           int ViolationCounter = Integer.parseInt(userScan.next());
           String Role = userScan.nextLine();
           Role = Role.replace(",","");

           userList.add(new User(Name,SurName,PNumber,Id,LoanCounter,ViolationCounter,Role));
       }

       User tempUser = new User();

       for (User user:userList) {
           if (userId == user.getId()){
               tempUser = user;
           }
       }

       Scanner bookScanLend = new Scanner(AllBooksFile).useDelimiter(",");

       ArrayList<Book> bookList = new ArrayList<Book>();

       while (bookScanLend.hasNext()) {
           int Id = Integer.parseInt(bookScanLend.next());
           String Name = bookScanLend.next();
           int ISBNScan = Integer.parseInt(bookScanLend.next());
           int Quantity = Integer.parseInt(bookScanLend.next());
           String Author = bookScanLend.nextLine();
           Author = Author.replace(",", "");

           bookList.add(new Book(Id, Name, ISBNScan, Quantity, Author));
       }

       if (tempUser.LoanCounter > 0){
           for (Book book:bookList) {
               if (book.Title.equals(theBook.Title)){
                   if (book.Quantity > 0){

                       Scanner userLoanScan = new Scanner(UserLoanFile).useDelimiter(",");

                       ArrayList<String> userLoanList = new ArrayList<String>();

                       while (userLoanScan.hasNext()) {
                           String Name = userLoanScan.next();
                           String Surname = userLoanScan.next();
                           String Id = userLoanScan.next();

                           String Title = userLoanScan.next();
                           String ISBN = userLoanScan.next();
                           String Author = userLoanScan.nextLine();
                           Author = Author.replace(",", "");

                         userLoanList.add(Name + "," + Surname + "," + Id + "," + Title + "," + ISBN + "," + Author);
                       }

                       String tempLoanString = tempUser.loanExport(tempUser) + book.loanExport(book);

                       userLoanList.add(tempLoanString);

                       PrintWriter printWriterUserLoanFile = new PrintWriter(UserLoanFile);

                       for (String row:userLoanList) {
                           printWriterUserLoanFile.println(row);
                       }

                       printWriterUserLoanFile.close();

                       book.Quantity--;

                       tempUser.LoanCounter--;

                       for (User user:userList) {
                           if (userId == user.Id){
                               user = tempUser;
                           }
                       }

                       PrintWriter printWriterUserList = new PrintWriter(UserFile);

                       for (User user: userList) {
                           printWriterUserList.println(user.export(user));
                       }
                       printWriterUserList.close();

                   }else {
                       System.out.println("The title is not available right now");
                   }
               }
           }

       } else {
           System.out.println("You have too many loaned books");
       }

       PrintWriter printWriterAllBooks = new PrintWriter(AllBooksFile);

       for (Book books : bookList) {
           printWriterAllBooks.println(books.export(books));
       }

       printWriterAllBooks.close();

   }
}