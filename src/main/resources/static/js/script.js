'use strict'

let console = document.querySelector("#console");
let stompClient;

document.querySelector("#connect").addEventListener("click", ev => {
    console.innerHTML = "";
    printLine("Opening connection...");

    const socket = new SockJS('/results-channel');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onConnected, onError);
});

const onConnected = (ev) => {
    let uuid = document.querySelector("#uuid").value;

    if (uuid == "") onError(new Error("No UUID provided."));

    else {
        stompClient.subscribe("/client/results.send", onMessageReceived);
        stompClient.subscribe("/client/results.done", onCompleteReceived);
        stompClient.subscribe("/client/connection.closed", onConnectionClosed);
        stompClient.send("/server/results.ask", {}, JSON.stringify({ uuid: uuid }));
    }
}

const onError = (error) => {
    stompClient.disconnect();
    printLine(error.message);
    console.style.color = 'red';
}

const onMessageReceived = (payload) => {
    printLine(payload.body);
}

const onCompleteReceived = (payload) => {
    printLine("Done receiving simulation messages.");
    stompClient.disconnect();
    // TODO: I thought this would trigger a disconnect on the server but it doesn't
}

const onConnectionClosed = (payload) => {
    printLine("Connection closed.");
}

const printLine = (line) => {
    console.innerHTML += `${line}<br>`;
    console.scrollTop = console.scrollHeight;
}