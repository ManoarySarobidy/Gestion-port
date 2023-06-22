<%@page import="formulaire.Formulaire" %>
<%@page import="bateau.Pavillon" %>
<%@page import="bateau.Devise" %>
<%

    Formulaire form = Formulaire.createFormulaire(new Pavillon());
    form.setError(request.getParameter("error"));
    form.setTitle("Saisie de Pavillon");
    form.setAction("/gestion-port/insert");
    form.getListeChamp()[2].changeToDrop(new Devise().findAll(null), "getValeur", "getIdDevise");

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