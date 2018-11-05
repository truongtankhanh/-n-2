<?php
// Start session
session_start();

//Khai báo utf-8 để hiển thị được tiếng việt
header('Content-Type: text/html; charset=UTF-8');

// Open the connection to the database.
require '../database/dbConnect.php';
require '../restaurant/getAllRestaurant.php';
require '../kind/getAllKind.php';

require 'importLibs.php';

?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Add new and update food information</title>

    <link rel="shortcut icon" type="image/png" href="/src/admin/modules/user/food/images/food_barnstar_hires.png"/>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

    <style>
        body{
            font-family: Serif;
            margin-bottom: 30px;
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
        <h1 style="text-align: center">Add new and update food information</h1>
        <hr>
        <!-- Start form add new Food -->
        <form action='formEditFood.php' method="POST" enctype="multipart/form-data">
            <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-12 col-12">
                    <!-- Food id -->
                    <div>
                        <input type="hidden" class="form-control" name="foodID" id="foodID" value="<?php echo $food->getId(); ?>">
                    </div>
                    <!-- Food id -->

                    <!-- Code dishes -->
                    <div>
                        <input type="hidden" class="form-control" placeholder="Enter code dishes" name="foodCode" id="foodCode" value="<?php echo $food->getFoodCode(); ?>">
                    </div>
                    <!-- Code dishes -->

                    <!-- Food's name -->
                    <div class="form-group">
                        <label for="foodName">Food's name:</label>
                        <input type="text" class="form-control" id="foodName" placeholder="Please enter food's name" name="foodName" value="<?php echo $food->getFoodName(); ?>">
                    </div>
                    <!-- Food's name -->

                    <!-- Food address -->
                    <div class="form-group">
                        <label for="foodAddress">Address of the dish:</label>
                        <input type="text" class="form-control" id="foodAddress" placeholder="Please enter address of the dish" name="foodAddress" value="<?php echo $food->getFoodAddress(); ?>">
                    </div>
                    <!-- Food address -->

                    <!-- Price of food -->
                    <div class="form-group">
                        <label for="foodPrice">Price of food:</label>
                        <input type="text" class="form-control" id="foodPrice" placeholder="Please enter price of food" name="foodPrice" value="<?php echo $food->getFoodPrice(); ?>">
                    </div>
                    <!-- Price of food -->

                    <!-- Restaurant code -->
                    <div class="form-group">
                        <label for="resCode">Restaurant name:</label>
                        <select name="resCode" id="resCode" class="form-control">
                            <option value="">
                                Please select restaurant...
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

                    <!-- Kind code -->
                    <div class="form-group">
                        <label for="kindCode">Name of the dish:</label>
                        <select name="kindCode" id="kindCode" class="form-control">
                            <option value="">
                                Please select kind...
                            </option>
                            <?php
                            while ($row = $resultKind->fetch_assoc()) {
                                echo '
                                <option value="'.$row['kind_code'].'">
                                    '.$row['kind_name'].'
                                </option>';
                            }?>
                        </select>
                    </div>
                    <!-- Kind code -->
                </div>
                <div class="col-lg-6 col-md-6 col-sm-12 col-12">
                    <!-- Picture of the dish -->
                    <div class="form-group">
                        <label for="foodImage">Picture of the dish:</label>
                        <div class="input-group mb-3">
                            <div class="custom-file">
                                <input type="file" class="custom-file-input" id="inputGroupFile01" name="foodImage" onchange="readURL(this);">
                                <label class="custom-file-label" for="inputGroupFile01">Choose file</label>
                            </div>
                        </div>
                    </div>

                    <br>

                    <div class="form-group" style="text-align: center">
                        <img id="blah" class="img-thumbnail" src="<?php echo $food->getImageAddress(); ?>" alt="" srcset="" width="440px">
                    </div>
                    <!-- Picture of the dish -->

                    <!-- Food create at -->
                    <div class="form-group">
                        <label for="foodCreate">Creation time:</label>
                        <input type="date" class="form-control" id="foodCreate" placeholder="Enter creation time" name="foodCreate" value="<?php echo $food->getCreate_at(); ?>">
                    </div>
                    <!-- Food create at -->

                    <!-- Food update at -->
                    <div>
                        <!-- <label for="foodUpdate">Update time:</label> -->
                        <input type="hidden" class="form-control" id="foodUpdate" placeholder="Enter update time" name="foodUpdate" value="<?php echo $food->getUpdate_at(); ?>">
                    </div>
                    <!-- Food update at -->
                </div>
            </div>
            <div>
                <br><hr>
                <!-- Add new and update food information -->
                <div class="form-group" style="text-align: center;">
                    <a class="btn btn-outline-warning" style="width: 35%; border-radius: 20px" href="formEditFood.php">Reset</a>
                    <br><br>
                    <button type="submit" id="btnSaveFood" name="btnSaveFood" class="btn btn-outline-primary" style="width: 35%; border-radius: 20px">Save</button>
                    <span>&nbsp;&nbsp;</span>
                    <button type="submit" id="btnUpdateFood" name="btnUpdateFood" class="btn btn-outline-info" style="width: 35%; border-radius: 20px">Update</button>
                </div>
                <!-- Add new and update food information -->
            </div>
        </form>
        <!-- End form add new City -->

    </div>
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
                        .width(340);
                };

                reader.readAsDataURL(input.files[0]);
            }
        }
    </script>
</html>