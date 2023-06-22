<%@page import="bateau.Bateau" %>
<%@page import="utilisateur.Utilisateur" %>
<%

    String error = (request.getParameter("error") == null) ? "" : request.getParameter("error");
    Bateau[] bateaus = new Bateau().findAll(null);

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
    <div class="container w-50 shadow p-5 rounded-3" style="margin-top: 5rem;">
        <h1 class="text-center mb-4">Saisie de Prevision</h1>
        <form method="post" action="./controlleur/insert-prevision.jsp" class="">
            <div class="row">
                <h4 class="mb-2">Bateau</h4>
                <select name="bateau" class="form-select">
                    <option value="" selected>Bateau</option>
                    <% for (Bateau bateau : bateaus) { %>
                    <option value="<%=bateau.getIdBateau() %>"><%=bateau.getNom() %></option>
                    <% } %>
                </select>
            </div>
            <div class="row mt-3">
                <h4 class="mb-2">Arrive</h4>
                <input type="datetime-local" name="arrive" class="form-control col" placeholder="Arrive">
            </div>
            <div class="row mt-3">
                <h4 class="mb-2">Depart</h4>
                <input type="datetime-local" name="depart" class="form-control col" placeholder="Arrive">
            </div>
            <div class="row">
                <input type="submit" value="Valider" class="btn btn-success mt-3">
            </div>
        </form>
        <div class="row">
            <a href="../proposition/liste-proposition.jsp" class="btn btn-dark mt-3">Propositions</a>
        </div>
        <h3 class="mt-4 text-danger"><%=error %></h3>
    </div>
</body>
</html>