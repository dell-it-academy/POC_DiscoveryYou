package com.dell.DiscoveryYou.Controller;

import com.dell.DiscoveryYou.Entity.Interest;
import com.dell.DiscoveryYou.Entity.User;
import com.dell.DiscoveryYou.Request.CreateInterestDetailsRequestModel;
import com.dell.DiscoveryYou.Service.InterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/interests") // http://localhost:8080/interests
public class InterestController {

    @Autowired
    InterestService interestService;


    @GetMapping
    public ResponseEntity<List<Interest>> getInterests(@RequestParam(value="page",  defaultValue  = "1", required = false) int startPage,
                                               @RequestParam(value="limit", defaultValue = "50", required = false) int pageOffset) {
        List<Interest> returnValue;
        if (Optional.ofNullable(startPage).isPresent()  || Optional.ofNullable(pageOffset).isPresent()) {
            returnValue = interestService.findAll(startPage, pageOffset);
        } else {
            returnValue = interestService.findAll();
        }
        return ResponseEntity.ok(returnValue);
    }

    @GetMapping(path ="/{name}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Interest> getInterestByName(@PathVariable String name) {
        Interest returnValue = interestService.getInterestByName(name);
        if (returnValue == null)
            return new ResponseEntity(HttpStatus.I_AM_A_TEAPOT);
        return new ResponseEntity(returnValue, HttpStatus.OK);
    }

//    @GetMapping("/user/{userBadge}")
//    public ResponseEntity<Interest> getInterestByUser(@PathVariable String userBadge) {
//        List<Interest> returnValue = interestService.getInterestsByUser(userBadge);
//        if (returnValue == null)
//            return new ResponseEntity(HttpStatus.I_AM_A_TEAPOT);
//        return new ResponseEntity(returnValue, HttpStatus.OK);
//    }

//    @PostMapping(
//            consumes = { MediaType.APPLICATION_JSON_VALUE },
//            produces = { MediaType.APPLICATION_JSON_VALUE }
//    )
//    public ResponseEntity createInterest(@Valid @RequestBody CreateInterestDetailsRequestModel interestDetails) {
//        Interest returnValue = interestService.createInterest(interestDetails);
//        if (returnValue == null)
//            return new ResponseEntity(HttpStatus.I_AM_A_TEAPOT);
//        return new ResponseEntity(returnValue, HttpStatus.CREATED);
//    }
}
