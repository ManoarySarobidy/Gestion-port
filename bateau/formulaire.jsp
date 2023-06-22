<%@page import="formulaire.Formulaire" %>
<%@page import="bateau.Bateau" %>
<%

    Formulaire form = Bateau.createFormulaire(request.getParameter("error"));

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="../assets/css/bootstrap.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ajout de bateau</title>
</head>
<body>
    <div class="container my-5">
        <%=form.getHTMLString() %>
    </div>
</body>
</html>