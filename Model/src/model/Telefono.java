 /**
  * Teléfono del paciente.
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
@NamedQueries({ @NamedQuery(name = "Telefono.findAll", query = "select o from Telefono o") })
@Table(name = "\"telefono\"")
public class Telefono implements Serializable {
    @Column(name = "habilitado")
    private boolean habilitado;
    @Id
    @Column(name = "idTelefono", nullable = false)
    private int idTelefono;
    @Column(name = "numeroTelefono")
    private String numeroTelefono;
    @OneToMany(mappedBy = "telefono", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Telefonopaciente> telefonopacienteList1;

    /**
     * Constructor de la clase.
     */
    public Telefono() {
        this.habilitado = true;
        this.numeroTelefono = "";
    }

    /**
     * Constructor de la clase.
     * @param habilitado Indica si está habilitado
     * @param idTelefono Id del teléfono
     * @param numeroTelefono Número de teléfono
     */
    public Telefono(boolean habilitado, int idTelefono, String numeroTelefono) {
        this.habilitado = habilitado;
        this.idTelefono = idTelefono;
        this.numeroTelefono = numeroTelefono;
    }

    public boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public int getIdTelefono() {
        return idTelefono;
    }

    public void setIdTelefono(int idTelefono) {
        this.idTelefono = idTelefono;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public List<Telefonopaciente> getTelefonopacienteList1() {
        return telefonopacienteList1;
    }

    public void setTelefonopacienteList1(List<Telefonopaciente> telefonopacienteList1) {
        this.telefonopacienteList1 = telefonopacienteList1;
    }

    public Telefonopaciente addTelefonopaciente(Telefonopaciente telefonopaciente) {
        getTelefonopacienteList1().add(telefonopaciente);
        telefonopaciente.setTelefono(this);
        return telefonopaciente;
    }

    public Telefonopaciente removeTelefonopaciente(Telefonopaciente telefonopaciente) {
        getTelefonopacienteList1().remove(telefonopaciente);
        telefonopaciente.setTelefono(null);
        return telefonopaciente;
    }
}
