/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helper.JdbcHelper;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.TaiKhoan;
import Helper.UtilsHelper;
import java.sql.PreparedStatement;

/**
 *
 * @author ADMIN
 */
public class TaiKhoanDao {

    static Connection con = UtilsHelper.myConnection();

    public TaiKhoan readFromResultSet(ResultSet rs) throws SQLException {
        TaiKhoan model = new TaiKhoan();
        model.setTenTaiKhoan(rs.getString(1));
        model.setHoTen(rs.getString(2));
        model.setMatKhau(rs.getString(3));
        model.setEmail(rs.getString(4));
        model.setVaiTro(rs.getBoolean(5));
        return model;
    }

    public synchronized static List<TaiKhoan> HienThiHD() {
        List<TaiKhoan> list = new ArrayList<>();
        try {
            String sql = "select tentaikhoan,matkhau,hoten,email,vaitro,trangthai from taikhoan\n"
                    + "where trangthai='1' and an='1'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                TaiKhoan hd = new TaiKhoan();
                hd.setTenTaiKhoan(rs.getString(1));
                hd.setMatKhau(rs.getString(2));
                hd.setHoTen(rs.getString(3));
                hd.setEmail(rs.getString(4));
                hd.setVaiTro(rs.getBoolean(5));
                hd.setTrangThai(rs.getString(6));

                list.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public synchronized static List<TaiKhoan> HienThiKhongHD() {
        List<TaiKhoan> list = new ArrayList<>();
        try {
            String sql = "select tentaikhoan,matkhau,hoten,email,vaitro,trangthai from taikhoan\n"
                    + "where trangthai= 0 and an='1'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                TaiKhoan hd = new TaiKhoan();
                hd.setTenTaiKhoan(rs.getString(1));
                hd.setMatKhau(rs.getString(2));
                hd.setHoTen(rs.getString(3));
                hd.setEmail(rs.getString(4));
                hd.setVaiTro(rs.getBoolean(5));
                hd.setTrangThai(rs.getString(6));
                list.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    //th???c hi???n truy v???n l???y v??? 1 t???p ResultSet r???i ??i???n t???p ResultSet ???? v??o 1 List
    public List<TaiKhoan> select(String sql, Object... args) {
        List<TaiKhoan> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    list.add(readFromResultSet(rs));
                }
            } finally {
                rs.getStatement().getConnection().close();      //????ng k???t n???i t??? resultSet
            }
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
        return list;
    }

    public void doimk(TaiKhoan emiu) {
        String sql = "update TAIKHOAN set MATKHAU = ?\n"
                + "where EMAIL = ?";
        JdbcHelper.executeUpdate(sql,
                emiu.getMatKhau(),
                emiu.getEmail());

    }

    public TaiKhoan findByEmail(String email) {
        String sql = "select * from TAIKHOAN\n"
                + "where EMAIL = ?";
        List<TaiKhoan> list = select(sql, email);
        return list.size() > 0 ? list.get(0) : null;
    }

    public static void insert(TaiKhoan entity) {
        String sql = "INSERT INTO TAIKHOAN VALUES (?, ?, ?, ?, ?, ?, ?)";
        JdbcHelper.executeUpdate(sql,
                entity.getTenTaiKhoan(),
                entity.getMatKhau(),
                entity.getHoTen(),
                entity.getEmail(),
                entity.isVaiTro(),
                entity.getTrangThai(),
                entity.isAn());
    }
    //check Tr??ng gmail trong b???ng t??i kho???n.
    public  TaiKhoan checkTrungGmail(String Gmail) {
        String sql = "select * from taikhoan \n"
                + "where gmail = ? and trangthai=1";
        List<TaiKhoan> list = select(sql, Gmail);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    //Update t??i kho???n
    public static void update(TaiKhoan entity) {
        String sql = "UPDATE TAIKHOAN SET MatKhau=?, HoTen=?, VaiTro=? , EMAIL=?,an=?,trangthai=? WHERE TENTAIKHOAN=?";
        JdbcHelper.executeUpdate(sql,
                entity.getMatKhau(),
                entity.getHoTen(),
                entity.isVaiTro(),
                entity.getEmail(),
                entity.isAn(),
                entity.getTrangThai(),
                entity.getTenTaiKhoan());
    }
    
    //Check tr??ng kh??a ch??nh
    public  TaiKhoan checkTrungMa(String tenTaiKhoan) {
        String sql = "select * from taikhoan \n"
                + "where tentaikhoan = ? and trangthai=1";
        List<TaiKhoan> list = select(sql, tenTaiKhoan);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    //x??a t??i kho???n.Th???c ra l?? cho an = 0 ????? kh??ng c??n thao t??c ???????c n???a v???i t??i kho???n n??y
     public void delete(String tenTaiKhoan) {
        String sql = "UPDATE taikhoan\n"
                + "SET an = 0\n"
                + "where tentaikhoan = ?";
        JdbcHelper.executeUpdate(sql, tenTaiKhoan);
    }
     
    //t??m ki???m t??i kho???n 
    public synchronized static List<TaiKhoan> TimKiemMa(String ma) {
        List<TaiKhoan> list = new ArrayList<>();
        try {
            String sql = "select * from taikhoan\n"
                    + "where (tentaikhoan + hoten + email) like ?";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, "%" + ma + "%");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                TaiKhoan tk = new TaiKhoan();
                tk.setTenTaiKhoan(rs.getString(1));
                tk.setHoTen(rs.getString(2));
                tk.setMatKhau(rs.getString(3));
                tk.setEmail(rs.getString(4));
                tk.setVaiTro(rs.getBoolean(5));
                list.add(tk);
            }
        } catch (Exception e) {
        }
        return list;
    }
}
