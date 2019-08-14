let xhr = new XMLHttpRequest();
let xHRStatus = new XMLHttpRequest();

window.onload = function(){
    getStatus();
    getFriends();};

function changeStatus() {
    let status = document.getElementById("state").value;
    let information = "status=" + encodeURI(status);
    document.getElementById("state").value = "";
    xhr.open("POST", "Controller?action=Status", true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.send(information);
    xhr.onreadystatechange = getData;
}

function getStatus() {

    xhr.open("GET", "Controller?action=GetStatus");
    xhr.send(null);
    xhr.onreadystatechange = getData;
}

function getData() {
    if (xhr.readyState === 4){
        if (xhr.status === 200){
            let serverResponse = JSON.parse(xhr.responseText);
            let statusXML = serverResponse.status;
            window.alert(serverResponse);
            let div = document.getElementById("status");
            let p = div.childNodes[0];
            if (p == null){
                p = document.createElement("p");
                p.id = "statustext";
                let statustext = document.createTextNode(statusXML);
                p.appendChild(statustext);
                div.appendChild(p);
            }else{
                let statustext = document.createTextNode(statusXML);
                p.removeChild(p.childNodes[0]);
                p.appendChild(statustext);
            }
        }
    }
}

