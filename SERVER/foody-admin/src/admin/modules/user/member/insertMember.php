<?php

$count = 0;
$flag = false;

$member->setCreate_at(date('Y-m-d'));
$member->setUpdate_at(date('Y-m-d'));

// If upload button is clicked ...
if (isset($_POST['btnSaveMember'])) {
    $member->setFullName(mysqli_real_escape_string($connect, $_REQUEST['memberName']));
    $member->setBirthday(mysqli_real_escape_string($connect, $_REQUEST['memberBirthday']));
    $member->setSex(mysqli_real_escape_string($connect, $_REQUEST['Gender']));
    $member->setEmail(mysqli_real_escape_string($connect, $_REQUEST['memberEmail']));
    $member->setPassword(md5(mysqli_real_escape_string($connect, $_REQUEST['memberPassword'])));
    $member->setLevel(mysqli_real_escape_string($connect, $_REQUEST['Level']));
    $member->setCreate_at(mysqli_real_escape_string($connect, $_REQUEST['memberCreate']));
    $member->setUpdate_at(mysqli_real_escape_string($connect, $_REQUEST['memberUpdate']));

    $sql = "Insert into members values(null,'".$member->getFullName()."','".$member->getBirthday()."','".$member->getSex()."','".$member->getEmail()."','".$member->getPassword()."','".$member->getLevel()."','".$member->getCreate_at()."','".$member->getUpdate_at()."')";

    mysqli_query($connect, $sql);
    $_SESSION['msg'] = 'Save successfully';
    header('Location: '.$config.'src/admin/modules/user/member/formEditMember.php');
    $connect->close();
}
