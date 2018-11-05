<?php

if (isset($_GET['delCity'])) {
    $id = $_GET['delCity'];

    $city->setId($id);

    $sql = 'delete from citys where id='.$city->getId().'';
    mysqli_query($connect, $sql);
    $_SESSION['msg'] = 'Delete successful';
    header('http://localhost:8080/foody-admin/process.php#resInfo');
}
