package g7.library.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import g7.library.dataaccess.DataPersistor.SaveMessage;
import g7.library.domain.Author;
import g7.library.domain.Book;
import g7.library.frontcontroller.LibraryController;
import g7.library.frontcontroller.LogicViewController;
import g7.library.model.UserDataBuilder;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * @author knguyen93
 */
public class CustomButtonCell extends TableCell<Book, Parent> {
  private LibraryController libraryController = new LibraryController();
	
  private final HBox buttons;
  private TextField nbrOfCopies;

  public CustomButtonCell() {
    buttons = new HBox(10);
    nbrOfCopies = new TextField();
    nbrOfCopies.setPrefWidth(100);
    Button viewBtn = new Button();
    viewBtn.getStyleClass().addAll("view-btn", "btn-icon");
    Button addCopyBtn = new Button();
    addCopyBtn.getStyleClass().addAll("copy-btn", "btn-icon");

    viewBtn.setOnAction(this::viewBook);
    addCopyBtn.setOnAction(this::addCopy);

    List<Node> nodes = new ArrayList<Node>();
    nodes.add(viewBtn);
    UserDataBuilder userData = Start.getUserData();
    if(userData != null) {
      LogicViewController logicView = new LogicViewController(userData.systemUser());
      if(logicView.isBookAddPermited()) {
        nodes.add(addCopyBtn);
      }
    }

    buttons.getChildren().addAll(nodes);
  }

  @Override
  protected void updateItem(Parent item, boolean empty) {
    super.updateItem(item, empty);
    if (!empty) {
      setGraphic(buttons);
    }
  }

  void viewBook(ActionEvent evt) {
    Book book = CustomButtonCell.this.getTableView().getItems().get(CustomButtonCell.this.getIndex());
    Start.displayPopup(generateBookInformation(book), "Book information", 400, 500, null);
  }

  void addCopy(ActionEvent evt) {
    Book book = CustomButtonCell.this.getTableView().getItems().get(CustomButtonCell.this.getIndex());
    Button addCopyBtn = new Button("Add");
    addCopyBtn.setOnAction(e -> {
		if (nbrOfCopies != null && nbrOfCopies.getText() != "") {
	      System.out.println("doAddCopy(ActionEvent actionEvent) - " + nbrOfCopies.getText());
	      book.makeBookCopies(Integer.valueOf(nbrOfCopies.getText()));
	      CustomButtonCell.this.getTableView().refresh();
	      
	      //save into database
	      SaveMessage message = libraryController.saveBook(book);
	      if(message.isSuccessed()) {
	    	  BookManagementScene.INSTANCE.showMessage("Processed add " 
	    			  + nbrOfCopies.getText() + " copies of book ISBN " + book.getIsbn());
	      }
	      
	      Start.hidePopup();
	     }
    });
    
    Start.displayPopup(generateAddCopy(book), "Add Copy", 400, 500, addCopyBtn);
  }

  private Parent generateAddCopy(Book book) {
    String authors = book.getAuthors().stream().map(Author::getFullName).collect(Collectors.joining(", "));

    VBox bookInfo = new VBox(10);
    Label title = new Label("Title:"), isbn = new Label("ISBN:"), available = new Label("Available copies:"),
        author = new Label("Author(s):"), addCopyLabel = new Label("Number of copies: ");

    Stream.of(title, isbn, available, author, addCopyLabel).forEach(lb -> lb.setPrefWidth(150));

    bookInfo.getChildren().addAll(
        new HBox(title, new Label(book.getTitle())),
        new HBox(isbn, new Label(book.getIsbn())),
        new HBox(available, new Label(String.valueOf(book.getCopieAvailable()))),
        new HBox(addCopyLabel, this.nbrOfCopies),
        new HBox(author, new Label(authors))
    );
    StackPane pane = new StackPane(bookInfo);
    StackPane.setMargin(bookInfo, new Insets(15));

    return pane;
  }

  private Parent generateBookInformation(g7.library.domain.Book book) {
    String authors = book.getAuthors().stream().map(Author::getFullName).collect(Collectors.joining(", "));

    VBox bookInfo = new VBox(10);
    Label title = new Label("Title:"), isbn = new Label("ISBN:"), available = new Label("Available copies:"),
        author = new Label("Author(s):");

    Stream.of(title, isbn, available, author).forEach(lb -> lb.setPrefWidth(150));

    bookInfo.getChildren().addAll(
        new HBox(title, new Label(book.getTitle())),
        new HBox(isbn, new Label(book.getIsbn())),
        new HBox(available, new Label(String.valueOf(book.getCopieAvailable()))),
        new HBox(author, new Label(authors))
    );
    StackPane pane = new StackPane(bookInfo);
    StackPane.setMargin(bookInfo, new Insets(15));

    return pane;
  }
}
