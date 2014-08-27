 /**
  * Procedimiento odontológico.
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
@NamedQueries({ @NamedQuery(name = "Procedimiento.findAll", query = "select o from Procedimiento o where o.habilitado = 1"),
                @NamedQuery(name = "Procedimiento.findByName", query = "select o from Procedimiento o where o.habilitado = 1 and (o.nombreProcedimiento like :filtro)") })
@Table(name = "\"procedimiento\"")
public class Procedimiento implements Serializable {
    @Column(name = "habilitado")
    private boolean habilitado;
    @Id
    @Column(name = "idProcedimiento", nullable = false)
    private int idProcedimiento;
    @Column(name = "nombreProcedimiento", nullable = false)
    private String nombreProcedimiento;
    @Column(name = "precio")
    private int precio;
    @OneToMany(mappedBy = "bitacoraprocedimiento", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Bitacoraprocedimiento> bitacoraprocedimientoList;
    @OneToMany(mappedBy = "bitacoraprocedimiento1", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Lineadetalle> lineadetalleList;

    /**
     * Constructor de la clase.
     */
    public Procedimiento() {
        this.habilitado = true;
        this.nombreProcedimiento = "";
        this.precio = 0;
    }

    /**
     * Constructor de la clase.
     * @param habilitado Indica si está habilitado
     * @param idProcedimiento Id del procedimiento
     * @param nombreProcedimiento Nombre del procedimiento
     * @param precio Precio
     */
    public Procedimiento(boolean habilitado, int idProcedimiento, String nombreProcedimiento, int precio) {
        this.habilitado = habilitado;
        this.idProcedimiento = idProcedimiento;
        this.nombreProcedimiento = nombreProcedimiento;
        this.precio = precio;
    }

    public boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public int getIdProcedimiento() {
        return idProcedimiento;
    }

    public void setIdProcedimiento(int idProcedimiento) {
        this.idProcedimiento = idProcedimiento;
    }

    public String getNombreProcedimiento() {
        return nombreProcedimiento;
    }

    public void setNombreProcedimiento(String nombreProcedimiento) {
        this.nombreProcedimiento = nombreProcedimiento;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public List<Bitacoraprocedimiento> getBitacoraprocedimientoList() {
        return bitacoraprocedimientoList;
    }

    public void setBitacoraprocedimientoList(List<Bitacoraprocedimiento> bitacoraprocedimientoList) {
        this.bitacoraprocedimientoList = bitacoraprocedimientoList;
    }

    public Bitacoraprocedimiento addBitacoraprocedimiento(Bitacoraprocedimiento bitacoraprocedimiento) {
        getBitacoraprocedimientoList().add(bitacoraprocedimiento);
        bitacoraprocedimiento.setBitacoraprocedimiento(this);
        return bitacoraprocedimiento;
    }

    public Bitacoraprocedimiento removeBitacoraprocedimiento(Bitacoraprocedimiento bitacoraprocedimiento) {
        getBitacoraprocedimientoList().remove(bitacoraprocedimiento);
        bitacoraprocedimiento.setBitacoraprocedimiento(null);
        return bitacoraprocedimiento;
    }

    public List<Lineadetalle> getLineadetalleList() {
        return lineadetalleList;
    }

    public void setLineadetalleList(List<Lineadetalle> lineadetalleList) {
        this.lineadetalleList = lineadetalleList;
    }

    public Lineadetalle addLineadetalle(Lineadetalle lineadetalle) {
        getLineadetalleList().add(lineadetalle);
        lineadetalle.setBitacoraprocedimiento1(this);
        return lineadetalle;
    }

    public Lineadetalle removeLineadetalle(Lineadetalle lineadetalle) {
        getLineadetalleList().remove(lineadetalle);
        lineadetalle.setBitacoraprocedimiento1(null);
        return lineadetalle;
    }
}
