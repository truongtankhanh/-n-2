<?php

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
    private $create_at;
    private $update_at;

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
     * Get the value of resBranchCode.
     */
    public function getResBranchCode()
    {
        return $this->resBranchCode;
    }

    /**
     * Set the value of resBranchCode.
     *
     * @return self
     */
    public function setResBranchCode($resBranchCode)
    {
        $this->resBranchCode = $resBranchCode;

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
     * Get the value of resBranchName.
     */
    public function getResBranchName()
    {
        return $this->resBranchName;
    }

    /**
     * Set the value of resBranchName.
     *
     * @return self
     */
    public function setResBranchName($resBranchName)
    {
        $this->resBranchName = $resBranchName;

        return $this;
    }

    /**
     * Get the value of resBranchAddress.
     */
    public function getResBranchAddress()
    {
        return $this->resBranchAddress;
    }

    /**
     * Set the value of resBranchAddress.
     *
     * @return self
     */
    public function setResBranchAddress($resBranchAddress)
    {
        $this->resBranchAddress = $resBranchAddress;

        return $this;
    }

    /**
     * Get the value of resBranchOpenTime.
     */
    public function getResBranchOpenTime()
    {
        return $this->resBranchOpenTime;
    }

    /**
     * Set the value of resBranchOpenTime.
     *
     * @return self
     */
    public function setResBranchOpenTime($resBranchOpenTime)
    {
        $this->resBranchOpenTime = $resBranchOpenTime;

        return $this;
    }

    /**
     * Get the value of resBranchPrice.
     */
    public function getResBranchPrice()
    {
        return $this->resBranchPrice;
    }

    /**
     * Set the value of resBranchPrice.
     *
     * @return self
     */
    public function setResBranchPrice($resBranchPrice)
    {
        $this->resBranchPrice = $resBranchPrice;

        return $this;
    }

    /**
     * Get the value of resBranchImage.
     */
    public function getResBranchImage()
    {
        return $this->resBranchImage;
    }

    /**
     * Set the value of resBranchImage.
     *
     * @return self
     */
    public function setResBranchImage($resBranchImage)
    {
        $this->resBranchImage = $resBranchImage;

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
