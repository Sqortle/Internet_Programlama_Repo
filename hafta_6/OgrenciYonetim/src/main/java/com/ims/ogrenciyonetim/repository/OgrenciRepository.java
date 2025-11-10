package com.ims.ogrenciyonetim.repository;

import com.ims.ogrenciyonetim.model.Ogrenci;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OgrenciRepository extends JpaRepository<Ogrenci, Long> {
    // Tüm CRUD (Create, Read, Update, Delete) metotları bu miras sayesinde otomatik gelir.
}