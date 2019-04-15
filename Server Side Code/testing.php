<?php 

$userId = $_POST['userId'];

function testinglol($userId)
{
	include 'connectionFYP.php';
	$stmt = $conn->prepare("SELECT SUM(moduleWeight) AS total FROM Module WHERE userId = :userId");
	$stmt->bindParam(':userId', $userId);
	$stmt->execute();
	$sum = $stmt->fetch(PDO::FETCH_ASSOC);
	$lol = $sum['total'];
	return $lol;
}

$haha = testinglol($userId);
echo $haha;

?>