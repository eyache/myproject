package net.myticket.MyTicketServer2.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity  
@Table(name = "tickets")  
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  
public class Ticket implements Serializable {  
	  
	 private static final long serialVersionUID = 1L;  
	 
	 @Id  
	 @GeneratedValue  
	 @Column(name = "id")  
	 private long id;  
	  
	 @Column(name = "band_name")  
	 private String bandName;  
	  
	 @Column(name = "venue")  
	 private String venue;  
	  
	 @Column(name = "gig_date")  
	 private Date gigDate;  
	  
	 @Column(name = "city")  
	 private String city;  
	 
	 @Column(name = "ticket_path")  
	 private String ticketFileName;

	 @Transient
	 private String ticketPath;

	 
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBandName() {
		return bandName;
	}

	public void setBandName(String bandName) {
		this.bandName = bandName;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public Date getGigDate() {
		return gigDate;
	}

	public void setGigDate(Date gigDate) {
		this.gigDate = gigDate;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTicketPath() {
		return ticketPath;
	}

	public void setTicketPath(String ticketPath) {
		this.ticketPath = ticketPath;
	}

	public String getTicketFileName() {
		return ticketFileName;
	}

	public void setTicketFileName(String ticketFileName) {
		this.ticketFileName = ticketFileName;
	}
	 
}
