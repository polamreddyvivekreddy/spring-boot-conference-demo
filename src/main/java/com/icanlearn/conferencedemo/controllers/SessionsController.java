package com.icanlearn.conferencedemo.controllers;

import com.icanlearn.conferencedemo.models.Session;
import com.icanlearn.conferencedemo.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/sessions")
public class SessionsController {

    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping
    public List<Session> list(){
        return sessionRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Session get(@PathVariable Long id){
        return sessionRepository.getOne(id);
    }

    @PostMapping
    public Session create(@RequestBody final Session session){
        return sessionRepository.saveAndFlush(session);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE )
    public void delete(@PathVariable Long id){
        //Also need to check children records before deleting
        sessionRepository.deleteById(id);
    }

    @RequestMapping(value="{id}", method=RequestMethod.PUT)
    public Session update(@PathVariable Long id,@RequestBody Session session){
        // because this is PUT, we expect all attributes to be passed otherwise use a PATCH
        //TODO: Add validation that all attributes are passed into, else return 400 bad payload
        Session existingSession = sessionRepository.getOne(id);
        BeanUtils.copyProperties(session,existingSession,"session_id");
        return sessionRepository.saveAndFlush(existingSession);
    }
}
