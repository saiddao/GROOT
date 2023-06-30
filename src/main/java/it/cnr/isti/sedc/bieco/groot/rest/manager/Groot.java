package it.cnr.isti.sedc.bieco.groot.rest.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.cnr.isti.sedc.bieco.groot.Component;
import it.cnr.isti.sedc.bieco.groot.CoreGroot;
import it.cnr.isti.sedc.bieco.groot.Device;
import it.cnr.isti.sedc.bieco.groot.GrootEntitiesNames;
import it.cnr.isti.sedc.bieco.groot.Rule;
import it.cnr.isti.sedc.bieco.groot.Skill;
import it.cnr.isti.sedc.bieco.groot.SoS;
import it.cnr.isti.sedc.bieco.groot.utils.BiecoMessageTypes;

/**
 * Root resource (exposed at "myresource" path)
 */

@Path("groot/biecointerface")

public class Groot {

	private String incomingToken = "MDM2grHjCbdRyGROOT";
	private String outcomingToken = "qJACs1J0apruOOJCgGROOT";

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to the
	 * client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */

	@GET
	@Produces({ MediaType.TEXT_HTML })
	public String getIt() {
		return homePage();

	}

	private String homePage() {

		String result = "<!DOCTYPE html><head><meta charset=\"utf-8\"><title>OntologyManager</title>"
				+ "</head><style>\n" + "body {\n" + "  background-color: #ddebef;\n" + "}\n"
				+ "</style><body><h2>GROOT</h2><h3>Status: " + GrootStatus() + "</h3>"
				+ "<h4>GROOT logs:</h4><textarea id=\"logs\" name=\"debugLog\" rows=\"30\" cols=\"200\">\n"
				+ getLoggerData() + "</textarea></body></html>";

		return result;
	}


	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response biecointerface(String jsonMessage, @Context HttpHeaders headers) {

		JSONParser parser = new JSONParser();
		Response output = null;
		JSONObject bodyMessage;
		String authorization = headers.getRequestHeader("Authorization").get(0);
		if (authorization.compareTo(outcomingToken) == 0) {

			try {
				bodyMessage = (JSONObject) parser.parse(jsonMessage);
				switch ((String) bodyMessage.get("messageType")) {
				case BiecoMessageTypes.HEARTBEAT:
					output = this.heartbeat();
					break;
				case BiecoMessageTypes.START:
					output = this.start();
					break;
				case BiecoMessageTypes.STOP:
					output = this.stop();
					break;
				case BiecoMessageTypes.GETSTATUS:
					output = this.getStatus();
					break;
				case BiecoMessageTypes.CONFIGURE:
					output = this.configure();
					break;
				case BiecoMessageTypes.DATA:
					output = this.data((JSONObject) bodyMessage.get("event"));
					break;
				case BiecoMessageTypes.EVENT:
					output = this.event();
					break;
				case BiecoMessageTypes.DEMO:
					output = this.demo();
					break;
				default:
					output = Response.status(400).entity("invalid messageType").build();
					break;
				}
			} catch (ParseException e) { 
				// TODO Auto-generated catch block 
				e.printStackTrace();
			}

		} else {
			output = Response.status(401).entity("invalid access token").build();
		}

		System.out.println(output);
		System.out.println(output.getEntity());

		System.out.println(System.getProperty("user.dir"));

		return output;
	}

	private Response start() {
		return Response.status(200).entity(GrootStart()).header("Authorization", incomingToken).build();
	}

	private Response stop() {
		return Response.status(200).entity(GrootStop()).header("Authorization", incomingToken).build();
	}

	private Response heartbeat() {
		return Response.status(200).build();
	}

	private Response getStatus() {
		return Response.status(200).entity(GrootStatus()).build();
	}

	private Response demo() {
		return Response.status(200).entity(DemoStatus()).build();
	}

	private Response configure() {
		return Response.status(404).build();
	}

	

	private Response event() {
		return Response.status(404).build();
	}

	private String GrootStart() {
		try {
			CoreGroot.getInstance();
			return "GROOT started";
		} catch (InterruptedException e) {
			return "failed to start GROOT";
		}
	}

	private String getLoggerData() {
		return CoreGroot.getLoggerData();
	}

	private String GrootStop() {
		if (CoreGroot.isRunning()) {
			CoreGroot.killInstance();
			return "GROOT stopped";
		} else
			return "GROOT was not running";
	}

	private String GrootStatus() {
		if (CoreGroot.isRunning()) {
			return "Running";
		}
		return "Online";
	}

	private String DemoStatus() {
		try {
			CoreGroot.getInstance();
			CoreGroot.DemoStart();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		return "Starting GROOT Demo";
	}

	

	/**
	 * GrootRequest is a JSON Object containing 
	 * { "operationName": "_"}
	 * 
	 * We have defined Four operation: 1) createACRequests 2) evaluateACRequest 3) getcomponents 4)
	 * getskills
	 * 
	 * 
	 * @param ontologyRequest
	 * @return
	 */
	private Response data(JSONObject event) {

		Response output;

		System.out.println(event.toJSONString());

		JSONObject ontologyRequest = (JSONObject) event.get(GrootEntitiesNames.GROOT_REQUEST);

		System.out.println(ontologyRequest.toJSONString());

		String operationName = (String) ontologyRequest.get(GrootEntitiesNames.OPERATION_NAME);
		System.out.println(operationName);

		String result;

		switch (operationName) {
		
		
		case "createACRequests":
			
			String acpolicyContent = (String) ontologyRequest.get(GrootEntitiesNames.ACCESS_CONTROL_POLICY_CONTENT);
			
			System.out.println(acpolicyContent);
			
			result = createACRequests().toString();
			System.out.println(result);
			output = Response.status(200).entity(result).build();
			
			break;
		
		case "evaluateACRequest":
			
			result = evaluateACRequest().toString();
			System.out.println(result);
			output = Response.status(200).entity(result).build();
			
			break;
		
		

		case "uploadontology":

			try {

				String ontologyContent = (String) ontologyRequest.get(GrootEntitiesNames.ONTOLOGY_CONTENT);
				System.out.println("Content of the Uploaded Ontology File -> " + ontologyContent);

				System.out.println(
						"JSONObject jsonObjectSOSs = (JSONObject) ontologyRequest.get(OntologyEntitiesNames.ONTOLOGY_CONTENT);");

				JSONParser parser = new JSONParser();
				JSONObject content = (JSONObject) parser.parse(ontologyContent);

				JSONArray sossArray = (JSONArray) content.get(GrootEntitiesNames.ONTOLOGY_SOSS);

				if (sossArray == null) {
					System.err.println("PLEASE see this:: SoSs Array is null" + sossArray);
					;
				}

				System.out.println(content.get(GrootEntitiesNames.ONTOLOGY_SOSS).toString());

				System.out.println(sossArray.toJSONString());

				dumpOntologyDatabase(sossArray);

				output = Response.status(404).entity("Uploading Ontology: " + ontologyRequest).build();

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				output = Response.status(404).entity("Uploading Ontology: " + ontologyRequest).build();

			}

			break;

		default:
			output = Response.status(404).entity("Invalid data request: " + ontologyRequest).build();
		}
		return output;
	}

	private String evaluateACRequest() {
		// TODO Auto-generated method stub
		return "\"Name\": \"evaluateACRequest\"";
	}

	private String createACRequests() {
		// TODO Auto-generated method stub
				
		return "\"Name\": \"createACRequests\"";
	}
}

