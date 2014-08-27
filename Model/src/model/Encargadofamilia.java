 /**
  * Encargado del paciente.
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
@NamedQueries({ @NamedQuery(name = "Encargadofamilia.findAll", query = "select o from Encargadofamilia o") })
@Table(name = "\"encargadofamilia\"")
public class Encargadofamilia implements Serializable {
    @Column(name = "habilitado")
    private boolean habilitado;
    @Id
    @Column(name = "idEncargadoFamilia", nullable = false)
    private int idEncargadoFamilia;
    @Column(name = "nombreFamiliar")
    private String nombreFamiliar;
    @Column(name = "parentezco")
    private String parentezco;
    @Column(name = "telefonoFamiliar")
    private String telefonoFamiliar;
    @ManyToOne
    @JoinColumn(name = "Paciente_idPaciente")
    private Paciente cuentapaciente1;

    /**
     * Constructor de la clase.
     */
    public Encargadofamilia() {
    }

    /**
     * Constructor de la clase.
     * @param habilitado Indica si está habilitado
     * @param idEncargadoFamilia Id del encargado
     * @param nombreFamiliar Nombre del encargado
     * @param cuentapaciente1 Paciente
     * @param parentezco Relación con el paciente
     * @param telefonoFamiliar Teléfono del encargado
     */
    public Encargadofamilia(boolean habilitado, int idEncargadoFamilia, String nombreFamiliar, Paciente cuentapaciente1,
                            String parentezco, String telefonoFamiliar) {
        this.habilitado = habilitado;
        this.idEncargadoFamilia = idEncargadoFamilia;
        this.nombreFamiliar = nombreFamiliar;
        this.cuentapaciente1 = cuentapaciente1;
        this.parentezco = parentezco;
        this.telefonoFamiliar = telefonoFamiliar;
    }


    public boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public int getIdEncargadoFamilia() {
        return idEncargadoFamilia;
    }

    public void setIdEncargadoFamilia(int idEncargadoFamilia) {
        this.idEncargadoFamilia = idEncargadoFamilia;
    }

    public String getNombreFamiliar() {
        return nombreFamiliar;
    }

    public void setNombreFamiliar(String nombreFamiliar) {
        this.nombreFamiliar = nombreFamiliar;
    }

    public String getParentezco() {
        return parentezco;
    }

    public void setParentezco(String parentezco) {
        this.parentezco = parentezco;
    }

    public String getTelefonoFamiliar() {
        return telefonoFamiliar;
    }

    public void setTelefonoFamiliar(String telefonoFamiliar) {
        this.telefonoFamiliar = telefonoFamiliar;
    }

    public Paciente getCuentapaciente1() {
        return cuentapaciente1;
    }

    public void setCuentapaciente1(Paciente cuentapaciente1) {
        this.cuentapaciente1 = cuentapaciente1;
    }
}
