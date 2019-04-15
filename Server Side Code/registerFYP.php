<?php   

//error_reporting(0);

include "connectionFYP.php";

$end = array();
$forename = $_POST['forename'];
$surname = $_POST['surname'];
$username = $_POST['username'];
$password = $_POST['password'];

if ($forename == null || $surname == null || 
    $username == null || $password == null)
{
  $end["message"] = "Please fill out all the fields";
  $end["success"] =  "false";
}
else 
{
  $sql = "INSERT INTO User (forename, surname, username, password) VALUES (:forename, :surname, :username, :password)";

    $stmt = $conn->prepare($sql);
    $stmt->bindParam(':forename', $forename, PDO::PARAM_STR);
    $stmt->bindParam(':surname', $surname, PDO::PARAM_STR);
    $stmt->bindParam(':username', $username, PDO::PARAM_STR);
    $stmt->bindParam(':password', password_hash($password, PASSWORD_BCRYPT));

    $dupHandler = $conn->prepare("SELECT username FROM User WHERE username = :username");
    $dupHandler->bindParam(':username', $username);

    $dupHandler->execute();
    
    if($dupHandler->rowCount() > 0)
    {
        $end['duplicate'] = "true";      
        $end['success'] = "false";
        $end['message'] = "Username taken! Please try a different username or Login!";
    }
    else
    {
      if ($stmt->execute())
      {   
        $end["success"] = "true";
        $end['duplicate'] = "false"; 
        $end["message"] = "Registration has been successful! Please login!";
      }
      else
      {
        $end["success"] = "false";
        $end['duplicate'] = "false"; 
        $end["message"] = "Registration failed!";
      }
    }
}
  echo json_encode($end);
?>