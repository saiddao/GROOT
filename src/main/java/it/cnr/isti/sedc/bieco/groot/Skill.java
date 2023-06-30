package it.cnr.isti.sedc.bieco.groot;

import javax.xml.bind.annotation.XmlRootElement;

import org.json.simple.JSONObject;

@XmlRootElement(name = "skill")
public class Skill {
	private String skillName, skillId, description;
	private JSONObject skillJsonObject;
	
	public Skill() {
		// TODO Auto-generated constructor stub
	}
	
	public Skill(JSONObject skill) {
		// TODO Auto-generated constructor stub
		skillJsonObject = skill;
		
		this.skillName = new String();
		this.skillId = new String();
		this.description = new String();
	}

	public Skill(String sName, String sID) {
		// TODO Auto-generated constructor stub
		this.skillName = sName;
		this.skillId = sID;

	}
	
	public Skill(String sName, String sID, String sDescription) {
		// TODO Auto-generated constructor stub
		this.skillName = sName;
		this.skillId = sID;
		this.description = sDescription;

	}


	public JSONObject toJson() {
		JSONObject result = new JSONObject();
		
		result.put(GrootEntitiesNames.SKILL_NAME, this.skillName);
		result.put(GrootEntitiesNames.SKILL_ID, this.skillId);
		result.put(GrootEntitiesNames.DESCRIPTION, this.description);
		return result;
	}
	
	
	public static Skill fromJSON(JSONObject object) {
		return new Skill((String) object.get(GrootEntitiesNames.SKILL_NAME), (String) object.get(GrootEntitiesNames.SKILL_ID), (String) object.get(GrootEntitiesNames.DESCRIPTION));			
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public String getSkillId() {
		return skillId;
	}

	public void setSkillId(String skillId) {
		this.skillId = skillId;
	}

	public JSONObject getSkillJsonObject() {
		return skillJsonObject;
	}

	public void setSkillJsonObject(JSONObject skillJsonObject) {
		this.skillJsonObject = skillJsonObject;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return toJson().toJSONString();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
