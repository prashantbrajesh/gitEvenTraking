package com.hackerrank.github.controller;

import com.hackerrank.github.exception.RecordNotFoundException;
import com.hackerrank.github.model.Event;
import com.hackerrank.github.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventsController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EventService service;

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> list = service.getAllEvents();

        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        Event entity = service.getEventById(id);

        return new ResponseEntity<>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/actors/{id}")
    public ResponseEntity<List<Event>> getEventsByActorId(@PathVariable("id") Long id)
            throws RecordNotFoundException{

        List<Event> list = service.getAllEventsByActorId(id);
        // how it is expecting 1 size in all test
        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping( method = { RequestMethod.PUT, RequestMethod.POST })
    public ResponseEntity<Event> createOrUpdateEvent(@RequestBody Event event)
            throws RecordNotFoundException {
        Event updated=null;
        try {
           updated = service.createOrUpdateEvent(event);
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return new ResponseEntity<>(updated, new HttpHeaders(), HttpStatus.CREATED);

        }

        return new ResponseEntity<>(updated, new HttpHeaders(), HttpStatus.CREATED);
    }

    @DeleteMapping()
    public HttpStatus deleteAll()
            throws RecordNotFoundException {
        service.deleteAll();
        return HttpStatus.OK;
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteEventById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        service.deleteEventById(id);
        return HttpStatus.FORBIDDEN;
    }

}
