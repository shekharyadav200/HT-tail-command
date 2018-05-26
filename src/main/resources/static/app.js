var stompClient = null;

function setConnected(connected) {
    $("#fetchLogs").prop("disabled", connected);
    $("#stop").prop("disabled", !connected);
   
   
}

function fetchLogs() {
    var socket = new SockJS('/logs-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/logs', function (response) {
            showLogs(JSON.parse(response.body).logs);
        });
    });
}

function stop() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
   
}
function clear() {
      $("#logs").html("");
   
}


 function showLogs(logs) {
var arrayLength = logs.length;
for (var i = 0; i < arrayLength; i++) {
   
  $("#logs").append("<tr><td>" + logs[i] + "</td></tr>");
}  
}
 
$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#fetchLogs" ).click(function() { fetchLogs(); });
    $( "#stop" ).click(function() { stop(); });
     $( "#clear" ).click(function() { clear(); });
});

