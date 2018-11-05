<?php

$connect = mysqli_connect("localhost", "root", "", "cuisines");
mysqli_query($connect, "SET NAMES 'utf8'");

// Check connection
if ($connect === false) {
    die("ERROR: Could not connect. " . mysqli_connect_error());
}

?>