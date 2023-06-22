<%@page import="formulaire.Formulaire" %>
<%@page import="bateau.Devise" %>
<%

    Formulaire form = Formulaire.createFormulaire(new Devise());
    form.setError(request.getParameter("error"));
    form.setTitle("Saisie de Devise");
    form.setAction("/gestion-port/insert");

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="../assets/css/bootstrap.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ajout de devise</title>
</head>
<body>
    <div class="container my-5">
        <%=form.getHTMLString() %>
    </div>
</body>
</html>