<?php
header('Content-Type: application/json');

$data = json_decode(file_get_contents('php://input'), true);
$book_id = $data['id'];

if (empty($book_id)) {
    echo json_encode(['success' => false, 'message' => 'Kitap ID\'si belirtilmedi.']);
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

$stmt = $conn->prepare("DELETE FROM kitaplar WHERE id = ?");
$stmt->bind_param("i", $book_id);

if ($stmt->execute()) {
    echo json_encode(['success' => true, 'message' => 'Kitap başarıyla silindi.']);
} else {
    echo json_encode(['success' => false, 'message' => 'Kitap silinirken bir hata oluştu.']);
}

$stmt->close();
$conn->close();
?>