<%@ page import="org.uapp.context.ContextAttributes" %>
<%@ page import="org.uapp.user.UserManager" %>
<%@ page import="org.uapp.user.UserDTO" %>
<%@ page import="org.uapp.user.UserFilterParams" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
    <style>
        #title {
            padding: 0;
        }

        header {
            display: flex;
            gap: 2rem;
            align-items: center;
        }

        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }

        th, td {
            padding: 0.4rem
        }

        .top-actions {
            display: flex;
            gap: 0.4rem;
        }

        .top-action {
            border-radius: 2px;
            height: 2rem;
            width: 2rem;
            font-size: 1.5rem;
            text-align: center;
        }

        .row-action {
            border-radius: 2px;
            height: 1.6rem;
            width: 1.6rem;
            font-size: 0.8rem;
            text-align: center;
        }
    </style>
</head>
<body>
    <header>
        <h1 id="title">Users</h1>
        <div class="top-actions">
            <button id="refresh-button" class="top-action">⟳</button>
            <button id="add-user" class="top-action">+</button>
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
                                    + "<td><button id=\"edit-%s\" class=\"row-action\">✎</button></td>"
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
</body>
</html>
