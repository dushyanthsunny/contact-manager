package com.mvcproject.contact.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.mvcproject.contact.model.ContactEntity;

class ContactDaoTest {


	private DriverManagerDataSource dataSource;

	private ContactDao dao; 
	
	@BeforeEach
	void setProperty(){
		dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/contactManagerDb");
		dataSource.setUsername("root");
		dataSource.setPassword("7788");
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		dao = new ContactDaoImpl(jdbcTemplate);
	}


	@Test
	void testSave() {

		ContactEntity entity = ContactEntity.builder()
						.id(2)
						.name("dush")
						.email("sunny@gmail")
						.phone("9698998208")
						.address("chennai")
						.build();

		int result = dao.save(entity);

		assertTrue(result>0);
	}

	@Test
	void testUpdate() {
		ContactEntity entity = ContactEntity.builder().id(2).name("dush").email("sunny@gmail")
				.phone("9698998208").address("chennai").build();
//		entity.setId(2);
//		entity.setName("dush");
//		entity.setEmail("sunny@gmail");
//		entity.setPhone("9698998208");
//		entity.setAddress("chennai");

		int result = dao.update(entity);

		assertTrue(result>0);
	}

	@Test
	void testGet() {
		ContactEntity contactEntity = dao.get(1);
		assertNotNull(contactEntity);
	}

	@Test
	void testDelete() {
		int count = dao.delete(3);
		assertTrue(count>0);
	}

	@Test
	void testGetAll() {

	List<ContactEntity> contactList = dao.getAll();
	contactList.stream().forEach(mapper -> System.out.println(mapper.toString()));
	
	assertNotNull("null check", contactList);
	}

}
