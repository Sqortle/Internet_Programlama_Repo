<?php
header('Content-Type: application/json');

$data = json_decode(file_get_contents('php://input'), true);
$user_id = $data['id'];

if (empty($user_id)) {
    echo json_encode(['success' => false, 'message' => 'Kullanıcı ID\'si belirtilmedi.']);
    exit;
}

// Güvenlik önlemi: Ana admin (ID=1) silinemez.
if ($user_id == 1) {
    echo json_encode(['success' => false, 'message' => 'Ana yönetici silinemez.']);
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

$stmt = $conn->prepare("DELETE FROM kullanicilar WHERE id = ?");
$stmt->bind_param("i", $user_id);

if ($stmt->execute()) {
    echo json_encode(['success' => true, 'message' => 'Kullanıcı başarıyla silindi.']);
} else {
    echo json_encode(['success' => false, 'message' => 'Kullanıcı silinirken bir hata oluştu.']);
}

$stmt->close();
$conn->close();
?>