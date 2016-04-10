/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.ajain62.mp2.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Ankith Jain
 */
public class UpdateCustomer {

    @NotNull(message = "Customer ID can not be null.")

    private Long id;
    @NotNull(message = "Customer First Name can not be null.")
    @Size(max = 45)
    private String firstName;
    @NotNull(message = "Customer Last Name can not be null.")
    @Size(max = 45)
    private String lastName;
    @Size(max = 50)
    private String email;

    /**
     *
     * @param id
     * @param firstName
     * @param lastName
     * @param email
     */
    public UpdateCustomer(Long id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    /**
     * Get the value of lastName
     *
     * @return the value of lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the value of lastName
     *
     * @param lastName new value of lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get the value of firstName
     *
     * @return the value of firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the value of firstName
     *
     * @param firstName new value of firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the value of Email
     *
     * @return the value of Email
     *
     * @return
     *
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the value of Email
     *
     * @param email new value of email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UpdateCustomer{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + '}';
    }

}
