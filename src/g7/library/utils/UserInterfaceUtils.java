package g7.library.utils;

import g7.library.domain.BookCopy;
import g7.library.domain.LibraryMember;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

/**
 * @author knguyen93
 */
public class UserInterfaceUtils {

  public static Parent renderMembers(ObservableList<LibraryMember> members) {
    TableColumn<LibraryMember, String> titleColumn = new TableColumn<>("Title");
    TableColumn<LibraryMember, String> isbnColumn = new TableColumn<>("ISBN");
    TableColumn<LibraryMember, Integer> availableColumn = new TableColumn<>("Available");

    titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
    isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
    availableColumn.setCellValueFactory(new PropertyValueFactory<>("available"));

    TableView<LibraryMember> table = new TableView<>(members);
    table.getColumns().addAll(titleColumn, isbnColumn, availableColumn);
    table.setMinWidth(500);

    return new HBox(table);
  }

  public static Parent renderBooks(ObservableList<BookCopy> books) {
    TableColumn<BookCopy, String> titleColumn = new TableColumn<>("Title");
    TableColumn<BookCopy, String> isbnColumn = new TableColumn<>("ISBN");
    TableColumn<BookCopy, Integer> availableColumn = new TableColumn<>("Available");

    titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
    isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
    availableColumn.setCellValueFactory(new PropertyValueFactory<>("available"));

    TableView<BookCopy> booksTable = new TableView<>(books);
    booksTable.getColumns().addAll(titleColumn, isbnColumn, availableColumn);
    booksTable.setMinWidth(400);

    return new HBox(booksTable);
  }
}
