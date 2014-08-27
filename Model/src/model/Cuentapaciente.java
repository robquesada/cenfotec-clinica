 /**
  * Cuenta financiera del paciente.
  * @author Roberto Quesada
  * @version 1.0, 26/01/2014
  */
package model;

import java.io.Serializable;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQueries({ @NamedQuery(name = "Cuentapaciente.findAll", query = "select o from Cuentapaciente o") })
@Table(name = "\"cuentapaciente\"")
public class Cuentapaciente implements Serializable {
    @Column(name = "codigo")
    private String codigo;
    @Id
    @Column(name = "idCuentaPaciente", nullable = false)
    private int idCuentaPaciente;
    @Column(name = "saldo")
    private double saldo;
    @OneToMany(mappedBy = "cuentapaciente", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Paciente> cuentapacienteList;
    @OneToMany(mappedBy = "cuentapaciente1", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Movimiento> movimientoList;

    /**
     * Constructor de la clase.
     */
    public Cuentapaciente() {
    }

    /**
     * Constructor de la clase.
     * @param codigo Código de la cuenta
     * @param idCuentaPaciente Id de la cuenta
     * @param saldo Saldo de la cuenta
     */
    public Cuentapaciente(String codigo, int idCuentaPaciente, double saldo) {
        this.codigo = codigo;
        this.idCuentaPaciente = idCuentaPaciente;
        this.saldo = saldo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getIdCuentaPaciente() {
        return idCuentaPaciente;
    }

    public void setIdCuentaPaciente(int idCuentaPaciente) {
        this.idCuentaPaciente = idCuentaPaciente;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public List<Paciente> getCuentapacienteList() {
        return cuentapacienteList;
    }

    public void setCuentapacienteList(List<Paciente> cuentapacienteList) {
        this.cuentapacienteList = cuentapacienteList;
    }

    public Paciente addPaciente(Paciente paciente) {
        getCuentapacienteList().add(paciente);
        paciente.setCuentapaciente(this);
        return paciente;
    }

    public Paciente removePaciente(Paciente paciente) {
        getCuentapacienteList().remove(paciente);
        paciente.setCuentapaciente(null);
        return paciente;
    }

    public List<Movimiento> getMovimientoList() {
        return movimientoList;
    }

    public void setMovimientoList(List<Movimiento> movimientoList) {
        this.movimientoList = movimientoList;
    }

    public Movimiento addMovimiento(Movimiento movimiento) {
        getMovimientoList().add(movimiento);
        movimiento.setCuentapaciente1(this);
        return movimiento;
    }

    public Movimiento removeMovimiento(Movimiento movimiento) {
        getMovimientoList().remove(movimiento);
        movimiento.setCuentapaciente1(null);
        return movimiento;
    }
}
