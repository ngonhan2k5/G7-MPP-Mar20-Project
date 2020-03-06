package g7.library.ui.validation;

import java.awt.Component;
import java.util.HashMap;

import g7.library.ui.IData;



final public class RuleSetFactory {
	
	private RuleSetFactory(){}
	static HashMap<String, RuleSet> map = new HashMap<>();
	static {
		map.put("Member", new UserInfoRuleSet());
		map.put("Address", new AddressRuleSet());
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
