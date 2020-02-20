package com.dell.DiscoveryYou.Request;

import com.dell.DiscoveryYou.Utility.ReturnMessages;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class AssociateUserSkillRequestModel {

    @NotBlank(message = ReturnMessages.NAME_NOT_BLANK)
//    @Size(min = 3, message = ReturnMessages.LENGTH_ERROR)
    private String skillName;
    @NotBlank(message = ReturnMessages.NAME_NOT_BLANK)
    @Size(min = 3, message = ReturnMessages.LENGTH_ERROR)
    private String userBadge;

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String interestName) {
        this.skillName = interestName;
    }

    public String getUserBadge() {
        return userBadge;
    }

    public void setUserBadge(String userBadge) {
        this.userBadge = userBadge;
    }
}