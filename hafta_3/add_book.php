<?php
header('Content-Type: application/json');

// Gelen JSON verisini oku
$data = json_decode(file_get_contents('php://input'), true);

// Veritabanı bağlantısı
$servername = "localhost";
$db_username = "root";
$db_password = "";
$dbname = "ims_library";
$conn = new mysqli($servername, $db_username, $db_password, $dbname);

if ($conn->connect_error) {
    echo json_encode(['success' => false, 'message' => 'Veritabanı bağlantı hatası']);
    exit;
}

// Gelen verileri al
$name = $data['name'];
$author = $data['author'];
$year = $data['year'];
// Kategori verisi formda olmadığı için şimdilik boş geçiyoruz.
// $category = $data['category'];

if (empty($name) || empty($author)) {
    echo json_encode(['success' => false, 'message' => 'Kitap adı ve yazar boş olamaz.']);
    exit;
}

$stmt = $conn->prepare("INSERT INTO kitaplar (name, author, year) VALUES (?, ?, ?)");
$stmt->bind_param("ssi", $name, $author, $year);

if ($stmt->execute()) {
    echo json_encode(['success' => true, 'message' => 'Kitap başarıyla eklendi.']);
} else {
    echo json_encode(['success' => false, 'message' => 'Kitap eklenirken bir hata oluştu.']);
}

$stmt->close();
$conn->close();
?>