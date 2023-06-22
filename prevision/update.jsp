<%@page import="bateau.Bateau" %>
<%@page import="utilisateur.Utilisateur" %>
<%@page import="prevision.Prevision" %>
<%@page import="formulaire.Formulaire" %>
<%

    Formulaire form = Formulaire.createFormulaire(new Prevision());
    form.setAction("../prevision/controlleur/insert-prevision.jsp");
    form.setError(request.getParameter("error"));
    form.getListeChamp()[0].setVisible(false, "");
    form.getListeChamp()[1].changeToDrop(new Bateau().findAll(null), "getNom", "getIdBateau");
    form.getListeChamp()[2].setType("datetime-local");
    form.getListeChamp()[3].setType("datetime-local");
    form.getListeChamp()[4].setVisible(false, "");

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../assets/css/bootstrap.css">
    <title>Saisie de prevision</title>
</head>
<body>
    <div class="" style="margin-top: 5rem;">
        <h1 class="text-center mb-4">Saisie de Prevision</h1>
        <%=form.getHTMLString() %>
    </div>
</body>
</html>