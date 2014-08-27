 /**
  * Movimiento de una cuenta financiera del paciente.
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
@NamedQueries({ @NamedQuery(name = "Movimiento.findAll", query = "select o from Movimiento o where o.habilitado = 1"),
                @NamedQuery(name = "Movimiento.findByCuenta", query = "select o from Movimiento o where o.habilitado = 1 and (o.cuentapaciente1.idCuentaPaciente = :filtro)") })
@Table(name = "\"movimiento\"")
public class Movimiento implements Serializable {
    @Column(name = "fechaMovimiento")
    private Timestamp fechaMovimiento;
    @Column(name = "habilitado")
    private boolean habilitado;
    @Id
    @Column(name = "idMovimiento", nullable = false)
    private int idMovimiento;
    @Column(name = "monto")
    private double monto;
    @Column(name = "nombreMovimiento")
    private String nombreMovimiento;
    @ManyToOne
    @JoinColumn(name = "CuentaPaciente_idCuentaPaciente")
    private Cuentapaciente cuentapaciente1;

    /**
     * Constructor de la clase.
     */
    public Movimiento() {
    }

    /**
     * Constructor de la clase.
     * @param cuentapaciente1 Cuenta del paciente
     * @param fechaMovimiento Fecha del movimiento
     * @param habilitado Indica si está habilitado
     * @param idMovimiento Id del movimiento
     * @param monto Monto
     * @param nombreMovimiento Nombre
     */
    public Movimiento(Cuentapaciente cuentapaciente1, Timestamp fechaMovimiento, boolean habilitado, int idMovimiento,
                      double monto, String nombreMovimiento) {
        this.cuentapaciente1 = cuentapaciente1;
        this.fechaMovimiento = fechaMovimiento;
        this.habilitado = habilitado;
        this.idMovimiento = idMovimiento;
        this.monto = monto;
        this.nombreMovimiento = nombreMovimiento;
    }


    public Timestamp getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(Timestamp fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getNombreMovimiento() {
        return nombreMovimiento;
    }

    public void setNombreMovimiento(String nombreMovimiento) {
        this.nombreMovimiento = nombreMovimiento;
    }

    public Cuentapaciente getCuentapaciente1() {
        return cuentapaciente1;
    }

    public void setCuentapaciente1(Cuentapaciente cuentapaciente1) {
        this.cuentapaciente1 = cuentapaciente1;
    }
}
