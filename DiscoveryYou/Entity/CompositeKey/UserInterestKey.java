package com.dell.DiscoveryYou.Entity.CompositeKey;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserInterestKey implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "interest_id")
    private Long InterestId;
}
