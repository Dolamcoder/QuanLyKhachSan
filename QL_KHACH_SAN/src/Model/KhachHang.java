/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class KhachHang {
    private String name, cccd, diaChi, sdt, idPhong, id;

    public KhachHang(String name, String id, String cccd, String diaChi, String sdt) {
        this.name = name;
        this.id=id;
        this.cccd = cccd;
        this.diaChi = diaChi;
        this.sdt = sdt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getCccd() {
        return cccd;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getIdPhong() {
        return idPhong;
    }

    public void setIdPhong(String idPhong) {
        this.idPhong = idPhong;
    }
    
}
