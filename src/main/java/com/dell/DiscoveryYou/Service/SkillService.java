package com.dell.DiscoveryYou.Service;

import com.dell.DiscoveryYou.Entity.Skill;
import com.dell.DiscoveryYou.Repository.SkillRepository;
import com.dell.DiscoveryYou.Request.CreateSkillDetailsRequestModel;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;


@Service
public class SkillService {

    private SkillRepository skillRepository;
    Map<String, Skill> skills;

    public SkillService(SkillRepository skillrepository) {
        this.skills = new HashMap<String, Skill>();
        this.skillRepository = skillrepository;
    }

    public List<Skill> findAll(int startPage, int pageOffset){
        return this.skillRepository.findAll(PageRequest.of(startPage, pageOffset)).getContent();
    }

    public Skill getSkillByName(String name) {
        return this.skillRepository.findByName(name).orElse(null);
    }

    @Transactional
    public Skill createSkill(CreateSkillDetailsRequestModel skill) {
        Skill returnValue = skills.get(skill.getName());
        if (returnValue == null) {
            returnValue = this.skillRepository.findByName(skill.getName()).orElse(null);
        } else {
            returnValue = new Skill(skill.getName());
            returnValue.setName(skill.getName());
            skillRepository.save(returnValue);
        }
        skills.put(returnValue.getName(), returnValue);
        return returnValue;
    }

    @Transactional
    public Skill createSkill(String interestName) {
        Skill returnValue = skills.get(interestName);
        if (returnValue == null) {
            returnValue = this.getSkillByName(interestName);
        } else {
            returnValue = new Skill(interestName);
            skillRepository.save(returnValue);
        }
        skills.put(returnValue.getName(), returnValue);
        return returnValue;
    }
}
