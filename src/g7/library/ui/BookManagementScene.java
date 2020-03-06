package g7.library.ui;

import g7.library.domain.Book;
import g7.library.domain.BookCopy;
import g7.library.frontcontroller.LibraryController;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyLongWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableStringValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BookManagementScene extends BaseScene {

  public static final BookManagementScene INSTANCE = new BookManagementScene();

  private BookManagementScene() {
    super();
  }

  private TextField searchField;
  private Label message;
  private ObservableList<Book> books;

  @Override
  protected Parent renderMainContent() {
    initFields();

    // Checkout Book Form
    HBox hBox_1 = new HBox(10);
    hBox_1.setAlignment(Pos.BASELINE_CENTER);
    VBox vBox = new VBox(10);

    Label title = new Label("Books Management");
    title.setStyle("-fx-font-size: 20");
    HBox titleContainer = new HBox(20, title);
    titleContainer.setAlignment(Pos.BOTTOM_CENTER);

    Button searchBtn = new Button("Search");

    HBox h1 = new HBox(10, searchField, searchBtn);

    searchBtn.setOnAction(this::handleOnSearch);

    vBox.getChildren().addAll(titleContainer, message, h1, this.renderBooks());
    hBox_1.getChildren().add(vBox);

    return hBox_1;
  }

  @SuppressWarnings("unchecked")
  private Parent renderBooks() {
    HBox booksContainer = new HBox();
    this.books = loadBooks();

    TableView<Book> booksTable = new TableView<>();


    TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
    TableColumn<Book, String> isbnColumn = new TableColumn<>("ISBN");
    TableColumn<Book, String> availableColumn = new TableColumn<>("Available Copies");

    titleColumn.setCellValueFactory(record -> new ReadOnlyStringWrapper(record.getValue().getTitle()));
    isbnColumn.setCellValueFactory(record -> new ReadOnlyStringWrapper(record.getValue().getIsbn()));
    availableColumn.setCellValueFactory(record -> new ReadOnlyStringWrapper(String.valueOf(record.getValue().getCopieAvailable())));

    booksTable.setItems(this.books);
    booksTable.getColumns().addAll(titleColumn, isbnColumn, availableColumn);
    booksTable.setMinWidth(400);
    booksContainer.getChildren().add(booksTable);

    return booksContainer;
  }

  private void handleOnSearch(ActionEvent evt) {

  }

  private ObservableList<Book> loadBooks() {
    return FXCollections.observableArrayList(libraryController.findAllBooks());
  }

  private void initFields() {
    searchField = new TextField();
    message = new Label();
  }
}
