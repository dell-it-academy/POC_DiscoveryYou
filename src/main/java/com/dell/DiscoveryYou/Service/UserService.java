package com.dell.DiscoveryYou.Service;

import com.dell.DiscoveryYou.Entity.Interest;
import com.dell.DiscoveryYou.Entity.Skill;
import com.dell.DiscoveryYou.Entity.User;
import com.dell.DiscoveryYou.Exception.UserNotFound;
import com.dell.DiscoveryYou.Repository.InterestRepository;
import com.dell.DiscoveryYou.Repository.SkillRepository;
import com.dell.DiscoveryYou.Repository.UserRepository;
import com.dell.DiscoveryYou.Requests.CreateUserDetailsRequestModel;
import com.dell.DiscoveryYou.Response.UserMatchDTO;
import com.dell.DiscoveryYou.Util.UserUtils;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public User createUser(CreateUserDetailsRequestModel user) {
        User returnValue = users.get(user.getBadge());

        if (returnValue == null)
            returnValue = this.userRepository.findByBadge(user.getBadge()).orElse(null);
        else
            return returnValue;

        if (returnValue != null)
            return returnValue;

        returnValue = new User();
        returnValue.setFirstName(user.getFirstName());
        returnValue.setLastName(user.getLastName());
        returnValue.setBadge(user.getBadge());

        userRepository.save(returnValue);
        users.put(returnValue.getBadge(), returnValue);
        return returnValue;
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public User getUsersByBadge(String badge) {
        User returnValue = users.get(badge);

        if(returnValue == null)
            returnValue = this.userRepository.findByBadge(badge).orElse(null);

        return returnValue;
    }

    public boolean associateInterestToUser(Long userId, Long interestId) {
        Interest interest = getInterest(interestId);
        if (interest != null) {
            User user = getUser(userId);
            if (user != null) {
                user.insertInterest(interest);
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

    public boolean associateSkillToUser(Long userId, Long skillId) {
        Skill skill = getSkill(skillId);
        if (skill != null) {
            User user = getUser(userId);
            if (user != null) {
                user.insertSkill(skill);
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

    public boolean disassociateInterestFromUser(Long userId, Long interestId) {
        Interest interest = getInterest(interestId);
        if (interest != null) {
            User user = getUser(userId);
            if (user != null) {
                user.removeInterest(interest);
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

    public boolean disassociateSkillFromUser(Long userId, Long skillId) {
        Skill skill = getSkill(skillId);
        if (skill != null) {
            User user = getUser(userId);
            if (user != null) {
                user.removeSkill(skill);
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

    private User getUser(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.orElse(null);
    }

    private Interest getInterest(Long interestId) {
        Optional<Interest> interestOptional = interestRepository.findById(interestId);
        return interestOptional.orElse(null);
    }

    private Skill getSkill(Long skillId) {
        Optional<Skill> skillOptional = skillRepository.findById(skillId);
        return skillOptional.orElse(null);
    }
}
