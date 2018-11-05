<?php

if (isset($_GET['delFood'])) {
    $id = $_GET['delFood'];

    $sql = "delete from foods where id=$id";
    mysqli_query($connect, $sql);
    $_SESSION['msg'] = 'Delete successful';
    header('Location: '.$config.'process.php');
}
