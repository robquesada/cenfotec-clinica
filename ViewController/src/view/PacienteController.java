package view;

import java.io.Serializable;

import java.sql.Timestamp;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import model.Bitacoraprocedimiento;
import model.Cuentapaciente;
import model.Cuestionariosalud;
import model.Encargadofamilia;
import model.JavaServiceFacade;
import model.Lineadetalle;
import model.Movimiento;
import model.Paciente;
import model.Presupuesto;
import model.Procedimiento;
import model.Telefono;
import model.Telefonopaciente;
import model.Usuario;

import org.apache.myfaces.trinidad.event.SelectionEvent;

 /**
  * Clase (Managed Bean) utilizada para la administración de los pacientes del sistema.
  * Maneja datos de pacientes, sus cuentas, bitácora de procedimientos, presupuestos y odontogramas.
  * @author Roberto Quesada
  * @version 1.0, 26/01/2014
  */
public class PacienteController implements Serializable {

    /** Servicio que permite enlace con la base de datos */
    private JavaServiceFacade service;
    
    // Pacientes
    /** Paciente que sirve como filtro para búsquedas */
    private Paciente pacienteFiltro;
    /** Paciente a registrar */
    private Paciente pacienteRegistrar;
    /** Paciente a editar */
    private Paciente pacienteEditar;
    /** Lista de los pacientes del sistema */
    private List<Paciente> listaPacientes;
    /** Identificacdor del paciente seleccionado en la tabla de pacientes */
    private int idPacienteSeleccionado;
    /** Lista de teléfonos del paciente a registrar */
    private List<Telefono> listaTelefonosPaciente;
    /** Teléfono del paciente a registrar */
    private String telefonoPacienteRegistrar;
    /** Identificador del teléfono seleccionado en la lista de teléfonos del paciente a registrar */
    private int idTelefonoSeleccionado;
    /** Lista de teléfonos del paciente a editar */
    private List<Telefono> listaTelefonosEditarPaciente;
    /** Teléfono del paciente a editar */
    private String telefonoPacienteEditar;
    /** Identificador del teléfono seleccionado en la lista de teléfonos del paciente a editar */
    private int idTelefonoSeleccionadoEditar;
    // Datos de paciente a registrar
    /** Lista de los encargados del paciente a registrar */
    private List<Encargadofamilia> listaEncargadosFamiliaRegistrar;
    /** Identificador del encargado seleccionado del paciente a registrar */
    private int idEncargadoSeleccionadoRegistrar;
    /** Nombre completo del encargado a registrar */
    private String nombreEncargadoRegistrar;
    /** Teléfono del encargado a registrar */
    private String numeroTelefonoEncargadoRegistrar;
    /** Nombre de Relación o parentezco entre el paciente a registrar y su encargado */
    private String parentezcoEncargadoRegistrar;
    // Datos de paciente a editar
    /** Lista de los encargados del paciente a editar*/
    private List<Encargadofamilia> listaEncargadosFamiliaEditar;
     /** Identificador del encargado seleccionado del paciente a editar */
    private int idEncargadoSeleccionadoEditar;
    /** Nombre del encargado a editar */
    private String nombreEncargadoEditar;
    /** Teléfono del encargado a editar */
    private String numeroTelefonoEncargadoEditar;
    /** Nombre de Relación o parentezco entre el paciente a editar y su encargado */
    private String parentezcoEncargadoEditar;
    /** Mensaje de retroalimentación al registrar un paciente */
    private String mensajePacienteRegistrar;
    /** Mensaje de retroalimentación al editar un paciente */
    private String mensajePacienteEditar;
    
    // Bitacora de procedimientos
    /** Lista de las líneas de detalle de la bitácora de procedimientos */
    private List<Bitacoraprocedimiento> listaBitacoraProcedimientos;
    /** Lista de los procedimientos del sistema */
    private List<Procedimiento> listaProcedimientos;
    /** Lista de los usuarios del sistema */
    private List<Usuario> listaUsuarios;
    /** Línea detalle a registrar de la bitácora de procedimientos */
    private Bitacoraprocedimiento bitacoraProcedimientoRegistrar;
    /** Línea detalle a editar de la bitácora de procedimientos */
    private Bitacoraprocedimiento bitacoraProcedimientoEditar;
    /** Primer dígito del número de diente a registrar */
    private int numeroDiente1;
    /** Segundo dígito del número de diente a registrar */
    private int numeroDiente2;
    /** Primer dígito del número de diente a editar */
    private int numeroDienteEditar1;
    /** Segundo dígito del número de diente a editar */
    private int numeroDienteEditar2;
    /** Valor que indica si se seleccionó un diente en específico para un registro en la bitácora */
    private boolean dienteSeleccionadoRegistrar;
    /** Valor que indica si se seleccionó un diente en específico para una edición en la bitácora */
    private boolean dienteSeleccionadoEditar;
    /** Mensaje de retroalimentación al registrar procedimiento en la bitácora */
    private String mensajeBitacoraRegistrar;
    /** Mensaje de retroalimentación al editar procedimiento en la bitácora */
    private String mensajeBitacoraEditar;
    
    // Cuenta financiera y movimientos
    /** Monto total del saldo de la cuenta del paciente */
    private double totalCuentaUsuario;
    /** Movimiento a registrar */
    private Movimiento movimientoRegistrar;
    /** Movimiento a editar */
    private Movimiento movimientoEditar;
    /** Lista de movimientos del sistema */
    private List<Movimiento> listaMovimientos;

    // Prespuestos y odontogramas
    /** Presupuesto a registrar */
    private Presupuesto presupuestoRegistrar;
    /** Presupuesto a editar */
    private Presupuesto presupuestoEditar;
    /** Lista de presupuestos del paciente */
    private List<Presupuesto> listaPresupuestos;
    /** Primer dígito del número del diente de una línea detalle a registrar en el presupuesto */
    private int odontogramaNumeroDiente1;
    /** Segundo dígito del número del diente de una línea detalle a registrar en el presupuesto */
    private int odontogramaNumeroDiente2;
    /** Primer dígito del número del diente de una línea detalle a editar en el presupuesto */
    private int odontogramaNumeroDienteEditar1;
    /** Segundo dígito del número del diente de una línea detalle a editar en el presupuesto */
    private int odontogramaNumeroDienteEditar2;
    /** Imagen del diente */
    private String imagenDiente;
    /** Cadena de texto con la fecha actual */
    private String fechaActual;
    /** Lista de líneas detalle del presupuesto mostrado */
    private List<Lineadetalle> listaLineasDetallePresupuesto;
    /** Línea de detalle a registrar */
    private Lineadetalle lineaDetalleRegistrar;
    /** Línea de detalle a editar */
    private Lineadetalle lineaDetalleEditar;
    /** Valor que indica si se seleccionó un diente en específico para un registro en el presupuesto */
    private boolean dienteSeleccionadoPresupuestoRegistrar;
    /** Valor que indica si se seleccionó un diente en específico para una edición en el presupuesto */
    private boolean dienteSeleccionadoPresupuestoEditar;
    /** Mensaje de retroalimentación al registrar un presupuesto */
    private String mensajePresupuesto;
    /** Identificador de la línea detalle seleccionada en el presupuesto */
    private int indiceLineaDetalleSeleccionada;
    /** Indica la cara del diente de un registro seleccionado en el presupuesto */
    private String caraDientePresupuestoSeleccionada;
    /** Presupuesto que sirve como filtro en las búsquedas */
    private Presupuesto presupuestoFiltro;
    /** Valor que indica si un presupuesto mostrado está aplicado o no */
    private boolean aplicado;
    /** Lista de nombres de imágenes de dientes del sector superior derecho del odontograma */
    private List<String> listaSuperiorDerecha;
    /** Lista de nombres de imágenes de dientes del sector superior izquierdo del odontograma */
    private List<String> listaSuperiorIzquierda;
     /** Lista de nombres de imágenes de dientes del sector inferior derecho del odontograma */
    private List<String> listaInferiorDerecha;
     /** Lista de nombres de imágenes de dientes del sector inferior izquierdo del odontograma */
    private List<String> listaInferiorIzquierda;

    /**
     * Constructor de la clase.
     */
    public PacienteController(){
        setService(new JavaServiceFacade());
        //Pacientes
        setPacienteFiltro(new Paciente());
        setPacienteRegistrar(new Paciente());
        setPacienteEditar(new Paciente());
        setListaPacientes(service.getPacienteFindAll());
        setListaTelefonosPaciente(new ArrayList());
        setListaTelefonosEditarPaciente(new ArrayList());
        setListaEncargadosFamiliaRegistrar(new ArrayList());
        setListaEncargadosFamiliaEditar(new ArrayList());
        //Movimientos
        setMovimientoRegistrar(new Movimiento());
        setMovimientoEditar(new Movimiento());
        setListaMovimientos(new ArrayList());
        //Bitácora
        setListaUsuarios(service.getUsuarioFindAll());
        setListaProcedimientos(service.getProcedimientoFindAll());
        setBitacoraProcedimientoRegistrar(new Bitacoraprocedimiento());
        setBitacoraProcedimientoEditar(new Bitacoraprocedimiento());
        setListaBitacoraProcedimientos(new ArrayList());
        //Odontograma
        setImagenDiente("");
        setListaSuperiorDerecha(crearListaSuperiorDerecha());
        setListaSuperiorIzquierda(crearListaSuperiorIzquierda());
        setListaInferiorDerecha(crearListaInferiorDerecha());
        setListaInferiorIzquierda(crearListaInferiorIzquierda());
        setFechaActual(new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime()));
        setListaLineasDetallePresupuesto(new ArrayList());
        setPresupuestoRegistrar(new Presupuesto());
        setPresupuestoEditar(new Presupuesto());
        setLineaDetalleRegistrar(new Lineadetalle());
        setLineaDetalleEditar(new Lineadetalle());
        setAplicado(false);
    }

    /**
     * Crea una lista de cadenas de texto, con el nombre de las imágenes correspondientes
     * al sector superior derecho del odontograma.
     * @return listaSuperiorDerecha Lista con nombres de imágenes del sector superior derecho del odontograma
     */
    public List<String> crearListaSuperiorDerecha(){
        listaSuperiorDerecha = new ArrayList();
        for(int i=0;i<8;i++){
            listaSuperiorDerecha.add("");    
        } 
        return listaSuperiorDerecha;
    } 
    
    /**
     * Crea una lista de cadenas de texto, con el nombre de las imágenes correspondientes
     * al sector superior izquierdo del odontograma.
     * @return listaSuperiorIzquierda Lista con nombres de imágenes del sector superior izquierdo del odontograma
     */
    public List<String> crearListaSuperiorIzquierda(){
        listaSuperiorIzquierda = new ArrayList();
        for(int i=0;i<8;i++){
            listaSuperiorIzquierda.add("");    
        } 
        return listaSuperiorIzquierda;
    }
    
    /**
     * Crea una lista de cadenas de texto, con el nombre de las imágenes correspondientes
     * al sector inferior derecho del odontograma.
     * @return listaInferiorDerecha Lista con nombres de imágenes del sector inferior derecho del odontograma
     */
    public List<String> crearListaInferiorDerecha(){
        listaInferiorDerecha = new ArrayList();
        for(int i=0;i<8;i++){
            listaInferiorDerecha.add("");    
        } 
        return listaInferiorDerecha;
    }
    
    /**
     * Crea una lista de cadenas de texto, con el nombre de las imágenes correspondientes
     * al sector inferior izquierdo del odontograma.
     * @return listaInferiorIzquierda Lista con nombres de imágenes del sector inferior izquierdo del odontograma
     */
    public List<String> crearListaInferiorIzquierda(){
        listaInferiorIzquierda = new ArrayList();
        for(int i=0;i<8;i++){
            listaInferiorIzquierda.add("");    
        }
        return listaInferiorIzquierda;
    }

    /**
     * Registra los datos de un nuevo paciente, incluyendo teléfonos y encargados del paciente.
     * @return String Nombre de la página o valor null
     * @throws Exception Se genera si se produce un error al intentar registrar el paciente
     */
    public String registrarPaciente(){
        try{
            if(listaTelefonosPaciente.size()>0){
                Cuestionariosalud cuestionarioSalud = new Cuestionariosalud();
                service.persistCuestionariosalud(cuestionarioSalud);
                service.commitTransaction();
                
                Cuentapaciente cuentaPaciente = new Cuentapaciente();
                service.persistCuentapaciente(cuentaPaciente);
                service.commitTransaction();
                
                Timestamp timestamp = new Timestamp(Calendar.getInstance().getTime().getTime());  
                pacienteRegistrar.setFechaIngreso(timestamp);
                    
                pacienteRegistrar.setCuestionariosalud(service.getCuestionariosaludFindAll().get(service.getCuestionariosaludFindAll().size()-1));
                pacienteRegistrar.setCuentapaciente(service.getCuentapacienteFindAll().get(service.getCuentapacienteFindAll().size()-1));
                service.persistPaciente(this.pacienteRegistrar);
                service.commitTransaction();
                
                for(int i=0; i<getListaEncargadosFamiliaRegistrar().size(); i++){
                    Encargadofamilia encargadoFamilia = getListaEncargadosFamiliaRegistrar().get(i);
                    encargadoFamilia.setCuentapaciente1(service.getPacienteFindAll().get(service.getPacienteFindAll().size()-1));
                    service.persistEncargadofamilia(encargadoFamilia);
                    service.commitTransaction();
                }
                
                for(int i=0; i<getListaTelefonosPaciente().size(); i++){
                    service.persistTelefono(this.getListaTelefonosPaciente().get(i));
                    service.commitTransaction();
                }
                
                for(int i=0; i<getListaTelefonosPaciente().size(); i++){
                    Telefonopaciente telefonopacienteRegistrar = new Telefonopaciente();
                    telefonopacienteRegistrar.setCuentapaciente2(service.getPacienteFindAll().get(service.getPacienteFindAll().size()-1));
                    telefonopacienteRegistrar.setTelefono(service.getTelefonoFindAll().get(service.getTelefonoFindAll().size()-(i+1)));
                    service.persistTelefonopaciente(telefonopacienteRegistrar);
                    service.commitTransaction();
                }
                
                setMensajePacienteRegistrar("El paciente se registró exitosamente");
                limpiarRegistroPaciente();
                return null;
            }else{
                setMensajePacienteRegistrar("Debe ingresar al menos un número de teléfono");
                return null;
            }
        }catch(Exception ex){
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Ha ocurrido un error al intentar registrar el paciente. Error: " + ex.getMessage()));
            return null;
        }
    }

    /**
     * Crea y carga listas de teléfonos y encargados del paciente.
     * @return String Nombre de la página del paciente
     */
    public String verPaciente(){
        this.setPacienteEditar(listaPacientes.get(idPacienteSeleccionado));
        
        setListaTelefonosEditarPaciente(new ArrayList());
        setListaEncargadosFamiliaEditar(new ArrayList());
         
        for(int i=0; i<pacienteEditar.getTelefonopacienteList().size(); i++){
            listaTelefonosEditarPaciente.add(pacienteEditar.getTelefonopacienteList().get(i).getTelefono());
        }
        
        for(int i=0; i<pacienteEditar.getEncargadofamiliaList().size(); i++){
            listaEncargadosFamiliaEditar.add(pacienteEditar.getEncargadofamiliaList().get(i));
        }
        
        setMensajePacienteEditar("");
        return "ver";
    }

    /**
     * Crea nuevas instancias del paciente a registrar y las listas de teléfonos y encargados del paciente.
     */
    public void limpiarRegistroPaciente(){
        this.setPacienteRegistrar(new Paciente());
        this.setListaTelefonosPaciente(new ArrayList());
        this.setListaEncargadosFamiliaRegistrar(new ArrayList());
        this.setListaPacientes(service.getPacienteFindAll());
    }

    /**
     * Edita los datos de un paciente, sus teléfonos y encargados.
     * @return String Nombre de la página o valor null
     * @throws Exception Se genera si se produce un error al intentar actualizar la base de datos
     */
    public String editarPaciente(){
        try{
            if(listaTelefonosEditarPaciente.size()>0){
                // Guardar teléfonos y encargados
                // Ingresa los encargados del paciente
                
                /*for(int i=0; i<getListaEncargadosFamiliaEditar().size(); i++){
                    Encargadofamilia encargadoFamilia = getListaEncargadosFamiliaEditar().get(i);
                    encargadoFamilia.setCuentapaciente1(pacienteEditar);
                    service.persistEncargadofamilia(encargadoFamilia);
                    service.commitTransaction();
                }
                
                for(int i=0; i<getListaTelefonosEditarPaciente().size(); i++){
                    service.persistTelefono(this.getListaTelefonosPaciente().get(i));
                    service.commitTransaction();
                }
                for(int i=0; i<getListaTelefonosEditarPaciente().size(); i++){
                    Telefonopaciente telefonopacienteRegistrar = new Telefonopaciente();
                    telefonopacienteRegistrar.setCuentapaciente2(pacienteEditar);
                    telefonopacienteRegistrar.setTelefono(service.getTelefonoFindAll().get(service.getTelefonoFindAll().size()-(i+1)));
                    service.persistTelefonopaciente(telefonopacienteRegistrar);
                    service.commitTransaction();
                }*/
                
                service.mergePaciente(this.pacienteEditar);
                service.commitTransaction();
                setMensajePacienteEditar("Se han guardado los cambios");   
            }else{
                setMensajePacienteEditar("Debe agregar al menos un número de teléfono");
            }
            return null; 
        }catch(Exception ex){
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Ha ocurrido un error al intentar editar los datos del paciente. Error: " + ex.getMessage()));
            return null;
        }    
    }

    /**
     * Busca pacientes que tengan un nombre o apellido aproximado al ingresado en la búsqueda.
     * @return String Nombre de la página o valor null
     * @throws Exception Se genera si se produce un error al consultar la base de datos
     */
    public String buscarPacientes(){
        try{
            if(getPacienteFiltro().getNombrePaciente().equalsIgnoreCase("")){
                setListaPacientes(service.getPacienteFindAll());
            }else{
                setListaPacientes(service.getPacienteFindByName(pacienteFiltro));
            }
            return null;
        }catch(Exception ex){
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Ha ocurrido un error al realizar la búsqueda. Error " + ex.getMessage()));
            return null;
        }    
    }

    /**
     * Agrega un teléfono a la lista de teléfonos del paciente a registrar.
     * @return String Nombre de la página o valor null
     * @throws Exception Se genera si se produce un error al agregar un teléfono al paciente
     */
    public String agregarTelefonoPaciente(){
        try{
            Telefono telefonoRegistrar = new Telefono();
            telefonoRegistrar.setNumeroTelefono(telefonoPacienteRegistrar);
            this.getListaTelefonosPaciente().add(telefonoRegistrar);
            this.setTelefonoPacienteRegistrar("");
            return null;
        }catch(Exception ex){
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Ha ocurrido un error al intentar agregar el teléfono. Error: " + ex.getMessage()));
            return null;
        }    
    }

    /**
     * Quita un teléfono de la lista de teléfonos del paciente a registrar.
     * @return String Nombre de la página o valor null
     * @throws Exception Se genera si se produce un error al quitar el teléfono
     */
    public String quitarTelefonoPaciente(){
        try{
            this.getListaTelefonosPaciente().remove(getListaTelefonosPaciente().get(idTelefonoSeleccionado));
            return null;
        }catch(Exception ex){
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Ha ocurrido un error al intentar borrar el teléfono. Error: " + ex.getMessage()));
            return null;
        }    
    }

    /**
     * Fija el identificador del teléfono seleccionado de la tabla de teléfonos del paciente a registrar.
     * @param selectionEvent Evento que se genera al seleccionar un teléfono de la tabla de teléfonos
     */
    public void seleccionarTelefonoRegistrar(SelectionEvent selectionEvent) {
        idTelefonoSeleccionado = (Integer) selectionEvent.getAddedSet().iterator().next();
    }
    
    /**
     * Agrega un teléfono a la lista de teléfonos del paciente a editar.
     * @return String Nombre de la página o valor null
     * @throws Exception Se genera si se produce un error al agregar un teléfono al paciente
     */
    public String agregarTelefonoPacienteEditar(){
        try{
            Telefono telefonoRegistrarEditar = new Telefono();
            telefonoRegistrarEditar.setNumeroTelefono(telefonoPacienteEditar);
            this.getListaTelefonosEditarPaciente().add(telefonoRegistrarEditar);
            this.setTelefonoPacienteEditar("");
            return null;
        }catch(Exception ex){
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Ha ocurrido un error al intentar agregar un nuevo teléfono. Error: " + ex.getMessage()));
            return null;
        }    
    }

    /**
     * Quita un teléfono de la lista de teléfonos del paciente a editar.
     * @return String Nombre de la página o valor null
     * @throws Exception Se genera si se produce un error al intentar borrar un teléfono
     */
    public String quitarTelefonoPacienteEditar(){
        try{
            this.getListaTelefonosEditarPaciente().remove(getListaTelefonosEditarPaciente().get(idTelefonoSeleccionadoEditar));
            return null;
        }catch(Exception ex){
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Ha ocurrido un error al intentar borrar el teléfono. Error: " + ex.getMessage()));
            return null;
        }    
    }
    
    /**
     * Fija el identificador del teléfono seleccionado de la tabla de teléfonos del paciente a editar.
     * @param selectionEvent Evento que se genera al seleccionar un teléfono de la tabla de teléfonos
     */
    public void seleccionarTelefonoEditar(SelectionEvent selectionEvent) {
        idTelefonoSeleccionadoEditar = (Integer) selectionEvent.getAddedSet().iterator().next();
    }

    /**
     * Agrega un encargado a la lista de encargados del paciente a registrar.
     * @return String Nombre de la página o valor null
     * @throws Exception Se genera si se produce un error al intentar agregar un encargado
     */
    public String agregarEncargadoFamiliaRegistrar(){
        try{
            Encargadofamilia encargadoRegistrar = new Encargadofamilia();
            encargadoRegistrar.setNombreFamiliar(this.nombreEncargadoRegistrar);
            encargadoRegistrar.setParentezco(parentezcoEncargadoRegistrar);
            encargadoRegistrar.setTelefonoFamiliar(numeroTelefonoEncargadoRegistrar);
            
            this.getListaEncargadosFamiliaRegistrar().add(encargadoRegistrar);
            this.setNombreEncargadoRegistrar("");
            this.setParentezcoEncargadoRegistrar("");
            this.setNumeroTelefonoEncargadoRegistrar("");
            return null;
        }catch(Exception ex){
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Ha ocurrido un error al intentar agregar el encargado del paciente. Error: " + ex.getMessage()));
            return null;
        }    
    }

     /**
      * Agrega un encargado a la lista de encargados del paciente a editar.
      * @return String Nombre de la página o valor null
      * @throws Exception Se genera si se produce un error al intentar agregar un encargado
      */
    public String agregarEncargadoFamiliaEditar(){
        try{
            Encargadofamilia encargadoRegistrar = new Encargadofamilia();
            encargadoRegistrar.setNombreFamiliar(nombreEncargadoEditar);
            encargadoRegistrar.setParentezco(parentezcoEncargadoEditar);
            encargadoRegistrar.setTelefonoFamiliar(numeroTelefonoEncargadoEditar);
            
            this.getListaEncargadosFamiliaEditar().add(encargadoRegistrar);
            setListaEncargadosFamiliaEditar(getListaEncargadosFamiliaEditar());
            this.setNombreEncargadoEditar("");
            this.setParentezcoEncargadoEditar("");
            this.setNumeroTelefonoEncargadoEditar("");
            return null;
        }catch(Exception ex){
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Ha ocurrido un error al intentar agregar un nuevo encargado. Error: " + ex.getMessage()));
            return null;
        }    
    }

    /**
     * Quita un encargado de la lista de encargados del paciente a editar.
     * @return String Nombre de la página o valor null
     * @throws Exception Se genera si se produce un error al intentar quitar un encargado
     */
    public String quitarEncargadoFamiliaEditar(){
        try{
            this.getListaEncargadosFamiliaEditar().remove(getListaEncargadosFamiliaEditar().get(idEncargadoSeleccionadoEditar));
            return null;
        }catch(Exception ex){
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Ha ocurrido un error al intentar quitar el encargado del paciente. Error: " + ex.getMessage()));
            return null;
        }    
    }
    
    /**
     * Quita un encargado de la lista de encargados del paciente a registrar.
     * @return String Nombre de la página o valor null
     * @throws Exception Se genera si se produce un error al intentar quitar un encargado
     */
    public String quitarEncargadoFamiliaRegistrar(){
        try{
            this.getListaEncargadosFamiliaRegistrar().remove(getListaEncargadosFamiliaRegistrar().get(idEncargadoSeleccionadoRegistrar));
            return null;
        }catch(Exception ex){
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Ha ocurrido un error al intentar quitar el encargado del paciente. Error: " + ex.getMessage()));
            return null;
        }    
    }

    /**
     * Fija el identificador del encargado seleccionado de la lista de encargados de un paciente a registrar.
     * @param selectionEvent Evento que se genera cuando se selecciona un encargado del paciente a registrar
     */
    public void seleccionarEncargadoFamiliaRegistrar(SelectionEvent selectionEvent) {
        idEncargadoSeleccionadoRegistrar = (Integer) selectionEvent.getAddedSet().iterator().next();
    }
    
    /**
     * Fija el identificador del encargado seleccionado de la lista de encargados de un paciente a editar.
     * @param selectionEvent Evento que se genera cuando se selecciona un encargado del paciente a editar
     */
    public void seleccionarEncargadoFamiliaEditar(SelectionEvent selectionEvent) {
        idEncargadoSeleccionadoEditar = (Integer) selectionEvent.getAddedSet().iterator().next();
    }

    /**
     * Deshabilita un paciente registrado.
     * @return String Nombre de la página o valor null
     */
    public String deshabilitarPaciente(){
        try{
            pacienteEditar.setHabilitado(false);
            service.mergePaciente(this.pacienteEditar);
            service.commitTransaction();
            setListaPacientes(service.getPacienteFindAll());
            return "listar";
        }catch(Exception ex){
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Ha ocurrido un error al intentar eliminar el paciente. Error: " + ex.getMessage()));
            return null;
        }    
    }
    
    // Movimientos de la cuenta financiera

    /**
     * Registra un nuevo movimiento en la cuenta financiera del paciente.
     * @return String Nombre de la página o valor null
     * @throws Exception Se genera si ocurre un error al registrar en la base de datos
     */
    public String registrarMovimientoPaciente(){
        try{
            movimientoRegistrar.setHabilitado(true);
            Cuentapaciente cp = pacienteEditar.getCuentapaciente();
            movimientoRegistrar.setCuentapaciente1(cp);
            cp.getMovimientoList().add(movimientoRegistrar);
            
            service.persistMovimiento(this.movimientoRegistrar);
            service.commitTransaction();
            
            service.mergeCuentapaciente(cp);
            service.commitTransaction();
            
            service.mergePaciente(pacienteEditar);
            service.commitTransaction();
            
            calcularSaldoCuenta();
            limpiarRegistroMovimiento();
            return null;
        }catch(Exception ex){
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Ha ocurrido un error al intentar registrar el movimiento. Error: " + ex.getMessage()));
            return null;
        }    
    }

    /**
     * Edita la información de un movimiento registrado en la cuenta financiera del paciente.
     * @return String Nombre de la página o valor null
     * @throws Exception Se genera si se produce un error al actualizar la base de datos
     */
    public String editarMovimientoPaciente(){
        try{
            service.mergeMovimiento(this.movimientoEditar);
            service.commitTransaction();
            calcularSaldoCuenta();
            return null;
        }catch(Exception ex){
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Ha ocurrido un error al intentar editar el movimiento. Error. " + ex.getMessage()));
            return null;
        }    
    }

    /**
     * Deshabilita un movimiento de la cuenta financiera del paciente.
     * @return String Nombre de la página o valor null
     * @throws Exception Se genera si se produce un error al intentar actualizar la base de datos
     */
    public String eliminarMovimientoPaciente(){
        try{
            movimientoEditar.setHabilitado(false);
            service.mergeMovimiento(this.movimientoEditar);
            service.commitTransaction();
            calcularSaldoCuenta();
            return null;
        }catch(Exception ex){
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Ha ocurrido un error al intentar eliminar el movimiento. Error: " + ex.getMessage()));
            return null;
        }    
    }


    /**
     * Fija el movimiento seleccionado en la tabla de movimientos como un movimiento a editar.
     * @param selectionEvent Evento que se genera cuando se selecciona un movimiento de la cuenta financiera
     */
    public void seleccionarMovimiento(SelectionEvent selectionEvent) {
        int row = (Integer) selectionEvent.getAddedSet().iterator().next();
        this.setMovimientoEditar(this.getListaMovimientos().get(row));
    }

    /**
     * Crea una nueva instancia del movimiento a registrar.
     */
    public void limpiarRegistroMovimiento(){
        this.setMovimientoRegistrar(new Movimiento());
    }

    /**
     * Calcula el saldo y actualiza la cuenta financiera del paciente.
     * @throws Exception Se genera si se produce un error al actualizar la base de datos
     */
    public void calcularSaldoCuenta(){
        try{
            int monto = 0;
            for(Movimiento movimiento : getListaMovimientos()){
                if(movimiento.getNombreMovimiento().equalsIgnoreCase("Cobro")){
                    monto -= movimiento.getMonto();
                }else{
                    monto += movimiento.getMonto();
                }
            }
            this.pacienteEditar.getCuentapaciente().setSaldo(monto);
            service.mergeCuentapaciente(this.pacienteEditar.getCuentapaciente());
            service.commitTransaction();
        }catch(Exception ex){
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Ha ocurrido un error al calcular el saldo de la cuenta financiera. Error: " + ex.getMessage()));
        } 
    }
    
    // Bitácora de procedimientos

    /**
     * Registra un nuevo procedimiento en la bitácora de procedimientos del paciente.
     * @return String Nombre de la página o valor null
     * @throws Exception Se genera si se produce un error al insertar en la base de datos
     */
    public String registrarBitacoraProcedimiento(){
        try{
            if(this.isDienteSeleccionadoRegistrar()){
                bitacoraProcedimientoRegistrar.setNumeroDiente((getNumeroDiente1()+"")+(getNumeroDiente2()+""));
            }else{
                bitacoraProcedimientoRegistrar.setCaraDiente("Todas");
                bitacoraProcedimientoRegistrar.setNumeroDiente("Todos");
            }
            
            this.bitacoraProcedimientoRegistrar.setCuentapaciente(getPacienteEditar());
            this.bitacoraProcedimientoRegistrar.setHabilitado(true);
            service.persistBitacoraprocedimiento(this.bitacoraProcedimientoRegistrar);
            service.commitTransaction();
            
            service.mergePaciente(pacienteEditar);
            service.commitTransaction();
            
            limpiarBitacoraProcedimientoRegistrar();
            this.setMensajeBitacoraRegistrar("Se ha registrado exitosamente");
            return null;
        }catch(Exception ex){
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Ha ocurrido un error al intentar registrar el procedimiento. Error: " + ex.getMessage()));
            return null;
        }    
    }

    /**
     * Crea una nueva instancia del procedimiento a registrar y fija datos numéricos a cero.
     */
    public void limpiarBitacoraProcedimientoRegistrar(){
        this.setBitacoraProcedimientoRegistrar(new Bitacoraprocedimiento());
        this.setNumeroDiente1(0);
        this.setNumeroDiente2(0);
        this.setDienteSeleccionadoRegistrar(true);
    }

    /**
     * Fija los datos del procedimiento a registrar según se seleccione en la bitácora de procedimientos.
     * @param selectionEvent Evento que se genera cuando se selecciona un procedimiento en la bitácora
     */
    public void seleccionarBitacoraProcedimiento(SelectionEvent selectionEvent) {
        int row = (Integer) selectionEvent.getAddedSet().iterator().next();
        this.setBitacoraProcedimientoEditar(this.getListaBitacoraProcedimientos().get(row));
        System.out.println("innnndice " +row);
        if(bitacoraProcedimientoEditar.getNumeroDiente().length()==2){
            setNumeroDienteEditar1(Integer.parseInt(bitacoraProcedimientoEditar.getNumeroDiente().charAt(0)+""));
            setNumeroDienteEditar2(Integer.parseInt(bitacoraProcedimientoEditar.getNumeroDiente().charAt(1)+""));    
        }
        this.setMensajeBitacoraRegistrar("");
        this.setMensajeBitacoraEditar("");
    }

    /**
     * Edita los datos de un procedimiento de la bitácora de procedimientos.
     * @return String Nombre de la página o valor null
     * @throws Exception Se genera si se produce un error al actualizar la base de datos
     */
    public String editarBitacoraProcedimiento(){
        try{
            if(this.isDienteSeleccionadoEditar()){
                bitacoraProcedimientoEditar.setNumeroDiente((getNumeroDienteEditar1()+"")+(getNumeroDienteEditar2()+""));
            }else{
                bitacoraProcedimientoEditar.setCaraDiente("Todas");
                bitacoraProcedimientoEditar.setNumeroDiente("Todos");
            }
             
            service.mergeBitacoraprocedimiento(this.bitacoraProcedimientoEditar);
            service.commitTransaction();
            
            service.mergePaciente(pacienteEditar);
            service.commitTransaction();
            
            this.setMensajeBitacoraEditar("Se han guardado los cambios");
            return null;
        }catch(Exception ex){
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Ha ocurrido un error al intentar editar el registro de la bitácora. Error: " + ex.getMessage()));
            return null;
        }    
    }

    /**
     * Deshabilita un procedimiento de la bitácora de procedimientos de un paciente.
     * @return String Nombre de la página o valor null
     */
    public String eliminarBitacoraProcedimiento() {
        this.bitacoraProcedimientoEditar.setHabilitado(false);
        service.mergeBitacoraprocedimiento(bitacoraProcedimientoEditar);
        service.commitTransaction();
        return null;
    }
    
    // Presupuestos y odontogramas

    /**
     * Agrega una línea de detalle al presupuesto y cambia la imagen correspondiente al procedimiento
     * específicado para el odontograma.
     * @return String Nombre de la página o valor null
     * @throws Exception Se genera si se produce un error al agregar una línea de detalle
     */
    public String agregarLineaDetallePresupuesto(){
        try{
            if(this.isDienteSeleccionadoPresupuestoRegistrar()){
                agregarImagenOdontograma(this.getOdontogramaNumeroDiente1(),
                                         this.getOdontogramaNumeroDiente2(),
                                         this.lineaDetalleRegistrar.getCaraDiente());
                lineaDetalleRegistrar.setNumeroDiente((odontogramaNumeroDiente1+"")+(odontogramaNumeroDiente2+""));
            }else{
                lineaDetalleRegistrar.setNumeroDiente("Todos");
                lineaDetalleRegistrar.setCaraDiente("Todas");
            }
            listaLineasDetallePresupuesto.add(lineaDetalleRegistrar);
            calcularMontoPresupuesto();
            limpiarLineaDetalleRegistrar();
            return null;
        }catch(Exception ex){
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Ha ocurrido un error al intentar agregar una línea de detalle al presupuesto. Error: " + ex.getMessage()));
            return null;
        }    
    }

    /**
     * Crea una nueva instancia de la línea detalle a registrar y fija valores numéricos a cero.
     */
    public void limpiarLineaDetalleRegistrar(){
        this.setLineaDetalleRegistrar(new Lineadetalle());
        this.setOdontogramaNumeroDiente1(0);
        this.setOdontogramaNumeroDiente2(0);
        this.setDienteSeleccionadoPresupuestoRegistrar(true);
    }

    /**
     * Fija la línea detalle y nombres de imágenes del odontograma según se seleccione en la
     * tabla del presupuesto.
     * @param selectionEvent Evento que se genera al seleccionar una línea detalle del presupuesto del paciente
     */
    public void seleccionarLineaDetallePresupuesto(SelectionEvent selectionEvent) {
        indiceLineaDetalleSeleccionada = (Integer) selectionEvent.getAddedSet().iterator().next();
        this.setLineaDetalleEditar(this.getListaLineasDetallePresupuesto().get(indiceLineaDetalleSeleccionada));
        this.setOdontogramaNumeroDienteEditar1(Character.getNumericValue(lineaDetalleEditar.getNumeroDiente().charAt(0)));
        this.setOdontogramaNumeroDienteEditar2(Character.getNumericValue(lineaDetalleEditar.getNumeroDiente().charAt(1)));
        caraDientePresupuestoSeleccionada = listaLineasDetallePresupuesto.get(indiceLineaDetalleSeleccionada).getCaraDiente();
    }

    /**
     * Edita una línea de detalle de un presupuesto registrado, y su respectiva imagen en el odontograma.
     * @return String Nombre de la página o valor null
     */
    public String editarLineaDetallePresupuesto(){
        quitarImagenOdontograma(Character.getNumericValue(listaLineasDetallePresupuesto.get(indiceLineaDetalleSeleccionada).getNumeroDiente().charAt(0)),
                                Character.getNumericValue(listaLineasDetallePresupuesto.get(indiceLineaDetalleSeleccionada).getNumeroDiente().charAt(1)),
                                caraDientePresupuestoSeleccionada);
        
        agregarImagenOdontograma(this.getOdontogramaNumeroDienteEditar1(),
                                 this.getOdontogramaNumeroDienteEditar2(),
                                 lineaDetalleEditar.getCaraDiente());
        
        lineaDetalleEditar.setNumeroDiente((odontogramaNumeroDienteEditar1+"")+(odontogramaNumeroDienteEditar2+""));
        this.getListaLineasDetallePresupuesto().set(indiceLineaDetalleSeleccionada, lineaDetalleEditar);
        calcularMontoPresupuesto();
        return null;
    }

    /**
     * Elimina una línea de detalle del presupuesto y cambia su respectiva imagen en el odontograma.
     * @return String Nombre de la página o valor null
     */
    public String eliminarLineaDetallePresupuesto(){
        quitarImagenOdontograma(Character.getNumericValue(lineaDetalleEditar.getNumeroDiente().charAt(0)),
                                Character.getNumericValue(lineaDetalleEditar.getNumeroDiente().charAt(1)),
                                lineaDetalleEditar.getCaraDiente());
        
        this.getListaLineasDetallePresupuesto().remove(indiceLineaDetalleSeleccionada);
        calcularMontoPresupuesto();
        return null;  
    }

    /**
     * Agrega o cambia una imagen del odontograma.
     * @param numeroDiente1 Primer dígito del número de diente del procedimiento
     * @param numeroDiente2 Segundo dígito del número de diente del procedimiento
     * @param caraDiente Indica el número de la cara del diente o si aplica para la totalidad de este
     */
    private void agregarImagenOdontograma(int numeroDiente1, int numeroDiente2, String caraDiente){
        String imagen = "";
        int length;
        String imagenCambiada;
        int indiceListaDerecha = numeroDiente2-1;
        int indiceListaIzquierda = cambiarIndiceIzquierda(numeroDiente2)-1;
        
        switch(numeroDiente1){
            case 1:
                imagen = listaSuperiorIzquierda.get(indiceListaIzquierda);
                break;
            case 2:
                imagen = listaSuperiorDerecha.get(indiceListaDerecha);
                break;
            case 3:
                imagen = listaInferiorDerecha.get(indiceListaDerecha);
                break;
            case 4:
                imagen = listaInferiorIzquierda.get(indiceListaIzquierda);
                break;
        }
        
        length = imagen.length();
        
        if(!(imagen.contains(caraDiente) && !imagen.equals(""))){
            imagenCambiada = imagen+caraDiente;
            if(length<5){
                switch(length){
                    case 0:
                        imagenCambiada = caraDiente;
                        break;
                    case 1:
                        if (imagenCambiada.contains("1") && imagenCambiada.contains("2")) {
                            imagenCambiada = "12";
                            break;
                        }else if (imagenCambiada.contains("1") && imagenCambiada.contains("3")) {
                            imagenCambiada = "13";
                            break;
                        }else if (imagenCambiada.contains("1") && imagenCambiada.contains("4")) {
                            imagenCambiada = "14";
                            break;
                        }else if (imagenCambiada.contains("1") && imagenCambiada.contains("5")) {
                            imagenCambiada = "15";
                            break;
                        }else if (imagenCambiada.contains("2") && imagenCambiada.contains("3")) {
                            imagenCambiada = "23";
                            break;
                        }else if (imagenCambiada.contains("2") && imagenCambiada.contains("4")) {
                            imagenCambiada = "24";
                            break;
                        }else if (imagenCambiada.contains("2") && imagenCambiada.contains("5")) {
                            imagenCambiada = "25";
                            break;
                        }else if (imagenCambiada.contains("3") && imagenCambiada.contains("4")) {
                            imagenCambiada = "34";
                            break;
                        }else if (imagenCambiada.contains("3") && imagenCambiada.contains("5")) {
                            imagenCambiada = "35";
                            break;
                        }else if (imagenCambiada.contains("4") && imagenCambiada.contains("5")) {
                            imagenCambiada = "45";
                            break;
                        }
                            break;
                    case 2:
                        if (imagenCambiada.contains("1") && imagenCambiada.contains("2") && imagenCambiada.contains("3")) {
                            imagenCambiada = "123";
                            break;
                        }else if (imagenCambiada.contains("1") && imagenCambiada.contains("2") && imagenCambiada.contains("4")) {
                            imagenCambiada = "124";
                            break;
                        }else if (imagenCambiada.contains("1") && imagenCambiada.contains("2") && imagenCambiada.contains("5")) {
                            imagenCambiada = "125";
                            break;
                        }else if (imagenCambiada.contains("1") && imagenCambiada.contains("3") && imagenCambiada.contains("4")) {
                            imagenCambiada = "134";
                            break;
                        }else if (imagenCambiada.contains("1") && imagenCambiada.contains("3") && imagenCambiada.contains("5")) {
                            imagenCambiada = "135";
                            break;
                        }else if (imagenCambiada.contains("1") && imagenCambiada.contains("4") && imagenCambiada.contains("5")) {
                            imagenCambiada = "145";
                            break;
                        }else if (imagenCambiada.contains("2") && imagenCambiada.contains("3") && imagenCambiada.contains("4")) {
                            imagenCambiada = "234";
                            break;
                        }else if (imagenCambiada.contains("2") && imagenCambiada.contains("3") && imagenCambiada.contains("5")) {
                            imagenCambiada = "235";
                            break;
                        }else if (imagenCambiada.contains("2") && imagenCambiada.contains("4") && imagenCambiada.contains("5")) {
                            imagenCambiada = "245";
                            break;
                        }else if (imagenCambiada.contains("3") && imagenCambiada.contains("4") && imagenCambiada.contains("5")) {
                            imagenCambiada = "345";
                            break;
                        }
                            break;
                    case 3:
                        if (imagenCambiada.contains("1") && imagenCambiada.contains("2") && imagenCambiada.contains("3") && imagenCambiada.contains("4")) {
                            imagenCambiada = "1234";
                            break;
                        }else if (imagenCambiada.contains("1") && imagenCambiada.contains("2") && imagenCambiada.contains("3")  && imagenCambiada.contains("5")) {
                            imagenCambiada = "1235";
                            break;
                        }else if (imagenCambiada.contains("1") && imagenCambiada.contains("2") && imagenCambiada.contains("4") && imagenCambiada.contains("5")) {
                            imagenCambiada = "1245";
                            break;
                        }else if (imagenCambiada.contains("1") && imagenCambiada.contains("3") && imagenCambiada.contains("4") && imagenCambiada.contains("5")) {
                            imagenCambiada = "1345";
                            break;
                        }else if (imagenCambiada.contains("2") && imagenCambiada.contains("3") && imagenCambiada.contains("4") && imagenCambiada.contains("5")) {
                            imagenCambiada = "2345";
                            break;
                        }
                            break;
                    case 4:
                        imagenCambiada = "Todas";
                        break;
                }
            }else{
                imagenCambiada = "Todas";
            }
            
            switch(numeroDiente1){
                case 1:
                    listaSuperiorIzquierda.set(indiceListaIzquierda, imagenCambiada);
                    break;
                case 2:
                    listaSuperiorDerecha.set(indiceListaDerecha, imagenCambiada);
                    break;
                case 3:
                    listaInferiorDerecha.set(indiceListaDerecha, imagenCambiada);
                    break;
                case 4:
                    listaInferiorIzquierda.set(indiceListaIzquierda, imagenCambiada);
                    break;
            }   
        }
    }

    /**
     * Cambia el índice de un diente del odontograma del sector derecho al izquierdo.
     * @param indice índice del sector derecho
     * @return indice índice modificado para ser utilizado en el sector izquierdo
     */
    private int cambiarIndiceIzquierda(int indice){
        for(int i=1, j=7;i<=8;i++){
            if(indice==i){
                indice = i+j;
                break;
            }
            j=j-2;
        }
        return indice;
    }

    /**
     * Cambia una imagen en el odontograma luego de que se haya quitado una línea de detalle en el presupuesto del paciente.
     * @param numeroDiente1 Primer dígito del número de diente del procedimiento
     * @param numeroDiente2 Segundo dígito del número de diente del procedimiento
     * @param caraDiente Indica el número de la cara del diente o si aplica para la totalidad de este
     */
    private void quitarImagenOdontograma(int numeroDiente1, int numeroDiente2, String caraDiente){
        String imagen = "";
        int length;
        String imagenCambiada = "";
        int indiceListaDerecha = numeroDiente2-1;
        int indiceListaIzquierda = cambiarIndiceIzquierda(numeroDiente2)-1;
        
        switch(numeroDiente1){
            case 1:
                imagen = listaSuperiorIzquierda.get(indiceListaIzquierda);
                break;
            case 2:
                imagen = listaSuperiorDerecha.get(indiceListaDerecha);
                break;
            case 3:
                imagen = listaInferiorDerecha.get(indiceListaDerecha);
                break;
            case 4:
                imagen = listaInferiorIzquierda.get(indiceListaIzquierda);
                break;
        }
        
        imagenCambiada = imagen.replace(caraDiente, "");
        length = imagenCambiada.length();
        
            if(length<5){
                switch(length){
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        if (imagenCambiada.contains("1") && imagenCambiada.contains("2")) {
                            imagenCambiada = "12";
                            break;
                        }else if (imagenCambiada.contains("1") && imagenCambiada.contains("3")) {
                            imagenCambiada = "13";
                            break;
                        }else if (imagenCambiada.contains("1") && imagenCambiada.contains("4")) {
                            imagenCambiada = "14";
                            break;
                        }else if (imagenCambiada.contains("1") && imagenCambiada.contains("5")) {
                            imagenCambiada = "15";
                            break;
                        }else if (imagenCambiada.contains("2") && imagenCambiada.contains("3")) {
                            imagenCambiada = "23";
                            break;
                        }else if (imagenCambiada.contains("2") && imagenCambiada.contains("4")) {
                            imagenCambiada = "24";
                            break;
                        }else if (imagenCambiada.contains("2") && imagenCambiada.contains("5")) {
                            imagenCambiada = "25";
                            break;
                        }else if (imagenCambiada.contains("3") && imagenCambiada.contains("4")) {
                            imagenCambiada = "34";
                            break;
                        }else if (imagenCambiada.contains("3") && imagenCambiada.contains("5")) {
                            imagenCambiada = "35";
                            break;
                        }else if (imagenCambiada.contains("4") && imagenCambiada.contains("5")) {
                            imagenCambiada = "45";
                            break;
                        }
                            break;
                    case 3:
                        if (imagenCambiada.contains("1") && imagenCambiada.contains("2") && imagenCambiada.contains("3")) {
                            imagenCambiada = "123";
                            break;
                        }else if (imagenCambiada.contains("1") && imagenCambiada.contains("2") && imagenCambiada.contains("4")) {
                            imagenCambiada = "124";
                            break;
                        }else if (imagenCambiada.contains("1") && imagenCambiada.contains("2") && imagenCambiada.contains("5")) {
                            imagenCambiada = "125";
                            break;
                        }else if (imagenCambiada.contains("1") && imagenCambiada.contains("3") && imagenCambiada.contains("4")) {
                            imagenCambiada = "134";
                            break;
                        }else if (imagenCambiada.contains("1") && imagenCambiada.contains("3") && imagenCambiada.contains("5")) {
                            imagenCambiada = "135";
                            break;
                        }else if (imagenCambiada.contains("1") && imagenCambiada.contains("4") && imagenCambiada.contains("5")) {
                            imagenCambiada = "145";
                            break;
                        }else if (imagenCambiada.contains("2") && imagenCambiada.contains("3") && imagenCambiada.contains("4")) {
                            imagenCambiada = "234";
                            break;
                        }else if (imagenCambiada.contains("2") && imagenCambiada.contains("3") && imagenCambiada.contains("5")) {
                            imagenCambiada = "235";
                            break;
                        }else if (imagenCambiada.contains("2") && imagenCambiada.contains("4") && imagenCambiada.contains("5")) {
                            imagenCambiada = "245";
                            break;
                        }else if (imagenCambiada.contains("3") && imagenCambiada.contains("4") && imagenCambiada.contains("5")) {
                            imagenCambiada = "345";
                            break;
                        }
                            break;
                    case 4:
                        if (imagenCambiada.contains("1") && imagenCambiada.contains("2") && imagenCambiada.contains("3") && imagenCambiada.contains("4")) {
                            imagenCambiada = "1234";
                            break;
                        }else if (imagenCambiada.contains("1") && imagenCambiada.contains("2") && imagenCambiada.contains("3")  && imagenCambiada.contains("5")) {
                            imagenCambiada = "1235";
                            break;
                        }else if (imagenCambiada.contains("1") && imagenCambiada.contains("2") && imagenCambiada.contains("4") && imagenCambiada.contains("5")) {
                            imagenCambiada = "1245";
                            break;
                        }else if (imagenCambiada.contains("1") && imagenCambiada.contains("3") && imagenCambiada.contains("4") && imagenCambiada.contains("5")) {
                            imagenCambiada = "1345";
                            break;
                        }else if (imagenCambiada.contains("2") && imagenCambiada.contains("3") && imagenCambiada.contains("4") && imagenCambiada.contains("5")) {
                            imagenCambiada = "2345";
                            break;
                        }
                            break;
                }
            
            switch(numeroDiente1){
                case 1:
                    listaSuperiorIzquierda.set(indiceListaIzquierda, imagenCambiada);
                    break;
                case 2:
                    listaSuperiorDerecha.set(indiceListaDerecha, imagenCambiada);
                    break;
                case 3:
                    listaInferiorDerecha.set(indiceListaDerecha, imagenCambiada);
                    break;
                case 4:
                    listaInferiorIzquierda.set(indiceListaIzquierda, imagenCambiada);
                    break;
            }   
        }
    }

    /**
     * Realiza la búsqueda de un presupuesto registrado y lo muestra junto a su respectivo odontograma.
     * @return String Nombre de la página o valor null
     * @throws Exception Se genera si se produce un error al consultar la base de datos
     */
    public String buscarPresupuesto(){
        try{
            limpiarPresupuestoOdontograma();
            presupuestoRegistrar = service.getPresupuestoFindById(presupuestoFiltro).get(0);
            service.commitTransaction();
            
            presupuestoRegistrar.setLineadetalleList1(service.getLineadetalleFindByPresupuesto(presupuestoRegistrar));
            service.commitTransaction();
            
            for(int i=0;i<this.presupuestoRegistrar.getLineadetalleList1().size();i++){
                this.getListaLineasDetallePresupuesto().add(this.presupuestoRegistrar.getLineadetalleList1().get(i));
                agregarImagenOdontograma(Character.getNumericValue(getListaLineasDetallePresupuesto().get(i).getNumeroDiente().charAt(0)),
                                         Character.getNumericValue(getListaLineasDetallePresupuesto().get(i).getNumeroDiente().charAt(1)),
                                         this.getListaLineasDetallePresupuesto().get(i).getCaraDiente());
                System.out.println("zzzzzzz" + presupuestoRegistrar.getLineadetalleList1().size());
            }
            setAplicado(true);
            setMensajePresupuesto("");
            return null;
        }catch(Exception ex){
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Ha ocurrido un error al realizar la búsqueda del presupuesto. Error: " + ex.getMessage()));
            return null;
        }    
    }

    /**
     * Registra el presupuesto como un movimiento en la cuenta financiera y como un registro en la bitácora de procedimientos.
     * @return String Nombre de la página o valor null
     * @throws Exception Se genera si se produce un error al insertar en la base de datos
     */
    public String aplicarPresupuesto(){
        try{
            if(listaLineasDetallePresupuesto.size()>0){
                java.util.Date date= new java.util.Date();
                presupuestoRegistrar.setFechaCreacion(new Timestamp(date.getTime()));
                calcularMontoPresupuesto();
                presupuestoRegistrar.setCuentapaciente4(pacienteEditar);
                presupuestoRegistrar.setHabilitado(true);
                service.persistPresupuesto(presupuestoRegistrar);
                service.commitTransaction();
                for(int i=0;i<this.getListaLineasDetallePresupuesto().size();i++){
                    this.getListaLineasDetallePresupuesto().get(i).setPresupuesto(service.getPresupuestoFindAll().get(service.getPresupuestoFindAll().size()-1));
                    this.getListaLineasDetallePresupuesto().get(i).setHabilitado(true);
                    service.persistLineadetalle(getListaLineasDetallePresupuesto().get(i));
                    service.commitTransaction();
                }
                
                // Registro en bitácora
                for(int i=0;i<this.getListaLineasDetallePresupuesto().size();i++){
                    Bitacoraprocedimiento bitacora = new Bitacoraprocedimiento();
                    this.getListaLineasDetallePresupuesto().get(i).setHabilitado(true);
                    bitacora.setFecha(new Timestamp(date.getTime()));
                    bitacora.setEstado(getListaLineasDetallePresupuesto().get(i).getEstadoProcedimiento());
                    bitacora.setNumeroDiente(getListaLineasDetallePresupuesto().get(i).getNumeroDiente());
                    bitacora.setCaraDiente(getListaLineasDetallePresupuesto().get(i).getCaraDiente());
                    bitacora.setCuentapaciente(pacienteEditar);
                    bitacora.setBitacoraprocedimiento(getListaLineasDetallePresupuesto().get(i).getBitacoraprocedimiento1());
                    bitacora.setUsuario2(presupuestoRegistrar.getUsuario());
                    bitacora.setHabilitado(true);
                    service.persistBitacoraprocedimiento(bitacora);
                    service.commitTransaction();
                }
                
                // Registro en cuenta financiera
                Movimiento movimiento = new Movimiento();
                movimiento.setFechaMovimiento(new Timestamp(date.getTime()));
                movimiento.setNombreMovimiento("Cobro");
                movimiento.setMonto(presupuestoRegistrar.getMonto());
                movimiento.setCuentapaciente1(pacienteEditar.getCuentapaciente());
                movimiento.setHabilitado(true);
                service.persistMovimiento(movimiento);
                service.commitTransaction();
                
                //calcularSaldoCuenta();
                limpiarPresupuestoOdontograma();
                setAplicado(true);
                setMensajePresupuesto("El presupuesto ha sido aplicado");   
            }else{
                setMensajePresupuesto("Debe agregar al menos un procedimiento para aplicar un presupuesto"); 
            }
            return null;
        }catch(Exception ex){
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Ha ocurrido un error al intentar aplicar el presupuesto. Error: " + ex.getMessage()));
            return null;
        }    
    }

    /**
     * Calcula el monto parcial total del presupuesto mostrado y actualiza este último.
     */
    public void calcularMontoPresupuesto(){
        int monto = 0;
        for(int i=0;i<this.getListaLineasDetallePresupuesto().size();i++){
            monto += getListaLineasDetallePresupuesto().get(i).getBitacoraprocedimiento1().getPrecio();   
        }
        this.presupuestoRegistrar.setMonto(monto);
    }

    /**
     * Crea nuevas instancias del presupuesto y la lista de líneas de detalle a registrar,
     * también restablece valores de los nombres de las imágenes y el monto del presupuesto.
     */
    public void limpiarPresupuestoOdontograma(){
        this.setPresupuestoRegistrar(new Presupuesto());
        this.setListaLineasDetallePresupuesto(new ArrayList());
        this.setListaSuperiorIzquierda(crearListaSuperiorIzquierda());
        this.setListaSuperiorDerecha(crearListaSuperiorDerecha());
        this.setListaInferiorIzquierda(crearListaInferiorIzquierda());
        this.setListaInferiorDerecha(crearListaInferiorDerecha());
        calcularMontoPresupuesto();
        this.setPresupuestoRegistrar(new Presupuesto());
        setAplicado(false);
    }

    /**
     * Edita los datos de un presupuesto registrado y sus líneas de detalle.
     * @return String Nombre de la página o valor null
     * @throws Exception Se genera si se produce un error al actualizar la base de datos
     */
    public String editarPresupuesto(){
        try{
            if(listaLineasDetallePresupuesto.size()>0){
                for(Lineadetalle linea : presupuestoRegistrar.getLineadetalleList1()){
                    linea.setHabilitado(false);
                    service.removeLineadetalle(linea);
                    service.commitTransaction();
                }
                
                for(Lineadetalle linea : getListaLineasDetallePresupuesto()){
                    linea.setPresupuesto(presupuestoRegistrar);
                    linea.setHabilitado(true);
                    service.persistLineadetalle(linea);
                    service.commitTransaction();
                }
                
                service.mergePresupuesto(presupuestoRegistrar);
                service.commitTransaction();
                
                setListaPresupuestos(service.getPresupuestoFindAll());
                service.commitTransaction();
                setMensajePresupuesto("Se han guardado los cambios");
            }else{
                setMensajePresupuesto("Debe agregar al menos un procedimiento para aplicar un presupuesto"); 
            }
            return null;
        }catch(Exception ex){
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage("", new FacesMessage(ex.getMessage()));
            return null;
        }    
    }

    /**
     * Reestablece valores de manera que se pueda crear un nuevo presupuesto desde cero.
     * @return String Nombre de la página o valor null
     */
    public String crearNuevoPresupuesto() {
        limpiarPresupuestoOdontograma();
        setAplicado(false);
        return null;
    }

    /**
     * Deshabilita un presupuesto registrado.
     * @return String Nombre de la página o valor null
     */
    public String eliminarPresupuesto() {
        this.getPresupuestoRegistrar().setHabilitado(false);
        service.mergePresupuesto(presupuestoRegistrar);
        service.commitTransaction();
        limpiarPresupuestoOdontograma();
        setAplicado(false);
        return null;
    }

    /**
     * Fija los valores de los mensajes en blanco y envía al usuario a la bitácora de procedimientos.
     * @return String Nombre de la página de la bitácora de procedimientos
     */
    public String verBitacoraPaciente(){
        setMensajePacienteRegistrar("");
        setMensajePacienteEditar("");
        return "bitacora";
    }

     /**
      * Fija los valores de los mensajes en blanco y envía al usuario a la cuenta financiera.
      * @return String Nombre de la página de la cuenta financiera
      */
    public String verCuentaPaciente(){
        setMensajePacienteRegistrar("");
        setMensajePacienteEditar("");
        return "cuenta";
    }
    
    /**
     * Fija los valores de los mensajes en blanco y envía al usuario a la página de presupuestos y odontogramas.
     * @return String Nombre de la página de los presupuestos y odontogramas
     */
    public String verOdontogramasPaciente(){
        setMensajePacienteRegistrar("");
        setMensajePacienteEditar("");
        setMensajePresupuesto("");
        this.limpiarPresupuestoOdontograma();
        return "odonto";
    }
    
    // Métodos getter y setter
    
    public void setService(JavaServiceFacade service) {
        this.service = service;
    }

    public JavaServiceFacade getService() {
        return service;
    }

    public void setPacienteFiltro(Paciente pacienteFiltro) {
        this.pacienteFiltro = pacienteFiltro;
    }

    public Paciente getPacienteFiltro() {
        return pacienteFiltro;
    }

    public void setPacienteRegistrar(Paciente pacienteRegistrar) {
        this.pacienteRegistrar = pacienteRegistrar;
    }

    public Paciente getPacienteRegistrar() {
        return pacienteRegistrar;
    }

    public void setPacienteEditar(Paciente pacienteEditar) {
        this.pacienteEditar = pacienteEditar;
    }

    public Paciente getPacienteEditar() {
        return pacienteEditar;
    }

    public void setListaPacientes(List<Paciente> listaPacientes) {
        this.listaPacientes = listaPacientes;
    }

    public List<Paciente> getListaPacientes() {
        return listaPacientes;
    }

    public void setMovimientoRegistrar(Movimiento movimientoRegistrar) {
        this.movimientoRegistrar = movimientoRegistrar;
    }

    public Movimiento getMovimientoRegistrar() {
        return movimientoRegistrar;
    }

    public void setMovimientoEditar(Movimiento movimientoEditar) {
        this.movimientoEditar = movimientoEditar;
    }

    public Movimiento getMovimientoEditar() {
        return movimientoEditar;
    }

    public void setListaMovimientos(List<Movimiento> listaMovimientos) {
        this.listaMovimientos = listaMovimientos;
    }

    public List<Movimiento> getListaMovimientos() {
        return service.getMovimientoFindByCuenta(pacienteEditar.getCuentapaciente());
    }

    public void setTelefonoPacienteRegistrar(String telefonoPacienteRegistrar) {
        this.telefonoPacienteRegistrar = telefonoPacienteRegistrar;
    }

    public String getTelefonoPacienteRegistrar() {
        return telefonoPacienteRegistrar;
    }

    public void setListaTelefonosPaciente(List<Telefono> listaTelefonosPaciente) {
        this.listaTelefonosPaciente = listaTelefonosPaciente;
    }

    public List<Telefono> getListaTelefonosPaciente() {
        return listaTelefonosPaciente;
    }

    public void setIdTelefonoSeleccionado(int idTelefonoSeleccionado) {
        this.idTelefonoSeleccionado = idTelefonoSeleccionado;
    }

    public int getIdTelefonoSeleccionado() {
        return idTelefonoSeleccionado;
    }

    public void setListaTelefonosEditarPaciente(List<Telefono> listaTelefonosEditarPaciente) {
        this.listaTelefonosEditarPaciente = listaTelefonosEditarPaciente;
    }

    public List<Telefono> getListaTelefonosEditarPaciente() {
        return listaTelefonosEditarPaciente;
    }

    public void setTelefonoPacienteEditar(String telefonoPacienteEditar) {
        this.telefonoPacienteEditar = telefonoPacienteEditar;
    }

    public String getTelefonoPacienteEditar() {
        return telefonoPacienteEditar;
    }

    public void setIdTelefonoSeleccionadoEditar(int idTelefonoSeleccionadoEditar) {
        this.idTelefonoSeleccionadoEditar = idTelefonoSeleccionadoEditar;
    }

    public int getIdTelefonoSeleccionadoEditar() {
        return idTelefonoSeleccionadoEditar;
    }

    public void setListaBitacoraProcedimientos(List<Bitacoraprocedimiento> listaBitacoraProcedimientos) {
        this.listaBitacoraProcedimientos = listaBitacoraProcedimientos;
    }

    public List<Bitacoraprocedimiento> getListaBitacoraProcedimientos() {
        return service.getBitacoraprocedimientoFindByPaciente(pacienteEditar.getIdPaciente());
    }

    public void setTotalCuentaUsuario(double totalCuentaUsuario) {
        this.totalCuentaUsuario = totalCuentaUsuario;
    }

    public double getTotalCuentaUsuario() {
        return totalCuentaUsuario;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }
    
    public void setBitacoraProcedimientoRegistrar(Bitacoraprocedimiento bitacoraProcedimientoRegistrar) {
        this.bitacoraProcedimientoRegistrar = bitacoraProcedimientoRegistrar;
    }

    public Bitacoraprocedimiento getBitacoraProcedimientoRegistrar() {
        return bitacoraProcedimientoRegistrar;
    }

    public void setBitacoraProcedimientoEditar(Bitacoraprocedimiento bitacoraProcedimientoEditar) {
        this.bitacoraProcedimientoEditar = bitacoraProcedimientoEditar;
    }

    public Bitacoraprocedimiento getBitacoraProcedimientoEditar() {
        return bitacoraProcedimientoEditar;
    }

    public void setNumeroDiente1(int numeroDiente1) {
        this.numeroDiente1 = numeroDiente1;
    }

    public int getNumeroDiente1() {
        return numeroDiente1;
    }

    public void setNumeroDiente2(int numeroDiente2) {
        this.numeroDiente2 = numeroDiente2;
    }

    public int getNumeroDiente2() {
        return numeroDiente2;
    }

    public void setListaProcedimientos(List<Procedimiento> listaProcedimientos) {
        this.listaProcedimientos = listaProcedimientos;
    }

    public List<Procedimiento> getListaProcedimientos() {
        return listaProcedimientos;
    }

    public void setNumeroDienteEditar1(int numeroDienteEditar1) {
        this.numeroDienteEditar1 = numeroDienteEditar1;
    }

    public int getNumeroDienteEditar1() {
        return numeroDienteEditar1;
    }

    public void setNumeroDienteEditar2(int numeroDienteEditar2) {
        this.numeroDienteEditar2 = numeroDienteEditar2;
    }

    public int getNumeroDienteEditar2() {
        return numeroDienteEditar2;
    }

    public void seleccionarPaciente(SelectionEvent selectionEvent) {
        idPacienteSeleccionado = (Integer) selectionEvent.getAddedSet().iterator().next();
    }

    public void setListaPresupuestos(List<Presupuesto> listaPresupuestos) {
        this.listaPresupuestos = listaPresupuestos;
    }

    public List<Presupuesto> getListaPresupuestos() {
        return service.getPresupuestoFindByPaciente(pacienteEditar);
    }

    public void setOdontogramaNumeroDiente1(int odontogramaNumeroDiente1) {
        this.odontogramaNumeroDiente1 = odontogramaNumeroDiente1;
    }

    public int getOdontogramaNumeroDiente1() {
        return odontogramaNumeroDiente1;
    }

    public void setOdontogramaNumeroDiente2(int odontogramaNumeroDiente2) {
        this.odontogramaNumeroDiente2 = odontogramaNumeroDiente2;
    }

    public int getOdontogramaNumeroDiente2() {
        return odontogramaNumeroDiente2;
    }

    public void setOdontogramaNumeroDienteEditar1(int odontogramaNumeroDienteEditar1) {
        this.odontogramaNumeroDienteEditar1 = odontogramaNumeroDienteEditar1;
    }

    public int getOdontogramaNumeroDienteEditar1() {
        return odontogramaNumeroDienteEditar1;
    }

    public void setOdontogramaNumeroDienteEditar2(int odontogramaNumeroDienteEditar2) {
        this.odontogramaNumeroDienteEditar2 = odontogramaNumeroDienteEditar2;
    }

    public int getOdontogramaNumeroDienteEditar2() {
        return odontogramaNumeroDienteEditar2;
    }

    public void setImagenDiente(String imagenDiente) {
        this.imagenDiente = imagenDiente;
    }

    public String getImagenDiente() {
        return imagenDiente;
    }

    public void setListaSuperiorDerecha(List<String> listaSuperiorDerecha) {
        this.listaSuperiorDerecha = listaSuperiorDerecha;
    }

    public List<String> getListaSuperiorDerecha() {
        return listaSuperiorDerecha;
    }

    public void setListaSuperiorIzquierda(List<String> listaSuperiorIzquierda) {
        this.listaSuperiorIzquierda = listaSuperiorIzquierda;
    }

    public List<String> getListaSuperiorIzquierda() {
        return listaSuperiorIzquierda;
    }

    public void setListaInferiorDerecha(List<String> listaInferiorDerecha) {
        this.listaInferiorDerecha = listaInferiorDerecha;
    }

    public List<String> getListaInferiorDerecha() {
        return listaInferiorDerecha;
    }

    public void setListaInferiorIzquierda(List<String> listaInferiorIzquierda) {
        this.listaInferiorIzquierda = listaInferiorIzquierda;
    }

    public List<String> getListaInferiorIzquierda() {
        return listaInferiorIzquierda;
    }

    public void setFechaActual(String fechaActual) {
        this.fechaActual = fechaActual;
    }

    public String getFechaActual() {
        return fechaActual;
    }

    public void setMensajePacienteEditar(String mensajePacienteEditar) {
        this.mensajePacienteEditar = mensajePacienteEditar;
    }

    public String getMensajePacienteEditar() {
        return mensajePacienteEditar;
    }

    public void setIdPacienteSeleccionado(int idPacienteSeleccionado) {
        this.idPacienteSeleccionado = idPacienteSeleccionado;
    }

    public int getIdPacienteSeleccionado() {
        return idPacienteSeleccionado;
    }

    public void setMensajePacienteRegistrar(String mensajePacienteRegistrar) {
        this.mensajePacienteRegistrar = mensajePacienteRegistrar;
    }

    public String getMensajePacienteRegistrar() {
        return mensajePacienteRegistrar;
    }

    public void setPresupuestoRegistrar(Presupuesto presupuestoRegistrar) {
        this.presupuestoRegistrar = presupuestoRegistrar;
    }

    public Presupuesto getPresupuestoRegistrar() {
        return presupuestoRegistrar;
    }

    public void setPresupuestoEditar(Presupuesto presupuestoEditar) {
        this.presupuestoEditar = presupuestoEditar;
    }

    public Presupuesto getPresupuestoEditar() {
        return presupuestoEditar;
    }

    public void setDienteSeleccionadoRegistrar(boolean dienteSeleccionadoRegistrar) {
        this.dienteSeleccionadoRegistrar = dienteSeleccionadoRegistrar;
    }

    public boolean isDienteSeleccionadoRegistrar() {
        return dienteSeleccionadoRegistrar;
    }

    public void setDienteSeleccionadoEditar(boolean dienteSeleccionadoEditar) {
        this.dienteSeleccionadoEditar = dienteSeleccionadoEditar;
    }

    public boolean isDienteSeleccionadoEditar() {
        return dienteSeleccionadoEditar;
    }

    public void setListaEncargadosFamiliaRegistrar(List<Encargadofamilia> listaEncargadosFamiliaRegistrar) {
        this.listaEncargadosFamiliaRegistrar = listaEncargadosFamiliaRegistrar;
    }

    public List<Encargadofamilia> getListaEncargadosFamiliaRegistrar() {
        return listaEncargadosFamiliaRegistrar;
    }

    public void setListaEncargadosFamiliaEditar(List<Encargadofamilia> listaEncargadosFamiliaEditar) {
        this.listaEncargadosFamiliaEditar = listaEncargadosFamiliaEditar;
    }

    public List<Encargadofamilia> getListaEncargadosFamiliaEditar() {
        return listaEncargadosFamiliaEditar;
    }

    public void setIdEncargadoSeleccionadoRegistrar(int idEncargadoSeleccionadoRegistrar) {
        this.idEncargadoSeleccionadoRegistrar = idEncargadoSeleccionadoRegistrar;
    }

    public int getIdEncargadoSeleccionadoRegistrar() {
        return idEncargadoSeleccionadoRegistrar;
    }

    public void setIdEncargadoSeleccionadoEditar(int idEncargadoSeleccionadoEditar) {
        this.idEncargadoSeleccionadoEditar = idEncargadoSeleccionadoEditar;
    }

    public int getIdEncargadoSeleccionadoEditar() {
        return idEncargadoSeleccionadoEditar;
    }

    public void setNombreEncargadoRegistrar(String nombreEncargadoRegistrar) {
        this.nombreEncargadoRegistrar = nombreEncargadoRegistrar;
    }

    public String getNombreEncargadoRegistrar() {
        return nombreEncargadoRegistrar;
    }

    public void setNumeroTelefonoEncargadoRegistrar(String numeroTelefonoEncargadoRegistrar) {
        this.numeroTelefonoEncargadoRegistrar = numeroTelefonoEncargadoRegistrar;
    }

    public String getNumeroTelefonoEncargadoRegistrar() {
        return numeroTelefonoEncargadoRegistrar;
    }

    public void setNombreEncargadoEditar(String nombreEncargadoEditar) {
        this.nombreEncargadoEditar = nombreEncargadoEditar;
    }

    public String getNombreEncargadoEditar() {
        return nombreEncargadoEditar;
    }

    public void setNumeroTelefonoEncargadoEditar(String numeroTelefonoEncargadoEditar) {
        this.numeroTelefonoEncargadoEditar = numeroTelefonoEncargadoEditar;
    }

    public String getNumeroTelefonoEncargadoEditar() {
        return numeroTelefonoEncargadoEditar;
    }

    public void setParentezcoEncargadoRegistrar(String parentezcoEncargadoRegistrar) {
        this.parentezcoEncargadoRegistrar = parentezcoEncargadoRegistrar;
    }

    public String getParentezcoEncargadoRegistrar() {
        return parentezcoEncargadoRegistrar;
    }

    public void setParentezcoEncargadoEditar(String parentezcoEncargadoEditar) {
        this.parentezcoEncargadoEditar = parentezcoEncargadoEditar;
    }

    public String getParentezcoEncargadoEditar() {
        return parentezcoEncargadoEditar;
    }

    public void setListaLineasDetallePresupuesto(List<Lineadetalle> listaLineasDetallePresupuesto) {
        this.listaLineasDetallePresupuesto = listaLineasDetallePresupuesto;
    }

    public List<Lineadetalle> getListaLineasDetallePresupuesto() {
        return listaLineasDetallePresupuesto;
    }

    public void setLineaDetalleRegistrar(Lineadetalle lineaDetalleRegistrar) {
        this.lineaDetalleRegistrar = lineaDetalleRegistrar;
    }

    public Lineadetalle getLineaDetalleRegistrar() {
        return lineaDetalleRegistrar;
    }

    public void setLineaDetalleEditar(Lineadetalle lineaDetalleEditar) {
        this.lineaDetalleEditar = lineaDetalleEditar;
    }

    public Lineadetalle getLineaDetalleEditar() {
        return lineaDetalleEditar;
    }

    public void setDienteSeleccionadoPresupuestoRegistrar(boolean dienteSeleccionadoPresupuestoRegistrar) {
        this.dienteSeleccionadoPresupuestoRegistrar = dienteSeleccionadoPresupuestoRegistrar;
    }

    public boolean isDienteSeleccionadoPresupuestoRegistrar() {
        return dienteSeleccionadoPresupuestoRegistrar;
    }

    public void setDienteSeleccionadoPresupuestoEditar(boolean dienteSeleccionadoPresupuestoEditar) {
        this.dienteSeleccionadoPresupuestoEditar = dienteSeleccionadoPresupuestoEditar;
    }

    public boolean isDienteSeleccionadoPresupuestoEditar() {
        return dienteSeleccionadoPresupuestoEditar;
    }

    public void setMensajeBitacoraRegistrar(String mensajeBitacoraRegistrar) {
        this.mensajeBitacoraRegistrar = mensajeBitacoraRegistrar;
    }

    public String getMensajeBitacoraRegistrar() {
        return mensajeBitacoraRegistrar;
    }

    public void setMensajeBitacoraEditar(String mensajeBitacoraEditar) {
        this.mensajeBitacoraEditar = mensajeBitacoraEditar;
    }

    public String getMensajeBitacoraEditar() {
        return mensajeBitacoraEditar;
    }

    public void setMensajePresupuesto(String mensajePresupuesto) {
        this.mensajePresupuesto = mensajePresupuesto;
    }

    public String getMensajePresupuesto() {
        return mensajePresupuesto;
    }

    public void setIndiceLineaDetalleSeleccionada(int indiceLineaDetalleSeleccionada) {
        this.indiceLineaDetalleSeleccionada = indiceLineaDetalleSeleccionada;
    }

    public int getIndiceLineaDetalleSeleccionada() {
        return indiceLineaDetalleSeleccionada;
    }

    public void setCaraDientePresupuestoSeleccionada(String caraDientePresupuestoSeleccionada) {
        this.caraDientePresupuestoSeleccionada = caraDientePresupuestoSeleccionada;
    }

    public String getCaraDientePresupuestoSeleccionada() {
        return caraDientePresupuestoSeleccionada;
    }

    public void setPresupuestoFiltro(Presupuesto presupuestoFiltro) {
        this.presupuestoFiltro = presupuestoFiltro;
    }

    public Presupuesto getPresupuestoFiltro() {
        return presupuestoFiltro;
    }

    public void setAplicado(boolean aplicado) {
        this.aplicado = aplicado;
    }

    public boolean isAplicado() {
        return aplicado;
    }
}
