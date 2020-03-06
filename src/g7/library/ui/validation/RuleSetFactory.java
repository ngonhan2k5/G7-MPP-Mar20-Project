package g7.library.ui.validation;

import java.awt.Component;
import java.util.HashMap;



final public class RuleSetFactory {
	private RuleSetFactory(){}
	static HashMap<Class<? extends RuleSet>, RuleSet> map = new HashMap<>();
	static {
		map.put(UserInfoRuleSet.class, new UserInfoRuleSet());
		map.put(AddressRuleSet.class, new AddressRuleSet());
	}
	public static RuleSet getRuleSet(Component c) {
		Class<? extends Component> cl = c.getClass();
		if(!map.containsKey(cl)) {
			throw new IllegalArgumentException(
					"No RuleSet found for this Component");
		}
		return map.get(cl);
	}
}
