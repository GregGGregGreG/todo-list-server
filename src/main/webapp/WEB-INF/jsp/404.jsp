<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
    <title><tiles:getAsString name="title"/></title>
    <link href="../../static/node_modules/bootstrap-3.3.4-dist/css/bootstrap.css" rel="stylesheet">
    <link href="../../static/css/app.css" rel="stylesheet">
    <style>
        body {
            /*background-color: #0d5574;*/
            color: white;
        }

        h1.error {
            font-size: 100px;
            margin: 125px auto;
            text-align: center;
            color: #fff;
            text-shadow: 0px 1px 1px #ddd,
            0px 2px 1px #d6d6d6,
            0px 3px 1px #ccc,
            0px 4px 1px #c5c5c5,
            0px 5px 1px #c1c1c1,
            0px 6px 1px #bbb,
            0px 1px 1px #777,
            0px 1px 3px rgba(100, 100, 100, 0.4),
            0px 1px 5px rgba(100, 100, 100, 0.1),
            0px 1px 7px rgba(100, 100, 100, 0.15),
            0px 1px 9px rgba(100, 100, 100, 0.2),
            0px 1px 11px rgba(100, 100, 100, 0.25),
            0px 1px 15px rgba(100, 100, 100, 0.3);
        }

        .error:hover {
            margin: 122px auto;
            border: 3px solid #cccccc;
            border-radius: 20%;
        }

        main a:link {
            text-decoration: none;
        }

        main a:link {
            color: white;
        }

        main a:visited {
            color: white;
        }

        main a:hover {
            background-color: #FFFFFF;
        }

        #hex {
            color: white; font: 64px/64px Lato; height: 64px;
            /*Vertical centering*/
            /*position: relative; top: 50%; margin-top: -32px;*/
            /*Horizontal centering*/
            /*text-align: center;*/
        }
    </style>
</head>
<body>
<main>
    <div class="container text-center">
        <div id="hex"></div>
        <a href="<spring:url value="/" />">
            <h1 class="error">404 <br>Page <br>Not Found</h1>

            <p>The page you requested was not found.</p>
        </a>
    </div>
</main>
<footer class="container">
    <tiles:insertAttribute name="footer"/>
</footer>
</body>
<script>
    //lets display the current time
    var d, h, m, s, color;
    function displayTime() {
        d = new Date(); //new data object
        h = d.getHours();
        m = d.getMinutes();
        s = d.getSeconds();

        //add zero to the left of the numbers if they are single digits
        if(h <= 9) h = '0'+h;
        if(m <= 9) m = '0'+m;
        if(s <= 9) s = '0'+s;

        color = "#"+h+m+s;
        //set background color
        document.body.style.background = color;
        //set time
        document.getElementById("hex").innerHTML = color;

        //retrigger the function every second
        setTimeout(displayTime, 1000);
    }

    //call the function
    displayTime();
</script>
</html>
