<%@page import="escale.Escale" %>
<%

    String idDebut = request.getParameter("idDebut");
    String reference = request.getParameter("reference");
    if (request.getParameter("validation") != null) {
        Escale escale = new Escale();
        escale.setIdDebut(idDebut);
        escale.setDepart(request.getParameter("date_fin"));
        escale.setCours(request.getParameter("cour"));
        escale.finir();
        response.sendRedirect("./ajout-prestation.jsp?reference=" + reference);
    }

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../assets/css/bootstrap.css">
    <title>Document</title>
</head>
<body>
    <div class="container w-50 shadow p-5 rounded-3" style="margin-top: 5rem;">
        <h1 class="text-center mb-4">Ajout fin esacle</h1>
        <form method="post" action="./formulaire-fin-escale.jsp" class="">
            <div class="row">
                <h4 class="mb-2">Fin escale</h4>
                <input type="datetime-local" name="date_fin" class="form-select">
            </div>

            <input type="hidden" name="idDebut" value="<%=idDebut %>">
            <input type="hidden" name="idDebut" value="<%=reference %>">

            <div class="row mt-3">
                <h4 class="mb-2">Cour</h4>
                <input type="number" name="cour" class="form-select" placeholder="4200">
            </div>
        
            <div class="row">
                <input type="submit" name="validation" value="Valider" class="btn btn-success mt-3">
            </div>
        </form>
    </div>

</body>
</html>