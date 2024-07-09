/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuochg.bookinghotel.rooms;

import java.io.Serializable;

/**
 *
 * @author Phước Hà
 */
public class RoomDTO implements Serializable {
//    Select hotelId, roomNo, roomName, availableDate, quantity, typeId, roomPrice
//from tblRoom
//Where hotelId = 0

    private int hotelId;
    private String hotelName;
    private int roomNo;
    private String roomName;
    private String availableDate;
    private int quantity;
    private String typeId;
    private float price;
    private int night;
    private String checkInDate;
    private String checkOutDate;
    private float total;

    public RoomDTO(int hotelId, int roomNo, String roomName, String availableDate, int quantity, String typeId, float price) {
        this.hotelId = hotelId;
        this.roomNo = roomNo;
        this.roomName = roomName;
        this.availableDate = availableDate;
        this.quantity = quantity;
        this.typeId = typeId;
        this.price = price;
    }

    public RoomDTO(int hotelId, String hotelName, int roomNo, String roomName, String typeId, float price, int night) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.roomNo = roomNo;
        this.roomName = roomName;
        this.typeId = typeId;
        this.price = price;
        this.night = night;
    }

    public RoomDTO(int hotelId, String hotelName, int roomNo, String roomName,
            String availableDate, int quantity, String typeId, float price, int night, String checkInDate, String checkOutDate, float total) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.roomNo = roomNo;
        this.roomName = roomName;
        this.availableDate = availableDate;
        this.quantity = quantity;
        this.typeId = typeId;
        this.price = price;
        this.night = night;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.total = total;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public int getNight() {
        return night;
    }

    public void setNight(int night) {
        this.night = night;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getAvailableDate() {
        return availableDate;
    }

    public void setAvailableDate(String availableDate) {
        this.availableDate = availableDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}
