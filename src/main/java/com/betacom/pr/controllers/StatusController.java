package com.betacom.pr.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.pr.services.interfaces.IStatusServices;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("rest/status")
public class StatusController {

    private final IStatusServices statusS;

    @GetMapping("/list")
    public ResponseEntity<Object> list() {
        Object r;
        HttpStatus status = HttpStatus.OK;
        try {
            r = statusS.getAll();
        } catch (Exception e) {
            r = e.getMessage();
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }

    @GetMapping("/getById")
    public ResponseEntity<Object> getById(@RequestParam(required = true) Integer id) {
        Object r;
        HttpStatus status = HttpStatus.OK;
        try {
            r = statusS.getById(id);
        } catch (Exception e) {
            r = e.getMessage();
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }
    
}
