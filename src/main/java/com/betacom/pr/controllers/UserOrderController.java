package com.betacom.pr.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.betacom.pr.dto.inputs.UserOrderReq;
import com.betacom.pr.response.Resp;
import com.betacom.pr.services.interfaces.IUserOrderServices;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("rest/order")
public class UserOrderController {

    private final IUserOrderServices orderS;

    @PostMapping("/create")
    public ResponseEntity<Resp> create(@RequestBody(required = true) UserOrderReq req) {
        Resp r = new Resp();
        HttpStatus status = HttpStatus.OK;
        try {
            orderS.create(req);
            r.setMsg("Ordine creato con successo");
        } catch (Exception e) {
            r.setMsg(e.getMessage());
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }

    @PutMapping("/updateStatus")
    public ResponseEntity<Resp> updateStatus(@RequestParam(required = true) Integer orderId, 
                                           @RequestParam(required = true) Integer statusId) {
        Resp r = new Resp();
        HttpStatus status = HttpStatus.OK;
        try {
            orderS.updateStatus(orderId, statusId);
            r.setMsg("Stato ordine aggiornato");
        } catch (Exception e) {
            r.setMsg(e.getMessage());
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> list() {
        Object r;
        HttpStatus status = HttpStatus.OK;
        try {
            r = orderS.getAll();
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
            r = orderS.getById(id);
        } catch (Exception e) {
            r = e.getMessage();
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }

    @GetMapping("/listByUser")
    public ResponseEntity<Object> listByUser(@RequestParam(required = true) String userId) {
        Object r;
        HttpStatus status = HttpStatus.OK;
        try {
            r = orderS.getByUserId(userId);
        } catch (Exception e) {
            r = e.getMessage();
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }
}
