<?php
// Start session
session_start();

//Khai báo utf-8 để hiển thị được tiếng việt
header('Content-Type: text/html; charset=UTF-8');

// Open the connection to the database.
require '../database/dbConnect.php';
require 'getAllBranch.php';

$name = '';
$level = '';

if (isset($_SESSION['email']) && $_SESSION['email']) {
    $name = $_SESSION['email'];
} else {
    echo "<a href='javascript: history.go(-1)'></a>";
    echo '<script language="javascript">';
    echo 'alert("Bạn chưa đăng nhập.")';
    echo '</script>';
    header('Location: '.$config.'index.php');
}
$sql = "Select * from members where email='".$name."'";
$query = mysqli_query($connect, $sql);
$row = mysqli_fetch_array($query);
$level = $row['level'];

$res_name = '';
$res_code = '';

if (isset($_GET['detailRes'])) {
    $id = $_GET['detailRes'];

    $rec = mysqli_query($connect, "select * from restaurants where id=$id");
    $record = mysqli_fetch_array($rec);

    $res_code = $record['res_code'];
    $res_name = $record['res_name'];
}

// Create a query statement.
$sql = "select id,res_branch_code,res_code,res_branch_name,res_branch_address
        ,res_branch_opentime,res_branch_price,res_branch_image from restaurants_branch where res_code='".$res_code."'";

// Run the query statement.
$resultBranch = $connect->query($sql);

?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title><?php echo $res_name; ?></title>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap4.min.css">

    <style>
        body{
            font-family: Serif;
            margin-bottom: 50px;
            margin-top:40px;
            background-color: #F8F8FF;
        }
        label{
            color: blue;
        }
    </style>
</head>

<body>
    <div class="container">
        <h1 style="text-align: center"> <?php echo $res_name; ?></h1><hr>
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-12">

                <!-- Table member info -->
                <?php
                if ($resultBranch->num_rows > 0) {
                    ?>
                <table class="table table-bordered table-hover table-light" id="tbBranch">
                    <thead>
                        <tr  style="color: blue">
                            <th style="text-align: center">STT</th>
                            <th style="padding-left: 30px;">Name</th>
                            <th style="padding-left: 30px;">Address</th>
                            <th style="text-align: center">Open time</th>
                            <th style="padding-left: 30px;">Price</th>
                            <?php if ($level == '1') {
                        ?>
                            <th style="width: 120px; text-align: center">Action</th>
                            <?php
                    } ?>
                        </tr>
                    </thead>
                    <tbody id="tableBranch">
                    <?php
                    while ($row = $resultBranch->fetch_assoc()) {
                        echo '
                        <tr>
                            <td style="word-wrap:break-word; text-align: center;">'.$row['id'].'</td>
                            <td style="word-wrap:break-word; padding-left: 30px;">'.$row['res_branch_name'].'</td>
                            <td style="word-wrap:break-word; padding-left: 30px;">'.$row['res_branch_address'].'</td>
                            <td style="word-wrap:break-word;">'.$row['res_branch_opentime'].'</td>
                            <td style="word-wrap:break-word; padding-left: 30px;">'.$row['res_branch_price'].'</td>
                            '; ?>
                            <?php if ($level == '1') {
                            ?>
                            <td style="text-align: center">
                                <a class="btn btn-outline-info" target="_blank" href="formAddNewBranch.php?editBranch=<?php echo $row['id']; ?>">Edit</a>
                                <a class="btn btn-outline-warning" href="formInfoDetailRestaurant.php?delBranch=<?php echo $row['id']; ?>">Remove</a>
                            </td>
                            <?php
                        } ?>
                        <?php
                        echo '</tr>';
                    } ?>
                    </tbody>
                </table>
                <?php
                } else {
                    echo '0 results';
                }
            ?>
                <!-- Table member info -->
            </div>
        </div>
        <hr>
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-12">
                <a class="btn btn-outline-primary" target="_blank" href="formAddNewBranch.php">Add new Branch</a>
            </div>
        </div>
    </div>
</body>

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

<!-- <script src="js/scriptMember.js"></script> -->

    <script src="js/jquery.dataTables.min.js"></script>
    <script src="js/dataTables.bootstrap4.min.js"></script>

    <script>
        $(document).ready(function() {
            $('#tbBranch').DataTable();
        });

        function readURL(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();

                reader.onload = function(e) {
                    $('#blah')
                        .attr('src', e.target.result)
                        .width(340);
                };

                reader.readAsDataURL(input.files[0]);
            }
        }
    </script>

</html>