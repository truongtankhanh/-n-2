<?php

if (isset($_GET['editKind'])) {
    $id = $_GET['editKind'];

    $rec = mysqli_query($connect, "select * from kind where id=$id");
    $record = mysqli_fetch_array($rec);

    $kind->setKindID($id);
    $kind->setKindCode($record['kind_code']);
    $kind->setKindName($record['kind_name']);
    $kind->setCreate_at($record['create_at']);
    $update_at = date('Y-m-d');
    $kind->setUpdate_at($update_at);
}

if (isset($_POST['btnUpdateKind'])) {
    $id = mysqli_real_escape_string($connect, $_POST['kindID']);
    $kind_code = mysqli_real_escape_string($connect, $_POST['kindCode']);
    $kind_name = mysqli_real_escape_string($connect, $_POST['kindName']);
    $create_at = mysqli_real_escape_string($connect, $_POST['kindCreate']);
    $update_at = mysqli_real_escape_string($connect, $_POST['kindUpdate']);

    $sql = "update kind set kind_code='$kind_code', kind_name='$kind_name', create_at='$create_at', update_at='$update_at' where id=$id";

    mysqli_query($connect, $sql);
    $_SESSION['msg'] = 'Update successful';
    echo '<script>window.close();</script>';
}
