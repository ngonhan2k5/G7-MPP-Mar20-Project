package g7.library.domain;

public class LibraryMember extends User {
	private final String memberId;
	
	private static final long serialVersionUID = 1L;
	private CheckoutRecord checkoutRecord;
	
	public LibraryMember(String memberId, String firstName, String lastName, String phoneNumber, Address address) {
		super(firstName, lastName, phoneNumber, address);
		this.memberId = memberId;
	}

	public String getMemberId() {
		return memberId;
	}

	public CheckoutRecord getCheckoutRecord() {
		return checkoutRecord;
	}

	public void setCheckoutRecord(CheckoutRecord checkoutRecord) {
		this.checkoutRecord = checkoutRecord;
	}
	
	public void addNewCheckoutEntry(CheckoutEntry checkoutEntry) {
		if(checkoutRecord == null) checkoutRecord = new CheckoutRecord();
		checkoutRecord.getCheckoutEntries().add(checkoutEntry);
	}
}
