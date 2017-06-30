package net.myticket.MyTicketServer2.service;

import java.util.List;

import net.myticket.MyTicketServer2.dao.DataDao;
import net.myticket.MyTicketServer2.model.Ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
  
@Service  
public class DataServiceImpl implements DataService {  
  
 @Autowired  
 DataDao dataDao;  
   
 @Override  
 public boolean addEntity(Ticket ticket) throws Exception {  
  return dataDao.addEntity(ticket);  
 }
 
 @Override  
 public boolean updateEntity(Ticket ticket) throws Exception {  
  return dataDao.updateEntity(ticket);  
 }
  
 @Override  
 public Ticket getEntityById(long id) throws Exception {  
  return dataDao.getEntityById(id);  
 }  
  
 @Override  
 public List<Ticket> getEntityList() throws Exception {  
  return dataDao.getEntityList();  
 }  
  
 @Override  
 public boolean deleteEntity(long id) throws Exception {  
  return dataDao.deleteEntity(id);  
 }  
  
} 
