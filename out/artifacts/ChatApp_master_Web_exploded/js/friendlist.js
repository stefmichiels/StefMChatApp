let xHRObjectFriends = new XMLHttpRequest();
let xHRObjectAdd = new XMLHttpRequest();
let xHRObjectOnline = new XMLHttpRequest();
let userIds;

function getFriendsOnline(){
    xHRObjectOnline.open("GET", "Controller?action=GetOnline");
    xHRObjectOnline.send();
    xHRObjectOnline.onreadystatechange= showOnline;

}

function showOnline(){
    setTimeout(null,1000);
    if(xHRObjectOnline.readyState ===4) {
        if(xHRObjectOnline.status===200){
            let serverResponse = JSON.parse(xHRObjectOnline.responseText);
            let html ="";
            html = html + "<li>" + "Online: " + serverResponse[0].online + "    Offline: " + serverResponse[0].offline + "</li>";
            document.getElementById("friendsonline").innerHTML = html;
            setTimeout(getFriendsOnline,7000);
        }
    }
}

getFriendsOnline()

function getFriends() {
    setTimeout(null,1000);
    xHRObjectFriends.open("GET", "Controller?action=GetFriends");
    xHRObjectFriends.send();
    xHRObjectFriends.onreadystatechange = showData;
}

function showData() {
    if (xHRObjectFriends.readyState === 4) {
        if (xHRObjectFriends.status === 200) {
            let serverResponse = JSON.parse(xHRObjectFriends.responseText);
            userIds = serverResponse;
            let html = "";
            for (let i = 0; i < serverResponse.length; i++) {
                html = html + "<li style='padding-bottom: 10px' class='mouse' id='dialog" + serverResponse[i].userId + "' onclick='showChat(\""+serverResponse[i].userId+"\")'>" + serverResponse[i].userId + ": <b>" + serverResponse[i].status + "</b></li>"
            }
            document.getElementById("friends").innerHTML = html;
            setTimeout(getFriends, 10000);
        }
    }
    //setTimeout(getFriends, 10000);
}


function addFriends() {
    let friend = document.getElementById("add").value;
    let information = "friend=" + encodeURI(friend);
    document.getElementById("add").value = "";
    xHRObjectAdd.open("POST", "Controller?action=AddFriends");
    xHRObjectAdd.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xHRObjectAdd.send(information);
    xHRObjectAdd.onreadystatechange = showData;
}

