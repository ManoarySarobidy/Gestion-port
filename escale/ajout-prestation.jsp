<%@page import="port.Quai" %>
<%@page import="escale.Escale"%>
<%@page import="escale.Prestation"%>
<%
    String reference = request.getParameter("reference");
    String idQuai = (request.getParameter("quai") != null) ? request.getParameter("quai") : "QUA001";
    Escale escale = Escale.createEscale(idQuai, reference);
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
    <div class="container w-50 shadow p-5 rounded-3 mb-5" style="margin-top: 5rem;">
        <h1 class="text-center mb-4">Ajout prestation</h1>
        <form method="get" action="./controlleur/insert-prestation.jsp" class="">
            <div class="row">
                <h4 class="mb-2">Prestation</h4>
                <select name="prestation" class="form-select">
                    <option value="" selected>Prestation</option>
                    <% for (Prestation prestation : escale.getPrestations()) { %>
                    <option value="<%=prestation.getIdPrestation() %>"><%=prestation.getNom() %></option>
                    <% } %>
                </select>
            </div>
            <input type="hidden" value="<%=idQuai %>" name="quai">
            <div class="row mt-3">
                <h4 class="mb-2">Arrive</h4>
                <input type="datetime-local" name="arrive" class="form-control col" placeholder="Arrive">
            </div>
            <div class="row mt-3">
                <h4 class="mb-2">Depart</h4>
                <input type="datetime-local" name="depart" class="form-control col" placeholder="Arrive">
                <input type="hidden" name="reference" value="<%=reference %>">
            </div>
            <div class="row">
                <input type="submit" value="Valider" class="btn btn-success mt-3">
            </div>
        </form>

        <div class="row my-5">
            <h4 class="mb-2">Quai</h4>
            <form action="./ajout-prestation.jsp" method="get">
                <select name="quai" class="form-select">
                    <option value="" selected>Quai</option>
                    <% for (Quai quai : escale.getQuais()) { %>
                    <option value="<%=quai.getIdQuai() %>"><%=quai.getNom() %></option>
                    <% } %>
                </select>
                <input type="hidden" name="reference" value="<%=reference %>">
                <input type="submit" value="Choisir" class="btn btn-primary mt-3">
            </form>
        </div>

        <div class="row">

            <h2>Prestation</h2>
            <table class="table mt-3">
                <tr>
                    <th>Nom</th>
                    <th>Date debut</th>
                    <th>Date fin</th>
                    <th>Duree</th>
                    <th>Prix</th>
                    <th>Etat</th>
                    <th></th>
                    <th></th>
                </tr>
                <% for (Prestation prestation : escale.getListePrestation()) { %>
                    <tr>
                        <td><%=prestation.getNom() %></td>
                        <td><%=prestation.getDebut() %></td>
                        <td><%=prestation.getFin() %></td>
                        <td><%=prestation.getDuree() %></td>
                        <td><%=prestation.getPrixDevise() %></td>
                        <td><%=prestation.getEtatLettre() %></td>
                        <td><a href="./controlleur/valide-prestation.jsp?prestation=<%=prestation.getId() %>"><svg xmlns="http://www.w3.org/2000/svg" height="25" viewBox="0 -960 960 960" width="48"><path d="M378-246 154-470l43-43 181 181 384-384 43 43-427 427Z"/></svg></a></td>
                        <td><a href=""><svg xmlns="http://www.w3.org/2000/svg" height="25" viewBox="0 -960 960 960" width="48"><path d="M483-120q-75 0-141-28.5T226.5-226q-49.5-49-78-115T120-482q0-75 28.5-140t78-113.5Q276-784 342-812t141-28q80 0 151.5 35T758-709v-106h60v208H609v-60h105q-44-51-103.5-82T483-780q-125 0-214 85.5T180-485q0 127 88 216t215 89q125 0 211-88t86-213h60q0 150-104 255.5T483-120Zm122-197L451-469v-214h60v189l137 134-43 43Z"/></svg></a></td>
                    </tr>
                <% } %>

            </table>
        </div>

    </div>
</body>
</html>
