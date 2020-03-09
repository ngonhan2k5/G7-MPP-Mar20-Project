package g7.library.ui;

import g7.library.ui.validation.Attributes;
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

public class MemberManagementScene extends BaseScene {

	public static final MemberManagementScene INSTANCE = new MemberManagementScene();

	private MemberManagementScene() {
		super();
	}
	
	private TextField searchField;
	private Label message;
	private MemberTableView memberTableView;
	
	@Override
	protected Parent renderMainContent() {
		initFields();

		VBox vBox = new VBox(10);
		Label title = new Label("Members Management");
		title.getStyleClass().add("form-title");
		HBox titleContainer = new HBox(20, title);
		titleContainer.setAlignment(Pos.BOTTOM_LEFT);
		Button searchBtn = new Button("Search");
		Button addNew = new Button("+ Add");
		searchBtn.setOnAction(this::handleOnSearch);
		addNew.setOnAction(this::handleOnAddNew);
		HBox h1 = new HBox(10, searchField, searchBtn, addNew);
		vBox.getChildren().addAll(titleContainer, message, h1, this.memberTableView);
		AnchorPane anchorPane = new AnchorPane(vBox);
		anchorPane.setPrefSize(700, 500);

		AnchorPane.setTopAnchor(vBox, 0.0);
		AnchorPane.setBottomAnchor(vBox, 0.0);
		AnchorPane.setLeftAnchor(vBox, 0.0);
		AnchorPane.setRightAnchor(vBox, 0.0);
		return anchorPane;
	}

	private void handleOnAddNew(ActionEvent event) {
		Button saveMember = new Button("Save");
		saveMember.setOnAction(this::doAddMember);
		AddNewMemberScene.INSTANCE.reinitialize(false);
		PopupWindow.INSTANCE.displayModal(this.getScene(), AddNewMemberScene.INSTANCE.getScene(), "Add New Member", 480,
				500);
	}

	private void doAddMember(ActionEvent event) {
	}

	private void handleOnSearch(ActionEvent evt) {
		this.memberTableView.update(libraryController.searchLibraryMember(searchField.getText()));
	}

	private void initFields() {
		searchField = new TextField();
		message = new Label();
		memberTableView = new MemberTableView(libraryController.findAllMembers());
	}

	  @Override
	  public void getDataFromFields(Attributes<Control> attrs) {
	    // TODO Auto-generated method stub
	
	  }
}
