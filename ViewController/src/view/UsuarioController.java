package view;

import java.io.Serializable;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import model.JavaServiceFacade;
import model.Rol;
import model.Usuario;

import org.apache.myfaces.trinidad.event.SelectionEvent;

 /**
  * Clase (Managed Bean) utilizada para el manejo de los usuarios del sistema, tanto registros y ediciones.
  * @author Roberto Quesada
  * @version 1.0, 26/01/2014
  */
public class UsuarioController implements Serializable {
    
    /** Servicio que permite enlace con la base de datos */
    private JavaServiceFacade service;
    /** Lista de usuarios del sistema */
    private List<Usuario> listaUsuarios;
    /** Lista de roles del sistema */
    private List<Rol> listaRoles;
    /** Usuario que sirve como filtro para búsquedas */
    private Usuario usuarioFiltro;
    /** Usuario a registrar */
    private Usuario usuarioRegistrar;
    /** Confirmación de contraseña del usuario a registrar */
    private String confirmarContrasennaRegistrar;
    /** Mensaje de retroalimentación al registrar un usuario */
    private String mensajeRegistrar;
    /** Usuario a editar */
    private Usuario usuarioEditar;
    /** Contraseña actual del usuario a editar */
    private String contrasennaActualEditar;
    /** Contraseña nueva del usuario a editar */
    private String contrasennaNuevaEditar;  
    /** Mensaje de retroalimentación al cambiar la contraseña */
    private String mensajeContrasenna;
    /** Confirmación de contraseña del usuario a editar */
    private String confirmarContrasennaEditar;
    /** Mensaje de retroalimentación al editar un usuario */
    private String mensajeEditar;
    /** Identificador del usuario seleccionado en la tabla de usuarios */
    private int idUsuarioSeleccionado;

    /**
     * Constructor de la clase.
     */
    public UsuarioController() {
        setService(new JavaServiceFacade());
        setUsuarioFiltro(new Usuario());
        setUsuarioRegistrar(new Usuario());
        setUsuarioEditar(new Usuario());
        setListaUsuarios(service.getUsuarioFindAll());
        setListaRoles(service.getRolFindAll());
    }

    /**
     * Busca usuarios que tengan un nombre aproximado al ingresado en la búsqueda.
     * @return String Nombre de la página o valor null
     * @throws Exception Se genera si se produce un error al consultar la base de datos
     */
    public String buscarUsuarios(){
        try{
            if(getUsuarioFiltro().getNombre().equalsIgnoreCase("")){
                setListaUsuarios(service.getUsuarioFindAll());
            }else{
                setListaUsuarios(service.getUsuarioFindByName(usuarioFiltro));
            }
            return null;
        }catch(Exception ex){
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Ha ocurrido un error al intentar realizar la búsqueda. Error " + ex.getMessage()));
            return null;
        }    
    }

    /**
     * Registra los datos de un nuevo usuario, y envía a encriptar la contraseña.
     * @return String Nombre de la página o valor null
     * @throws Exception Se genera si 
     */
    public String registrarUsuario(){
        try{
            usuarioRegistrar.setContrasenna(MD5(usuarioRegistrar.getContrasenna()));
            service.persistUsuario(this.usuarioRegistrar);
            service.commitTransaction();
            setMensajeRegistrar("Se ha registrado exitosamente");
            limpiarRegistro();
            setListaUsuarios(service.getUsuarioFindAll());
            return null;
        }catch(Exception ex){
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Ha ocurrido un error al intentar registrar un nuevo usuario. Error: " + ex.getMessage()));
            return null;
        }    
    }

    /**
     * Fija el identifidor del usuario seleccionado en la tabla de usuarios.
     * @param selectionEvent Evento que se genera al momento de seleccionar un usuario de la tabla
     */
    public void seleccionarUsuario(SelectionEvent selectionEvent) {
        idUsuarioSeleccionado = (Integer) selectionEvent.getAddedSet().iterator().next();
    }

    /**
     * Edita los datos de un usuario registrado.
     * @return String Nombre de la página o un valor null
     * @throws Exception Se genera si se produce un error al actualizar la base de datos
     */
    public String editarUsuario(){
        try{
            service.mergeUsuario(this.usuarioEditar);
            service.commitTransaction();
            setMensajeEditar("Se guardaron los cambios exitosamente");
            setMensajeContrasenna("");
            return null;
        }catch(Exception ex){
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Ha ocurrido un error al intentar editar el usuario. Error: " + ex.getMessage()));
            return null;
        }    
    }

    /**
     * Deshabilita un usuario registrado.
     * @return String Nombre de la página o valor null
     * @throws Exception Se genera si se produce un error al intentar actualizar la base de datos
     */
    public String eliminarUsuario(){
        try{
            usuarioEditar.setHabilitado(false);
            service.mergeUsuario(this.usuarioEditar);
            service.commitTransaction();
            setListaUsuarios(service.getUsuarioFindAll());
            return "listarusuarios";
        }catch(Exception ex){
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Ha ocurrido un error al intentar eliminar el usuario. Error: " + ex.getMessage()));
            return null;
        }    
    }

    /**
     * Cambia la contraseña actual por una nueva. Encripta la nueva contraseña y actualiza el usuario.
     * @return String Nombre de la página o valor null
     * @throws Exception Se genera si se produce un error al actualizar la base de datos
     */
    public String modificarContrasenna(){
        try{
            if(usuarioEditar.getContrasenna().equals(MD5(getContrasennaActualEditar()))){
                usuarioEditar.setContrasenna(MD5(getContrasennaNuevaEditar()));
                service.mergeUsuario(usuarioEditar);
                service.commitTransaction();
                setMensajeEditar("");
                setMensajeContrasenna("La contraseña ha sido cambiada");
            }else{
                this.setMensajeContrasenna("La contraseña que ingresó como actual no es correcta");
            }
            limpiarEditarContrasenna();
            return null;
        }catch(Exception ex){
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Ha ocurrido un error al intentar modificar la contraseña. Error: " + ex.getMessage()));
            return null;
        }    
    }

    /**
     * Fija mensajes a valores en blanco y limpia registros de formularios.
     * @return String Nombre de la página de editar al usuario
     */
    public String verUsuario(){
        this.setUsuarioEditar(listaUsuarios.get(this.idUsuarioSeleccionado));
        setMensajeEditar("");
        setMensajeContrasenna("");
        setMensajeRegistrar("");
        limpiarRegistro();
        limpiarEditarContrasenna();
        return "verusuarios";
    }

    /**
     * Crea una nueva instancia del usuario a registrar y asigna valor en blanco a retroalimentación
     */
    private void limpiarRegistro(){
        this.setUsuarioRegistrar(new Usuario());
        this.setConfirmarContrasennaRegistrar("");
    }

    /**
     * Fija valores en blanco a contraseñas ingresadas en formulario de cambio de contraseña
     */
    private void limpiarEditarContrasenna(){
        this.setContrasennaActualEditar("");
        this.setContrasennaNuevaEditar("");
        this.setConfirmarContrasennaEditar("");
    }

     /** 
      * Encripta una contraseña.
      * @param md5 Contraseña a encriptar
      * @return String Contraseña encriptada
      */
    private String MD5(String md5){
        try{
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
              sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        }catch(Exception ex){
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Se ha producido un error al intentar encriptar la contraseña. Error: " + ex.getMessage()));
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

    public void setUsuarioFiltro(Usuario usuarioFiltro) {
        this.usuarioFiltro = usuarioFiltro;
    }

    public Usuario getUsuarioFiltro() {
        return usuarioFiltro;
    }

    public void setUsuarioRegistrar(Usuario usuarioRegistrar) {
        this.usuarioRegistrar = usuarioRegistrar;
    }

    public Usuario getUsuarioRegistrar() {
        return usuarioRegistrar;
    }

    public void setUsuarioEditar(Usuario usuarioEditar) {
        this.usuarioEditar = usuarioEditar;
    }

    public Usuario getUsuarioEditar() {
        return usuarioEditar;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaRoles(List<Rol> listaRoles) {
        this.listaRoles = listaRoles;
    }

    public List<Rol> getListaRoles() {
        return service.getRolFindAll();
    }

    public void setContrasennaActualEditar(String contrasennaActualEditar) {
        this.contrasennaActualEditar = contrasennaActualEditar;
    }

    public String getContrasennaActualEditar() {
        return contrasennaActualEditar;
    }

    public void setContrasennaNuevaEditar(String contrasennaNuevaEditar) {
        this.contrasennaNuevaEditar = contrasennaNuevaEditar;
    }

    public String getContrasennaNuevaEditar() {
        return contrasennaNuevaEditar;
    }

    public void setMensajeContrasenna(String mensajeContrasenna) {
        this.mensajeContrasenna = mensajeContrasenna;
    }

    public String getMensajeContrasenna() {
        return mensajeContrasenna;
    }

    public void setConfirmarContrasennaRegistrar(String confirmarContrasennaRegistrar) {
        this.confirmarContrasennaRegistrar = confirmarContrasennaRegistrar;
    }

    public String getConfirmarContrasennaRegistrar() {
        return confirmarContrasennaRegistrar;
    }

    public void setConfirmarContrasennaEditar(String confirmarContrasennaEditar) {
        this.confirmarContrasennaEditar = confirmarContrasennaEditar;
    }

    public String getConfirmarContrasennaEditar() {
        return confirmarContrasennaEditar;
    }

    public void setMensajeEditar(String mensajeEditar) {
        this.mensajeEditar = mensajeEditar;
    }

    public String getMensajeEditar() {
        return mensajeEditar;
    }

    public void setMensajeRegistrar(String mensajeRegistrar) {
        this.mensajeRegistrar = mensajeRegistrar;
    }

    public String getMensajeRegistrar() {
        return mensajeRegistrar;
    }
}
