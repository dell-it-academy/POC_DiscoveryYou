package com.dell.DiscoveryYou.Util;

import com.dell.DiscoveryYou.Entity.Interest;
import com.dell.DiscoveryYou.Entity.Skill;
import com.dell.DiscoveryYou.Entity.User;
import com.dell.DiscoveryYou.Response.UserDTO;
import com.dell.DiscoveryYou.Response.UserMatchDTO;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserUtils {

    public UserUtils() {}

    public UserMatchDTO calculateMatchingPercentage(User user, User relUser) {
        float userInterestsPercentage = 100;
        float userSkillsPercentage = 100;

        List<Interest> userInterests = user.getInterests();
        List<Interest> relUserInterests = relUser.getInterests();
        List<Skill> userSkills = user.getSkills();
        List<Skill> relUserSkills = relUser.getSkills();

        Set<String> infoSet = new HashSet<>(userInterests.stream().map(interest -> interest.getName()).collect(Collectors.toList()));
        infoSet.retainAll(new HashSet<>(relUserInterests.stream().map(interest -> interest.getName()).collect(Collectors.toList())));

        if (userInterests.size() < relUserInterests.size())
            userInterestsPercentage *= infoSet.size() / (float) userInterests.size();
        else
            userInterestsPercentage *= infoSet.size() / (float) relUserInterests.size();

        infoSet = new HashSet<>(userSkills.stream().map(skill -> skill.getName()).collect(Collectors.toList()));
        infoSet.retainAll(new HashSet<>(relUserSkills.stream().map(skill -> skill.getName()).collect(Collectors.toList())));

        if (userSkills.size() < relUserSkills.size())
            userSkillsPercentage *= infoSet.size() / (float) userSkills.size();
        else
            userSkillsPercentage *= infoSet.size() / (float) relUserSkills.size();

        return new UserMatchDTO(userInterestsPercentage, userSkillsPercentage, relUser);
    }

    public UserDTO convertUserToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setBadge(user.getBadge());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setInterests(user.getInterests());
        userDTO.setSkills(user.getSkills());
        return userDTO;
    }
}
