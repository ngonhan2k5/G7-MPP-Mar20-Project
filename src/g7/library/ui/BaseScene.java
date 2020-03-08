package g7.library.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import g7.library.domain.PermissionType;
import g7.library.frontcontroller.LibraryController;
import g7.library.frontcontroller.LogicViewController;
import g7.library.model.UserDataBuilder;
import g7.library.ui.validation.Attributes;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public abstract class BaseScene implements IData {

	private Scene scene;
	protected LibraryController libraryController;
	boolean hasLeftMenu = true;
	
	protected final HashMap<String, String> data = new HashMap<>();
	Label infoLbl = new Label();
	
	protected BaseScene() {
		libraryController = new LibraryController();
		scene = createScene();
	}
	
	protected BaseScene(boolean hasLeftMenu) {
		this();
		this.hasLeftMenu = hasLeftMenu;
		
	}
	
	public void reinitialize(boolean hasLeftMenu) {
		this.hasLeftMenu = hasLeftMenu;
		scene = createScene();
	}
	
	public Scene getScene() {return scene;}
	protected abstract Parent renderMainContent();

	protected Scene createScene() {
		// Row
		AnchorPane leftMenu = new AnchorPane(renderLeftMenu());
		Parent mainContent = this.renderMainContent();
		mainContent.getStyleClass().add("main-content");

		StackPane mainContainer = new StackPane(mainContent);
		StackPane.setMargin(mainContent, new Insets(10));

		// set prefer size
		leftMenu.setPrefWidth(220);
		mainContainer.setPrefWidth(680);

		Separator separator = new Separator(Orientation.VERTICAL);
		separator.prefHeightProperty().bind(mainContainer.heightProperty());

		HBox mainScreen = new HBox(5);
		
		if (hasLeftMenu)
			mainScreen.getChildren().addAll(leftMenu, separator, mainContainer);
		else
			mainScreen.getChildren().addAll( mainContainer);

		mainScreen.setPrefWidth(900);

		mainScreen.getStyleClass().add("main-container");

		return new Scene(mainScreen, 900, 600);
	}

	private Parent renderLeftMenu() {
		StackPane pane = new StackPane();
		VBox menuContainer = new VBox(10);

		// buttons
		Button btnLogin = createButton("_Login", LoginScene.class.getName());
		Button btnAddNew = createButton("_Add new member", AddNewMemberScene.class.getName());
		Button btnMemberManagement = createButton("_Members Mamagement", MemberManagementScene.class.getName());
		Button btnBooksManagement = createButton("_Books Management", BookManagementScene.class.getName());
		Button btnCheckout = createButton("_Checkout", CheckoutScene.class.getName());
		Button btnLogout = new Button("Log_out");
		Button btnExit = new Button("E_xit");
		
		List<Node> fxNodes = new ArrayList<Node>();
		UserDataBuilder userData = Start.getUserData();
		if(userData == null) {
			fxNodes.add(btnLogin);
			fxNodes.add( new Separator(Orientation.HORIZONTAL));
		} else {
			LogicViewController logicView = new LogicViewController(userData.systemUser());
			if(logicView.isPermissionGranted(PermissionType.ADD_MEMBER)) {
				fxNodes.add(btnMemberManagement);
				fxNodes.add(btnAddNew);
			}

			if(logicView.isBookCheckoutPermited())
				fxNodes.add(btnCheckout);
			
			fxNodes.add(btnBooksManagement);
			fxNodes.add( new Separator(Orientation.HORIZONTAL));
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
		pane.getStyleClass().add("left-menu");

		return pane;
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

	// Data fields access
	public String getFieldValueOrBlank(String fieldName) {
		return getFieldValue(fieldName, "");
	}

	public String getFieldValue(String fieldName) {
		return data.get(fieldName)==null?null:data.get(fieldName).trim();
	}

	public String getFieldValue(String fieldName, String defaultValue) {
		if (data.get(fieldName)==null)
			return defaultValue;

		return data.get(fieldName).trim();

	}

	public int getIntFieldValue(String fieldName) {
		String val = getFieldValue(fieldName);
		try {
			return Integer.parseInt(val);
		}catch(NumberFormatException e) {
			return 0;
		}
	}

//	public String getFieldValue(String fieldName) {
//		if (Util.objectHasProperty(this.getField(fieldName), "text"))
//		return data.get(fieldName).getText();
//	}
	/**
	 * Clear all fields text
	 * @param cons
	 */
	public void clearFields(Control[] cons) {
		
		Arrays.asList(cons).forEach(f -> {
			if (ComboBox.class.isAssignableFrom(f.getClass())) {
				((ComboBox<?>) f).setValue(null);
			}else if (TextInputControl.class.isAssignableFrom(f.getClass())){
				((TextInputControl) f).clear();
			}
			
		});
	}
	
	@Override
	public void getDataFromFields(Attributes<Control> ats) {
		
		ats.getList().forEach(f -> {
			if (ComboBox.class.isAssignableFrom(f.control.getClass())) {
				Object value = ((ComboBox) f.control).getValue();
				if (value != null)
					data.put(f.name, value.toString());
				else
					data.put(f.name, null);
			}else if (TextInputControl.class.isAssignableFrom(f.control.getClass())){
				data.put(f.name, ((TextInputControl) f.control).getText());
			}
			
		});
		System.out.println(data);
		
	}

}
