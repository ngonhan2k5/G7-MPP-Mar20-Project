package g7.library.ui;

import java.util.Collection;
import java.util.Optional;

import g7.library.domain.Address;
import g7.library.domain.LibraryMember;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class MemberTableView extends TableView<LibraryMember> {
	private ObservableList<LibraryMember> libraryMember;
	
	public MemberTableView() {
		this(null);
	}
	
	public MemberTableView(Collection<LibraryMember> libraryMembers) {
		if(libraryMembers != null)
			this.libraryMember = FXCollections.observableArrayList(libraryMembers);
		
		initDesign();
	}
	
	@SuppressWarnings("unchecked")
	private void initDesign() {
		TableColumn<LibraryMember, String> memberIdColumn = new TableColumn<>("Member Id");
	    TableColumn<LibraryMember, String> firstNameColumn = new TableColumn<>("First Name");
	    TableColumn<LibraryMember, String> lastNameColumn = new TableColumn<>("Last Name");
	    TableColumn<LibraryMember, String> phoneColumn = new TableColumn<>("Phone");
	    TableColumn<LibraryMember, String> addressColumn = new TableColumn<>("Address");

	    memberIdColumn.setCellValueFactory(m -> new ReadOnlyStringWrapper(m.getValue().getMemberId()));
	    firstNameColumn.setCellValueFactory(m -> new ReadOnlyStringWrapper(m.getValue().getFirstName()));
	    lastNameColumn.setCellValueFactory(m -> new ReadOnlyStringWrapper(m.getValue().getLastName()));
	    phoneColumn.setCellValueFactory(m -> new ReadOnlyStringWrapper(m.getValue().getPhoneNumber()));
	    addressColumn.setCellValueFactory(m -> new ReadOnlyStringWrapper(Optional.ofNullable(m.getValue().getAddress()).map(Address::toString).orElse("")));

	    this.getColumns().addAll(memberIdColumn, firstNameColumn, lastNameColumn, phoneColumn, addressColumn);
	    this.setMinWidth(500);
	    this.setItems(this.libraryMember);
	    
	    AnchorPane anchorPane = new AnchorPane(this);
	    anchorPane.setPrefSize(700, 500);

	    AnchorPane.setTopAnchor(this, 0.0);
	    AnchorPane.setBottomAnchor(this, 0.0);
	    AnchorPane.setLeftAnchor(this, 0.0);
	    AnchorPane.setRightAnchor(this, 0.0);
	}
	
	public void update(Collection<LibraryMember> libraryMembers) {
		this.libraryMember = FXCollections.observableArrayList(libraryMembers);
		this.setItems(this.libraryMember);
		this.refresh();
	}
}
