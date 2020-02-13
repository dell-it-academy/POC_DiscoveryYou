package com.dell.DiscoveryYou.Controller;

import com.dell.DiscoveryYou.Entity.Skill;
import com.dell.DiscoveryYou.Request.CreateSkillDetailsRequestModel;
import com.dell.DiscoveryYou.Service.SkillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/skills") // http://localhost:8080/Skills
public class SkillController {

    private SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping
    public List<Skill> getSkills(@RequestParam(value="page",  defaultValue  = "1") int startPage,
                                 @RequestParam(value="limit", defaultValue = "50") int pageOffset){
        return skillService.findAll(startPage, pageOffset);
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
    public ResponseEntity<Skill> getSkillByName(@PathVariable String skillName){
        Skill returnValue = skillService.getSkillByName(skillName);

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
