package com.ims.ogrenciyonetim.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Ogrenci {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Word dosyasındaki [Required] karşılığı:
    @NotEmpty(message = "Ad alanı boş bırakılamaz.")
    private String ad;

    // Word dosyasındaki [Required] karşılığı:
    @NotEmpty(message = "Soyad alanı boş bırakılamaz.")
    private String soyad;

    // Word dosyasındaki [Range(18, 40)] karşılığı:
    @NotNull(message = "Yaş alanı zorunludur.")
    @Min(value = 0, message = "en az 0 olablir")
    @Max(value = 999999999, message = "En fazla 9 haneli oalbilir")
    private int ogrenciNo;


    public Ogrenci() {
    }

    // İsteğe bağlı, tüm alanları alan Yapıcı Metot
    public Ogrenci(String ad, String soyad, int ogrenciNo) {
        this.ad = ad;
        this.soyad = soyad;
        this.ogrenciNo = ogrenciNo;
    }

    // ----------------------------------------------------
    // 2. GETTER (Okuma) ve SETTER (Yazma) METOTLARI
    // ----------------------------------------------------

    // ID
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Ad
    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    // Soyad
    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    // Öğrenci Numarası
    public int getOgrenciNo() {
        return ogrenciNo;
    }

    public void setOgrenciNo(int ogrenciNo) {
        this.ogrenciNo = ogrenciNo;
    }
}