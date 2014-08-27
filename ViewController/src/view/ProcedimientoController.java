package view;

import java.io.Serializable;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import model.JavaServiceFacade;
import model.Procedimiento;

import org.apache.myfaces.trinidad.event.SelectionEvent;

 /**
  * Clase (Managed Bean) utilizada para la administración de los procedimientos
  * que se registran en el sistema, y sirven para generar odontogramas, presupuestos, entre otros.
  * @author Roberto Quesada
  * @version 1.0, 26/01/2014
  */
public class ProcedimientoController implements Serializable {
    
    /** Servicio que permite enlace con la base de datos */
    private JavaServiceFacade service;
    /** Objeto Procedimiento que sirve como filtro cuando se realizan búsquedas */
    private Procedimiento filtro;
    /** Procedimiento a registrar */
    private Procedimiento procedimientoRegistrar;
    /** Procedimiento a editar */
    private Procedimiento procedimientoEditar;
    /** Lista de los procedimientos registrados en el sistema */
    private List<Procedimiento> listaProcedimientos;
    /** Mensaje de retroalimentación al registrar un procedimiento */
    private String mensajeRegistrar;

    /**
     * Constructor de la clase.
     */
    public ProcedimientoController() {
        setService(new JavaServiceFacade());
        setFiltro(new Procedimiento());
        setProcedimientoRegistrar(new Procedimiento());
        setProcedimientoEditar(new Procedimiento());
        setListaProcedimientos(service.getProcedimientoFindAll());
        setMensajeRegistrar("");
    }

    /**
     * Busca procedimientos que tengan un nombre aproximado al ingresado en la búsqueda.
     * @return String Nombre de la página o valor null
     * @throws Exception Si se presenta un error al intentar obtener registros de la base de datos
     */
    public String buscarProcedimientos(){
        try{
            if(getFiltro().getNombreProcedimiento().equalsIgnoreCase("")){
                setListaProcedimientos(service.getProcedimientoFindAll());
            }else{
                setListaProcedimientos(service.getProcedimientoFindByName(filtro));
            }
            service.commitTransaction();
            setMensajeRegistrar("");
            return null;
        }catch(Exception ex){
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Ha ocurrido un error al intentar buscar el procedimiento. Error: " + ex.getMessage()));
            return null;
        }    
    }

    /**
     * Registra un nuevo procedimiento en la base de datos.
     * @return String Nombre de la página o valor null
     * @throws Exception Se genera si se produce un error al intentar registrar en la base de datos
     */
    public String registrarProcedimiento(){
        try{
            if(!procedimientoRegistrar.getNombreProcedimiento().equals("")){
                service.persistProcedimiento(getProcedimientoRegistrar());
                service.commitTransaction();
                this.setListaProcedimientos(service.getProcedimientoFindAll());
                setMensajeRegistrar("Se ha registrado el procedimiento");
                limpiarRegistroProcedimiento();
            }else{
                setMensajeRegistrar("Ingrese un nombre para el procedimiento");
            }
            return null;
        }catch(Exception ex){
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Ha ocurrido un error al intentar registrar el procedimiento. Error: " + ex.getMessage()));
            return null;
        }    
    }

    /**
     * Crea una nueva instancia del procedimiento a registrar.
     */
    public void limpiarRegistroProcedimiento() {
        this.setProcedimientoRegistrar(new Procedimiento());
    }

    /**
     * Fija el procedimiento a editar según se seleccione en la tabla de procedimientos.
     * @param selectionEvent Evento que se genera al momento de seleccionar una fila en la tabla
     */
    public void seleccionarProcedimiento(SelectionEvent selectionEvent) {
        int row = (Integer) selectionEvent.getAddedSet().iterator().next();
        this.setProcedimientoEditar(this.getListaProcedimientos().get(row));
        setMensajeRegistrar("");
    }

    /**
     * Edita un procedimiento registrado.
     * @return String Nombre de la página o valor null
     * @throws Exception Se genera si se produce un error al actualizar la base de datos
     */
    public String editarProcedimiento(){
        try{
            service.mergeProcedimiento(getProcedimientoEditar());
            service.commitTransaction();
            this.setListaProcedimientos(service.getProcedimientoFindAll());
            setMensajeRegistrar("");
            return null;
        }catch(Exception ex){
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Ha ocurrido un error al intentar editar el procedimiento. Error: " + ex.getMessage()));
            return null;
        }    
    }

    /**
     * Deshabilita un procedimiento registrado.
     * @return String Nombre de la página o valor null
     * @throws Exception Se genera si se produce un error al intentar actualizar la base de datos
     */
    public String eliminarProcedimiento(){
        try{
            this.procedimientoEditar.setHabilitado(false);
            service.mergeProcedimiento(getProcedimientoEditar());
            service.commitTransaction();
            this.setListaProcedimientos(service.getProcedimientoFindAll());
            setMensajeRegistrar("");
            return null; 
        }catch(Exception ex){
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Ha ocurrido un error al intentar eliminar el procedimiento. Error: " + ex.getMessage()));
            return null;
        }    
    }

    // Métodos getter y setter

    public void setService(JavaServiceFacade service) {
        this.service = service;
    }

    public JavaServiceFacade getService() {
        return service;
    }

    public void setFiltro(Procedimiento filtro) {
        this.filtro = filtro;
    }

    public Procedimiento getFiltro() {
        return filtro;
    }

    public void setProcedimientoRegistrar(Procedimiento procedimientoRegistrar) {
        this.procedimientoRegistrar = procedimientoRegistrar;
    }

    public Procedimiento getProcedimientoRegistrar() {
        return procedimientoRegistrar;
    }

    public void setProcedimientoEditar(Procedimiento procedimientoEditar) {
        this.procedimientoEditar = procedimientoEditar;
    }

    public Procedimiento getProcedimientoEditar() {
        return procedimientoEditar;
    }

    public void setListaProcedimientos(List<Procedimiento> listaProcedimientos) {
        this.listaProcedimientos = listaProcedimientos;
    }

    public List<Procedimiento> getListaProcedimientos() {
        return listaProcedimientos;
    }

    public void setMensajeRegistrar(String mensajeRegistrar) {
        this.mensajeRegistrar = mensajeRegistrar;
    }

    public String getMensajeRegistrar() {
        return mensajeRegistrar;
    }
}
