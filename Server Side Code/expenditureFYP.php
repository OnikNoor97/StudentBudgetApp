<?php  

	include 'connectionFYP.php';

	function getAccount($bank)
	{
		include 'connectionFYP.php';
		$sql = 'SELECT currentBalance, income, expenditure, bankId FROM Bank WHERE bankId = :bankId';
		$stmt = $conn->prepare($sql);
		$stmt->bindParam(':bankId', $bank);
		$stmt->execute();
		$results = $stmt->fetch(PDO::FETCH_ASSOC);
		return $results;
	}

	function updateBank($bank, $expenditure)
	{
		$bankAccount = getAccount($bank);
		$expenditureFromDatabase = $bankAccount['expenditure'];
		$expenditureUpdated = $expenditureFromDatabase + 1;
		$currentBalanceFromDatabase = $bankAccount['currentBalance'];
		$currentBalanceUpdated = $currentBalanceFromDatabase - $expenditure;

		include 'connectionFYP.php';
		$sql = 'UPDATE Bank SET currentBalance = :currentBalanceUpdated, expenditure = :expenditureUpdated WHERE bankId = :bankId';
		$stmt = $conn->prepare($sql);
		$stmt->bindParam(':currentBalanceUpdated', $currentBalanceUpdated);
		$stmt->bindParam(':expenditureUpdated', $expenditureUpdated);
		$stmt->bindParam(':bankId', $bank);
		$stmt->execute();
	}

	function createTransaction($amount, $description, $date, $bank)
	{
		include 'connectionFYP.php';
		$sql = "INSERT INTO Expenditure (amount, description, date, bankId) VALUES (:amount, :description, :date, :bankId)";
		$stmt = $conn->prepare($sql);
		$stmt->bindParam(':amount', $amount);
		$stmt->bindParam(':description', $description);
		$stmt->bindParam(':date', $date);
		$stmt->bindParam(':bankId', $bank);
		$stmt->execute();
	}

	$bankId = $_POST['bankId'];
	$expenditure = $_POST['expenditure'];
	$description = $_POST['description'];
	$date = date("Y-m-d H:i:s");
	$end = array();


	if($bankId == null || $expenditure == null || $description == null)
	{
		$end['success'] = 'false';
		$end['message'] = 'Please enter all fields'	;
	}
	else
	{
		$end['success'] = 'true';
		$end['message'] = 'Transaction has been successful';

		updateBank($bankId, $expenditure);
		createTransaction($expenditure, $description, $date, $bankId);

		$results = getAccount($bankId);
		$end['income'] = $results['income'];
		$end['expenditure'] = $results['expenditure'];
		$end['currentBalance'] = $results['currentBalance']; 
	}
	
	echo json_encode($end);
?>