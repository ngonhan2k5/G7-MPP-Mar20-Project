package g7.library.ui;

import g7.library.domain.Author;
import g7.library.domain.Book;
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author knguyen93
 */
public class CustomButtonCell extends TableCell<Book, Parent> {
  private final HBox buttons;
  private TextField copyNumber;

  public CustomButtonCell() {
    buttons = new HBox(10);
    copyNumber = new TextField();
    copyNumber.setPrefWidth(100);
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
    addCopyBtn.setOnAction(this::doAddCopy);
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
        new HBox(addCopyLabel, this.copyNumber),
        new HBox(author, new Label(authors))
    );
    StackPane pane = new StackPane(bookInfo);
    StackPane.setMargin(bookInfo, new Insets(15));

    return pane;
  }

  private void doAddCopy(ActionEvent actionEvent) {
    if (copyNumber != null && copyNumber.getText() != "") {
      System.out.println("doAddCopy(ActionEvent actionEvent) - " + copyNumber.getText());
      Start.hidePopup();
      // TODO: perform add copy
    }
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
