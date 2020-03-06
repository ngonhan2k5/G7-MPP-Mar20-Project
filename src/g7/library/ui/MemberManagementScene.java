package g7.library.ui;

import g7.library.domain.LibraryMember;
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
import javafx.scene.layout.VBox;

public class MemberManagementScene extends BaseScene {

	public static final MemberManagementScene INSTANCE = new MemberManagementScene();

	private MemberManagementScene() {
		super();
	}
	
	private TextField searchField;
	private Label message;
	private ObservableList<LibraryMember> members;
	
	@Override
	protected Parent renderMainContent() {
		initFields();
		
		// Checkout Book Form
		HBox hBox_1 = new HBox(10);
		hBox_1.setAlignment(Pos.BASELINE_CENTER);
		VBox vBox = new VBox(10);

		Label title = new Label("Members Management");
		title.setStyle("-fx-font-size: 20");
		HBox titleContainer = new HBox(20, title);
		titleContainer.setAlignment(Pos.BOTTOM_CENTER);

		Button searchBtn = new Button("Search");

		HBox h1 = new HBox(10, searchField, searchBtn);

		searchBtn.setOnAction(this::handleOnSearch);

		vBox.getChildren().addAll(titleContainer, message, h1, this.renderMembers());
		hBox_1.getChildren().add(vBox);

		return hBox_1;
	}
	
	@SuppressWarnings("unchecked")
	private Parent renderMembers() {
		HBox membersContainer = new HBox();
		this.members = loadMembers();
		
		TableView<LibraryMember> membersTable = new TableView<>();
		
		
		TableColumn<LibraryMember, String> titleColumn = new TableColumn<>("Title");
		TableColumn<LibraryMember, String> isbnColumn = new TableColumn<>("ISBN");
		TableColumn<LibraryMember, Integer> availableColumn = new TableColumn<>("Available");
		
		titleColumn.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>("title"));
		isbnColumn.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>("isbn"));
		availableColumn.setCellValueFactory(new PropertyValueFactory<LibraryMember, Integer>("available"));

		membersTable.setItems(this.members);
		membersTable.getColumns().addAll(titleColumn, isbnColumn, availableColumn);
		membersTable.setMinWidth(400);
		membersContainer.getChildren().add(membersTable);
		
		return membersContainer;
	}

	private void handleOnSearch(ActionEvent evt) {
		
	}
	
	private ObservableList<LibraryMember> loadMembers() {
		return FXCollections.observableArrayList();
	}
	
	private void initFields() {
		searchField = new TextField();
		message = new Label();
	}
}
