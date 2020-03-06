package g7.library.ui.validation;

//import java.awt.Component;

import g7.library.ui.IData;

public interface RuleSet {
	public void applyRules(IData ob) throws RuleException;
}
