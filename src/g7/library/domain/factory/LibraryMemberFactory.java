package g7.library.domain.factory;

import java.util.Date;

import g7.library.dataaccess.SingletoneDataLoader;
import g7.library.domain.CheckoutEntry;
import g7.library.domain.LibraryMember;

public class LibraryMemberFactory {
	private LibraryMember libraryMember;

	private LibraryMemberFactory(LibraryMember libraryMember) {
		this.libraryMember = libraryMember;
	}

	public static LibraryMemberFactory getInstance(String memberId) {
		LibraryMember member = SingletoneDataLoader.getInstance().getLibraryMember().get(memberId);
		if(member == null) throw new IllegalArgumentException("Lib Member Not Found.");
		return new LibraryMemberFactory(member);
	}
	
	public LibraryMember addNewCheckoutEntry(String bookIsbn, Date checkoutDate, Date returnDueDate) {
		CheckoutEntry checkoutEntry = 
				CheckoutEntryFactory.getInstance(bookIsbn).createNewCheckoutEntry(checkoutDate, returnDueDate);
		libraryMember.addNewCheckoutEntry(checkoutEntry);
		return libraryMember;
	}

}
