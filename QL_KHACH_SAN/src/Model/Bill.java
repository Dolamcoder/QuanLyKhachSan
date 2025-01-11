/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author Admin
 */
public class Bill {
    private String id, idKH, idPhong;
    private int idDP;
    private double chiPhi;
    Date ngayDat, ngayTra;
    private int thoiGianThue;
    private double giaThue;
    
    public Bill(String id, int idDP, double chiPhi) {
        this.id = id;
        this.idDP = idDP;
        this.chiPhi = chiPhi;
    }

    public String getId() {
        return id;
    }

    public int getIdDP() {
        return idDP;
    }

    public double getChiPhi() {
        return chiPhi;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIdDP(int idDP) {
        this.idDP = idDP;
    }

    public void setChiPhi(double chiPhi) {
        this.chiPhi = chiPhi;
    }

    public String getIdKH() {
        return idKH;
    }

    public String getIdPhong() {
        return idPhong;
    }

    public Date getNgayDat() {
        return ngayDat;
    }

    public Date getNgayTra() {
        return ngayTra;
    }

    public int getThoiGianThue() {
        return thoiGianThue;
    }

    public double getGiaThue() {
        return giaThue;
    }

    public void setIdKH(String idKH) {
        this.idKH = idKH;
    }

    public void setIdPhong(String idPhong) {
        this.idPhong = idPhong;
    }

    public void setNgayDat(Date ngayDat) {
        this.ngayDat = ngayDat;
    }

    public void setNgayTra(Date ngayTra) {
        this.ngayTra = ngayTra;
    }

    public void setThoiGianThue(int thoiGianThue) {
        this.thoiGianThue = thoiGianThue;
    }

    public void setGiaThue(double giaThue) {
        this.giaThue = giaThue;
    }
    public String getMonthAndYearOfNgayDat() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-yyyy"); // Định dạng tháng-năm
        return sdf.format(ngayDat); 
    }

}
