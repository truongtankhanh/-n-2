<?php

// Mở kết nối tới database.
require '../admin/database/dbConnect.php';

// Khai báo biến chứa dữ liệu thêm vào
$userName = $_POST['userName'];
$email = $_POST['email'];
$photoUrl = $_POST['photoUrl'];
$comment = $_POST['comment'];
$createTime = date('Y-m-d');

// Tạo câu lệnh truy vấn
$query = "Insert into comments values(null,'$userName','$email','$photoUrl','$comment','$createTime')";

// Chạy câu lệnh truy vấn và kiểm tra kết quả
if (mysqli_query($connect, $query)) {
    echo 'success';
} else {
    echo 'error';
}
