/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuochg.bookinghotel.accounts;

import java.io.Serializable;

/**
 *
 * @author Phước Hà
 */
public class AccountDTO implements Serializable {

    private String userId;
    private String password;
    private int roleId;
    private String status;

    public AccountDTO(String userId, String password, int roleId, String status) {
        this.userId = userId;
        this.password = password;
        this.roleId = roleId;
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
