package com.dell.DiscoveryYou.Request;

import com.dell.DiscoveryYou.Entity.User;
import com.dell.DiscoveryYou.Util.ReturnMessages;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

public class CreateInterestDetailsRequestModel {

    private Long id;
    @NotBlank(message = ReturnMessages.NAME_NOT_BLANK)
    @Size(min = 3, message = ReturnMessages.LENGTH_ERROR)
    private String name;
    private List<User> users;
    @Size(min = 1, max = 5, message = ReturnMessages.RANK_INVALID)
    private Byte rank;

    public Byte getRank() {
        return rank;
    }

    public void setRank(Byte rank) {
        this.rank = rank;
    }

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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
