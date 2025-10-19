<?php
header('Content-Type: application/json');

$data = json_decode(file_get_contents('php://input'), true);
$book_id = $data['id'];
$name = $data['name'];
$author = $data['author'];
$year = $data['year'];

if (empty($book_id) || empty($name) || empty($author)) {
    echo json_encode(['success' => false, 'message' => 'Gerekli bilgiler eksik.']);
    exit;
}

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

$stmt = $conn->prepare("UPDATE kitaplar SET name = ?, author = ?, year = ? WHERE id = ?");
$stmt->bind_param("ssii", $name, $author, $year, $book_id);

if ($stmt->execute()) {
    echo json_encode(['success' => true, 'message' => 'Kitap başarıyla güncellendi.']);
} else {
    echo json_encode(['success' => false, 'message' => 'Kitap güncellenirken bir hata oluştu.']);
}

$stmt->close();
$conn->close();
?>