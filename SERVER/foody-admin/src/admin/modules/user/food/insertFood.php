<?php

$count = 0;

$food->setFoodCode('F00'.($resultFood->num_rows + 1));
$food->setCreate_at(date('Y-m-d'));
$food->setUpdate_at(date('Y-m-d'));

// If upload button is clicked ...
if (isset($_POST['btnSaveFood'])) {
    // Get image name
    $image_address = $_FILES['foodImage']['name'];

    // image file directory
    $target = 'images/'.basename($image_address);

    if (move_uploaded_file($_FILES['foodImage']['tmp_name'], $target)) {
        $msg = 'Image uploaded successfully';
    } else {
        $msg = 'Failed to upload image';
    }

    $food->setFoodName(mysqli_real_escape_string($connect, $_REQUEST['foodName']));
    $food->setFoodAddress(mysqli_real_escape_string($connect, $_REQUEST['foodAddress']));
    $food->setFoodPrice(mysqli_real_escape_string($connect, $_REQUEST['foodPrice']));
    $food->setImageAddress($target);
    $food->setResCode(mysqli_real_escape_string($connect, $_REQUEST['resCode']));
    $food->setKindCode(mysqli_real_escape_string($connect, $_REQUEST['kindCode']));
    $food->setCreate_at(mysqli_real_escape_string($connect, $_REQUEST['foodCreate']));
    $food->setUpdate_at(mysqli_real_escape_string($connect, $_REQUEST['foodUpdate']));

    // Create câu lệnh insert
    $sql = "Insert into foods values(null,'".$food->getFoodCode()."','".$food->getFoodName()."','".$food->getFoodAddress()."','".$food->getFoodPrice()."','".$food->getImageAddress()."','".$food->getResCode()."','".$food->getKindCode()."','".$food->getCreate_at()."','".$food->getUpdate_at()."')";

    mysqli_query($connect, $sql);
    $_SESSION['msg'] = 'Save successfully';
    header('Location: '.$config.'src/admin/modules/user/food/formEditFood.php');
    //echo '<script>window.close();</script>';
}
