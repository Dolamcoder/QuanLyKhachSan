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
    private boolean status;
    public KhachHang(String name, String id,String idPhong, String cccd, String diaChi, String sdt) {
        this.name = name;
        this.id=id;
        this.idPhong=idPhong;
        this.cccd = cccd;
        this.diaChi = diaChi;
        this.sdt = sdt;
    }

    public KhachHang(String id, String name, String cccd, String diaChi, String sdt) {
        this.id=id;
        this.name = name;
        this.cccd = cccd;
        this.diaChi = diaChi;
        this.sdt = sdt;
    }
    public KhachHang(String name, String id, String idPhong, String cccd, String diaChi, String sdt, boolean status){
        this.name=name;
        this.id=id;
        this.idPhong=idPhong;
        this.cccd=cccd;
        this.diaChi=diaChi;
        this.sdt=sdt;
        this.status=status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
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
    public String trangThai(){
        if(this.status) return "Đã Trả Phòng";
        return "Chưa Trả Phòng";
    }
    public void setIdPhong(String idPhong) {
        this.idPhong = idPhong;
    }

   @Override
public String toString() {
    return
           "  Tên Khách Hàng : " + name + "\n" +
           "  CCCD : " + cccd + "\n" +
           "  Địa Chỉ : " + diaChi + "\n" +
           "  Số Điện Thoại : " + sdt + "\n" +
           "  Mã Khách Hàng  : " + id + "\n" +
           "  Trạng Thái : Khách hàng củ" +  "\n";
}

    
}
