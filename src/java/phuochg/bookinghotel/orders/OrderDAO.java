/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuochg.bookinghotel.orders;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import phuochg.bookinghotel.utils.DBHelper;

/**
 *
 * @author Phước Hà
 */
public class OrderDAO implements Serializable {

    public boolean insertOrder(OrderDTO orderDTO) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "insert into tblOrder values(?,?,?,?,?)";
                pst = con.prepareStatement(sql);
                pst.setString(1, orderDTO.getOrderId());
                pst.setString(2, orderDTO.getUserId());
                pst.setString(3, orderDTO.getOrderDate());
                pst.setFloat(4, orderDTO.getTotal());
                pst.setBoolean(5, orderDTO.isStatus());
                if (pst.executeUpdate() > 0) {
                    return true;
                }
            }

        } finally {
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public List<OrderDTO> listOrderByName(String userId) throws NamingException, SQLException {
        List<OrderDTO> listOrder = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "Select orderId, userId, orderDate, total, status\n"
                        + "from tblOrder\n"
                        + "Where userId = ? and status = 1 \n"
                        + "order by orderDate";
                pst = con.prepareStatement(sql);
                pst.setString(1, userId);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String orderId = rs.getString("orderId");
                    String orderDate = rs.getString("orderDate");
                    Float total = rs.getFloat("total");
                    boolean status = rs.getBoolean("status");
                    OrderDTO order = new OrderDTO(orderId, userId, orderDate, total, status);
                    listOrder.add(order);
                }
            }

        } finally {
            if(rs != null){
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return listOrder;
    }

    public boolean updateOrderStatus(String orderId, boolean status) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "update tblOrder\n"
                        + "set status = ? \n"
                        + "where orderId = ? ";
                pst = con.prepareStatement(sql);
                pst.setBoolean(1, status);
                pst.setString(2, orderId);
                if (pst.executeUpdate() > 0) {
                    return true;
                }
            }

        } finally {
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

}
