package com.mvcproject.contact.service;

import java.util.List;

import com.mvcproject.contact.model.ContactEntity;

public interface ContactService {

	int save(ContactEntity contact);

	int update(ContactEntity contact);

	ContactEntity get(int id);

	int delete(int id);

	List<ContactEntity> getAll();

}
