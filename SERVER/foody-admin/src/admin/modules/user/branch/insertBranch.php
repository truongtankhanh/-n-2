<?php

$branch->setResBranchCode('BR00'.($resultBranch->num_rows + 1));
$branch->setCreate_at(date('Y-m-d'));
$branch->setUpdate_at(date('Y-m-d'));

// If upload button is clicked ...
if (isset($_POST['btnSaveBranch'])) {
    // Get image name
    $image_address = $_FILES['branchImage']['name'];

    // image file directory
    $target = 'images/'.basename($image_address);

    if (move_uploaded_file($_FILES['branchImage']['tmp_name'], $target)) {
        $msg = 'Image uploaded successfully';
    } else {
        $msg = 'Failed to upload image';
    }

    $branch->setResCode(mysqli_real_escape_string($connect, $_REQUEST['resCode']));
    $branch->setResBranchName(mysqli_real_escape_string($connect, $_REQUEST['branchName']));
    $branch->setResBranchAddress(mysqli_real_escape_string($connect, $_REQUEST['branchAddress']));
    $branch->setResBranchOpenTime(mysqli_real_escape_string($connect, $_REQUEST['branchOpenTime']));
    $branch->setResBranchPrice(mysqli_real_escape_string($connect, $_REQUEST['branchPrice']));
    $branch->setResBranchImage($target);

    $sql = "Insert into restaurants_branch values(null,'".$branch->getResBranchCode()."','".$branch->getResCode()."','".$branch->getResBranchName()."','".$branch->getResBranchAddress()."','".$branch->getResBranchOpenTime()."','".$branch->getResBranchPrice()."','".$branch->getResBranchImage()."','".$branch->getCreate_at()."','".$branch->getUpdate_at()."')";

    mysqli_query($connect, $sql);
    $_SESSION['msg'] = 'Save successfully';
    header('Location: '.$config.'src/admin/modules/user/branch/formAddNewBranch.php');
    // //echo '<script>window.close();</script>';
    $connect->close();
}
