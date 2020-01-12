package com.hackerrank.github.service;

import com.hackerrank.github.exception.RecordNotFoundException;
import com.hackerrank.github.model.Actor;
import com.hackerrank.github.repository.ActorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActorService {

    Logger logger = LoggerFactory.getLogger(this.getClass());
     
    @Autowired
    ActorRepository repository;

     
    public List<Actor> getAllActors()
    {

        List<Actor> actorList = repository.findAllOrderByEventCountTheTime();
         
        if(actorList.size() > 0) {
            return actorList;
        } else {
            return new ArrayList<>();
        }
    }

    public List<Actor> getActorsBySteak() throws RecordNotFoundException
    {
        List<Actor> actorList = repository.findAllOrderByMaxSteakOrderByTheTime();

        if(actorList.size() > 0) {
            return actorList;
        } else {
            return new ArrayList<>();
        }

    }



    @Transactional
    public Actor createOrUpdateActor(Actor entity) throws RecordNotFoundException
    {

        Actor actor=null;

        if(entity.getId()!=null)
             actor = repository.findOne(entity.getId());
         
        if(actor!=null)
        {
            logger.info("updating entity");

            actor.setAvatar_url(entity.getAvatar_url());
            actor.setLogin(entity.getLogin());

            return repository.save(actor);
             

        } else {
            logger.info("saving entity");

            entity = repository.save(entity);
             
            return entity;
        }
    }


    public void deleteAll()
    {
        repository.deleteAll();

    }
}