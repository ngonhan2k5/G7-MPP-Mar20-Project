package g7.library.ui.validation;

import g7.library.ui.BaseScene;

public class BookRuleSet implements RuleSet {

	private BaseScene ob;
	public void applyRules(BaseScene ob) throws RuleException {

		this.ob = ob;
		nonemptyRule(new String[]{ "title","iSBN", "numOfCopy"});
		idNumericRule();
		idNumericRule2();
			
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
		String val = ob.getFieldValue("iSBN");
		
		if (val.isEmpty()) 
			return;
		

		
		if (!val.matches("[0-9]*"))
			throw new RuleException("ISBN number must be numeric");
	}
	
	private void idNumericRule2() throws RuleException {
		String val = ob.getFieldValue("numOfCopy");
		
		if (val.isEmpty()) 
			return;
		
		if (val.length()> 5)
			throw new RuleException("NumOfCopy number too big");
		
		if (!val.matches("[0-9]*"))
			throw new RuleException("numOfCopy number must be numeric");
	}
}
