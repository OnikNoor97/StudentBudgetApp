<?php

//error_reporting(0);

function createModule($name, $moduleWeight, $userId)
{
	include 'connectionFYP.php';
	$sql = "INSERT INTO Module (name, moduleWeight, userId) VALUES (:name, :moduleWeight, :userId)";
	$stmt = $conn->prepare($sql);
	$stmt->bindParam(':name', $name, PDO::PARAM_STR);
	$stmt->bindParam(':moduleWeight', $moduleWeight, PDO::PARAM_STR);
	$stmt->bindParam(':userId', $userId, PDO::PARAM_STR);
	$stmt->execute();
	return $stmt;
}

function moduleExist($name, $userId)
{
	include 'connectionFYP.php';
	$sql = 'SELECT name FROM Module WHERE name = :name AND userId = :userId';
	$stmt = $conn->prepare($sql);
	$stmt->bindParam(':name', $name);
	$stmt->bindParam(':userId', $userId);
	$stmt->execute();

	if ($stmt->rowCount() > 0)
	{
		$checker = 'true';
	}
	else
	{
		$checker = 'false';
	}
	return $checker;
}

function moduleWeightSoFar($userId)
{
	include 'connectionFYP.php';
	$sql = "SELECT SUM(moduleWeight) AS total FROM Module WHERE userId = :userId";
	$stmt = $conn->prepare($sql);
	$stmt->bindParam(':userId', $userId);
	$stmt->execute();
	$value = $stmt->fetch(PDO::FETCH_ASSOC);
	$sum = $value['total'];
	return $sum;
}


$moduleName = $_POST['name'];
$moduleWeight = $_POST['moduleWeight'];
$user = $_POST['userId'];
$end = array();
$moduleExist = moduleExist($moduleName, $user);
$totalWeightBefore = moduleWeightSoFar($user);
$totalWeightNow = $totalWeightBefore + $moduleWeight;

if ($moduleExist == 'true')
{
	$end['success'] = 'false';
	$end['message'] = 'Module already exist!';
}
else if ($totalWeightNow > 100)
{
	$end['success'] = 'false';
	$end['message'] = 'Module Weight too high, exceeding 100%';
}
else
{
	$end['success'] = 'true';
	createModule($moduleName, $moduleWeight, $user);
	$end['message'] = 'Module is created';
}

echo json_encode($end);

?>