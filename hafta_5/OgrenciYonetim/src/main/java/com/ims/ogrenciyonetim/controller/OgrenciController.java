package com.ims.ogrenciyonetim.controller;// src/main/java/com/example/demo/controller/OgrenciController.java

import com.ims.ogrenciyonetim.model.Ogrenci;
import com.ims.ogrenciyonetim.repository.OgrenciRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Optional;

@Controller
@RequestMapping("/ogrenciler")
public class OgrenciController {

    @Autowired
    private OgrenciRepository ogrenciRepository;

    // --- R (READ) - Tüm öğrencileri listeleme ---
    // IActionResult: ViewResult
    @GetMapping
    public String listele(Model model) {
        model.addAttribute("ogrenciListesi", ogrenciRepository.findAll());
        return "ogrenci_listesi"; // src/main/resources/templates/ogrenci_listesi.html dosyasını çağırır
    }

    // --- C (CREATE) - Ekleme formu gösterme ---
    // IActionResult: ViewResult
    @GetMapping("/ekle")
    public String ekleFormuGoster(Model model) {
        model.addAttribute("ogrenci", new Ogrenci());
        return "ogrenci_formu"; // src/main/resources/templates/ogrenci_formu.html dosyasını çağırır
    }

    // --- C (CREATE) - Kaydetme (Ekleme) işlemi ---
    // IActionResult: RedirectToActionResult
    @PostMapping("/kaydet")
    public String kaydet(@ModelAttribute Ogrenci ogrenci, RedirectAttributes redirectAttributes) {
        ogrenciRepository.save(ogrenci); // Ekleme ve Güncelleme için aynı metot kullanılır
        redirectAttributes.addFlashAttribute("mesaj", "Öğrenci başarıyla kaydedildi!");
        return "redirect:/ogrenciler"; // Tüm öğrencilerin listesine yönlendirir
    }

    // --- U (UPDATE) - Düzenleme formu gösterme ---
    // IActionResult: ViewResult
    @GetMapping("/duzenle/{id}")
    public String duzenleFormuGoster(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Ogrenci> ogrenci = ogrenciRepository.findById(id);

        if (ogrenci.isPresent()) {
            model.addAttribute("ogrenci", ogrenci.get());
            return "ogrenci_formu"; // Aynı formu kullanabiliriz
        } else {
            redirectAttributes.addFlashAttribute("hata", "Öğrenci bulunamadı!");
            return "redirect:/ogrenciler";
        }
    }

    // --- D (DELETE) - Silme işlemi ---
    // IActionResult: RedirectToActionResult
    @GetMapping("/sil/{id}") // Genellikle POST kullanılır ama basitlik için Get de yapılabilir
    public String sil(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            ogrenciRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("mesaj", id + " ID'li öğrenci silindi.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("hata", "Silme işlemi başarısız.");
        }
        return "redirect:/ogrenciler"; // Listeleme sayfasına yönlendirir
    }
}