
 /**
  * Cita del calendario. La palabra 'actividad' y 'cita', son sinónimos.
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
@NamedQueries({ @NamedQuery(name = "Cita.findAll", query = "select o from Cita o where o.habilitado = 1 order by o.fechaInicio"),
                @NamedQuery(name = "Cita.findAllByTodo", query = "select o from Cita o"),
                @NamedQuery(name = "Cita.findById", query = "select o from Cita o where o.habilitado = 1 and o.idCita = :filtro"),
                @NamedQuery(name = "Cita.findByUsuario", query = "select o from Cita o where o.habilitado = 1 and o.usuario1.idUsuario = :filtro order by o.fechaInicio")})
@Table(name = "\"cita\"")
public class Cita implements Serializable {
    private static final long serialVersionUID = -320985672716293960L;
    @Column(name = "Proveedor", nullable = false)
    private String proveedor;
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    @Column(name = "estado")
    private String estado;
    @Column(name = "fechaFin", nullable = false)
    private Timestamp fechaFin;
    @Column(name = "fechaInicio", nullable = false)
    private Timestamp fechaInicio;
    @Column(name = "habilitado")
    private boolean habilitado;
    @Id
    @Column(name = "idCita", nullable = false)
    private String idCita;
    @ManyToOne
    @JoinColumn(name = "Usuario_idUsuario")
    private Usuario usuario1;
    @ManyToOne
    @JoinColumn(name = "Paciente_idPaciente")
    private Paciente cuentapaciente3;

    /**
     * Constructor de la clase.
     */
    public Cita() {
    }

    /**
     * Constructor de la clase.
     * @param descripcion Descripción de la cita
     * @param estado Estado
     * @param fechaFin Fecha final
     * @param fechaInicio Fecha de inicio
     * @param habilitado Indica si está habilitada
     * @param idCita Id de la cita
     * @param cuentapaciente3 Paciente de la cita
     * @param proveedor Proveedor de la cita
     * @param usuario1 Usuario a quien pertenece la cita
     */
    public Cita(String descripcion, String estado, Timestamp fechaFin, Timestamp fechaInicio, boolean habilitado,
                String idCita, Paciente cuentapaciente3, String proveedor, Usuario usuario1) {
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaFin = fechaFin;
        this.fechaInicio = fechaInicio;
        this.habilitado = habilitado;
        this.idCita = idCita;
        this.cuentapaciente3 = cuentapaciente3;
        this.proveedor = proveedor;
        this.usuario1 = usuario1;
    }


    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Timestamp getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Timestamp fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Timestamp getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Timestamp fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public String getIdCita() {
        return idCita;
    }

    public void setIdCita(String idCita) {
        this.idCita = idCita;
    }

    public Usuario getUsuario1() {
        return usuario1;
    }

    public void setUsuario1(Usuario usuario1) {
        this.usuario1 = usuario1;
    }

    public Paciente getCuentapaciente3() {
        return cuentapaciente3;
    }

    public void setCuentapaciente3(Paciente cuentapaciente3) {
        this.cuentapaciente3 = cuentapaciente3;
    }
}
