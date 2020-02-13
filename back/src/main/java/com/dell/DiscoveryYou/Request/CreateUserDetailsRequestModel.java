package com.dell.DiscoveryYou.Request;

import javax.persistence.GeneratedValue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.dell.DiscoveryYou.Utility.ReturnMessages;
import com.dell.DiscoveryYou.Util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class CreateUserDetailsRequestModel {

    @Autowired
    UserUtils utils;


    private Long id;
    @NotBlank(message = ReturnMessages.FIRST_NAME_NOT_BLANK)
    private String firstName;
    @NotBlank(message = ReturnMessages.LAST_NAME_NOT_BLANK)
    private String lastName;
    @NotNull
    private String badge;

    public Long getId() {
        return id;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
