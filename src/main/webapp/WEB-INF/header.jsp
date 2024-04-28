<style>
    /* Basic styling */
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
    }

    nav {
        background-color: #333;
        color: #fff;
        display: flex;
        justify-content: space-between;
        padding: 10px 20px;
        height: 40px;
    }

    nav ul {
        list-style-type: none;
        margin: 0;
        padding: 0;
        width: 100%;
        display: flex;
        margin-left: 10%;
        justify-content: center;
        align-items: center;
    }

    nav ul li h3{
        margin-right: 200px;
        margin-left: -400px
    }

    nav ul li {
        margin-right: 150px;
    }

    nav ul li:last-child {
        margin-right: -200px;
    }

    nav ul li a {
        color: #fff;
        text-decoration: none;
    }

    nav ul li a:hover {
        text-decoration: underline;
    }
</style>
<nav>

    <ul>
        <li><h3><a href="/expense2/dash">Expanses Tracker</a></h3></li>
        <li><a href="/expense2/dash/categories">Categories</a></li>
        <li><a href="/expense2/dash/expenses">Expenses</a></li>
        <li><a href="/expense2/dash/expenses/form">Add Expenses</a></li>
        <li><a href="/expense2/logout">Logout</a></li>
    </ul>
</nav>