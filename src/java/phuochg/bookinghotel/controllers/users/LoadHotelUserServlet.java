/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuochg.bookinghotel.controllers.users;

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
import phuochg.bookinghotel.hotels.HotelDAO;
import phuochg.bookinghotel.hotels.HotelDTO;
import phuochg.bookinghotel.rooms.RoomDAO;
import phuochg.bookinghotel.rooms.RoomDTO;
import phuochg.bookinghotel.validation.HotelUtils;
import phuochg.bookinghotel.validation.OrderUtils;

/**
 *
 * @author Phước Hà
 */
@WebServlet(name = "LoadHotelUserServlet", urlPatterns = {"/LoadHotelUserServlet"})
public class LoadHotelUserServlet extends HttpServlet {

    private static final String HOME_PAGE_USER = "homeForUser.jsp";

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
        try {
            HotelDAO hotelDAO = new HotelDAO();
            
            List<HotelDTO> listHotel = hotelDAO.loadListHotel();

            RoomDAO roomDAO = new RoomDAO();
            HotelUtils hotelUtils = new HotelUtils();

            HttpSession session = request.getSession();
            List<RoomDTO> listRoom = null;
            OrderUtils orderUtils = new OrderUtils();
            if (session.getAttribute("CLICK_SEARCH") == null) {
                for (int i = 0; i < listHotel.size(); i++) {
                   
                   listRoom = roomDAO.searchListRoom(listHotel.get(i).getHotelId());
                    listHotel.get(i).setAvailable(hotelUtils.getAvailableHotel(listRoom));
                }
            } else {
                String checkIn = (String) session.getAttribute("CHECKIN_DATE");
                String checkOut = (String) session.getAttribute("CHECKOUT_DATE");
                for (int i = 0; i < listHotel.size(); i++) {
                   listRoom = roomDAO.searchListRoom2(listHotel.get(i).getHotelId(), checkIn, checkOut);
                    listHotel.get(i).setAvailable(hotelUtils.getAvailableHotel(listRoom));
                }
            }
            
            

            request.setAttribute("LIST_HOTEL", listHotel);

        } catch (SQLException e) {
            log("Error SQL EXCEPTION AT CheckDiscountCodeServlet:" + e.toString());
        } catch (NamingException e) {
            log("Error Naming EXCEPTION AT CheckDiscountCodeServlet:" + e.toString());
        } catch (Exception e) {
            log("Error AT LOADSERVLET:" + e.toString());
        } finally {
            RequestDispatcher rd= request.getRequestDispatcher(HOME_PAGE_USER);
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
