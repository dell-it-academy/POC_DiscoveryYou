package com.dell.DiscoveryYou.Entity;

import com.dell.DiscoveryYou.Entity.CompositeKey.UserInterestKey;

import javax.persistence.*;

@Entity
public class UserInterest {

    @EmbeddedId
    private UserInterestKey id;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    private UserRest userRest;

    @ManyToOne
    @MapsId("interest_id")
    @JoinColumn(name = "interest_id")
    private Interest interest;
}