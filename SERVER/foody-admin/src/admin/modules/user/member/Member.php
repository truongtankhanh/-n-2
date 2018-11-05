<?php

class Member
{
    private $id;
    private $fullName;
    private $birthday;
    private $sex;
    private $email;
    private $password;
    private $level;
    private $create_at;
    private $update_at;

    // function Member($id, $fullName, $birthday, $sex, $email, $password)
    // {
    //     $this->ID = $id;
    //     $this->FullName = $fullName;
    //     $this->Birthday = $birthday;
    //     $this->Sex = $sex;
    //     $this->Email = $email;
    //     $this->Password = $password;
    // }

    /**
     * Get the value of email.
     */
    public function getEmail()
    {
        return $this->email;
    }

    /**
     * Set the value of email.
     *
     * @return self
     */
    public function setEmail($email)
    {
        $this->email = $email;

        return $this;
    }

    /**
     * Get the value of password.
     */
    public function getPassword()
    {
        return $this->password;
    }

    /**
     * Set the value of password.
     *
     * @return self
     */
    public function setPassword($password)
    {
        $this->password = $password;

        return $this;
    }

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
     * Get the value of fullName.
     */
    public function getFullName()
    {
        return $this->fullName;
    }

    /**
     * Set the value of fullName.
     *
     * @return self
     */
    public function setFullName($fullName)
    {
        $this->fullName = $fullName;

        return $this;
    }

    /**
     * Get the value of birthday.
     */
    public function getBirthday()
    {
        return $this->birthday;
    }

    /**
     * Set the value of birthday.
     *
     * @return self
     */
    public function setBirthday($birthday)
    {
        $this->birthday = $birthday;

        return $this;
    }

    /**
     * Get the value of sex.
     */
    public function getSex()
    {
        return $this->sex;
    }

    /**
     * Set the value of sex.
     *
     * @return self
     */
    public function setSex($sex)
    {
        $this->sex = $sex;

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
     * Get the value of level.
     */
    public function getLevel()
    {
        return $this->level;
    }

    /**
     * Set the value of level.
     *
     * @return self
     */
    public function setLevel($level)
    {
        $this->level = $level;

        return $this;
    }
}
