/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuochg.bookinghotel.accounts;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import phuochg.bookinghotel.utils.DBHelper;

/**
 *
 * @author Phước Hà
 */
public class AccountDAO implements Serializable {

    public AccountDTO login(String username, String Password) throws NamingException, SQLException {
        AccountDTO acc = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "Select userId, Password, RoleId, Status\n"
                        + "From tblAccount\n"
                        + "Where userId = ? and Password = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, username);
                pst.setString(2, Password);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String email = rs.getString("userId");
                    String password = rs.getString("Password");
                    int roleId = rs.getInt("RoleId");
                    String status = rs.getString("Status");
                    acc = new AccountDTO(email, password, roleId, status);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }

        }
        return acc;
    }

    public boolean insertAccount(AccountDTO acc) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "insert into tblAccount values(?,?,?,?);";
                pst = con.prepareStatement(sql);
                pst.setString(1, acc.getUserId());
                pst.setString(2, acc.getPassword());
                pst.setInt(3, acc.getRoleId());
                pst.setString(4, acc.getStatus());
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

    public boolean checkAccountExist(String userId) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "Select userId\n"
                        + "from tblAccount\n"
                        + "Where userId = ? ";
                pst = con.prepareStatement(sql);
                pst.setString(1, userId);
                rs = pst.executeQuery();
                if (rs.next()) {
                    return true;
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
        return false;
    }

}
