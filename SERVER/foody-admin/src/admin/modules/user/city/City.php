<?php

class City
{
    private $id;
    private $city_code;
    private $city_name;
    private $image_address;
    private $create_at;
    private $update_at;

    // function City($id, $city_code, $city_name)
    // {
    //     $this->ID = $id;
    //     $this->CityCode = $city_code;
    //     $this->CityName = $city_name;
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
     * Get the value of city_code.
     */
    public function getCity_code()
    {
        return $this->city_code;
    }

    /**
     * Set the value of city_code.
     *
     * @return self
     */
    public function setCity_code($city_code)
    {
        $this->city_code = $city_code;

        return $this;
    }

    /**
     * Get the value of city_name.
     */
    public function getCity_name()
    {
        return $this->city_name;
    }

    /**
     * Set the value of city_name.
     *
     * @return self
     */
    public function setCity_name($city_name)
    {
        $this->city_name = $city_name;

        return $this;
    }

    /**
     * Get the value of image_address.
     */
    public function getImage_address()
    {
        return $this->image_address;
    }

    /**
     * Set the value of image_address.
     *
     * @return self
     */
    public function setImage_address($image_address)
    {
        $this->image_address = $image_address;

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
}
