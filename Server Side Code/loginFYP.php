<?php

error_reporting(0);

      include 'connectionFYP.php';
      
      $username = $_POST['username'];
      $password = $_POST['password'];
      $end = array();

      if ($username == null || $password == null)
      {
        $end['success'] = "false";
        $end['message'] = "Please enter both the username and password";
      }  
      else 
      {
          $sql = 'SELECT userId, forename, surname, password FROM User 
                  WHERE username = :username';

          $stmt = $conn->prepare($sql);
          $stmt->bindParam(':username', $username);
          $stmt->execute();
          $results = $stmt->fetch(PDO::FETCH_ASSOC);

          if($stmt->rowCount() && password_verify($password, $results['password']) )
          {
            $end["success"] = "true";
            $end["message"] = "Login has been success!";
            $end["forename"] = $results['forename'];
            $end["surname"] = $results['surname'];
            $end["userId"] = $results['userId'];
          }
            else
          {
              $end["success"] = "false";
              $end['message'] = "Username or Password does not match, please try again!";
          }
      }   
             echo json_encode($end);  
?>