package g7.library.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CheckoutRecord implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<CheckoutEntry> checkoutEntries;
	private CheckoutRecord aFineRecord;

	public CheckoutRecord() {
		checkoutEntries = new ArrayList<CheckoutEntry>();
	}

	public List<CheckoutEntry> getCheckoutEntries() {
		return checkoutEntries;
	}

	public void setCheckoutEntries(List<CheckoutEntry> checkoutEntries) {
		this.checkoutEntries = checkoutEntries;
	}

	public CheckoutRecord getaFineRecord() {
		return aFineRecord;
	}

	public void setaFineRecord(CheckoutRecord aFineRecord) {
		this.aFineRecord = aFineRecord;
	}
}
