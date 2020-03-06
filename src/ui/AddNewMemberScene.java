package ui;

import java.util.stream.Stream;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AddNewMemberScene extends BaseScene {

	public static final AddNewMemberScene INSTANCE = new AddNewMemberScene();
	private TextField memberId, firstName, lastName, street, city, zip, state, phone;

	private AddNewMemberScene() {
		super();
	}

	@Override
	protected Parent renderMainContent() {
		initFields();
		
		// Add New Member form
		HBox hBox_1 = new HBox(10);
		hBox_1.setAlignment(Pos.BASELINE_CENTER);
		VBox vBox = new VBox(10);

		Label title = new Label("Add New Member Form");
		title.setStyle("-fx-font-size: 20");
		HBox titleContainer = new HBox(20, title);
		titleContainer.setAlignment(Pos.BOTTOM_CENTER);

		HBox hButtons = new HBox(10);

		VBox memberFields = new VBox(10);
		
		Stream.of(firstName, lastName, street, city, state, zip, phone).forEach(field -> field.setMinWidth(200));

		HBox h1 = new HBox(10, new Label("First Name: "), firstName);
		HBox h2 = new HBox(10, new Label("Last Name: "), lastName);
		HBox h3 = new HBox(10, new Label("Street: "), street);
		HBox h4 = new HBox(10, new Label("City: "), city);
		HBox h5 = new HBox(10, new Label("State: "), state);
		HBox h6 = new HBox(10, new Label("Zipe: "), zip);
		HBox h7 = new HBox(10, new Label("Phone: "), phone);

		Stream.of(h1, h2, h3, h4, h5, h6, h7).forEach(h -> h.setAlignment(Pos.BASELINE_RIGHT));

		memberFields.getChildren().addAll(h1, h2, h3, h4, h5, h6, h7);

		Button btn = new Button("Add");
		hButtons.getChildren().add(btn);
		hButtons.setAlignment(Pos.BASELINE_RIGHT);
		hButtons.setMinHeight(50);

		btn.setOnAction((event) -> {

		});

		vBox.getChildren().addAll(titleContainer, memberFields, hButtons);
		hBox_1.getChildren().add(vBox);

		return hBox_1;
	}

	private void initFields() {
		memberId = new TextField();
		firstName = new TextField();
		lastName = new TextField();
		street = new TextField();
		city = new TextField();
		zip = new TextField();
		state = new TextField();
		phone = new TextField();
	}
}