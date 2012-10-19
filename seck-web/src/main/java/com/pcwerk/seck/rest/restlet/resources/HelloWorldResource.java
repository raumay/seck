package com.pcwerk.seck.rest.restlet.resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.restlet.data.MediaType;
import org.restlet.ext.freemarker.TemplateRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import com.pcwerk.seck.rest.entities.GuestEntry;
import com.pcwerk.seck.rest.models.GuestBook;
import com.pcwerk.seck.rest.restlet.SeckWebRestletApp;

/**
 * Sample Resource for handling standard HTTP methods (GET, POST, PUT, DELETE,
 * OPTIONS) You can write public methods that will handle each HTTP method by
 * putting the appropriate annotation:
 * 
 * @Get - GET
 * @Post - POST
 * @Put - PUT
 * @Delete - DELETE
 * 
 * @author mlbuising
 * 
 */
public class HelloWorldResource extends ServerResource {

	@Get
	public Representation getEntries(Representation r) {
		// Return data for HTML Freemarker representation
		Map<String, Object> map = new HashMap<String, Object>();

		// Get the name parameter from the URI pattern /helloworld/{name}
		String name = (String) this.getRequest().getAttributes().get("name");
		name = (name == null) ? "" : name;

		GuestBook gb = getApplication().getGuestBook();

		map.put("guestBookEntries", gb.getGuestEntries(name));
		map.put("name", "Guest6");

		return toRepresentation(map, "guestbook_main.html", MediaType.TEXT_HTML);
	}

	public SeckWebRestletApp getApplication() {
		return (SeckWebRestletApp) super.getApplication();
	}

	private Representation toRepresentation(Map<String, Object> map,
			String templateName, MediaType mediaType) {
		return new TemplateRepresentation(templateName, getApplication()
				.getConfiguration(), map, mediaType);
	}
}