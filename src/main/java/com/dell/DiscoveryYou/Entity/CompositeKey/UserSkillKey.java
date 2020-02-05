package com.dell.DiscoveryYou.Entity.CompositeKey;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserSkillKey implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "skill_id")
    private Long skillId;
}
