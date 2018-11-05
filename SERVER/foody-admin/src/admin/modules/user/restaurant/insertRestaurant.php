<?php

$count = 0;
$flag = false;

$restaurant->setResCode('R00'.($resultRestaurant->num_rows + 1));
$restaurant->setCreate_at(date('Y-m-d'));
$restaurant->setUpdate_at(date('Y-m-d'));

// If upload button is clicked ...
if (isset($_POST['btnSaveRes'])) {
    // Get image name
    $image_address = $_FILES['resImage']['name'];

    // image file directory
    $target = 'images/'.basename($image_address);

    if (move_uploaded_file($_FILES['resImage']['tmp_name'], $target)) {
        $msg = 'Image uploaded successfully';
    } else {
        $msg = 'Failed to upload image';
    }

    $restaurant->setResName(mysqli_real_escape_string($connect, $_REQUEST['resNameNew']));
    $restaurant->setNumberOfBranches(mysqli_real_escape_string($connect, $_REQUEST['numberOfBranches']));
    $restaurant->setImageAddress($target);
    $restaurant->setCityCode(mysqli_real_escape_string($connect, $_REQUEST['resCityCode']));
    $restaurant->setCreate_at(mysqli_real_escape_string($connect, $_REQUEST['resCreate']));
    $restaurant->setUpdate_at(mysqli_real_escape_string($connect, $_REQUEST['resUpdate']));

    // echo $restaurant->getNumberOfBranches();
    // echo $restaurant->getResName();
    // echo $restaurant->getCreate_at();
    // echo $restaurant->getImageAddress();

    $sql = "Insert into restaurants values(null,'".$restaurant->getResCode()."','".$restaurant->getResName()."','".$restaurant->getNumberOfBranches()."','".$restaurant->getImageAddress()."','".$restaurant->getCityCode()."','".$restaurant->getCreate_at()."','".$restaurant->getCreate_at()."')";

    mysqli_query($connect, $sql);
    $_SESSION['msg'] = 'Save successfully';
    header('Location: '.$config.'src/admin/modules/user/restaurant/formAddEditRestaurant.php');
    //echo '<script>window.close();</script>';
    $connect->close();
}
