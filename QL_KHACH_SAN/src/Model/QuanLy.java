package Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 
 */
public class QuanLy {
    private ArrayList<KhachHang> danhSachKH;
    // Constructor
    private ArrayList<Room> danhSachRoom;
    private ArrayList<DatPhong> danhSachDatPhong;
    private ArrayList<Bill> danhSachHoaDon;
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

    public void setDanhSachKH(ArrayList<KhachHang> danhSachKH) {
        this.danhSachKH = danhSachKH;
    }

    public void setDanhSachHoaDon(ArrayList<Bill> danhSachHoaDon) {
        this.danhSachHoaDon = danhSachHoaDon;
    }
    
    public ArrayList<Bill> getDanhSachHoaDon() {
        return danhSachHoaDon;
    }

    public void setDanhSachRoom(ArrayList<Room> danhSachRoom) {
        this.danhSachRoom = danhSachRoom;
    }

    public void setDanhSachDatPhong(ArrayList<DatPhong> danhSachDatPhong) {
        this.danhSachDatPhong = danhSachDatPhong;
    }

    public ArrayList<KhachHang> getDanhSachKH() {
        return danhSachKH;
    }

    public ArrayList<Room> getDanhSachRoom() {
        return danhSachRoom;
    }

    public ArrayList<DatPhong> getDanhSachDatPhong() {
        return danhSachDatPhong;
    }
    public ArrayList<Room> sapXepTheoBed(){
        Collections.sort(danhSachRoom, new Comparator<Room>(){
            @Override
            public int compare(Room o1, Room o2) {
               return o2.getBed()-o1.getBed();
            }
            
        });
        return danhSachRoom;
    }
   public ArrayList<Room> sapXepTheoFloor(){
       Collections.sort(danhSachRoom, new Comparator<Room>(){
           @Override
           public int compare(Room o1, Room o2) {
              return o2.getFloor()-o1.getFloor();
           }
       });
       return danhSachRoom;
   }
   public ArrayList<Room> sapXepTheoGia(){
       Collections.sort(danhSachRoom, new Comparator<Room>(){
           @Override
           public int compare(Room o1, Room o2) {
              return (int)(o2.getPrice()-o1.getPrice());
              
           }
       });
        return danhSachRoom;
   }
  public ArrayList<KhachHang> sapXepTheoTen() {
    Collections.sort(this.danhSachKH, new Comparator<KhachHang>() {
        @Override
        public int compare(KhachHang o1, KhachHang o2) {
            return o1.getName().compareToIgnoreCase(o2.getName());
        }
    });
    return this.danhSachKH; // Trả về danh sách đã sắp xếp
}
    public KhachHang timKiem(String id){
        for(KhachHang x: this.danhSachKH){
            if(x.getId().equals(id)) return x;
        }
        return null;
    }
   public KhachHang timKiemKhachHang(String id) {
    for (KhachHang kh : danhSachKH) {
        if (kh.getId().equals(id)) {
            return kh;
        }
    }
    System.out.println("Không tìm thấy khách hàng với ID: " + id);
    return null;
}
public DatPhong timKiemDatPhong(int key){
     for(DatPhong x: this.danhSachDatPhong){
         if(x.getIdDP()==key){
             return x;
         }
     }
     return null;
}
public Bill timKiemBill(String ma){
    for(Bill x:this.danhSachHoaDon){
        if(x.getId().equals(ma)){
            return x;
        }
    }
    return null;
}
}
