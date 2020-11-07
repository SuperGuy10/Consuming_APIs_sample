package com.chad.practice.consuming_api.controller;


import com.chad.practice.consuming_api.model.Forms;
import com.chad.practice.consuming_api.model.Meta;
import com.chad.practice.consuming_api.model.User;
import com.chad.practice.consuming_api.server.ConsumingServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    ConsumingServer server;
    @Autowired
    RestTemplate rt;

    //token: 3cca4ec9633c68ab984d64b100fa6bceadff2508843b87be6b454e3d36555d84

    @RequestMapping(value = "/getAllInfo", method = RequestMethod.GET, produces = "application/json; charset = UTF-8")
    public ResponseEntity<Forms> getAllInfo() {
        return new ResponseEntity<Forms>(server.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/getCode", method = RequestMethod.GET, produces = "application/json; charset = UTF-8")
    public ResponseEntity<String>getCode(){
        return new ResponseEntity<>(server.getCode(), HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllUser", method = RequestMethod.GET, produces = "application/json; charset = UTF-8")
    public ResponseEntity<List<User>> getAllUser() {
        return new ResponseEntity<List<User>>(server.getUserList(), HttpStatus.OK);
    }

    @RequestMapping(value = "/getMeta", method = RequestMethod.GET, produces = "application/json; charset = UTF-8")
    public ResponseEntity<Meta> getMeta() {
        return new ResponseEntity<Meta>(server.getMeta(), HttpStatus.OK);
    }

    @RequestMapping(value = "/add/user", method = RequestMethod.POST, produces = "application/json; charset = UTF-8")
    public ResponseEntity<User> addUser(@RequestBody(required = true) User user){
        return new ResponseEntity<User>(server.addUser(user), HttpStatus.OK);
    }

    @RequestMapping(value = "/update/user/{id}", method = RequestMethod.PUT, produces = "application/json; charset = UTF-8")
    public ResponseEntity<String> updateUser(@PathVariable("id") String uid, @RequestBody(required = true) User user){

        return new ResponseEntity<String>(server.updateUser(uid, user), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/user/{id}", method = RequestMethod.DELETE, produces = "application/json; charset = UTF-8")
    public ResponseEntity<String> deleteUser(@PathVariable("id") String uid){

        return new ResponseEntity<String>(server.deleteUser(uid), HttpStatus.OK);
    }




}
