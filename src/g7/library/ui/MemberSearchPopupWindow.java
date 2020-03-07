package g7.library.ui;

import g7.library.frontcontroller.LibraryController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MemberSearchPopupWindow extends PopupWindow {
	public static final PopupWindow INSTANCE = new MemberSearchPopupWindow();
	
	protected LibraryController libraryController = new LibraryController();
	private MemberTableView memberTableView;
	private TextField searchText;
	
	public MemberSearchPopupWindow() {
		this.searchText = new TextField();
		this.memberTableView = new MemberTableView();
		this.memberTableView.update(libraryController.findAllMembers());
		
		Button searchButton = new Button("Search");
		Button choose = new Button("OK");

		HBox topButtons = new HBox(10, searchText, searchButton);
		
		Button closeButton = new Button("Close");
		closeButton.setOnAction(evt -> this.hide());
		
		HBox bottomButtons = new HBox(10, choose, closeButton);
		Separator separator = new Separator();
		separator.prefWidthProperty().bind(topButtons.widthProperty());
		HBox sepLine = new HBox(separator);
		bottomButtons.setAlignment(Pos.BOTTOM_RIGHT);
		
		VBox finderContainer = new VBox(10, topButtons, memberTableView, sepLine, bottomButtons);
		StackPane pane = new StackPane(finderContainer);
		StackPane.setMargin(finderContainer, new Insets(15));
		
		this.setScene(pane, 550, 600);
		this.setTitle("Member Finder");
	}
}
