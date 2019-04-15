<?php 

function getModuleNames($userId)
{
	include 'connectionFYP.php';
	$sql = "SELECT moduleId, name, userId FROM Module WHERE userId = :userId";
	$stmt = $conn->prepare($sql);
	$stmt->bindParam(':userId', $userId, PDO::PARAM_STR);
	$stmt->execute();
	$data = $stmt->fetchAll();
	return $data;
}

$user = $_POST['userId']; 
$end = array();
$modules = getModuleNames($user); 

if ($user == null)
{
	$end['success'] = "false";
	$end['message'] = "User not found";
}
else if ($modules == null)
{
	$end['success'] = "false";
	$end['message'] = "No Modules found, please create one!";
}
else
{
	$end['success'] = "true";
	$end['message'] = "Modules have been found";
	$end['modules'] = $modules;
	$end['moduleSize'] = sizeof($modules);
}

echo json_encode($end);

?>