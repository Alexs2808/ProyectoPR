<%@page import="logica.Practicas"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/header.jsp"%>
<%@include file="components/bodyCabecera.jsp"%>

<br>
<h1 class="text-center mb-4">Información de Prácticas</h1>
<br>

<div class="row gx-5 justify-content-center">
    <div class="col-lg-12 col-xl-10">
        <div class="card shadow-sm">
            <div class="card-body p-0">
                <div class="table-responsive">
                    <table class="table table-hover table-striped mb-0">
                        <thead class="bg-light">
                            <tr>
                                <th scope="col">ID Práctica</th>
                                <th scope="col">Piloto</th>
                                <th scope="col">Pista</th>
                                <th scope="col">Tipo Tester</th>
                                <th scope="col">Total Tiempos</th>
                            </tr>
                        </thead>
                        <%
                            List<Practicas> listaPracticas = (List)request.getAttribute("listaPracticas");
                            if (listaPracticas == null) {
                                listaPracticas = new java.util.ArrayList<>();
                            }
                        %>
                        <tbody>
                            <% if (listaPracticas.isEmpty()) { %>
                                <tr><td colspan="5" class="text-center text-muted">No hay prácticas registradas.</td></tr>
                            <% } else { %>
                                <%for (Practicas pc : listaPracticas){%>
                                <tr>
                                    <td><%= pc.getIdPractica()%></td>
                                    
                                    <td><%= pc.getPiloto().getNombrePiloto() + " " + pc.getPiloto().getApellidoPaterno() %></td>
                                    <td><%= pc.getPista().getNombrePista()%></td>
                                    <td><%= pc.getTipoTester().getNombreTester()%></td>
                                    
                                    <td><%= pc.getListaTiemposSector() != null ? pc.getListaTiemposSector().size() : 0 %></td>
                                </tr>
                                <%}%>
                            <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>                     
<br>
<br>
<%@include file="components/bodyFinal.jsp"%>