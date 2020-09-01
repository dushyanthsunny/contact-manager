package com.mvcproject.contact.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactEntity {
	
	private int id;
	private String name;
	private String email;
	private String address;
	private String phone;
	
	public ContactEntity formEn(ContactEntity contactEntity) {
		contactEntity.setAddress(contactEntity.getAddress());
		contactEntity.setEmail(contactEntity.getEmail());
		contactEntity.setId(contactEntity.getId());
		contactEntity.setName(contactEntity.getName());
		contactEntity.setPhone(contactEntity.getPhone());
		return contactEntity;
	}
	

}
