package net.myticket.MyTicketServer2.client;

import java.net.URI;
import java.util.Calendar;

import net.myticket.MyTicketServer2.model.Ticket;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * http://www.concretepage.com/spring-boot/spring-boot-rest-jpa-hibernate-mysql-example
 * @author Emiliano
 *
 */
public class RestClientUtil {
	
    public void getTicketById(int id) {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/myticket/{id}";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<Ticket> responseEntity = restTemplate.exchange(url, HttpMethod.GET, 
        		requestEntity, Ticket.class, id);
        Ticket ticket = responseEntity.getBody();
        System.out.println(
        		"Id: "+ticket.getId()
        		+", Band Name: "+ticket.getBandName()
                +", City: "+ticket.getCity());      
    }
    
    public void getAllTickets() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/myticket/list";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<Ticket[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, 
        		requestEntity, Ticket[].class);
        Ticket[] tickets = responseEntity.getBody();
        for(Ticket ticket : tickets) {
        	System.out.println(
        			"Id: "+ticket.getId()
        			+", Band Name: "+ticket.getBandName()
                    +", City: "+ticket.getCity()); 
        }
    }
    
    public void addTicket() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/myticket/create";
		Ticket ticket = new Ticket();
		ticket.setBandName("Demo");
		ticket.setCity("Demo city");
		ticket.setVenue("Demo venue");
		ticket.setGigDate(Calendar.getInstance().getTime());
		ticket.setTicketPath("d:\\fnm_ticket.jpg");
        HttpEntity<Ticket> requestEntity = new HttpEntity<Ticket>(ticket, headers);
        URI uri = restTemplate.postForLocation(url, requestEntity);
        System.out.println(uri.getPath());    	
    }
    
    public void updateTicket() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/myticket/update";
        Ticket ticket = new Ticket();
        ticket.setId(2);
		ticket.setBandName("Demo2");
		ticket.setCity("Demo city2");
		ticket.setVenue("Demo venue2");
        HttpEntity<Ticket> requestEntity = new HttpEntity<Ticket>(ticket, headers);
        restTemplate.put(url, requestEntity);
    }
    
    public void deleteTicket() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/myticket/delete/{id}";
        HttpEntity<Ticket> requestEntity = new HttpEntity<Ticket>(headers);
        restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class, 7);        
    }
    
    public static void main(String args[]) {
    	RestClientUtil util = new RestClientUtil();
        util.getTicketById(2);
    	//util.getAllTickets();
    	//util.addTicket();
    	//util.updateTicket();
    	//util.deleteTicket();
    }    
} 