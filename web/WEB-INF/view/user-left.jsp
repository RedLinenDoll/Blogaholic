<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Goodbye, ${userLeft}</title>
    <script type="text/javascript">
        setTimeout(function () {
            window.location.replace(`/team-java_blogaholic/index.jsp`);
        }, 3000);
    </script>
    <link href="https://fonts.googleapis.com/css?family=Lora:400,400i&display=swap" rel="stylesheet">

    <style>
        #goodbye {
            position: fixed;
            top: 80px;
            color: #106688;
            font-family:  Lora, 'Times New Roman', serif;
            text-align: center;
            font-weight: 800;
        }
        p {
            font-size: 30px;
        }
    </style>

</head>
<body>
<div id="goodbye">
<h1>
    Goodbye, ${userLeft}!</h1>
    <p>We are now redirecting you to our index page...</p>
    <p>Hope we'll see you again!</p>

</div>

</body>
</html>
