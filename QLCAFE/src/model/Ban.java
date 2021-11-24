/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Doanh
 */
public class Ban {
    private int MaBan;
private String TenBan;
private int MaKhuVuc;
private boolean TrangThai;

    public Ban() {
    }

    public Ban(int MaBan, String TenBan, int MaKhuVuc, boolean TrangThai) {
        this.MaBan = MaBan;
        this.TenBan = TenBan;
        this.MaKhuVuc = MaKhuVuc;
        this.TrangThai = TrangThai;
    }

    public int getMaBan() {
        return MaBan;
    }

    public void setMaBan(int MaBan) {
        this.MaBan = MaBan;
    }

    public String getTenBan() {
        return TenBan;
    }

    public void setTenBan(String TenBan) {
        this.TenBan = TenBan;
    }

    public int getMaKhuVuc() {
        return MaKhuVuc;
    }

    public void setMaKhuVuc(int MaKhuVuc) {
        this.MaKhuVuc = MaKhuVuc;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    @Override
    public String toString() {
        return "Ban{" + "MaBan=" + MaBan + ", TenBan=" + TenBan + ", MaKhuVuc=" + MaKhuVuc + ", TrangThai=" + TrangThai + '}';
    }

}
