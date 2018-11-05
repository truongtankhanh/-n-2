<?php

require '../admin/database/dbConnect.php';

// Create a query statement.
$sql = 'select * from comments';

// Run the query statement.
$result = $connect->query($sql);

class Comment
{
    private $id;
    private $userName;
    private $email;
    private $photoUrl;
    private $comment;
    private $create_time;

    public function __construct($id, $userName, $email, $photoUrl, $comment, $create_time)
    {
        $this->ID = $id;
        $this->UserName = $userName;
        $this->Email = $email;
        $this->PhotoUrl = $photoUrl;
        $this->Comment = $comment;
        $this->Create_time = $create_time;
    }
}

$arrayComment = array();

while ($row = mysqli_fetch_assoc($result)) {
    array_push($arrayComment, new Comment($row['id'], $row['user_name'], $row['email'], $row['photo_url'], $row['commment'], $row['create_time']));
}

echo json_encode($arrayComment);
