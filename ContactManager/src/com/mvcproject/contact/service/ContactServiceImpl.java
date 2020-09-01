package com.mvcproject.contact.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mvcproject.contact.dao.ContactDao;
import com.mvcproject.contact.model.ContactEntity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor=@__(@Autowired))
public class ContactServiceImpl implements ContactService {
	
	private final ContactDao contactDao;
	
	 private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public int save(ContactEntity contact) {
		int save = contactDao.save(contact);
		logger.info("No of records saved " + save);
		return save;
	}

	@Override
	public int update(ContactEntity contact) {
		int update = contactDao.update(contact);
		logger.info("No of records updated " + update);
		return update;
	}

	@Override
	public ContactEntity get(int id) {
		ContactEntity contactEntity = contactDao.get(id);
		return contactEntity;
	}

	@Override
	public int delete(int id) {
		int delete = contactDao.delete(id);
		logger.info("No of records deleted " + delete);
		return 0;
	}

	@Override
	public List<ContactEntity> getAll() {
		List<ContactEntity> contactList = contactDao.getAll();
		logger.info("No of records fetched " + contactList.size());
		return contactList;
	}

}
