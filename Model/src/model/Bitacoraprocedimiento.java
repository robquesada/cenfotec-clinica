 /**
  * Clase de la bitácora de procedimientos del paciente.
  * @author Roberto Quesada
  * @version 1.0, 26/01/2014
  */

package model;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({ @NamedQuery(name = "Bitacoraprocedimiento.findAll", query = "select o from Bitacoraprocedimiento o where o.habilitado = 1"),
                @NamedQuery(name = "Bitacoraprocedimiento.findByPaciente", query = "select o from Bitacoraprocedimiento o where o.habilitado = 1 and (o.cuentapaciente.idPaciente = :filtro)") })

@Table(name = "\"bitacoraprocedimiento\"")
public class Bitacoraprocedimiento implements Serializable {
    @Column(name = "CaraDiente")
    private String caraDiente;
    @Column(name = "estado")
    private String estado;
    @Column(name = "fecha")
    private Timestamp fecha;
    @Column(name = "habilitado")
    private boolean habilitado;
    @Id
    @Column(name = "idBitacoraProcedimiento", nullable = false)
    private int idBitacoraProcedimiento;
    @Column(name = "NumeroDiente")
    private String numeroDiente;
    @ManyToOne
    @JoinColumn(name = "Procedimiento_idProcedimiento")
    private Procedimiento bitacoraprocedimiento;
    @ManyToOne
    @JoinColumn(name = "Paciente_idPaciente")
    private Paciente cuentapaciente;
    @ManyToOne
    @JoinColumn(name = "Usuario_idUsuario")
    private Usuario usuario2;

    /**
     * Constructor de la clase.
     */
    public Bitacoraprocedimiento() {
    }

    /**
     * Constructor de la clase.
     * @param caraDiente Cara del diente
     * @param estado Estado del procedimiento
     * @param fecha Fecha de aplicación
     * @param habilitado Indica si está habilitado
     * @param idBitacoraProcedimiento Id del registro
     * @param numeroDiente Número del diente
     * @param cuentapaciente Paciente a quien se le aplicó el procedimiento
     * @param bitacoraprocedimiento Procedimiento a registrar
     * @param usuario2 Usuario que lo aplicó
     */
    public Bitacoraprocedimiento(String caraDiente, String estado, Timestamp fecha, boolean habilitado,
                                 int idBitacoraProcedimiento, String numeroDiente, Paciente cuentapaciente,
                                 Procedimiento bitacoraprocedimiento, Usuario usuario2) {
        this.caraDiente = caraDiente;
        this.estado = estado;
        this.fecha = fecha;
        this.habilitado = habilitado;
        this.idBitacoraProcedimiento = idBitacoraProcedimiento;
        this.numeroDiente = numeroDiente;
        this.cuentapaciente = cuentapaciente;
        this.bitacoraprocedimiento = bitacoraprocedimiento;
        this.usuario2 = usuario2;
    }

    public String getCaraDiente() {
        return caraDiente;
    }

    public void setCaraDiente(String caraDiente) {
        this.caraDiente = caraDiente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public int getIdBitacoraProcedimiento() {
        return idBitacoraProcedimiento;
    }

    public void setIdBitacoraProcedimiento(int idBitacoraProcedimiento) {
        this.idBitacoraProcedimiento = idBitacoraProcedimiento;
    }

    public String getNumeroDiente() {
        return numeroDiente;
    }

    public void setNumeroDiente(String numeroDiente) {
        this.numeroDiente = numeroDiente;
    }


    public Procedimiento getBitacoraprocedimiento() {
        return bitacoraprocedimiento;
    }

    public void setBitacoraprocedimiento(Procedimiento bitacoraprocedimiento) {
        this.bitacoraprocedimiento = bitacoraprocedimiento;
    }

    public Paciente getCuentapaciente() {
        return cuentapaciente;
    }

    public void setCuentapaciente(Paciente cuentapaciente) {
        this.cuentapaciente = cuentapaciente;
    }

    public Usuario getUsuario2() {
        return usuario2;
    }

    public void setUsuario2(Usuario usuario2) {
        this.usuario2 = usuario2;
    }
}
