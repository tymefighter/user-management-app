<%@ page import="org.uapp.context.ContextAttributes" %>
<%@ page import="org.uapp.user.UserManager" %>
<%@ page import="org.uapp.user.UserDTO" %>
<%@ page import="org.uapp.user.UserFilterParams" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Users</title>
        <link rel="stylesheet" href="static/users.css" />
        <script src="static/button-actions.js" defer></script>
    </head>
    <body>
        <header>
            <h1 id="title">Users</h1>
            <div class="top-actions">
                <button id="refresh-button" class="top-action">⟳</button>
                <button id="add-user-button" class="top-action">+</button>
            </div>
        </header>
        <table>
            <thead>
                <tr>
                    <th>Actions</th>
                    <th>ID</th>
                    <th>Username</th>
                    <th>Name</th>
                    <th>Email</th>
                </tr>
            </thead>
            <tbody>
                <%
                    UserManager userManager = (UserManager) request
                            .getServletContext()
                            .getAttribute(
                                ContextAttributes.USERS.getAttribute()
                            );

                    UserDTO[] userDTOs = userManager.getUsers(
                        new UserFilterParams()
                    );

                    ArrayList<String> tableRows = new ArrayList<String>();
                    for(UserDTO userDTO : userDTOs) {
                        tableRows.add(
                            String.format(
                                "<tr>"
                                        + "<td><button id=\"edit:%s\" class=\"edit-user-action\">✎</button></td>"
                                        + "<td>%s</td>"
                                        + "<td>%s</td>"
                                        + "<td>%s</td>"
                                        + "<td>%s</td>"
                                        + "</tr>",
                                userDTO.getId(),
                                userDTO.getId(),
                                userDTO.getUsername(),
                                userDTO.getName(),
                                userDTO.getEmail()
                            )
                        );
                    }

                    String tableRowsStr = String.join("\n", tableRows);
                %>
                <%= tableRowsStr %>
            </tbody>
        </table>
        <div id="modal-root"></div>
    </body>
</html>
