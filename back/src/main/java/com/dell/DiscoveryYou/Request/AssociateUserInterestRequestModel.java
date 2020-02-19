package com.dell.DiscoveryYou.Request;

import com.dell.DiscoveryYou.Utility.ReturnMessages;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AssociateUserInterestRequestModel {

    @NotBlank(message = ReturnMessages.NAME_NOT_BLANK)
    @Size(min = 3, message = ReturnMessages.LENGTH_ERROR)
    private String interestName;
    @NotBlank(message = ReturnMessages.NAME_NOT_BLANK)
    @Size(min = 3, message = ReturnMessages.LENGTH_ERROR)
    private String userBadge;

    public String getInterestName() {
        return interestName;
    }

    public void setInterestName(String interestName) {
        this.interestName = interestName;
    }

    public String getUserBadge() {
        return userBadge;
    }

    public void setUserBadge(String userBadge) {
        this.userBadge = userBadge;
    }
}
