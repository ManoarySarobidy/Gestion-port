<%@page import="port.Quai" %>
<%@page import="escale.Escale"%>
<%@page import="escale.Prestation"%>
<%  
    String idPrestation = request.getParameter("prestation");
    String reference = request.getParameter("reference");
    Prestation prestation = new Prestation();
    prestation.setIdPrestation( idPrestation );
    String idQuai = (request.getParameter("quai") != null) ? request.getParameter("quai") : "QUA001";
    Escale escale = Escale.createEscale(idQuai, reference);
%>
    <%=reference%>
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
        <form method="get" action="./controlleur/insert-prestation.jsp" class="">
            <div class="row">
                <h4 class="mb-2">Prestation</h4>
            </div>
            <input type="hidden" value="<%= idQuai %>" name="quai">
            <div class="row mt-3">
                <h4 class="mb-2"> Arrive </h4>
                <input type="datetime-local" name="arrive" class="form-control col" placeholder="Arrive">
            </div>
            <div class="row mt-3">
                <h4 class="mb-2"> Depart </h4>
                <input type="datetime-local" name="depart" class="form-control col" placeholder="Arrive">
                <input type="hidden" name="reference" value="<%= reference %>">
            </div>
            <div class="row mt-3">
                <h4 class="mb-2"> Prix du prestation </h4>
                <input type="text" name="prix" class="form-control col" placeholder="Prix du prestation">
                <!-- <input type="hidden" name="reference" value="<%= reference %>"> -->
            </div>
            <div class="row">
                <input type="submit" value="Valider" class="btn btn-success mt-3">
            </div>
        </form>

    </div>
</body>
</html>
