package g7.library.ui;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class HomeScene extends BaseScene {
	public static final HomeScene INSTANCE = new HomeScene();
	
	private HomeScene() {
		super();
	}
	
	@Override
	protected Parent renderMainContent() {
		// Login form
		HBox hBox_1 = new HBox(10);
		hBox_1.setAlignment(Pos.BASELINE_CENTER);
		Label title = new Label("Welcome to Library Management Tool");
		title.getStyleClass().addAll("form-title", "home-title");
		HBox titleContainer = new HBox(20, title);
		titleContainer.setAlignment(Pos.BOTTOM_CENTER);
		hBox_1.getChildren().add(titleContainer);
		hBox_1.getStyleClass().add("home-page");
		return hBox_1;
	}

	@Override
	public void getDataFromFields() {
		// TODO Auto-generated method stub
		
	}
}
