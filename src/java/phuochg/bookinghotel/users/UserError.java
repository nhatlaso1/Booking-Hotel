/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuochg.bookinghotel.users;

/**
 *
 * @author Phước Hà
 */
public class UserError {

    private String UserIDError;
    private String passwordError;
    private String confirmPasswordError;
    private String NameError;
    private String PhoneNumberError;
    private String Address;

    public UserError(String UserIDError, String passwordError, String confirmPasswordError, String NameError, String PhoneNumberError, String Address) {
        this.UserIDError = UserIDError;
        this.passwordError = passwordError;
        this.confirmPasswordError = confirmPasswordError;
        this.NameError = NameError;
        this.PhoneNumberError = PhoneNumberError;
        this.Address = Address;
    }

    public String getUserIDError() {
        return UserIDError;
    }

    public void setUserIDError(String UserIDError) {
        this.UserIDError = UserIDError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getConfirmPasswordError() {
        return confirmPasswordError;
    }

    public void setConfirmPasswordError(String confirmPasswordError) {
        this.confirmPasswordError = confirmPasswordError;
    }

    public String getNameError() {
        return NameError;
    }

    public void setNameError(String NameError) {
        this.NameError = NameError;
    }

    public String getPhoneNumberError() {
        return PhoneNumberError;
    }

    public void setPhoneNumberError(String PhoneNumberError) {
        this.PhoneNumberError = PhoneNumberError;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

}
