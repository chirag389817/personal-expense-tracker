<%-- 
    Document   : home
    Created on : 12-Apr-2024, 4:23:33 pm
    Author     : 91951
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard</title>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <style>
            body{
                background-color: #212121;
            }
            h1{
                color: #aaaaaa;
                text-align: center;
            }
            .main{
                height: 500px;
                display: flex;
                align-items: center;
                justify-content: center;
                gap: 300px;
            }
            /* Basic styling */
            .card {
                border: 1px solid #212121;
                border-radius: 8px;
                padding: 20px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                transition: box-shadow 0.3s ease;
                background-color: #444444;
                max-width: 290px;
                margin: 0 auto;
                margin-top: 50px;
            }


            .card:hover {
                box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
            }
            a{
                text-decoration: none;
                color: #aaaaaa;
            }

            .card h1 {
                color: #aaaaaa;
            }

            .card button  {
                font-size: 16px;
                margin-bottom: 0;
                text-align: center;
                margin-left: 80px;
                background-color: #212121;
                color: #aaaaaa;
                font: bold;
                height: 30px;
                border: 2px;
                border-radius: 5px;
            }
        </style>
    </head>
    <body>
        <%@include file="/WEB-INF/header.jsp" %>

        <h1>Welcome to expanses management application</h1>
        ${sessionScope.user.getName()}
        <div class="main">
            <div>
                <img src="https://cdni.iconscout.com/illustration/premium/thumb/expense-management-4268366-3561009.png?f=webp" alt=""/>
            </div>

            <div class="cards">
                <canvas id="myPieChart" width="400" height="400"></canvas>
            </div>
        </div>
        <script>
        // Data for the pie chart
        var data = {
            labels: ${cats},
            datasets: [{
                data: ${moneys}
            }]
        };

        // Options for the pie chart
        var options = {
            responsive: true,
            legend: {
                position: 'right'
            },
            tooltip: {
                callbacks: {
                    label: function(tooltipItem, data) {
                        var dataset = data.datasets[tooltipItem.datasetIndex];
                        var total = dataset.data.reduce(function(previousValue, currentValue, currentIndex, array) {
                            return previousValue + currentValue;
                        });
                        var currentValue = dataset.data[tooltipItem.index];
                        var percentage = Math.round((currentValue / total) * 100);
                        var count = currentValue;
                        return data.labels[tooltipItem.index] + ': ' + percentage + '% (' + count + ')';
                    }
                }
            }
        };

        // Get the context of the canvas element
        var ctx = document.getElementById('myPieChart').getContext('2d');

        // Create the pie chart
        var myPieChart = new Chart(ctx, {
            type: 'pie',
            data: data,
            options: options
        });
    </script>
    </body>
</html>