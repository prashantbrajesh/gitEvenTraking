package com.hackerrank.github.service;

import com.hackerrank.github.exception.RecordNotFoundException;
import com.hackerrank.github.model.Actor;
import com.hackerrank.github.model.Event;
import com.hackerrank.github.repository.ActorRepository;
import com.hackerrank.github.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    Logger logger = LoggerFactory.getLogger(this.getClass());
     
    @Autowired
    EventRepository repository;

     
    public List<Event> getAllEvents()
    {
        List<Event> eventList = repository.findAll();
         
        if(eventList.size() > 0) {
            return eventList;
        } else {
            return new ArrayList<Event>();
        }
    }

    public List<Event> getAllEventsByActorId(Long id) throws RecordNotFoundException
    {
        List<Event> eventList = repository.findAll();
        List<Event> actorEvent=new ArrayList<>();
        if(eventList!=null)
          actorEvent = eventList.stream().filter(i->i.getActor().getId().equals(id)).collect(Collectors.toList());
        if(actorEvent.size() > 0) {
            return actorEvent;
        }  else {
            throw new RecordNotFoundException("No Event record exist for given id");
        }
    }

     
    public Event getEventById(Long id) throws RecordNotFoundException
    {

        Event event = repository.findOne(id);
         
        if(event!=null) {
            return event;
        } else {
            throw new RecordNotFoundException("No Event record exist for given id");
        }
    }

    @Transactional
    public Event createOrUpdateEvent(Event entity) throws RecordNotFoundException
    {

        Event event=null;

        if(entity.getId()!=null)
             event = repository.findOne(entity.getId());
         
        if(event!=null)
        {
            entity.getRepo().addEvent(event);
            entity.getActor().addEvent(event);
            logger.info("updating entity");
            event.setType(entity.getType());
            event.setCreated_at(entity.getCreated_at());

            return repository.save(event);
             

        } else {
            logger.info("saving entity");
            entity.getRepo().addEvent(entity);
            entity.getActor().addEvent(entity);
            entity = repository.save(entity);
             
            return entity;
        }
    }
     
    public void deleteEventById(Long id) throws RecordNotFoundException
    {
        Event event = repository.findOne(id);
         
        if(event!=null)
        {
            logger.info("deleting entity {}",id);
            repository.delete(id);
        } else {
            throw new RecordNotFoundException("No Event record exist for given id");
        }
    }

    public void deleteAll()
    {
        repository.deleteAll();

    }
}