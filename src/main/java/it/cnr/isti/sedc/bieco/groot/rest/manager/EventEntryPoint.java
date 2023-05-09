package it.cnr.isti.sedc.bieco.groot.rest.manager;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import it.cnr.isti.sedc.bieco.groot.utils.BiecoMessageTypes;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("groot/evententrypoint")

public class EventEntryPoint {

	static String incomingToken = "MDM2grHjCbdRyGROOT";
	private String outgoingToken = "qJACs1J0apruOOJCgGROOT";

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response evententrypoint(String jsonMessage, @Context HttpHeaders headers) {
		String authorization = headers.getRequestHeader("Authorization").get(0);
		if (authorization.compareTo(outgoingToken) == 0) {
			JSONObject bodyMessage = new JSONObject(jsonMessage);

			if (((String) bodyMessage.get("messageType")).compareTo(BiecoMessageTypes.EVENT) == 0) {
				return this.messageOK(bodyMessage);
			} else
				return Response.status(400).entity("invalid messageType").build();
		}
		return Response.status(401).entity("invalid access token").build();
	}

	private Response messageOK(JSONObject bodyMessage) {

		return Response.status(200).build();
	}
}
