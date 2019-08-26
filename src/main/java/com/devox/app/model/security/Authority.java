package com.devox.app.model.security;

import com.devox.app.model.Entities.ReadingCost;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "AUTHORITY")
public class Authority {

    @Id
    @Column(name = "ID" , nullable=false)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authority_seq")
//    @SequenceGenerator(name = "authority_seq", sequenceName = "authority_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "NAME",nullable=false, length=255)
    @NotNull
    @Enumerated(EnumType.STRING)
    @NaturalId
    private AuthorityName name;

    @JsonIgnore
    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
    private List<User> users;
    //bi-directional many-to-one association to ReadingCost
    @OneToMany(mappedBy="authority")
    private List<ReadingCost> readingCosts;
    public Authority() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AuthorityName getName() {
        return name;
    }

    public void setName(AuthorityName name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }


    public List<ReadingCost> getReadingCosts() {
        return this.readingCosts;
    }

    public void setReadingCosts(List<ReadingCost> readingCosts) {
        this.readingCosts = readingCosts;
    }

    public ReadingCost addReadingCost(ReadingCost readingCost) {
        getReadingCosts().add(readingCost);
        readingCost.setAuthority(this);

        return readingCost;
    }

    public ReadingCost removeReadingCost(ReadingCost readingCost) {
        getReadingCosts().remove(readingCost);
        readingCost.setAuthority(null);

        return readingCost;
    }
}
