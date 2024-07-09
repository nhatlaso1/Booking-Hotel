/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuochg.bookinghotel.feedbacks;

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
public class FeedBackDAO implements Serializable {

    public boolean insertFeedBack(FeedBackDTO feedBackDTO) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "insert into tblFeedBack values(?,?,?)";
                pst = con.prepareStatement(sql);
                pst.setInt(1, feedBackDTO.getRoomNo());
                pst.setString(2, feedBackDTO.getUserId());
                pst.setInt(3, feedBackDTO.getValue());
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

    public boolean checkUserIdFeedBacked(int roomNo, String userId) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "    Select roomNo, userId, value \n"
                        + "from tblFeedBack \n"
                        + "where roomNo = ? and userId = ?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, roomNo);
                pst.setString(2, userId);
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

    public List<FeedBackDTO> getListFeedBack(int roomNo) throws NamingException, SQLException {
        List<FeedBackDTO> listFeedBackDTOs = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "Select roomNo, userId, value \n"
                        + "from tblFeedBack\n"
                        + "where roomNo = ? ";
                pst = con.prepareStatement(sql);
                pst.setInt(1, roomNo);
                rs = pst.executeQuery();

                while (rs.next()) {
                    String userId = rs.getString("userId");
                    int value = rs.getInt("value");
                    FeedBackDTO feedBackDTO = new FeedBackDTO(roomNo, userId, value);
                    listFeedBackDTOs.add(feedBackDTO);
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
        return listFeedBackDTOs;

    }

}
