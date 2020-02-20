package com.dell.DiscoveryYou.Entity;

import com.dell.DiscoveryYou.Utility.ReturnMessages;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


@Entity
@Table(indexes = { @Index(name = "badgeIndex", columnList = "badge") })
public class User {

    /**
     * PROPERTIES
     */

//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_gen")
//    @SequenceGenerator(name = "user_id_gen", sequenceName = "user_id_seq")
//    @Column(name = "id", updatable = false, nullable = false)
//    private Long id;

    @Id
    @NotBlank(message = ReturnMessages.BADGE_NOT_BLANK)
    @Column(unique = true)
    private String badge;

    @NotBlank(message = ReturnMessages.FIRST_NAME_NOT_BLANK)
    private String firstName;

    @NotBlank(message = ReturnMessages.LAST_NAME_NOT_BLANK)
    private String lastName;

    @ManyToMany
    @JoinTable(name = "matches", indexes = @Index(columnList = "user_id"), joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "rel_user_id"))
    private List<User> matches;

    @ManyToMany
    @JoinTable(name = "connections", indexes = @Index(columnList = "user_id"), joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "rel_user_id"))
    @JsonManagedReference
    private List<User> connections;

    @ManyToMany
    @JoinTable(indexes = @Index(columnList = "user_id"), joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "interest_id"))
    @JsonIgnoreProperties("users")
    @Cascade(CascadeType.ALL)
    private List<Interest> interests;

    @ManyToMany
    @JoinTable(indexes = @Index(columnList = "user_id"), joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "skill_id"))
    @JsonIgnoreProperties("users")
    @Cascade(CascadeType.ALL)
    private List<Skill> skills;

    /**
     * CONSTRUCTORS
     */

    public User() {
        this.interests = new LinkedList<Interest>();
        this.skills = new LinkedList<Skill>();
    }

    /**
     * GETTERS
     */

//    public Long getId() {
//        return this.id;
//    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getBadge() {
        return this.badge;
    }

    public List<User> getMatches() { return this.matches; }

    public List<User> getConnections() { return this.connections; }

    public List<Interest> getInterests() {
        return this.interests;
    }

    public List<Skill> getSkills() {
        return Collections.unmodifiableList(skills);
    }

    /**
     * SETTERS
     */

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public void setMatches(List<User> matches) { this.matches = matches; }

    public void setConnections(List<User> collections) { this.connections = connections; }

    public void setInterests(List<Interest> interests) {
        this.interests = interests;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    /**
     * PERSISTENCE METHODS
     */

    public void insertMatch(User user) {
        this.matches.add(user);
        user.getMatches().add(this);
    }

    public void removeMatch(User user) {
        this.matches.remove(user);
        user.getMatches().remove(this);
    }

    public void insertConnection(User user) {
        this.connections.add(user);
        user.getConnections().add(this);
    }

    public void removeConnection(User user) {
        this.connections.remove(user);
        user.getConnections().remove(this);
    }

    public void insertInterest(Interest interest){
        this.interests.add(interest);
        interest.getUsers().add(this);
    }

    public void removeInterest(Interest interest){
        this.interests.remove(interest);
        interest.getUsers().remove(this);
    }

    public void insertSkill(Skill skill){
        this.skills.add(skill);
        skill.getUsers().add(this);
    }

    public void removeSkill(Skill skill){
        this.skills.remove(skill);
        skill.getUsers().remove(this);
    }

    /**
     * UTILITIES
     */

    @Override
    public String toString() {
        return "User{" +
//                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", badge='" + badge + '\'' +
                ", interests=" + interests +
                ", skills=" + skills +
                '}';
    }
}
