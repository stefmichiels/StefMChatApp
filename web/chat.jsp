<%--
  Created by IntelliJ IDEA.
  User: stefm
  Date: 21/02/2019
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>

<ul id="friendsonline"> </ul>
<ul id="friends" style='margin-left: -40px'></ul>

<div style="display: flex;">
    <p>status:
    <div id="status" onload="getStatus();"></div>
    </p>
</div>
<p style="display: block">Wat is je status?: <input type="text" id="state"  name="status"> <button id="changestatus" onclick="changeStatus();"> Verander status </button></p>
<p style="display: block">Vrienden toevoegen: <input type="text" id="add" name="addfriend"> <button id="addfriend" onclick="addFriends()"> Voeg toe </button></p>


<div id="chat" style="bottom: 0; right: 200px; position: absolute; width: auto;  height: 300px; overflow: auto ">

</div>



<script src="js/chatter.js"></script>
<script src="js/js.js"></script>
<script src="js/friendlist.js"></script>
<script src="js/websockets.js"></script>
<script src="https://code.jquery.com/jquery-3.4.0.min.js"></script>









</div>