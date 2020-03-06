package g7.library.ui;

import g7.library.domain.BookCopy;
import g7.library.domain.LibraryMember;
import g7.library.utils.UserInterfaceUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
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
		title.getStyleClass().add("form-title");

		HBox titleContainer = new HBox(20, title);
		titleContainer.setAlignment(Pos.BOTTOM_CENTER);

		message.setStyle("-fx-text-color: red");
		HBox errorContainer = new HBox(20, message);
		errorContainer.setAlignment(Pos.BASELINE_LEFT);

		HBox hButtons = new HBox(10);
		VBox checkoutFields = new VBox(10);
		Button checkout = new Button("Checkout");
		Button findBook = new Button("Find");
		Button findMember = new Button("Find");
		checkout.setOnAction(this::handleOnSubmit);
		findBook.setOnAction(this::findBooks);
		findMember.setOnAction(this::findMembers);
		Separator separator = new Separator();
		separator.prefWidthProperty().bind(checkoutFields.widthProperty());

		HBox h1 = new HBox(10, new Label("Member ID: "), memberId, findMember);
		HBox h2 = new HBox(10, new Label("ISBN: "), isbn, findBook);
		HBox h3 = new HBox(separator);

		Stream.of(h1, h2).forEach(h -> h.setAlignment(Pos.BASELINE_RIGHT));

		checkoutFields.getChildren().addAll(h1, h2, h3);
		hButtons.setAlignment(Pos.BASELINE_RIGHT);
		hButtons.setMinHeight(50);
		hButtons.getChildren().addAll(checkout);


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
		TextField searchText = new TextField();
		Button searchButton = new Button("Search");
		ObservableList<BookCopy> books = loadBooks();
		Parent booksTable = UserInterfaceUtils.renderBooks(books);

		HBox container = new HBox(10, searchText, searchButton);
		VBox finderContainer = new VBox(10, container, booksTable);
		StackPane pane = new StackPane(finderContainer);

		Start.displayPopup(pane, "Book Finder", 550, 500);
	}

	private void findMembers(ActionEvent evt) {
		TextField searchText = new TextField();
		Button searchButton = new Button("Search");
		ObservableList<LibraryMember> members = loadMembers("");
		Parent booksTable = UserInterfaceUtils.renderMembers(members);

		HBox container = new HBox(10, searchText, searchButton);
		VBox finderContainer = new VBox(10, container, booksTable);
		StackPane pane = new StackPane(finderContainer);

		Start.displayPopup(pane, "Book Finder", 550, 500);
	}

	private ObservableList<LibraryMember> loadMembers(String searchString) {
		return FXCollections.observableArrayList();
	}
	
	private void closePopup(ActionEvent evt) {
		Start.hidePopup();
	}
	
	private ObservableList<BookCopy> loadBooks() {
		return FXCollections.observableArrayList();
	}

	@Override
	public void getDataFromFields() {
		// TODO Auto-generated method stub
		
	}
}
