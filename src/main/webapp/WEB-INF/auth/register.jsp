<%-- 
    Document   : register
    Created on : 25-Apr-2024, 6:08:44 pm
    Author     : csp38
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Expanse tracker</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #212121;
                margin: 0;
                padding: 0;
                display: flex;
                flex-direction: row;
                gap: 300px;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }

            .container {
                background-color: gray;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                padding: 20px;
                width: 300px;
            }
            p:editable{

            }

            h2 {
                text-align: center;
            }

            form {
                display: flex;
                flex-direction: column;
            }

            label {
                margin-bottom: 5px;
            }


            input[type="text"],
            input[type="password"],
            input[type="email"] {
                padding: 10px;
                margin-bottom: 10px;
                border: 1px solid #ccc;
                border-radius: 3px;
            }

            button {
                padding: 10px 20px;
                background-color: #007bff;
                color: #fff;
                border: none;
                border-radius: 3px;
                cursor: pointer;
            }

            a{
                text-align: center;
                margin: 80px;

            }

            button:hover {
                background-color: #0056b3;
            }
        </style>
    </head>
    <body>
        <div class="container">

            <h2>User Registration</h2>

            <form action="register" method="POST">
                <table>
                    <tr>
                        <td>
                            <label for="name">Name</label>

                        </td>
                        <td>
                            <input type="text" id="name" name="name" required>

                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="email">Email:</label>

                        </td>
                        <td>
                            <input type="email" id="email" name="email" required>

                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="password">Password:</label>

                        </td>
                        <td>
                            <input type="password" id="password" name="password" required>

                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="confirmPassword">Confirm Password:</label>

                        </td>
                        <td>
                            <input type="password" id="confirmPassword" name="confirmPassword" required>

                        </td>
                    </tr>
                </table>
                <button type="submit">Register</button>
            </form>
            <br/>
            <a href="login">Already registered?</a>

        </div> 
    </body>
</html>