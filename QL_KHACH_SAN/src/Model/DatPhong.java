/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDate;

/**
 *
 * @author Admin
 */
public class DatPhong {
    private String idPhong, idKH, name;
    LocalDate ngayDat=LocalDate.now();
    private int thoiGianThue;
    private double giaThue;

    public DatPhong(String idPhong, String idKH, String name, int thoiGianThue, double giaThue) {
        this.idPhong = idPhong;
        this.idKH=idKH;
        this.name = name;
        this.thoiGianThue = thoiGianThue;
        this.giaThue = giaThue;
    }

    public String getIdPhong() {
        return idPhong;
    }

   

    public String getName() {
        return name;
    }

    public LocalDate getNgayDat() {
        return ngayDat;
    }

    public int getThoiGianThue() {
        return thoiGianThue;
    }

    public double getGiaThue() {
        return giaThue;
    }

    public void setIdPhong(String idPhong) {
        this.idPhong = idPhong;
    }

    public String getIdKH() {
        return idKH;
    }

    public void setIdKH(String idKH) {
        this.idKH = idKH;
    }

   
    public void setName(String name) {
        this.name = name;
    }

    public void setNgayDat(LocalDate ngayDat) {
        this.ngayDat = ngayDat;
    }

    public void setThoiGianThue(int thoiGianThue) {
        this.thoiGianThue = thoiGianThue;
    }

    public void setGiaThue(double giaThue) {
        this.giaThue = giaThue;
    }
    public LocalDate ngayTra(){
        return this.ngayDat.plusDays(thoiGianThue);
    }
}
