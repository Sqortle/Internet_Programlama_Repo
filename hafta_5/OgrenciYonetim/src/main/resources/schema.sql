-- src/main/resources/schema.sql

-- Uygulama her ba?lat?ld???nda tabloyu olu?turur
CREATE TABLE IF NOT EXISTS OGRENCI (
                                       id          BIGINT PRIMARY KEY AUTO_INCREMENT,
                                       ad          VARCHAR(255) NOT NULL,
    soyad       VARCHAR(255) NOT NULL,
    ogrenci_no  INT NOT NULL
    );