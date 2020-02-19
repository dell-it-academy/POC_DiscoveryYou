package com.dell.DiscoveryYou.Util;

import com.dell.DiscoveryYou.Entity.Interest;
import com.dell.DiscoveryYou.Entity.Skill;
import com.dell.DiscoveryYou.Entity.User;
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

        Set<String> infoSet = new HashSet<>(user.getInterests().stream().map(interest -> interest.getName()).collect(Collectors.toList()));
        infoSet.retainAll(new HashSet<>(relUser.getInterests().stream().map(interest -> interest.getName()).collect(Collectors.toList())));

        userInterestsPercentage *= infoSet.size() / (float)((List<Interest>) user.getInterests()).size();

        infoSet = new HashSet<>(user.getSkills().stream().map(skill -> skill.getName()).collect(Collectors.toList()));
        infoSet.retainAll(new HashSet<>(relUser.getSkills().stream().map(skill -> skill.getName()).collect(Collectors.toList())));

        userSkillsPercentage *= infoSet.size() / (float) ((List<Skill>) user.getSkills()).size();

        return new UserMatchDTO(userInterestsPercentage, userSkillsPercentage, relUser);
    }
}
