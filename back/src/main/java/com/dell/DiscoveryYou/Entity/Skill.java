package com.dell.DiscoveryYou.Entity;

import com.dell.DiscoveryYou.Utility.ReturnMessages;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Entity
public class Skill {

    public Skill() {}

    public Skill(String name) {
        this.name = name;
        this.users = new ArrayList<User>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "skill_id_gen")
    @SequenceGenerator(name = "skill_id_gen", sequenceName = "skill_id_seq")
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<User> users;

    @NotBlank(message = ReturnMessages.NAME_NOT_BLANK)
    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<User> getUsers() { return this.users; }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", users=" + users +
                ", name='" + name + '\'' +
                '}';
    }
}
