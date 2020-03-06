package g7.library.ui;

import g7.library.domain.BookCopy;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.stream.Stream;

public class CheckoutScene extends BaseScene {
	public static final CheckoutScene INSTANCE = new CheckoutScene();

	private CheckoutScene() {
		super();
	}

	private Label message;
	private TextField memberId;
	private TextField isbn;

	@Override
	protected Parent renderMainContent() {
		initFields();

		// Checkout Book Form
		HBox hBox_1 = new HBox(10);
		hBox_1.setAlignment(Pos.BASELINE_CENTER);
		VBox vBox = new VBox(10);

		Label title = new Label("Checkout Book Form");
		title.setStyle("-fx-font-size: 20");
		HBox titleContainer = new HBox(20, title);
		titleContainer.setAlignment(Pos.BOTTOM_CENTER);

		message.setStyle("-fx-text-color: red");
		HBox errorContainer = new HBox(20, message);
		errorContainer.setAlignment(Pos.BASELINE_LEFT);

		HBox hButtons = new HBox(10);

		VBox checkoutFields = new VBox(10);

		HBox h1 = new HBox(10, new Label("Member ID: "), memberId);
		HBox h2 = new HBox(10, new Label("ISBN: "), isbn);

		Stream.of(h1, h2).forEach(h -> h.setAlignment(Pos.BASELINE_RIGHT));

		checkoutFields.getChildren().addAll(h1, h2);

		Button checkout = new Button("Checkout");
		Button findBook = new Button("Find Books");
		checkout.setOnAction(this::handleOnSubmit);
		findBook.setOnAction(this::findBooks);
		
		hButtons.setAlignment(Pos.BASELINE_RIGHT);
		hButtons.setMinHeight(50);
		hButtons.getChildren().addAll(findBook, checkout);


		vBox.getChildren().addAll(titleContainer, errorContainer, checkoutFields, hButtons);
		hBox_1.getChildren().add(vBox);

		return hBox_1;
	}

	private void initFields() {
		message = new Label();
		memberId = new TextField();
		isbn = new TextField();
	}

	private void handleOnSubmit(ActionEvent evt) {

	}

	private void findBooks(ActionEvent evt) {
		StackPane pane = new StackPane();
		
		VBox finderContainer = new VBox(10);
		HBox container = new HBox(10);
		TextField searchText = new TextField();
		Button searchButton = new Button("Search");
		
		Parent booksTable = renderBooks();
		
		container.getChildren().addAll(searchText, searchButton);
		finderContainer.getChildren().addAll(container, booksTable);
		
		pane.getChildren().addAll(finderContainer);
		
		Start.displayPopup(pane, "Book Finder", 550, 500);
	}
	
	private void closePopup(ActionEvent evt) {
		Start.hidePopup();
	}
	
	@SuppressWarnings("unchecked")
	private Parent renderBooks() {
		HBox booksContainer = new HBox();
		ObservableList<BookCopy> books = loadBooks();
		
		TableView<BookCopy> booksTable = new TableView<>();
		
		
		TableColumn<BookCopy, String> titleColumn = new TableColumn<>("Title");
		TableColumn<BookCopy, String> isbnColumn = new TableColumn<>("ISBN");
		TableColumn<BookCopy, Integer> availableColumn = new TableColumn<>("Available");
		
		titleColumn.setCellValueFactory(new PropertyValueFactory<BookCopy, String>("title"));
		isbnColumn.setCellValueFactory(new PropertyValueFactory<BookCopy, String>("isbn"));
		availableColumn.setCellValueFactory(new PropertyValueFactory<BookCopy, Integer>("available"));
		
		booksTable.setItems(books);
		booksTable.getColumns().addAll(titleColumn, isbnColumn, availableColumn);
		booksTable.setMinWidth(400);
		
		booksContainer.getChildren().add(booksTable);
		
		return booksContainer;
	}
	
	private ObservableList<BookCopy> loadBooks() {
		return FXCollections.observableArrayList();
	}
}
