<?php

// // Import class
// include "City.php";

// //Create variable
// $city = new City;

if (isset($_GET['editCity'])) {
    $id = $_GET['editCity'];

    $rec = mysqli_query($connect, "select * from citys where id=$id");
    $record = mysqli_fetch_array($rec);

    $city->setId($id);
    $city->setCity_code($record['city_code']);
    $city->setCity_name($record['city_name']);
    $city->setImage_address($record['image_address']);
    $city->setCreate_at($record['create_at']);
    $update_at = date('Y-m-d');
    $city->setUpdate_at($update_at);
}

if (isset($_POST['btnUpdateCity'])) {
    $id = mysqli_real_escape_string($connect, $_POST['cityID']);
    $city_code = mysqli_real_escape_string($connect, $_POST['cityCode']);
    $city_name = mysqli_real_escape_string($connect, $_POST['cityName']);
    $create_at = mysqli_real_escape_string($connect, $_POST['cityCreate']);
    $update_at = mysqli_real_escape_string($connect, $_POST['cityUpdate']);

    // Get image name
    $image_address = $_FILES['cityImage']['name'];

    // image file directory
    $target = 'src/admin/modules/user/city/images/'.basename($image_address);

    if (move_uploaded_file($_FILES['cityImage']['tmp_name'], $target)) {
        $msg = 'Image uploaded successfully';
    } else {
        $msg = 'Failed to upload image';
    }

    $sql = "update citys set city_code='$city_code', city_name='$city_name', image_address='$target', create_at='$create_at', update_at='$update_at' where id=$id";

    mysqli_query($connect, $sql);
    $_SESSION['msg'] = 'Update successful';
    header('Location: http://localhost/foody-admin/process.php');
}
