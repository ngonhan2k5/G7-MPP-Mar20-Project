package g7.library.ui.validation;

import g7.library.ui.BaseScene;


/**
 * Rules:
 *  1. All fields must be nonempty 
 *  2. ID field must be numeric 
 *  3. Zip must be numeric with exactly 5 digits 
 *  4. State must have exactly two characters in the range A-Z 5. 
 *  5. ID field may not equal zip field.
 *
 */

public class AddressRuleSet implements RuleSet {
	private BaseScene addr;

	@Override
	public void applyRules(BaseScene ob) throws RuleException {
		addr = ob;
		nonemptyRule();
//		idNumericRule();
		zipNumericRule();
		stateRule();
		idNotZipRule();
	}

	private void nonemptyRule() throws RuleException {
		if(addr.getFieldValue("id").trim().isEmpty() ||
				addr.getFieldValue("stree").trim().isEmpty() ||
				addr.getFieldValue("city").trim().isEmpty() ||
				addr.getFieldValue("state").trim().isEmpty() ||
				addr.getFieldValue("zip").trim().isEmpty()) {
			throw new RuleException("All fields must be non-empty!");
		}
	}

	private void idNumericRule() throws RuleException {
		String val = addr.getFieldValue("id").trim();
		try {
			Integer.parseInt(val);
			//val is numeric
		} catch(NumberFormatException e) {
			throw new RuleException("ID must be numeric");
		}		
	}

	private void zipNumericRule() throws RuleException {
		String val = addr.getFieldValue("zip").trim();
		try {
			Integer.parseInt(val);
			//val is numeric
		} catch(NumberFormatException e) {
			throw new RuleException("Zipcode must be numeric");
		}
		if(val.length() != 5) throw new RuleException("Zipcode must have 5 digits");
	}

	private void stateRule() throws RuleException {
		String state = addr.getFieldValue("state").trim();
		if(state.length() != 2) throw new RuleException("State field must have two characters");
		if(!Util.isInRangeAtoZ(state.charAt(0)) 
				|| !Util.isInRangeAtoZ(state.charAt(1))) {
			throw new RuleException("Characters is state field must be in range A-Z");
		}
	}
	
	

	private void idNotZipRule() throws RuleException {
		String zip = addr.getFieldValue("zip").trim();
		String id = addr.getFieldValue("id").trim();
		if(zip.equals(id)) throw new RuleException("ID may not be the same as zipcode");
	}

}
