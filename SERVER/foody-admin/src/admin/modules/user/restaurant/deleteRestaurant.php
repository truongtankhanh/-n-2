<?php

if (isset($_GET['delRes'])) {
    $id = $_GET['delRes'];

    $sql = "delete from restaurants where id=$id";
    mysqli_query($connect, $sql);
    $_SESSION['msg'] = 'Delete successful';
    header('Location: '.$config.'process.php');
}
