package com.ims.ogrenciyonetim.repository;

import com.ims.ogrenciyonetim.model.Ogrenci;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class OgrenciRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Ogrenci> ogrenciRowMapper = new RowMapper<Ogrenci>() {
        @Override
        public Ogrenci mapRow(ResultSet rs, int rowNum) throws SQLException {
            Ogrenci ogrenci = new Ogrenci();
            ogrenci.setId(rs.getLong("id"));
            ogrenci.setAd(rs.getString("ad"));
            ogrenci.setSoyad(rs.getString("soyad"));
            // SQL'deki sütun adı: ogrenci_no | Java'daki alan adı: ogrenciNo
            ogrenci.setOgrenciNo(rs.getInt("ogrenci_no"));
            return ogrenci;
        }
    };


    // 1. R (Read) - Tümünü Bulma
    public List<Ogrenci> findAll() {
        String sql = "SELECT id, ad, soyad, ogrenci_no FROM OGRENCI";
        return jdbcTemplate.query(sql, ogrenciRowMapper);
    }

    // 2. R (Read) - ID ile Bulma
    public Optional<Ogrenci> findById(Long id) {
        String sql = "SELECT id, ad, soyad, ogrenci_no FROM OGRENCI WHERE id = ?";
        try {
            Ogrenci ogrenci = jdbcTemplate.queryForObject(sql, ogrenciRowMapper, id);
            return Optional.ofNullable(ogrenci);
        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    // 3. C (Create) ve U (Update) - Kaydetme
    public Ogrenci save(Ogrenci ogrenci) {
        if (ogrenci.getId() == null || ogrenci.getId() == 0L) { 

            String sql = "INSERT INTO OGRENCI (ad, soyad, ogrenci_no) VALUES (?, ?, ?)";
            jdbcTemplate.update(sql, ogrenci.getAd(), ogrenci.getSoyad(), ogrenci.getOgrenciNo());


        } else {
            String sql = "UPDATE OGRENCI SET ad = ?, soyad = ?, ogrenci_no = ? WHERE id = ?";
            jdbcTemplate.update(sql, ogrenci.getAd(), ogrenci.getSoyad(), ogrenci.getOgrenciNo(), ogrenci.getId());
        }
        return ogrenci;
    }
    // 4. D (Delete) - Silme
    public void deleteById(Long id) {
        String sql = "DELETE FROM OGRENCI WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
