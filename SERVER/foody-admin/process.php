<?php

// Start session
session_start();

//Khai báo utf-8 để hiển thị được tiếng việt
header('Content-Type: text/html; charset=UTF-8');

require 'config.php';

// Open the connection to the database.
require 'src/admin/modules/user/database/dbConnect.php';

require 'src/admin/modules/user/city/importLibs.php';
require 'src/admin/modules/user/restaurant/importLibs.php';
require 'src/admin/modules/user/food/importLibs.php';
require 'src/admin/modules/user/member/importLibs.php';
require 'src/admin/modules/user/kind/importList.php';

$name = '';
$level = '';
$id = '';

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
    $id = $row['id'];
?>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>Vietnamese Cuisine</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <link rel="shortcut icon" type="image/png" href="images/food_barnstar_hires.png"/>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    
    <!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.1/css/bootstrap.css"> -->
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap4.min.css">

    <style>
        body{
            font-family: Serif;
            background-color: #F8F8FF;
        }
        label{
            color: blue;
        }
    </style>

</head>

<body>

    <!-- Start Navigation -->
    <nav class="navbar navbar-expand-md bg-secondary navbar-dark"  style="color: blue">
        <div class="container">
            <!-- Brand/logo -->
            <a class="navbar-brand" href="#" style="pointer-events: none;">
                <img src="images/food_barnstar_hires.png" alt="logo" style="width:40px;font-weight: bold">
            </a>

            <!-- Brand -->
            <a class="navbar-brand" href="index.php" style="font-weight: bold; font-size: 30px;color: red">Vietnamese Cuisine</a>

            <!-- Toggler/collapsibe Button -->
            <button class="navbar-toggler navbar-right" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
                <span class="navbar-toggler-icon"></span>
            </button>

            <!-- Start navbar links -->
            <div class="collapse navbar-collapse" id="collapsibleNavbar" style="font-size: 15px;">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="#" style="pointer-events: none; background: none;">
                            <img src="images/user-icon.png">
                        </a>
                    </li>
                    <li class="nav-item">
                        <?php
                        echo '<a class="nav-link" href="#" style="pointer-events: none;">'.$row['fullname'].'</a>';
                        ?>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                        <span>&nbsp;</span>
                        </a>
                        
                        <div class="dropdown-menu dropdown-menu-right" style="font-size:15px; font-weight:bold;">
                                <!-- Login and continue -->
                                <?php
                                echo '<a class="dropdown-item" href="login.php">Login</a>';
                                ?>
                            <div class="dropdown-divider"></div>
                                <!-- Logout and go back home -->
                                <?php
                                if (isset($_SESSION['email']) && $_SESSION['email']) {
                                    echo '<a class="dropdown-item" href="index.php">Logout</a>';
                                }
                                ?>
                            <div class="dropdown-divider"></div>
                                <a class="dropdown-item" target="_blank" href="src/admin/modules/user/member/formEditMember.php?editMember=<?php echo $id; ?>">Account Management</a>
                        </div>
                    </li>
                </ul>
            </div>
            <!-- End navbar links -->

        </div>
    </nav>
    <!-- End Navigation -->

    <br>

    <!-- Start page content -->
    <div class="container">
        <!-- Nav tabs -->
        <ul class="nav nav-tabs nav-justified"  style="font-weight: bold; font-size: 20px;">
            <li class="nav-item">
                <a class="nav-link active" href="#cityInfo">City</a>
            </li>
            <br>
            <li class="nav-item">
                <a class="nav-link" href="#resInfo">Restaurant</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#foodInfo">Food</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#kindInfo">Food type</a>
            </li>
        </ul>

        <!-- Tab panes -->
        <div class="tab-content border mb-3">

            <!-- Start tab panes city information -->
            <div id="cityInfo" class="container tab-pane active"><br>
        
                <div class="row">
                    <div class="col-lg-8 col-md-8 col-sm-8 col-12">

                <!-- <?php
                // if (isset($_SESSION['msg'])) {
                //     echo '<script language="javascript">';
                //     echo 'alert("'.$_SESSION['msg'].'")';
                //     echo '</script>';
                // }
                // unset($_SESSION['msg']);
                ?> -->
                
                <!-- Table city info -->
                <?php
                if ($resultCity->num_rows > 0) {
                    ?>
                <table class="table table-bordered table-hover table-light" id="tbCity">
                    <thead>
                        <tr style="color: blue">
                            <th style="text-align: center">STT</th>
                            <th style="padding-left: 30px;">City name</th>
                            <?php if ($level == '1') {
                        ?>
                            <th style="text-align: center">Action</th>
                            <?php
                    } ?>
                        </tr>
                    </thead>
                    <tbody id="tableCity">
                        <?php
                        while ($row = $resultCity->fetch_assoc()) {
                            echo '
                                <tr>
                                    <td style="text-align: center">'.$row['id'].'</td>
                                    <td style="padding-left: 30px;">'.$row['city_name'].'</td>
                                    '; ?>
                                    <?php if ($level == '1') {
                                ?>
                                    <td style="text-align: center">
                                        <a class="btn btn-outline-info" href="process.php?editCity=<?php echo $row['id']; ?>">Edit</a>
                                        <a class="btn btn-outline-warning" href="process.php?delCity=<?php echo $row['id']; ?>">Remove</a>
                                        
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
                <!-- Table city info -->

                <!-- Start form add new City -->
                </div>
                    <div class="col-lg-4 col-md-4 col-sm-4 col-12">
                        <br>
                        <form action='process.php?do=addNewCity' method="POST" enctype="multipart/form-data" id="formCity">

                            <!-- City id -->
                            <div class="form-group">
                                <input type="hidden" class="form-control" placeholder="Enter city code" name="cityID" value="<?php echo $city->getId(); ?>">
                            </div>
                            <!-- City id -->

                            <!-- City code -->
                            <div class="form-group">
                                <input type="hidden" class="form-control" placeholder="Enter city code" name="cityCode" value="<?php echo $city->getCity_code(); ?>">
                            </div>
                            <!-- City code -->

                            <!-- City name -->
                            <div class="form-group">
                                <label for="cityName">City name:</label>
                                <input type="text" class="form-control" id="cityName" placeholder="Enter city name" name="cityName" value="<?php echo $city->getCity_name(); ?>">
                            </div>
                            <!-- City name -->

                            <!-- Picture of the city -->
                            <div class="form-group">
                                <label for="cityImage">Picture of the restaurant:</label>
                                <div class="input-group mb-3">
                                    <div class="custom-file">
                                        <input type="file" class="custom-file-input" id="inputGroupFile01" name="cityImage" id="cityImage" onchange="readURL(this);">
                                        <label class="custom-file-label" for="inputGroupFile01">Choose file</label>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group" style="text-align: center">
                                <img id="blah" class="img-thumbnail" src="<?php echo $city->getImage_address(); ?>" width="240">
                            </div>
                            <!-- Picture of the city -->

                            <!-- City create at -->
                            <div class="form-group">
                                <label for="cityCreate">Creation time:</label>
                                <input type="date" class="form-control" id="cityCreate" placeholder="Enter creation time" name="cityCreate" value="<?php echo $city->getCreate_at(); ?>">
                            </div>
                            <!-- City create at -->

                            <!-- City update at -->
                            <div>
                                <!-- <label for="cityUpdate">Update time:</label> -->
                                <input type="hidden" class="form-control" id="cityUpdate" placeholder="Enter update time" name="cityUpdate" value="<?php echo $city->getUpdate_at(); ?>">
                            </div>
                            <!-- City update at -->

                            <hr>
                            <!-- Add new and update city information -->
                            <div class="form-group" style="text-align: center;">
                                <a class="btn btn-outline-warning" style="width: 35%; border-radius: 20px" href="process.php">Reset</a>
                                <br><br>
                                <button type="submit" id="btnSaveCity" name="btnSaveCity" class="btn btn-outline-primary" style="width: 35%; border-radius: 20px">Save</button>
                                <span>&nbsp;&nbsp;</span>
                                <button type="submit" id="btnUpdateCity" name="btnUpdateCity" class="btn btn-outline-info" style="width: 35%; border-radius: 20px">Update</button>
                            </div>
                            <!-- Add new and update city information -->

                        </form>
                    </div>
                </div>
                 <!-- End form add new City -->

            </div>
            <!-- End tab panes city information -->

            <!-- Start tab panes restaurant information -->
            <div id="resInfo" class="container tab-pane fade"><br>

                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-12">

                        <!-- Table restaurant info -->
                        <?php
                        if ($resultRestaurant->num_rows > 0) {
                            ?>
                        <table class="table table-bordered table-hover table-light" id="tbRes">
                            <thead>
                                <tr  style="color: blue">
                                    <th style="width: 7%; text-align: center">STT</th>
                                    <th style="padding-left: 30px;">Restaurant name</th>
                                    <th style="padding-left: 30px;">Number of branches</th>
                                    <?php if ($level == '1') {
                                ?>
                                    <th style="width: 15%; text-align: center">Action</th>
                                    <?php
                            } ?>
                                    <th style="width: 15%; text-align: center"></th>
                                </tr>
                            </thead>
                            <tbody id="tableRes">
                            <?php
                            while ($row = $resultRestaurant->fetch_assoc()) {
                                echo '<tr>
                                    <td style="text-align: center">'.$row['id'].'</td>
                                    <td style="word-wrap:break-word; padding-left: 30px;">'.$row['res_name'].'</td>
                                    <td style="word-wrap:break-word; padding-left: 30px;">'.$row['number_of_branches'].'</td>'; ?>
                                    <?php if ($level == '1') {
                                    ?>
                                    <td style="text-align: center">
                                        <a class="btn btn-outline-info" target="_blank" href="src/admin/modules/user/restaurant/formAddEditRestaurant.php?editRes=<?php echo $row['id']; ?>">Edit</a>
                                        
                                        <a class="btn btn-outline-warning" target="_blank" href="process.php?delRes=<?php echo $row['id']; ?>">Remove</a>
                                    </td>
                                    <?php
                                } ?>
                                    <td style="text-align: center">
                                        <a class="btn btn-outline-info" target="_blank" href="http://localhost/foody-admin/src/admin/modules/user/branch/formInfoDetailRestaurant.php?detailRes=<?php echo $row['id']; ?>">Detail</a>
                                    </td>
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
                        <!-- Table restaurant info -->

                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-12">
                        <a class="btn btn-outline-primary" target="_blank" href="src/admin/modules/user/restaurant/formAddEditRestaurant.php">Add new Restaurant</a>
                    </div>
                </div>
                <br>
            </div>
            <!-- End tab panes restaurant information -->

            <!-- Start tab panes food information -->
            <div id="foodInfo" class="container tab-pane fade"><br>

                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-12">

                        <!-- Table food info -->
                        <?php
                        if ($resultFood->num_rows > 0) {
                            ?>
                        <table class="table table-bordered table-hover table-light" id="tbFood">
                            <thead>
                                <tr style="color: blue">
                                    <th style="text-align: center">STT</th>
                                    <th style="padding-left: 30px;">Food's name</th>
                                    <th style="padding-left: 30px;">Address of the dish</th>
                                    <th style="text-align: center">Price of food</th>
                                    <th style="text-align: center">Name of the dish</th>
                                    <?php if ($level == '1') {
                                ?>
                                    <th style="width: 115px; text-align: center">Action</th>
                                    <?php
                            } ?>
                                </tr>
                            </thead>
                            <tbody id="tableFood">
                                <?php
                                while ($row = $resultFood->fetch_assoc()) {
                                    $rec = mysqli_query($connect, "select * from kind where kind_code='".$row['kind_code']."'");
                                    $record = mysqli_fetch_array($rec);

                                    echo '<tr>
                                        <td style="word-wrap:break-word; text-align: center">'.$row['id'].'</td>
                                        <td style="word-wrap:break-word; padding-left: 30px;">'.$row['food_name'].'</td>
                                        <td style="word-wrap:break-word; padding-left: 30px;">'.$row['food_address'].'</td>
                                        <td style="word-wrap:break-word; text-align: center">'.$row['food_price'].'</td>
                                        <td style="word-wrap:break-word; text-align: center">'.$record['kind_name'].'</td>'; ?>
                                        <?php if ($level == '1') {
                                        ?>
                                        <td  style="text-align: center">
                                            <a class="btn btn-outline-info" target="_blank" href="src/admin/modules/user/food/formEditFood.php?editFood=<?php echo $row['id']; ?>">Edit</a>
                                            
                                            <a class="btn btn-outline-warning" href="process.php?delFood=<?php echo $row['id']; ?>">Remove</a>
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
                        <!-- Table food info -->
                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-12">
                        <a class="btn btn-outline-primary" target="_blank" href="src/admin/modules/user/food/formEditFood.php">Add new Food</a>
                    </div>
                </div>
                <br>
            </div>
            <!-- End tab panes food information -->

            <!-- Start tab panes kind information -->
            <div id="kindInfo" class="container tab-pane fade"><br>

                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-12">

                        <!-- Table kind info -->
                        <?php
                        if ($resultKind->num_rows > 0) {
                            ?>
                        <table class="table table-bordered table-hover table-light" id="tbKind">
                            <thead>
                                <tr style="color: blue">
                                    <th style="text-align: center">STT</th>
                                    <th style="padding-left: 30px;">Name</th>
                                    <?php if ($level == '1') {
                                ?>
                                    <th style="width: 115px; text-align: center">Action</th>
                                    <?php
                            } ?>
                                </tr>
                            </thead>
                            <tbody id="tableKind">
                                <?php
                                while ($row = $resultKind->fetch_assoc()) {
                                    echo '<tr>
                                        <td style="word-wrap:break-word; text-align: center">'.$row['id'].'</td>
                                        <td style="word-wrap:break-word; padding-left: 30px;">'.$row['kind_name'].'</td>'; ?>
                                        <?php if ($level == '1') {
                                        ?>
                                        <td  style="text-align: center">
                                            <a class="btn btn-outline-info" target="_blank" href="src/admin/modules/user/kind/formAddEditKind.php?editKind=<?php echo $row['id']; ?>">Edit</a>
                                            
                                            <a class="btn btn-outline-warning" href="process.php?delKind=<?php echo $row['id']; ?>">Remove</a>
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
                        <!-- Table kind info -->
                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-12">
                        <a class="btn btn-outline-primary" target="_blank" href="src/admin/modules/user/kind/formAddEditKind.php">Add new Kind</a>
                    </div>
                </div>
                <br>
            </div>
            <!-- End tab panes kind information -->

        </div>
        <!-- End tab panes -->

    </div>
    <!-- End page content -->

</body>

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>


    <script>
        function readURL(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();

                reader.onload = function(e) {
                    $('#blah')
                        .attr('src', e.target.result)
                        .width(240);
                };

                reader.readAsDataURL(input.files[0]);
            }
        }
    </script>

    <script src="./assets/js/script.js"></script>
    <script src="./assets/js/scriptCity.js"></script>
    
    <script src="./assets/js/jquery.dataTables.min.js"></script>
    <script src="./assets/js/dataTables.bootstrap4.min.js"></script>

    <!-- Page-Level Demo Scripts - Tables - Use for reference -->
    <script>
        $(document).ready(function() {
            $('#tbCity').DataTable();
            $('#tbRes').DataTable();
            $('#tbFood').DataTable();
            $('#tbKind').DataTable();
            $('#tbMember').DataTable();
        });
    </script>

</html>