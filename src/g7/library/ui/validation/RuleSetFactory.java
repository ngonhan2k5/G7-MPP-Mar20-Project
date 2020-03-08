package g7.library.ui.validation;

import java.awt.Component;
import java.util.HashMap;

import g7.library.ui.IData;



final public class RuleSetFactory {
	
	private RuleSetFactory(){}
	static HashMap<String, RuleSet> map = new HashMap<>();
	final public static String MEMBER = "Member";
	final public static String ADDRESS = "Address";
	final public static String BOOK = "Book";
	
	static {
		map.put(MEMBER, new UserInfoRuleSet());
		map.put(ADDRESS, new AddressRuleSet());
		map.put(BOOK, new BookRuleSet());
	}
	public static RuleSet getRuleSet(String cl) {

		if(!map.containsKey(cl)) {
			throw new IllegalArgumentException(
					"No RuleSet found for this Component");
		}
		return map.get(cl);
	}
}
