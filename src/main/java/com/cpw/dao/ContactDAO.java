package com.cpw.dao;

import java.util.List;

import javax.sql.DataSource;

import com.cpw.jdbc.model.Contact;

public interface ContactDAO {

	public void setDataSource(DataSource ds);

	public int upsertContact(Contact contact,String userId);

	public List<Contact> contactList(long contactId);
	
	public int removeContact(long contactId,String userId);
}
