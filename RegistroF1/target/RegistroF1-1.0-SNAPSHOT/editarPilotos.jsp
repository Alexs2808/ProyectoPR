<%@page import="logica.Pilotos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/header.jsp"%>
<%@include file="components/bodyCabecera.jsp"%>

<br>
<h1 align="center">Actualizacion de Pilotos</h1>
<br>

<%Pilotos pil = (Pilotos)request.getSession().getAttribute("Editar");%>


<div class="row gx-5 justify-content-center">
                <div class="col-lg-8 col-xl-6">

                    <form id="contactForm" data-sb-form-api-token="API_TOKEN" action="SvPilotosEdit" method="POST">
                        <!-- Name input-->
                        <div class="form-floating mb-3">
                            <input class="form-control" id="NombrePiloto" type="text" placeholder="Nombre del piloto" data-sb-validations="required" name="NombrePiloto" 
                                   value="<%=pil.getNombrePiloto()%>"/>
                            <label for="NombrePiloto">Nombre del piloto</label>
                            <div class="invalid-feedback" data-sb-feedback="NombrePiloto">Es requerido el nombre del piloto.</div>
                        </div>
                        <div class="form-floating mb-3">
                            <input class="form-control" id="ApellidoPaterno" type="text" placeholder="Apellido Paterno" data-sb-validations="required" name="ApellidoPaterno"
                                   value="<%=pil.getApellidoPaterno()%>"/>
                            <label for="ApellidoPaterno">Apellido Paterno</label>
                            <div class="invalid-feedback" data-sb-feedback="ApellidoPaterno:required">Es requerido el apellido del piloto.</div>
                        </div>
                        <div class="form-floating mb-3">
                            <input class="form-control" id="ApellidoMaterno" type="text" placeholder="Apellido Materno" data-sb-validations="required" name="ApellidoMaterno"
                                   value="<%=pil.getApellidoMaterno()%>"/>
                            <label for="ApellidoMaterno">Apellido Materno</label>
                            <div class="invalid-feedback" data-sb-feedback="ApellidoMaterno:required">Es requerido el apellido del piloto.</div>
                        </div>

                        <div class="form-floating mb-3">
                            <select class="form-select" id="Nacionalidad" name="Nacionalidad" placeholder="Nacionalidad" data-sb-validations="required">
                                <option value="<%=pil.getNacionalidad()%>" selected disabled>Seleccione Nacionalidad</option>
                                <option value="Mexicano">Mexicano</option>
                                <option value="Frances">Frances</option>
                                <option value="Ingles">Ingles</option>
                                <option value="Espaniol">Espaniol</option>
                                <option value="Australiano">Australiano</option>
                            </select>
                            <label for="Nacionalidad">Nacionalidad</label>
                            <div class="invalid-feedback" data-sb-feedback="Nacionalidad:required">Es requerida una Nacionalidad.</div>
                        </div>

                        <div class="form-floating mb-3">
                            <input class="form-control" id="Edad" type="number" placeholder="Edad" data-sb-validations="required" name="Edad" 
                                   value="<%=pil.getEdad()%>"/>
                            <label for="Edad">Edad</label>
                            <div class="invalid-feedback" data-sb-feedback="Edad:required">Es requerida la edad.</div>
                        </div>                              

                        <div class="d-none" id="submitSuccessMessage">
                            <div class="text-center mb-3">
                                <div class="fw-bolder">Form submission successful!</div>
                                To activate this form, sign up at
                                <br />
                                <a href="https://startbootstrap.com/solution/contact-forms">https://startbootstrap.com/solution/contact-forms</a>
                            </div>
                        </div>
                        <div class="d-none" id="submitErrorMessage"><div class="text-center text-danger mb-3">Error sending message!</div></div>
                        <!-- Submit Button-->
                        <div class="d-grid"><button class="btn btn-primary btn-lg" id="submitButton" type="submit">Guardar informacion</button></div>
                    </form>
                </div>
            </div>
<br><br>
<%@include file="components/bodyFinal.jsp"%>
