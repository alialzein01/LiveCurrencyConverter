<?php

$server = "localhost";
$username = "root";
$password = null;
$database_name = "alihadi_db";

$mysqli = new mysqli($server, $username, $password, $database_name);

if(mysqli_connect_errno()){
    die("Conenction Failed!");
}

$live_rate = $_POST["live_rate"];
$usd_rate = $_POST["usd_rate"];
$lbp_rate = $_POST["lbp_rate"]

$query = $mysqli->prepare("INSERT INTO historyofrate (live_rate, usd_rate,lbp_rate) VALUES (?, ?, ?,?)");
$query->bind_param("ddd", $live_rate, $usd_rate,$lbp_rate);
$query->execute();

$response = [];
$response["status"] = "Mabrouk!";

$json_response = json_encode($response);
echo $json_response;

?>