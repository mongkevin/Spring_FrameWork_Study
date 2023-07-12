<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  login : ${not empty loginUser } </P>
<h5> userId : ${loginUser } </h5>

</body>
</html>
