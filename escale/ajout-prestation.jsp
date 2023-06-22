<%@page import="port.Quai" %>
<%@page import="escale.Escale"%>
<%@page import="escale.Prestation"%>
<%
    String reference = request.getParameter("reference");
    Escale escale = Escale.createEscale("QU001", reference);
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
        <h1 class="text-center mb-4">Ajout prestation</h1>
        <form method="post" action="" class="">
            <div class="row">
                <h4 class="mb-2">Prestation</h4>
                <select name="prestation" class="form-select">
                    <option value="" selected>Prestation</option>
                    <% for (Prestation prestation : escale.getPrestations()) { %>
                    <option value="<%=prestation.getIdPrestation() %>"><%=prestation.getIdprestation() %></option>
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
        <h3 class="mt-4 text-danger"><%=error %></h3>
    </div>
</body>
</html>
