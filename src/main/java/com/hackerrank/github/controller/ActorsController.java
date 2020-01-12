package com.hackerrank.github.controller;

import com.hackerrank.github.exception.RecordNotFoundException;
import com.hackerrank.github.model.Actor;
import com.hackerrank.github.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actors")
public class ActorsController {
    @Autowired
    ActorService service;

    @GetMapping
    public ResponseEntity<List<Actor>> getAllActors() {
        List<Actor> list = service.getAllActors();

        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }


    @GetMapping("/actors/steak")
    public ResponseEntity<List<Actor>> getActorsBySteak()
            throws RecordNotFoundException{

        List<Actor> list = service.getActorsBySteak();

        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping( method = { RequestMethod.PUT, RequestMethod.POST })
    public ResponseEntity<Actor> createOrUpdateActor(@RequestBody Actor Actor)
            throws RecordNotFoundException {
        Actor updated = service.createOrUpdateActor(Actor);
        return new ResponseEntity<>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping()
    public HttpStatus deleteAll()
            throws RecordNotFoundException {
        service.deleteAll();
        return HttpStatus.OK;
    }


}
