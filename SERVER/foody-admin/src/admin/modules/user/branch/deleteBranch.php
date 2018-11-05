<?php

if (isset($_GET['delBranch'])) {
    $id = $_GET['delBranch'];

    $sql = "delete from restaurants_branch where id=$id";
    mysqli_query($connect, $sql);
    $_SESSION['msg'] = 'Delete successful';
    header('Location: '.$config.'src/admin/modules/user/branch/formInfoDetailRestaurant.php?detailRes='.$id);
}
