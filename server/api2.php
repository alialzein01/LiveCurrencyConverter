<?php

include ("db_info.php");

$live_rate = $_GET["live_rate"];
$first_rate = $_GET["first_rate"];
$second_rate = $_GET["second_rate"];
$amount_converted = $_GET["amount"];



$usd_res = 0;
$ll_res = 0;

if($first_rate == "LL") {
    $usd_res = $amount_converted / $live_rate;
} else {
    $ll_res = $amount_converted * $live_rate;
}

$query = $mysqli->prepare("INSERT INTO historyofrate (live_rate, first_rate,second_rate,amount_converted) VALUES ($live_rate, $first_rate, $second_rate,$amount_converted)");
$query->execute();

echo json_encode($usd_res). " ".json_encode($ll_res);

?>