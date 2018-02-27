function startTimer() {
    var timer = document.getElementById("timer");
    var time = timer.innerHTML;
    var timeArray = time.split(":");
    var hours = timeArray[0];
    var minutes = timeArray[1];
    var seconds = timeArray[2];
    if (seconds == 0) {
        if (minutes == 0) {
            if (hours == 0) {
                //alert("Время вышло");
                window.location.replace("/logout");
                return;
            }
            hours--;
            minutes = 60;
            if (hours < 10) hours = "0" + hours;
        }
        minutes--;
        if (minutes < 10) minutes = "0" + minutes;
        seconds = 59;
    }
    else seconds--;
    if (seconds < 10) seconds = "0" + seconds;
    document.getElementById("timer").innerHTML = hours + ":" + minutes + ":" + seconds;
    setTimeout(startTimer, 1000);
}
