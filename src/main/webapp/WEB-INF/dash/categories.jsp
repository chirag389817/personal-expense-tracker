<%-- 
    Document   : index.jsp
    Created on : 20-Apr-2024, 10:26:43 am
    Author     : csp38
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Categories</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <style>
            body {
                background-color: #212121;
                color: #fff;
                margin: 0;
            }

            .grid-container {
                display: grid;
                grid-template-columns: repeat(3, 1fr);
                padding: 0 50px;
                gap: 20px;
                margin: 20px auto;
                width: 70vw;
            }

            .grid-item {
                background-color: #424242; /* Dark Grey */
                border-radius: 8px;
                padding: 20px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
                transition: all 0.3s ease;
                display: flex;
                align-items: center;
            }

            .grid-item:hover {
                transform: translateY(-3px);
                cursor: pointer;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            }

            .category {
                font-size: 18px;
                flex-grow: 1;
            }
            .btn{
                border: none;
                background: none;
                cursor: pointer;
                font-size: 20px;
            }
            .delete-button {
                color: #ff6347; /* Indian Red */
            }

            .delete-button:hover {
                color:red;
            }



            .dialog-container {
                position: fixed;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                background-color: #424242;
                border-radius: 8px;
                padding: 20px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
                z-index: 1000;
            }
            .overlay {
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background-color: rgba(0, 0, 0, 0.5); /* Semi-transparent black */
                backdrop-filter: blur(5px); /* Apply blur effect */
                z-index: 999; /* Ensure overlay is behind dialog container */
                display: none; /* Initially hidden */
            }

            .dialog-title {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 20px;
            }

            .close-icon {
                cursor: pointer;
                color: #fff;
                font-size: 20px;
            }

            .close-icon:hover {
                color: #dc3545; /* Red */
            }

            .category-input {
                width: calc(100% - 20px);
                padding: 10px;
                border: none;
                border-radius: 4px;
                background-color: #333;
                color: #fff;
                font-size: 16px;
                outline: none;
                margin-bottom: 20px;
            }

            .category-input::placeholder {
                color: #aaa;
            }

            .add-button {
                width: 100%;
                padding: 10px 20px;
                background-color: #007bff; /* Blue */
                border: none;
                border-radius: 4px;
                color: #fff;
                font-size: 16px;
                cursor: pointer;
            }

            .add-button:hover {
                background-color: #0056b3; /* Dark Blue */
            }

            a{
                text-decoration: none;
                color: white;
            }
        </style>
    </head>
    <body>
        <%@include file="/WEB-INF/header.jsp" %>
        <div class="overlay">
            <div class="dialog-container" id="dialog-container">
                <div class="dialog-title">
                    <span>Add Category</span>
                    <span id="cancelButton" class="close-icon"><i class="fa fa-times"></i></span>
                </div>
                <form action="/expense2/dash/categories" method="POST">
                    <input type="text" name="category" class="category-input" placeholder="Enter category name" id="inputCat"/>
                    <button class="add-button" id="btnAddCat">Add</button>
                </form>
            </div>
        </div>

        <div class="grid-container" id="grid-container">
            <div class="grid-item" id="openDialogButton" onclick="openDlg()">
                <button class="btn" style="color: white"><i class="fa fa-plus-circle"></i></button>
                <div class="category">Add New Category</div>
            </div>
            <c:forEach var="category" items="${categories}">
                <div class="grid-item" id="item${category.getId()}" >
                    <a href="/expense2/dash/expenses?catId=${category.getId()}" class="category">${category.getName()}</a>
                    <button onclick="deleteCat(${category.getId()})" class="btn delete-button"><i class="fa fa-trash"></i></button>
                </div>
            </c:forEach>
        </div>

        <script>
            function openDlg() {
                document.querySelector('.overlay').style.display = 'block';
            }
            document.getElementById('cancelButton').addEventListener('click', function () {
                document.querySelector('.overlay').style.display = 'none';
            });
            function deleteCat(id){
                fetch("/expense2/dash/categories?id="+id, {
                    method: "DELETE"
                }).then(e=>{
                    window.location.reload();
                });
                
            }
        </script>
    </body>
</html>
