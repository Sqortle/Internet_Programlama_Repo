<?php
// Session'ı başlatarak kullanıcı bilgilerini saklayabiliriz.
session_start();

// Veritabanı bağlantı bilgileri
$servername = "localhost"; // Genellikle localhost'tur
$db_username = "root";     // XAMPP için varsayılan kullanıcı adı 'root'
$db_password = "";         // XAMPP için varsayılan şifre boştur
$dbname = "ims_library";   // Oluşturduğumuz veritabanının adı

// Veritabanına bağlan
$conn = new mysqli($servername, $db_username, $db_password, $dbname);

// Bağlantıyı kontrol et
if ($conn->connect_error) {
    die("Veritabanı bağlantısı başarısız: " . $conn->connect_error);
}

// Formdan gelen verileri al
$username = $_POST['username'];
$password = $_POST['password'];

// SQL Injection'a karşı korunmak için prepared statement kullanalım
$stmt = $conn->prepare("SELECT * FROM kullanicilar WHERE username = ?");
$stmt->bind_param("s", $username);
$stmt->execute();
$result = $stmt->get_result();

if ($result->num_rows === 1) {
    // Kullanıcı bulundu, şimdi şifreyi kontrol et
    $user = $result->fetch_assoc();

    // EKSİK OLAN VE EKLENEN ÖNEMLİ KISIM BURASI
    if ($password === $user['password']) {
        // Şifre doğruysa session bilgilerini ayarla ve yönlendir

        $_SESSION['loggedin'] = true;
        $_SESSION['username'] = $user['username'];
        $_SESSION['role'] = $user['role'];

        if ($user['role'] === 'admin') {
            // Kullanıcı 'admin' ise admin paneline yönlendir
            header("Location: admin.html");
            exit;
        } else {
            // Kullanıcı 'admin' değilse (örneğin 'user' ise) kullanıcı paneline yönlendir
            header("Location: user_panel.html");
            exit;
        }

    } else {
        // Şifre yanlışsa burası çalışacak
        header("Location: login.html?error=1"); // Hata koduyla login sayfasına geri dön
        exit;
    }

} else {
    // Kullanıcı bulunamadıysa burası çalışacak
    header("Location: login.html?error=1"); // Hata koduyla login sayfasına geri dön
    exit;
}

$stmt->close();
$conn->close();
?>