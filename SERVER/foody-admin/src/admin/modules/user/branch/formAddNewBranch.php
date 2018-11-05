<?php
// Start session
session_start();

//Khai báo utf-8 để hiển thị được tiếng việt
header('Content-Type: text/html; charset=UTF-8');

// Open the connection to the database.
require '../database/dbConnect.php';
require '../restaurant/getAllRestaurant.php';

require 'importLibs.php';

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

if (isset($_GET['detailRes'])) {
    $id = $_GET['detailRes'];

    $rec = mysqli_query($connect, "select * from restaurants where id=$id");
    $record = mysqli_fetch_array($rec);

    $res_name = $record['res_name'];
}
?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Add new and update branch information</title>

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
        <h1 style="text-align: center">Add new and update branch information</h1>
        <hr>
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-12">
                <form action='formAddNewBranch.php' method="POST" enctype="multipart/form-data">
                    <div class="row">
                        <div class="col-lg-6 col-md-8 col-sm-12 col-12">

                            <!-- Branch id -->
                            <div>
                                <input type="hidden" class="form-control" placeholder="Enter branch id" name="branchID" id="branchID" value="<?php echo $branch->getId(); ?>">
                            </div>
                            <!-- Branch id -->

                            <!-- Branch code -->
                            <div>
                                <input type="hidden" class="form-control" placeholder="Enter branch code" name="branchCode" id="branchCode" value="<?php echo $branch->getResBranchCode(); ?>">
                            </div>
                            <!-- Branch code -->

                            <!-- Restaurant code -->
                            <div class="form-group">
                                <label for="resCode">Restaurant name:</label>
                                <select name="resCode" id="resCode" class="form-control">
                                    <option value="">
                                            Select restaurant...
                                    </option>
                                    <?php
                                    while ($row = $resultRestaurant->fetch_assoc()) {
                                        echo '
                                        <option value="'.$row['res_code'].'">
                                            '.$row['res_name'].'
                                        </option>';
                                    }?>
                                </select>
                            </div>
                            <!-- Restaurant code -->

                            <!-- Branch name -->
                            <div class="form-group">
                                <label for="branchName">Branch name:</label>
                                <input type="text" class="form-control" placeholder="Enter branch name" name="branchName" id="branchName" value="<?php echo $branch->getResBranchName(); ?>">
                            </div>
                            <!-- Branch name -->

                            <!-- Branch address -->
                            <div class="form-group">
                                <label for="branchAddress">Branch address:</label>
                                <input type="text" class="form-control" placeholder="Enter branch address" name="branchAddress" id="branchAddress" value="<?php echo $branch->getResBranchAddress(); ?>">
                            </div>
                            <!-- Branch address -->

                            <!-- Branch open time -->
                            <div class="form-group">
                                <label for="branchOpenTime">Open time:</label>
                                <input type="text" class="form-control" placeholder="Enter open time" name="branchOpenTime" id="branchOpenTime" value="<?php echo $branch->getResBranchOpenTime(); ?>">
                            </div>
                            <!-- Branch open time -->

                            <!-- Branch price -->
                            <div class="form-group">
                                <label for="branchPrice">Price of food:</label>
                                <input type="text" class="form-control" placeholder="Enter Price of food" name="branchPrice" id="branchPrice" value="<?php echo $branch->getResBranchPrice(); ?>">
                            </div>
                            <!-- Branch price -->
                        </div>

                        <div class="col-lg-6 col-md-8 col-sm-12 col-12">
                            <!-- Picture of the restaurant -->
                            <div class="form-group">
                                <label for="branchImage">Picture of the branch:</label>
                                <div class="input-group mb-3">
                                    <div class="custom-file">
                                        <input type="file" class="custom-file-input" id="inputGroupFile01" name="branchImage" onchange="readURL(this);" value="<?php echo $branch->getResBranchImage(); ?>">
                                        <label class="custom-file-label" for="inputGroupFile01">
                                            Choose file
                                        </label>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group" style="text-align: center">
                                <img id="blah" class="img-thumbnail" src="<?php echo $branch->getResBranchImage(); ?>" alt="" srcset="" width="340px">
                            </div>
                            <!-- Picture of the restaurant -->

                            <!-- Member create at -->
                            <div class="form-group">
                                <label for="branchCreate">Creation time:</label>
                                <input type="date" class="form-control" id="branchCreate" placeholder="Enter creation time" name="branchCreate" value="<?php echo $branch->getCreate_at(); ?>">
                            </div>
                            <!-- Member create at -->

                            <!-- Member update at -->
                            <div>
                                <input type="hidden" class="form-control" id="branchUpdate" placeholder="Enter update time" name="branchUpdate" value="<?php echo $branch->getUpdate_at(); ?>">
                            </div>
                            <!-- Member update at -->
                        </div>
                    </div>
                    <hr>
                    <!-- Update branch information -->
                    <div class="form-group" style="text-align: center;">
                        <a class="btn btn-outline-warning" style="width: 35%; border-radius: 20px" href="formAddNewBranch.php">Reset</a>
                        <br><br>
                        <button type="submit" id="btnSaveBranch" name="btnSaveBranch" class="btn btn-outline-primary" style="width: 35%; border-radius: 20px">Save</button>
                        <button type="submit" id="btnUpdateBranch" name="btnUpdateBranch" class="btn btn-outline-info" style="width: 35%; border-radius: 20px">Update</button>
                    </div>
                    <!-- Update branch information -->

                </form>
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