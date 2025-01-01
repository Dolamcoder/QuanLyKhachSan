package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class QuanLy {
    private List<KhachHang> danhSachKH;
    // Constructor
    private List<Room> danhSachRoom;
    private List<DatPhong> danhSachDatPhong;
    public QuanLy(){
        danhSachKH=new ArrayList<>();
        danhSachRoom=new ArrayList<>();
        danhSachDatPhong=new ArrayList<>();
    }
    public Room timKiemPhong(String id){
        for(Room x: danhSachRoom){
            if(x.getIdPhong().equals(id)){
                return x;
            }
        }
        return null;
    }

    public void setDanhSachKH(List<KhachHang> danhSachKH) {
        this.danhSachKH = danhSachKH;
    }

    public void setDanhSachRoom(List<Room> danhSachRoom) {
        this.danhSachRoom = danhSachRoom;
    }

    public void setDanhSachDatPhong(List<DatPhong> danhSachDatPhong) {
        this.danhSachDatPhong = danhSachDatPhong;
    }

    public List<KhachHang> getDanhSachKH() {
        return danhSachKH;
    }

    public List<Room> getDanhSachRoom() {
        return danhSachRoom;
    }

    public List<DatPhong> getDanhSachDatPhong() {
        return danhSachDatPhong;
    }
    
}
