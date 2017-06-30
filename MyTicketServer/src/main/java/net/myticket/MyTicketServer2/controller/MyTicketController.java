package net.myticket.MyTicketServer2.controller;

import java.util.List;

import net.myticket.MyTicketServer2.model.Status;
import net.myticket.MyTicketServer2.model.Ticket;
import net.myticket.MyTicketServer2.service.DataService;
import net.myticket.MyTicketServer2.service.FileService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller  
@RequestMapping("/myticket")
@CrossOrigin(origins = "http://localhost:3000")
public class MyTicketController {
	
	 @Autowired  
	 DataService dataService;
	 
	 @Autowired  
	 FileService fileService;
	  
	 static final Logger logger = Logger.getLogger(MyTicketController.class);  
	  
	 /* Submit form in Spring Restful Services 
	  * Example { "bandName": "fnm", "venue": "olympia theatre", "gigDate": "2009-08-27", 
	  * "city": "Dublin", "ticketPath": "d:\\fnm_ticket.jpg" }
	  * */  
	 @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)  
	 public @ResponseBody  
	 Status addTicket(@RequestBody Ticket ticket) {  
	  try {  
		  String ticketPath = ticket.getTicketPath();
		  String fileName = ticketPath.substring(ticketPath.lastIndexOf("\\")+1);
		  ticket.setTicketFileName(fileName);
		  
		  if(fileService.saveFile(fileName, ticketPath)) {
			  dataService.addEntity(ticket);  	  
		  }
		  else {
			  return new Status(0, "Failed to save file");  
		  }
	   
	   return new Status(1, "Ticket added Successfully !");  
	  } catch (Exception e) {  
	   // e.printStackTrace();  
	   return new Status(0, e.toString());  
	  }  
	  
	 }  
	  
	 /* Get a single object in Json form in Spring Rest Services */  
	 @RequestMapping(value = "/{id}", method = RequestMethod.GET)  
	 public @ResponseBody  
	 Ticket getTicket(@PathVariable("id") long id) {  
	  Ticket ticket = null;  
	  try {  
	   ticket = dataService.getEntityById(id);  
	  
	  } catch (Exception e) {  
	   e.printStackTrace();  
	  }  
	  return ticket;  
	 }
	 
	 /* Get a single object in Json form in Spring Rest Services */  
	 @RequestMapping(value = "/update", method = RequestMethod.PUT)  
	 public @ResponseBody  
	 Status updateTicket(@RequestBody Ticket ticket) {  
	  try {  
	   dataService.updateEntity(ticket);  
	   return new Status(1, "Ticket updated Successfully !");
	  } catch (Exception e) {  
		  return new Status(0, e.toString());
	  }  
	 }
	  
	 /* Getting List of objects in Json format in Spring Restful Services */  
	 @RequestMapping(value = "/list", method = RequestMethod.GET)  
	 public @ResponseBody  
	 List<Ticket> getTicket() {  
	  
	  List<Ticket> ticketList = null;  
	  try {  
	   ticketList = dataService.getEntityList();  
	  
	  } catch (Exception e) {  
	   e.printStackTrace();  
	  }  
	  
	  return ticketList;  
	 }  
	  
	 /* Delete an object from DB in Spring Restful Services */  
	 @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)  
	 public @ResponseBody  
	 Status deleteTicket(@PathVariable("id") long id) {  
	  
	  try {  
	   dataService.deleteEntity(id);  
	   return new Status(1, "Ticket deleted Successfully !");  
	  } catch (Exception e) {  
	   return new Status(0, e.toString());  
	  }  
	  
	 }  
	}  
	  
