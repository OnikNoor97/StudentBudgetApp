<?php
try
	{
		$servername = "kunet.kingston.ac.uk";
		$dbusername = "k1517267";
		$dbpassword = "password";
		$dbname = "dbFk1517267";
		$conn = new PDO("mysql:host=$servername;dbname=$dbname", $dbusername, $dbpassword);
		$conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

		//echo "Connection Successful, Database is found";
	}
	catch(PDOException $e)
	{
		die("Error!: " . $e->getMessage());            
	} 
?>