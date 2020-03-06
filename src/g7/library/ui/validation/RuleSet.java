package g7.library.ui.validation;

import g7.library.ui.BaseScene;

//import java.awt.Component;

import g7.library.ui.IData;

public interface RuleSet {
	public void applyRules(BaseScene ob) throws RuleException;
//	public void applyRules(String ob) throws RuleException;
}
