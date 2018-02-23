<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript">
        function startTimer() {
            var my_timer = document.getElementById("my_timer");
            var time = my_timer.innerHTML;
            var arr = time.split(":");
            var h = arr[0];
            var m = arr[1];
            var s = arr[2];
            if (s == 0) {
                if (m == 0) {
                    if (h == 0) {
                        //alert("Время вышло");
                        window.location.replace("/");
                        return;
                    }
                    h--;
                    m = 60;
                    if (h < 10) h = "0" + h;
                }
                m--;
                if (m < 10) m = "0" + m;
                s = 59;
            }
            else s--;
            if (s < 10) s = "0" + s;
            document.getElementById("my_timer").innerHTML = h+":"+m+":"+s;
            setTimeout(startTimer, 1000);
        }
    </script>
    <title>Hello Page</title>
</head>
<body onload="startTimer()">
<p><span id="my_timer" style="color: #f00; font-size: 150%; font-weight: bold;">00:00:21</span></p>
    <h2>Hello ${name}!</h2>
</body>
</html>
