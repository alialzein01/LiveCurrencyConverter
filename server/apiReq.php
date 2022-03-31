<?php

$url = "https://lirarate.org/wp-json/lirarate/v2/rates?currency=LBP&_ver=t202233118";
$curl = curl_init();
curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
curl_setopt($curl, CURLOPT_URL, $url);
$resp = curl_exec($curl);
$decoded = json_decode($resp,true);

$sizeArr = sizeof($decoded['buy']);
$buy = $decoded['buy'][$sizeArr-1]["1"];
echo $buy;

$sizeArr2 = sizeof($decoded['sell']);
$sell = $decoded['sell'][$sizeArr2-1]["1"];
echo (" ");
echo $sell;

curl_close($curl);

?>