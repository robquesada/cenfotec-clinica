 /**
  * Presupuesto de un paciente (odontograma).
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
@NamedQueries({ @NamedQuery(name = "Presupuesto.findAll", query = "select o from Presupuesto o where o.habilitado = 1"),
                @NamedQuery(name = "Presupuesto.findById", query = "select o from Presupuesto o where o.habilitado = 1 and (o.idPresupuesto = :filtro)"),
                @NamedQuery(name = "Presupuesto.findByPaciente", query = "select o from Presupuesto o where o.habilitado = 1 and (o.cuentapaciente4.idPaciente = :filtro)") })
@Table(name = "\"presupuesto\"")
public class Presupuesto implements Serializable {
    @Column(name = "fechaCreacion")
    private Timestamp fechaCreacion;
    @Column(name = "habilitado")
    private boolean habilitado;
    @Id
    @Column(name = "idPresupuesto", nullable = false)
    private int idPresupuesto;
    @Column(name = "monto")
    private double monto;
    @ManyToOne
    @JoinColumn(name = "Usuario_idUsuario")
    private Usuario usuario;
    @OneToMany(mappedBy = "presupuesto", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Lineadetalle> lineadetalleList1;
    @ManyToOne
    @JoinColumn(name = "Paciente_idPaciente")
    private Paciente cuentapaciente4;

    /**
     * Constructor de la clase.
     */
    public Presupuesto() {
    }

    /**
     * Constructor de la clase.
     * @param fechaCreacion Fecha de creación
     * @param habilitado Indica si está habilitado
     * @param idPresupuesto Id del presupuesto
     * @param monto Monto
     * @param cuentapaciente4 Paciente
     * @param usuario Usuario
     */
    public Presupuesto(Timestamp fechaCreacion, boolean habilitado, int idPresupuesto, double monto,
                       Paciente cuentapaciente4, Usuario usuario) {
        this.fechaCreacion = fechaCreacion;
        this.habilitado = habilitado;
        this.idPresupuesto = idPresupuesto;
        this.monto = monto;
        this.cuentapaciente4 = cuentapaciente4;
        this.usuario = usuario;
    }


    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public int getIdPresupuesto() {
        return idPresupuesto;
    }

    public void setIdPresupuesto(int idPresupuesto) {
        this.idPresupuesto = idPresupuesto;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Lineadetalle> getLineadetalleList1() {
        return lineadetalleList1;
    }

    public void setLineadetalleList1(List<Lineadetalle> lineadetalleList1) {
        this.lineadetalleList1 = lineadetalleList1;
    }

    public Lineadetalle addLineadetalle(Lineadetalle lineadetalle) {
        getLineadetalleList1().add(lineadetalle);
        lineadetalle.setPresupuesto(this);
        return lineadetalle;
    }

    public Lineadetalle removeLineadetalle(Lineadetalle lineadetalle) {
        getLineadetalleList1().remove(lineadetalle);
        lineadetalle.setPresupuesto(null);
        return lineadetalle;
    }

    public Paciente getCuentapaciente4() {
        return cuentapaciente4;
    }

    public void setCuentapaciente4(Paciente cuentapaciente4) {
        this.cuentapaciente4 = cuentapaciente4;
    }
}
