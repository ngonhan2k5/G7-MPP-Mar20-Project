package g7.library.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import g7.library.frontcontroller.LibraryController;
import g7.library.frontcontroller.LogicViewController;
import g7.library.model.UserDataBuilder;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public abstract class BaseScene implements IData {

	private Scene scene;
	protected LibraryController libraryController;
	
	protected final HashMap<String, String> data = new HashMap<>();
	
	public String getFieldValue(String fieldName) {

		return data.get(fieldName);
	}
	
//	public String getFieldValue(String fieldName) {
//		if (Util.objectHasProperty(this.getField(fieldName), "text"))
//		return data.get(fieldName).getText();
//	}
	
	protected BaseScene() {
		libraryController = new LibraryController();
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
		StackPane.setMargin(mainContent, new Insets(10));

		// set prefer size
		leftMenu.setPrefWidth(220);
		mainContainer.minWidth(580);

		Separator separator = new Separator(Orientation.VERTICAL);
		separator.prefHeightProperty().bind(mainContainer.heightProperty());
		HBox mainScreen = new HBox(5, leftMenu, separator, mainContainer);

//		SplitPane splitPane = new SplitPane(leftMenu, mainContainer);
//		SplitPane.setResizableWithParent(leftMenu, false);
//		SplitPane.setResizableWithParent(mainContainer, true);
//		splitPane.setDividerPositions(0.3, 1);
//		splitPane.setMinSize(800, 500);
		
		return new Scene(mainScreen, 800, 500);
	}

	private Parent renderLeftMenu() {
		StackPane pane = new StackPane();
		VBox menuContainer = new VBox(10);

		// buttons
		Button btnLogin = createButton("Login", LoginScene.class.getName());
		Button btnAddNew = createButton("Add new member", AddNewMemberScene.class.getName());
		Button btnMemberManagement = createButton("Members Mamagement", MemberManagementScene.class.getName());
		Button btnBooksManagement = createButton("Books Management", BookManagementScene.class.getName());
		Button btnCheckout = createButton("Checkout", CheckoutScene.class.getName());
		Button btnLogout = new Button("Logout");
		Button btnExit = new Button("Exit");
		
		List<Node> fxNodes = new ArrayList<Node>();
		UserDataBuilder userData = Start.getUserData();
		if(userData == null) {
			fxNodes.add(btnLogin);
		} else {
			LogicViewController logicView = new LogicViewController(userData.systemUser());
			if(logicView.isLibMemberAddPermited()) {
				fxNodes.add(btnMemberManagement);
				fxNodes.add(btnAddNew);
			}

			if(logicView.isBookCheckoutPermited())
				fxNodes.add(btnCheckout);
			
			fxNodes.add(btnBooksManagement);
			fxNodes.add(btnLogout);
		}
		
		fxNodes.add(btnExit);
		
		btnAddNew.setOnAction(this::openAddNewMember);
		btnBooksManagement.setOnAction(this::openBooksManagement);
		btnMemberManagement.setOnAction(this::openMembersManagement);
		btnCheckout.setOnAction(this::openCheckout);
		btnLogin.setOnAction(this::openLogin);
		btnLogout.setOnAction(this::handleLogout);
		btnExit.setOnAction(this::handleExit);

		Stream.of(btnAddNew, btnMemberManagement, btnBooksManagement, btnCheckout, btnLogin, btnLogout, btnExit).forEach(b -> b.setPrefWidth(200));
		menuContainer.getChildren().addAll(fxNodes);
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

	private void openMembersManagement(ActionEvent evt) {Start.changeScene(MemberManagementScene.INSTANCE);}

	private void handleLogout(ActionEvent evt) {
		Start.trackOfUserData(null);
		Start.changeScene(LoginScene.INSTANCE);
	}
	
	private void handleExit(ActionEvent evt) {
		Start.exit();
	}
}
