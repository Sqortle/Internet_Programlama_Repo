package com.ims.ogrenciyonetim.model;


public class Ogrenci {


    private Long id;

    private String ad;
    private String soyad;
    private int ogrenciNo;

    // --- Elle Yazılan GETTER ve SETTER Metotları ---

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }
    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }
    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    // Hata veren alanın metotları
    public int getOgrenciNo() {
        return ogrenciNo;
    }
    public void setOgrenciNo(int ogrenciNo) {
        this.ogrenciNo = ogrenciNo;
    }
    // ----------------------------------------------------
}