<%@page import="formulaire.Formulaire" %>
<%@page import="port.Quai" %>
<%

    Formulaire form = Formulaire.createFormulaire(new Quai());
    form.setError(request.getParameter("error"));
    form.setTitle("Saisie de Quai");
    form.setAction("/gestion-port/insert");
    form.getListeChamp()[0].changeToDrop(new Quai().findAll(null), "getNom", "getIdQuai");

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="../assets/css/bootstrap.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ajout de pavillon</title> 
</head>
<body>
    <div class="container my-5">
        <%=form.getHTMLString() %>
    </div>
</body>
</html>