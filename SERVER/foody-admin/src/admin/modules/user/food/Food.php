<?php

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
    private $create_at;
    private $update_at;

    // public function __construct($id, $foodCode, $foodName, $foodAddress, $foodPrice, $imageAddress)
    // {
    //     $this->ID = $id;
    //     $this->FoodCode = $foodCode;
    //     $this->FoodName = $foodName;
    //     $this->FoodAddress = $foodAddress;
    //     $this->FoodPrice = $foodPrice;
    //     $this->ImageAddress = $imageAddress;
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
     * Get the value of foodCode.
     */
    public function getFoodCode()
    {
        return $this->foodCode;
    }

    /**
     * Set the value of foodCode.
     *
     * @return self
     */
    public function setFoodCode($foodCode)
    {
        $this->foodCode = $foodCode;

        return $this;
    }

    /**
     * Get the value of foodName.
     */
    public function getFoodName()
    {
        return $this->foodName;
    }

    /**
     * Set the value of foodName.
     *
     * @return self
     */
    public function setFoodName($foodName)
    {
        $this->foodName = $foodName;

        return $this;
    }

    /**
     * Get the value of foodPrice.
     */
    public function getFoodPrice()
    {
        return $this->foodPrice;
    }

    /**
     * Set the value of foodPrice.
     *
     * @return self
     */
    public function setFoodPrice($foodPrice)
    {
        $this->foodPrice = $foodPrice;

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
     * Get the value of foodAddress.
     */
    public function getFoodAddress()
    {
        return $this->foodAddress;
    }

    /**
     * Set the value of foodAddress.
     *
     * @return self
     */
    public function setFoodAddress($foodAddress)
    {
        $this->foodAddress = $foodAddress;

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
     * Get the value of kindCode.
     */
    public function getKindCode()
    {
        return $this->kindCode;
    }

    /**
     * Set the value of kindCode.
     *
     * @return self
     */
    public function setKindCode($kindCode)
    {
        $this->kindCode = $kindCode;

        return $this;
    }
}
