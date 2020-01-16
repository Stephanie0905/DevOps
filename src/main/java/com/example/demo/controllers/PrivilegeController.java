package com.example.demo.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.common.AbstractController;
import com.example.demo.entities.Privilege;
import com.example.demo.services.IPrivilegeService;



@Controller
@RequestMapping(value = "api/privileges")
public class PrivilegeController extends AbstractController<Privilege> {

    @Autowired
    private IPrivilegeService service;

    public PrivilegeController() {
        super(Privilege.class);
    }

    
    @RequestMapping(method = RequestMethod.GET)
    @Secured("ROLE_PRIVILEGE_READ")
    @ResponseBody
    public List<Privilege> findAll(final HttpServletRequest request) {
        return findAllInternal(request);
    }

    // find - one

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Privilege findOne(@PathVariable("id") final Long id) {
        return findOneInternal(id);
    }

    // create

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid final Privilege resource) {
        createInternal(resource);
    }

    // update

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") final Long id, @RequestBody @Valid final Privilege resource) {
        updateInternal(id, resource);
    }

    // delete

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") final Long id) {
        deleteByIdInternal(id);
    }

    // Spring

    @Override
    protected final IPrivilegeService getService() {
        return service;
    }

}
