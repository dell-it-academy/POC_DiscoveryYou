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

    /**
     * PROPERTIES
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "skill_id_gen")
    @SequenceGenerator(name = "skill_id_gen", sequenceName = "skill_id_seq")
    @Column(name = "skill_id")
    private Long id;

    @NotBlank(message = ReturnMessages.NAME_NOT_BLANK)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<User> users;


    /**
     * CONSTRUCTORS
     */

    public Skill() {}

    public Skill(String name) {
        this.name = name;
        this.users = new ArrayList<User>();
    }

    /**
     * GETTERS
     */

    public Long getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public List<User> getUsers() {
        return this.users;
    }

    /**
     * SETTERS
     */

    public void setId(Long id) { this.id = id; }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    /**
     * UTILITIES
     */

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", users=" + users +
                ", name='" + name + '\'' +
                '}';
    }
}
