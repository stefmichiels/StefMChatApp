<%--
  Created by IntelliJ IDEA.
  User: stefm
  Date: 22/05/2019
  Time: 23:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<main>
    <div id="statusDiv">
        <p style="float: right" id="status"></p>
    </div>
    <div>
        <h3>Friends</h3>
        <table>
            <thead>
            <th>Name</th>
            <th>Status</th>
            </thead>
            <tbody id="friendsList">

            </tbody>
        </table>


        <!-- ChatOld div -->
        <div id="chat" style="bottom: 0; right: 200px; position: absolute; width: auto;  height: 300px; overflow: auto ">

        </div>


    </div>
</html>
