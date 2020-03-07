package g7.library.ui;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import g7.library.domain.Author;
import g7.library.domain.Book;
import g7.library.ui.validation.Attributes;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class BookManagementScene extends BaseScene {

	public static final BookManagementScene INSTANCE = new BookManagementScene();

	private BookManagementScene() {
		super();
	}

	private TextField searchField;
	private Label message;
	private TableView<Book> booksTable;

	@Override
	protected Parent renderMainContent() {
		initFields();

		// Checkout Book Form
		HBox hBox_1 = new HBox(10);
		hBox_1.setAlignment(Pos.BASELINE_CENTER);
		VBox vBox = new VBox(10);

		Label title = new Label("Books Management");
		title.getStyleClass().add("form-title");

		HBox titleContainer = new HBox(20, title);
		titleContainer.setAlignment(Pos.BOTTOM_CENTER);

		Button searchBtn = new Button("Search");

		Button addBtn = new Button("+ Add");

		HBox h1 = new HBox(10, searchField, searchBtn, addBtn);

		searchBtn.setOnAction(this::handleOnSearch);

		addBtn.setOnAction(this::handleOnAdd);

		HBox booksContainer = new HBox();
		booksContainer.getChildren().add(this.booksTable);

		vBox.getChildren().addAll(titleContainer, message, h1, booksContainer);
		hBox_1.getChildren().add(vBox);

		return hBox_1;
	}

	@SuppressWarnings("unchecked")
	private TableView<Book> createBookTableView() {
		TableView<Book> booksTable = new TableView<>();

		TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
		TableColumn<Book, String> isbnColumn = new TableColumn<>("ISBN");
		TableColumn<Book, String> availableColumn = new TableColumn<>("Available Copies");
		TableColumn<Book, Parent> actionsColumn = new TableColumn<>("Actions");

		titleColumn.setCellValueFactory(record -> new ReadOnlyStringWrapper(record.getValue().getTitle()));
		isbnColumn.setCellValueFactory(record -> new ReadOnlyStringWrapper(record.getValue().getIsbn()));
		availableColumn.setCellValueFactory(
				record -> new ReadOnlyStringWrapper(String.valueOf(record.getValue().getCopieAvailable())));
		actionsColumn.setCellFactory(record -> new CustomButtonCell<>());

		booksTable.getColumns().addAll(titleColumn, isbnColumn, availableColumn, actionsColumn);
		booksTable.setMinWidth(400);
		booksTable.setItems(loadBooks());
		return booksTable;
	}

	private void handleOnSearch(ActionEvent evt) {
		Set<Book> books = libraryController.searchBook(searchField.getText());
		this.booksTable.setItems(FXCollections.observableArrayList(books));
		this.booksTable.refresh();
	}
	
	private void handleOnAdd(ActionEvent evt) {
		Set<Book> books = libraryController.searchBook(searchField.getText());
		this.booksTable.setItems(FXCollections.observableArrayList(books));
		this.booksTable.refresh();
	}

	private ObservableList<Book> loadBooks() {
		return FXCollections.observableArrayList(libraryController.findAllBooks());
	}

	private void initFields() {
		searchField = new TextField();
		message = new Label();
		this.booksTable = createBookTableView();
	}

	@Override
	public void getDataFromFields(Attributes<Control> attrs) {
		// TODO Auto-generated method stub

	}

	public static class CustomButtonCell<Book, Parent> extends TableCell<Book, Parent> {
		final HBox buttons;

		CustomButtonCell() {
			buttons = new HBox(10);
			Button viewBtn = new Button("View");
			Button addCopyBtn = new Button("Add Copy");

			viewBtn.setOnAction(this::viewBook);
			addCopyBtn.setOnAction(this::addCopy);
			buttons.getChildren().addAll(viewBtn, addCopyBtn);
		}

		@Override
		protected void updateItem(Parent item, boolean empty) {
			super.updateItem(item, empty);
			if (!empty) {
				setGraphic(buttons);
			}
		}

		void viewBook(ActionEvent evt) {
			g7.library.domain.Book book = (g7.library.domain.Book) CustomButtonCell.this.getTableView().getItems()
					.get(CustomButtonCell.this.getIndex());
			System.out.println("Item: " + book.toString());
			Start.displayPopup(generateBookInformation(book), "Book information", 400, 500);
		}

		void addCopy(ActionEvent evt) {
		}

		private javafx.scene.Parent generateBookInformation(g7.library.domain.Book book) {
			String authors = book.getAuthors().stream().map(Author::getFullName).collect(Collectors.joining(", "));

			VBox bookInfo = new VBox(10);
			Label title = new Label("Title:"), isbn = new Label("ISBN:"), available = new Label("Available copies:"),
					author = new Label("Author(s):");

			Stream.of(title, isbn, available, author).forEach(lb -> lb.setPrefWidth(150));

			bookInfo.getChildren().addAll(new HBox(title, new Label(book.getTitle())),
					new HBox(isbn, new Label(book.getIsbn())),
					new HBox(available, new Label(String.valueOf(book.getCopieAvailable()))),
					new HBox(author, new Label(authors)));
			StackPane pane = new StackPane(bookInfo);
			StackPane.setMargin(bookInfo, new Insets(15));

			return pane;
		}
	}
}
