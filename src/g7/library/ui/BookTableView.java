package g7.library.ui;

import java.util.Collection;

import g7.library.domain.Book;
import g7.library.ui.BookManagementScene.CustomButtonCell;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class BookTableView extends TableView<Book> {
	private ObservableList<Book> books;

	public BookTableView() {
		initDesign();
	}
	
	@SuppressWarnings("unchecked")
	public void initDesign() {

		TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
	    TableColumn<Book, String> isbnColumn = new TableColumn<>("ISBN");
	    TableColumn<Book, String> availableColumn = new TableColumn<>("Available Copies");
	    TableColumn<Book, Parent> actionsColumn = new TableColumn<>("");

	    titleColumn.setCellValueFactory(record -> new ReadOnlyStringWrapper(record.getValue().getTitle()));
	    isbnColumn.setCellValueFactory(record -> new ReadOnlyStringWrapper(record.getValue().getIsbn()));
	    availableColumn.setCellValueFactory(record -> new ReadOnlyStringWrapper(String.valueOf(record.getValue().getCopieAvailable())));
	    actionsColumn.setCellFactory(record -> new CustomButtonCell());

	    this.getColumns().addAll(titleColumn, isbnColumn, availableColumn, actionsColumn);
	    this.setMinWidth(400);
	    this.setItems(books);
	}
	
	public void update(Collection<Book> books) {
		this.books = FXCollections.observableArrayList(books);
		this.setItems(this.books);
		this.refresh();
	}
	
}
