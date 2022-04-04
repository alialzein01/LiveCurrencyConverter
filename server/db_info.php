<?php

$server = "localhost";
$username = "root";
$password = null;
$database_name = "alihadi_db";

//connecting the local database and initializing it
$mysqli = new mysqli($server, $username, $password, $database_name);

if(mysqli_connect_errno()){
    die("Conenction Failed!");
}


?>