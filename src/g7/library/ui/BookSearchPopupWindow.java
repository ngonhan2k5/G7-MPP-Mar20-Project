package g7.library.ui;

import java.util.Set;

import g7.library.domain.Book;
import g7.library.frontcontroller.LibraryController;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class BookSearchPopupWindow extends PopupWindow {
	public static final PopupWindow INSTANCE = new BookSearchPopupWindow();
	
	protected LibraryController libraryController = new LibraryController();
	private BookTableView bookTableView;
	private TextField searchText;
	
	public BookSearchPopupWindow () {
		searchText = new TextField();
		Button searchButton = new Button("Search");
		searchButton.setOnAction(this::handleOnSearch);
		
		Button choose = new Button("OK");
		choose.setOnAction(e -> {this.hide();});
		
		this.bookTableView = new BookTableView();
		this.bookTableView.update(libraryController.findAllBooks());
		Parent booksTable = new HBox(this.bookTableView);
		HBox container = new HBox(10, searchText, searchButton, choose);
		VBox finderContainer = new VBox(10, container, booksTable);
		StackPane pane = new StackPane(finderContainer);
		this.setScene(pane, "Book Finder", 550, 600, choose);
	}
	
	public void handleOnSearch(ActionEvent evt) {
	  Set<Book> books = libraryController.searchBook(searchText.getText());
	  this.bookTableView.update(books);
	}
}
