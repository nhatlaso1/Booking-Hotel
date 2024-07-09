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
import phuochg.bookinghotel.orders.OrderDAO;
import phuochg.bookinghotel.rooms.RoomDAO;

/**
 *
 * @author Phước Hà
 */
@WebServlet(name = "DeleteOrderServlet", urlPatterns = {"/DeleteOrderServlet"})
public class DeleteOrderServlet extends HttpServlet {

    private static final String ORDER_PAGE = "viewOrder.jsp";
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
        String url = ORDER_PAGE;
        try {
            HttpSession session = request.getSession();
            AccountDTO acc = (AccountDTO) session.getAttribute("ACC");
            String msg = "";

            if (acc != null) {
                String orderId = request.getParameter("orderId");

                OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO();
                RoomDAO roomDAO = new RoomDAO();
                List<OrderDetailsDTO> listOrderDetail = orderDetailsDAO.listOrderDetailsByOrderId(orderId);            
                OrderDAO orderDAO = new OrderDAO();
                if (orderDAO.updateOrderStatus(orderId, false)) {
                    msg = "Delete Success!";
                }

                request.setAttribute("LIST_ORDER", orderDAO.listOrderByName(acc.getUserId()));
            } else {
                msg = "You Need To Login to Process this request!";
                url = LOGIN_PAGE;
            }
            request.setAttribute("VIEWORDER_MSG", msg);

        } catch (SQLException e) {
            log("Error SQL EXCEPTION AT DeleteOrderServlet:" + e.toString());
        } catch (NamingException e) {
            log("Error Naming EXCEPTION AT DeleteOrderServlet:" + e.toString());
        } catch (Exception e) {
            log("Error at DeleteOrderServlet:" + e.toString());
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
