<?php

require '../admin/database/dbConnect.php';
require '../admin/modules/user/kind/getAllKind.php';

class Kind
{
    private $kindID;
    private $kindCode;
    private $kindName;

    public function __construct($kindID, $kindCode, $kindName)
    {
        $this->ID = $kindID;
        $this->KindCode = $kindCode;
        $this->KindName = $kindName;
    }
}

$arrayKind = array();

while ($row = mysqli_fetch_assoc($resultKind)) {
    array_push($arrayKind, new Kind($row['id'], $row['kind_code'], $row['kind_name']));
}

echo json_encode($arrayKind);
