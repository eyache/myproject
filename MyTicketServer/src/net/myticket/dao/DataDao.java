package net.myticket.dao;

import java.util.List;

import net.myticket.model.Ticket;

public interface DataDao {  
  
 public boolean addEntity(Ticket ticket) throws Exception; 
 public boolean updateEntity(Ticket ticket) throws Exception; 
 public Ticket getEntityById(long id) throws Exception;  
 public List<Ticket> getEntityList() throws Exception;  
 public boolean deleteEntity(long id) throws Exception;  
}  
  
