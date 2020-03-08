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

		HBox hBox_1 = new HBox(10);
		hBox_1.setAlignment(Pos.BASELINE_CENTER);
		VBox vBox = new VBox(10);
		Label title = new Label("Members Management");
		title.getStyleClass().add("form-title");
		HBox titleContainer = new HBox(20, title);
		titleContainer.setAlignment(Pos.BOTTOM_LEFT);
		Button searchBtn = new Button("Search");
		HBox h1 = new HBox(10, searchField, searchBtn);
		searchBtn.setOnAction(this::handleOnSearch);
		vBox.getChildren().addAll(titleContainer, message, h1, this.memberTableView);
		hBox_1.getChildren().add(vBox);
		AnchorPane anchorPane = new AnchorPane(hBox_1);
		anchorPane.setPrefSize(700, 500);

		AnchorPane.setTopAnchor(hBox_1, 0.0);
		AnchorPane.setBottomAnchor(hBox_1, 0.0);
		AnchorPane.setLeftAnchor(hBox_1, 0.0);
		AnchorPane.setRightAnchor(hBox_1, 0.0);
		return anchorPane;
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
