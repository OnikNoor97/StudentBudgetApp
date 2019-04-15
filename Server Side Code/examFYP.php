<?php  

function findId($module)
{
	include 'connectionFYP.php';
	$sql = 'SELECT moduleId FROM Module WHERE name = :name';
	$stmt = $conn->prepare($sql);
	$stmt->bindParam(':name', $module, PDO::PARAM_STR);
	$stmt->execute();
	$data = $stmt->fetch(PDO::FETCH_ASSOC);
	$id = $data['moduleId'];
	return $id;
}

function addExam($moduleId, $name, $date, $weight)
{
	include 'connectionFYP.php';
	$sql = 'INSERT INTO Exam (name, date, weight, grade, moduleId) VALUES (:name, :date, :weight, 0, :moduleId)';
	$stmt = $conn->prepare($sql);
	$stmt->bindParam(':name', $name, PDO::PARAM_STR);
	$stmt->bindParam(':date', $date, PDO::PARAM_STR);
	$stmt->bindParam(':weight', $weight, PDO::PARAM_STR);
	$stmt->bindParam(':moduleId', $moduleId, PDO::PARAM_STR);
	$stmt->execute();
}

function getCourseworkWeight($moduleId)
{
	include 'connectionFYP.php';
	$sql = "SELECT SUM(weight) AS total FROM Coursework WHERE moduleId = :moduleId";	
	$stmt = $conn->prepare($sql);
	$stmt->bindParam(':moduleId', $moduleId);
	$stmt->execute();
	$value = $stmt->fetch(PDO::FETCH_ASSOC);
	$sum = $value['total'];
	return $sum;
}

function getExamWeight($moduleId)
{
	include 'connectionFYP.php';
	$sql = "SELECT SUM(weight) AS total FROM Exam WHERE moduleId = :moduleId";
	$stmt = $conn->prepare($sql);
	$stmt->bindParam(':moduleId', $moduleId);
	$stmt->execute();
	$value = $stmt->fetch(PDO::FETCH_ASSOC);
	$sum = $value['total'];
	return $sum;
}


$name = $_POST['name'];
$dateFromAndroid = $_POST['date'];
$weight = $_POST['weight'];
$module = $_POST['module'];

$end = array();
$moduleId = findId($module);
$dateConverted = str_replace('/', '-', $dateFromAndroid);
$weightfromCoursework = getCourseworkWeight($moduleId);
$weightFromExam = + getExamWeight($moduleId);
$totalWeight = $weightfromCoursework + $weightFromExam + $weight;

if ($module == null || $name == null || $dateFromAndroid == null || $weight == null)
{
	$end['success'] = 'false';
	$end['message'] = 'Please fill in all the fields provided';
}
else if ($totalWeight > 100)
{
	$end['success'] = 'false';
	$end['message'] = 'The Exam weight you entered exceeds 100% total weight of the module';
}
else
{
	addExam($moduleId, $name, $dateConverted, $weight);
	$end['success'] = 'true';
	$end['message'] = 'Exam successfully added';
}

echo json_encode($end);

?>