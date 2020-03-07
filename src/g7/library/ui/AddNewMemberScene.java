package g7.library.ui;
import java.util.stream.Stream;

import g7.library.dataaccess.DataPersistor.SaveMessage;
import g7.library.domain.Address;
import g7.library.domain.LibraryMember;
import g7.library.domain.factory.LibraryMemberFactory;
import g7.library.service.LibraryServiceInterface;
import g7.library.service.impl.LibraryServiceImpl;
import g7.library.ui.validation.Attributes;
import g7.library.ui.validation.RuleException;
import g7.library.ui.validation.RuleSet;
import g7.library.ui.validation.RuleSetFactory;
import g7.library.ui.validation.Util;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;



public class AddNewMemberScene extends BaseScene {

	public static final AddNewMemberScene INSTANCE = new AddNewMemberScene();
	private TextField memberId, firstName, lastName, street, city, zip, state, phone;

	private Attributes<Control> attrs;

	
	
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

		
		infoLbl.setTextFill(Color.web("red", 0.8));
		
		HBox h0 = new HBox(10, infoLbl);
		HBox h1 = new HBox(10, new Label("First Name: "), firstName);
		HBox h2 = new HBox(10, new Label("Last Name: "), lastName);
		HBox h3 = new HBox(10, new Label("Street: "), street);
		HBox h4 = new HBox(10, new Label("City: "), city);
		HBox h5 = new HBox(10, new Label("State: "), state);
		HBox h6 = new HBox(10, new Label("Zipcode: "), zip);
		HBox h7 = new HBox(10, new Label("Phone: "), phone);

		Stream.of(h0, h1, h2, h3, h4, h5, h6, h7).forEach(h -> h.setAlignment(Pos.BASELINE_RIGHT));

		memberFields.getChildren().addAll(h0, h1, h2, h3, h4, h5, h6, h7);

		Button btn = new Button("Add");
		hButtons.getChildren().add(btn);
		hButtons.setAlignment(Pos.BASELINE_RIGHT);
		hButtons.setMinHeight(50);

		btn.setOnAction((event) -> {
			onSave();
		});
		
		btn.setDefaultButton(true);

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
		zip =  new TextField() {
		    @Override public void replaceText(int start, int end, String text) {
		        // If the replaced text would end up being invalid, then simply
		        // ignore this call!
		        if (!text.matches("[a-z]")) {
		            super.replaceText(start, end, text);
		        }
		    }

		    @Override public void replaceSelection(String text) {
		        if (!text.matches("[a-z]")) {
		            super.replaceSelection(text);
		        }
		    }
		};

		state = new TextField() {
		    @Override public void replaceText(int start, int end, String text) {
		        // If the replaced text would end up being invalid, then simply
		        // ignore this call!
		    	System.out.println(text);
		        if (text.matches("[A-Z]") && this.getText().length()<2) {
		            super.replaceText(start, end, text);
		        }
		        
		    }

		    @Override public void replaceSelection(String text) {
		        if (text.matches("[A-Z]") && this.getText().length()<2) {
		            super.replaceSelection(text);
		        }
		    }
		};

		phone = new TextField() {
		    @Override public void replaceText(int start, int end, String text) {
		        // If the replaced text would end up being invalid, then simply
		        // ignore this call!
		        if (!text.matches("[a-z]")) {
		            super.replaceText(start, end, text);
		        }
		    }

		    @Override public void replaceSelection(String text) {
		        if (!text.matches("[a-z]")) {
		            super.replaceSelection(text);
		        }
		    }
		};
		
		
		TextInputControl [] controls = {memberId, firstName, lastName, street, city, zip, state, phone};
		String[] ids = {"memberId", "firstName", "lastName", "street", "city", "zip", "state", "phone"};
		attrs = new Attributes<Control>(ids, controls);
	}
	
	private void onSave() {
		infoLbl.setText("");
		getDataFromFields(attrs);
		RuleSet rules = RuleSetFactory.getRuleSet(RuleSetFactory.MEMBER);
		try {
			rules.applyRules(INSTANCE);
		} catch (RuleException e) { 
			//Util.showAlert(e.getMessage());
			Util.showErrorLabel(infoLbl, e.getMessage());
			return;
		}
		
		System.out.println("111"+getFieldValue("zip", "0000"));
		LibraryServiceInterface srv = new LibraryServiceImpl();
		SaveMessage ret = srv.addNewLibraryMember(
				new LibraryMember(
					getFieldValue("memberId"), 
					getFieldValue("firstName"), 
					getFieldValue("lastName"), 
					getFieldValue("phoneNumber"),
					new Address(
							getFieldValue("street"),
							getFieldValue("city"),
							getFieldValue("state"),
							getIntFieldValue("zip")
					)
				)
		);
		
		if (ret.isSuccessed()) {
			Util.showInfoLabel(infoLbl, "Add new Member succeeded");
			clearFields(attrs.getFieldControls());
		}else {
			Util.showErrorLabel(infoLbl, ret.getMessage());
		}
	}
	

	@Override
	public void getDataFromFields(Attributes<Control> ats) {
		
		ats.getList().forEach(f -> data.put(f.name, ((TextInputControl) f.control).getText()));
		System.out.println(data);
//		for(int i=0; i< controls.length; i++) {
//			TextInputControl f = controls[i];
//			System.out.println(ids[i] + ":" + f.getText());
//			data.put(ids[i], f.getText());
//			
//		}
		
	}



	
}
