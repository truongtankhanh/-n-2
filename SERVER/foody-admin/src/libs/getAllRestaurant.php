<?php

require '../admin/database/dbConnect.php';
require '../admin/modules/user/restaurant/getAllRestaurant.php';

class Restaurant
{
    private $id;
    private $resCode;
    private $resName;
    private $numberOfBranches;
    private $imageAddress;
    private $cityCode;

    public function __construct($id, $resCode, $resName, $numberOfBranches, $imageAddress, $cityCode)
    {
        $this->ID = $id;
        $this->ResCode = $resCode;
        $this->ResName = $resName;
        $this->NumberOfBranches = $numberOfBranches;
        $this->ImageAddress = $imageAddress;
        $this->CityCode = $cityCode;
    }
}

$arrayRes = array();

while ($row = mysqli_fetch_assoc($resultRestaurant)) {
    array_push($arrayRes, new Restaurant($row['id'], $row['res_code'], $row['res_name'], $row['number_of_branches'], $row['image_address'], $row['city_code']));
}

echo json_encode($arrayRes);
