<?php

// Start session
session_start();

//Khai báo utf-8 để hiển thị được tiếng việt
header('Content-Type: text/html; charset=UTF-8');

// Open the connection to the database.
require '../database/dbConnect.php';
require '../city/getAllCity.php';

require 'importLibs.php';

?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Add new and update restaurant information</title>

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
        <h1 style="text-align: center">Add new and update restaurant information</h1>
        <hr>
        <!-- Start form add new restaurant -->
        <form action='formAddEditRestaurant.php' method="POST" enctype="multipart/form-data">
            <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-12 col-12">
                    <!-- Restaurant id -->
                    <div>
                        <label for="resIDNew"></label>
                        <input type="hidden" class="form-control" id="resIDNew" placeholder="Enter restaurant code" name="resIDNew" value="<?php echo $restaurant->getId(); ?>">
                    </div>
                    <!-- Restaurant id -->

                    <!-- Restaurant code -->
                    <div>
                        <input type="hidden" class="form-control" id="resCodeNew" placeholder="Enter restaurant code" name="resCodeNew" value="<?php echo $restaurant->getResCode(); ?>">
                    </div>
                    <!-- Restaurant code -->

                    <!-- Restaurant name -->
                    <div class="form-group">
                        <label for="resNameNew">Restaurant name:</label>
                        <input type="text" class="form-control" id="resNameNew" placeholder="Enter restaurant name" name="resNameNew" value="<?php echo $restaurant->getResName(); ?>">
                    </div>
                    <!-- Restaurant name -->

                    <!-- Restaurant number of branches -->
                    <div class="form-group">
                        <label for="numberOfBranches">Restaurant number of branches:</label>
                        <input type="number" class="form-control" id="numberOfBranches" placeholder="Enter restaurant number of branches" name="numberOfBranches" value="<?php echo $restaurant->getNumberOfBranches(); ?>">
                    </div>
                    <!-- Restaurant number of branches -->

                    <!-- City code -->
                    <div class="form-group">
                        <label for="resCityCode">City name:</label>
                        <select name="resCityCode" id="resCityCode" class="form-control">
                            <option value="">
                                    Select city...
                            </option>
                            <?php
                            while ($row = $resultCity->fetch_assoc()) {
                                echo '
                                <option value="'.$row['city_code'].'">
                                    '.$row['city_name'].'
                                </option>';
                            }?>
                        </select>
                    </div>
                    <!-- City code -->

                    <!-- Restaurant create at -->
                    <div class="form-group">
                        <label for="resCreate">Creation time:</label>
                        <input type="date" class="form-control" id="resCreate" placeholder="Enter creation time" name="resCreate" value="<?php echo $restaurant->getCreate_at(); ?>">
                    </div>
                    <!-- Restaurant create at -->

                     <!-- Restaurant update at -->
                     <div>
                        <input type="hidden" class="form-control" id="resUpdate" placeholder="Enter update time" name="resUpdate" value="<?php echo $restaurant->getUpdate_at(); ?>">
                    </div>
                    <!-- Restaurant update at -->
                </div>

                <div class="col-lg-6 col-md-6 col-sm-12 col-12">
                    <br>
                    <!-- Picture of the restaurant -->
                    <div class="form-group">
                        <label for="resImage">Picture of the restaurant:</label>
                        <div class="input-group mb-3">
                            <div class="custom-file">
                                <input type="file" class="custom-file-input" id="inputGroupFile01" name="resImage" onchange="readURL(this);">
                                <label class="custom-file-label" for="inputGroupFile01">
                                    Choose file
                                </label>
                            </div>
                        </div>
                    </div>

                    <div class="form-group" style="text-align: center">
                        <img id="blah" class="img-thumbnail" src="<?php echo $restaurant->getImageAddress(); ?>" alt="" srcset="" width="340px">
                    </div>
                    <!-- Picture of the restaurant -->
                </div>
            </div>
            <!-- End form add new restaurant -->
            <div>
                <br><hr>
                <!-- Add new and update restaurant information -->
                <div class="form-group" style="text-align: center;">
                    <a class="btn btn-outline-warning" style="width: 35%; border-radius: 20px" href="formAddEditRestaurant.php">Reset</a>
                    <br><br>
                    <button type="submit" id="btnSaveRes" name="btnSaveRes" class="btn btn-outline-primary" style="width: 35%; border-radius: 20px">Save</button>
                    <span>&nbsp;&nbsp;</span>
                    <button type="submit" id="btnUpdateRes" name="btnUpdateRes" class="btn btn-outline-info" style="width: 35%; border-radius: 20px">Update</button>
                </div>
                <!-- Add new and update restaurant information -->
            </div>
        </form>
    </div>
</body>

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

    <!-- <script src="js/scriptRestaurant.js"></script> -->

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

