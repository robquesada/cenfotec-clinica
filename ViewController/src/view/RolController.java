package view;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import model.JavaServiceFacade;
import model.Permiso;
import model.Rol;
import model.Rolpermiso;

 /**
  * Clase (Managed Bean) utilizada para el manejo de roles y permisos del sistema y sus usuarios.
  * @author Roberto Quesada
  * @version 1.0, 26/01/2014
  */
public class RolController implements Serializable {
    
    /** Servicio que permite enlace con la base de datos */
    private JavaServiceFacade service;
    /** Rol a registrar */
    private Rol rolRegistrar;
    /** Rol a editar */
    private Rol rolEditar;
    /** Lista de los roles del sistema */
    private List<Rol> listaRoles;
    /** Lista de los permisos a registrar */
    private List<Permiso> listaPermisosRegistrar;
    /** Lista de los permisos a registrar */
    private List<Permiso> listaPermisosEditar;
    /** Lista de identificadores String de los permisos generales a registrar */
    private List<String> listaIdPermisosGeneral;
    /** Lista de identificadores String de los permisos a registrar para opciones de pacientes */
    private List<String> listaIdPermisosPacientesOpciones;
    /** Lista de identificadores String de los permisos a registrar de configuración */
    private List<String> listaIdPermisosConfiguracion;
     /** Lista de identificadores String de los permisos generales a editar */
    private List<String> listaEditarPermisosGeneral;
    /** Lista de identificadores String de los permisos a editar para opciones de pacientes */
    private List<String> listaEditarPermisosOpciones;
    /** Lista de identificadores String de los permisos a editar de configuración */
    private List<String> listaEditarPermisosConfiguracion;

    /**
     * Constructor de la clase.
     */
    public RolController() {
        setService(new JavaServiceFacade());
        setRolRegistrar(new Rol());
        setRolEditar(new Rol());
        setListaRoles(service.getRolFindAll());
        setListaPermisosRegistrar(new ArrayList());
        setListaIdPermisosGeneral(new ArrayList());
        setListaIdPermisosPacientesOpciones(new ArrayList());
        setListaIdPermisosConfiguracion(new ArrayList());
        setListaEditarPermisosGeneral(new ArrayList());
        setListaEditarPermisosOpciones(new ArrayList());
        setListaEditarPermisosConfiguracion(new ArrayList());
    }

    /**
     * Registra un nuevo rol y los permisos seleccionados para este.
     * @return String Nombre de la página o valor null
     * @throws Exception Se genera si se produce un error al intentar registrar en la base de datos
     */
    public String registrarRol(){
        try{
            for (int i=0; i<getListaIdPermisosGeneral().size(); i++) {
                listaPermisosRegistrar.add(service.getPermisoFindByDesc(getListaIdPermisosGeneral().get(i)).get(0));
            }
            for (int i=0; i<getListaIdPermisosPacientesOpciones().size(); i++) {
                listaPermisosRegistrar.add(service.getPermisoFindByDesc(getListaIdPermisosPacientesOpciones().get(i)).get(0));
            }
            for (int i=0; i<getListaIdPermisosConfiguracion().size(); i++) {
                listaPermisosRegistrar.add(service.getPermisoFindByDesc(getListaIdPermisosConfiguracion().get(i)).get(0));
            }
            service.persistRol(rolRegistrar);
            service.commitTransaction();
            for (int i=0; i<this.getListaPermisosRegistrar().size(); i++) {
                Rolpermiso rolpermisoRegistrar = new Rolpermiso();
                rolpermisoRegistrar.setPermiso(getListaPermisosRegistrar().get(i));
                rolpermisoRegistrar.setRol(service.getRolFindAll().get(service.getRolFindAll().size()-1));
                service.persistRolpermiso(rolpermisoRegistrar); 
                service.commitTransaction();
            }
            service.commitTransaction();
            limpiarRegistro();
            return null;
        }catch(Exception ex){
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Ha ocurrido un error al intentar registrar un nuevo rol. Error: " + ex.getMessage()));
            return null;
        }    
    }

    /**
     * Crea nuevas instancias del rol a registrar
     * y de las listas de identificadores de permisos a registrar
     */
    private void limpiarRegistro(){
        this.setRolRegistrar(new Rol());
        this.setListaIdPermisosGeneral(new ArrayList());
        this.setListaIdPermisosPacientesOpciones(new ArrayList());
        this.setListaIdPermisosConfiguracion(new ArrayList());
    }

    /**
     * Fija el rol a editar según se seleccione en la lista de roles, también todos los permisos
     * que este tenga asignados.
     * @param valueChangeEvent Evento que se genera al momento de seleccionar un nuevo rol de la lista
     */
    public void seleccionarRol(ValueChangeEvent valueChangeEvent) {
        setRolEditar((Rol)valueChangeEvent.getNewValue());
        
        if(rolEditar!=null){
            setListaEditarPermisosGeneral(new ArrayList());
            setListaEditarPermisosOpciones(new ArrayList());
            setListaEditarPermisosConfiguracion(new ArrayList());
            
            List<Rolpermiso> lista = service.getRolpermisoFindByRol(rolEditar);
            
            for(Rolpermiso rolpermiso : lista){
                if(rolpermiso.getPermiso().getDescripcion().equals("AUsuarios")){
                    listaEditarPermisosGeneral.add("AUsuarios");
                }
                if(rolpermiso.getPermiso().getDescripcion().equals("CAgenda")){
                    listaEditarPermisosGeneral.add("CAgenda");
                }
                if(rolpermiso.getPermiso().getDescripcion().equals("AAgenda")){
                    listaEditarPermisosGeneral.add("AAgenda");
                }
            }
            
            for(Rolpermiso rolpermiso : lista){
                if(rolpermiso.getPermiso().getDescripcion().equals("PCuentas")){
                    listaEditarPermisosOpciones.add("PCuentas");
                }
                if(rolpermiso.getPermiso().getDescripcion().equals("POdontogramas")){
                    listaEditarPermisosOpciones.add("POdontogramas");
                }
            }
            
            for(Rolpermiso rolpermiso : lista){
                if(rolpermiso.getPermiso().getDescripcion().equals("CPermisos")){
                    listaEditarPermisosConfiguracion.add("CPermisos");
                }
                if(rolpermiso.getPermiso().getDescripcion().equals("CProcedimientos")){
                    listaEditarPermisosConfiguracion.add("CProcedimientos");
                }
            }
        } 
    }

    /**
     * Edita los datos de un rol registrado y sus permisos.
     * @return String Nombre de la página o valor null
     * @throws Exception Se genera si se produce un error al intentar actualizar la base de datos
     */
    public String editarRol(){
        try{
            if(rolEditar!=null){
                List<Rolpermiso> lista = service.getRolpermisoFindByRol(rolEditar);
                for(Rolpermiso rp : lista){
                    rp.setHabiitado(false);
                    service.mergeRolpermiso(rp);
                    service.commitTransaction();
                }
                
                for (int i=0; i<getListaEditarPermisosGeneral().size(); i++) {
                    listaPermisosEditar.add(service.getPermisoFindByDesc(getListaEditarPermisosGeneral().get(i)).get(0));
                }
                for (int i=0; i<getListaEditarPermisosOpciones().size(); i++) {
                    listaPermisosEditar.add(service.getPermisoFindByDesc(getListaEditarPermisosOpciones().get(i)).get(0));
                }
                for (int i=0; i<getListaEditarPermisosConfiguracion().size(); i++) {
                    listaPermisosEditar.add(service.getPermisoFindByDesc(getListaEditarPermisosConfiguracion().get(i)).get(0));
                }
                
                for (int i=0; i<this.getListaPermisosRegistrar().size(); i++) {
                    Rolpermiso rolpermisoEditar = new Rolpermiso();
                    rolpermisoEditar.setPermiso(getListaPermisosEditar().get(i));
                    rolpermisoEditar.setRol(rolEditar);
                    service.persistRolpermiso(rolpermisoEditar); 
                    service.commitTransaction();
                }
                
                service.mergeRol(this.rolEditar);
                service.commitTransaction();
            }
            return null;
        }catch(Exception ex){
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Ha ocurrido un error al intentar editar el rol. Error: " + ex.getMessage()));
            return null;
        }    
    }

    /**
     * Deshabilita un rol registrado.
     * @return String Nombre de la página o valor null
     * @throws Exception Se genera si se produce un error al intentar actualizar la base de datos
     */
    public String eliminarRol(){
        try{
            rolEditar.setHabilitado(false);
            service.mergeRol(rolEditar);
            service.commitTransaction();
            return null;
        }catch(Exception ex){
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Ha ocurrido un error al intentar eliminar el rol. Error: " + ex.getMessage()));
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

    public void setRolRegistrar(Rol rolRegistrar) {
        this.rolRegistrar = rolRegistrar;
    }

    public Rol getRolRegistrar() {
        return rolRegistrar;
    }

    public void setRolEditar(Rol rolEditar) {
        this.rolEditar = rolEditar;
    }

    public Rol getRolEditar() {
        return rolEditar;
    }

    public void setListaRoles(List<Rol> listaRoles) {
        this.listaRoles = listaRoles;
    }

    public List<Rol> getListaRoles() {
        return service.getRolFindAll();
    }

    public void setListaPermisosRegistrar(List<Permiso> listaPermisosRegistrar) {
        this.listaPermisosRegistrar = listaPermisosRegistrar;
    }

    public List<Permiso> getListaPermisosRegistrar() {
        return listaPermisosRegistrar;
    }

    public void setListaIdPermisosGeneral(List<String> listaIdPermisosGeneral) {
        this.listaIdPermisosGeneral = listaIdPermisosGeneral;
    }

    public List<String> getListaIdPermisosGeneral() {
        return listaIdPermisosGeneral;
    }

    public void setListaIdPermisosPacientesOpciones(List<String> listaIdPermisosPacientesOpciones) {
        this.listaIdPermisosPacientesOpciones = listaIdPermisosPacientesOpciones;
    }

    public List<String> getListaIdPermisosPacientesOpciones() {
        return listaIdPermisosPacientesOpciones;
    }

    public void setListaIdPermisosConfiguracion(List<String> listaIdPermisosConfiguracion) {
        this.listaIdPermisosConfiguracion = listaIdPermisosConfiguracion;
    }

    public List<String> getListaIdPermisosConfiguracion() {
        return listaIdPermisosConfiguracion;
    }

    public void setListaEditarPermisosGeneral(List<String> listaEditarPermisosGeneral) {
        this.listaEditarPermisosGeneral = listaEditarPermisosGeneral;
    }

    public List<String> getListaEditarPermisosGeneral() {
        return listaEditarPermisosGeneral;
    }

    public void setListaEditarPermisosOpciones(List<String> listaEditarPermisosOpciones) {
        this.listaEditarPermisosOpciones = listaEditarPermisosOpciones;
    }

    public List<String> getListaEditarPermisosOpciones() {
        return listaEditarPermisosOpciones;
    }

    public void setListaEditarPermisosConfiguracion(List<String> listaEditarPermisosConfiguracion) {
        this.listaEditarPermisosConfiguracion = listaEditarPermisosConfiguracion;
    }

    public List<String> getListaEditarPermisosConfiguracion() {
        return listaEditarPermisosConfiguracion;
    }
    
    public void setListaPermisosEditar(List<Permiso> listaPermisosEditar) {
        this.listaPermisosEditar = listaPermisosEditar;
    }

    public List<Permiso> getListaPermisosEditar() {
        return listaPermisosEditar;
    }
}
