package com.dell.DiscoveryYou.Entity;

import com.dell.DiscoveryYou.Entity.CompositeKey.UserSkillKey;

import javax.persistence.*;

@Entity
public class UserSkill {

    @EmbeddedId
    private UserSkillKey id;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    private User userRest;

    @ManyToOne
    @MapsId("skill_id")
    @JoinColumn(name = "skill_id")
    private Skill skill;
}
