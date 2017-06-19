package net.myticket.dao;

import java.util.List;

import net.myticket.model.Ticket;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

public class DataDaoImpl implements DataDao {  
	  
	 @Autowired  
	 SessionFactory sessionFactory;  
	  
	 Session session = null;  
	 Transaction tx = null;  
	  
	 @Override  
	 public boolean addEntity(Ticket ticket) throws Exception {  
	  
	  session = sessionFactory.openSession();  
	  tx = session.beginTransaction();  
	  session.save(ticket);  
	  tx.commit();  
	  session.close();  
	  
	  return false;  
	 }
	 
	 @Override  
	 public boolean updateEntity(Ticket ticket) throws Exception {  
	  
	  session = sessionFactory.openSession();  
	  tx = session.beginTransaction();  
	  session.saveOrUpdate(ticket);  
	  tx.commit();  
	  session.close();  
	  
	  return false;  
	 } 
	  
	 @Override  
	 public Ticket getEntityById(long id) throws Exception {  
	  session = sessionFactory.openSession();  
	  Ticket ticket = (Ticket) session.load(Ticket.class,  
	    new Long(id));  
	  tx = session.getTransaction();  
	  session.beginTransaction();  
	  tx.commit();  
	  return ticket;  
	 }  
	  
	 @SuppressWarnings("unchecked")  
	 @Override  
	 public List<Ticket> getEntityList() throws Exception {  
	  session = sessionFactory.openSession();  
	  tx = session.beginTransaction();  
	  List<Ticket> ticketList = session.createCriteria(Ticket.class)  
	    .list();  
	  tx.commit();  
	  session.close();  
	  return ticketList;  
	 }  
	   
	 @Override  
	 public boolean deleteEntity(long id)  
	   throws Exception {  
	  session = sessionFactory.openSession();  
	  Object o = session.load(Ticket.class, id);  
	  tx = session.getTransaction();  
	  session.beginTransaction();  
	  session.delete(o);  
	  tx.commit();  
	  return false;  
	 }  
	  
	}  
