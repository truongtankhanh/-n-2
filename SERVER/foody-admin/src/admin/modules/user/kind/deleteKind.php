<?php

if (isset($_GET['delKind'])) {
    $id = $_GET['delKind'];

    $sql = "delete from kind where id=$id";
    mysqli_query($connect, $sql);
    $_SESSION['msg'] = 'Delete successful';
    header('Location: '.$config.'process.php');
}
