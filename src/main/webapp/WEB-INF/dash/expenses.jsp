<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Expenses</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" integrity="sha384-rB6SrfoTv8QzHji7RVRdyJKDyOzKN8kFi8RW5C4NtmIWyvWh67hYe9vfnke7u8sF" crossorigin="anonymous">
        <style>
            body {
                background-color: #212121;
                color: #fff;
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 20px;
            }
            .topbar{
                display: flex;
                justify-content: end;
                margin-bottom: 20px;
            }
            .layout{
                width: 70%;
                margin: 0 auto;
                margin-top: 20px;
            }
            table{
                width: 100%;
            }
            table tr {
                width: 100%;
                margin-bottom: 20px;
                padding: 10px;
                background-color: #424242;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            }
            
            a{
                color: red;
                text-decoration: none;
            }


            .record-buttons{
                display: flex;
                align-items: center;
                gap: 3px;
            }
            .record-buttons button {
                border: none;
                background-color: transparent;
                cursor: pointer;
                color: #ff6347; /* Indian Red */
                font-size: 16px;
                margin-left: 5px;
            }

            .record-buttons button:hover {
                color: #dc3545; /* Red */
            }

            .add-button {
                border: none;
                background-color: #007bff; /* Blue */
                color: #fff;
                padding: 10px 20px;
                border-radius: 4px;
                cursor: pointer;
                font-size: 16px;
            }

            .add-button:hover {
                background-color: #0056b3; /* Dark Blue */
            }
            table td{
                padding:  5px 20px;
            }
            table tr{
                border-bottom: 5px solid red;
            }

        </style>
    </head>
    <body>
        <%@include file="/WEB-INF/header.jsp" %>
        <div class="layout">

            <!-- Button to add new record -->
            <div class="topbar">
                <a class="add-button" href="/expense2/dash/expenses/form">Add Record</a>
            </div>

            <!-- Record groups -->
            <table>
                <c:forEach var="expense" items="${expenses}">
                    <tr id="item${expense.getId()}">
                    <td>Rs. ${expense.getMoney()}</td>
                    <td>${catsMap.get(expense.getCatId())}</td>
                    <td>${expense.getDate()}</td>
                    <td style="white-space: normal;">${expense.getNote()}</td>
                    <td class="record-buttons">
                        <a href="/expense2/dash/expenses/form?id=${expense.getId()}"><i class="fa fa-pencil"></i></a>
                        <button onclick="deleteExpense(${expense.getId()})"><i class="fa fa-trash"></i></button>
                    </td>
                </tr>
                </c:forEach>
            </table>
        </div>

        <script>
             function deleteExpense(id) {
                fetch("/expense2/dash/expenses?eid=" + id, {
                    method: "DELETE"
                }).then(e=>{
                    location.reload();
                });
            }
        </script>
    </body>
</html>
