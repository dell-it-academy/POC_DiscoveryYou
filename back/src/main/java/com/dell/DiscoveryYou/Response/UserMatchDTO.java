package com.dell.DiscoveryYou.Response;

import com.dell.DiscoveryYou.Entity.User;

public class UserMatchDTO {

    float matchingInterest = 0.0f;
    float matchingSkills = 0.0f;
    User user;

    public UserMatchDTO(float matchingInterest, float matchingSkills, User user) {
        this.matchingInterest = matchingInterest;
        this.matchingSkills = matchingSkills;
        this.user = user;
    }

    public float getMatchingInterest() {
        return matchingInterest;
    }

    public void setMatchingInterest(float matchingInterest) {
        this.matchingInterest = matchingInterest;
    }

    public float getMatchingSkills() {
        return matchingSkills;
    }

    public void setMatchingSkills(float matchingSkills) {
        this.matchingSkills = matchingSkills;
    }

    public User getUserReference() {
        return user;
    }

    public void setUserReference(User userReference) {
        this.user = userReference;
    }
}
