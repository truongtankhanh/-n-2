<?php

// Import class
include 'City.php';
require 'src/admin/modules/user/config/config.php';

// Create variable
$city = new City();

// Import file function
require 'src/admin/modules/user/city/getAllCity.php';
require 'src/admin/modules/user/city/addCity.php';
require 'src/admin/modules/user/city/updateCity.php';
require 'src/admin/modules/user/city/deleteCity.php';
