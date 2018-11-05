<?php

require '../admin/database/dbConnect.php';
require '../admin/modules/user/member/getAllMember.php';

class Member
{
    private $id;
    private $fullName;
    private $birthday;
    private $sex;
    private $email;
    private $password;

    public function __construct($id, $fullName, $birthday, $sex, $email, $password)
    {
        $this->ID = $id;
        $this->FullName = $fullName;
        $this->Birthday = $birthday;
        $this->Sex = $sex;
        $this->Email = $email;
        $this->Password = $password;
    }
}

$arrayMember = array();

while ($row = mysqli_fetch_assoc($resultMember)) {
    array_push($arrayMember, new Member($row['id'], $row['fullname'], $row['birthday'], $row['sex'], $row['email'], $row['password']));
}

echo json_encode($arrayMember);
