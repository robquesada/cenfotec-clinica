 /**
  * Paciente de la clínica.
  * @author Roberto Quesada
  * @version 1.0, 26/01/2014
  */

package model;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQueries({ @NamedQuery(name = "Paciente.findAll", query = "select o from Paciente o where o.habilitado = 1"),
                @NamedQuery(name = "Paciente.findByName", query = "select o from Paciente o where o.habilitado = 1 and (o.nombrePaciente like :filtro or o.primerApellido like :filtro or o.segundoApellido like :filtro)") })
@Table(name = "\"paciente\"")
public class Paciente implements Serializable {
    @Column(name = "comentario")
    private String comentario;
    @Column(name = "correoElectronico")
    private String correoElectronico;
    @Column(name = "fechaIngreso")
    private Timestamp fechaIngreso;
    @Column(name = "fechaNacimiento")
    private Timestamp fechaNacimiento;
    @Column(name = "habilitado")
    private boolean habilitado;
    @Id
    @Column(name = "idPaciente", nullable = false)
    private int idPaciente;
    @Column(name = "identificacionPaciente")
    private String identificacionPaciente;
    @Column(name = "lugarResidencia", nullable = false)
    private String lugarResidencia;
    @Column(name = "nombrePaciente", nullable = false)
    private String nombrePaciente;
    @Column(name = "numeroExpediente")
    private String numeroExpediente;
    @Column(name = "porcentajeDescuento", nullable = false)
    private double porcentajeDescuento;
    @Column(name = "primerApellido", nullable = false)
    private String primerApellido;
    @Column(name = "segundoApellido")
    private String segundoApellido;
    @Column(name = "sexo")
    private String sexo;
    @OneToMany(mappedBy = "cuentapaciente", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Bitacoraprocedimiento> bitacoraprocedimientoList1;
    @ManyToOne
    @JoinColumn(name = "CuentaPaciente_idCuentaPaciente")
    private Cuentapaciente cuentapaciente;
    @OneToMany(mappedBy = "cuentapaciente1", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Encargadofamilia> encargadofamiliaList;
    @OneToMany(mappedBy = "cuentapaciente2", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Telefonopaciente> telefonopacienteList;
    @OneToMany(mappedBy = "cuentapaciente3", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Cita> citaList1;
    @OneToMany(mappedBy = "cuentapaciente4", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Presupuesto> presupuestoList1;
    @ManyToOne
    @JoinColumn(name = "CuestionarioSalud_idCuestionarioSalud")
    private Cuestionariosalud cuestionariosalud;

    /**
     * Constructor de la clase.
     */
    public Paciente() {
        this.comentario = "";
        this.correoElectronico = "";
        this.habilitado = true;
        this.identificacionPaciente = "";
        this.lugarResidencia = "";
        this.nombrePaciente = "";
        this.numeroExpediente = "";
        this.porcentajeDescuento = 0;
        this.primerApellido = "";
        this.segundoApellido = "";
        this.sexo = "";
    }

    /**
     * Constructor de la clase.
     * @param comentario Comentario
     * @param correoElectronico Correo electrónico
     * @param cuentapaciente Cuenta del paciente
     * @param cuestionariosalud Cuestionario de salud
     * @param fechaIngreso Fecha de ingreso
     * @param fechaNacimiento Fecha de nacimiento
     * @param habilitado Indica si esta habilitado
     * @param idPaciente Id del paciente
     * @param identificacionPaciente Identificación
     * @param lugarResidencia Lugar de residencia
     * @param nombrePaciente Nombre
     * @param numeroExpediente Numero de expediente
     * @param porcentajeDescuento Porcentaje de descuento
     * @param primerApellido Primer apellido
     * @param segundoApellido Segundo apellido
     * @param sexo Sexo
     */
    public Paciente(String comentario, String correoElectronico, Cuentapaciente cuentapaciente,
                    Cuestionariosalud cuestionariosalud, Timestamp fechaIngreso, Timestamp fechaNacimiento,
                    boolean habilitado, int idPaciente, String identificacionPaciente, String lugarResidencia,
                    String nombrePaciente, String numeroExpediente, double porcentajeDescuento, String primerApellido,
                    String segundoApellido, String sexo) {
        this.comentario = comentario;
        this.correoElectronico = correoElectronico;
        this.cuentapaciente = cuentapaciente;
        this.cuestionariosalud = cuestionariosalud;
        this.fechaIngreso = fechaIngreso;
        this.fechaNacimiento = fechaNacimiento;
        this.habilitado = habilitado;
        this.idPaciente = idPaciente;
        this.identificacionPaciente = identificacionPaciente;
        this.lugarResidencia = lugarResidencia;
        this.nombrePaciente = nombrePaciente;
        this.numeroExpediente = numeroExpediente;
        this.porcentajeDescuento = porcentajeDescuento;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.sexo = sexo;
    }


    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public Timestamp getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Timestamp fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Timestamp getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Timestamp fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getIdentificacionPaciente() {
        return identificacionPaciente;
    }

    public void setIdentificacionPaciente(String identificacionPaciente) {
        this.identificacionPaciente = identificacionPaciente;
    }

    public String getLugarResidencia() {
        return lugarResidencia;
    }

    public void setLugarResidencia(String lugarResidencia) {
        this.lugarResidencia = lugarResidencia;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getNumeroExpediente() {
        return numeroExpediente;
    }

    public void setNumeroExpediente(String numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public List<Bitacoraprocedimiento> getBitacoraprocedimientoList1() {
        return bitacoraprocedimientoList1;
    }

    public void setBitacoraprocedimientoList1(List<Bitacoraprocedimiento> bitacoraprocedimientoList1) {
        this.bitacoraprocedimientoList1 = bitacoraprocedimientoList1;
    }

    public Bitacoraprocedimiento addBitacoraprocedimiento(Bitacoraprocedimiento bitacoraprocedimiento) {
        getBitacoraprocedimientoList1().add(bitacoraprocedimiento);
        bitacoraprocedimiento.setCuentapaciente(this);
        return bitacoraprocedimiento;
    }

    public Bitacoraprocedimiento removeBitacoraprocedimiento(Bitacoraprocedimiento bitacoraprocedimiento) {
        getBitacoraprocedimientoList1().remove(bitacoraprocedimiento);
        bitacoraprocedimiento.setCuentapaciente(null);
        return bitacoraprocedimiento;
    }

    public Cuentapaciente getCuentapaciente() {
        return cuentapaciente;
    }

    public void setCuentapaciente(Cuentapaciente cuentapaciente) {
        this.cuentapaciente = cuentapaciente;
    }

    public List<Encargadofamilia> getEncargadofamiliaList() {
        return encargadofamiliaList;
    }

    public void setEncargadofamiliaList(List<Encargadofamilia> encargadofamiliaList) {
        this.encargadofamiliaList = encargadofamiliaList;
    }

    public Encargadofamilia addEncargadofamilia(Encargadofamilia encargadofamilia) {
        getEncargadofamiliaList().add(encargadofamilia);
        encargadofamilia.setCuentapaciente1(this);
        return encargadofamilia;
    }

    public Encargadofamilia removeEncargadofamilia(Encargadofamilia encargadofamilia) {
        getEncargadofamiliaList().remove(encargadofamilia);
        encargadofamilia.setCuentapaciente1(null);
        return encargadofamilia;
    }

    public List<Telefonopaciente> getTelefonopacienteList() {
        return telefonopacienteList;
    }

    public void setTelefonopacienteList(List<Telefonopaciente> telefonopacienteList) {
        this.telefonopacienteList = telefonopacienteList;
    }

    public Telefonopaciente addTelefonopaciente(Telefonopaciente telefonopaciente) {
        getTelefonopacienteList().add(telefonopaciente);
        telefonopaciente.setCuentapaciente2(this);
        return telefonopaciente;
    }

    public Telefonopaciente removeTelefonopaciente(Telefonopaciente telefonopaciente) {
        getTelefonopacienteList().remove(telefonopaciente);
        telefonopaciente.setCuentapaciente2(null);
        return telefonopaciente;
    }

    public List<Cita> getCitaList1() {
        return citaList1;
    }

    public void setCitaList1(List<Cita> citaList1) {
        this.citaList1 = citaList1;
    }

    public Cita addCita(Cita cita) {
        getCitaList1().add(cita);
        cita.setCuentapaciente3(this);
        return cita;
    }

    public Cita removeCita(Cita cita) {
        getCitaList1().remove(cita);
        cita.setCuentapaciente3(null);
        return cita;
    }

    public List<Presupuesto> getPresupuestoList1() {
        return presupuestoList1;
    }

    public void setPresupuestoList1(List<Presupuesto> presupuestoList1) {
        this.presupuestoList1 = presupuestoList1;
    }

    public Presupuesto addPresupuesto(Presupuesto presupuesto) {
        getPresupuestoList1().add(presupuesto);
        presupuesto.setCuentapaciente4(this);
        return presupuesto;
    }

    public Presupuesto removePresupuesto(Presupuesto presupuesto) {
        getPresupuestoList1().remove(presupuesto);
        presupuesto.setCuentapaciente4(null);
        return presupuesto;
    }

    public Cuestionariosalud getCuestionariosalud() {
        return cuestionariosalud;
    }

    public void setCuestionariosalud(Cuestionariosalud cuestionariosalud) {
        this.cuestionariosalud = cuestionariosalud;
    }
}
