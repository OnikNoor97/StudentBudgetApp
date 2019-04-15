<?php  

function getIncomes($bank)
{
	include 'connectionFYP.php';
	$sql = 'SELECT incomeId, amount, description, date FROM Income WHERE bankId = :bankId';
		$stmt = $conn->prepare($sql);
		$stmt->bindParam(':bankId', $bank);
		$stmt->execute();
		$results = $stmt->fetchAll();
		return $results;
}

function getExpenditures($bank)
{
	include 'connectionFYP.php';
	$sql = 'SELECT expenditureId, amount, description, date FROM Expenditure WHERE bankId = :bankId';
		$stmt = $conn->prepare($sql);
		$stmt->bindParam(':bankId', $bank);
		$stmt->execute();
		$results = $stmt->fetchAll();
		return $results;
}


$bankId = $_POST['bankId'];
$data = array();

if ($bankId == null)
{
	$data['success'] = 'false';
	$data['message'] = 'Bank Account not found';
}
else
{
	$data['success'] = 'true';
	$data['message'] = "Bank account has been found";
	$data['incomes'] = getIncomes($bankId);
	$data['expenditures'] = getExpenditures($bankId);
	$data['incomesSize'] = sizeof(getIncomes($bankId));
	$data['expendituresSize'] = sizeof(getExpenditures($bankId));
}
	echo json_encode($data);
	
?>