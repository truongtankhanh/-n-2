<?php

$kind->setKindCode('K00'.($resultKind->num_rows + 1));
$kind->setCreate_at(date('Y-m-d'));
$kind->setUpdate_at(date('Y-m-d'));

// If upload button is clicked ...
if (isset($_POST['btnSaveKind'])) {
    $kind->setKindName(mysqli_real_escape_string($connect, $_REQUEST['kindName']));

    $sql = "Insert into kind values(null,'".$kind->getKindCode()."','".$kind->getKindName()."','".$kind->getCreate_at()."','".$kind->getUpdate_at()."')";

    mysqli_query($connect, $sql);
    $_SESSION['msg'] = 'Save successfully';
    echo '<script>window.close();</script>';
}
