<?php

// // Import class
// require 'Food.php';

// // Create variable
// $food = new Food();

if (isset($_GET['editFood'])) {
    $id = $_GET['editFood'];

    $rec = mysqli_query($connect, "select * from foods where id=$id");
    $record = mysqli_fetch_array($rec);

    $food->setId($id);
    $food->setFoodCode($record['food_code']);
    $food->setFoodName($record['food_name']);
    $food->setFoodAddress($record['food_address']);
    $food->setFoodPrice($record['food_price']);
    $food->setImageAddress($record['image_address']);
    $food->setResCode($record['res_code']);
    $food->setKindCode($record['kind_code']);
    $food->setCreate_at($record['create_at']);
    $update_at = date('Y-m-d');
    $food->setUpdate_at($update_at);
}

if (isset($_POST['btnUpdateFood'])) {
    $id = mysqli_real_escape_string($connect, $_POST['foodID']);
    $food_code = mysqli_real_escape_string($connect, $_POST['foodCode']);
    $food_name = mysqli_real_escape_string($connect, $_POST['foodName']);
    $food_address = mysqli_real_escape_string($connect, $_POST['foodAddress']);
    $food_price = mysqli_real_escape_string($connect, $_POST['foodPrice']);
    $res_code = mysqli_real_escape_string($connect, $_POST['resCode']);
    $kind_code = mysqli_real_escape_string($connect, $_POST['kindCode']);
    $create_at = mysqli_real_escape_string($connect, $_POST['foodCreate']);
    $update_at = mysqli_real_escape_string($connect, $_POST['foodUpdate']);

    // Get image name
    $image_address = $_FILES['foodImage']['name'];

    // image file directory
    $target = 'images/'.basename($image_address);

    if (move_uploaded_file($_FILES['foodImage']['tmp_name'], $target)) {
        $msg = 'Image uploaded successfully';
    } else {
        $msg = 'Failed to upload image';
    }

    $sql = "update foods set food_code='$food_code', food_name='$food_name', food_address='$food_address', food_price='$food_price', image_address='$target', res_code='$res_code', kind_code='$kind_code', create_at='$create_at', update_at='$update_at' where id=$id";

    mysqli_query($connect, $sql);
    $_SESSION['msg'] = 'Update successful';
    echo '<script>window.close();</script>';
}
