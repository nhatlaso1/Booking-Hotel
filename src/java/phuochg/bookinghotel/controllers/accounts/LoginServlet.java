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
import javax.servlet.http.HttpSession;
import phuochg.bookinghotel.accounts.AccountDAO;
import phuochg.bookinghotel.accounts.AccountDTO;
import phuochg.bookinghotel.validation.encrypted;

/**
 *
 * @author Phước Hà
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private static final String LOGIN_PAGE = "login.jsp";
    private static final String HOME_PAGE_USER = "MainController?btnAction=";
    private static final String HOME_PAGE_ADMIN = "MainController?btnAction=homeForAdmin";

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
        String url = LOGIN_PAGE;
        try {
            String username = request.getParameter("Username");
            String password = request.getParameter("Password");

            AccountDAO accDao = new AccountDAO();
            AccountDTO accDto = accDao.login(username, encrypted.encryptedPassword(password));
            HttpSession session = request.getSession();
            String msg = "";
            if (accDto == null) {
                msg = "Your Username or Password is Invalid!";
            } else {
                session.setAttribute("ACC", accDto);
                if (accDto.getRoleId() == 1) {
                    url = HOME_PAGE_ADMIN;
                } else {
                    url = HOME_PAGE_USER;
                }
            }

            request.setAttribute("LOGIN_MSG", msg);

        } catch (SQLException e) {
            log("Error SQL EXCEPTION AT LOGINSERVLET:" + e.toString());
        } catch (NamingException e) {
            log("Error Naming EXCEPTION AT LOGINSERVLET:" + e.toString());
        } catch (Exception e) {
            log("Error AT LOGINSERVLET:" + e.toString());
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
