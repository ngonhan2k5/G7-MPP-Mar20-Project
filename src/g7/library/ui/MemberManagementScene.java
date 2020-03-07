package g7.library.ui;

import g7.library.domain.LibraryMember;
import g7.library.utils.UserInterfaceUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
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
		this.members = loadMembers();
		
		// Checkout Book Form
		HBox hBox_1 = new HBox(10);
		hBox_1.setAlignment(Pos.BASELINE_CENTER);
		VBox vBox = new VBox(10);
		Label title = new Label("Members Management");
		title.getStyleClass().add("form-title");
		HBox titleContainer = new HBox(20, title);
		titleContainer.setAlignment(Pos.BOTTOM_CENTER);
		Button searchBtn = new Button("Search");
		HBox h1 = new HBox(10, searchField, searchBtn);
		searchBtn.setOnAction(this::handleOnSearch);
		Parent membersContainer = UserInterfaceUtils.renderMembers(members);
		StackPane pane = new StackPane(membersContainer);
		StackPane.setMargin(membersContainer, new Insets(15)); // Make sure there are spaces around

		vBox.getChildren().addAll(titleContainer, message, h1, UserInterfaceUtils.renderMembers(members));
		hBox_1.getChildren().add(vBox);

		return hBox_1;
	}

	private void handleOnSearch(ActionEvent evt) {
		
	}
	
	private ObservableList<LibraryMember> loadMembers() {
		return FXCollections.observableArrayList(libraryController.findAllMembers());
	}
	
	private void initFields() {
		searchField = new TextField();
		message = new Label();
	}

	@Override
	public void getDataFromFields() {
		// TODO Auto-generated method stub
		
	}
}
