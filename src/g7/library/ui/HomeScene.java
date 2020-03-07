package g7.library.ui;

import g7.library.ui.validation.Attributes;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class HomeScene extends BaseScene {
	public static final HomeScene INSTANCE = new HomeScene();
	
	private HomeScene() {
		super();
	}
	
	@Override
	protected Parent renderMainContent() {
		StackPane pane = new StackPane();

		// Login form
		HBox hBox_1 = new HBox(10);
		hBox_1.setAlignment(Pos.BASELINE_CENTER);
		
		
		Label title = new Label("Welcome to Library Management Tool");
		title.getStyleClass().addAll("form-title", "home-title");

		HBox titleContainer = new HBox(20, title);
		titleContainer.setAlignment(Pos.BOTTOM_CENTER);
		
		hBox_1.getChildren().add(titleContainer);
		
		StackPane.setMargin(hBox_1, new Insets(20));
		pane.getChildren().add(hBox_1);
		pane.getStyleClass().add("home-page");

		return pane;
	}

	  @Override
	  public void getDataFromFields(Attributes<Control> attrs) {
	    // TODO Auto-generated method stub

	  }
}
