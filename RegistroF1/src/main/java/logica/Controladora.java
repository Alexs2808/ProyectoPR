    package logica;

    import java.util.ArrayList;
    import java.util.List;
    import persistencia.ControladoraPersistencia;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


    public class Controladora {

        ControladoraPersistencia controlPersis = new ControladoraPersistencia();
        //JSP --> SERVLET --> CONTROLADORA_LOGICA --> CONTROLADORA_PERSISTENCIA --> JPA --> REALIZA TODAS LAS ACCIONES EN LA BD
        private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 


        /*-----------METODOS EQUIPOS-------------------*/

        public void crearUsuario(String nombreEquipo, String nombreUsuario, String Contrasenia){

            String ContraseniaSegura = passwordEncoder.encode(Contrasenia);

            Usuarios usu = new Usuarios();
            usu.setNombreEquipo(nombreEquipo);
            usu.setNombreUsuario(nombreUsuario);
            usu.setContrasenia(ContraseniaSegura);
            controlPersis.crearUsuario(usu);
        }

        public List<Usuarios>traerUsuarios(){
            return controlPersis.traerUsuarios();
        }

        public boolean Comprobacion(String NombreUsuario, String Contrasenia) {

            boolean ingreso = false;

            List<Usuarios>traerEquipos = new ArrayList<Usuarios>();
            traerEquipos = controlPersis.traerUsuarios();

            for(Usuarios usu:traerEquipos){
                if(usu.getNombreUsuario().equals(NombreUsuario)){
                    if(usu.getContrasenia()!=null && passwordEncoder.matches(Contrasenia, usu.getContrasenia())){
                        ingreso = true;
                    }else{
                        ingreso=false;
                    }

                }
            }

            return ingreso;
        }

        public Usuarios getUsuario(String nombreUsuario){
            return controlPersis.getUsuarios(nombreUsuario);
        }

        /*-----------METODOS PILOTOS-------------------*/

        public void crearPiloto(String nombrePiloto, String apellidoPaterno, String apellidoMaterno, String nacionalidad, int edad, Usuarios equipoLogeado) {

            Pilotos pil = new Pilotos();
            pil.setNombrePiloto(nombrePiloto);
            pil.setApellidoPaterno(apellidoPaterno);
            pil.setApellidoMaterno(apellidoMaterno);
            pil.setNacionalidad(nacionalidad);
            pil.setEdad(edad);
            pil.setEquipo(equipoLogeado);

            controlPersis.crearPiloto(pil);

        }

        public List<Pilotos>traerPilotos(){
            return controlPersis.traerPilotos();
        }

        public Pilotos buscarPilotos(int Piloto){
            return controlPersis.buscarPilotos(Piloto);
        }


        public List<Pilotos>getPilotos() {
            return controlPersis.getPilotos();
        }

        public void desactivarPiloto(int id) throws Exception{
            Pilotos piloto = controlPersis.buscarPilotos(id);
            controlPersis.desactivarPiloto(id);

        }

        public void editarPiloto(Pilotos pil) {
            controlPersis.editarPiloto(pil);
        }



        /*----------METODOS PISTAS----------*/
        public List<Pistas>traerPistas(){
            return controlPersis.traerPistas();
        }

        public Pistas buscarPistas(int Pista){
            return controlPersis.buscarPista(Pista);
        }

        /*---------METODOS TIPOS DE TESTER-------------*/
        public List<TipoTester>traerTester(){
            return controlPersis.traerTesters();
        }

        public TipoTester buscarTest(int Tester){
            return controlPersis.buscarTest(Tester);
        }


        /*--------METODOS PRACTICAS----------*/

        public Practicas crearPractica(int Pista, int Piloto, int Tester) {
            Pistas pts = controlPersis.buscarPista(Pista);
            Pilotos pil = controlPersis.buscarPilotos(Piloto);
            TipoTester tt = controlPersis.buscarTest(Tester);

            Practicas prc = new Practicas();
            prc.setPista(pts);
            prc.setPiloto(pil);
            prc.setTipoTester(tt);
            //prc.setFecha(Fecha)

            controlPersis.crearPractica(prc);
            return prc;
        }

        /*public void borrarPracticasConPilotos(int id){
            controlPersis.borrarPracticasConPilotos(id);
        }*/
        
        public List<Practicas>traerPracticas(){
            return controlPersis.traerPracticas();
        }
        

        /*----------------------------------*/

        public void crearTest(int Practic, int Vuelta, int Sector, float TiempoSector, float Distancia) {
        //me equivoque de nombre, era crear tiempo o sector    
            TiemposSector ts = new TiemposSector();

            Practicas Prac = controlPersis.buscarPracticas(Practic);

            ts.setPractica(Prac);
            ts.setNumVuelta(Vuelta);
            ts.setNumSector(Sector);
            ts.setTiempoSector(TiempoSector);
            ts.setRitmo(Distancia);

            controlPersis.crearTest(ts);
        }



    }
