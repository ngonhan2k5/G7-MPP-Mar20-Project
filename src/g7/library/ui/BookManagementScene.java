package g7.library.ui;

import java.util.Set;

import g7.library.domain.Book;
import g7.library.ui.validation.Attributes;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BookManagementScene extends BaseScene {

	public static final BookManagementScene INSTANCE = new BookManagementScene();

	private BookManagementScene() {
		super();
	}

	private TextField searchField;
	private Label message;
	private BookTableView booksTable;

	public void showMessage(String msg) {
		this.message.setText(msg);
	}
	
	@Override
	protected Parent renderMainContent() {
		initFields();
		VBox vBox = new VBox(10);
		Label title = new Label("Books Management");
		title.getStyleClass().add("form-title");
		HBox titleContainer = new HBox(20, title);
		titleContainer.setAlignment(Pos.BOTTOM_LEFT);
		Button searchBtn = new Button("Search");
		Button addBtn = new Button("+ Add");
		searchBtn.setOnAction(this::handleOnSearch);
		addBtn.setOnAction(this::handleOnAdd);
		HBox h1 = new HBox(10, searchField, searchBtn, addBtn);
		vBox.getChildren().addAll(titleContainer, message, h1, booksTable);
		AnchorPane anchorPane = new AnchorPane(vBox);
		anchorPane.setPrefSize(700, 500);

		AnchorPane.setTopAnchor(vBox, 0.0);
		AnchorPane.setBottomAnchor(vBox, 0.0);
		AnchorPane.setLeftAnchor(vBox, 0.0);
		AnchorPane.setRightAnchor(vBox, 0.0);
		return anchorPane;
	}

	private void handleOnAdd(ActionEvent evt) {
		System.out.println("dfd");
		AddNewBookScene.INSTANCE.reinitialize(false);
		PopupWindow.INSTANCE.displayModal(this.getScene(), AddNewBookScene.INSTANCE.getScene(), "Add New " +
				"Book", 380,320);
	}

	public void refreshMembersTable() {
		this.booksTable.update(libraryController.findAllBooks());
	}

	private void handleOnSearch(ActionEvent evt) {
		Set<Book> books = libraryController.searchBook(searchField.getText());
		this.booksTable.update(books);
	}

	private void initFields() {
		searchField = new TextField();
		message = new Label();
		message.getStyleClass().addAll("message");
		
		this.booksTable = new BookTableView();
		this.booksTable.update(FXCollections.observableArrayList(libraryController.findAllBooks()));
	}



}
