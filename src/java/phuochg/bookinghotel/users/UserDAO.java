/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuochg.bookinghotel.users;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;
import phuochg.bookinghotel.utils.DBHelper;

/**
 *
 * @author Phước Hà
 */
public class UserDAO implements Serializable {

    public boolean insertUser(UserDTO user) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "insert into tblUser values(?,?,?,?,?);";
                pst = con.prepareStatement(sql);
                pst.setString(1, user.getUserId());
                pst.setString(2, user.getName());
                pst.setString(3, user.getAddress());
                pst.setString(4, user.getPhoneNumber());
                long milis = System.currentTimeMillis();
                Date date = new Date(milis);
                pst.setString(5, date.toString());
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
