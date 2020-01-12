package com.hackerrank.github.controller;

import com.hackerrank.github.exception.RecordNotFoundException;
import com.hackerrank.github.model.Actor;
import com.hackerrank.github.service.ActorService;
import com.hackerrank.github.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/erase")
public class EraseController {
    @Autowired
    ActorService service;

    @Autowired
    EventService eventService;



    @DeleteMapping()
    public HttpStatus deleteAll()
            throws RecordNotFoundException {
        service.deleteAll();
        eventService.deleteAll();
        return HttpStatus.OK;
    }


}
