package com.dell.DiscoveryYou.Entity;

import com.dell.DiscoveryYou.Utility.ReturnMessages;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collections;
import java.util.List;

@Entity
public class Interest {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = ReturnMessages.NAME_NOT_BLANK)
    private String name;

    @OneToMany(mappedBy = "interests")
    private List<User> users;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }


    public Interest(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Long getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Interest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", users=" + users +
                '}';
    }
}
