package g7.library.ui.validation;

import g7.library.ui.BaseScene;

public class BookRuleSet {

	private BaseScene ob;
	public void applyRules(BaseScene ob) throws RuleException {

		this.ob = ob;
		nonemptyRule(new String[]{"firstName", "lastName", "street", "city", "zip", "state"});
		idNumericRule();

		RuleSetFactory.getRuleSet(RuleSetFactory.ADDRESS).applyRules(ob);
		
	}
	
	private void nonemptyRule(String[] fields) throws RuleException {
		//System.out.println("A"+ob.getFieldValue(fields[0]).isEmpty());
		for(int i=0; i< fields.length; i++) {
			boolean s = ob.getFieldValue(fields[i]).isEmpty();
			if (ob.getFieldValue(fields[i]).isEmpty())
			   throw new RuleException(Util.camel2Name(fields[i]) + " field must be nonempty");
		}
	}
	

	private void idNumericRule() throws RuleException {
		String val = ob.getFieldValue("phone");
		if (val.isEmpty()) return;
		try {
			Integer.parseInt(val);
			//val is numeric
		} catch(NumberFormatException e) {
			throw new RuleException("Phone number must be numeric");
		}		
	}
}
