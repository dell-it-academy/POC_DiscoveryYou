package com.dell.DiscoveryYou.Util;

import com.dell.DiscoveryYou.Entity.Interest;
import com.dell.DiscoveryYou.Entity.Skill;
import com.dell.DiscoveryYou.Entity.User;
import com.dell.DiscoveryYou.Response.UserMatchDTO;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UserUtils {

    public UserUtils() {}

    public UserMatchDTO calculateMatchingPercentage(User user, User relUser) {

        float userInterestsPercentage = 100;
        float userSkillsPercentage = 100;

        Set<String> infoSet = new HashSet<>((List) user.getInterests().stream().map(interest -> interest.getName()));
        infoSet.retainAll(new HashSet<>((List) relUser.getInterests().stream().map(interest -> interest.getName())));

        userInterestsPercentage *= infoSet.size() / (float)((List<Interest>) user.getInterests()).size();

        infoSet = new HashSet<>((List) user.getSkills().stream().map(skill -> skill.getName()));
        infoSet.retainAll(new HashSet<>((List) relUser.getSkills().stream().map(skill -> skill.getName())));

        userSkillsPercentage *= infoSet.size() / (float) ((List<Skill>) user.getSkills()).size();

        return new UserMatchDTO( userInterestsPercentage, userSkillsPercentage,user);
    }
}
