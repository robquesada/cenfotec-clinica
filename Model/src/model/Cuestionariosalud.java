 /**
  * Cuestionario de salud. Clase no utilizada.
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
@NamedQueries({ @NamedQuery(name = "Cuestionariosalud.findAll", query = "select o from Cuestionariosalud o") })
@Table(name = "\"cuestionariosalud\"")
public class Cuestionariosalud implements Serializable {
    @Column(name = "cuestionario")
    private String cuestionario;
    @Id
    @Column(name = "idCuestionarioSalud", nullable = false)
    private int idCuestionarioSalud;
    @OneToMany(mappedBy = "cuestionariosalud", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Paciente> cuentapacienteList1;

    /**
     * Constructor de la clase.
     */
    public Cuestionariosalud() {
    }

    /**
     * Constructor de la clase.
     * @param cuestionario Cuestionario
     * @param idCuestionarioSalud Id del cuestionario de salud
     */
    public Cuestionariosalud(String cuestionario, int idCuestionarioSalud) {
        this.cuestionario = cuestionario;
        this.idCuestionarioSalud = idCuestionarioSalud;
    }

    public String getCuestionario() {
        return cuestionario;
    }

    public void setCuestionario(String cuestionario) {
        this.cuestionario = cuestionario;
    }

    public int getIdCuestionarioSalud() {
        return idCuestionarioSalud;
    }

    public void setIdCuestionarioSalud(int idCuestionarioSalud) {
        this.idCuestionarioSalud = idCuestionarioSalud;
    }

    public List<Paciente> getCuentapacienteList1() {
        return cuentapacienteList1;
    }

    public void setCuentapacienteList1(List<Paciente> cuentapacienteList1) {
        this.cuentapacienteList1 = cuentapacienteList1;
    }

    public Paciente addPaciente(Paciente paciente) {
        getCuentapacienteList1().add(paciente);
        paciente.setCuestionariosalud(this);
        return paciente;
    }

    public Paciente removePaciente(Paciente paciente) {
        getCuentapacienteList1().remove(paciente);
        paciente.setCuestionariosalud(null);
        return paciente;
    }
}
