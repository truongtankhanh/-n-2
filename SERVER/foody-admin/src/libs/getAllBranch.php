<?php

require '../admin/database/dbConnect.php';
require '../admin/modules/user/branch/getAllBranch.php';

class Branch
{
    private $id;
    private $resBranchCode;
    private $resCode;
    private $resBranchName;
    private $resBranchAddress;
    private $resBranchOpenTime;
    private $resBranchPrice;
    private $resBranchImage;

    public function __construct($id, $resBranchCode, $resCode, $resBranchName, $resBranchAddress, $resBranchOpenTime, $resBranchPrice, $resBranchImage)
    {
        $this->ID = $id;
        $this->ResBranchCode = $resBranchCode;
        $this->ResCode = $resCode;
        $this->ResBranchName = $resBranchName;
        $this->ResBranchAddress = $resBranchAddress;
        $this->ResBranchOpenTime = $resBranchOpenTime;
        $this->ResBranchPrice = $resBranchPrice;
        $this->ResBranchImage = $resBranchImage;
    }
}

$arrayBranch = array();

while ($row = mysqli_fetch_assoc($resultBranch)) {
    array_push($arrayBranch, new Branch($row['id'], $row['res_branch_code'], $row['res_code'], $row['res_branch_name'], $row['res_branch_address'], $row['res_branch_opentime'], $row['res_branch_price'], $row['res_branch_image']));
}

echo json_encode($arrayBranch);
