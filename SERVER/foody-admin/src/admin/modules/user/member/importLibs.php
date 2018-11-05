<?php

// Import class
include 'Member.php';
require 'config.php';

// Create variable
$member = new Member();

// Import file function
require 'getAllMember.php';
require 'deleteMember.php';
require 'editMember.php';
require 'insertMember.php';
