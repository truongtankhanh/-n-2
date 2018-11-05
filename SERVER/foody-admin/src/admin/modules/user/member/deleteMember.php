<?php

if (isset($_GET['delMember'])) {
    $id = $_GET['delMember'];

    $sql = "delete from members where id=$id";
    mysqli_query($connect, $sql);
    $_SESSION['msg'] = 'Delete successful';
    header('Location: '.$config.'src/admin/modules/user/member/formEditMember.php');
}
