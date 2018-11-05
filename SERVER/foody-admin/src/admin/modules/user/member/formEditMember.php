<?php
// Start session
session_start();

//Khai báo utf-8 để hiển thị được tiếng việt
header('Content-Type: text/html; charset=UTF-8');

// Open the connection to the database.
require '../database/dbConnect.php';

require 'importLibs.php';

$member->getFullName();
$member->getBirthday();
$member->getEmail();
$member->getSex();

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
?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Member information</title>

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
        <h1 style="text-align: center">Member information</h1><hr>
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-12">

                <!-- Table member info -->
                <?php
                if ($resultMember->num_rows > 0) {
                    ?>
                <table class="table table-bordered table-hover table-light" id="tbMember">
                    <thead>
                        <tr  style="color: blue">
                            <th style="text-align: center">STT</th>
                            <th style="padding-left: 30px;">Full name</th>
                            <th style="text-align: center">Birthday</th>
                            <th style="text-align: center">Gender</th>
                            <th style="padding-left: 30px;">Email</th>
                            <th style="padding-left: 30px;text-align: center;">Level</th>
                            <?php if ($level == '1') {
                        ?>
                            <th style="width: 180px; text-align: center">Action</th>
                            <?php
                    } ?>
                        </tr>
                    </thead>
                    <tbody id="tableMember">
                    <?php
                    while ($row = $resultMember->fetch_assoc()) {
                        $date = date_create($row['birthday']);
                        date_format($date, 'd/m/Y');
                        echo '
                        <tr>
                            <td style="word-wrap:break-word; text-align: center;">'.$row['id'].'</td>
                            <td style="word-wrap:break-word; padding-left: 30px;">'.$row['fullname'].'</td>
                            <td style="word-wrap:break-word; text-align: center;">'.date_format($date, 'Y/m/d').'</td>
                            <td style="word-wrap:break-word; text-align: center;">'.$row['sex'].'</td>
                            <td style="word-wrap:break-word; padding-left: 30px;">'.$row['email'].'</td>
                            <td style="word-wrap:break-word; text-align: center;">'.$row['level'].'</td>
                            '; ?>
                            <?php if ($level == '1') {
                            ?>
                            <td style="text-align: center">
                                <a class="btn btn-outline-info" href="formEditMember.php?editMember=<?php echo $row['id']; ?>">Edit</a>
                                <a class="btn btn-outline-warning" href="formEditMember.php?delMember=<?php echo $row['id']; ?>">Remove</a>
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
                <form action='formEditMember.php' method="POST">
                    <div class="row">
                        <div class="col-lg-6 col-md-8 col-sm-12 col-12">

                            <!-- Member id -->
                            <div>
                                <input type="hidden" class="form-control" placeholder="Enter city code" name="memberID" id="memberID" value="<?php echo $member->getId(); ?>">
                            </div>
                            <!-- Member id -->

                            <!-- Full name -->
                            <div class="form-group">
                                <label for="memberName">Member's name:</label>
                                <input type="text" class="form-control" placeholder="Enter your full name" name="memberName" id="memberName" value="<?php echo $member->getFullName(); ?>">
                            </div>
                            <!-- Full name -->

                            <!-- Birthday of member -->
                            <div class="form-group">
                                <label for="memberBirthday">Date of birth:</label>
                                <input type="date" class="form-control" name="memberBirthday" id="memberBirthday" value="<?php echo $member->getBirthday(); ?>">
                            </div>
                            <!-- Birthday of member -->

                            <!-- Gender of member -->
                            <div class="form-group">
                                <label for="Gender">Gender of the member:</label><br>
                                <select name="Gender" id="Gender" class="form-control">
                                    <option value="">
                                        Select gender...
                                    </option>
                                    <option value="nam">
                                        nam
                                    </option>
                                    <option value="nữ">
                                        nữ
                                    </option>
                                </select>
                            </div>
                            <!-- Gender of member -->

                            <!-- Level of member -->
                            <div class="form-group">
                                <label for="Level">Level of the member:</label><br>
                                <select name="Level" id="Level" class="form-control">
                                    <option value="">
                                        Select level...
                                    </option>
                                    <option value="1">
                                        level: 1
                                    </option>
                                    <option value="2">
                                        level: 2
                                    </option>
                                </select>
                            </div>
                            <!-- Level of member -->
                        </div>

                        <div class="col-lg-6 col-md-8 col-sm-12 col-12">
                            <!-- Email of member -->
                            <div class="form-group">
                                <label for="memberEmail">Member's email:</label>
                                <input type="email" class="form-control" placeholder="Enter your email" name="memberEmail" id="memberEmail" value="<?php echo $member->getEmail(); ?>">
                            </div>
                            <!-- Email of member -->

                            <!-- Password of member -->
                            <div class="form-group">
                                <label for="memberPassword">Member's password:</label>
                                <input type="password" class="form-control" placeholder="Enter your password" name="memberPassword" id="memberPassword" value="<?php echo $member->getPassword(); ?>">
                            </div>
                            <!-- Password of member -->

                            <!-- Member create at -->
                            <div class="form-group">
                                <label for="memberCreate">Creation time:</label>
                                <input type="date" class="form-control" id="memberCreate" placeholder="Enter creation time" name="memberCreate" value="<?php echo $member->getCreate_at(); ?>">
                            </div>
                            <!-- Member create at -->

                            <!-- Member update at -->
                            <div class="form-group">
                                <label for="memberUpdate">Update time:</label>
                                <input type="date" class="form-control" id="memberUpdate" placeholder="Enter update time" name="memberUpdate" value="<?php echo $member->getUpdate_at(); ?>">
                            </div>
                            <!-- Member update at -->
                        </div>
                    </div>
                    <hr>
                    <!-- Update member information -->
                    <div class="form-group" style="text-align: center;">
                        <a class="btn btn-outline-warning" style="width: 35%; border-radius: 20px" href="formEditMember.php">Reset</a>
                        <br><br>
                        <button type="submit" id="btnSaveMember" name="btnSaveMember" class="btn btn-outline-primary" style="width: 35%; border-radius: 20px">Save</button>
                        <button type="submit" id="btnUpdateMember" name="btnUpdateMember" class="btn btn-outline-info" style="width: 35%; border-radius: 20px">Update</button>
                    </div>
                    <!-- Update member information -->

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
            $('#tbMember').DataTable();
        });
    </script>

</html>