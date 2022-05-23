package com.example.libraryproject.LibProject;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;
import java.util.jar.Attributes;


public class Scenery extends Application {

   /*    public File AllBooksFile = new File("src/main/java/com/example/libraryproject/LibProject/AllBooks.txt");
       public File UserLoanFile = new File("src/main/java/com/example/libraryproject/LibProject/LoanedBooks.txt");
       public File UserFile = new File("src/main/java/com/example/libraryproject/LibProject/WhiteList.txt");
       public File blackFile = new File("src/main/java/com/example/libraryproject/LibProject/BlackList.txt");
       public File timeoutFile = new File("src/main/java/com/example/libraryproject/LibProject/TimeoutList.txt");


    */
        public static void main(String[] args) {

       launch(args);
        }

        @Override
        public void start(Stage primaryStage) {

            VBox root = new VBox();
            root.setSpacing(5);
            root.setPadding(new Insets(5, 5, 5, 5));
            Label titleLibrarian = new Label("Login as a libriarian");
            Label titleMember = new Label("Login as a member");
            Label titleNewAccount = new Label("Register new account");
            titleNewAccount.setVisible(false);
            titleLibrarian.setFont(new Font("Fira Sans", 32));
            titleMember.setFont(new Font("Fira Sans", 32));
            titleNewAccount.setFont(new Font("Fira Sans", 32));

            SplitPane splitPane = new SplitPane();

            VBox leftControl  = new VBox(new Label("Left Control"));
            VBox rightControl = new VBox(new Label("Right Control"));


            GridPane paneLeft = new GridPane();
            paneLeft.setAlignment(Pos.BASELINE_LEFT);
            paneLeft.setHgap(5);
            paneLeft.setVgap(5);

            GridPane paneRight = new GridPane();
            paneRight.setAlignment(Pos.BASELINE_LEFT);
            paneRight.setHgap(5);
            paneRight.setVgap(5);

            GridPane paneLeft2 = new GridPane();
            paneLeft2.setAlignment(Pos.BASELINE_LEFT);
            paneLeft2.setHgap(5);
            paneLeft2.setVgap(5);

            GridPane paneRight2 = new GridPane();
            paneRight2.setAlignment(Pos.BASELINE_LEFT);
            paneRight2.setHgap(5);
            paneRight2.setVgap(5);

            File UserFile = new File("/Users/stefanbucei/LibraryProject/src/main/java/com/example/libraryproject/LibProject/WhiteList.txt");
            Scanner userScan = null;
            try {
                userScan = new Scanner(UserFile).useDelimiter(",");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            ArrayList<User> userList = new ArrayList<>();

            while (userScan.hasNext()){
                 String Name = userScan.next();
                 String SurName = userScan.next();
                 int PNumber = Integer.parseInt(userScan.next());
                 int Id = Integer.parseInt(userScan.next());
                 int LocalCounter = Integer.parseInt(userScan.next());
                 int ViolationCounter = Integer.parseInt(userScan.next());
                 String Role = userScan.nextLine();
                 Role = Role.replace(",","");
                 userList.add(new User(Name,SurName,PNumber,Id,LocalCounter,ViolationCounter,Role));
            }

            ListView<String> memberList = new ListView<>();
            memberList.setPrefWidth(150);
            for ( int i = 0; i <userList.size(); i++){
              memberList.getItems().addAll(userList.get(i).Name);  
            }



            Font fira = new Font(15);
            Text namn = new Text();
            namn.setStyle("-fx-font-weight: bold");
            namn.setFont(fira);
            Font tre = new Font(10);
            Text role = new Text();
            role.setFont(fira);
            Text surname = new Text();
            surname.setFont(fira);

            memberList.getSelectionModel().selectedIndexProperty().addListener(ov -> {
                namn.setText(userList.get(memberList.getSelectionModel().getSelectedIndex()).getName()+ "\n");
                role.setText(userList.get(memberList.getSelectionModel().getSelectedIndex()).getRole()+ "\n" + "\n");
                surname.setText(userList.get(memberList.getSelectionModel().getSelectedIndex()).getSurname());
            });

            TextFlow textMembers = new TextFlow(namn, role, surname);
            textMembers.setPrefWidth(250);

            //Medlem ansöker om att låna en bok
            Label titleRequestLoan = new Label("Request for a loan");
            titleRequestLoan.setFont(new Font("Fira Sans", 32));
            Label bookTitleLbl = new Label("Book title:");
            TextField bookTitleTxt = new TextField();
            Button searchBookBtn = new Button("Search Book");

            ToggleGroup tgl = new ToggleGroup();
            RadioButton loanBook1 = new RadioButton("Book Name");
            RadioButton loanBook2 = new RadioButton("Book Name");
            RadioButton loanBook3 = new RadioButton("Book Name");
            RadioButton loanBook4 = new RadioButton("Book Name");

            loanBook1.setToggleGroup(tgl);
            loanBook2.setToggleGroup(tgl);
            loanBook3.setToggleGroup(tgl);
            loanBook4.setToggleGroup(tgl);

            Button loanBookBtn = new Button("Loan Book");

            //Medlem se alla lånade böcker
            Label titleAllLoanedBooks = new Label("All loaned Books");
            titleAllLoanedBooks.setFont(new Font("Fira Sans", 32));

            ToggleGroup tglReturnBook = new ToggleGroup();
            RadioButton returnBook1 = new RadioButton("Book Name");
            RadioButton returnBook2 = new RadioButton("Book Name");
            RadioButton returnBook3 = new RadioButton("Book Name");
            RadioButton returnBook4 = new RadioButton("Book Name");

            returnBook1.setToggleGroup(tglReturnBook);
            returnBook2.setToggleGroup(tglReturnBook);
            returnBook3.setToggleGroup(tglReturnBook);
            returnBook4.setToggleGroup(tglReturnBook);
        
            Button returnBookBtn = new Button("Return Book");

            //Logga in som medlem
            Label nameMemberLbl = new Label("Name:");
            TextField nameMemberTxt = new TextField();
            Label idMemberLbl = new Label("ID-number:");
            TextField idMemberTxt = new TextField();

            //Logga in som bibliotikarie
            Label nameLibrarianLbl = new Label("Name:");
            TextField nameLibrarianTxt = new TextField();
            Label idLibrarianLbl = new Label("ID-number:");
            TextField idLibrarianTxt = new TextField();

            //Skapa nytt konto
            Label surnameMemberLbl = new Label("Surname:"); TextField surnameMemberTxt = new TextField();
            surnameMemberLbl.setVisible(false);
            Label personalNrMemberLbl = new Label("Personal Number:");
            TextField personalNrMemberTxt = new TextField();
            personalNrMemberLbl.setVisible(false);
            personalNrMemberTxt.setVisible(false);

            //Lägg till en ny bok
            Label titleAddNewBook = new Label("Add a new Book");
            titleAddNewBook.setFont(new Font("Fira Sans", 32));
            Label bookIDLbl = new Label("ID:"); TextField bookIdTxt = new TextField();
            Label bookNameLbl = new Label("Title:"); TextField bookNameTxt = new TextField();
            Label bookAuthorLbl = new Label("Author:"); TextField bookAuthorTxt = new TextField();
            Label bookISBNLbl = new Label("ISBN:"); TextField bookISBNTxt = new TextField();
            Label bookQuantityLbl = new Label("Quantity:"); TextField bookQuantityTxt = new TextField();
            Button addANewBookBtn = new Button("Add a new Book");
            bookIdTxt.setMaxWidth(80); bookNameTxt.setMaxWidth(80); bookAuthorTxt.setMaxWidth(80); bookISBNTxt.setMaxWidth(80); bookQuantityTxt.setMaxWidth(80);
            Label addBookText = new Label();

            Librarian librarianObj = new Librarian();
            Book bookObj;

            //Ändra tillbaka till att kunna adda en bok
            Button changeSearchAddBookBtn2 = new Button("Add"); changeSearchAddBookBtn2.setVisible(false);

            //Bibliotekarie söker efter en bok
            Button changeSearchAddBookBtn = new Button("Search");

            Label titleSearchBook = new Label("Search for book"); titleSearchBook.setVisible(false);
            titleSearchBook.setFont(new Font("Fira Sans", 32));
            Label searchBookISBNLbl = new Label("Search after ISBN:"); TextField searchBookISBNTxt = new TextField(); searchBookISBNTxt.setMaxWidth(80);
            searchBookISBNLbl.setVisible(false); searchBookISBNTxt.setVisible(false);
            Button searchBookByISBNBtn = new Button("Search book");
            searchBookByISBNBtn.setVisible(false);

            Text bokN = new Text();
            Text bokId = new Text();
            Text bokA = new Text();
            Text bokQ = new Text();
            Text bokISBNN = new Text();

            //Bibliotekarien deletar en befintlig medlem
            Button deleteUserBtn = new Button("Delete User");

            //--------------------------------------------------------//
            Button registerNewAccount = new Button("Register new account ");
            Button loginAsAMember = new Button("Login as a member");
            loginAsAMember.setVisible(false);

            Button loginAsLibraryan = new Button("Login as a librarian ");

            Button loginBtn = new Button("Login ");
            Button registerBtn = new Button("Create account");
            registerBtn.setVisible(false);

            ComboBox<String> chooseRole = new ComboBox<>();
            chooseRole.setVisible(false);

            registerNewAccount.setOnAction(e-> {
                nameMemberTxt.clear(); idMemberTxt.clear();
                titleMember.setVisible(false); titleNewAccount.setVisible(true);
                registerNewAccount.setVisible(false); loginAsAMember.setVisible(true);
                loginBtn.setVisible(false); registerBtn.setVisible(true);
                nameMemberTxt.setVisible(true); nameMemberTxt.setVisible(true);
                idMemberLbl.setVisible(false); idMemberTxt.setVisible(false);
                surnameMemberLbl.setVisible(true); surnameMemberTxt.setVisible(true);
                personalNrMemberLbl.setVisible(true); personalNrMemberTxt.setVisible(true);
                chooseRole.setVisible(true);
            });

            loginAsAMember.setOnAction(e-> {
                nameMemberTxt.clear();
                titleMember.setVisible(true); titleNewAccount.setVisible(false);
                registerNewAccount.setVisible(true); loginAsAMember.setVisible(false);
                loginBtn.setVisible(true); registerBtn.setVisible(false);
                nameMemberTxt.setVisible(true); nameMemberTxt.setVisible(true);
                idMemberLbl.setVisible(true); idMemberTxt.setVisible(true);
                surnameMemberLbl.setVisible(false); surnameMemberTxt.setVisible(false);
                personalNrMemberLbl.setVisible(false); personalNrMemberTxt.setVisible(false);
                chooseRole.setVisible(false);
            });

            loginAsLibraryan.setOnAction(e-> {
                titleLibrarian.setVisible(true);
                nameLibrarianTxt.setVisible(true); nameLibrarianLbl.setVisible(true);
                idLibrarianLbl.setVisible(true); idLibrarianTxt.setVisible(true);
                loginAsLibraryan.setVisible(true);
                leftControl.getChildren().clear();
                leftControl.getChildren().addAll(paneLeft2);
                paneLeft2.setVisible(true);
            });

            loginBtn.setOnAction(e-> {
                rightControl.getChildren().clear();
                paneRight.setVisible(false);
                rightControl.getChildren().addAll(paneRight2);
                paneRight2.setVisible(true);
                loanBook1.setVisible(false);  loanBook2.setVisible(false);  loanBook3.setVisible(false);  loanBook4.setVisible(false);
            });

            //Fixa så att man får fram det böcker som man söker på
            searchBookBtn.setOnAction(e-> {

                loanBook1.setVisible(true);
                loanBook2.setVisible(true);
                loanBook3.setVisible(true);
                loanBook4.setVisible(true);
                    });

            registerBtn.setOnAction(e-> {
                rightControl.getChildren().clear();

                    });

            changeSearchAddBookBtn.setOnAction(e->  {
             titleAddNewBook.setVisible(false);
             bookIDLbl.setVisible(false); bookIdTxt.setVisible(false);
             bookNameLbl.setVisible(false); bookNameTxt.setVisible(false);
             bookAuthorLbl.setVisible(false); bookAuthorTxt.setVisible(false);
             bookISBNLbl.setVisible(false); bookISBNTxt.setVisible(false);
             bookQuantityLbl.setVisible(false); bookQuantityTxt.setVisible(false);
             addANewBookBtn.setVisible(false);
             changeSearchAddBookBtn2.setVisible(true);
             changeSearchAddBookBtn.setVisible(false);
             searchBookISBNLbl.setVisible(true); searchBookISBNTxt.setVisible(true); searchBookByISBNBtn.setVisible(true);
             titleSearchBook.setVisible(true);
             bokN.setVisible(true); bokA.setVisible(true); bokId.setVisible(true); bokISBNN.setVisible(true); bokQ.setVisible(true);
             addBookText.setVisible(false);

                  });

            changeSearchAddBookBtn2.setOnAction(e ->{
             changeSearchAddBookBtn.setVisible(true);
             changeSearchAddBookBtn2.setVisible(false);
             titleAddNewBook.setVisible(true);
             bookIDLbl.setVisible(true); bookIdTxt.setVisible(true);
             bookNameLbl.setVisible(true); bookNameTxt.setVisible(true);
             bookAuthorLbl.setVisible(true); bookAuthorTxt.setVisible(true);
             bookISBNLbl.setVisible(true); bookISBNTxt.setVisible(true);
             bookQuantityLbl.setVisible(true); bookQuantityTxt.setVisible(true);
             addANewBookBtn.setVisible(true);
             searchBookISBNLbl.setVisible(false); searchBookISBNTxt.setVisible(false); searchBookByISBNBtn.setVisible(false);
             titleSearchBook.setVisible(false);
             bokN.setVisible(false); bokA.setVisible(false); bokId.setVisible(false); bokISBNN.setVisible(false); bokQ.setVisible(false);
             addBookText.setVisible(true);

                });

            searchBookByISBNBtn.setOnAction(e-> {
                Book bookObc = new Book();
                String ISBNtext = searchBookISBNTxt.getText();
                int id;
                String bokName;
                int ISBN;
                int bokQuant;
                String bokAuthor;

                try {
                   int ISBNnr = Integer.parseInt(ISBNtext);
                   bookObc = librarianObj.getBookByISBN(ISBNnr);
                   id = bookObc.getId();
                   bokName = bookObc.getTitle();
                   ISBN = bookObc.getISBN();
                   bokQuant = bookObc.getQuantity();
                   bokAuthor = bookObc.getAuthor();

                   bokId.setText("ID: " + Integer.toString(id));
                   bokN.setText("Title: " + bokName);
                   bokISBNN.setText("ISBN: " + Integer.toString(ISBN));
                   bokQ.setText("Quantity: " + Integer.toString(bokQuant));
                   bokA.setText("Author: " + bokAuthor);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });

            addANewBookBtn.setOnAction(e-> {
                try {
                    librarianObj.addBook(Integer.parseInt(bookIdTxt.getText()),bookNameTxt.getText(),Integer.parseInt(bookISBNTxt.getText()),Integer.parseInt(bookQuantityTxt.getText()),bookAuthorTxt.getText());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                bookIdTxt.clear(); bookNameTxt.clear(); bookISBNTxt.clear(); bookQuantityTxt.clear(); bookAuthorTxt.clear();
                addBookText.setText("Book added");
            });

            //Lägg till på vänster sida login funktionen
            paneLeft.add(titleLibrarian,0,0);
            paneLeft.add(nameLibrarianLbl,0,1); paneLeft.add(nameLibrarianTxt,1,1);
            paneLeft.add(idLibrarianLbl,0,2); paneLeft.add(idLibrarianTxt,1,2);
            paneLeft.add(loginAsLibraryan,0,5);

            //Lägg till på höger sida login funktion
            paneRight.add(titleMember,0,0);   paneRight.add(titleNewAccount,0,0);
            paneRight.add(loginAsAMember,0,1); paneRight.add(registerNewAccount,0,1);
            paneRight.add(nameMemberLbl,0,2); paneRight.add(nameMemberTxt,1,2);
            paneRight.add(idMemberLbl,0,3); paneRight.add(idMemberTxt,1,3);
            paneRight.add(personalNrMemberLbl,0,4); paneRight.add(personalNrMemberTxt,1,4);
            paneRight.add(surnameMemberLbl,0,3); paneRight.add(surnameMemberTxt,1,3);
            paneRight.add(loginBtn,1,6); paneRight.add(registerBtn,1,6);
            paneRight.add(chooseRole,2,1);

            //Lägg till på vänster sida -  det som bibliotikarien ser
            paneLeft2.add(titleAddNewBook,0,0); paneLeft2.add(titleSearchBook,0,0);
            paneLeft2.add(changeSearchAddBookBtn,0,1); paneLeft2.add(changeSearchAddBookBtn2,0,1);
            paneLeft2.add(bookIDLbl,0,2); paneLeft2.add(bookIdTxt,1,2); paneLeft2.add(searchBookISBNLbl,0,2); paneLeft2.add(searchBookISBNTxt,1,2);
            paneLeft2.add(bookNameLbl,0,3); paneLeft2.add(bookNameTxt,1,3);
            paneLeft2.add(bookAuthorLbl,0,4); paneLeft2.add(bookAuthorTxt,1,4);
            paneLeft2.add(bookISBNLbl,0,5); paneLeft2.add(bookISBNTxt,1,5);
            paneLeft2.add(bookQuantityLbl,0,6); paneLeft2.add(bookQuantityTxt,1,6);
            paneLeft2.add(addANewBookBtn,1,7); paneLeft2.add(addBookText,2,7); paneLeft2.add(searchBookByISBNBtn,1,7);

            paneLeft2.add(bokA,0,3); paneLeft2.add(bokId,0,4); paneLeft2.add(bokN,0,5); paneLeft2.add(bokQ,0,6); paneLeft2.add(bokISBNN,0,7);
         
            paneLeft2.add(memberList,0,8); paneLeft2.add(textMembers,1,8);
            paneLeft2.add(deleteUserBtn,2,8);
            paneLeft2.setVisible(false);

            //Lägg till på höger sida - det som användaren ser
            paneRight2.add(titleRequestLoan,0,0);
            paneRight2.add(bookTitleLbl,0,1); paneRight2.add(bookTitleTxt,1,1); paneRight2.add(searchBookBtn,2,1);
            paneRight2.add(loanBook1,0,2); paneRight2.add(loanBook2,0,3); paneRight2.add(loanBook3,0,4); paneRight2.add(loanBook4,0,5);
            paneRight2.add(loanBookBtn,1,6);
            paneRight2.add(titleAllLoanedBooks,0,7);
            paneRight2.add(returnBook1,0,8); paneRight2.add(returnBook2,0,9); paneRight2.add(returnBook3,0,10); paneRight2.add(returnBook4,0,11);
            paneRight2.add(returnBookBtn,1,12);
            paneRight2.setVisible(false);

            leftControl.getChildren().addAll(paneLeft);
            rightControl.getChildren().addAll(paneRight);

            rightControl.getChildren().addAll(paneRight2);

            splitPane.getItems().addAll(leftControl,rightControl);
            root.getChildren().addAll(splitPane);
            Scene scene = new Scene(root,1000,600);
            primaryStage.setTitle("Library");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
}