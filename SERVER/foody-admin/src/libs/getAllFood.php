<?php

require '../admin/database/dbConnect.php';
require '../admin/modules/user/food/getAllFood.php';

class Food
{
    private $id;
    private $foodCode;
    private $foodName;
    private $foodAddress;
    private $foodPrice;
    private $imageAddress;
    private $resCode;
    private $kindCode;

    public function __construct($id, $foodCode, $foodName, $foodAddress, $foodPrice, $imageAddress, $resCode, $kindCode)
    {
        $this->ID = $id;
        $this->FoodCode = $foodCode;
        $this->FoodName = $foodName;
        $this->FoodAddress = $foodAddress;
        $this->FoodPrice = $foodPrice;
        $this->ImageAddress = $imageAddress;
        $this->ResCode = $resCode;
        $this->KindCode = $kindCode;
    }
}

$arrayFood = array();

while ($row = mysqli_fetch_assoc($resultFood)) {
    array_push($arrayFood, new Food($row['id'], $row['food_code'], $row['food_name'], $row['food_address'], $row['food_price'], $row['image_address'], $row['res_code'], $row['kind_code']));
}

echo json_encode($arrayFood);
