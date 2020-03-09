package g7.library.ui;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Stream;

import g7.library.dataaccess.SerializableDataPersistor.SaveMessage;
import g7.library.domain.Address;
import g7.library.domain.Book;
import g7.library.domain.LibraryMember;
import g7.library.domain.factory.LibraryMemberFactory;
import g7.library.service.LibraryServiceInterface;
import g7.library.service.impl.LibraryServiceImpl;
import g7.library.ui.validation.Attributes;
import g7.library.ui.validation.RuleException;
import g7.library.ui.validation.RuleSet;
import g7.library.ui.validation.RuleSetFactory;
import g7.library.ui.validation.Util;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;



public class AddNewBookScene extends BaseScene {

	public static final AddNewBookScene INSTANCE = new AddNewBookScene();
	private TextField iSBN, title, numOfCopy;
	private ComboBox<String> maxCheckOutLength;

	private Attributes<Control> attrs;
	
	private AddNewBookScene() {
		super();
	}

	@Override
	public Parent renderMainContent() {
		initFields();
		// Add New Member form
		HBox hBox_1 = new HBox(10);
		hBox_1.setAlignment(Pos.BASELINE_CENTER);
		VBox vBox = new VBox(10);

		Label title1 = new Label("Add New Book");
		title1.setStyle("-fx-font-size: 20");
		HBox titleContainer = new HBox(20, title1);
		titleContainer.setAlignment(Pos.BOTTOM_CENTER);
		
		

		HBox hButtons = new HBox(10);

		VBox memberFields = new VBox(10);
		
		Stream.of(iSBN, title).forEach(field -> field.setMinWidth(200));

		
		infoLbl.setTextFill(Color.web("red", 0.8));
		
		HBox h0 = new HBox(10, infoLbl);
		HBox h1 = new HBox(10, (new Label("Title: ")), title);
		HBox h2 = new HBox(10, new Label("ISBN: "), iSBN);
		HBox h3 = new HBox(10, new Label("Checkout Length: "), maxCheckOutLength);
		HBox h4 = new HBox(10, new Label("Number of Copies: "), numOfCopy);
		

		Stream.of(h0, h1, h2, h3, h4).forEach(h -> {h.setAlignment(Pos.BASELINE_LEFT); ((Label)h.getChildren().get(0)).setMinWidth(120);});

		memberFields.getChildren().addAll(h0, h1, h2, h3, h4);

		Button btn = new Button("Add Book");
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
		iSBN = new TextField() {
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
		title = new TextField();
		numOfCopy = new TextField();
		maxCheckOutLength = new ComboBox<String>();
		maxCheckOutLength.setItems(FXCollections.observableArrayList(Arrays.asList("20","7") ));
		maxCheckOutLength.setValue("20");
		
		Control [] controls = {iSBN, title, maxCheckOutLength, numOfCopy};
		String[] ids = {"iSBN", "title", "maxCheckOutLength", "numOfCopy"};
		attrs = new Attributes<Control>(ids, controls);
	}
	
	private void onSave() {
		infoLbl.setText("");
		getDataFromFields(attrs);
		RuleSet rules = RuleSetFactory.getRuleSet(RuleSetFactory.BOOK);
		try {
			rules.applyRules(INSTANCE);
		} catch (RuleException e) { 
			//Util.showAlert(e.getMessage());
			Util.showErrorLabel(infoLbl, e.getMessage());
			return;
		}
		
		LibraryServiceInterface srv = new LibraryServiceImpl();
		Book book = srv.searchBookByIBSN(getFieldValue("ISBN"));
		
		if (book != null) {
			Util.showErrorLabel(infoLbl, "A book with same ISBN has existed");
			return;
		}
		
		book = new Book(
				getFieldValue("iSBN"),
				getFieldValue("title"), 
				getIntFieldValue("maxCheckOutLength")
				
		);
		book.makeBookCopies(getIntFieldValue("numOfCopy"));
//		book.setMaxCheckoutLength(maxCheckoutLength);
		
		SaveMessage ret = srv.saveBook(book);
				
        	
		if (ret.isSuccessed()) {
			Util.showInfoLabel(infoLbl, "New Book and "+getIntFieldValue("numOfCopy")+" (copies) created!");
			clearFields(attrs.getFieldControls());
		}else {
			Util.showErrorLabel(infoLbl, ret.getMessage());
		}
	}
	

	@Override
	public void getDataFromFields(Attributes<Control> ats) {
		
		super.getDataFromFields(ats);
		
	}



	
}
