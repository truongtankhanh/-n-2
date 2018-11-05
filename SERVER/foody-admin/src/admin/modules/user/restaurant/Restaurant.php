<?php

class Restaurant
{
    private $id;
    private $resCode;
    private $resName;
    private $numberOfBranches;
    private $cityCode;
    private $create_at;
    private $update_at;

    // function Restaurant($id, $resCode, $resName, $resAddress, $imageAddress, $cityCode)
    // {
    //     $this->ID = $id;
    //     $this->ResCode = $resCode;
    //     $this->ResName = $resName;
    //     $this->ResAddress = $resAddress;
    //     $this->ImageAddress = $imageAddress;
    //     $this->CityCode = $cityCode;
    // }

    /**
     * Get the value of id.
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * Set the value of id.
     *
     * @return self
     */
    public function setId($id)
    {
        $this->id = $id;

        return $this;
    }

    /**
     * Get the value of resCode.
     */
    public function getResCode()
    {
        return $this->resCode;
    }

    /**
     * Set the value of resCode.
     *
     * @return self
     */
    public function setResCode($resCode)
    {
        $this->resCode = $resCode;

        return $this;
    }

    /**
     * Get the value of resName.
     */
    public function getResName()
    {
        return $this->resName;
    }

    /**
     * Set the value of resName.
     *
     * @return self
     */
    public function setResName($resName)
    {
        $this->resName = $resName;

        return $this;
    }

    /**
     * Get the value of cityCode.
     */
    public function getCityCode()
    {
        return $this->cityCode;
    }

    /**
     * Set the value of cityCode.
     *
     * @return self
     */
    public function setCityCode($cityCode)
    {
        $this->cityCode = $cityCode;

        return $this;
    }

    /**
     * Get the value of imageAddress.
     */
    public function getImageAddress()
    {
        return $this->imageAddress;
    }

    /**
     * Set the value of imageAddress.
     *
     * @return self
     */
    public function setImageAddress($imageAddress)
    {
        $this->imageAddress = $imageAddress;

        return $this;
    }

    /**
     * Get the value of create_at.
     */
    public function getCreate_at()
    {
        return $this->create_at;
    }

    /**
     * Set the value of create_at.
     *
     * @return self
     */
    public function setCreate_at($create_at)
    {
        $this->create_at = $create_at;

        return $this;
    }

    /**
     * Get the value of update_at.
     */
    public function getUpdate_at()
    {
        return $this->update_at;
    }

    /**
     * Set the value of update_at.
     *
     * @return self
     */
    public function setUpdate_at($update_at)
    {
        $this->update_at = $update_at;

        return $this;
    }

    /**
     * Get the value of numberOfBranches.
     */
    public function getNumberOfBranches()
    {
        return $this->numberOfBranches;
    }

    /**
     * Set the value of numberOfBranches.
     *
     * @return self
     */
    public function setNumberOfBranches($numberOfBranches)
    {
        $this->numberOfBranches = $numberOfBranches;

        return $this;
    }
}
