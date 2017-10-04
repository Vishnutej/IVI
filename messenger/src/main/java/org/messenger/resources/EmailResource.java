package org.messenger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.messenger.data.EmailMessage;
import org.messenger.services.MailService;

@Path("/email")
public class EmailResource {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getEmail()
	{
		return "inside GEt!!";
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void sendEmail(EmailMessage message)
	{
		if(message!=null)
		{
			MailService service = new MailService();
			service.sendEmail(message);
		}
	}
}
