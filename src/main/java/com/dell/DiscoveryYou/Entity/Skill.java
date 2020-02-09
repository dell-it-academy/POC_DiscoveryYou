package com.dell.DiscoveryYou.Entity;

import com.dell.DiscoveryYou.Utility.ReturnMessages;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.List;

@Entity
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "skills")
    private List<User> users;

    @NotBlank(message = ReturnMessages.NAME_NOT_BLANK)
    private String name;

    @Size(min = 1, max = 5)
    private byte rank;

    public Skill(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRank(byte rank) {
        this.rank = rank;
    }

    public Skill(String name, byte rank) {
        this.name = name;
        this.rank = rank;
    }

    public String getName() {
        return this.name;
    }

    public byte getRank() {
        return this.rank;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", users=" + users +
                ", name='" + name + '\'' +
                ", rank=" + rank +
                '}';
    }
}
