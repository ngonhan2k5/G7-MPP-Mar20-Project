package g7.library.utils;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Observable;
import java.util.Optional;
import java.util.stream.Collectors;

import g7.library.domain.Address;
import g7.library.domain.Author;
import g7.library.domain.Book;
import g7.library.domain.BookCopy;
import g7.library.domain.CheckoutEntry;
import g7.library.domain.LibraryMember;
import g7.library.ui.CustomButtonCell;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 * @author knguyen93
 */
public class UserInterfaceUtils {
  private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
  private static final SimpleDateFormat DATE_FORMATER = new SimpleDateFormat(DATE_FORMAT);

  @SuppressWarnings("unchecked")
  public static Parent renderMembers(ObservableList<LibraryMember> members) {
    TableColumn<LibraryMember, String> memberIdColumn = new TableColumn<>("Member Id");
    TableColumn<LibraryMember, String> firstNameColumn = new TableColumn<>("First Name");
    TableColumn<LibraryMember, String> lastNameColumn = new TableColumn<>("Last Name");
    TableColumn<LibraryMember, String> phoneColumn = new TableColumn<>("Phone");
    TableColumn<LibraryMember, String> addressColumn = new TableColumn<>("Address");

    memberIdColumn.setCellValueFactory(m -> new ReadOnlyStringWrapper(m.getValue().getMemberId()));
    firstNameColumn.setCellValueFactory(m -> new ReadOnlyStringWrapper(m.getValue().getFirstName()));
    lastNameColumn.setCellValueFactory(m -> new ReadOnlyStringWrapper(m.getValue().getLastName()));
    phoneColumn.setCellValueFactory(m -> new ReadOnlyStringWrapper(m.getValue().getPhoneNumber()));
    addressColumn.setCellValueFactory(m -> new ReadOnlyStringWrapper(Optional.ofNullable(m.getValue().getAddress()).map(Address::toString).orElse("")));

    TableView<LibraryMember> table = new TableView<>(members);
    table.getColumns().addAll(memberIdColumn, firstNameColumn, lastNameColumn, phoneColumn, addressColumn);
    table.setMinWidth(500);
    AnchorPane anchorPane = new AnchorPane(table);
    anchorPane.setPrefSize(700, 500);

    AnchorPane.setTopAnchor(table, 0.0);
    AnchorPane.setBottomAnchor(table, 0.0);
    AnchorPane.setLeftAnchor(table, 0.0);
    AnchorPane.setRightAnchor(table, 0.0);
    return anchorPane;
  }

  @SuppressWarnings("unchecked")
  public static Parent renderBookCopies(ObservableList<BookCopy> books) {
    TableColumn<BookCopy, String> titleColumn = new TableColumn<>("Title");
    TableColumn<BookCopy, String> isbnColumn = new TableColumn<>("ISBN");
    TableColumn<BookCopy, Integer> availableColumn = new TableColumn<>("Available");

    titleColumn.setCellValueFactory(record -> new ReadOnlyStringWrapper(record.getValue().getBook().getTitle()));
    isbnColumn.setCellValueFactory(record -> new ReadOnlyStringWrapper(record.getValue().getBook().getIsbn()));
    availableColumn.setCellValueFactory(new PropertyValueFactory<>("available"));

    TableView<BookCopy> booksTable = new TableView<>(books);
    booksTable.getColumns().addAll(titleColumn, isbnColumn, availableColumn);
    booksTable.setMinWidth(400);

    AnchorPane anchorPane = new AnchorPane(booksTable);
    AnchorPane.setTopAnchor(booksTable, 0.0);
    AnchorPane.setRightAnchor(booksTable, 0.0);
    AnchorPane.setBottomAnchor(booksTable, 0.0);
    AnchorPane.setLeftAnchor(booksTable, 0.0);

    return anchorPane;
  }

  public static Parent renderBooks(ObservableList<Book> books) {
    TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
    TableColumn<Book, String> isbnColumn = new TableColumn<>("ISBN");
    TableColumn<Book, String> authorsColumn = new TableColumn<>("Authors");
    TableColumn<Book, String> availableColumn = new TableColumn<>("Available Copy");
    TableColumn<Book, Parent> actionColumn = new TableColumn<>("");


    titleColumn.setCellValueFactory(record -> new ReadOnlyStringWrapper(record.getValue().getTitle()));
    isbnColumn.setCellValueFactory(record -> new ReadOnlyStringWrapper(record.getValue().getIsbn()));
    authorsColumn.setCellValueFactory(record -> new ReadOnlyStringWrapper(extractAuthorsName(record.getValue())));
    availableColumn.setCellValueFactory(record -> new ReadOnlyStringWrapper(String.valueOf(record.getValue().getCopieAvailable())));
    actionColumn.setCellFactory(record -> new CustomButtonCell());

    TableView<Book> table = new TableView<>(books);
    table.getColumns().addAll(titleColumn, isbnColumn, authorsColumn, availableColumn, actionColumn);
    table.setMinWidth(500);
    AnchorPane anchorPane = new AnchorPane(table);
    anchorPane.setPrefSize(700, 500);

    AnchorPane.setTopAnchor(table, 0.0);
    AnchorPane.setBottomAnchor(table, 0.0);
    AnchorPane.setLeftAnchor(table, 0.0);
    AnchorPane.setRightAnchor(table, 0.0);
    return anchorPane;
  }

  private static String extractAuthorsName(Book book) {
    return Optional.ofNullable(book)
        .map(Book::getAuthors)
        .orElse(Collections.emptySet())
        .stream()
        .map(Author::getFullName)
        .collect(Collectors.joining(", "));
  }

  @SuppressWarnings("unchecked")
  public static Parent renderCheckoutRecords(ObservableList<CheckoutEntry> records) {
    TableColumn<CheckoutEntry, String> bookTileColumn = new TableColumn<>("Book tile");
    TableColumn<CheckoutEntry, String> isbnColumn = new TableColumn<>("ISBN");
    TableColumn<CheckoutEntry, String> copyNumberColumn = new TableColumn<>("Copy Number");
    TableColumn<CheckoutEntry, String> checkoutDateColumn = new TableColumn<>("Checkout Date");
    TableColumn<CheckoutEntry, String> dueDateColumn = new TableColumn<>("Due date");
    TableColumn<CheckoutEntry, Parent> overdueColumn = new TableColumn<>("");

    bookTileColumn.setCellValueFactory(r -> new ReadOnlyStringWrapper(r.getValue().getBook().getBook().getTitle()));
    isbnColumn.setCellValueFactory(r -> new ReadOnlyStringWrapper(r.getValue().getBook().getBook().getIsbn()));
    checkoutDateColumn.setCellValueFactory(r -> new ReadOnlyStringWrapper(DATE_FORMATER.format(r.getValue().getCheckoutDate())));
    dueDateColumn.setCellValueFactory(r -> new ReadOnlyStringWrapper(DATE_FORMATER.format(r.getValue().getReturnDueDate())));
    copyNumberColumn.setCellValueFactory(r -> new ReadOnlyStringWrapper(String.valueOf(r.getValue().getBook().getCopyNumber())));
    overdueColumn.setCellValueFactory(UserInterfaceUtils::renderOverdueColumn);


    TableView<CheckoutEntry> table = new TableView<>(records);
    table.getColumns().addAll(bookTileColumn, isbnColumn, copyNumberColumn, checkoutDateColumn, dueDateColumn,
        overdueColumn);
    table.setMinWidth(500);
    AnchorPane anchorPane = new AnchorPane(table);
    anchorPane.setPrefSize(700, 500);

    AnchorPane.setTopAnchor(table, 0.0);
    AnchorPane.setBottomAnchor(table, 0.0);
    AnchorPane.setLeftAnchor(table, 0.0);
    AnchorPane.setRightAnchor(table, 0.0);
    return anchorPane;
  }

  private static ObservableValue<Parent> renderOverdueColumn(TableColumn.CellDataFeatures<CheckoutEntry,
      Parent> cellDataFeatures) {
    HBox box = new HBox();
    CheckoutEntry record = cellDataFeatures.getValue();
    if (!record.isOverDue()) {
      Label overdueIcon = new Label();
      overdueIcon.getStyleClass().addAll("overdue-icon");
      box.getChildren().add(overdueIcon);
    }
    return new ReadOnlyObjectWrapper<>(box);
  }

}
