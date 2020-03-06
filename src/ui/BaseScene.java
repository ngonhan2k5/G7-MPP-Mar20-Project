package ui;

import java.util.stream.Stream;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public abstract class BaseScene {

	private Scene scene;
	
	protected BaseScene() {
		scene = createScene();
		loadCss();
	}
	
	public void reinitialize() {
		scene = createScene();
		loadCss();
	}
	
	public Scene getScene() {return scene;}
	protected abstract Parent renderMainContent();

	protected Scene createScene() {
		// Row
		AnchorPane leftMenu = new AnchorPane(renderLeftMenu());
		Parent mainContent = this.renderMainContent();
		StackPane mainContainer = new StackPane(mainContent);
		StackPane.setMargin(mainContent, new Insets(15));

		// set prefer size
		leftMenu.setPrefWidth(250);
		mainContainer.minWidth(450);

		SplitPane splitPane = new SplitPane(leftMenu, mainContainer);
		SplitPane.setResizableWithParent(leftMenu, false);
		SplitPane.setResizableWithParent(mainContainer, true);
		splitPane.setDividerPositions(0.3, 1);
		splitPane.setMinSize(700, 500);
		
		return new Scene(splitPane, 700, 500);
	}

	private Parent renderLeftMenu() {
		StackPane pane = new StackPane();
		VBox menuContainer = new VBox(10);

		// buttons
		Button btnLogin = createButton("Login", LoginScene.class.getName());
		Button btnAddNew = createButton("Add new member", AddNewMemberScene.class.getName());
		Button btnBooksManagement = createButton("Books Management", BookManagementScene.class.getName());
		Button btnCheckout = createButton("Checkout", CheckoutScene.class.getName());
		Button btnLogout = new Button("Logout");
		Button btnExit = new Button("Exit");
		
		btnAddNew.setOnAction(this::openAddNewMember);
		btnBooksManagement.setOnAction(this::openBooksManagement);
		btnCheckout.setOnAction(this::openCheckout);
		btnLogin.setOnAction(this::openLogin);
		btnLogout.setOnAction(this::handleLogout);
		btnExit.setOnAction(this::handleExit);

		Stream.of(btnAddNew, btnBooksManagement, btnCheckout, btnLogin, btnLogout, btnExit).forEach(b -> b.setPrefWidth(150));
		menuContainer.getChildren().addAll(btnAddNew, btnBooksManagement, btnCheckout, btnLogin, btnLogout, btnExit);
		StackPane.setMargin(menuContainer, new Insets(20));
		pane.getChildren().add(menuContainer);

		return pane;
	}
	
	private void loadCss() {
		scene.getStylesheets().add(getClass().getResource("scene.css").toExternalForm());
	}
	
	private Button createButton(String text, String className) {
		Button btn = new Button(text);
		btn.setDisable(className.equals(Start.getActiveScene()));
		return btn;
	}
	
	private void openLogin(ActionEvent evt) {
		Start.changeScene(LoginScene.INSTANCE);
	}
	
	private void openBooksManagement(ActionEvent evt) {
		Start.changeScene(BookManagementScene.INSTANCE);
	}
	
	private void openAddNewMember(ActionEvent evt) {
		Start.changeScene(AddNewMemberScene.INSTANCE);
	}
	
	private void openCheckout(ActionEvent evt) {
		Start.changeScene(CheckoutScene.INSTANCE);
	}
	
	private void handleLogout(ActionEvent evt) {
		Start.changeScene(HomeScene.INSTANCE);
	}
	
	private void handleExit(ActionEvent evt) {
		Start.exit();
	}
}