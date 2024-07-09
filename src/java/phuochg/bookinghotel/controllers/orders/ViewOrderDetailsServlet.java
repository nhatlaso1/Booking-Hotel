/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuochg.bookinghotel.controllers.orders;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import phuochg.bookinghotel.accounts.AccountDTO;
import phuochg.bookinghotel.orderdetails.OrderDetailsDAO;
import phuochg.bookinghotel.orderdetails.OrderDetailsDTO;

/**
 *
 * @author Phước Hà
 */
@WebServlet(name = "ViewOrderDetailsServlet", urlPatterns = {"/ViewOrderDetailsServlet"})
public class ViewOrderDetailsServlet extends HttpServlet {

    private static final String VIEW_ORDERDETAIL_PAGE = "viewOrderDetails.jsp";
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
        String url = VIEW_ORDERDETAIL_PAGE;
        try {
            String msg = "";
            HttpSession session = request.getSession();
            AccountDTO acc = (AccountDTO) session.getAttribute("ACC");
            if (acc != null) {
                String orderId = request.getParameter("orderId");
                OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO();
                List<OrderDetailsDTO> listOrderDetails = orderDetailsDAO.listOrderDetailsByOrderId(orderId);
                if (listOrderDetails.size() == 0) {
                    msg = "You haven't book any Room!";
                } else {
                    msg = "Success!";
                }
                request.setAttribute("LIST_ORDERDETAILS", listOrderDetails);

            } else {
                url = LOGIN_PAGE;
                msg = "You Need Login To Process! this Request!";
            }

            request.setAttribute("ORDERDETAILS_MSG", msg);
        } catch (SQLException e) {
            log("Error SQL EXCEPTION AT View OrderDetailsServlet:" + e.toString());
        } catch (NamingException e) {
            log("Error Naming EXCEPTION AT View OrderDetailsServlet:" + e.toString());
        } catch (Exception e) {
            log("Error at View OrderDetailsServlet:" + e.toString());
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
