 /**
  * Telefonopaciente, clase de la relación entre Telefono y Paciente.
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
@NamedQueries({ @NamedQuery(name = "Telefonopaciente.findAll", query = "select o from Telefonopaciente o") })
@Table(name = "\"telefonopaciente\"")
public class Telefonopaciente implements Serializable {
    @Id
    @Column(name = "idTelefonoPaciente", nullable = false)
    private int idTelefonoPaciente;
    @ManyToOne
    @JoinColumn(name = "Telefono_idTelefono")
    private Telefono telefono;
    @ManyToOne
    @JoinColumn(name = "Paciente_idPaciente")
    private Paciente cuentapaciente2;

    /**
     * Constructor de la clase.
     */
    public Telefonopaciente() {
    }

    /**
     * Constructor de la clase.
     * @param idTelefonoPaciente Id del telefonopaciente
     * @param cuentapaciente2 Paciente
     * @param telefono Teléfono
     */
    public Telefonopaciente(int idTelefonoPaciente, Paciente cuentapaciente2, Telefono telefono) {
        this.idTelefonoPaciente = idTelefonoPaciente;
        this.cuentapaciente2 = cuentapaciente2;
        this.telefono = telefono;
    }


    public int getIdTelefonoPaciente() {
        return idTelefonoPaciente;
    }

    public void setIdTelefonoPaciente(int idTelefonoPaciente) {
        this.idTelefonoPaciente = idTelefonoPaciente;
    }

    public Telefono getTelefono() {
        return telefono;
    }

    public void setTelefono(Telefono telefono) {
        this.telefono = telefono;
    }

    public Paciente getCuentapaciente2() {
        return cuentapaciente2;
    }

    public void setCuentapaciente2(Paciente cuentapaciente2) {
        this.cuentapaciente2 = cuentapaciente2;
    }
}
