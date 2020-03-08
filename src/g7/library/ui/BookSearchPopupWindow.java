package g7.library.ui;

import java.util.Set;

import g7.library.domain.Book;
import g7.library.frontcontroller.LibraryController;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
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
		choose.setOnAction(e -> {
			Book book = this.bookTableView.getSelectionModel().getSelectedItem();
			CheckoutScene.INSTANCE.assignBookISBN(book.getIsbn());
			this.close();
		});
		
		this.bookTableView = new BookTableView();
		this.bookTableView.update(libraryController.findAllBooks());
		Parent booksTable = new HBox(this.bookTableView);
		HBox container = new HBox(10, searchText, searchButton);
		
		Button closeButton = new Button("Close");
		closeButton.setOnAction(evt -> this.hide());

		Separator separator = new Separator();
		separator.prefWidthProperty().bind(container.widthProperty());
		HBox buttons = new HBox(10, choose, closeButton);
		HBox sepLine = new HBox(separator);
		buttons.setAlignment(Pos.BOTTOM_RIGHT);
		
		VBox finderContainer = new VBox(10, container, booksTable, sepLine, buttons);
		StackPane pane = new StackPane(finderContainer);
		StackPane.setMargin(finderContainer, new Insets(15));
		
		this.setScene(pane, 550, 600);
		this.setTitle("Book Finder");
		
	}
	
	public void handleOnSearch(ActionEvent evt) {
	  Set<Book> books = libraryController.searchBook(searchText.getText());
	  this.bookTableView.update(books);
	}
}
