package com.dell.DiscoveryYou.Entity;

import com.dell.DiscoveryYou.Utility.ReturnMessages;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @NotBlank(message = ReturnMessages.FIRST_NAME_NOT_BLANK)
    private String firstName;
    @NotBlank(message = ReturnMessages.LAST_NAME_NOT_BLANK)
    private String lastName;
    @NotBlank(message = ReturnMessages.BADGE_NOT_BLANK)
    @Column(unique = true)
    private String badge;

    @OneToMany(mappedBy = "USERS")
    private List<Interest> interests;
    @OneToMany(mappedBy = "USERS")
    private List<Skill> skills;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", badge='" + badge + '\'' +
                ", interests=" + interests +
                ", skills=" + skills +
                '}';
    }

    public User() {
        this.interests = new LinkedList<Interest>();
        this.skills = new LinkedList<Skill>();
    }

    public List<Interest> getInterests() {
        return Collections.unmodifiableList(this.interests);
    }

    public void insertInterest(Interest interest){
        this.interests.add(interest);
    }

    public void removeInterest(Interest interest){
        this.interests.remove(interest);
    }

    public void setInterests(List<Interest> interests) {
        this.interests = interests;
    }

    public List<Skill> getSkills() {
        return Collections.unmodifiableList(skills);
    }

    public void insertSkill(Skill skill){
        this.skills.add(skill);
    }

    public void removeSkill(Skill skill){
        this.skills.remove(skill);
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }


    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
