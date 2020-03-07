package g7.library.utils;

import java.util.Optional;

import g7.library.domain.Address;
import g7.library.domain.BookCopy;
import g7.library.domain.LibraryMember;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * @author knguyen93
 */
public class UserInterfaceUtils {

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
  public static Parent renderBooks(ObservableList<BookCopy> books) {
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
}
