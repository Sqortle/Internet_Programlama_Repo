<?php
header('Content-Type: application/json');

// Veritabanı bağlantısı
$servername = "localhost";
$db_username = "root";
$db_password = "";
$dbname = "ims_library";
$conn = new mysqli($servername, $db_username, $db_password, $dbname);

if ($conn->connect_error) {
    die(json_encode(['error' => 'Veritabanı bağlantısı başarısız']));
}

// ÖNEMLİ: Asla şifre sütununu seçmeyin!
$sql = "SELECT id, username, role FROM kullanicilar";
$result = $conn->query($sql);

$users = [];
if ($result->num_rows > 0) {
    while($row = $result->fetch_assoc()) {
        $users[] = $row;
    }
}

echo json_encode($users);

$conn->close();
?>