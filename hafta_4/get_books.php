<?php
// HTTP başlığını JSON olarak ayarla, böylece tarayıcı bunun bir veri dosyası olduğunu anlar
header('Content-Type: application/json');

// Veritabanı bağlantı bilgileri
$servername = "localhost";
$db_username = "root";
$db_password = "";
$dbname = "ims_library";

// Veritabanına bağlan
$conn = new mysqli($servername, $db_username, $db_password, $dbname);

// Bağlantı hatası varsa işlemi durdur ve hata mesajı göster
if ($conn->connect_error) {
    // Gerçek bir uygulamada bu kadar detaylı hata göstermek güvenlik açığı olabilir.
    // Şimdilik geliştirme için bu şekilde bırakıyoruz.
    die(json_encode(['error' => 'Veritabanı bağlantısı başarısız: ' . $conn->connect_error]));
}

// Veritabanından tüm kitapları çekmek için SQL sorgusu (ID'ye göre tersten sıralayarak en yenileri başa alalım)
$sql = "SELECT id, name, author, year FROM kitaplar ORDER BY id DESC";
$result = $conn->query($sql);

$books = [];
if ($result->num_rows > 0) {
    // Sonuçları satır satır döngüye al
    while($row = $result->fetch_assoc()) {
        // Her satırı $books dizisine ekle
        $books[] = $row;
    }
}

// PHP dizisini JSON formatına dönüştürerek ekrana yazdır
echo json_encode($books);

// Veritabanı bağlantısını kapat
$conn->close();
?>