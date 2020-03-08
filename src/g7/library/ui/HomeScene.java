package g7.library.ui;

import g7.library.ui.validation.Attributes;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HomeScene extends BaseScene {
	public static final HomeScene INSTANCE = new HomeScene();
	
	private HomeScene() {
		super();
	}
	
	@Override
	protected Parent renderMainContent() {
		VBox vBox = new VBox();
		Label title = new Label("Welcome to Library Management Tool");
		title.getStyleClass().addAll("form-title", "home-title");
		HBox titleContainer = new HBox(20, title);
		titleContainer.setAlignment(Pos.BOTTOM_CENTER);
		vBox.getChildren().add(titleContainer);
		vBox.getStyleClass().add("home-page");
		return vBox;
	}

	  @Override
	  public void getDataFromFields(Attributes<Control> attrs) {
	    // TODO Auto-generated method stub

	  }
}
