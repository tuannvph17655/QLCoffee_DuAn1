/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author BlackBoy
 */
public class DoanhThu {
    private String nam;
    private float doanhThu;

    public DoanhThu() {
    }

    public DoanhThu(String nam, float doanhThu) {
        this.nam = nam;
        this.doanhThu = doanhThu;
    }

    public String getNam() {
        return nam;
    }

    public void setNam(String nam) {
        this.nam = nam;
    }

    public float getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(float doanhThu) {
        this.doanhThu = doanhThu;
    }

    @Override
    public String toString() {
        return "doanhThu{" + "nam=" + nam + ", doanhThu=" + doanhThu + '}';
    }
}
