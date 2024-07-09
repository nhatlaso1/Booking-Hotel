/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuochg.bookinghotel.controllers.orders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import phuochg.bookinghotel.accounts.AccountDTO;
import phuochg.bookinghotel.discountcodes.DiscountDTO;
import phuochg.bookinghotel.rooms.RoomDTO;
import phuochg.bookinghotel.validation.OrderUtils;

/**
 *
 * @author Phước Hà
 */
@WebServlet(name = "ViewCartServlet", urlPatterns = {"/ViewCartServlet"})
public class ViewCartServlet extends HttpServlet {

    private static final String VIEW_CART_PAGE = "viewCart.jsp";
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
        String url = VIEW_CART_PAGE;
        try {
            HttpSession session = request.getSession();
            AccountDTO accountDTO = (AccountDTO) session.getAttribute("ACC");
            String msg = "";
            if (accountDTO != null) {
                OrderUtils orderUtils = new OrderUtils();
                List<RoomDTO> listRoomDTOs = (List<RoomDTO>) session.getAttribute("LIST_CART");
                if (listRoomDTOs == null) {
                    listRoomDTOs = new ArrayList<>();
                    msg = "Nothing In list!";
                } else {
                    float total = orderUtils.calculateTotal(listRoomDTOs);
                    DiscountDTO discount = (DiscountDTO) session.getAttribute("DISCOUNT_CODE");
                    if (discount == null) {
                        session.setAttribute("TOTAL", total);
                    } else {
                        float discountTotal = orderUtils.calculatorDiscount(total, discount.getDiscountValue());
                        float newTotal = total - discountTotal;
                        session.setAttribute("TOTAL", newTotal);
                    }
                    session.setAttribute("LIST_CART", listRoomDTOs);
                }
                request.setAttribute("CART_MSG", msg);
            } else {
                msg = "You Need Login To Process This Request!";
                url = LOGIN_PAGE;
                request.setAttribute("CART_MSG", msg);
            }

        } catch (Exception e) {
            log("Error at ViewCartServlet: " + e.toString());
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
