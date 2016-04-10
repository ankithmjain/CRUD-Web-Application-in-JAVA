/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.ajain62.mp2.web;

import edu.iit.sat.itmd4515.ajain62.mp2.model.Customer;
import edu.iit.sat.itmd4515.ajain62.mp2.model.DeleteCustomer;
import edu.iit.sat.itmd4515.ajain62.mp2.model.UpdateCustomer;
import edu.iit.sat.itmd4515.ajain62.mp2.service.CustomerService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

/**
 *
 * @author Ankith Jain
 */
@WebServlet(name = "CustomerServlet", urlPatterns = {"/jain", "/customer", "/readCustomer", "/updateCustomer", "/deleteCustomer"})
public class CustomerServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(CustomerServlet.class.getName());
    @Resource
    Validator validator;

    @EJB
    CustomerService svc;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        svc.findAll();
        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CustomerServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CustomerServlet at " + request.getContextPath() + "</h1>");

            out.println("<ol>");
            for (Customer c : svc.findAll()) {
                out.println("<li>" + c.toString() + "</li>");

            }

            out.println("</ol>");

            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOG.info("Inside doGet method. handling a dispatch to a patch ");
        switch (request.getServletPath()) {
            // Get request /readCustomer to view all customers
            case "/readCustomer":
                LOG.info(" Get request to /readCustomer page");
                request.setAttribute("customers", svc.findAll());
                LOG.info("Processed from db, now I will show output");
                request.getRequestDispatcher("/WEB-INF/pages/readCustomer.jsp").forward(request, response);
                break;
            // Get request /insert a customer     
            case "/customer":
                LOG.info("Get request to /InsertCustomer page");

                if (!WebUtil.isEmpty(request.getParameter("customerId"))) {
                    LOG.info("Checking User availability in database, I am in doGet Method");
                    Long customerId = Long.parseLong(WebUtil.trimParam(request.getParameter("customerId")));
                    Customer c = svc.findByID(customerId);
                    request.setAttribute("customer", c);

                }
                // get request /update customer
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/customer.jsp");
                dispatcher.forward(request, response);
                break;
            case "/updateCustomer":
                LOG.info("Get request to /updateCustomer page");
                // TODO check whether customer exists or not and then make a decision to add in database
                if (!WebUtil.isEmpty(request.getParameter("customerId"))) {
                    LOG.info("Checking User availability in database, I am in doGet Method of case updateCustomer");
                    Long customerId = Long.parseLong(WebUtil.trimParam(request.getParameter("customerId")));
                    Customer c = svc.findByID(customerId);
                    request.setAttribute("customer", c);
                }

                request.getRequestDispatcher("/WEB-INF/pages/updateCustomer.jsp").forward(request, response);
                break;
            case "/deleteCustomer":
                LOG.info("Get request to /deleteCustomer page");
                // TODO check whether customer exists or not and then make a decision to add in database
                if (!WebUtil.isEmpty(request.getParameter("customerId"))) {
                    LOG.info("Checking User availability in database, I am in doGet Method of case deleteCustomer");
                    Long customerId = Long.parseLong(WebUtil.trimParam(request.getParameter("customerId")));
                    Customer c = svc.findByID(customerId);
                    request.setAttribute("customer", c);
                }

                request.getRequestDispatcher("/WEB-INF/pages/deleteCustomer.jsp").forward(request, response);
                break;

        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        LOG.info("Inside doPost method. I am processing a form POST");

        Map<String, String> messages = new HashMap<>();
        request.setAttribute("messages", messages);
        switch (request.getServletPath()) {
            case "/customer":
                LOG.info("I am inside /customer do post method to insert customer");
                Long customerId = null;

                if (!WebUtil.isEmpty(request.getParameter("customerId"))) {
                    // needs additional checks to ensure it is actually Long and right format
                    customerId = Long.parseLong(WebUtil.trimParam(request.getParameter("customerId")));
                }

                String firstName = WebUtil.trimParam(request.getParameter("firstName"));
                String lastName = WebUtil.trimParam(request.getParameter("lastName"));
                String email = WebUtil.trimParam(request.getParameter("email"));

                Integer storeid = null;
                String storeIdStr = WebUtil.trimParam(request.getParameter("storeId"));
                if (storeIdStr != null && !storeIdStr.isEmpty()) {
                    storeid = Integer.parseInt(storeIdStr);
                }

                Integer addressid = null;
                String addressIdStr = WebUtil.trimParam(request.getParameter("addressId"));
                if (addressIdStr != null && !addressIdStr.isEmpty()) {
                    addressid = Integer.parseInt(addressIdStr);
                }

                Customer c = new Customer(firstName, lastName, email, storeid, addressid);
                //validatation 
                Set<ConstraintViolation<Customer>> violations = validator.validate(c);

                if (violations.isEmpty()) {

                    LOG.info("Received the following customer from user form:\t" + c.toString());
                    LOG.info("I am in doPost Method going to database for save method");

                    if (svc.save(c)) {
                        LOG.info("I am in doPost Method coming out from database after doing save method");
                        messages.put("success", "Successfully saved customer, I am in do Post Method");
                        request.setAttribute("customers", svc.findAll());
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/readCustomer.jsp");
                        dispatcher.forward(request, response);
                    }

                } else {
                    LOG.info("will pop violations for missing fields");
                    // if violations is not empty, our object failed validation    
                    LOG.info("There are" + violations.size() + "violations.");
                    for (ConstraintViolation<Customer> violation : violations) {
                        LOG.info("####### " + violation.getRootBeanClass().getSimpleName()
                                + "." + violation.getPropertyPath() + " failed violation:\t"
                                + violation.getInvalidValue() + " failed with message:\t"
                                + violation.getMessage());
                    }

                    // SetAttribute is one of the method of showing error messages to user by setting up at jsp pages
                    request.setAttribute("violations", violations);

                    request.setAttribute("customer", c);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/customer.jsp");
                    dispatcher.forward(request, response);

                }

                break;
            case "/updateCustomer":
                LOG.info("I am inside /updatecustomer do post method");
                Long ucustomerId = null;

                if (!WebUtil.isEmpty(request.getParameter("customerId"))) {
                    // needs additional checks to ensure it is actually Long and right format
                    ucustomerId = Long.parseLong(WebUtil.trimParam(request.getParameter("customerId")));
                }

                String ufirstName = WebUtil.trimParam(request.getParameter("firstName"));
                String ulastName = WebUtil.trimParam(request.getParameter("lastName"));
                String uemail = WebUtil.trimParam(request.getParameter("email"));

                UpdateCustomer uc = new UpdateCustomer(ucustomerId, ufirstName, ulastName, uemail);

                //validatation
                Set<ConstraintViolation<UpdateCustomer>> uviolations = validator.validate(uc);

                if (uviolations.isEmpty()) {

                    LOG.info("Received the following customer from user form:\t" + uc.toString());
                    LOG.info("I am in doPost Method going to database for save method");

                    if (svc.update(uc)) {
                        LOG.info("I am in doPost Method coming out from database after doing Update method");
                        messages.put("success", "Successfully Updated customer, I am in do Post Method");
                        request.setAttribute("customers", svc.findAll());
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/readCustomer.jsp");
                        dispatcher.forward(request, response);
                    }

                } else {
                    LOG.info("will pop violations for missing fields");
                    // if violations is not empty, our object failed validation    
                    LOG.info("There are" + uviolations.size() + "violations.");
                    for (ConstraintViolation<UpdateCustomer> violation : uviolations) {
                        LOG.info("####### " + violation.getRootBeanClass().getSimpleName()
                                + "." + violation.getPropertyPath() + " failed violation:\t"
                                + violation.getInvalidValue() + " failed with message:\t"
                                + violation.getMessage());
                    }

                    // SetAttribute is one of the method of showing error messages to user by setting up at jsp pages
                    request.setAttribute("violations", uviolations);
                    request.setAttribute("customer", uc);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/updateCustomer.jsp");
                    dispatcher.forward(request, response);

                }
                break;

            case "/deleteCustomer":
                LOG.info("I am inside /updatecustomer do post method");
                Long dcustomerId = null;

                if (!WebUtil.isEmpty(request.getParameter("customerId"))) {
                    // needs additional checks to ensure it is actually Long and right format
                    dcustomerId = Long.parseLong(WebUtil.trimParam(request.getParameter("customerId")));
                }

                DeleteCustomer dc = new DeleteCustomer(dcustomerId);

                //validation
                Set<ConstraintViolation<DeleteCustomer>> dviolations = validator.validate(dc);

                if (dviolations.isEmpty()) {

                    LOG.info("Received the following customer from user form:\t" + dc.toString());
                    LOG.info("I am in doPost Method going to database for delete method");

                    // do some processing here - call service or database
                    // TODO
                    if (svc.delete(dc)) {
                        LOG.info("I am in doPost Method coming out from database after doing Update method");
                        messages.put("success", "Successfully Updated customer, I am in do Post Method");
                        request.setAttribute("customers", svc.findAll());
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/readCustomer.jsp");
                        dispatcher.forward(request, response);
                    }

                } else {
                    LOG.info("will pop violations for missing fields");
                    // if violations is not empty, our object failed validation    
                    LOG.info("There are" + dviolations.size() + "violations.");
                    for (ConstraintViolation<DeleteCustomer> violation : dviolations) {
                        LOG.info("####### " + violation.getRootBeanClass().getSimpleName()
                                + "." + violation.getPropertyPath() + " failed violation:\t"
                                + violation.getInvalidValue() + " failed with message:\t"
                                + violation.getMessage());
                    }

                    // SetAttribute is one of the method of showing error messages to user by setting up at jsp pages
                    request.setAttribute("violations", dviolations);
                    request.setAttribute("customer", dc);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/deleteCustomer.jsp");
                    dispatcher.forward(request, response);

                }

                break;

        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
