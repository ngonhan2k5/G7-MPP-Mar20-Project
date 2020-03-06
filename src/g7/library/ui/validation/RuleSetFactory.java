package g7.library.ui.validation;

import java.awt.Component;
import java.util.HashMap;

import g7.library.ui.IData;



final public class RuleSetFactory {
	
	private RuleSetFactory(){}
	static HashMap<String, RuleSet> map = new HashMap<>();
	final public static String MEMBER = "Member";
	final public static String ADDRESS = "Address";
	
	static {
		map.put(MEMBER, new UserInfoRuleSet());
		map.put(ADDRESS, new AddressRuleSet());
	}
	public static RuleSet getRuleSet(String cl) {
//		Class<? extends Component> cl = (Class<? extends Component>) c.getClass();
		if(!map.containsKey(cl)) {
			throw new IllegalArgumentException(
					"No RuleSet found for this Component");
		}
		return map.get(cl);
	}
}
