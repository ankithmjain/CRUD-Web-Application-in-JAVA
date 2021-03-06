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
public class Customer {

    private Long id;
    @NotNull(message = "Customer First Name can not be null.")
    @Size(max = 45)
    private String firstName;
    @NotNull(message = "Customer Last Name can not be null.")
    @Size(max = 45)
    private String lastName;
    @Size(max = 50)
    private String email;
    @NotNull(message = "Store ID can not be null.")
    @Min(value = 0, message = "Store ID must be postivie and starting from 1 to 100")
    @Max(value = 100, message = "Store ID must be postivie and starting from 1 to 100")
    private Integer storeId;
    @NotNull(message = "addressId ID can not be null.")
    @Min(value = 0, message = "addressId ID must be postivie and starting from 1 to 100")
    @Max(value = 100, message = "addressId ID must be postivie and starting from 1 to 100")
    private Integer addressId;

    /**
     *
     * @param id
     * @param firstName
     * @param lastName
     * @param email
     */
    public Customer(Long id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    /**
     *
     * @param id
     * @param firstName
     * @param lastName
     * @param email
     * @param storeId
     * @param addressId
     */
    public Customer(Long id, String firstName, String lastName, String email, Integer storeId, Integer addressId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.storeId = storeId;
        this.addressId = addressId;
    }

    /**
     *
     * @param firstName
     * @param lastName
     * @param email
     * @param storeId
     * @param addressId
     */
    public Customer(String firstName, String lastName, String email, Integer storeId, Integer addressId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.storeId = storeId;
        this.addressId = addressId;
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
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the value of Email
     *
     *
     * @param email new value of email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the value of storeId
     *
     * @return the value of StoreId
     *
     */
    public Integer getStoreId() {
        return storeId;
    }

    /**
     * Set the value of storeId
     *
     * @param storeId
     */
    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    /**
     * Get the value if address
     *
     * @return the value of address
     */
    public Integer getAddressId() {
        return addressId;
    }

    /**
     * set the value of addressId
     *
     * @param addressId new value of addressId
     */
    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", storeId=" + storeId + ", addressId=" + addressId + '}';
    }

}
