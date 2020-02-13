package com.dell.DiscoveryYou.Service;

import com.dell.DiscoveryYou.Entity.Interest;
import com.dell.DiscoveryYou.Entity.Skill;
import com.dell.DiscoveryYou.Entity.User;
import com.dell.DiscoveryYou.Exception.*;
import com.dell.DiscoveryYou.Repository.InterestRepository;
import com.dell.DiscoveryYou.Repository.UserRepository;
import com.dell.DiscoveryYou.Requests.CreateUserDetailsRequestModel;
import com.dell.DiscoveryYou.Response.UserMatchDTO;
import com.dell.DiscoveryYou.Util.UserUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class UserService {

    private Map<String, User> users;
    private UserRepository userRepository;
    private InterestService interestService;
    private SkillService skillService;
    private UserUtils userUtils;

    public UserService(UserRepository userRepository, InterestRepository interestRepository, SkillService skillService, UserUtils userutils, InterestService interestService) {
        this.users = new HashMap<String, User>();
        this.userRepository = userRepository;
        this.interestService = interestService;
        this.skillService = skillService;
        this.userUtils = userutils;
    }

    public List<User> getUsersWithMatchingPercentage(String userBadge, int startPage, int pageOffset) throws UserNotFound {
        User appUser = userRepository.findByBadge(userBadge).orElse(null);
        if (appUser == null)
            throw new UserNotFound("User with badge " + userBadge + " not found");
        List<User> relatedUsers = userRepository.findAll(PageRequest.of(startPage, pageOffset)).getContent();
        List<UserMatchDTO> users = new ArrayList<>();
        relatedUsers.forEach((relUser) -> users.add(userUtils.calculateMatchingPercentage(appUser, relUser)));
        return relatedUsers;
    }

    @Transactional
    public User createUser(CreateUserDetailsRequestModel user) {
        User returnValue = users.get(user.getBadge());
        if (returnValue == null) {
            returnValue = this.userRepository.findByBadge(user.getBadge()).orElse(null);
        } else {
            returnValue = new User();
            returnValue.setFirstName(user.getFirstName());
            returnValue.setLastName(user.getLastName());
            returnValue.setBadge(user.getBadge());
            userRepository.save(returnValue);
        }
        users.put(returnValue.getBadge(), returnValue);
        return returnValue;
    }

    @Transactional
    public boolean deleteUser(String userBadge) throws UserNotFound {
        User user = getUserByBadge(userBadge);
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

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Transactional
    public boolean associateInterestToUser(String userBadge, String interestName) throws UserNotFound, UserAlreadyHasInterest {
        User user = this.getUserByBadge(userBadge);
        if (user == null)
            throw new UserNotFound("User with badge " + userBadge + " not found");
        if (user.getInterests().stream().map(interest -> interest.getName()).collect(Collectors.toList()).contains(interestName))
            throw new UserAlreadyHasInterest("User with badge " + userBadge + " already has interest \"" + interestName + "\"");
        Interest interest = interestService.getInterestByName(interestName);
        if (interest == null)
            interest = interestService.createInterest(interestName);
        user.insertInterest(interest);
        return userRepository.save(user) instanceof User;
    }

    @Transactional
    public boolean associateSkillToUser(String userBadge, String skillName) throws UserNotFound, UserAlreadyHasSkill {
        User user = this.getUserByBadge(userBadge);
        if (user == null)
            throw new UserNotFound("User with badge " + userBadge + " not found");
        if (user.getSkills().stream().map(skill -> skill.getName()).collect(Collectors.toList()).contains(skillName))
            throw new UserAlreadyHasSkill("User with badge " + userBadge + " already has skill \"" + skillName + "\"");
        Skill skill = skillService.getSkillByName(skillName);
        if (skill == null)
            skill = skillService.createSkill(skillName);
        user.insertSkill(skill);
        return userRepository.save(user) instanceof User;
    }

    @Transactional
    public boolean disassociateInterestFromUser(String userBadge, String interestName) throws UserNotFound, UserDoesNotHaveInterest {
        User user = this.getUserByBadge(userBadge);
        if (user == null)
            throw new UserNotFound("User with badge " + userBadge + " not found");
        if (!user.getInterests().stream().map(interest -> interest.getName()).collect(Collectors.toList()).contains(interestName))
            throw new UserDoesNotHaveInterest("user with badge " + userBadge + " does not have interest \"" + interestName + "\"");
        Interest interest = interestService.getInterestByName(interestName);
        user.removeInterest(interest);
        return userRepository.save(user) instanceof User;
    }

    @Transactional
    public boolean disassociateSkillFromUser(String userBadge, String skillName) throws UserNotFound, UserDoesNotHaveSkill {
        User user = this.getUserByBadge(userBadge);
        if (user == null)
            throw new UserNotFound("User with badge " + userBadge + " not found");
        if (!user.getSkills().stream().map(skill -> skill.getName()).collect(Collectors.toList()).contains(skillName))
            throw new UserDoesNotHaveSkill("user with badge " + userBadge + " does not have interest \"" + skillName + "\"");
        Skill skill = skillService.getSkillByName(skillName);
        user.removeSkill(skill);
        return userRepository.save(user) instanceof User;
    }

    public User getUserByBadge(String userBadge) {
        Optional<User> userOptional = userRepository.findByBadge(userBadge);
        return userOptional.orElse(null);
    }
}
