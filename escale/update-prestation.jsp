<%@page import="port.Quai" %>
<%@page import="escale.Escale"%>
<%@page import="escale.Prestation"%>
<%  

    String error = (request.getParameter("error") == null) ? "" : request.getParameter("error");
    String idQuai = (request.getParameter("quai") != null) ? request.getParameter("quai") : "QUA001";
    Escale escale = Escale.createEscale(idQuai, request.getParameter("reference"));
    Prestation prestation = escale.getById(null, request.getParameter("prestation"));

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
        <h1 class="text-center mb-4">Modifier la prestation </h1>
        <form method="get" action="./controlleur/update-prestation.jsp" class="">
            <input type="hidden" value="<%= prestation.getId() %>" name="prestation">
            <div class="row">
                <h4 class="mb-2">Prestation : <%= prestation.getNom() %></h4>
            </div>
            <input type="hidden" value="<%= idQuai %>" name="quai">
            <div class="row mt-3">
                <h4 class="mb-2"> Arrive </h4>

                <input type="datetime-local" value="<%= prestation.getDebut() %>" name="arrive" class="form-control col" placeholder="Arrive">
            </div>
            <div class="row mt-3">
                <h4 class="mb-2"> Depart </h4>
                <input type="datetime-local" name="depart" value="<%= prestation.getFin() %>" class="form-control col" placeholder="Arrive">
                <input type="hidden" name="reference" value="<%= escale.getReference() %>">
            </div>
            <div class="row mt-3">
                <h4 class="mb-2"> Prix du prestation </h4>
                <input type="text" name="prix" value="<%= prestation.getPrix() %>" class="form-control col" placeholder="Prix du prestation">
            </div>
            <div class="row">
                <input type="submit" value="Valider" class="btn btn-success mt-3">
            </div>
        </form>
        <h3 class="mt-4 text-danger"><%=error %></h3>
    </div>
</body>
</html>
