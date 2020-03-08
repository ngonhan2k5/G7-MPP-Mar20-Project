package g7.library.ui.validation;

import g7.library.ui.BaseScene;

/**
 * Rules:
 * 1. all fields non empty
 * 2. favorite restaurant cannot equal favorite movie
 * 3. id must be numeric
 * 4. firstname and lastname fields may not contain spaces or 
 * characters other than a-z, A-Z.
 *
 */
public class UserInfoRuleSet implements RuleSet {

	private BaseScene ob;
	@Override
	public void applyRules(BaseScene ob) throws RuleException {
//		scene = (ProfileWindow)ob;
		this.ob = ob;
		nonemptyRule(new String[]{"firstName", "lastName", "street", "city", "zip", "state"});
		idNumericRule();
//		favRestAndMovieRule();		
//		correctCharTypeRule();
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
	
//	private void favRestAndMovieRule() throws RuleException {
//		if(ob.getFavoriteMovieValue().trim().equals(
//				ob.getFavoriteRestaurantValue().trim())) {
//			throw new RuleException("Favorite movie must not equal favorite restaurant");
//		}
//	}

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
//	private void correctCharTypeRule() throws RuleException {
//		char[] fname = ob.getFirstNameValue().toCharArray();
//		char[] lname = ob.getLastNameValue().toCharArray();
//		for(char c: fname) {
//			if(!Util.isInRangeAtoZ(c) || !Util.isInRangeatoz(c)) 
//				throw new RuleException("All characters in first name must be "
//						+ "in range A-Z or a-z");
//		}
//		for(char c: lname) {
//			if(!Util.isInRangeAtoZ(c) || !Util.isInRangeatoz(c)) 
//				throw new RuleException("All characters in lasst name must be "
//						+ "in range A-Z or a-z");
//		}
//	}




}
