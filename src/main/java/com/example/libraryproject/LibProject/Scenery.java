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

       public File AllBooksFile = new File("src/main/java/com/example/libraryproject/LibProject/AllBooks.txt");
       public File UserLoanFile = new File("src/main/java/com/example/libraryproject/LibProject/LoanedBooks.txt");
       public File UserFile = new File("src/main/java/com/example/libraryproject/LibProject/WhiteList.txt");
       public File blackFile = new File("src/main/java/com/example/libraryproject/LibProject/BlackList.txt");
       public File timeoutFile = new File("src/main/java/com/example/libraryproject/LibProject/TimeoutList.txt");

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

            //Objekt av olika klasser
               Librarian librarianObj = new Librarian();            
               Book bookObj;
               User userObj = new User();

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
            Text surname = new Text();
            surname.setFont(fira);
            Font tre = new Font(14);
            Text role = new Text();
            role.setFont(tre);
            Text idid = new Text();
            idid.setFont(tre);
            Text violationCounter = new Text();
            violationCounter.setFont(tre);
            Text prNumber = new Text();
            prNumber.setFont(tre);
            Text loanCount = new Text();
            loanCount.setFont(tre);


            ToggleGroup deleteTgl = new ToggleGroup();
             RadioButton deleteByChoise = new RadioButton("Detele by choies");
             RadioButton deleteByPenalties = new RadioButton("Delete by penalties");
             deleteByChoise.setToggleGroup(deleteTgl); deleteByPenalties.setToggleGroup(deleteTgl);
             deleteByPenalties.setVisible(false); deleteByChoise.setVisible(false);

            memberList.getSelectionModel().selectedIndexProperty().addListener(ov -> {
                namn.setText(userList.get(memberList.getSelectionModel().getSelectedIndex()).getName()+ " ");
                surname.setText(userList.get(memberList.getSelectionModel().getSelectedIndex()).getSurname()+ "\n" );
                role.setText(userList.get(memberList.getSelectionModel().getSelectedIndex()).getRole()+ "\n" + "\n" + "Id: ");
                idid.setText(String.valueOf(userList.get(memberList.getSelectionModel().getSelectedIndex()).getId())+"\n" + "Personal Nr: ");
                prNumber.setText(String.valueOf(userList.get(memberList.getSelectionModel().getSelectedIndex()).getPNumber())+"\n" + "Violation counter: ");
                violationCounter.setText(String.valueOf(userList.get(memberList.getSelectionModel().getSelectedIndex()).getViolationCounter())+"\n" + "Loan counter: ");
                loanCount.setText(String.valueOf(userList.get(memberList.getSelectionModel().getSelectedIndex()).getLoanCounter())+"\n");

            });

            TextFlow textMembers = new TextFlow(namn, surname, role, idid, prNumber, violationCounter, loanCount);
            textMembers.setPrefWidth(250);

            //Medlem ansöker om att låna en bok
            Label titleRequestLoan = new Label("Request for a loan");
            titleRequestLoan.setFont(new Font("Fira Sans", 32));
            Label bookTitleLbl = new Label("Book title:");
            TextField bookTitleTxt = new TextField();
            Button searchBookBtn = new Button("Search Book");

            ToggleGroup tgl = new ToggleGroup();
            RadioButton loanBook1 = new RadioButton();

            loanBook1.setToggleGroup(tgl);

            Text bookUserSearchTxt = new Text();
            Button loanBookBtn = new Button("Loan Book");

            //Medlem ser alla lånade böcker
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
            Label nameMemberLoginLbl = new Label("Name:");
            TextField nameMemberLoginTxt = new TextField();
            Label idMemberLoginLbl = new Label("ID-number:");
            TextField idMemberLoginTxt = new TextField();
            Text idForUser = new Text();
            idForUser.setVisible(false);

            //Logga in som bibliotekarie
            Label nameLibrarianLbl = new Label("Name:");
            TextField nameLibrarianTxt = new TextField();
            Label idLibrarianLbl = new Label("ID-number:");
            TextField idLibrarianTxt = new TextField();

            //Skapa nytt konto
            Label nameMemberRegisterLbl = new Label("Nam");
            nameMemberRegisterLbl.setVisible(false);
            TextField nameMemberRegisterTxt = new TextField();
            nameMemberRegisterTxt.setVisible(false);
            Label surnameMemberLbl = new Label("Surname:");
            TextField surnameMemberTxt = new TextField();
            surnameMemberTxt.setVisible(false);
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

            //Ändra tillbaka till att kunna adda en bok
            Button changeSearchAddBookBtn2 = new Button("To Add"); changeSearchAddBookBtn2.setVisible(false);

            //Bibliotekarie söker efter en bok
            Button changeSearchAddBookBtn = new Button("To Search");

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

             //Bibliotekarien raderar en befintlig medlem
             Button formDeteleUserBtn = new Button("To delete");
             Button overviewMembersBtn = new Button("To overview");
             overviewMembersBtn.setVisible(false);
             Button deleteUserBtn = new Button("Delete User");
             Label userIdLbl = new Label("User ID: ");
             userIdLbl.setVisible(false);
             TextField userIdTxt = new TextField();
             userIdTxt.setVisible(false);
             deleteUserBtn.setVisible(false);

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
                nameMemberLoginTxt.clear(); idMemberLoginTxt.clear();
                titleMember.setVisible(false); titleNewAccount.setVisible(true);
                registerNewAccount.setVisible(false); loginAsAMember.setVisible(true);
                loginBtn.setVisible(false); registerBtn.setVisible(true);
                nameMemberLoginTxt.setVisible(false); nameMemberLoginTxt.setVisible(false);
                nameMemberRegisterLbl.setVisible(true); nameMemberRegisterTxt.setVisible(true);
                idMemberLoginLbl.setVisible(false); idMemberLoginTxt.setVisible(false);
                surnameMemberLbl.setVisible(true); surnameMemberTxt.setVisible(true);
                personalNrMemberLbl.setVisible(true); personalNrMemberTxt.setVisible(true);
                chooseRole.setVisible(true);
            });

            loginAsAMember.setOnAction(e-> {
                nameMemberLoginTxt.clear(); surnameMemberTxt.clear(); personalNrMemberTxt.clear();
                titleMember.setVisible(true); titleNewAccount.setVisible(false);
                registerNewAccount.setVisible(true); loginAsAMember.setVisible(false);
                loginBtn.setVisible(true); registerBtn.setVisible(false);
                nameMemberLoginLbl.setVisible(true); nameMemberLoginTxt.setVisible(true);
                nameMemberRegisterLbl.setVisible(false); nameMemberRegisterTxt.setVisible(false);
                idMemberLoginLbl.setVisible(true); idMemberLoginTxt.setVisible(true);
                surnameMemberLbl.setVisible(false); surnameMemberTxt.setVisible(false);
                personalNrMemberLbl.setVisible(false); personalNrMemberTxt.setVisible(false);
                chooseRole.setVisible(false);
            });

            loginAsLibraryan.setOnAction(e-> {

                try {
                    if(librarianObj.loginLibrarian(nameLibrarianTxt.getText(), Integer.parseInt(idLibrarianTxt.getText()))) {
                    titleLibrarian.setVisible(true);
                    nameLibrarianTxt.setVisible(true);
                    nameLibrarianLbl.setVisible(true);
                    idLibrarianLbl.setVisible(true);
                    idLibrarianTxt.setVisible(true);
                    loginAsLibraryan.setVisible(true);
                    leftControl.getChildren().clear();
                    leftControl.getChildren().addAll(paneLeft2);
                    paneLeft2.setVisible(true);
                } else {
                    Alert aler = new Alert(Alert.AlertType.INFORMATION);
                    aler.setHeaderText("This isn't an active librarian account ");
                    aler.showAndWait();
                }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });

            loginBtn.setOnAction(e-> {
                String name = nameMemberLoginTxt.getText();
                String id = idMemberLoginTxt.getText();
                int idNr = Integer.parseInt(id);
                try {
                    boolean verify = userObj.loginUser(name,idNr);
                    if (verify){
                        rightControl.getChildren().clear();
                        paneRight.setVisible(false);
                        rightControl.getChildren().addAll(paneRight2);
                        paneRight2.setVisible(true);
                        loanBook1.setVisible(false);
                        idForUser.setText("User: " + name + "/ID: "+Integer.toString(idNr));
                        idForUser.setVisible(true);
                    } else {
                        Alert aler = new Alert(Alert.AlertType.INFORMATION);
                        aler.setHeaderText("User doesnt exist");
                        aler.showAndWait();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
                    Text getIdForBookToSendForLoan = new Text();
            //Fixa så att man får fram det böcker som man söker på
            searchBookBtn.setOnAction(e-> {
                Book bookObc = new Book();
                String bokName;
                int iddd;
                try {
                    bookObc = userObj.searchTitle(bookTitleTxt.getText());
                    bokName = bookObc.getTitle();
                    iddd = bookObc.getId();
                    getIdForBookToSendForLoan.setText(Integer.toString(iddd));
                    loanBook1.setText(bokName);
                    loanBook1.setVisible(true);

                    //Lägg till loan book knappen till att kunna skicka förfrågan om att låna bok
                    } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                loanBookBtn.setOnAction(event-> {

                    String bName;
                    bName = loanBook1.getText();
                    String pName;
                    pName = nameMemberLoginTxt.getText();
                    String surName; // Få tag på
                    surName = userObj.getSurname();
                    int pNumber;
                    pNumber = Integer.parseInt(idMemberLoginTxt.getText());
                    int idBok;
                    idBok = Integer.parseInt(getIdForBookToSendForLoan.getText());

                    if (loanBook1.isSelected()) {
                        try {
                           userObj.requestLoan(idBok,bName,pName,surName,pNumber);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        Alert aler = new Alert(Alert.AlertType.INFORMATION);
                        aler.setHeaderText("Select a book");
                        aler.showAndWait();
                    }

                });
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
//J
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

            formDeteleUserBtn.setOnAction(e->{
                memberList.setVisible(false); textMembers.setVisible(false);
                overviewMembersBtn.setVisible(true);
                deleteByChoise.setVisible(true); deleteByPenalties.setVisible(true); deleteUserBtn.setVisible(true);
                overviewMembersBtn.setVisible(true);
                userIdLbl.setVisible(true); userIdTxt.setVisible(true);
                    });

            overviewMembersBtn.setOnAction(e->{
                formDeteleUserBtn.setVisible(true); overviewMembersBtn.setVisible(false);
                memberList.setVisible(true); textMembers.setVisible(true);
                deleteByChoise.setVisible(false); deleteByPenalties.setVisible(false); deleteUserBtn.setVisible(false);
                formDeteleUserBtn.setVisible(true);
                userIdLbl.setVisible(false); userIdTxt.setVisible(false);
                    });

            //Lägg till på vänster sida login funktionen
            paneLeft.add(titleLibrarian,0,0);
            paneLeft.add(nameLibrarianLbl,0,1); paneLeft.add(nameLibrarianTxt,1,1);
            paneLeft.add(idLibrarianLbl,0,2); paneLeft.add(idLibrarianTxt,1,2);
            paneLeft.add(loginAsLibraryan,0,5);

            //Lägg till på höger sida login funktion
            paneRight.add(titleMember,0,0);   paneRight.add(titleNewAccount,0,0);
            paneRight.add(loginAsAMember,0,1); paneRight.add(registerNewAccount,0,1);
            paneRight.add(nameMemberLoginLbl,0,2); paneRight.add(nameMemberLoginTxt,1,2); paneRight.add(nameMemberRegisterLbl,0,2); paneRight.add(nameMemberRegisterTxt,1,2);
            paneRight.add(idMemberLoginLbl,0,3); paneRight.add(idMemberLoginTxt,1,3);
            paneRight.add(personalNrMemberLbl,0,4); paneRight.add(personalNrMemberTxt,1,4);
            paneRight.add(surnameMemberLbl,0,3); paneRight.add(surnameMemberTxt,1,3);
            paneRight.add(loginBtn,1,6); paneRight.add(registerBtn,1,6);
            paneRight.add(chooseRole,2,1);

            //Lägg till på vänster sida - det som bibliotekarien ser
            paneLeft2.add(titleAddNewBook,0,0); paneLeft2.add(titleSearchBook,0,0);
            paneLeft2.add(changeSearchAddBookBtn,0,1); paneLeft2.add(changeSearchAddBookBtn2,0,1);
            paneLeft2.add(bookIDLbl,0,2); paneLeft2.add(bookIdTxt,1,2); paneLeft2.add(searchBookISBNLbl,0,2); paneLeft2.add(searchBookISBNTxt,1,2);
            paneLeft2.add(bookNameLbl,0,3); paneLeft2.add(bookNameTxt,1,3);
            paneLeft2.add(bookAuthorLbl,0,4); paneLeft2.add(bookAuthorTxt,1,4);
            paneLeft2.add(bookISBNLbl,0,5); paneLeft2.add(bookISBNTxt,1,5);
            paneLeft2.add(bookQuantityLbl,0,6); paneLeft2.add(bookQuantityTxt,1,6);
            paneLeft2.add(formDeteleUserBtn,0,8); paneLeft2.add(overviewMembersBtn,0,8);
            paneLeft2.add(addANewBookBtn,1,7); paneLeft2.add(addBookText,2,7); paneLeft2.add(searchBookByISBNBtn,1,7);

            paneLeft2.add(bokA,0,3); paneLeft2.add(bokId,0,4); paneLeft2.add(bokN,0,5); paneLeft2.add(bokQ,0,6); paneLeft2.add(bokISBNN,0,7);

            paneLeft2.add(userIdLbl,0,9); paneLeft2.add(userIdTxt,1,9);
            paneLeft2.add(memberList,0,9); paneLeft2.add(textMembers,1,9);
            paneLeft2.add(deleteByChoise, 0,10); paneLeft2.add(deleteByPenalties,1,10); paneLeft2.add(deleteUserBtn,2,10);
            paneLeft2.setVisible(false);

            //Lägg till på höger sida - det som användaren ser
            paneRight2.add(titleRequestLoan,0,0); paneRight2.add(idForUser,1,0);
            paneRight2.add(bookTitleLbl,0,1); paneRight2.add(bookTitleTxt,1,1); paneRight2.add(searchBookBtn,2,1);
            paneRight2.add(loanBook1,0,2);
            paneRight2.add(bookUserSearchTxt,0,2);
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
            Scene scene = new Scene(root,1250,650);
            primaryStage.setTitle("Library");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
}