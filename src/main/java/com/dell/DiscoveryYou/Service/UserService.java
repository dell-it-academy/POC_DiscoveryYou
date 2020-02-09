package com.dell.DiscoveryYou.Service;

import com.dell.DiscoveryYou.Entity.Interest;
import com.dell.DiscoveryYou.Entity.Skill;
import com.dell.DiscoveryYou.Entity.User;
import com.dell.DiscoveryYou.Exception.*;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

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
        if (appUser == null) throw new UserNotFound("User with badge " + userBadge +" not found");
        List<User> relatedUsers = userRepository.findAll(PageRequest.of(startPage, pageOffset)).getContent();
        List<UserMatchDTO> users = new ArrayList<>();
        relatedUsers.forEach((relUser) -> users.add(userUtils.calculateMatchingPercentage(appUser, relUser)));
        return relatedUsers;
    }

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

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public User getUsersByBadge(String badge) {
        User returnValue = users.get(badge);

        if(returnValue == null)
            returnValue = this.userRepository.findByBadge(badge).orElse(null);

        return returnValue;
    }

    @Transactional
    public boolean associateInterestToUser(String userBadge, String interestName) throws UserNotFound, UserAlreadyHasInterest{
        User user = this.getUser(userBadge);
        if (user == null)
            throw new UserNotFound("User with badge " + userBadge +" not found");
        if (user.getInterests().stream().map(interest -> interest.getName()).collect(Collectors.toList()).contains(interestName))
            throw new UserAlreadyHasInterest("User with badge " + userBadge + " already has interest \"" + interestName + "\"");
        Interest interest = getInterest(interestName);
        if (interest == null)
            interest = interestRepository.save(new Interest(interestName));
        user.insertInterest(interest);
        return userRepository.save(user) instanceof User;
    }

    @Transactional
    public boolean associateSkillToUser(String userBadge, String skillName) throws UserNotFound, UserAlreadyHasSkill{
        User user = this.getUser(userBadge);
        if (user == null)
            throw new UserNotFound("User with badge " + userBadge +" not found");
        if (user.getSkills().stream().map(skill -> skill.getName()).collect(Collectors.toList()).contains(skillName))
            throw new UserAlreadyHasSkill("User with badge " + userBadge + " already has skill \"" + skillName + "\"");
        Skill skill = getSkill(skillName);
        if (skill == null)
            skill = skillRepository.save(new Skill(skillName));
        user.insertSkill(skill);
        return userRepository.save(user) instanceof User;
    }

    @Transactional
    public boolean disassociateInterestFromUser(String userBadge, String interestName) throws UserNotFound, UserDoesNotHaveInterest{
        User user = this.getUser(userBadge);
        if (user == null)
            throw new UserNotFound("User with badge " + userBadge +" not found");
        if (!user.getInterests().stream().map(interest -> interest.getName()).collect(Collectors.toList()).contains(interestName))
            throw new UserDoesNotHaveInterest("user with badge " + userBadge + " does not have interest \"" + interestName + "\"");
        Interest interest = getInterest(interestName);
        user.removeInterest(interest);
        return userRepository.save(user) instanceof User;
    }

    @Transactional
    public boolean disassociateSkillFromUser(String userBadge, String skillName) throws UserNotFound, UserDoesNotHaveSkill {
        User user = this.getUser(userBadge);
        if (user == null)
            throw new UserNotFound("User with badge " + userBadge +" not found");
        if (!user.getSkills().stream().map(skill -> skill.getName()).collect(Collectors.toList()).contains(skillName))
            throw new UserDoesNotHaveSkill("user with badge " + userBadge + " does not have interest \"" + skillName + "\"");
        Skill skill = getSkill(skillName);
        user.removeSkill(skill);
        return userRepository.save(user) instanceof User;
    }

    private User getUser(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.orElse(null);
    }

    private User getUser(String userBadge) {
        Optional<User> userOptional = userRepository.findByBadge(userBadge);
        return userOptional.orElse(null);
    }

    private Interest getInterest(Long interestId) {
        Optional<Interest> interestOptional = interestRepository.findById(interestId);
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
