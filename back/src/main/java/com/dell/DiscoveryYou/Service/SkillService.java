package com.dell.DiscoveryYou.Service;

import com.dell.DiscoveryYou.Entity.Skill;
import com.dell.DiscoveryYou.Entity.User;
import com.dell.DiscoveryYou.Repository.SkillRepository;
import com.dell.DiscoveryYou.Repository.UserRepository;
import com.dell.DiscoveryYou.Request.CreateSkillDetailsRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.*;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;
    Map<String, Skill> skills;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    public SkillService(SkillRepository skillrepository) {
        this.skills = new HashMap<String, Skill>();
        this.skillRepository = skillrepository;
    }

    public Skill getInterestByName(String name) {
        return this.skillRepository.findByName(name).orElse(null);
    }


    public List<Skill> findAll(){
        return skillRepository.findAll();
    }

    public boolean insertSkill(Skill skill) {
        if(skill != null) {
            skillRepository.save(skill);
            return true;
        }
        return false;
    }

    public Skill createSkill(@Valid CreateSkillDetailsRequestModel skill) {

        Skill returnValue = skills.get(skill.getName());

        if(returnValue == null)
            returnValue = this.skillRepository.findByName(skill.getName()).orElse(null);
        else
            return returnValue;

        if (returnValue != null)
            return returnValue;

        returnValue = new Skill();
        returnValue.setName(skill.getName());
        returnValue.setUsers(skill.getUsers());
        returnValue.setRank(skill.getRank());

        skillRepository.save(returnValue);
        skills.put(returnValue.getName(), returnValue);
        return returnValue;
    }

    public boolean deleteSkillFromUser(Long skillId, Long userId) {
        if(skillId != null && userId != null) {
            Optional<User> userOptional = userRepository.findById(userId);
            Optional<Skill> skill = skillRepository.findById(skillId);
            if(userOptional.isPresent() && skill.isPresent()){
                User user = userOptional.get();
                user.removeSkill(skill.get());
                userRepository.save(user);
                skillRepository.save(skill.get());
                return true;
            }
        }
        return false;
    }
}
