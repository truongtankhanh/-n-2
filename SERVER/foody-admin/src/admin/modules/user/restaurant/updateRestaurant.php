<?php

// // Import class
// require 'Restaurant.php';

// // Create variable
// $restaurant = new Restaurant();

if (isset($_GET['editRes'])) {
    $id = $_GET['editRes'];

    $rec = mysqli_query($connect, "select * from restaurants where id=$id");
    $record = mysqli_fetch_array($rec);

    $restaurant->setId($id);
    $restaurant->setResCode($record['res_code']);
    $restaurant->setResName($record['res_name']);
    $restaurant->setNumberOfBranches($record['number_of_branches']);
    $restaurant->setImageAddress($record['image_address']);
    $restaurant->setCityCode($record['city_code']);
    $restaurant->setCreate_at($record['create_at']);
    $update_at = date('Y-m-d');
    $restaurant->setUpdate_at($update_at);
}

if (isset($_POST['btnUpdateRes'])) {
    $id = mysqli_real_escape_string($connect, $_POST['resIDNew']);
    $res_code = mysqli_real_escape_string($connect, $_POST['resCodeNew']);
    $res_name = mysqli_real_escape_string($connect, $_POST['resNameNew']);
    $number_of_branches = mysqli_real_escape_string($connect, $_POST['numberOfBranches']);
    $create_at = mysqli_real_escape_string($connect, $_POST['resCreate']);
    $update_at = mysqli_real_escape_string($connect, $_POST['resUpdate']);

    // Get image name
    $image_address = $_FILES['resImage']['name'];

    // image file directory
    $target = 'images/'.basename($image_address);

    if (move_uploaded_file($_FILES['resImage']['tmp_name'], $target)) {
        $msg = 'Image uploaded successfully';
    } else {
        $msg = 'Failed to upload image';
    }

    $city_code = mysqli_real_escape_string($connect, $_POST['resCityCode']);

    $sql = "update restaurants set res_code='$res_code', res_name='$res_name', number_of_branches='$number_of_branches', image_address='$target', city_code='$city_code', create_at='$create_at', update_at='$update_at' where id=$id";

    mysqli_query($connect, $sql);
    $_SESSION['msg'] = 'Update successful';
    echo '<script>window.close();</script>';
}
