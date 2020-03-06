package g7.library.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PopupWindow extends Stage {

	public static final PopupWindow INSTANCE = new PopupWindow();
	public static final double DEFAULT_WIDTH = 300;
	public static final double DEFAULT_HEIGHT = 250;

	private PopupWindow() {
	}

	public void setScene(Parent content, double width, double height) {
		setScene(new Scene(content, width, height));
	}

	public void setScene(Parent content, String title) {
		this.setScene(content, title, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	public void setScene(Parent content, String title, double width, double height) {
		// Create close button
		VBox container = new VBox(30);
		Button closeButton = new Button("Close");
		closeButton.setOnAction(evt -> this.hide());

		HBox buttons = new HBox(closeButton);
		buttons.setAlignment(Pos.BOTTOM_RIGHT);

		// Wrapping content and make some spaces
		container.getChildren().addAll(content);
		StackPane pane = new StackPane(container, buttons);
		StackPane.setMargin(container, new Insets(15));
		StackPane.setMargin(buttons, new Insets(15));

		this.setScene(pane, width, height);
		this.setTitle(title);
	}
}
