package com.dell.DiscoveryYou.Request;

import com.dell.DiscoveryYou.Entity.User;
import com.dell.DiscoveryYou.Utility.ReturnMessages;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

public class CreateSkillDetailsRequestModel {

    private Long id;
    @NotBlank(message = ReturnMessages.NAME_NOT_BLANK)
    @Size(min = 3, message = ReturnMessages.LENGTH_ERROR)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}