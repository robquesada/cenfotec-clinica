package view;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import model.JavaServiceFacade;
import model.Permiso;
import model.Rolpermiso;
import model.Usuario;

/**
 * Clase (Managed Bean) utilizada para el control de los datos
 * ligados al usuario que utiliza el sistema, tales como el inicio y cierre de una sesión,
 * así como el manejo de permisos.
 * @author Roberto Quesada
 * @version 1.0, 26/01/2014
 */
public class LoginController implements Serializable {
     
    /** Servicio que permite enlace con la base de datos */
    private JavaServiceFacade service;
    /** Atributo de clase. Usuario logueado. */
    private static Usuario usuarioLogged;
    /** Atributos del usuario logueado. */
    private String contrasennaLog;
    private String mensajeLog;
    private String nombreUsuarioLog;
    private String nombreCompletoLog;
    /** Lista de los permisos del usuario. */
    private List<Permiso> listaPermisosUsuarioLog;

    /**
     * Constructor de la clase.
     */
    public LoginController() {
        this.setService(new JavaServiceFacade());
        this.setUsuarioLogged(new Usuario());
        this.setNombreUsuarioLog("");
        this.setContrasennaLog("");
        this.setMensajeLog("");
        this.setListaPermisosUsuarioLog(new ArrayList());
    }

    /**
     * Método utilizado para ingresar al sistema como un usuario.
     * Verifica y valida nombre de usuario y contraseña del usuario.
     * @return String Nombre de la página o valor nulo si hay datos incorrectos
     * @throws Exception Si se presenta algún error al consultar la base de datos
     */
    public String ingresar(){
        try {
            // Contra admin = 123456
            List<Usuario> listUsr = service.getUsuarioFindByUsrName(this.getNombreUsuarioLog());
            if(listUsr.size()==0){
                this.setMensajeLog("El nombre de usuario o contraseña son incorrectos");
                return null;
            }else{
                Usuario usr = listUsr.get(0);
                if(usr.getContrasenna().equals(this.MD5(getContrasennaLog()))){
                    this.setMensajeLog("");
                    this.setNombreUsuarioLog("");
                    this.setContrasennaLog("");
                    this.setUsuarioLogged(usr);
                    this.setNombreCompletoLog(getUsuarioLogged().getNombre() + " " +
                                              getUsuarioLogged().getPrimerApellido() + " " +
                                              getUsuarioLogged().getSegundoApellido());
                    this.setListaPermisosUsuarioLog(cargarPermisos());
                    return "Menu.jsf";
                }else{
                    this.setMensajeLog("El nombre de usuario o contraseña son incorrectos");
                    this.setNombreUsuarioLog("");
                    this.setContrasennaLog("");
                    return null;
                }
            }
         }catch(Exception ex){
             FacesContext fc = FacesContext.getCurrentInstance();
             fc.addMessage(null, new FacesMessage("Ha ocurrido un error al intentar ingresar al sistema. Error: " + ex.getMessage()));
             return null;
         }
    }


    /**
     * Método utilizado para el ingreso de un paciente al sistema.
     * Valida y verifica que el número de identidad exista en el sistema para permitir el ingreso.
     * @return String Nombre de la página o valor nulo si hay datos incorrectos
     * @throws Exception Si se presenta algún error al consultar la base de datos
     */
    public String ingresarPaciente(){
        try {
            // Valorar
            List<Usuario> listUsr = service.getUsuarioFindByUsrName(this.getNombreUsuarioLog());
            if(listUsr.size()==0){
                this.setMensajeLog("El nombre de usuario o contraseña son incorrectos");
                return null;
            }else{
                Usuario usr = listUsr.get(0);
                if(usr.getContrasenna().equals(this.MD5(getContrasennaLog()))){
                    this.setMensajeLog("");
                    this.setNombreUsuarioLog("");
                    this.setContrasennaLog("");
                    this.setUsuarioLogged(usr);
                    this.setListaPermisosUsuarioLog(cargarPermisos());
                    return "Menu.jsf";
                }else{
                    this.setMensajeLog("El nombre de usuario o contraseña son incorrectos");
                    this.setNombreUsuarioLog("");
                    this.setContrasennaLog("");
                    return null;
                }
            }
         }catch(Exception ex){
             FacesContext fc = FacesContext.getCurrentInstance();
             fc.addMessage(null, new FacesMessage("Ha ocurrido un error al intentar ingresar como paciente. Error: " + ex.getMessage()));
             return null;
         }
    }

    /**
     * Cierra la sesión actual y envía al usuario
     * a la página de inicio.
     * @return String Nombre de la página de inicio
     */
    public String cerrarSesion(){
        this.setUsuarioLogged(new Usuario());
        return "Login.jsf";
    }

    /** 
     * Encripta una contraseña para verificar si es la correcta.
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
            fc.addMessage(null, new FacesMessage("Ha ocurrido un error con la contraseña ingresada. Error: " + ex.getMessage()));
            return null;
        }
    }

    /**
     * Carga una lista con todos los permisos del rol del usuario ingresado.
     * @return listaPermisos Lista con todos los permisos del rol del usuario
     */
    public List<Permiso> cargarPermisos(){
        List<Permiso> listaPermisos = new ArrayList();
        for(Rolpermiso rolpermiso : usuarioLogged.getRol1().getRolpermisoList()){
            listaPermisos.add(rolpermiso.getPermiso());
        }
        return listaPermisos;
    }

    // Métodos que verifican si el rol de un usuario posee tales permisos

    /**
     * Valida si el usuario tiene permiso de administrar datos de otros usuarios.
     * @return boolean Valor true si posee el permiso, de lo contrario false
     */
    public boolean isPermisoUsuarios(){
        for(Permiso permiso : this.getListaPermisosUsuarioLog()){
            if(permiso.getDescripcion().equals("AUsuarios")){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Valida si el usuario tiene permiso de administrar la clínica de la agenda.
     * @return boolean Valor true si posee el permiso, de lo contrario false
     */
    public boolean isPermisoAgendaClinica(){
        for(Permiso permiso : this.getListaPermisosUsuarioLog()){
            if(permiso.getDescripcion().equals("CAgenda")){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Valida si el usuario tiene permiso de administrar las cuentas de los pacientes.
     * @return boolean Valor true si posee el permiso, de lo contrario false
     */
    public boolean isPermisoCuentas(){
        for(Permiso permiso : this.getListaPermisosUsuarioLog()){
            if(permiso.getDescripcion().equals("PCuentas")){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Valida si el usuario tiene permiso de administrar los presupuestos y odontogramas de los pacientes.
     * @return boolean Valor true si posee el permiso, de lo contrario false
     */
    public boolean isPermisoOdontogramas(){
        for(Permiso permiso : this.getListaPermisosUsuarioLog()){
            if(permiso.getDescripcion().equals("POdontogramas")){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Valida si el usuario tiene permiso de administrar su agenda personal.
     * @return boolean Valor true si posee el permiso, de lo contrario false
     */
    public boolean isPermisoAgendaPersonal(){
        for(Permiso permiso : this.getListaPermisosUsuarioLog()){
            if(permiso.getDescripcion().equals("AAgenda")){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Valida si el usuario tiene permiso de administrar los roles y permisos de otros usuarios.
     * @return boolean Valor true si posee el permiso, de lo contrario false
     */
    public boolean isPermisoPermisos(){
        for(Permiso permiso : this.getListaPermisosUsuarioLog()){
            if(permiso.getDescripcion().equals("CPermisos")){
                return true;
            }
        }
        return false;
    }

    /**
     * Valida si el usuario tiene permiso de administrar los procedimientos del sistema.
     * @return boolean Valor true si posee el permiso, de lo contrario false
     */
    public boolean isPermisoProcedimientos(){
        for(Permiso permiso : this.getListaPermisosUsuarioLog()){
            if(permiso.getDescripcion().equals("CProcedimientos")){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Valida si el usuario tiene permiso de administrar los reportes del sistema.
     * @return boolean Valor true si posee el permiso, de lo contrario false
     */
    public boolean isPermisoReportes(){
        for(Permiso permiso : this.getListaPermisosUsuarioLog()){
            if(permiso.getDescripcion().equals("CReportes")){
                return true;
            }
        }
        return false;
    }

    /**
     * Valida si el usuario tiene permiso de administrar los datos de configuración del sistema.
     * @return boolean Valor true si posee el permiso, de lo contrario false
     */
    public boolean isPermisoConfiguracionMenu(){
        if(!isPermisoUsuarios() && !isPermisoProcedimientos() && !isPermisoPermisos()){
            return false;
        }
        return true;
    }

    /**
     * Valida si el usuario tiene permiso de administrar las agendas del sistema.
     * @return boolean Valor true si posee el permiso, de lo contrario false
     */
    public boolean isPermisoAgendaMenu(){
        if(!isPermisoAgendaPersonal() && !isPermisoAgendaClinica()){
            return false;
        }
        return true;
    }

    /**
     * Valida si el usuario tiene permiso de administrar datos de los pacientes.
     * @return boolean Valor true si posee el permiso, de lo contrario false
     */
    public boolean isPacienteMenu(){
        if(!isPermisoOdontogramas() && !isPermisoCuentas()){
            return false;
        }
        return true;
    }

    // Métodos setter y getter
    
    public static void setUsuarioLogged(Usuario pusuarioLogged) {
        usuarioLogged = pusuarioLogged;
    }

    public static Usuario getUsuarioLogged() {
        return usuarioLogged;
    }

    public void setNombreUsuarioLog(String nombreUsuarioLog) {
        this.nombreUsuarioLog = nombreUsuarioLog;
    }

    public String getNombreUsuarioLog() {
        return nombreUsuarioLog;
    }

    public void setContrasennaLog(String contrasennaLog) {
        this.contrasennaLog = contrasennaLog;
    }

    public String getContrasennaLog() {
        return contrasennaLog;
    }

    public void setMensajeLog(String mensajeLog) {
        this.mensajeLog = mensajeLog;
    }

    public String getMensajeLog() {
        return mensajeLog;
    }

    public void setService(JavaServiceFacade service) {
        this.service = service;
    }

    public JavaServiceFacade getService() {
        return service;
    }

    public void setListaPermisosUsuarioLog(List<Permiso> listaPermisosUsuarioLog) {
        this.listaPermisosUsuarioLog = listaPermisosUsuarioLog;
    }

    public List<Permiso> getListaPermisosUsuarioLog() {
        return listaPermisosUsuarioLog;
    }

    public void setNombreCompletoLog(String nombreCompletoLog) {
        this.nombreCompletoLog = nombreCompletoLog;
    }

    public String getNombreCompletoLog() {
        return nombreCompletoLog;
    }
}
