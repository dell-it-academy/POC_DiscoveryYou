package com.dell.DiscoveryYou.Requests;

import com.dell.DiscoveryYou.Utility.ReturnMessages;

import javax.validation.constraints.NotBlank;

public class UserDetailsRequestModel {

    @NotBlank(message = ReturnMessages.FIRST_NAME_NOT_BLANK)
    private String firstName;
    @NotBlank(message = ReturnMessages.LAST_NAME_NOT_BLANK)
    private String lastName;

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
