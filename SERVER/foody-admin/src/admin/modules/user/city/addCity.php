<?php

  // Initialize message variable
$msg = '';

$city->setCity_code('CT00'.($resultCity->num_rows + 1));
$city->setCreate_at(date('Y-m-d'));
$city->setUpdate_at(date('Y-m-d'));

if (isset($_POST['btnSaveCity'])) {
    // Get image name
    $image_address = $_FILES['cityImage']['name'];

    // image file directory
    $target = 'src/admin/modules/user/city/images/'.basename($image_address);

    if (move_uploaded_file($_FILES['cityImage']['tmp_name'], $target)) {
        $msg = 'Image uploaded successfully';
    } else {
        $msg = 'Failed to upload image';
    }

    $city->setImage_address($target);

    // Declare the variable containing the data to be added to the database.
    $city->setCity_name(mysqli_real_escape_string($connect, $_REQUEST['cityName']));
    $city->setCreate_at(mysqli_real_escape_string($connect, $_REQUEST['cityCreate']));
    $city->setUpdate_at(mysqli_real_escape_string($connect, $_REQUEST['cityUpdate']));

    // Create a query statement.
    $query = "Insert into citys values(null,'".$city->getCity_code()."','".$city->getCity_name()."','".$city->getImage_address()."','".$city->getCreate_at()."','".$city->getUpdate_at()."')";

    // Run the query statement.
    mysqli_query($connect, $query);
    $_SESSION['msg'] = 'Save successfully';
    header('Location: '.$config.'process.php');
    //echo "<script>window.close();</script>";
    $connect->close();
}
