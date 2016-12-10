<?php

$stars =urldecode($_POST['stars']);
$feedback=urldecode($_POST['feedback']);
$servername = 'localhost:3306';

/*** mysql username ***/
$username = '********'; // Username hidden for security purposes

/*** mysql password ***/
$password = '*********'; //Password not given for security purposes

$dbname = "********";

// Create connection
$conn = mysqli_connect($servername, $username, $password, $dbname);
// Check connection
if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}

$sql = "INSERT INTO recipe 
VALUES ('$stars', '$feedback')";

if (mysqli_query($conn, $sql)) {
    echo "New record created successfully";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>