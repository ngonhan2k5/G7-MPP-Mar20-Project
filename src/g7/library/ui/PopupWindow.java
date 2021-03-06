package g7.library.ui;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class PopupWindow extends Stage {

	public static final PopupWindow INSTANCE = new PopupWindow();
	public static final double DEFAULT_WIDTH = 300;
	public static final double DEFAULT_HEIGHT = 250;
	
	
	public PopupWindow setModal(Scene owner) {
			this.initModality(Modality.WINDOW_MODAL);
			this.initOwner( owner.getWindow() );
			return this;
			
	}
	public void setScene(Parent content, double width, double height) {
		content.getStyleClass().add("popup-window");
		setMinWidth(width);
		setMinHeight(height);
		Scene scene = new Scene(content, width, height);
		scene.getStylesheets().add(getClass().getResource("scene.css").toExternalForm());
		setScene(scene);
	}

	public void setScene(Parent content, String title) {
		this.setScene(content, title, DEFAULT_WIDTH, DEFAULT_HEIGHT, null);
	}

	public void setScene(Parent content, String title, double width, double height, Parent item) {
		// Create close button
		VBox container = new VBox(30);
		Button closeButton = new Button("Close");
		closeButton.setOnAction(evt -> this.hide());

		Separator separator = new Separator();
		separator.prefWidthProperty().bind(container.widthProperty());
		HBox buttons = new HBox(10);
		if (item != null) buttons.getChildren().add(item);
		buttons.getChildren().add(closeButton);
		HBox sepLine = new HBox(separator);
		buttons.setAlignment(Pos.BOTTOM_RIGHT);

		// Wrapping content and make some spaces
		container.getChildren().addAll(content);
		StackPane pane = new StackPane(container, sepLine, buttons);
		StackPane.setMargin(container, new Insets(15));
		StackPane.setMargin(buttons, new Insets(15));
		this.setScene(pane, width, height);
		this.setTitle(title);
		
		System.out.println("");
	}
	
	public void displayPopup(Parent content, String title, double width, double height) {
		this.setScene(content, width, height);
		
		this.setTitle(title);
		this.show();
	}
	
	private Scene parent;
	public Stage displayModal(Scene parent, Scene content, String title, double width, double height) {
		PopupWindow pop = new PopupWindow();
		pop.setWidth(width);
		pop.setHeight(height);
		pop.setScene(content);
		pop.setTitle(title);
		

	    pop.setMinHeight(height);
	    pop.setMinWidth(width);
	    pop.setModal(parent);
	    pop.setOnCloseRequest(this::getOnClose);
		pop.showAndWait();
	    
	    return pop;
	}
	
	private void getOnClose(WindowEvent we) {
//		this.parent.?
	}
	

	public void setScene(Parent content, String title, Parent item) {
		this.setScene(content, title, DEFAULT_WIDTH, DEFAULT_HEIGHT, item);
		this.show();
	}
}
