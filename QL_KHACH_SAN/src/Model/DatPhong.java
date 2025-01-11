/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class DatPhong {
    private String idPhong, idKH, name;
    private int idDP;
    Date ngayDat, ngayTra;
    private int thoiGianThue;
    private double giaThue;
    
    public DatPhong(int idDP, String idPhong, String idKH, String name, int thoiGianThue, double giaThue) {
        this.idDP=idDP;
        this.idPhong = idPhong;
        this.idKH=idKH;
        this.name = name;
        this.thoiGianThue = thoiGianThue;
        this.giaThue = giaThue;
    }
    public DatPhong(int idDP,String idPhong, String idKH, String name, double giaThue, Date ngayDat, Date ngayTra, int thoiGianThue) {
        this.idDP=idDP;
        this.idPhong = idPhong;
        this.idKH = idKH;
        this.name = name;
        this.ngayTra=ngayTra;
        this.ngayDat = ngayDat;
        this.thoiGianThue = thoiGianThue;
        this.giaThue = giaThue;
    }

    public DatPhong(String idPhong, String idKH, String name, int thoiGianThue, double giaThue) {
        this.idPhong = idPhong;
        this.idKH = idKH;
        this.name = name;
        this.thoiGianThue = thoiGianThue;
        this.giaThue = giaThue;
    }
    
    public DatPhong(){
        
    }
    public String getIdPhong() {
        return idPhong;
    }
    public void setNgayDat(Date ngayDat) {
        this.ngayDat = ngayDat;
    }

    public void setNgayTra(Date ngayTra) {
        this.ngayTra = ngayTra;
    }

    public Date getNgayTra() {
        return ngayTra;
    }

    public int getIdDP() {
        return idDP;
    }

    public void setIdDP(int idDP) {
        this.idDP = idDP;
    }

    
    public Date getNgayDat() {
        return ngayDat;
    }

    public String getName() {
        return name;
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

    
    public void setThoiGianThue(int thoiGianThue) {
        this.thoiGianThue = thoiGianThue;
    }

    public void setGiaThue(double giaThue) {
        this.giaThue = giaThue;
    }
    public double chiPhi(){
        return this.thoiGianThue*this.giaThue;
    }

    @Override
    public String toString() {
        return "DatPhong{" + "idPhong=" + idPhong + ", idKH=" + idKH + ", name=" + name + ", ngayDat=" + ngayDat + ", ngayTra=" + ngayTra + ", thoiGianThue=" + thoiGianThue + ", giaThue=" + giaThue + '}';
    }
    
}
