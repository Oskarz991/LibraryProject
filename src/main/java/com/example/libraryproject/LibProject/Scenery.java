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
       public File pendingWorkFile = new File("src/main/java/com/example/libraryproject/LibProject/PendingWork.txt");
       public Storage storage = new Storage();

        public static void main(String[] args) {

       launch(args);
        }

        @Override
        public void start(Stage primaryStage) throws IOException {

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

            ArrayList<User> userList = storage.getUserList() ;



            ListView<String> memberList = new ListView<>();
            memberList.setPrefWidth(150);
            for ( int i = 0; i <userList.size(); i++){
              memberList.getItems().addAll(userList.get(i).Name);
            }

            Font fira = new Font(15);
            Text namn = new Text();
            namn.setStyle("-fx-font-weight: bold");
            namn.setFont(fira);
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
                memberList.refresh();
                namn.setText(userList.get(memberList.getSelectionModel().getSelectedIndex()).getName()+ " ");
                role.setText(userList.get(memberList.getSelectionModel().getSelectedIndex()).getRole()+ "\n" + "\n" + "Id: ");
                idid.setText(String.valueOf(userList.get(memberList.getSelectionModel().getSelectedIndex()).getId())+"\n" + "Personal Nr: ");
                prNumber.setText(String.valueOf(userList.get(memberList.getSelectionModel().getSelectedIndex()).getPNumber())+"\n" + "Violation counter: ");
                violationCounter.setText(String.valueOf(userList.get(memberList.getSelectionModel().getSelectedIndex()).getViolationCounter())+"\n" + "Loan counter: ");
                loanCount.setText(String.valueOf(userList.get(memberList.getSelectionModel().getSelectedIndex()).getLoanCounter())+"\n");
            });

            TextFlow textMembers = new TextFlow(namn, role, idid, prNumber, violationCounter, loanCount);
            textMembers.setPrefWidth(250);

            //Bibliotekarie kan se alla pendings
            Button toSeePendingBtn = new Button("See requests");

            Scanner pendingScan = null;
            try {
                pendingScan = new Scanner(pendingWorkFile).useDelimiter(",");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            ArrayList<User> pendingList = storage.getPendingList();

            ListView<String> pendingListView = new ListView<>();
            pendingListView.setVisible(false);
            pendingListView.setPrefWidth(150);

            for ( int i = 0; i <pendingList.size(); i++){
                pendingListView.getItems().addAll(pendingList.get(i).Request);
            }

            Text namnPending = new Text();
            namnPending.setStyle("-fx-font-weight: bold");
            namnPending.setFont(fira);
            Text prNumberPending = new Text();
            prNumberPending.setFont(tre);
            Text idPending = new Text();
            idPending.setFont(tre);


            pendingListView.getSelectionModel().selectedIndexProperty().addListener(ov -> {
                    namnPending.setText(pendingList.get(pendingListView.getSelectionModel().getSelectedIndex()).getName() + " " + "\n" + "Personal number: ");
                    prNumberPending.setText(String.valueOf(pendingList.get(pendingListView.getSelectionModel().getSelectedIndex()).getPNumber()) + "\n" + "Id: ");
                idPending.setText(String.valueOf(pendingList.get(pendingListView.getSelectionModel().getSelectedIndex()).getId()) + "\n");

            });

            TextFlow textPending = new TextFlow(namnPending,prNumberPending,idPending);
            textPending.setPrefWidth(250);
            textPending.setVisible(false);


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

            Label sentISBNtoReturnBookLbl = new Label("Return book by isbn:");
            TextField sentISBNtoReturnBookTxt = new TextField();

            //Ansöka om att lämna tillbaka bok
             Button returnBookBtn = new Button("Return Book");

            //Logga in som medlem
            Label nameMemberLoginLbl = new Label("Name:");
            TextField nameMemberLoginTxt = new TextField();
            Label idMemberLoginLbl = new Label("ID-number:");
            PasswordField idMemberLoginTxt = new PasswordField();
            Text idForUser = new Text();
            idForUser.setVisible(false);

            //Medlem request för att bli deleted
            Label titleRequestDelete = new Label("Request delete user");
            titleRequestDelete.setVisible(false);
            titleRequestDelete.setFont(new Font("Fira Sans", 32));
            Button toRequestDeleteBtn = new Button("To delete");
            Button toAllLoanedBooksBtn = new Button("To loaned books");
            toAllLoanedBooksBtn.setVisible(false);
            Button userRequestDeleteBtn = new Button("Send request");
            userRequestDeleteBtn.setVisible(false);

            Label nameUserRequestDeleteLbl = new Label("Name:");
            TextField nameUserRequestDeleteTxt = new TextField();
            nameUserRequestDeleteLbl.setVisible(false); nameUserRequestDeleteTxt.setVisible(false);
            Label idUserRequestDeleteLbl = new Label("Id:");
            TextField idUserRequestDeleteTxt = new TextField();
            idUserRequestDeleteLbl.setVisible(false); idUserRequestDeleteTxt.setVisible(false);
            Label personalNrUserRequestDeleteLbl = new Label("Personal number:");
            TextField personalNrUserRequestDeleteTxt = new TextField();
            personalNrUserRequestDeleteLbl.setVisible(false); personalNrUserRequestDeleteTxt.setVisible(false);

            //Logga in som bibliotekarie
            Label nameLibrarianLbl = new Label("Name:");
            TextField nameLibrarianTxt = new TextField();
            Label idLibrarianLbl = new Label("ID-number:");
            PasswordField idLibrarianTxt = new PasswordField();

            //Skapa nytt konto
            Label nameMemberRegisterLbl = new Label("Nam");
            nameMemberRegisterLbl.setVisible(false);
            TextField nameMemberRegisterTxt = new TextField();
            nameMemberRegisterTxt.setVisible(false);
            Label personalNrMemberRegisterLbl = new Label("Personal Number:");
            TextField personalNrMemberRegisterTxt = new TextField();
            personalNrMemberRegisterLbl.setVisible(false);
            personalNrMemberRegisterTxt.setVisible(false);

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

             //Bibliotekarien kan bekräfta ett lån till användarna
             TextField lendBookBookNameLibrarianTxt = new TextField();
             lendBookBookNameLibrarianTxt.setPromptText("Book name");
             TextField lendBookUserIdLibrarianTxt = new TextField();
             lendBookUserIdLibrarianTxt.setPromptText("User Id");
             lendBookUserIdLibrarianTxt.setVisible(false);
             lendBookBookNameLibrarianTxt.setVisible(false);
             Button lendBookLibrarianBtn = new Button("Accept Lend book");
             lendBookLibrarianBtn.setVisible(false);

             //Skapa en ny medlem
             TextField createANewUserNameTxt = new TextField(); createANewUserNameTxt.setPromptText("Name"); createANewUserNameTxt.setVisible(false);
             TextField createANewUserPersonalNumberTxt = new TextField(); createANewUserPersonalNumberTxt.setPromptText("Personal number"); createANewUserPersonalNumberTxt.setVisible(false);
             TextField createANewUserRoleTxt = new TextField(); createANewUserRoleTxt.setPromptText("Role"); createANewUserRoleTxt.setVisible(false);
             Button createANewUserBtn = new Button("Create user"); createANewUserBtn.setVisible(false);

             //Bibliotekarien kan ge timeouts
             TextField librarianGiveTimeoutIdTxt = new TextField(); librarianGiveTimeoutIdTxt.setPromptText("User Id");
             Button librarianGiveTimeoutBtn = new Button("Give Timeout");

            //--------------------------------------------------------//
            Button registerNewAccount = new Button("Register new account ");
            Button loginAsAMember = new Button("Login as a member");
            loginAsAMember.setVisible(false);

            Button loginAsLibraryan = new Button("Login as a librarian ");

            Button loginBtn = new Button("Login ");
            Button registerBtn = new Button("Create account");
            registerBtn.setVisible(false);

            ToggleGroup chooseRoleTgl = new ToggleGroup();
            RadioButton userIsUndergraduateStudent = new RadioButton("Undergraduate student");
            RadioButton userIsPostgraduateStudent = new RadioButton("Postgraduate student");
            RadioButton userIsPhDStudent = new RadioButton("PhD Student");
            RadioButton userIsTeacher = new RadioButton("Teacher");

            userIsUndergraduateStudent.setToggleGroup(chooseRoleTgl); userIsPostgraduateStudent.setToggleGroup(chooseRoleTgl); userIsPhDStudent.setToggleGroup(chooseRoleTgl); userIsTeacher.setToggleGroup(chooseRoleTgl);
            userIsUndergraduateStudent.setVisible(false); userIsPostgraduateStudent.setVisible(false); userIsPhDStudent.setVisible(false); userIsTeacher.setVisible(false);

            registerNewAccount.setOnAction(e-> {
                nameMemberLoginTxt.clear(); idMemberLoginTxt.clear();
                titleMember.setVisible(false); titleNewAccount.setVisible(true);
                registerNewAccount.setVisible(false); loginAsAMember.setVisible(true);
                loginBtn.setVisible(false); registerBtn.setVisible(true);
                nameMemberLoginTxt.setVisible(false); nameMemberLoginTxt.setVisible(false);
                nameMemberRegisterLbl.setVisible(true); nameMemberRegisterTxt.setVisible(true);
                idMemberLoginLbl.setVisible(false); idMemberLoginTxt.setVisible(false);
                personalNrMemberRegisterLbl.setVisible(true); personalNrMemberRegisterTxt.setVisible(true);
                userIsUndergraduateStudent.setVisible(true); userIsPostgraduateStudent.setVisible(true); userIsPhDStudent.setVisible(true); userIsTeacher.setVisible(true);
            });

            loginAsAMember.setOnAction(e-> {
                nameMemberRegisterTxt.clear(); personalNrMemberRegisterTxt.clear();
                titleMember.setVisible(true); titleNewAccount.setVisible(false);
                registerNewAccount.setVisible(true); loginAsAMember.setVisible(false);
                loginBtn.setVisible(true); registerBtn.setVisible(false);
                nameMemberLoginLbl.setVisible(true); nameMemberLoginTxt.setVisible(true);
                nameMemberRegisterLbl.setVisible(false); nameMemberRegisterTxt.setVisible(false);
                idMemberLoginLbl.setVisible(true); idMemberLoginTxt.setVisible(true);
                personalNrMemberRegisterLbl.setVisible(false); personalNrMemberRegisterTxt.setVisible(false);
                userIsUndergraduateStudent.setVisible(false); userIsPostgraduateStudent.setVisible(false); userIsPhDStudent.setVisible(false); userIsTeacher.setVisible(false);
            });

            loginAsLibraryan.setOnAction(e-> {
                try {
                    librarianObj.controlTimeouts();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

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

            Text bookNameGet = new Text();

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

                ArrayList<Book> bookOjcc = new ArrayList<Book>();
                   int prNameForSeeAllBooks = Integer.parseInt(idMemberLoginTxt.getText());
                   String nameForSeeAllBooks = nameMemberLoginTxt.getText();
                   String bookNameg = "";
                   String bookNameg2 = "";


                try {
                    bookOjcc = userObj.myBooks(prNameForSeeAllBooks,nameForSeeAllBooks);
                    for (Book book:bookOjcc ){
                        bookNameg = book.getTitle() + ": " + book.getISBN();
                        bookNameg2 = bookNameg2 + "\n" + bookNameg;

                    }
                    bookNameGet.setText(bookNameg2);


                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });

                    Text getIdForBookToSendForLoan = new Text();

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
                    int pNumber;
                    pNumber = Integer.parseInt(idMemberLoginTxt.getText());
                    int idBok;
                    idBok = Integer.parseInt(getIdForBookToSendForLoan.getText());

                    if (loanBook1.isSelected()) {
                        try {
                           userObj.requestLoan(idBok,bName,pName,pNumber);
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

            lendBookLibrarianBtn.setOnAction(e->{
                Book bookOkj = new Book();
                int idUser;

                if (lendBookBookNameLibrarianTxt.getText().isEmpty()) {
                    Alert aler = new Alert(Alert.AlertType.INFORMATION);
                    aler.setHeaderText("Something went wrong");
                    aler.showAndWait();
                }else {
                    try {
                        bookOkj = userObj.searchTitle(lendBookBookNameLibrarianTxt.getText());
                        idUser = Integer.parseInt(lendBookUserIdLibrarianTxt.getText());
                        librarianObj.lendBook(bookOkj, idUser);
                        Alert aler = new Alert(Alert.AlertType.INFORMATION);
                        aler.setHeaderText("Lend book accepted");
                        aler.showAndWait();
                        lendBookUserIdLibrarianTxt.clear();
                        lendBookBookNameLibrarianTxt.clear();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
//
                registerBtn.setOnAction(e-> {
                            String nameUser = nameMemberRegisterTxt.getText();
                            int personalNrUser = Integer.parseInt(personalNrMemberRegisterTxt.getText());
                            String pr = "Undergraduate student";
                            String pr2 = "Postgraduate student";
                            String pr3 = "PhD Student";
                            String pr4 = "Teacher";

                            if (userIsUndergraduateStudent.isSelected() && !nameUser.isEmpty() && personalNrUser > 0) {
                                try {
                                    userObj.requestAddUser(nameUser, personalNrUser, pr);
                                    Alert aler = new Alert(Alert.AlertType.INFORMATION);
                                    aler.setHeaderText("Your request has been sent");
                                    aler.showAndWait();

                                    nameMemberRegisterTxt.clear();
                                    personalNrMemberRegisterTxt.clear();

                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }   
                            } else if (userIsPostgraduateStudent.isSelected() && !nameUser.isEmpty() && personalNrUser > 0) {
                            try {
                                userObj.requestAddUser(nameUser, personalNrUser, pr2);
                                Alert aler = new Alert(Alert.AlertType.INFORMATION);
                                aler.setHeaderText("Your request has been sent");
                                aler.showAndWait();

                                nameMemberRegisterTxt.clear();
                                personalNrMemberRegisterTxt.clear();

                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }    else if (userIsPhDStudent.isSelected() && !nameUser.isEmpty() && personalNrUser > 0) {
                                try {
                                    userObj.requestAddUser(nameUser,personalNrUser,pr3);
                                    Alert aler = new Alert(Alert.AlertType.INFORMATION);
                                    aler.setHeaderText("Your request has been sent");
                                    aler.showAndWait();

                                    nameMemberRegisterTxt.clear();
                                    personalNrMemberRegisterTxt.clear();

                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }   else if (userIsTeacher.isSelected() && !nameUser.isEmpty() && personalNrUser > 0){
                                try {
                                    userObj.requestAddUser(nameUser,personalNrUser,pr4);
                                    Alert aler = new Alert(Alert.AlertType.INFORMATION);
                                    aler.setHeaderText("Your request has been sent");
                                    aler.showAndWait();

                                    nameMemberRegisterTxt.clear();
                                    personalNrMemberRegisterTxt.clear();

                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }   else  {
                                Alert aler = new Alert(Alert.AlertType.INFORMATION);
                                aler.setHeaderText("Verify that you have entered everything");
                                aler.showAndWait();
                    }

            });

                createANewUserBtn.setOnAction(e->{
                    String nameUser = createANewUserNameTxt.getText();
                    int personalNrUser = Integer.parseInt(createANewUserPersonalNumberTxt.getText());
                    String roleUser = createANewUserRoleTxt.getText();

                    try {
                        librarianObj.addUser(nameUser,personalNrUser,roleUser);
                        Alert aler = new Alert(Alert.AlertType.INFORMATION);
                        aler.setHeaderText("User created");
                        aler.showAndWait();
                        createANewUserNameTxt.clear();
                        createANewUserPersonalNumberTxt.clear();
                        createANewUserRoleTxt.clear();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });

                librarianGiveTimeoutBtn.setOnAction(e->{
                    int idForGiveTimeout = Integer.parseInt(librarianGiveTimeoutIdTxt.getText());


                    try {
                        librarianObj.giveTimeout(idForGiveTimeout);
                        lendBookUserIdLibrarianTxt.clear();

                        Alert aler = new Alert(Alert.AlertType.INFORMATION);
                        aler.setHeaderText("Timeout given");
                        aler.showAndWait();
                        lendBookBookNameLibrarianTxt.clear();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
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

            deleteUserBtn.setOnAction(e->{
                String id;
                id = userIdTxt.getText();

                if (deleteByChoise.isSelected() && !id.isEmpty()) {
                    try {
                        librarianObj.deleteUser(Integer.parseInt(id),true);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    Alert aler = new Alert(Alert.AlertType.INFORMATION);
                    aler.setHeaderText("User delete by choice");
                    aler.showAndWait();
                    userIdTxt.clear();

                } else if (deleteByPenalties.isSelected() && !id.isEmpty()){
                    try {
                        librarianObj.deleteUser(Integer.parseInt(id),false);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    Alert aler = new Alert(Alert.AlertType.INFORMATION);
                    aler.setHeaderText("User delete because of penalties");
                    aler.showAndWait();
                    userIdTxt.clear();

                }
                else {
                    Alert aler = new Alert(Alert.AlertType.INFORMATION);
                    aler.setHeaderText("Inser an ID");
                    aler.showAndWait();
                }

            });

            formDeteleUserBtn.setOnAction(e->{
                memberList.setVisible(false);
                textMembers.setVisible(false);
                overviewMembersBtn.setVisible(true);
                deleteByChoise.setVisible(true);
                deleteByPenalties.setVisible(true);
                deleteUserBtn.setVisible(true);
                overviewMembersBtn.setVisible(true);
                userIdLbl.setVisible(true);
                userIdTxt.setVisible(true);
                formDeteleUserBtn.setVisible(false);
                pendingListView.setVisible(false); textPending.setVisible(false);
                lendBookUserIdLibrarianTxt.setVisible(false);
                lendBookBookNameLibrarianTxt.setVisible(false);
                lendBookLibrarianBtn.setVisible(false);
                createANewUserNameTxt.setVisible(false); createANewUserPersonalNumberTxt.setVisible(false); createANewUserRoleTxt.setVisible(false); createANewUserBtn.setVisible(false);
                librarianGiveTimeoutIdTxt.setVisible(false); librarianGiveTimeoutBtn.setVisible(false);
                   });

            overviewMembersBtn.setOnAction(e->{
                memberList.refresh();
                formDeteleUserBtn.setVisible(true); overviewMembersBtn.setVisible(false);
                memberList.setVisible(true); textMembers.setVisible(true);
                deleteByChoise.setVisible(false); deleteByPenalties.setVisible(false); deleteUserBtn.setVisible(false);
                formDeteleUserBtn.setVisible(true);
                userIdLbl.setVisible(false); userIdTxt.setVisible(false);
                pendingListView.setVisible(false); textPending.setVisible(false);
                lendBookUserIdLibrarianTxt.setVisible(false);
                lendBookBookNameLibrarianTxt.setVisible(false);
                lendBookLibrarianBtn.setVisible(false);
                createANewUserNameTxt.setVisible(false); createANewUserPersonalNumberTxt.setVisible(false); createANewUserRoleTxt.setVisible(false); createANewUserBtn.setVisible(false);
                librarianGiveTimeoutIdTxt.setVisible(true); librarianGiveTimeoutBtn.setVisible(true);
                    });

            toSeePendingBtn.setOnAction(e->{
                pendingListView.setVisible(true);
                memberList.setVisible(false); textMembers.setVisible(false); deleteByChoise.setVisible(false); deleteByPenalties.setVisible(false);
                deleteUserBtn.setVisible(false); userIdLbl.setVisible(false); userIdTxt.setVisible(false); textPending.setVisible(true);
                lendBookUserIdLibrarianTxt.setVisible(true); lendBookBookNameLibrarianTxt.setVisible(true); lendBookLibrarianBtn.setVisible(true);
                createANewUserNameTxt.setVisible(true); createANewUserPersonalNumberTxt.setVisible(true); createANewUserRoleTxt.setVisible(true); createANewUserBtn.setVisible(true);
                librarianGiveTimeoutIdTxt.setVisible(false); librarianGiveTimeoutBtn.setVisible(false);
            });

            toRequestDeleteBtn.setOnAction(e->{
                  titleAllLoanedBooks.setVisible(false);
                  titleRequestDelete.setVisible(true);
                  toRequestDeleteBtn.setVisible(false);
                  toAllLoanedBooksBtn.setVisible(true);
                  nameUserRequestDeleteLbl.setVisible(true); nameUserRequestDeleteTxt.setVisible(true);
                  idUserRequestDeleteLbl.setVisible(true); idUserRequestDeleteTxt.setVisible(true);
                  personalNrUserRequestDeleteLbl.setVisible(true); personalNrUserRequestDeleteTxt.setVisible(true);
                  returnBookBtn.setVisible(false); userRequestDeleteBtn.setVisible(true);
                  bookNameGet.setVisible(false);
                  sentISBNtoReturnBookLbl.setVisible(false); sentISBNtoReturnBookTxt.setVisible(false);
            });

            toAllLoanedBooksBtn.setOnAction(e->{
                titleAllLoanedBooks.setVisible(true);
                titleRequestDelete.setVisible(false);
                toRequestDeleteBtn.setVisible(true);
                toAllLoanedBooksBtn.setVisible(false);
                titleAllLoanedBooks.setVisible(true);
                nameUserRequestDeleteLbl.setVisible(false); nameUserRequestDeleteTxt.setVisible(false);
                idUserRequestDeleteLbl.setVisible(false); idUserRequestDeleteTxt.setVisible(false);
                personalNrUserRequestDeleteLbl.setVisible(false); personalNrUserRequestDeleteTxt.setVisible(false);
                returnBookBtn.setVisible(true); userRequestDeleteBtn.setVisible(false);
                bookNameGet.setVisible(true);
                sentISBNtoReturnBookLbl.setVisible(true); sentISBNtoReturnBookTxt.setVisible(true);

            });

            Librarian newLibraryan = new Librarian();

            returnBookBtn.setOnAction(e->{
                int ISBN = Integer.parseInt(sentISBNtoReturnBookTxt.getText());
                int IdUser = Integer.parseInt(idMemberLoginTxt.getText());

                      Book bookObc = new Book();
                try {

                        bookObc = newLibraryan.getBookByISBN(ISBN);
                        userObj.returnBook(bookObc,IdUser);
                    bookNameTxt.clear();

                             ArrayList<Book> bookOjcc = new ArrayList<Book>();
                                int prNameForSeeAllBooks = Integer.parseInt(idMemberLoginTxt.getText());
                                String nameForSeeAllBooks = nameMemberLoginTxt.getText();
                                String bookNameg = "";
                                String bookNameg2 = "";
                                 bookOjcc = userObj.myBooks(prNameForSeeAllBooks,nameForSeeAllBooks);
                                 for (Book book:bookOjcc ){
                                     bookNameg = book.getTitle() + ": " + book.getISBN();
                                     bookNameg2 = bookNameg2 + "\n" + bookNameg;
                                 }
                                 bookNameGet.setText(bookNameg2);
                    
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });

            userRequestDeleteBtn.setOnAction(e->{
                String nameUserDeleteGet = nameUserRequestDeleteTxt.getText();
                int idUserDeleteGet = Integer.parseInt(idUserRequestDeleteTxt.getText());
                int prNumberDeleteGet = Integer.parseInt(personalNrUserRequestDeleteTxt.getText());

                if ( !nameUserDeleteGet.isEmpty() && idUserDeleteGet > 0 && prNumberDeleteGet > 0) {
                    try {
                        userObj.requestDelete(idUserDeleteGet, nameUserDeleteGet, prNumberDeleteGet);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                     Alert aler = new Alert(Alert.AlertType.INFORMATION);
                     aler.setHeaderText("Delete request sent");
                     aler.showAndWait();
                    nameUserRequestDeleteTxt.clear(); idUserRequestDeleteTxt.clear(); personalNrUserRequestDeleteTxt.clear();
                } else if (nameUserDeleteGet.isEmpty() && idUserDeleteGet < 0 && prNumberDeleteGet < 0){
                Alert aler = new Alert(Alert.AlertType.INFORMATION);
                aler.setHeaderText("Empty spaces");
                aler.showAndWait();
                }
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
            paneRight.add(userIsUndergraduateStudent,0,4); paneRight.add(userIsPostgraduateStudent,1,4);
            paneRight.add(userIsPhDStudent,0,5); paneRight.add(userIsTeacher,1,5);
            paneRight.add(personalNrMemberRegisterLbl,0,6); paneRight.add(personalNrMemberRegisterTxt,1,6);
            paneRight.add(loginBtn,1,7); paneRight.add(registerBtn,1,7);

            //Lägg till på vänster sida - det som bibliotekarien ser
            paneLeft2.add(titleAddNewBook,0,0); paneLeft2.add(titleSearchBook,0,0);
            paneLeft2.add(changeSearchAddBookBtn,0,1); paneLeft2.add(changeSearchAddBookBtn2,0,1);
            paneLeft2.add(bookIDLbl,0,2); paneLeft2.add(bookIdTxt,1,2); paneLeft2.add(searchBookISBNLbl,0,2); paneLeft2.add(searchBookISBNTxt,1,2);
            paneLeft2.add(bookNameLbl,0,3); paneLeft2.add(bookNameTxt,1,3);
            paneLeft2.add(bookAuthorLbl,0,4); paneLeft2.add(bookAuthorTxt,1,4);
            paneLeft2.add(bookISBNLbl,0,5); paneLeft2.add(bookISBNTxt,1,5);
            paneLeft2.add(bookQuantityLbl,0,6); paneLeft2.add(bookQuantityTxt,1,6);
            paneLeft2.add(formDeteleUserBtn,0,8); paneLeft2.add(overviewMembersBtn,0,8); paneLeft2.add(toSeePendingBtn,1,8);
            paneLeft2.add(addANewBookBtn,1,7); paneLeft2.add(addBookText,2,7); paneLeft2.add(searchBookByISBNBtn,1,7);

            paneLeft2.add(bokA,0,3); paneLeft2.add(bokId,0,4); paneLeft2.add(bokN,0,5); paneLeft2.add(bokQ,0,6); paneLeft2.add(bokISBNN,0,7);

            paneLeft2.add(userIdLbl,0,9); paneLeft2.add(userIdTxt,1,9);
            paneLeft2.add(memberList,0,9); paneLeft2.add(textMembers,1,9); paneLeft2.add(pendingListView,0,9); paneLeft2.add(textPending,1,9);
            paneLeft2.add(librarianGiveTimeoutIdTxt,2,10); paneLeft2.add(librarianGiveTimeoutBtn,1,10);
            paneLeft2.add(deleteByChoise, 0,10); paneLeft2.add(deleteByPenalties,1,10); paneLeft2.add(deleteUserBtn,2,10);

            paneLeft2.add(lendBookBookNameLibrarianTxt,3,9); paneLeft2.add(lendBookUserIdLibrarianTxt,2,9); paneLeft2.add(lendBookLibrarianBtn,1,9);
            paneLeft2.add(createANewUserBtn,1,10); paneLeft2.add(createANewUserNameTxt,2,10); paneLeft2.add(createANewUserPersonalNumberTxt,3,10); paneLeft2.add(createANewUserRoleTxt,4,10);
            paneLeft2.setVisible(false);

            //Lägg till på höger sida - det som användaren ser
            paneRight2.add(titleRequestLoan,0,0); paneRight2.add(idForUser,1,0);
            paneRight2.add(bookTitleLbl,0,1); paneRight2.add(bookTitleTxt,1,1); paneRight2.add(searchBookBtn,2,1);
            paneRight2.add(loanBook1,0,2);
            paneRight2.add(bookUserSearchTxt,0,2);
            paneRight2.add(loanBookBtn,1,6);
            paneRight2.add(titleAllLoanedBooks,0,7); paneRight2.add(titleRequestDelete,0,7);
            paneRight2.add(toRequestDeleteBtn,0,8); paneRight2.add(toAllLoanedBooksBtn,0,8); 
            paneRight2.add(bookNameGet,0,9) ;paneRight2.add(nameUserRequestDeleteLbl,0,9); paneRight2.add(nameUserRequestDeleteTxt,1,9);
            paneRight2.add(sentISBNtoReturnBookLbl,0,10) ;paneRight2.add(sentISBNtoReturnBookTxt,1,10);
            paneRight2.add(idUserRequestDeleteLbl,0,10); paneRight2.add(idUserRequestDeleteTxt,1,10);
            paneRight2.add(personalNrUserRequestDeleteLbl,0,11); paneRight2.add(personalNrUserRequestDeleteTxt,1,11);
          //  paneRight2.add(returnBook1,0,9); paneRight2.add(returnBook2,0,10); paneRight2.add(returnBook3,0,11); paneRight2.add(returnBook4,0,12);
            paneRight2.add(returnBookBtn,1,12); paneRight2.add(userRequestDeleteBtn,1,12);
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