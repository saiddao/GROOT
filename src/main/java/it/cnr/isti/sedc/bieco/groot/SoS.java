package it.cnr.isti.sedc.bieco.groot;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


@XmlRootElement(name = "sos")
public class SoS {
	private String soSname, sosId, description;
	
	private List<Device> devices;
	private JSONObject sosJsonOnject;
	
	public SoS() {
	}

	public SoS(String fname, String lname) {
		this.soSname = fname;
		this.sosId = lname;
		
		this.devices = new ArrayList<Device>();
		
	}
	
	public SoS(String fname, String lname, String sDescription) {
		this.soSname = fname;
		this.sosId = lname;
		this.description = sDescription;
		
		this.devices = new ArrayList<Device>();
	}


	public SoS(JSONObject jo) {
		// TODO Auto-generated constructor stub
		
		this.soSname = new String();
		this.sosId = new String();
		sosJsonOnject = jo;

		this.devices = new ArrayList<Device>();
	}

	public String getSoSname() {
		return soSname;
	}

	public void setSoSname(String soSname) {
		this.soSname = soSname;
	}

	public String getSosId() {
		return sosId;
	}

	public void setSosId(String sosId) {
		this.sosId = sosId;
	}

	public static SoS fromJSON(JSONObject object) {
		return new SoS((String) object.get(GrootEntitiesNames.SOS_NAME), (String) object.get(GrootEntitiesNames.SOS_ID), (String) object.get(GrootEntitiesNames.DESCRIPTION));
				
				/**
				 * (String) ((JSONObject) object.get("address")).get("street"),
				 * 				(long) ((JSONObject) object.get("address")).get("no"), (long) object.get("id"),
				(double) object.get("grade"));
				 */
	}

	public JSONObject toJson() {
		JSONObject result = new JSONObject();
		
		result.put(GrootEntitiesNames.SOS_NAME, this.soSname);
		result.put(GrootEntitiesNames.SOS_ID, this.sosId);
		result.put(GrootEntitiesNames.DESCRIPTION, this.description);
/**
 * 

		result.put("id", this.studentId);
		result.put("grade", this.grade);
		
		JSONObject address = new JSONObject();
		address.put("no", this.addressNo);
		address.put("street", this.streetAddress);
		
		result.put("address", address);
 */		
		return result;
	}



	public JSONObject getSosJsonOnject() {
		return sosJsonOnject;
	}

	public void setSosJsonOnject(JSONObject sosJsonOnject) {
		this.sosJsonOnject = sosJsonOnject;
	}

	public void parse() {
		// TODO Auto-generated method stub
		
		System.out.println("####### START Parse() ############");
		System.out.println(this.sosJsonOnject.keySet());
		Set keys = this.sosJsonOnject.keySet();
		
		for (Object key : keys) {
			System.out.println(key);
			String keyAsString = (String) key;
			Object value = this.sosJsonOnject.get(key);
			System.out.println(value.getClass().getCanonicalName());
			System.out.println("Value = "+value);
			switch (keyAsString) {
			
			case GrootEntitiesNames.SOS_NAME:
				this.soSname = (String) value;
				break;
				
			case GrootEntitiesNames.SOS_ID:
				this.sosId = (String) value;
				break;
			
			case GrootEntitiesNames.DEVICES:
				parseDevices ((JSONArray) value);
				break;

			default:
				break;
			}
		}
		System.out.println("####### END Parse() ############");
	}

	private void parseDevices(JSONArray devicesJson) {
		// TODO Auto-generated method stub
		System.out.println(devicesJson.toJSONString());
		for (Object jo : devicesJson) {
			
			
			Device currentDevice = new Device((JSONObject) jo);
			currentDevice.parse();
			this.devices.add(currentDevice);
			System.out.println(this.devices.get(this.devices.size() - 1).toJson());
			
		}
	}
	
	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(ArrayList<Device> devices) {
		this.devices = devices;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return toJson().toJSONString();
	}
	
	public String getHTML() {
		
		return "<option value=\""+this.soSname+"\">"+this.soSname+"</option>";
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}