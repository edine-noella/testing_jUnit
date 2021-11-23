package com.example.classajunit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String email;

    private String address;

    private String workPhone;

    private String homePhone;

    @Column(unique = true)
    private String mobilePhone;

    @Column(unique = true)
    private String twitterProfileUrl;

    @Column(unique = true)
    private String facebookProfileUrl;

    @Column(unique = true)
    private String linkedInProfileUrl;

    public Contact() {
    }

    public Contact(String firstName, String lastName, String mobilePhone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobilePhone = mobilePhone;
    }

    public Contact(String firstName, String lastName, String email, String address, String workPhone, String homePhone, String mobilePhone, String twitterProfileUrl, String facebookProfileUrl, String linkedInProfileUrl) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.workPhone = workPhone;
        this.homePhone = homePhone;
        this.mobilePhone = mobilePhone;
        this.twitterProfileUrl = twitterProfileUrl;
        this.facebookProfileUrl = facebookProfileUrl;
        this.linkedInProfileUrl = linkedInProfileUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getTwitterProfileUrl() {
        return twitterProfileUrl;
    }

    public void setTwitterProfileUrl(String twitterProfileUrl) {
        this.twitterProfileUrl = twitterProfileUrl;
    }

    public String getFacebookProfileUrl() {
        return facebookProfileUrl;
    }

    public void setFacebookProfileUrl(String facebookProfileUrl) {
        this.facebookProfileUrl = facebookProfileUrl;
    }

    public String getLinkedInProfileUrl() {
        return linkedInProfileUrl;
    }

    public void setLinkedInProfileUrl(String linkedInProfileUrl) {
        this.linkedInProfileUrl = linkedInProfileUrl;
    }
}
