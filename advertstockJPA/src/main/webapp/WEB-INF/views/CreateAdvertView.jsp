<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login</title>
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
        function validate() {
            var result = true;
            let arrAlert = new Array();
            for (let input of $(".carCreator")) {
                if (input.value == '') {
                    arrAlert.push("Fill " + input.getAttribute('name') + " form!");
                    // alert("Fill " + input.getAttribute('name') + " form!");
                    result = false;
                }
            }
            if (!result) {
                alert(arrAlert.join('\n'));
            }
            return result;
        }

        function checkFile() {
            console.log($('#myPic')[0].files[0].size);
            let form = document.forms.create;
            let elem = form.elements.fileUpload;
            let size = elem.files[0].size;
            if (elem.files[0].size > 1_000_000) {
                alert("File is too big!!!");
                elem.value = "";
            }
        }

        function previewFile() {
            var preview = document.querySelector('img');
            var file    = document.querySelector('input[type=file]').files[0];
            var reader  = new FileReader();

            reader.onloadend = function () {
                preview.src = reader.result;
            }

            if (file && file.size < 1_000_000) {
                reader.readAsDataURL(file);
            } else {
                preview.src = "";
            }
        }

        function getMark() {
            $.ajax({
                url: '${pageContext.servletContext.contextPath}/carmake',
                method: 'post',
                dataType: 'text/json',
                complete: function (data) {
                    let marks = JSON.parse(data.responseText);
                    for (let mark of marks) {
                        $('#mark').append('<option value="' + mark.id + '">' + mark.name + '</option>');
                    }
                }
            });
        }

        function getModel() {
            $('#model').empty();
            $('#model').removeAttr('disabled');
            disabledSelects();
            $.ajax({
                url: '${pageContext.servletContext.contextPath}/model',
                method: 'post',
                dataType: 'json',
                data: JSON.stringify($('#mark option:selected').val()),
                complete: function (data) {
                    $('#model').append('<option selected value="">' + 'select model...' + '</option>');
                    let models = JSON.parse(data.responseText);
                    for (let model of models) {
                        $('#model').append('<option value="' + model.id + '">' + model.name + '</option>');
                    }
                }
            });
        }

        function getBodyType() {
            $('#bodytype').empty();
            $('#bodytype').removeAttr('disabled');
            $.ajax({
                url: '${pageContext.servletContext.contextPath}/bodytype',
                method: 'post',
                dataType: 'text/json',
                complete: function (data) {
                    $('#bodytype').append('<option selected value="">' + 'select body type...' + '</option>');
                    let bodies = JSON.parse(data.responseText);
                    for (let body of bodies) {
                        $('#bodytype').append('<option value="' + body.id + '">' + body.name + '</option>');
                    }
                }
            });
        }

        function getEngineType() {
            $('#enginetype').empty();
            $('#enginetype').removeAttr('disabled');
            $.ajax({
                url: '${pageContext.servletContext.contextPath}/enginetype',
                method: 'post',
                dataType: 'text/json',
                complete: function (data) {
                    $('#enginetype').append('<option selected value="">' + 'select engine type...' + '</option>');
                    let engines = JSON.parse(data.responseText);
                    for (let engine of engines) {
                        $('#enginetype').append('<option value="' + engine.id + '">' + engine.name + '</option>');
                    }
                }
            });
        }

        function getGearboxType() {
            $('#gearboxtype').empty();
            $('#gearboxtype').removeAttr('disabled');
            $.ajax({
                url: '${pageContext.servletContext.contextPath}/gearboxtype',
                method: 'post',
                dataType: 'text/json',
                complete: function (data) {
                    $('#gearboxtype').append('<option selected value="">' + 'select gearbox type...' + '</option>');
                    let gearboxes = JSON.parse(data.responseText);
                    for (let gearbox of gearboxes) {
                        $('#gearboxtype').append('<option value="' + gearbox.id + '">' + gearbox.name + '</option>');
                    }
                }
            });
        }

        function disabledSelects() {
            $('#bodytype').attr('disabled', true);
            $('#enginetype').attr('disabled', true);
            $('#gearboxtype').attr('disabled', true);
        }
    </script>
</head>
<body>

<div class="container">
    <div class="row my-4">
        <div class="col-3 mt-4">
            <form name="create" action="${pageContext.servletContext.contextPath}/create" method="post" enctype="multipart/form-data">

                <div class="form-group">
                    <input class="carCreator" accept="image/jpeg" type="file" name="fileUpload" id="myPic" onchange="previewFile(), checkFile()"/>
                </div>
                <div class="form-group">
                    <label for="mileage_input">Mileage</label>
                    <input id="mileage_input" type="text" class="form-control carCreator" name="carMileage" placeholder="Enter mileage">
                </div>
                <div class="form-group">
                    <label for="year_input">Year</label>
                    <input id="year_input" type="text" class="form-control carCreator" name="year"
                           placeholder="Enter year">
                </div>
                <div class="form-group">
                    <label for="price_input">Price</label>
                    <input id="price_input" type="text" class="form-control carCreator" name="price"
                           placeholder="Enter price">
                </div>
                <div class="form-group">
                    <input  id="user_input" type="hidden" class="form-control carCreator" name="user" value="${sessionScope.get("userId")}">
                </div>
                <div class="form-group my-3">
                    <label>Mark</label>
                    <select class="form-control carCreator" name="mark" id="mark" onchange="getModel()">
                        <script>getMark()</script>
                        <option value="">select mark...</option>
                    </select>
                </div>

                <div class="form-group my-3">
                    <label>Model</label>
                    <select disabled class="form-control carCreator" name="model" id="model" onchange="getBodyType()">
                        <option value="">select model...</option>
                    </select>
                </div>

                <div class="form-group my-3">
                    <label>Body type</label>
                    <select disabled class="form-control carCreator" name="bodytype" id="bodytype" onchange="getEngineType()">
                        <option value="">select body type...</option>
                    </select>
                </div>

                <div class="form-group my-3">
                    <label>Engine type</label>
                    <select disabled class="form-control carCreator" name="enginetype" id="enginetype" onchange="getGearboxType()">
                        <option value="">select engine type...</option>
                    </select>
                </div>

                <div class="form-group my-3">
                    <label>Gearbox type</label>
                    <select disabled class="form-control carCreator" name="gearboxtype" id="gearboxtype">
                        <option value="">select gearbox type...</option>
                    </select>
                </div>
                <div class="my-5">
                    <button type="submit" class="btn btn-secondary" onclick="return validate()">Create</button>
                </div>
            </form>

        </div>

        <div class="col-7 d-flex justify-content-center align-items-start mt-4" >
            <img class="img-thumbnail" src="" alt="your image" width="300"/>
        </div>

        <div class="col-2 d-flex justify-content-end mt-4">
            <form action="${pageContext.servletContext.contextPath}/signout" method="post">
                <button type="submit" class="btn btn-secondary">SignOut</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>