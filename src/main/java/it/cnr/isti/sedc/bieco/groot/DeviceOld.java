package it.cnr.isti.sedc.bieco.groot;

import org.json.simple.JSONObject;

public class DeviceOld {
	private String deviceName, deviceId;
	private JSONObject deviceJsonObject;

	public DeviceOld(JSONObject device) {
		// TODO Auto-generated constructor stub
		deviceJsonObject = device;
		
		this.deviceName = new String();
		this.deviceId = new String();
		
	}

	public JSONObject getDeviceJsonObject() {
		return deviceJsonObject;
	}

	public void setDeviceJsonObject(JSONObject deviceJsonObject) {
		this.deviceJsonObject = deviceJsonObject;
	}

	public void parse() {
		// TODO Auto-generated method stub
		
		
		this.deviceName = (String) this.deviceJsonObject.get(GrootEntitiesNames.DEVICE_NAME); 
		this.deviceId = (String) this.deviceJsonObject.get(GrootEntitiesNames.DEVICE_ID);
				
	}
	
	
	
	public JSONObject toJson() {
		JSONObject result = new JSONObject();
		
		result.put(GrootEntitiesNames.DEVICE_NAME, this.deviceName);
		result.put(GrootEntitiesNames.DEVICE_ID, this.deviceId);
		return result;
	}
	
	
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

}
