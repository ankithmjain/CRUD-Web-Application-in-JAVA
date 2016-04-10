/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.ajain62.mp2.service;

import edu.iit.sat.itmd4515.ajain62.mp2.model.Customer;
import edu.iit.sat.itmd4515.ajain62.mp2.model.DeleteCustomer;
import edu.iit.sat.itmd4515.ajain62.mp2.model.UpdateCustomer;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

/**
 *
 * @author Ankith Jain
 */
@Stateless
public class CustomerService {

    private static final Logger LOG = Logger.getLogger(CustomerService.class.getName());

    @Resource(lookup = "jdbc/ajain62Mp2DS")
    private DataSource ds;

    /**
     *
     * @return List of Customers in Sakila DB
     */
    public List<Customer> findAll() {
        LOG.info("Inside find all method of DAO");
        List<Customer> customers = new ArrayList<>();

        try (Connection c = ds.getConnection()) {
            // JDBC has both Statement and PreparedStatement
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("select * from customer");
            while (rs.next()) {

                customers.add(new Customer(
                        rs.getLong("customer_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email")));

            }

        } catch (SQLException ex) {
            LOG.info("Sql Exception in showing data");
            LOG.log(Level.SEVERE, null, ex);

        }

        return customers;

    }

    /**
     * Find Customer by ID
     *
     * @param id the id to find in the customer table of the database
     * @return The customer found with customer_id matching the parameter id, or
     * null if no customer is found.
     *
     */
    public Customer findByID(Long id) {

        try (Connection c = ds.getConnection()) {

            PreparedStatement ps = c.prepareStatement("select * from customer where customer_id = ?");
            ps.setLong(1, id);
            LOG.info("Checking User availability in database, I am in DAO Customerservice ");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                return new Customer(
                        rs.getLong("customer_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email")
                //rs.getInt("storeId"),
                //rs.getInt("addressId")
                );

            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);

        }

// if nothing was found, return null
        return null;

    }

    /**
     * This will save customer to the database
     *
     * @param customer This Contains all details of customer inserted by used
     *
     * @return true if data insertion success or false if failed
     */
    public boolean save(Customer customer) {

        LOG.info("I will save the customer to the database");
        String insertSql = "insert into customer("
                + "customer_id,"
                + "first_name,"
                + "last_name,"
                + "email,"
                + "store_id,"
                + "address_id,"
                + "create_date)"
                + " values(?,?,?,?,?,?,?)";
        String insertSql2 = "insert into customer("
                + "first_name,"
                + "last_name,"
                + "email,"
                + "store_id,"
                + "address_id,"
                + "create_date)"
                + " values(?,?,?,?,?,?)";

        int returnVal = 0;
        try (Connection con = ds.getConnection()) {

            PreparedStatement ps = con.prepareStatement(insertSql2);
            LOG.info("Inserting new record " + customer.toString());

            ps = con.prepareStatement(insertSql2);
            LOG.info("your sql" + insertSql);
            LOG.info("your sql" + insertSql2);

            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());
            ps.setString(3, customer.getEmail());
            ps.setInt(4, customer.getStoreId());
            ps.setInt(5, customer.getAddressId());
            ps.setDate(6, Date.valueOf(LocalDate.now()));

            LOG.info("your sql" + insertSql);
            returnVal = ps.executeUpdate();

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);

        }
        return returnVal > 0;

    }

    /**
     *
     * @param uc contains the details to update related to customer
     * @return true if success else false
     */
    public boolean update(UpdateCustomer uc) {
        LOG.info("Came to Update Database");
        String updateSql = "update customer set "
                + "first_name=?, "
                + "last_name=?, "
                + "email=? "
                + "where customer_id=?";

        int returnVal = 0;

        try (Connection c = ds.getConnection()) {

            PreparedStatement ps = c.prepareStatement(updateSql);
            ps.setString(1, uc.getFirstName());
            ps.setString(2, uc.getLastName());
            ps.setString(3, uc.getEmail());
            ps.setLong(4, uc.getId());

            LOG.info("Updating a new record " + uc.toString());
            returnVal = ps.executeUpdate();

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            return false;
        }

        return returnVal > 0;
    }

    /**
     *
     * @param dc contains detail of customer id to delete
     * @return true if delete is successful
     */
    public boolean delete(DeleteCustomer dc) {
        int returnVal = 0;
        int rem1 = 0;
        int rem2 = 0;
        int rem3 = 0;
        LOG.info("Came to delete Database");
        LOG.info("Deleting a new record " + dc.toString());
        try (Connection c = ds.getConnection()) {

            PreparedStatement deleteCustomerRem1 = c.prepareStatement("delete from rental where customer_id = ?");
            deleteCustomerRem1.setLong(1, dc.getId());
            rem1 = deleteCustomerRem1.executeUpdate();

            PreparedStatement deleteCustomerRem2 = c.prepareStatement("delete  from payment where customer_id = ?");
            deleteCustomerRem2.setLong(1, dc.getId());
            rem2 = deleteCustomerRem2.executeUpdate();

            PreparedStatement deleteCustomerRem3 = c.prepareStatement("delete  from customer where customer_id = ?");
            deleteCustomerRem3.setLong(1, dc.getId());
            rem2 = deleteCustomerRem3.executeUpdate();

            LOG.info("Deleted in Database");

            LOG.info("Deleting a new record " + dc.toString());
            returnVal = deleteCustomerRem3.executeUpdate();

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            return false;
        }

        return true;
    }

}
