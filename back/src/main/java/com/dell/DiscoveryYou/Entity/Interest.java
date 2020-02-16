package com.dell.DiscoveryYou.Entity;

import com.dell.DiscoveryYou.Utility.ReturnMessages;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Interest {

    public Interest() {}

    public Interest(String name) {
        this.name = name;
        this.users = new ArrayList<User>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "interest_id_gen")
    @SequenceGenerator(name = "interest_id_gen",sequenceName = "interest_id_seq")
    private Long id;

    @NotBlank(message = ReturnMessages.NAME_NOT_BLANK)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<User> users;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() { return this.users; }

    public void setUsers(List<User> users) {
        this.users = users;
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
