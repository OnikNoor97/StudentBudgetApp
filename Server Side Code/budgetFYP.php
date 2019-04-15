<?php

	error_reporting(0);

	

	function createAccount($user)
	{
		include 'connectionFYP.php';
		$create ="INSERT INTO Bank (currentBalance, income, expenditure, userId) VALUES (0, 0, 0, :userId)";
		$stmt = $conn->prepare($create);
		$stmt->bindParam(':userId', $user);
		$stmt->execute();
		return $stmt;
	}

	function getAccount($user)
	{
		include 'connectionFYP.php';
		$sql = 'SELECT currentBalance, income, expenditure, bankId FROM Bank WHERE userId = :userId';
		$stmt = $conn->prepare($sql);
		$stmt->bindParam(':userId', $user);
		$stmt->execute();
		return $stmt;
	}

	function selectAccount($user)
	{
		$stmt = getAccount($user);
		$results = $stmt->fetch(PDO::FETCH_ASSOC);
		return $results;
	}

	$userId = $_POST['userId'];
	$end = array();
	$stmt = getAccount($userId);

	if($userId == null)
	{
		$end['success'] = 'false';
		$end['message'] = 'User not found...';
	}
	else
	{
		if(!$stmt->rowCount())
		{
			createAccount($userId);
			$results = selectAccount($userId);

			$end['success'] = "true";
			$end['message'] = "New user detected, creating bank account";
			$end['currentBalance'] = $results['currentBalance'];
			$end['income'] = $results['income'];
			$end['expenditure'] = $results['expenditure'];
			$end['bankId'] = $results['bankId'];
		}
		else
		{
			$results = selectAccount($userId);

			$end['success'] = "true";
			$end['message'] = "Bank account found";
			$end['currentBalance'] = $results['currentBalance'];
			$end['income'] = $results['income'];
			$end['expenditure'] = $results['expenditure'];
			$end['bankId'] = $results['bankId'];
		}
}

	echo json_encode($end);	
?>