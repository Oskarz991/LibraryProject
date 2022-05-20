package com.example.libraryproject.LibProject;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Scenery extends Application {

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
            searchBookBtn.setOnAction(e-> {
                loanBook1.setVisible(true);
                loanBook2.setVisible(true);
                loanBook3.setVisible(true);
                loanBook4.setVisible(true);
                    });

            registerBtn.setOnAction(e-> {
                rightControl.getChildren().clear();

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
            paneLeft2.add(titleAddNewBook,0,0);
            paneLeft2.add(bookIDLbl,0,1); paneLeft2.add(bookIdTxt,1,1);
            paneLeft2.add(bookNameLbl,0,2); paneLeft2.add(bookNameTxt,1,2);
            paneLeft2.add(bookAuthorLbl,0,3); paneLeft2.add(bookAuthorTxt,1,3);
            paneLeft2.add(bookISBNLbl,0,4); paneLeft2.add(bookISBNTxt,1,4);
            paneLeft2.add(bookQuantityLbl,0,5); paneLeft2.add(bookQuantityTxt,1,5);
            paneLeft2.add(addANewBookBtn,1,6);
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
            Scene scene = new Scene(root,1000,500);
            primaryStage.setTitle("Library");
            primaryStage.setScene(scene);
            primaryStage.show();


        }
}