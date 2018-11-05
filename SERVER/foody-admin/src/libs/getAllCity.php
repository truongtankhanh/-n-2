<?php

require '../admin/database/dbConnect.php';
require '../admin/modules/user/city/getAllCity.php';

class City
{
    private $id;
    private $city_code;
    private $city_name;
    private $city_image;

    public function __construct($id, $city_code, $city_name, $city_image)
    {
        $this->ID = $id;
        $this->CityCode = $city_code;
        $this->CityName = $city_name;
        $this->CityImage = $city_image;
    }
}

$arrayCity = array();

while ($row = mysqli_fetch_assoc($resultCity)) {
    array_push($arrayCity, new City($row['id'], $row['city_code'], $row['city_name'], $row['image_address']));
}

echo json_encode($arrayCity);
