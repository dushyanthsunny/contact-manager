package com.mvcproject.contact.dao;

import java.util.List;

import com.mvcproject.contact.model.ContactEntity;

public interface ContactDao {
	
	int save(ContactEntity contact);
	
	int update(ContactEntity contact);
	
	ContactEntity get(int id);
	
	int delete(int id);
	
	List<ContactEntity> getAll();
	
}
