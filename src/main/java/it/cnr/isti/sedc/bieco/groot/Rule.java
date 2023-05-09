package it.cnr.isti.sedc.bieco.groot;

import javax.xml.bind.annotation.XmlRootElement;

import org.json.simple.JSONObject;

@XmlRootElement(name = "rule")
public class Rule {
	private String ruleName, ruleId;
	private JSONObject ruleJsonObject;
	private String ruleID;
	private String ruleTYPE;
	private String abstractRULE;
	private String wellDefinedRULE;
	private String descriptionRule;
	private String skillId;
	
	/**
	 * 
	 */
	public Rule() {
		// TODO Auto-generated constructor stub
	}
	
	public Rule(JSONObject rule) {
		// TODO Auto-generated constructor stub
		ruleJsonObject = rule;
		
		this.ruleName = new String();
		this.ruleId = new String();
		
	}

	public Rule(String rName, String rID) {
		// TODO Auto-generated constructor stub
		this.ruleName = rName;
		this.ruleId = rID;

	}


	public Rule(String ruleID2, String ruleNamePar, String ruleTYPE, String abstractRULE, String wellDefinedRULE, String descriptionRule, String skillId) {
		// TODO Auto-generated constructor stub
		
		this.ruleID = ruleID2; 
		this.ruleTYPE = ruleTYPE;
		this.abstractRULE = abstractRULE; 
		this.wellDefinedRULE = wellDefinedRULE; 
		this.descriptionRule = descriptionRule; 
		this.skillId = skillId;
		this.ruleName = ruleNamePar;
		
		System.out.println("Rule.Rule()");
		
	}

	public JSONObject toJson() {
		
		JSONObject result = new JSONObject();
		
		if (!(this.ruleName.equals("")&& this.ruleId.equals(""))) {
			result.put(OntologyEntitiesNames.RULE_NAME, this.ruleName);
			result.put(OntologyEntitiesNames.Rule_ID, this.ruleId);
		} else {
			result = this.ruleJsonObject;
		}
		
		return result;
	}
	
	
	public static Rule fromJSON(JSONObject object) {
		
		String ruleAsJSONResult = object.toJSONString();
		System.out.println(ruleAsJSONResult);
		
//		return new Rule((String) object.get(OntologyEntitiesNames.RULE_NAME), (String) object.get(OntologyEntitiesNames.Rule_ID));			
		return new Rule(object);
	}

	
	
	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public JSONObject getRuleJsonObject() {
		return ruleJsonObject;
	}

	public void setRuleJsonObject(JSONObject ruleJsonObject) {
		this.ruleJsonObject = ruleJsonObject;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return toJsonAbstractRule().toJSONString();
	}

	private JSONObject toJsonAbstractRule() {
		// TODO Auto-generated method stub
		JSONObject result = new JSONObject();
		result.put(OntologyEntitiesNames.Rule_ID, this.ruleID);
		result.put(OntologyEntitiesNames.RULE_NAME, this.ruleName);
		result.put(OntologyEntitiesNames.DESCRIPTION, this.descriptionRule);
		result.put(OntologyEntitiesNames.RULE_TYPE, this.ruleTYPE);
		result.put(OntologyEntitiesNames.ABSTRACT_RULE, this.abstractRULE);
		result.put(OntologyEntitiesNames.WELL_DEFINED_RULE, this.wellDefinedRULE);
		result.put(OntologyEntitiesNames.SKILL_ID, this.skillId);
		
		System.out.println("Rule.toJsonAbstractRule()");
		return result;
	}

	public static Rule fromJSONAbstractRule(JSONObject ruleObject, String skillId) {
		// TODO Auto-generated method stub
		
		
		/**
		 * {
        "ruleType": "Standard",
        "description": "This is a Standard Rule that allows monitoring the Maximum Number of of established simultaneous connections a Component should have.",
        "abstractrule": "Maximum number of established simultaneous connections",
        "welldefinedrule":"#maximum_number of established simultaneous connections",
        "ruleId": "1",
        "skillId": "1"
      }

		 */
		
		String ruleID = (String) ruleObject.get(OntologyEntitiesNames.Rule_ID);
		String ruleName = (String) ruleObject.get(OntologyEntitiesNames.RULE_NAME);
		String descriptionRule = (String) ruleObject.get(OntologyEntitiesNames.DESCRIPTION);
		String ruleTYPE = (String) ruleObject.get(OntologyEntitiesNames.RULE_TYPE);
		String abstractRULE = (String) ruleObject.get(OntologyEntitiesNames.ABSTRACT_RULE);
		String wellDefinedRULE = (String) ruleObject.get(OntologyEntitiesNames.WELL_DEFINED_RULE);
		
		return new Rule(ruleID, ruleName, ruleTYPE, abstractRULE, wellDefinedRULE, descriptionRule, skillId);
	}

	public String getRuleID() {
		return ruleID;
	}

	public void setRuleID(String ruleID) {
		this.ruleID = ruleID;
	}

	public String getRuleTYPE() {
		return ruleTYPE;
	}

	public void setRuleTYPE(String ruleTYPE) {
		this.ruleTYPE = ruleTYPE;
	}

	public String getAbstractRULE() {
		return abstractRULE;
	}

	public void setAbstractRULE(String abstractRULE) {
		this.abstractRULE = abstractRULE;
	}

	public String getWellDefinedRULE() {
		return wellDefinedRULE;
	}

	public void setWellDefinedRULE(String wellDefinedRULE) {
		this.wellDefinedRULE = wellDefinedRULE;
	}

	public String getDescriptionRule() {
		return descriptionRule;
	}

	public void setDescriptionRule(String descriptionRule) {
		this.descriptionRule = descriptionRule;
	}

	public String getSkillId() {
		return skillId;
	}

	public void setSkillId(String skillId) {
		this.skillId = skillId;
	}
	
}
