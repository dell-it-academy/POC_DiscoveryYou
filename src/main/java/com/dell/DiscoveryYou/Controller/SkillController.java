package com.dell.DiscoveryYou.Controller;

import com.dell.DiscoveryYou.Entity.Skill;
import com.dell.DiscoveryYou.Requests.CreateSkillDetailsRequestModel;
import com.dell.DiscoveryYou.Services.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/skills") // http://localhost:8080/Skills
public class SkillController {

    @Autowired
    SkillService skillService;

    @GetMapping
    public String getSkills(@RequestParam(value="page",  defaultValue  = "1") int page,
                               @RequestParam(value="limit", defaultValue = "50") int limit){
        return skillService.findAll().toString();
    }

    @GetMapping(path ="/{name}",
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            },
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    public ResponseEntity<Skill> getSkillByName(@PathVariable String name){
        Skill returnValue = skillService.getInterestByName(name);

        if(returnValue == null)
            return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);

        return new ResponseEntity<Skill>(returnValue, HttpStatus.OK);

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
    public ResponseEntity createSkill(@Valid @RequestBody CreateSkillDetailsRequestModel skillsDetails){
        Skill returnValue = skillService.createSkill(skillsDetails);
        if(returnValue == null)
            return new ResponseEntity(HttpStatus.I_AM_A_TEAPOT);

        return new ResponseEntity(returnValue, HttpStatus.CREATED);
    }
}
