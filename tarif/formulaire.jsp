<%@page import="formulaire.Formulaire" %>
<%@page import="escale.Tarif" %>
<%

    Formulaire form = Tarif.createFormulaire();

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../assets/css/bootstrap.css">
    <title>Saisie de tarif</title>
</head>
<body>
    <div class="container mt-4">
        <%=form.getHTMLString() %>
    </div>
</body>
</html>