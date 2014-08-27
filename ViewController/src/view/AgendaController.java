package view;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import oracle.adf.view.rich.datatransfer.DataFlavor;
import oracle.adf.view.rich.datatransfer.Transferable;
import oracle.adf.view.rich.dnd.CalendarDropSite;
import oracle.adf.view.rich.dnd.DnDAction;
import oracle.adf.view.rich.event.DropEvent;
import oracle.adf.view.rich.model.CalendarActivity;

import model.CalendarActivityClinica;
import model.CalendarModelClinica;

import model.CalendarProviderClinica;
import model.Cita;
import model.JavaServiceFacade;

import model.Paciente;
import model.Rolpermiso;
import model.Usuario;

import oracle.adf.view.rich.event.CalendarActivityEvent;
import oracle.adf.view.rich.event.CalendarEvent;

import oracle.adf.view.rich.model.CalendarActivity.TimeType;

 /**
  * Clase (Managed Bean) utilizada para el control de las agendas del sistema.
  * La palabra 'actividad' y 'cita', son sinónimos.
  * @author Roberto Quesada
  * @version 1.0, 26/01/2014
  */
public class AgendaController implements Serializable{
    
    /** Servicio que permite enlace con la base de datos */
    private JavaServiceFacade service;
    /** Modelo del componente calendario */
    private CalendarModelClinica model;
    /** Proveedor de las actividades del componente calendario */
    private CalendarProviderClinica proveedor;
    /** Actividad a registrar del componente calendario */
    private CalendarActivityClinica actividadRegistrar;
    /** Actividad a editar del componente calendario */
    private CalendarActivityClinica actividadEditar;
    /** Lista de usuarios del sistema */
    private List<Usuario> listaUsuarios;
    /** Lista de pacientes del sistema */
    private List<Paciente> listaPacientes;
    /** Indicador del tipo tiempo de la actividad a registrar */
    private boolean todoElDia;
    /** Indicador del tipo tiempo de la actividad a editar */
    private boolean todoElDiaEditar;
    /** Fecha inicio de la actividad a registrar */
    private Date inicioRegistrar;
    /** Fecha fin de la actividad a registrar */
    private Date finRegistrar;
    /** Fecha inicio de la actividad a editar */
    private Date inicioEditar;
    /** Fecha fin de la actividad a editar */
    private Date finEditar;
    /** Mensaje de retroalimentación al registrar una actividad */
    private String mensajeRegistrar;
    /** Mensaje de retroalimentación al editar una actividad */
    private String mensajeEditar;
    /** Usuario propietario de la agenda actual. Si es null, está en uso la agenda general de la clínica */
    private Usuario usuarioAgenda;

    /**
     * Constructor de la clase.
     */
    public AgendaController(){
        setService(new JavaServiceFacade());
        setActividadRegistrar(new CalendarActivityClinica());
        setModel(new CalendarModelClinica());
        if(this.isPermisoAgendaPersonal()){
            setUsuarioAgenda(LoginController.getUsuarioLogged());
        }
        generarActividades();
        setListaPacientes(service.getPacienteFindAll());
        setListaUsuarios(service.getUsuarioFindAll());
        setMensajeRegistrar("");
        setMensajeEditar("");
    }

    /**
     * Carga desde la base de datos y genera las actividades que se van a mostrar en el calendario,
     * esto dependiendo de si se ha seleccionado ver la agenda de un usuario o una general con las
     * citas de todos los usuarios.
     * @return String nombre de la página o valor null
     * @throws Exception Se genera si se produce un error al consultar la base de datos
     */
    public String generarActividades(){
         try{
            List<Cita> listaCitas;
            if(usuarioAgenda==null){
                listaCitas = service.getCitaFindAll();
            }else{
                listaCitas = service.getCitaFindByUsuario(usuarioAgenda);
                actividadRegistrar.setUsuario(usuarioAgenda);
            }
            List<CalendarActivity> listaActividades = new ArrayList();
            
            for(Cita cita : listaCitas){
                CalendarProviderClinica prov = new CalendarProviderClinica(cita.getUsuario1().getIdUsuario()+"",
                                                                           cita.getUsuario1().getNombre()+" "+
                                                                           cita.getUsuario1().getPrimerApellido()+" "+
                                                                           cita.getUsuario1().getSegundoApellido());
                TimeType tt;
                if(cita.getEstado().equals("ALLDAY")){
                    tt = CalendarActivity.TimeType.ALLDAY;
                }else{
                    tt = CalendarActivity.TimeType.TIME;
                }
                CalendarActivityClinica ca = new CalendarActivityClinica(cita.getIdCita(),
                                                                         cita.getDescripcion(), 
                                                                         cita.getFechaInicio(), 
                                                                         cita.getFechaFin(), 
                                                                         tt,
                                                                         prov);
                ca.setUsuario(cita.getUsuario1());
                ca.setPaciente(cita.getCuentapaciente3());
                listaActividades.add(ca);
            }
            getModel().setListaActividades(listaActividades);
            return null;
        }catch(Exception ex){
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Ha ocurrido un error al intentar cargar las citas. Error: " + ex.getMessage()));
            return null;
        } 
    }

    /**
     * Se ejecuta cada vez que se haya dado clic al calendario, fija las fechas de inicio
     * y fin de una actividad a registrar.
     * @param ce Evento que se genera al hacer clic en el calendario
     */
    public void calendarListener(CalendarEvent ce){
        Date triggerDate = (Date)ce.getTriggerDate().clone();
        setInicioRegistrar(triggerDate);
        setFinRegistrar(triggerDate);
    }

    /**
     * Registra todos los datos una nueva cita en la base de datos
     * y la agrega en el modelo del calendario que se muestra en pantalla.
     * @return String Nombre de la página o valor null
     * @throws Exception Se genera si se presenta un error al intentar registrar en la base de datos
     */
    public String agregarCita() {
        try{
            if(inicioRegistrar.before(finRegistrar) || !inicioRegistrar.after(finRegistrar)){
                setMensajeRegistrar("");
                
                String tt;
                if(this.isTodoElDia()){
                    tt = "ALLDAY";
                }else{
                    tt = "TIME";
                }
                
                Cita cita = new Cita();
                cita.setIdCita((service.getCitaFindAllByTodo().size()+1)+"");
                cita.setDescripcion(actividadRegistrar.getTitle());
                cita.setEstado(tt);
                
                Timestamp timestampInicio = new Timestamp(inicioRegistrar.getTime());  
                cita.setFechaInicio(timestampInicio);
                
                Timestamp timestampFin = new Timestamp(finRegistrar.getTime());  
                cita.setFechaFin(timestampFin);
                
                cita.setHabilitado(true);
                cita.setCuentapaciente3(actividadRegistrar.getPaciente());
                cita.setProveedor(actividadRegistrar.getUsuario().getNombre()+" "+
                                  actividadRegistrar.getUsuario().getSegundoApellido()+" "+
                                  actividadRegistrar.getUsuario().getPrimerApellido());
                cita.setUsuario1(actividadRegistrar.getUsuario());
                
                service.persistCita(cita);
                service.commitTransaction();
                
                generarActividades();
                
                actividadRegistrar = new CalendarActivityClinica();
                
                return null;
            }else{
                setMensajeRegistrar("La fecha de inicio debe ser menor o igual a la final");
                return null;
            }
        }catch(Exception ex){
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Ha ocurrido un error al intentar registrar una nueva cita. Error: " + ex.getMessage()));
            return null;
        } 
    }

    /**
     * Obtiene la actividad cliqueada y se fijan los datos para poder editarlos.
     * @param ae Evento que se genera al hacer clic sobre una actividad del calendario.
     */
    public void activityListener(CalendarActivityEvent ae){
        CalendarActivity activity = ae.getCalendarActivity();
    
        if(activity != null){
            setActividadEditar((CalendarActivityClinica)activity);
            if(actividadEditar.getTimeType().equals(CalendarActivity.TimeType.ALLDAY)){
                setTodoElDiaEditar(true);
            }else{
                setTodoElDiaEditar(false);
            }
            setInicioEditar(actividadEditar.getStartDate(null));
            setFinEditar(actividadEditar.getEndDate(null));
        }else{
            System.out.println("No activity with event " + ae.toString());
        }
    }

    /**
     * Edita los datos de una actividad del calendario.
     * @return String Nombre de la página o valor null
     * @throws Exception Se genera si ocurre un error al intentar actualizar la base de datos
     */
    public String editarCita() {
        try{
            if(inicioEditar.before(finEditar) || !inicioEditar.after(finEditar)){
                
            }else{
                setMensajeEditar("La fecha de inicio debe ser menor o igual a la final");
            }
            Cita cita = service.getCitaFindById(actividadEditar.getId()).get(0);
            
            String tt;
            if(this.isTodoElDia()){
                tt = "ALLDAY";
            }else{
                tt = "TIME";
            }
            cita.setDescripcion(actividadEditar.getTitle());
            cita.setEstado(tt);
            
            Timestamp timestampInicio = new Timestamp(inicioEditar.getTime());  
            cita.setFechaInicio(timestampInicio);
            
            Timestamp timestampFin = new Timestamp(finEditar.getTime());  
            cita.setFechaFin(timestampFin);
            
            cita.setCuentapaciente3(actividadEditar.getPaciente());
            cita.setProveedor(actividadEditar.getUsuario().getNombre()+" "+
                              actividadEditar.getUsuario().getSegundoApellido()+" "+
                              actividadEditar.getUsuario().getPrimerApellido());
            cita.setUsuario1(actividadEditar.getUsuario());
            
            service.mergeCita(cita);
            service.commitTransaction();
            generarActividades();
            return null;
        }catch(Exception ex){
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Ha ocurrido un error al intentar editar la actividad. Error: " + ex.getMessage()));
            return null;
        } 
    }

    /**
     * Deshabilita la actividad de manera que no se vuelva a mostrar en ningún calendario.
     * @return String Nombre de la página o valor null
     * @throws Exception Se genera si se produce un error al intentar actualizar la base de datos
     */
    public String eliminarCita() {
        try{
            Cita cita = service.getCitaFindById(actividadEditar.getId()).get(0);
            cita.setHabilitado(false);
            service.mergeCita(cita);
            service.commitTransaction();
            generarActividades();
            return null;
        }catch(Exception ex){
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Ha ocurrido un error al intentar eliminar la cita. Error: " + ex.getMessage()));
            return null;
        } 
    }

    /**
     * Controla el arrastre de una actividad por el calendario.
     * Se ejecuta a la hora de soltar la actividad sobre otra fecha diferente a la que esta tiene.
     * Fija las nuevas fechas de inicio y de fin de la actividad arrastrada y soltada.
     * @param dropEvent Evento que se ejecuta al momento de soltar la actividad
     * @return DnAction.MOVE Acción de mover la actividad
     */
    public DnDAction handleDrop(DropEvent dropEvent) {
        Transferable transferable = dropEvent.getTransferable();
        CalendarDropSite dropSite = (CalendarDropSite)dropEvent.getDropSite();
        Date dropSiteDate = dropSite.getDate();
        CalendarActivity.TimeType timeType = dropSite.getTimeType();
        
        // Obtiene la actividad arrastrada
        CalendarActivityClinica activity = (CalendarActivityClinica)transferable.getData(DataFlavor.getDataFlavor(CalendarActivity.class));
        
        // Obtener hora y minutos de fecha de inicio de la actividad
        Calendar calStartDate = Calendar.getInstance();
        calStartDate.setTime(activity.getStartDate(null));
        int horaInicio = calStartDate.get(Calendar.HOUR);
        int minutosInicio = calStartDate.get(Calendar.MINUTE);
        
        // Obtener hora y minutos e fecha fin de la actividad
        Calendar calEndDate = Calendar.getInstance(); 
        calEndDate.setTime(activity.getEndDate(null));
        int horaFin = calEndDate.get(Calendar.HOUR);
        int minutosFin = calEndDate.get(Calendar.MINUTE);
        
        // Calcula la diferencia de días entre la fecha de inicio y la de fin
        long delta = activity.getEndDate(null).getTime() - activity.getStartDate(null).getTime();
        
        // Setear hora y minutos de fecha inicio actividad
        Calendar calActivityStartDate = Calendar.getInstance();
        calActivityStartDate.setTime(dropSiteDate);
        calActivityStartDate.set(Calendar.HOUR, horaInicio);
        calActivityStartDate.set(Calendar.MINUTE, minutosInicio);
        
        // Setear hora y minutos de fecha fin actividad
        Calendar calActivityEndDate = Calendar.getInstance();
        calActivityEndDate.setTime(new Date(calActivityStartDate.getTime().getTime() + delta));
        calActivityEndDate.set(Calendar.HOUR, horaFin);
        calActivityEndDate.set(Calendar.MINUTE, minutosFin);
        
        Cita cita = service.getCitaFindById(activity.getId()).get(0);
        
        Timestamp timestampInicio = new Timestamp(calActivityStartDate.getTime().getTime());  
        cita.setFechaInicio(timestampInicio);
        Timestamp timestampFin = new Timestamp(calActivityEndDate.getTime().getTime());  
        cita.setFechaFin(timestampFin);
        
        service.mergeCita(cita);
        service.commitTransaction();
        generarActividades();
        
        return DnDAction.MOVE;
    }

     /**
      * Valida si el usuario tiene permiso de administrar la agenda general de la clínica.
      * @return boolean Valor true si posee el permiso, de lo contrario false
      */
    public boolean isPermisoAgendaClinica() {
        for(Rolpermiso rp : LoginController.getUsuarioLogged().getRol1().getRolpermisoList()){
            if(rp.getPermiso().getDescripcion().equals("CAgenda")){
                return true;
            }
        }
        return false;
    }

    /**
     * Valida si el usuario tiene permiso de administrar su agenda personal.
     * @return boolean Valor true si posee el permiso, de lo contrario false
     */
    public boolean isPermisoAgendaPersonal() {
        for(Rolpermiso rp : LoginController.getUsuarioLogged().getRol1().getRolpermisoList()){
            if(rp.getPermiso().getDescripcion().equals("AAgenda")){
                return true;
            }
        }
        return false;
    }

    // Métodos getter y setter

    public void setService(JavaServiceFacade service) {
        this.service = service;
    }

    public JavaServiceFacade getService() {
        return service;
    }

    public void setModel(CalendarModelClinica model) {
        this.model = model;
    }

    public CalendarModelClinica getModel() {
        return model;
    }

    public void setProveedor(CalendarProviderClinica proveedor) {
        this.proveedor = proveedor;
    }

    public CalendarProviderClinica getProveedor() {
        return proveedor;
    }

    public void setActividadRegistrar(CalendarActivityClinica actividadRegistrar) {
        this.actividadRegistrar = actividadRegistrar;
    }

    public CalendarActivityClinica getActividadRegistrar() {
        return actividadRegistrar;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public List<Usuario> getListaUsuarios() {
        return service.getUsuarioFindAll();
    }

    public void setListaPacientes(List<Paciente> listaPacientes) {
        this.listaPacientes = listaPacientes;
    }

    public List<Paciente> getListaPacientes() {
        return service.getPacienteFindAll();
    }

    public void setTodoElDia(boolean todoElDia) {
        this.todoElDia = todoElDia;
    }

    public boolean isTodoElDia() {
        return todoElDia;
    }

    public void setInicioRegistrar(Date inicioRegistrar) {
        this.inicioRegistrar = inicioRegistrar;
    }

    public Date getInicioRegistrar() {
        return inicioRegistrar;
    }

    public void setFinRegistrar(Date finRegistrar) {
        this.finRegistrar = finRegistrar;
    }

    public Date getFinRegistrar() {
        return finRegistrar;
    }

    public void setActividadEditar(CalendarActivityClinica actividadEditar) {
        this.actividadEditar = actividadEditar;
    }

    public CalendarActivityClinica getActividadEditar() {
        return actividadEditar;
    }

    public void setInicioEditar(Date inicioEditar) {
        this.inicioEditar = inicioEditar;
    }

    public Date getInicioEditar() {
        return inicioEditar;
    }

    public void setFinEditar(Date finEditar) {
        this.finEditar = finEditar;
    }

    public Date getFinEditar() {
        return finEditar;
    }

    public void setTodoElDiaEditar(boolean todoElDiaEditar) {
        this.todoElDiaEditar = todoElDiaEditar;
    }

    public boolean isTodoElDiaEditar() {
        return todoElDiaEditar;
    }

    public void setMensajeRegistrar(String mensajeRegistrar) {
        this.mensajeRegistrar = mensajeRegistrar;
    }

    public String getMensajeRegistrar() {
        return mensajeRegistrar;
    }

    public void setMensajeEditar(String mensajeEditar) {
        this.mensajeEditar = mensajeEditar;
    }

    public String getMensajeEditar() {
        return mensajeEditar;
    }

    public void setUsuarioAgenda(Usuario usuarioAgenda) {
        this.usuarioAgenda = usuarioAgenda;
    }

    public Usuario getUsuarioAgenda() {
        return usuarioAgenda;
    }
}
