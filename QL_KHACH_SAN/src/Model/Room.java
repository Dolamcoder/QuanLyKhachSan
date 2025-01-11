/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class Room {
    private String idPhong;
    private int bed, floor;
    private boolean status;
    private double price;

    public Room(String idPhong, int bed, int floor, double price) {
        this.idPhong = idPhong;
        this.bed = bed;
        this.floor = floor;
        this.price = price;
        this.status=false;
    }

    public Room(String idPhong, int bed, int floor, boolean status, double price) {
        this.idPhong = idPhong;
        this.bed = bed;
        this.floor = floor;
        this.status = status;
        this.price = price;
    }

    public Room(int bed, int floor, boolean status, double price) {
        this.bed = bed;
        this.floor = floor;
        this.status = status;
        this.price = price;
    }
    
    public String getIdPhong() {
        return idPhong;
    }

    public int getBed() {
        return bed;
    }

    public int getFloor() {
        return floor;
    }

    public boolean isStatus() {
        return status;
    }

    public double getPrice() {
        return price;
    }

    public void setIdPhong(String idPhong) {
        this.idPhong = idPhong;
    }

    public void setBed(int bed) {
        this.bed = bed;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public String trangThai(){
        if(this.status){
            return "Sẵn Sàng";
        }
        return "Chưa Sẵn Sàng";
    }

   @Override
public String toString() {
    return
           "  Mã Phòng  : " + idPhong + "\n" +
           "  Số Giường : " + bed + "\n" +
           "  Số Tầng    : " + floor + "\n" +
           "  Tráng Thái : " + this.trangThai() + "\n" +
           "  Giá Thuê   : " + price + "\n" ;
}

    
}
