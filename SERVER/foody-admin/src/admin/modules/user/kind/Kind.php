<?php

class Kind
{
    private $kindID;
    private $kindCode;
    private $kindName;
    private $create_at;
    private $update_at;

    // public function __construct()
    // {
    // }

    // public function __construct($kindID, $kindCode, $kindName)
    // {
    //     setKindID($kindID);
    //     setKindCode($kindCode);
    //     setKindName($kindName);
    // }

    /**
     * Get the value of kindName.
     */
    public function getKindName()
    {
        return $this->kindName;
    }

    /**
     * Set the value of kindName.
     *
     * @return self
     */
    public function setKindName($kindName)
    {
        $this->kindName = $kindName;

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

    /**
     * Get the value of kindID.
     */
    public function getKindID()
    {
        return $this->kindID;
    }

    /**
     * Set the value of kindID.
     *
     * @return self
     */
    public function setKindID($kindID)
    {
        $this->kindID = $kindID;

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
