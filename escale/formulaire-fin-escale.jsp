<%@page import="escale.Escale, escale.DebutEscale, escale.FinEscale"%>
<%
    if(request.getParameter("validation") != null){
        String idDebut = request.getParameter("idDebut");
        String fin = request.getParameter("date_fin");
        String cour = request.getParameter("cour");
        FinEscale finEscale = new FinEscale(idDebut,fin, cour);
        finEscale.insert(null);
    }  
    String idDebut = request.getParameter("idDebut");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <form method="post" action="./formulaire-fin-escale.jsp">
        <p>Fin d'éscale <input type="datetime-local" name="date_fin"></p>
        <p>Cour <input type="number" name="cour"></p>
        <input type="hidden" name="idDebut" value="<%=idDebut%>">
        <input type="submit" name="validation" value="Valider">
    </form>
</body>
</html>