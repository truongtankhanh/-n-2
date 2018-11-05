<?php
// Start session
session_start();

//Khai báo utf-8 để hiển thị được tiếng việt
header('Content-Type: text/html; charset=UTF-8');

// Open the connection to the database.
require '../database/dbConnect.php';

require 'importList.php';

?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Add new and update kind information</title>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

    <style>
        body{
            font-family: Serif;
            margin-bottom: 30px;
            margin-top:50px;
            background-color: #F8F8FF;
        }
        label{
            color: blue;
        }
    </style>
</head>

<body>
    <div class="container">
        <h1 style="text-align: center">Add new and update kind information</h1>
        <hr>
        <form action='formAddEditKind.php' method="POST">
            <div class="row">
                <div class="col-lg-3 col-md-3 col-sm-3 col-12">

                </div>

                <div class="col-lg-6 col-md-8 col-sm-12 col-12">
                

                    <!-- Kind id -->
                    <div class="form-group">
                        <input type="hidden" class="form-control" placeholder="Enter kind code" name="kindID" value="<?php echo $kind->getKindID(); ?>">
                    </div>
                    <!-- Kind id -->

                    <!-- Kind Code -->
                    <div class="form-group">
                        <input type="hidden" class="form-control" placeholder="Enter the type code of the dish" name="kindCode" id="kindCode" value="<?php echo $kind->getKindCode(); ?>">
                    </div>
                    <!-- Kind Code -->

                    <!-- Kind name -->
                    <div class="form-group">
                        <label for="kindName">Name of the dish:</label>
                        <input type="text" class="form-control" placeholder="Enter the type name of the dish" name="kindName" id="kindName" value="<?php echo $kind->getKindName(); ?>">
                    </div>
                    <!-- Kind name -->

                    <!-- Kind create at -->
                    <div class="form-group">
                        <label for="kindCreate">Creation time:</label>
                        <input type="date" class="form-control" id="kindCreate" placeholder="Enter creation time" name="kindCreate" value="<?php echo $kind->getCreate_at(); ?>">
                    </div>
                    <!-- Kind create at -->

                    <!-- Kind update at -->
                    <div>
                        <!-- <label for="kindUpdate">Update time:</label> -->
                        <input type="hidden" class="form-control" id="kindUpdate" placeholder="Enter update time" name="kindUpdate" value="<?php echo $kind->getUpdate_at(); ?>">
                    </div>
                    <!-- Kind update at -->
                </div>
            </div>
            <hr>
            <!-- Update and add new kind information -->
                <div class="form-group" style="text-align: center;">
                    <a class="btn btn-outline-warning" style="width: 35%; border-radius: 20px" href="formAddEditKind.php">Reset</a>
                    <br><br>
                    <button type="submit" id="btnSaveKind" name="btnSaveKind" class="btn btn-outline-primary" style="width: 35%; border-radius: 20px">Save</button>
                    <span>&nbsp;&nbsp;</span>
                    <button type="submit" id="btnUpdateKind" name="btnUpdateKind" class="btn btn-outline-info" style="width: 35%; border-radius: 20px">Update</button>
                </div>
            <!-- Update kind information -->
        </form>
    </div>
</body>

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

<script src="js/scriptKind.js"></script>

</html>