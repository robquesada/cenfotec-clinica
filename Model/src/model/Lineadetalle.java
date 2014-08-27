 /**
  * Línea de detalle del presupuesto del paciente.
  * @author Roberto Quesada
  * @version 1.0, 26/01/2014
  */

package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({ @NamedQuery(name = "Lineadetalle.findAll", query = "select o from Lineadetalle o"),
                @NamedQuery(name = "Lineadetalle.findByPresupuesto", query = "select o from Lineadetalle o where o.habilitado = 1 and (o.presupuesto.idPresupuesto = :filtro)") })
@Table(name = "\"lineadetalle\"")
public class Lineadetalle implements Serializable {
    @Column(name = "caraDiente")
    private String caraDiente;
    @Column(name = "estadoProcedimiento")
    private String estadoProcedimiento;
    @Column(name = "habilitado")
    private boolean habilitado;
    @Id
    @Column(name = "idlineadetalle", nullable = false)
    private int idlineadetalle;
    @Column(name = "numeroDiente")
    private String numeroDiente;
    @ManyToOne
    @JoinColumn(name = "procedimiento_idProcedimiento")
    private Procedimiento bitacoraprocedimiento1;
    @ManyToOne
    @JoinColumn(name = "presupuesto_idPresupuesto")
    private Presupuesto presupuesto;

    /**
     * Constructor de la clase.
     */
    public Lineadetalle() {
    }

    /**
     * Constructor de la clase.
     * @param caraDiente Cara del diente
     * @param estadoProcedimiento Estado del procedimiento
     * @param habilitado Indica si está habilitado
     * @param idlineadetalle Id de la línea de detalle
     * @param numeroDiente Número del diente
     * @param presupuesto Presupuesto
     * @param bitacoraprocedimiento1 Procedimiento
     */
    public Lineadetalle(String caraDiente, String estadoProcedimiento, boolean habilitado, int idlineadetalle,
                        String numeroDiente, Presupuesto presupuesto, Procedimiento bitacoraprocedimiento1) {
        this.caraDiente = caraDiente;
        this.estadoProcedimiento = estadoProcedimiento;
        this.habilitado = habilitado;
        this.idlineadetalle = idlineadetalle;
        this.numeroDiente = numeroDiente;
        this.presupuesto = presupuesto;
        this.bitacoraprocedimiento1 = bitacoraprocedimiento1;
    }

    public String getCaraDiente() {
        return caraDiente;
    }

    public void setCaraDiente(String caraDiente) {
        this.caraDiente = caraDiente;
    }

    public String getEstadoProcedimiento() {
        return estadoProcedimiento;
    }

    public void setEstadoProcedimiento(String estadoProcedimiento) {
        this.estadoProcedimiento = estadoProcedimiento;
    }

    public boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public int getIdlineadetalle() {
        return idlineadetalle;
    }

    public void setIdlineadetalle(int idlineadetalle) {
        this.idlineadetalle = idlineadetalle;
    }

    public String getNumeroDiente() {
        return numeroDiente;
    }

    public void setNumeroDiente(String numeroDiente) {
        this.numeroDiente = numeroDiente;
    }


    public Procedimiento getBitacoraprocedimiento1() {
        return bitacoraprocedimiento1;
    }

    public void setBitacoraprocedimiento1(Procedimiento bitacoraprocedimiento1) {
        this.bitacoraprocedimiento1 = bitacoraprocedimiento1;
    }

    public Presupuesto getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Presupuesto presupuesto) {
        this.presupuesto = presupuesto;
    }
}
