<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
<title>First Web Application</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>

<body>
    Establishments:
    <c:forEach var="i" items="${list}">
    <div>
	    ${i.name}<br/>
	    ${i.address}<br/>       
	    <a href="/establishment/<c:out value='${i.id}'/>">Available screenings:</a><br/> 
    </div>
</c:forEach> 
<button id="addBtn">Add</button>
</body>
<script>
$('#addBtn').on('click', function() {
	    window.location.href = '/establishment/addEstablishment';
});
Colors = {};
Colors.names = {
    lightblue: "#add8e6",
    lightcyan: "#e0ffff",
    lightgreen: "#90ee90",
    lightgrey: "#d3d3d3",
    lightpink: "#ffb6c1",
    lightyellow: "#ffffe0",
};
Colors.random = function() {
    var result;
    var count = 0;
    for (var prop in this.names)
        if (Math.random() < 1/++count)
           result = prop;
    return result;
};
var divs = document.getElementsByTagName('div');
for(var i =0; i < divs.length; i++){
	divs[i].style.background = Colors.random();
	console.log(Colors.random());
	}
</script>
</html>