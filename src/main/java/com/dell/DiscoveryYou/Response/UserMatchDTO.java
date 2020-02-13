package com.dell.DiscoveryYou.Response;

import com.dell.DiscoveryYou.Entity.User;

public class UserMatchDTO {

    float matchingInterest;
    float matchingSkills;
    User userReference;

    public UserMatchDTO(float matchingInterest, float matchingSkills, User userReference) {
        this.matchingInterest = matchingInterest;
        this.matchingSkills = matchingSkills;
        this.userReference = userReference;
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
        return userReference;
    }

    public void setUserReference(User userReference) {
        this.userReference = userReference;
    }
}
