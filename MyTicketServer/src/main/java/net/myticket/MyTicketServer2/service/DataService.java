package net.myticket.MyTicketServer2.service;

import java.util.List;

import net.myticket.MyTicketServer2.model.Ticket;

public interface DataService {  
 public boolean addEntity(Ticket ticket) throws Exception; 
 public boolean updateEntity(Ticket ticket) throws Exception; 
 public Ticket getEntityById(long id) throws Exception;  
 public List<Ticket> getEntityList() throws Exception;  
 public boolean deleteEntity(long id) throws Exception;  
} 
