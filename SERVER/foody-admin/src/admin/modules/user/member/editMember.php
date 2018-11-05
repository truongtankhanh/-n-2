<?php

if (isset($_GET['editMember'])) {
    $id = $_GET['editMember'];

    $rec = mysqli_query($connect, "select * from members where id=$id");
    $record = mysqli_fetch_array($rec);

    $member->setId($id);
    $member->setFullName($record['fullname']);
    $member->setBirthday($record['birthday']);
    $member->setSex($record['sex']);
    $member->setEmail($record['email']);
    $member->setPassword($record['password']);
    $member->setLevel($record['level']);
    $member->setCreate_at($record['create_at']);
    $update_at = date('Y-m-d');
    $member->setUpdate_at($update_at);
}

if (isset($_POST['btnUpdateMember'])) {
    $id = mysqli_real_escape_string($connect, $_POST['memberID']);
    $fullname = mysqli_real_escape_string($connect, $_POST['memberName']);
    $birthday = mysqli_real_escape_string($connect, $_POST['memberBirthday']);
    $sex = mysqli_real_escape_string($connect, $_POST['Gender']);
    $email = mysqli_real_escape_string($connect, $_POST['memberEmail']);
    $password = mysqli_real_escape_string($connect, $_POST['memberPassword']);
    $level = mysqli_real_escape_string($connect, $_POST['Level']);
    $create_at = mysqli_real_escape_string($connect, $_POST['memberCreate']);
    $update_at = mysqli_real_escape_string($connect, $_POST['memberUpdate']);

    $selected_val = $_POST['Gender'];  // Storing Selected Value In Variable
    echo 'You have selected :'.$selected_val;  // Displaying Selected Value

    $sql = "update members set fullname='$fullname', birthday='$birthday', sex='$sex', email='$email', password='$password', level='$level', create_at='$create_at', update_at='$update_at' where id=$id";
    mysqli_query($connect, $sql);
    $_SESSION['msg'] = 'Update successful';
    //echo '<script>window.close();</script>';

    header('Location: '.$config.'src/admin/modules/user/member/formEditMember.php');
}
