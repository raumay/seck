package com.pcwerk.seck.rest.restlet.resources;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.ext.freemarker.TemplateRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
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

	@Post
	public void postEntries(Representation r) throws IOException {

		GuestBook gb = getApplication().getGuestBook();

		Form form = new Form(r);

		String method = form.getFirstValue("method", "");

		if (method.equals("delete")) {
			int id = -1;

			try {
				id = Integer.parseInt(form.getFirstValue("id"));
			} catch (NumberFormatException e) {
			}
			String name = form.getFirstValue("deleteName", "");

			System.out.println("delete: " + name);

			GuestEntry entry = new GuestEntry(id, name);
			gb.deleteGuestEntry(entry);
		} else if (method.equals("add")) {
			String name = form.getFirstValue("addName", "Anonymous");
			String message = form.getFirstValue("message", "");

			GuestEntry entry = new GuestEntry(name, new Date(), message);
			gb.addGuestEntry(entry);
		}

		redirectPermanent("/rest/helloworld");
	}

	public SeckWebRestletApp getApplication() {
		return (SeckWebRestletApp) super.getApplication();
	}

	private Representation toRepresentation(Map<String, Object> map,
			String templateName, MediaType mediaType) {
		return new TemplateRepresentation(templateName, getApplication()
				.getConfiguration(), map, mediaType);
	}

	/*
	 * TODO: Implementation for PUT and DELETE are not available at this time
	 * since HTML forms do not support these methods, only GET and POST. Use AJAX
	 * to do PUT and DELETE requests.
	 */
}