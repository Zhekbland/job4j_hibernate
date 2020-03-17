<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Users</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    <style>
        body {
            font-family: 'Roboto', -apple-system, system-ui, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
        }
    </style>
    <script>
        function getAdverts() {
            $.ajax({
                url: '${pageContext.servletContext.contextPath}/adverts',
                method: 'POST',
                dataType: 'JSON',
                complete: function (data) {
                    let cars = new Object();
                    cars = JSON.parse(data.responseText);
                    for (let i = 0; i < cars.length; i++) {
                        for (let j = 0; j < cars[i].length; j++) {
                            for (let value in cars[i][j]) {
                                console.log(value)
                            }
                        }
                    }
                }
            });
        }
    </script>
</head>
<body>

<div class="container">
    <div class="row d-flex justify-content-end mt-4">
        <form class="mx-5" action="${pageContext.servletContext.contextPath}/myadvert" method="get">
            <button type="submit" class="btn btn-secondary">My adverts</button>
        </form>
        <form class="mx-5" action="${pageContext.servletContext.contextPath}/create" method="get">
            <button type="submit" class="btn btn-secondary">Create New</button>
        </form>
        <form action="${pageContext.servletContext.contextPath}/signout" method="post">
            <button type="submit" class="btn btn-secondary">SignOut</button>
        </form>
    </div>
</div>
<div class="container my-4">
    <table class="table">
        <thead class="thead-dark">
        </thead>
        <tbody>
        <c:forEach items="${cars}" var="car">
            <c:if test="${car.onSale}">
                <tr id="<c:out value="${car.id}"/>">

                    <td>
                        <img id="picByte" src="${applicationScope.get("imgContextPath")}${car.picture.fileName}"
                             width="200"/>
                    </td>
                    <td>
                        <c:out value="${car.carMake.name}"/>
                        <br><br>
                        <c:out value="${car.model.name}"/>
                    </td>

                    <td>
                        Mileage: <c:out value="${car.carMileage}"/>
                        <br><br>
                        Year: <c:out value="${car.year}"/>
                        <br><br>
                        Price: <c:out value="${car.price}"/>
                        <br><br>
                        Seller: <c:out value="${car.user.name}"/>
                    </td>
                    <td>
                        Body type: <c:out value="${car.bodyType.name}"/>
                        <br><br>
                        Engine: <c:out value="${car.engineType.name}"/>
                        <br><br>
                        Gearbox: <c:out value="${car.gearboxType.name}"/>
                    </td>
                </tr>
            </c:if>
        </c:forEach>
        </tbody>
    </table>
</div>
<div class="container">
</div>
</body>
</html>