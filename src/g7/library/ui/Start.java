package g7.library.ui;

import g7.library.model.UserDataBuilder;
import g7.library.ui.validation.Util;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Start extends Application {

	private static Stage stage = null;
	
	private static String activeScene = null;
	
	public static void trackOfUserData(UserDataBuilder userData) {
		stage.setUserData(userData);
	}
	
	public static UserDataBuilder getUserData() {
		return (UserDataBuilder)stage.getUserData();
	}
	
	public static String getActiveScene() {
		return activeScene;
	}
	
	public static Stage getCurrentStage() {
		return stage;
	}

	public static void main(String[] args) {
		launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		
		// set minimum size
		stage.setMinHeight(600);
		stage.setWidth(900);
		stage.setResizable(false);
		stage.setTitle("Library Management");
		changeScene(HomeScene.INSTANCE);
		stage.show();
		stage.setOnCloseRequest(evt -> System.exit(1));
		centeringWindow();
	}

	public static void changeScene(BaseScene scene) {
		if (stage != null) {
			activeScene = scene.getClass().getName();
			scene.reinitialize(true);
			Scene targetScene = scene.getScene();
			targetScene.getStylesheets().add(Start.class.getResource("scene.css").toExternalForm());
			stage.setScene(scene.getScene());
		}
	}
	
	private void centeringWindow() {
		Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
	}
	
	public static void displayPopup(Parent content, String title) {
		PopupWindow.INSTANCE.setScene(content, title);
		PopupWindow.INSTANCE.show();
	}
	
	public static void displayPopup(Parent content, String title, double width, double height, Parent item) {
		PopupWindow.INSTANCE.setScene(content, title, width, height, item);
		PopupWindow.INSTANCE.show();
	}
	
	public static void hidePopup() {
		PopupWindow.INSTANCE.hide();
	}

 	public static void exit() {
 		stage.close();
 	}
}
