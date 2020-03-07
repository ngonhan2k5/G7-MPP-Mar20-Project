package g7.library.ui;

import g7.library.domain.SystemUser;
import g7.library.model.UserDataBuilder;
import g7.library.ui.validation.Attributes;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class LoginScene extends BaseScene {
	public static final LoginScene INSTANCE = new LoginScene();
	
	private LoginScene() {
		super();
	}
	
	private TextField userName;
	private PasswordField password;

	@Override
	protected Parent renderMainContent() {
		initFields();
		
		StackPane pane = new StackPane();

		// Login form
		HBox hBox_0 = new HBox(50);
		Label globalMessage = new Label("");
		globalMessage.setStyle("-fx-text-fill: red;");
		hBox_0.getChildren().add(globalMessage);
		pane.getChildren().add(hBox_0);
		
		HBox hBox_1 = new HBox(10);
		hBox_1.setAlignment(Pos.BASELINE_CENTER);
		VBox vBox = new VBox(10);
		
		Label title = new Label("Login");
		title.setStyle("-fx-font-size: 20");
		HBox titleContainer = new HBox(20, title);
		titleContainer.setAlignment(Pos.BASELINE_CENTER);

		HBox hBox_2 = new HBox(10);
		HBox hBox_3 = new HBox(10);
		HBox hBox_4 = new HBox(10);
		hBox_2.setAlignment(Pos.CENTER_RIGHT);
		hBox_3.setAlignment(Pos.CENTER_RIGHT);

		hBox_2.getChildren().addAll(new Label("User Name: "), userName);
		hBox_3.getChildren().addAll(new Label("Password: "), password);

		Button btn = new Button("Login");
		hBox_4.getChildren().add(btn);
		hBox_4.setAlignment(Pos.BASELINE_RIGHT);
		hBox_4.setMinHeight(80);

		btn.setOnAction((event) -> {
			SystemUser systemUser = this.libraryController.login(userName.getText(), password.getText());
			if(systemUser != null) {
				Start.trackOfUserData(new UserDataBuilder(systemUser));
				Start.changeScene(HomeScene.INSTANCE);
			} else {
				userName.setText("");
				password.setText("");
				globalMessage.setText("Login Failed. The provided credentials were not valid.");
			}
		});

		vBox.getChildren().addAll(titleContainer, hBox_2, hBox_3, hBox_4);
		hBox_1.getChildren().add(vBox);

		StackPane.setMargin(hBox_1, new Insets(20));
		pane.getChildren().add(hBox_1);

		return pane;
	}

	private void initFields() {
		userName = new TextField("admin");
		password = new PasswordField();
		password.setText("12345");
	}

	  @Override
	  public void getDataFromFields(Attributes<Control> attrs) {
	    // TODO Auto-generated method stub

	  }
}
