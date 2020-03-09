package g7.library.ui;

import g7.library.dataaccess.SerializableDataPersistor.SaveMessage;
import g7.library.dataaccess.storage.Constants;
import g7.library.domain.Address;
import g7.library.domain.LibraryMember;
import g7.library.service.LibraryServiceInterface;
import g7.library.service.impl.LibraryServiceImpl;
import g7.library.ui.validation.Attributes;
import g7.library.ui.validation.RuleException;
import g7.library.ui.validation.RuleSet;
import g7.library.ui.validation.RuleSetFactory;
import g7.library.ui.validation.Util;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
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

import java.sql.Timestamp;
import java.util.Date;
import java.util.stream.Stream;

public class AddNewMemberScene extends BaseScene {
  public static final AddNewMemberScene INSTANCE = new AddNewMemberScene();
  private TextField memberId, firstName, lastName, street, city, zip, phone;
  private ComboBox<String> state;
  private Attributes<Control> attrs;
  private AddNewMemberScene() {
    super();
  }

  @Override
  protected Parent renderMainContent() {
    initFields();

    // Add New Member form
    VBox vBox = new VBox(10);
    HBox hButtons = new HBox(10);
    VBox memberFields = new VBox(10);
    Stream.of(firstName, lastName, street, city, state, zip, phone).forEach(field -> field.setMinWidth(200));
    infoLbl.setTextFill(Color.web("red", 0.8));
    HBox h0 = new HBox(10, infoLbl);
    HBox h1 = new HBox(10, createLabel("First Name: ", 70), firstName);
    HBox h2 = new HBox(10, createLabel("Last Name: ", 70), lastName);
    HBox h3 = new HBox(10, createLabel("Street: ", 70), street);
    HBox h4 = new HBox(10, createLabel("City: ", 70), city);
    HBox h5 = new HBox(10, createLabel("State: ", 70), state);
    HBox h6 = new HBox(10, createLabel("Zip Code: ", 70), zip);
    HBox h7 = new HBox(10, createLabel("Phone: ", 70), phone);

    Stream.of(h0, h1, h2, h3, h4, h5, h6, h7).forEach(h -> h.setAlignment(Pos.BASELINE_CENTER));
    memberFields.getChildren().addAll(h0, h1, h2, h3, h4, h5, h6, h7);
    memberFields.setAlignment(Pos.TOP_CENTER);
    Button btn = new Button("Add");
		btn.setOnAction(e -> onSave());
		btn.setDefaultButton(true);
		hButtons.getChildren().add(btn);
		hButtons.setAlignment(Pos.BASELINE_RIGHT);
		hButtons.setMinHeight(50);
    vBox.getChildren().addAll(memberFields, hButtons);

    return vBox;
  }

  private Label createLabel(String text, double width) {
  	Label lb = new Label(text);
  	lb.setPrefWidth(width);
  	return lb;
	}

  private void initFields() {
    memberId = new TextField();
    firstName = new TextField();
    lastName = new TextField();
    street = new TextField();
    city = new TextField();


    // Create a combo box
    state = new ComboBox(FXCollections
        .observableArrayList(Constants.STATE_MAP.values()));

    zip = new TextField() {
      @Override
      public void replaceText(int start, int end, String text) {
        // If the replaced text would end up being invalid, then simply
        // ignore this call!
        if (!text.matches("[a-z]")) {
          super.replaceText(start, end, text);
        }
      }

      @Override
      public void replaceSelection(String text) {
        if (!text.matches("[a-z]")) {
          super.replaceSelection(text);
        }
      }
    };

    phone = new TextField() {
      @Override
      public void replaceText(int start, int end, String text) {
        // If the replaced text would end up being invalid, then simply
        // ignore this call!
        if (!text.matches("[a-z]")) {
          super.replaceText(start, end, text);
        }
      }

      @Override
      public void replaceSelection(String text) {
        if (!text.matches("[a-z]")) {
          super.replaceSelection(text);
        }
      }
    };

    Control[] controls = {memberId, firstName, lastName, street, city, zip, state, phone};
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
      Util.showErrorLabel(infoLbl, e.getMessage());
      return;
    }
    //method 2 - via Date
    Date date = new Date();
    Timestamp timestamp = new Timestamp(date.getTime());

    LibraryServiceInterface srv = new LibraryServiceImpl();
    SaveMessage ret = srv.addNewLibraryMember(
        new LibraryMember(
            String.valueOf(timestamp.getTime()),
            getFieldValue("firstName"),
            getFieldValue("lastName"),
            getFieldValue("phone"),
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
      MemberManagementScene.INSTANCE.refreshMembersTable();
    } else {
      Util.showErrorLabel(infoLbl, ret.getMessage());
    }
  }

  @Override
  public void getDataFromFields(Attributes<Control> ats) {
    super.getDataFromFields(ats);
  }
}
