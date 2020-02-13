package com.dell.DiscoveryYou.Service;

import com.dell.DiscoveryYou.Entity.Interest;
import com.dell.DiscoveryYou.Entity.Skill;
import com.dell.DiscoveryYou.Entity.User;
import com.dell.DiscoveryYou.Exception.UserNotFound;
import com.dell.DiscoveryYou.Repository.InterestRepository;
import com.dell.DiscoveryYou.Repository.SkillRepository;
import com.dell.DiscoveryYou.Repository.UserRepository;
import com.dell.DiscoveryYou.Request.CreateUserDetailsRequestModel;
import com.dell.DiscoveryYou.Response.UserMatchDTO;
import com.dell.DiscoveryYou.Util.UserUtils;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

import java.util.*;

@Service
public class UserService {

    private Map<String, User> users;
    private UserRepository userRepository;
    private InterestRepository interestRepository;
    private SkillRepository skillRepository;
    private UserUtils userUtils;

    @Autowired
    public UserService(UserRepository userRepository, InterestRepository interestRepository, SkillRepository skillRepository, UserUtils userutils) {
        this.users = new HashMap<String, User>();
        this.userRepository = userRepository;
        this.interestRepository = interestRepository;
        this.skillRepository = skillRepository;
        this.userUtils = userutils;
    }

    public List<User> getUsersWithMatchingPercentage(String userBadge, int startPage, int pageOffset) throws UserNotFound {

        User appUser = userRepository.findByBadge(userBadge).orElse(null);
        if (appUser == null) throw new UserNotFound("User with badge ".concat(userBadge).concat(" not found"));
        List<User> relatedUsers = userRepository.findAll(PageRequest.of(startPage, pageOffset)).getContent();
        List<UserMatchDTO> users = new ArrayList<>();
        relatedUsers.forEach((relUser) -> users.add(userUtils.calculateMatchingPercentage(appUser, relUser)));
        return relatedUsers;
    }

    @Transactional
    public User createUser(@Valid CreateUserDetailsRequestModel user) {
        User returnValue = users.get(user.getBadge());

        if (returnValue == null) {
            returnValue = this.userRepository.findByBadge(user.getBadge()).orElse(null);
            if(returnValue == null) {
                returnValue = new User();
                returnValue.setFirstName(user.getFirstName());
                returnValue.setLastName(user.getLastName());
                returnValue.setBadge(user.getBadge());
            }
        } else {
            return returnValue;
        }

            userRepository.save(returnValue);
            users.put(returnValue.getBadge(), returnValue);
        return returnValue;
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Transactional
    public boolean deleteUser(String userBadge) throws UserNotFound {
        User user = getUsersByBadge(userBadge);
        if (user == null)
            throw new UserNotFound("User with badge " + userBadge + " not found");
        user.getInterests().forEach(interest -> user.removeInterest(interest));
        user.getSkills().forEach(skill -> user.removeSkill(skill));
        try {
            userRepository.delete(user);
        } catch (Exception e) {
            throw e;
        }
        return true;
    }

    public List<User> findAll(int startPage, int pageOffset) {
        return this.userRepository.findAll(PageRequest.of(startPage, pageOffset)).getContent();
    }

    public User getUsersByBadge(String badge) {
        User returnValue = users.get(badge);

        if(returnValue == null)
            returnValue = this.userRepository.findByBadge(badge).orElse(null);

        return returnValue;
    }

    public boolean associateInterestToUser(String userBadge, String interestName) {
        Interest interest = getInterest(interestName);
        if (interest != null) {
            User user = getUser(userBadge);
            if (user != null) {
                user.insertInterest(interest);
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

    public boolean associateSkillToUser(String userBadge, String skillName) {
        Skill skill = getSkill(skillName);
        if (skill != null) {
            User user = getUser(userBadge);
            if (user != null) {
                user.insertSkill(skill);
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

    public boolean disassociateInterestFromUser(String userBadge, String interestName) {
        Interest interest = getInterest(interestName);
        if (interest != null) {
            User user = getUser(userBadge);
            if (user != null) {
                user.removeInterest(interest);
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

    public boolean disassociateSkillFromUser(String userBadge, String skillName) {
        Skill skill = getSkill(skillName);
        if (skill != null) {
            User user = getUser(userBadge);
            if (user != null) {
                user.removeSkill(skill);
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

    private User getUser(String userBadge) {
        Optional<User> userOptional = userRepository.findByBadge(userBadge);
        return userOptional.orElse(null);
    }

    private Interest getInterest(Long interestName) {
        Optional<Interest> interestOptional = interestRepository.findById(interestName);
        return interestOptional.orElse(null);
    }

    private Interest getInterest(String interestName) {
        Optional<Interest> interestOptional = interestRepository.findByName(interestName);
        return interestOptional.orElse(null);
    }

    private Skill getSkill(Long skillId) {
        Optional<Skill> skillOptional = skillRepository.findById(skillId);
        return skillOptional.orElse(null);
    }

    private Skill getSkill(String skillName) {
        Optional<Skill> skillOptional = skillRepository.findByName(skillName);
        return skillOptional.orElse(null);
    }
}
