package com.mvcproject.contact.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mvcproject.contact.model.ContactEntity;
import com.mvcproject.contact.service.ContactService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor(onConstructor=@__(@Autowired))
public class ContactController {

	private final ContactService contactService;

	@RequestMapping(value="/")
	public ModelAndView contact(ModelAndView model) {
		List<ContactEntity> contactList = contactService.getAll();
		model.addObject("contactList", contactList);
		model.setViewName("index");
		return model;
	}

	@RequestMapping(value="/newContact", method = RequestMethod.GET)
	public ModelAndView newContact(ModelAndView model) {
		ContactEntity entity = new ContactEntity();
		//attributeName should match with jsp form modelAttribute tag
		model.addObject("contact", entity);
		model.setViewName("contact_form");
		return model;
	}

	@RequestMapping(value="/save", method = RequestMethod.POST)
	//	@ModelAttribute should match with jsp ModelAttribute
	public ModelAndView saveContact(@ModelAttribute  ContactEntity  contact) {
		if(contact.getId() == 0)
			contactService.save(contact);
		else
			contactService.update(contact);
		return new ModelAndView("redirect:/");
	} 

	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public ModelAndView editContact(@RequestParam Integer id) {
//		int id = Integer.parseInt(request.getParameter("id"));
		ContactEntity contactEntity = contactService.get(id);
		ModelAndView model = new ModelAndView();
		model.addObject("contact", contactEntity);
		model.setViewName("contact_form");
		return  model;
	} 
	
	@RequestMapping(value="/delete", method = RequestMethod.GET)
	public ModelAndView deleteContact(@RequestParam Integer id) {
		contactService.delete(id);
		return new ModelAndView("redirect:/");
	}

}
