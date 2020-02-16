package com.dell.DiscoveryYou.Controller;

import com.dell.DiscoveryYou.Entity.User;
import com.dell.DiscoveryYou.Exception.*;
import com.dell.DiscoveryYou.Request.CreateUserDetailsRequestModel;
import com.dell.DiscoveryYou.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/users") // http://localhost:8080/users
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getUsers(@RequestParam(value="page", required = false) Integer startPage,
                           @RequestParam(value="limit", required = false) Integer pageOffset) {
        List<User> returnValue;
        if (Optional.ofNullable(startPage).isPresent()  || Optional.ofNullable(pageOffset).isPresent()) {
            returnValue = userService.findAll(startPage, pageOffset);
        } else {
            returnValue = userService.findAll();
        }
        return ResponseEntity.ok(returnValue);
    }

    @GetMapping(path ="/{badge}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<User> getUserByBadge(@PathVariable String badge){
        User returnValue = userService.getUserByBadge(badge);
        ResponseEntity<User> response;
        if (returnValue == null)
            response = new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
        else
            response = new ResponseEntity<User>(returnValue, HttpStatus.OK);
        return response;
    }

    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity createUser(@Valid @RequestBody CreateUserDetailsRequestModel userDetails){
        User returnValue = userService.createUser(userDetails);
        ResponseEntity<User> response;
        if (returnValue != null)
            response = new ResponseEntity(returnValue, HttpStatus.CREATED);
        else
            response = new ResponseEntity(HttpStatus.I_AM_A_TEAPOT);
        return response;
    }

    @PostMapping("/associate/interest")
    public ResponseEntity<User> associateInterestToUser(@RequestParam @NotNull String userBadge, @RequestParam @NotNull String interestName) throws UserNotFound, UserAlreadyHasInterest {
        User returnValue = userService.associateInterestToUser(userBadge, interestName);
        ResponseEntity<User> response;
        if (Optional.ofNullable(returnValue).isPresent()) {
            response = ResponseEntity.ok(returnValue);
        } else {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    @PostMapping("/associate/skill")
    public ResponseEntity<User> associateSkillToUser(@RequestParam @NotNull String userBadge, @RequestParam @NotNull String skillName) throws UserNotFound, UserAlreadyHasSkill {
        User returnValue = userService.associateSkillToUser(userBadge, skillName);
        ResponseEntity<User> response;
        if (Optional.ofNullable(returnValue).isPresent()) {
            response = ResponseEntity.ok(returnValue);
        } else {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    @PutMapping("/disassociate/interest")
    public ResponseEntity<User> disassociateInterestFromUser(@RequestParam @NotNull String userBadge, @RequestParam @NotNull String interestName) throws UserNotFound, UserDoesNotHaveInterest {
        User returnValue = userService.disassociateInterestFromUser(userBadge, interestName);
        ResponseEntity<User> response;
        if (Optional.ofNullable(returnValue).isPresent()) {
            response = ResponseEntity.ok(returnValue);
        } else {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    @PutMapping("/disassociate/skill")
    public ResponseEntity<User> disassociateSkillFromUser(@RequestParam @NotNull String userBadge, @RequestParam @NotNull String skillName) throws UserNotFound, UserDoesNotHaveSkill {
        User returnValue = userService.disassociateSkillFromUser(userBadge, skillName);
        ResponseEntity<User> response;
        if (Optional.ofNullable(returnValue).isPresent()) {
            response = ResponseEntity.ok(returnValue);
        } else {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }
}