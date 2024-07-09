/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuochg.bookinghotel.controllers.accounts;

import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import phuochg.bookinghotel.accounts.AccountDAO;
import phuochg.bookinghotel.accounts.AccountDTO;
import phuochg.bookinghotel.users.UserDAO;
import phuochg.bookinghotel.users.UserDTO;
import phuochg.bookinghotel.users.UserError;
import phuochg.bookinghotel.validation.CheckValidation;
import phuochg.bookinghotel.validation.encrypted;

/**
 *
 * @author Phước Hà
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

    private static final String REGISTER_PAGE = "registerPage.jsp";
    private static final String LOGIN_PAGE = "login.jsp";

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
        String url = REGISTER_PAGE;
        UserError userError = new UserError("", "", "", "", "", "");

        try {
            AccountDAO accountDAO = new AccountDAO();

            String userId = request.getParameter("userId");
            String password = request.getParameter("Password");
            String rePassword = request.getParameter("rePassword");
            String fullname = request.getParameter("fullName");
            String address = request.getParameter("address");
            String phoneNumber = request.getParameter("phoneNumber");
            boolean check = true;
            String msg = "";

            if (accountDAO.checkAccountExist(userId)) {
                check = false;
                userError.setUserIDError("The Account have been Taken!");
            }

            if (!CheckValidation.isValidEmailAddress(userId)) {
                check = false;
                userError.setUserIDError("Email must match !xxxx@xxxx.xxx!");
            }
            if (!CheckValidation.isValidPassword(password)) {
                check = false;
                userError.setPasswordError("Password must contain one spectify character!");
            }
            if (!rePassword.equals(password)) {
                check = false;
                userError.setConfirmPasswordError("RePassword must match the password");
            }
            if (fullname.length() < 1 && fullname.length() > 20) {
                check = false;
                userError.setNameError("Name valid in [1,20]!");
            }
            if (address.length() < 5) {
                check = false;
                userError.setAddress("Address can't less than 5 character!");
            }
            if (!CheckValidation.isValidPhoneNumber(phoneNumber)) {
                check = false;
                userError.setPhoneNumberError("Phone Number is Invalid");
            }

            if (check) {
                UserDAO userDAO = new UserDAO();
                AccountDTO acc = new AccountDTO(userId, encrypted.encryptedPassword(rePassword), 2, "Active");
                UserDTO user = new UserDTO(userId, fullname, address, phoneNumber);

                if (accountDAO.insertAccount(acc) && userDAO.insertUser(user)) {
                    url = LOGIN_PAGE;
                    msg = "Create Success! Login here!";
                    request.setAttribute("CREATE_MSG", msg);
                }

            } else {
                request.setAttribute("USER_ERROR", userError);
            }

        } catch (SQLException e) {
            log("Error SQL EXCEPTION AT RegisterServlet:" + e.toString());
        } catch (NamingException e) {
            log("Error Naming EXCEPTION AT RegisterServlet:" + e.toString());
        } catch (Exception e) {
            log("Error At RegisterServlet: " + e.toString());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
