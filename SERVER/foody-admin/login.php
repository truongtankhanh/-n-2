<?php
//Khai báo sử dụng session
session_start();
//Khai báo utf-8 để hiển thị được tiếng việt
header('Content-Type: text/html; charset=UTF-8');

require 'config.php';
require './src/admin/database/dbConnect.php';
require './src/admin/modules/user/member/Member.php';

$member = new Member();

if (isset($_POST['login'])) {
    //Lấy dữ liệu nhập vào
    $member->setEmail(mysqli_real_escape_string($connect, $_REQUEST['email']));
    // mã hóa pasword
    $member->setPassword(md5(mysqli_real_escape_string($connect, $_REQUEST['pass'])));

    //Kiểm tra tên đăng nhập có tồn tại không
    $sql = "Select email, password from members where email='".$member->getEmail()."'";
    $query = mysqli_query($connect, $sql);
    if (mysqli_num_rows($query) == 0) {
        echo "<a href='javascript: history.go(-1)'></a>";
        echo '<script language="javascript">';
        echo 'alert("Tên đăng nhập này không tồn tại. Vui lòng kiểm tra lại.")';
        echo '</script>';
        exit;
    }

    //Lấy mật khẩu trong database ra
    $row = mysqli_fetch_array($query);

    //So sánh 2 mật khẩu có trùng khớp hay không
    if ($member->getPassword() != $row['password']) {
        echo "<a href='javascript: history.go(-1)'></a>";
        echo '<script language="javascript">';
        echo 'alert("Mật khẩu không đúng. Vui lòng nhập lại")';
        echo '</script>';
        exit;
    }

    //Lưu tên đăng nhập
    $_SESSION['email'] = $member->getEmail();
    header('Location: '.$config.'process.php');
    die();
}
require './src/admin/modules/common/login.html';
?>

