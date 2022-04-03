<?php

include ("db_info.php");

// getting the values from android studio
$buy_rate = $_GET["buy_rate"];
$sell_rate = $_GET["sell_rate"];
$first_rate = $_GET["first_rate"];
$second_rate = $_GET["second_rate"];
$amount_converted = $_GET["amount"];



$usd_res = 0;
$ll_res = 0;

// conversion in backend
if($first_rate == "LL") {
    $usd_res = $amount_converted / $buy_rate;
} else {
    $ll_res = $amount_converted * $sell_rate;
}

// inserting to db
$query = $mysqli->prepare("INSERT INTO historyofrate (buy_rate,sell_rate, first_rate,second_rate,amount_converted) VALUES ($buy_rate,$sell_rate, $first_rate, $second_rate,$amount_converted)");
$query->execute();

echo json_encode($usd_res). " ".json_encode($ll_res);

?>