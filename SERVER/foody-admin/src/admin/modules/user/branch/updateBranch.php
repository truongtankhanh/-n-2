<?php

if (isset($_GET['editBranch'])) {
    $id = $_GET['editBranch'];

    $rec = mysqli_query($connect, "select * from restaurants_branch where id=$id");
    $record = mysqli_fetch_array($rec);

    $branch->setId($id);
    $branch->setResBranchCode($record['res_branch_code']);
    $branch->setResCode($record['res_code']);
    $branch->setResBranchName($record['res_branch_name']);
    $branch->setResBranchAddress($record['res_branch_address']);
    $branch->setResBranchOpenTime($record['res_branch_opentime']);
    $branch->setResBranchPrice($record['res_branch_price']);
    $branch->setResBranchImage($record['res_branch_image']);
    $branch->setCreate_at($record['create_at']);
    $update_at = date('Y-m-d');
    $branch->setUpdate_at($update_at);
}

if (isset($_POST['btnUpdateBranch'])) {
    $id = mysqli_real_escape_string($connect, $_POST['branchID']);
    $res_branch_code = mysqli_real_escape_string($connect, $_POST['branchCode']);
    $res_code = mysqli_real_escape_string($connect, $_POST['resCode']);
    $res_branch_name = mysqli_real_escape_string($connect, $_POST['branchName']);
    $res_branch_address = mysqli_real_escape_string($connect, $_POST['branchAddress']);
    $res_branch_opentime = mysqli_real_escape_string($connect, $_POST['branchOpenTime']);
    $res_branch_price = mysqli_real_escape_string($connect, $_POST['branchPrice']);
    $create_at = mysqli_real_escape_string($connect, $_POST['branchCreate']);
    $update_at = mysqli_real_escape_string($connect, $_POST['branchUpdate']);

    // Get image name
    $image_address = $_FILES['branchImage']['name'];

    // image file directory
    $target = 'images/'.basename($image_address);

    if (move_uploaded_file($_FILES['branchImage']['tmp_name'], $target)) {
        $msg = 'Image uploaded successfully';
    } else {
        $msg = 'Failed to upload image';
    }

    $sql = "update restaurants_branch set res_branch_code='$res_branch_code', res_code='$res_code', res_branch_name='$res_branch_name', res_branch_address='$res_branch_address', res_branch_opentime='$res_branch_opentime', res_branch_price='$res_branch_price',res_branch_image='$target', create_at='$create_at', update_at='$update_at' where id=$id";

    mysqli_query($connect, $sql);
    $_SESSION['msg'] = 'Update successful';
    echo '<script>window.close();</script>';
}
