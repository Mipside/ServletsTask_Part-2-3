<%--
  Created by IntelliJ IDEA.
  User: Julia
  Date: 18.05.2019
  Time: 13:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@ include file="../../user/login/header.jsp" %>

<style>
    table, th, td {
        border: 1px solid black;
    }
</style>
<body>
<div class="container-login100" style="background-image: url('../../../La Casa/img/hero.jpg');">
    <div class="wrap-login100 p-l-55 p-r-55 p-t-80 p-b-30" style="width: 1200px">
        <div class="login100-form validate-form">
				<span class="login100-form-title p-b-37">
					CRUD Users
				</span>

            <div>
                <h5 class="call" style="margin-bottom: 20px; text-align:center;">Please, click on this header to show
                    the table</h5>
            </div>
            <div class="container-login100-form-btn">
                <div>
                    <table class="listRecords" style="border:1px solid black; width: 900px">
                    </table>
                    <br>
                    <p id="sometext" style="display: none;">* If you want to update firstname or lastname, please click
                        on an appropriate Update button</p>

                    <form id="form1" style="border: 1px solid black; text-align: center; display: none;">
                        <p id="pinform1" style="text-align: center;"></p>
                        <br>
                        <p id='username1' style="text-align: center;"></p>
                        <input type="text" placeholder="Edit first name" name="firstname" class='firstname' required
                               style="text-align: center; border: 1px solid black;"><br>
                        <input type="text" placeholder="Edit last name" name="lastname" class='lastname' required
                               style="text-align: center; border: 1px solid black;"><br>
                        <p class="buttonUpdate"
                           style="color:yellow; border: 1px solid black; margin: 0 363px 0 363px; background: gray;">
                            update user</p>
                    </form>

                    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
                    <script type="text/javascript">

                        $(document).ready(function () {
                            $(".call").click(function () {
                                $.ajax({
                                    url: '/management/users',
                                    type: 'GET',
                                    async: true,
                                    dataType: 'json',
                                    success: function (data, status) {
                                        document.getElementById("sometext").style.display = "block";
                                        listEmployee(data);
                                    }
                                });
                            });

                            $(".buttonUpdate").click(function () {
                                var username = $('.username').val();
                                var firstname = $('.firstname').val();
                                var lastname = $('.lastname').val();
                                $.ajax({
                                    /*    //working with json parameters
                                    type: 'PUT',
                                         contentType: "application/json; charset=utf-8",
                                       //  data: {"username": username, "firstname": firstname, "lastname": lastname},
                                         data: JSON.stringify({"firstname": firstname, "lastname": lastname, "username":username, "password":"", "status":"" }),
                                         url: '/management/user',
                                         success: function () {
                                             alert('User was updated! Please, click on that paragraph ones more');
                                         },error:function () {
                                             alert("error");
                                         }*/

                                    //working with url parameters
                                    type: 'PUT',
                                    contentType: "application/json; charset=utf-8",
                                    url: '/management/user?username=' + username + "&firstname=" + firstname + "&lastname=" + lastname,
                                    success: function () {
                                        alert('User was updated! Please, click on that paragraph ones more');
                                    }, error: function () {
                                        alert("error");
                                    }
                                });
                            });

                            function listEmployee(data) {
                                var eTable = "<table style='color:black;'>" +
                                    "<tr>" +
                                    "<th>First Name&ensp;</th>" +
                                    "<th>Last Name&ensp;</th>" +
                                    "<th>Username&ensp;</th>" +
                                    "<th>Update&ensp;</th>" +
                                    "<th>Delete&ensp;</th>" +
                                    "</tr>";

                                $.each(data, function (index, row) {
                                        eTable += "<tr>";
                                        $.each(row, function (key, value) {
                                            if (key === "username") {
                                                eTable += "<th>" + value + "&ensp;" + "</th>";
                                            }
                                            if (key === "firstName") {
                                                eTable += "<th>" + value + "&ensp;" + "</th>";
                                            }
                                            if (key === "lastName") {
                                                eTable += "<th>" + value + "&ensp;" + "</th>";
                                            }
                                        });


                                        eTable += "<td style='background: gray;'>" +
                                            "<a style='color:yellow' onclick=\"updateUser(\'" + row.username + "\')\">Update user</a>" + "</td>" +
                                            "<td style='background: gray;'>" +
                                            "<a style='color:red' onclick=\"deleteUser(\'" + row.username + "\')\">Delete user</a>" +
                                            "</td>";
                                        eTable += "</tr>";
                                    }
                                );
                                eTable += "</table>";
                                $('.listRecords').html(eTable);
                            }
                        });

                        function deleteUser(username) {
                            $.ajax({
                                url: '/management/user?username=' + username,
                                type: 'DELETE',
                                success: function (data, status) {
                                    alert('User was deleted! Please, click on that paragraph ones more');
                                }
                            });
                        }

                        function updateUser(username) {
                            document.getElementById("form1").style.display = "block";
                            document.getElementById("pinform1").innerHTML = "<h5>We are updating: " + username + "</h5>";
                            document.getElementById("username1").innerHTML = "<input type='text' style='text-align: center;' name='username' class='username' value='" + username + "' />";
                        }


                    </script>

                </div>
            </div>


        </div>
    </div>
</div>
<%@ include file="../../user/login/footer.jsp" %>
</body>
</html>
