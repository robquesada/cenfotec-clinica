 /**
  * Usuario del sistema.
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQueries({ @NamedQuery(name = "Usuario.findAll", query = "select o from Usuario o where o.habilitado = 1"),
                @NamedQuery(name = "Usuario.findByName", query = "select o from Usuario o where o.habilitado = 1 and (o.nombre like :filtro or o.primerApellido like :filtro or o.segundoApellido like :filtro)"),
                @NamedQuery(name = "Usuario.findByUsrName", query = "select o from Usuario o where o.habilitado = 1 and o.nombreUsuario = :filtro")})
@Table(name = "\"usuario\"")
public class Usuario implements Serializable {
    @Column(name = "contrasenna")
    private String contrasenna;
    @Column(name = "correoElectronico")
    private String correoElectronico;
    @Column(name = "especialidad")
    private String especialidad;
    @Column(name = "habilitado")
    private boolean habilitado;
    @Id
    @Column(name = "idUsuario", nullable = false)
    private int idUsuario;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "nombreUsuario", unique = true)
    private String nombreUsuario;
    @Column(name = "primerApellido")
    private String primerApellido;
    @Column(name = "segundoApellido")
    private String segundoApellido;
    @OneToMany(mappedBy = "usuario", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Presupuesto> presupuestoList;
    @OneToMany(mappedBy = "usuario1", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Cita> citaList;
    @ManyToOne
    @JoinColumn(name = "Rol_idRol")
    private Rol rol1;
    @OneToMany(mappedBy = "usuario2", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Bitacoraprocedimiento> bitacoraprocedimientoList2;

    /**
     * Constructor de la clase.
     */
    public Usuario() {
        this.contrasenna = "";
        this.correoElectronico = "";
        this.especialidad = "";
        this.habilitado = true;
        this.nombre = "";
        this.nombreUsuario = "";
        this.primerApellido = "";
        this.segundoApellido = "";
    }

    /**
     * Constructor de la clase.
     * @param contrasenna Contraseña
     * @param correoElectronico Correo electrónico
     * @param especialidad Especialidad
     * @param habilitado Indica si está habilitado
     * @param idUsuario Id del usuario
     * @param nombre Nombre
     * @param nombreUsuario Nombre de usuario
     * @param primerApellido Primer apellido
     * @param rol1 Rol
     * @param segundoApellido Segundo apellido
     */
    public Usuario(String contrasenna, String correoElectronico, String especialidad, boolean habilitado, int idUsuario,
                   String nombre, String nombreUsuario, String primerApellido, Rol rol1, String segundoApellido) {
        this.contrasenna = contrasenna;
        this.correoElectronico = correoElectronico;
        this.especialidad = especialidad;
        this.habilitado = habilitado;
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.primerApellido = primerApellido;
        this.rol1 = rol1;
        this.segundoApellido = segundoApellido;
    }


    public String getContrasenna() {
        return contrasenna;
    }

    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public List<Presupuesto> getPresupuestoList() {
        return presupuestoList;
    }

    public void setPresupuestoList(List<Presupuesto> presupuestoList) {
        this.presupuestoList = presupuestoList;
    }

    public Presupuesto addPresupuesto(Presupuesto presupuesto) {
        getPresupuestoList().add(presupuesto);
        presupuesto.setUsuario(this);
        return presupuesto;
    }

    public Presupuesto removePresupuesto(Presupuesto presupuesto) {
        getPresupuestoList().remove(presupuesto);
        presupuesto.setUsuario(null);
        return presupuesto;
    }

    public List<Cita> getCitaList() {
        return citaList;
    }

    public void setCitaList(List<Cita> citaList) {
        this.citaList = citaList;
    }

    public Cita addCita(Cita cita) {
        getCitaList().add(cita);
        cita.setUsuario1(this);
        return cita;
    }

    public Cita removeCita(Cita cita) {
        getCitaList().remove(cita);
        cita.setUsuario1(null);
        return cita;
    }

    public Rol getRol1() {
        return rol1;
    }

    public void setRol1(Rol rol1) {
        this.rol1 = rol1;
    }

    public List<Bitacoraprocedimiento> getBitacoraprocedimientoList2() {
        return bitacoraprocedimientoList2;
    }

    public void setBitacoraprocedimientoList2(List<Bitacoraprocedimiento> bitacoraprocedimientoList2) {
        this.bitacoraprocedimientoList2 = bitacoraprocedimientoList2;
    }

    public Bitacoraprocedimiento addBitacoraprocedimiento(Bitacoraprocedimiento bitacoraprocedimiento) {
        getBitacoraprocedimientoList2().add(bitacoraprocedimiento);
        bitacoraprocedimiento.setUsuario2(this);
        return bitacoraprocedimiento;
    }

    public Bitacoraprocedimiento removeBitacoraprocedimiento(Bitacoraprocedimiento bitacoraprocedimiento) {
        getBitacoraprocedimientoList2().remove(bitacoraprocedimiento);
        bitacoraprocedimiento.setUsuario2(null);
        return bitacoraprocedimiento;
    }
}
