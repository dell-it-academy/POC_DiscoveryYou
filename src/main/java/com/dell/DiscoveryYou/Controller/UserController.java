package com.dell.DiscoveryYou.Controller;

import com.dell.DiscoveryYou.Entity.User;
import com.dell.DiscoveryYou.Exception.*;
import com.dell.DiscoveryYou.Requests.CreateUserDetailsRequestModel;
import com.dell.DiscoveryYou.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@RestController
@RequestMapping("/users") // http://localhost:8080/users
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public String getUsers(@RequestParam(value="page",  defaultValue  = "1") int page,
                           @RequestParam(value="limit", defaultValue = "50") int limit){
        return userService.findAll().toString();
    }

    @GetMapping(path ="/{badge}",
        consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
        },
        produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
        }
    )
    public ResponseEntity<User> getUserByBadge(@PathVariable String badge){
        User returnValue = userService.getUserByBadge(badge);

        if(returnValue == null)
            return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
        else
            return new ResponseEntity<>(returnValue, HttpStatus.OK);

    }

    @PostMapping(
        consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
        },
        produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
        }
    )
    public ResponseEntity createUser(@Valid @RequestBody CreateUserDetailsRequestModel userDetails){
        User returnValue = userService.createUser(userDetails);
        if (returnValue != null)
            return new ResponseEntity(returnValue, HttpStatus.CREATED);
        return new ResponseEntity(HttpStatus.I_AM_A_TEAPOT);
    }

    @PostMapping("/associate/interest")
    public ResponseEntity<String> associateInterestToUser(@RequestParam @NotNull String userBadge, @RequestParam @NotNull String interestName) throws UserNotFound, UserAlreadyHasInterest {
        boolean result = userService.associateInterestToUser(userBadge, interestName);
        if (result) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/associate/skill")
    public ResponseEntity<String> associateSkillToUser(@RequestParam @NotNull String userBadge, @RequestParam @NotNull String skillName) throws UserNotFound, UserAlreadyHasSkill {
        boolean result = userService.associateSkillToUser(userBadge, skillName);
        if (result) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/disassociate/interest")
    public ResponseEntity<String> disassociateInterestFromUser(@RequestParam @NotNull String userBadge, @RequestParam @NotNull String interestName) throws UserNotFound, UserDoesNotHaveInterest {
        boolean result = userService.disassociateInterestFromUser(userBadge, interestName);
        if (result) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/disassociate/skill")
    public ResponseEntity<String> disassociateSkillFromUser(@RequestParam @NotNull String userBadge, @RequestParam @NotNull String skillName) throws UserNotFound, UserDoesNotHaveSkill {
        boolean result = userService.disassociateSkillFromUser(userBadge, skillName);
        if (result) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}