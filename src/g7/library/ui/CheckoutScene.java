package g7.library.ui;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

import g7.library.dataaccess.SerializableDataPersistor.SaveMessage;
import g7.library.domain.Book;
import g7.library.domain.CheckoutEntry;
import g7.library.domain.CheckoutRecord;
import g7.library.domain.LibraryMember;
import g7.library.ui.validation.Attributes;
import g7.library.utils.UserInterfaceUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CheckoutScene extends BaseScene {
	public static final CheckoutScene INSTANCE = new CheckoutScene();

	private CheckoutScene() {
		super();
	}

	private Label message;
	private Label errorMessage;
	private TextField memberId;
	private TextField isbn;
	private VBox checkoutRecordsPanel;

	public void showMessage(String msg) {
		message.setText(msg);
	}
	
	public void assignBookISBN(String isbn) {
		this.isbn.setText(isbn);
	}
	
	public void assignMemberId(String memberId) {
		this.memberId.setText(memberId);

		if (memberId != null && !memberId.isEmpty()) {
			// display checkout records
			renderMemberCheckoutRecords(memberId);
		}
	}

	@Override
	protected Parent renderMainContent() {
		initFields();

		// Checkout Book Form
		HBox hBox_1 = new HBox(10);
		hBox_1.setAlignment(Pos.BASELINE_LEFT);
		VBox vBox = new VBox(10);

		Label title = new Label("Checkout Book Form");
		title.getStyleClass().add("form-title");

		HBox titleContainer = new HBox(20, title);
		titleContainer.setAlignment(Pos.BOTTOM_LEFT);

		message.getStyleClass().addAll("message");
		errorMessage.getStyleClass().addAll("error-message");
		HBox errorContainer = new HBox(20, errorMessage);
		HBox messageContainer = new HBox(20, message);
		errorContainer.setAlignment(Pos.BASELINE_LEFT);

		HBox hButtons = new HBox(10);
		VBox checkoutFields = new VBox(10);
		Button checkout = new Button("Checkout");
		Button findBook = new Button();
		Button findMember = new Button();
		findBook.getStyleClass().addAll("find-btn", "btn-icon");
		findMember.getStyleClass().addAll("find-btn", "btn-icon");
		checkout.setOnAction(this::handleOnSubmit);
		findBook.setOnAction(this::findBooks);
		findMember.setOnAction(this::findMembers);
		Separator separator = new Separator();
		separator.prefWidthProperty().bind(checkoutFields.widthProperty());

		HBox h1 = new HBox(5, new Label("Member ID: "), memberId, findMember);
		HBox h2 = new HBox(5, new Label("ISBN: "), isbn, findBook);
		HBox h3 = new HBox(separator);

		Stream.of(h1, h2).forEach(h -> h.setAlignment(Pos.BASELINE_RIGHT));

		checkoutFields.getChildren().addAll(h1, h2, h3, hButtons);
		hButtons.setAlignment(Pos.BASELINE_RIGHT);
		hButtons.setMinHeight(50);
		hButtons.getChildren().addAll(checkout);

		HBox checkoutFormContainer = new HBox(checkoutFields);
		checkoutFormContainer.setAlignment(Pos.BASELINE_LEFT);

		vBox.getChildren().addAll(titleContainer, errorContainer, messageContainer, checkoutFormContainer, checkoutRecordsPanel);
		hBox_1.getChildren().add(vBox);
		hBox_1.maxWidth(400);

		return hBox_1;
	}

	private void renderMemberCheckoutRecords(String mId) {
		VBox container = new VBox(10);
		Separator separator = new Separator();
		Label title = new Label("Checkout records");
		title.getStyleClass().add("form-title");
		Label memberName = new Label("Member Name: ");
		Label memberId = new Label("Member Id: ");
		HBox id = new HBox(memberId,  new Label(mId));
		if (mId != null && !mId.isEmpty()) {
			LibraryMember member = libraryController.findMemberById(mId);
			if (member != null) {
				HBox name = new HBox(memberName, new Label(member.getFullName()));
				ObservableList<CheckoutEntry> records =
						FXCollections.observableArrayList(
								Optional.ofNullable(member.getCheckoutRecord())
										.map(CheckoutRecord::getCheckoutEntries)
										.orElse(new ArrayList<>())
						);
				Parent checkoutRecordsTable =
						UserInterfaceUtils.renderCheckoutRecords(records);
				container.getChildren().addAll(separator, title, name, id, checkoutRecordsTable);
				checkoutRecordsPanel.getChildren().clear();
				checkoutRecordsPanel.getChildren().addAll(container);
				return;
			}
		}
		container.getChildren().addAll(separator, title, memberName, id, new Label("No available record"));
		checkoutRecordsPanel.getChildren().clear();
		checkoutRecordsPanel.getChildren().addAll(container);
	}

	private void initFields() {
		message = new Label();
		errorMessage = new Label();
		memberId = new TextField();
		isbn = new TextField();
		checkoutRecordsPanel = new VBox();
	}

	private void handleOnSubmit(ActionEvent evt) {
		if("".equals(isbn.getText()) || "".equals(memberId.getText())) {
			errorMessage.setText("Member Id and ISBN are required.");
			return;
		}
		
		if(!libraryController.findAllMembers().stream().anyMatch(
				u -> u.getMemberId().equals(memberId.getText()))) {
			errorMessage.setText("Member Id is invalid.");
			return;
		}
		
		Book book = libraryController.findBookByISBN(isbn.getText());
		if(book == null) {
			errorMessage.setText("Book is not found or unavailable.");
			return;
		}

		SaveMessage result = libraryController.checkoutBook(isbn.getText(), memberId.getText());
		if(result.isSuccessed()) {
			renderMemberCheckoutRecords(memberId.getText());
			message.setText("Processed book checkout successfully.");
			isbn.setText("");
			memberId.setText("");
		} else {
			message.setText(result.showErrors());
		}
	}

	private void findBooks(ActionEvent evt) {
		BookSearchPopupWindow.INSTANCE.show();
	}

	private void findMembers(ActionEvent evt) {
		MemberSearchPopupWindow.INSTANCE.show();
	}
	
	  @Override
	  public void getDataFromFields(Attributes<Control> attrs) {
	    // TODO Auto-generated method stub

	  }
}
