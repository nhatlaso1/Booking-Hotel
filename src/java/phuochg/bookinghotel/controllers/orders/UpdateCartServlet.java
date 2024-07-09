/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuochg.bookinghotel.controllers.orders;

import java.io.IOException;
import java.time.LocalDate;
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
import phuochg.bookinghotel.rooms.RoomDAO;
import phuochg.bookinghotel.rooms.RoomDTO;
import phuochg.bookinghotel.validation.OrderUtils;

/**
 *
 * @author Phước Hà
 */
@WebServlet(name = "UpdateCartServlet", urlPatterns = {"/UpdateCartServlet"})
public class UpdateCartServlet extends HttpServlet {

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
            AccountDTO acc = (AccountDTO) session.getAttribute("ACC");
            String msg = "";
            int roomQuantity = Integer.parseInt(request.getParameter("quantity"));
            int roomNo = Integer.parseInt(request.getParameter("roomNo"));
            int night = Integer.parseInt(request.getParameter("night"));
            List<RoomDTO> listRoom = (List<RoomDTO>) session.getAttribute("LIST_CART");
            String checkIn = request.getParameter("checkIn");
            String checkOut = request.getParameter("checkOut");

            if (acc != null) {

                RoomDAO roomDAO = new RoomDAO();
                int oldQuantity = roomDAO.getSubtractRoomQuantityWithDate(roomNo, checkIn.toString(), checkOut.toString());
                if (oldQuantity <= 0) {
                    oldQuantity = roomDAO.getRoomQuantity(roomNo);
                }
                if (roomQuantity >= oldQuantity || roomQuantity < 0) {
                    msg = "The Room you need Haven't enought Room For You! Sorry";
                } else {
                    OrderUtils orderUtils = new OrderUtils();
                    orderUtils.updateRoomNight(listRoom, roomNo, night, roomQuantity);
                    String checkInDate = orderUtils.getCheckInRoomDate(listRoom, roomNo);
                    LocalDate checkOutDate = LocalDate.parse(checkInDate).plusDays(night);
                    orderUtils.updateRoomCheckoutDate(listRoom, roomNo, "" + checkOutDate);
                    orderUtils.updateRoomTotal(listRoom, roomNo,
                            orderUtils.calculatePriceTotalRoomPrice(night, orderUtils.getRoomPrice(listRoom, roomNo), roomQuantity));
                    msg = "Update Success!";
                    float total = orderUtils.calculateTotal(listRoom);
                    DiscountDTO discount = (DiscountDTO) session.getAttribute("DISCOUNT_CODE");
                    if (discount == null) {
                        session.setAttribute("TOTAL", total);
                    } else {
                        float discountTotal = orderUtils.calculatorDiscount(total, discount.getDiscountValue());
                        float newTotal = total - discountTotal;
                        session.setAttribute("TOTAL", newTotal);
                    }
                }
                session.setAttribute("LIST_CART", listRoom);
            } else {
                url = LOGIN_PAGE;
                msg = "You need login to process this request!";
            }
            request.setAttribute("CART_MSG", msg);
        } catch (Exception e) {
            log("Error at UpdateCartServlet:" + e.toString());
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
