<%@page import="logica.TipoTester"%>
<%@page import="logica.Pistas"%>
<%@page import="java.util.List"%>
<%@page import="logica.Pilotos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/header.jsp"%>
<%@include file="components/bodyCabecera.jsp"%>

<%
    List<Pilotos>listaPilotos=(List<Pilotos>)request.getAttribute("listaPilotos");
    List<Pistas>listaPistas=(List<Pistas>)request.getAttribute("listaPistas");
    List<TipoTester>listaTesters=(List<TipoTester>)request.getAttribute("listaTesters");
%>

<br>
<h1 align="center">Registro de Pruebas</h1>
<br>

<div class="row gx-5 justify-content-center">
                <div class="col-lg-8 col-xl-6">

<form id="contactForm" data-sb-form-api-token="API_TOKEN" action="SvPracticas" method="POST">
            
            <div class="form-floating mb-3">
                <select class="form-select" id="Pista" name="Pista" placeholder="Pista" data-sb-validations="required">
                    <option value="" selected disabled>Seleccione la pista</option>
                    <% if (listaPistas != null) {%>
                        <% for (Pistas pts : listaPistas){%>
                        <option value="<%= pts.getIdPista()%>">
                            <%= pts.getNombrePista()%>
                        </option>
                        <%}%>
                    <% } %>
                </select>
                <label for="Pista">Pista</label>
                <div class="invalid-feedback" data-sb-feedback="Pista:required">Es requerida una Pista.</div>
            </div>
            
            <div class="form-floating mb-3">
                <select class="form-select" id="Piloto" name="Piloto" placeholder="Piloto" data-sb-validations="required">
                    <option value="" selected disabled>Seleccione el piloto</option>
                    <% if (listaPilotos != null) {%>
                        <% for (Pilotos pil : listaPilotos){%>
                        <option value="<%= pil.getIdPiloto()%>">
                            <%= pil.getNombrePiloto()%> <%= pil.getApellidoPaterno()%>
                        </option>
                        <%}%>
                    <% } %>
                </select>
                <label for="Piloto">Piloto</label>
                <div class="invalid-feedback" data-sb-feedback="Piloto:required">Es requerido un piloto.</div>
            </div>
            
            <div class="form-floating mb-3">
                <select class="form-select" id="Tester" name="Tester" placeholder="Tester" data-sb-validations="required">
                    <option value="" selected disabled>Seleccione el tipo de Tester</option>
                    <% if (listaTesters != null) {%>
                        <% for (TipoTester tt : listaTesters){%>
                        <option value="<%= tt.getIdTipoTester()%>">
                            <%= tt.getNombreTester()%>
                        </option>
                        <%}%>
                    <% } %>
                </select>
                <label for="Tester">Tester</label>
                <div class="invalid-feedback" data-sb-feedback="Tester:required">Es requerido un Tester.</div>
            </div>
            
            <div class="d-grid"><button class="btn btn-primary btn-lg" id="submitButton" type="submit">Registrar</button></div>
        </form>
                </div>
            </div>
                
        <%
            HttpSession sesion = request.getSession(false);  
            Integer Practic = null;
            if (sesion != null) {
                Practic = (Integer) sesion.getAttribute("Practic");
            }
        %>     
        
        <%if(Practic!=null){%>
        <br>
            <h2 align="center">Registro de Tiempos - Práctica #<%= Practic %></h2>
        <br>
        <div class="row gx-5 justify-content-center">
            <div class="col-lg-8 col-xl-6"> 
                <form id="contactForm" data-sb-form-api-token="API_TOKEN" action="SvTester" method="POST">
                   
                    <input type="hidden" name="Practic" value="<%= Practic %>"/>
                    
                    <div class="form-floating mb-3">
                        <input class="form-control" id="Vuelta" type="number" placeholder="Vuelta" data-sb-validations="required" name="Vuelta" min="1" max="6" />
                        <label for="Vuelta">Vuelta</label>
                        <div class="invalid-feedback" data-sb-feedback="Vuelta:required">Es requerido el numero de vuelta.</div>
                    </div> 

                    <div class="form-floating mb-3">
                        <input class="form-control" id="Sector" type="number" placeholder="Sector" data-sb-validations="required" name="Sector" min="1" max="6"/>
                        <label for="Sector">Sector</label>
                        <div class="invalid-feedback" data-sb-feedback="Sector:required">Es requerido el sectpr.</div>
                    </div> 

                    <div class="form-floating mb-3">
                        <input class="form-control" id="Distancia" type="number" step="0.01" placeholder="Distancia" data-sb-validations="required" name="Distancia" min="1"/>
                        <label for="Distancia">Distancia en metros</label>
                        <div class="invalid-feedback" data-sb-feedback="Distancia:required">Es requerida la distancia en metros.</div>
                    </div>  

                    <div class="form-floating mb-3">
                        <input class="form-control" id="TiempoSector" type="number" step="0.01" placeholder="TiempoSector" data-sb-validations="required" name="TiempoSector" min="1"/>
                        <label for="TiempoSector">Tiempo en segundos</label>
                        <div class="invalid-feedback" data-sb-feedback="TiempoSector:required">Es requerido el tiempo en segundos.</div>
                    </div>                        
                    <div class="d-grid"><button class="btn btn-primary btn-lg" id="submitButton" type="submit">Registrar</button></div>
                    
                </form>

            </div>
        </div>
        <%}else{%>
            <div class="alert alert-info text-center mt-4" role="alert">
                <strong>Primero debes registrar una práctica</strong> usando el formulario de arriba.
            </div>
        <% } %>
<%@include file="components/bodyFinal.jsp"%>
