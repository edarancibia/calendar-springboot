package com.calendar.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.calendar.entities.Events2;
import com.calendar.repository.ClinicaJpaRepository;
import com.calendar.repository.EventJpaRepository;
import com.calendar.service.EventService;
import com.calendar.service.EventServiceImpl;

@RestController
@CrossOrigin(origins="*")
public class EventController {

	private static final Log LOG = LogFactory.getLog(EventController.class);
	
	@Autowired
	private EventJpaRepository eventRepository;
	
	@Autowired
	@Qualifier("eventServiceImpl")
	private EventServiceImpl eventService;
	
	@GetMapping("/")
	public String index(){
		return "inicio";
	}
	
	@RequestMapping(value="/calendar", method=RequestMethod.GET) 
	public ModelAndView jsoncalendar() {
		return new ModelAndView("calendar");
	}
	
	@RequestMapping(value="/allevents", method=RequestMethod.GET)
	public @ResponseBody List<Map<String, Object>> events(){
		List<Map<String, Object>> eventos = eventService.getAllEvents();
		LOG.info("datos:"+ eventos);
		return eventos;
	}
	
	/*@RequestMapping(value="/addevent", method=RequestMethod.POST,headers = "Accept=application/json")
	public ResponseEntity<Events2> addEvent(@RequestBody Events2 event){
		eventRepository.save(event);
		LOG.info("datos:"+ event );
		return new ResponseEntity<Events2>(HttpStatus.OK);
	}*/

	
	@RequestMapping(value="addevent", method=RequestMethod.POST)
	public Events2 addEvent(@RequestBody Events2 event){
		LOG.info("datos:"+ event );
		Events2 created = eventRepository.save(event);
		return created;
	}
	
	
}
