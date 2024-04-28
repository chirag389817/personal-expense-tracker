<%-- 
    Document   : addExpensesView
    Created on : 14-Apr-2024, 11:45:23 am
    Author     : 91951
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Add Expense</title>
        <style>
            body {
                background-color: #212121;
                color: #fff;
                font-family: Arial, sans-serif;
            }
            h1{
                color: #aaaaaa;
                text-align: center;
            }

            form {
                max-width: 330px;
                width: auto;
                margin: 100px auto;
                padding: 20px;
                background-color: #333;
                border-radius: 8px;
            }

            label {
                display: block;
                margin-bottom: 10px;
            }
            input[type=number]::-webkit-inner-spin-button,
            input[type=number]::-webkit-outer-spin-button {
                -webkit-appearance: none;
                -moz-appearance: none;
                appearance: none;
                margin: 0;
            }
            input[type="text"],
            input[type="number"],
            input[type="date"],
            select,
            textarea {
                max-width: 400px;
                min-width: 300px;
                padding: 10px;
                margin-bottom: 20px;
                border: none;
                border-radius: 4px;
                background-color: #444;
                color: #fff;
            }
            div{
                justify-content: center;
                align-items: center;
                display: flex;
            }
            img{
                margin-left: 150px;
            }

            input[type="submit"] {
                background-color: #45a010;
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                width: 100%;
            }

            input[type="submit"]:hover {
                background-color: #45a049;
            }
        </style>
    </head>
    <body>
        <%@include file="/WEB-INF/header.jsp" %>
        <div>
            <img src="https://cdni.iconscout.com/illustration/premium/thumb/expense-management-6264415-5167525.png"/>
            <form action="/expense2/dash/expenses/form" method="POST">
                <h1>${title}</h1>

                <input name="id" value="${expense.getId()}" hidden=""/>

                <label for="expenseType">Expense Type:</label>
                <select id="expenseType" name="category">
                    <c:forEach var="category" items="${categories}">
                        <option value="${category.getId()}" <c:if test="${category.getId()==expense.getCatId()}">selected</c:if> >${category.getName()}</option>
                    </c:forEach>
                </select>

                <label for="money">Money:</label>
                <input type="number" id="money" name="money" value="${expense.getMoney()}" placeholder="Enter amount" required>

                <label for="date">Date:</label>
                <input type="date" id="date" value="${expense.getDate()}" name="date" required>

                <label for="note">Note:</label>
                <textarea id="comment" name="note" placeholder="Enter comment">${expense.getNote()}</textarea>

                <input type="submit" value="Save">
            </form>
        </div>
        <script>
            <c:if test="${expense.getDate()==null}">
            let currentDate = new Date().toISOString().split('T')[0];
            document.getElementById('date').value = currentDate;
            </c:if>

        </script>
    </body>
</html>