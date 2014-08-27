 /**
  * Rolpermiso, clase de relación entre Rol y Permiso.
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
@NamedQueries({ @NamedQuery(name = "Rolpermiso.findAll", query = "select o from Rolpermiso o"),
                @NamedQuery(name = "Rolpermiso.findByRol", query = "select o from Rolpermiso o where o.rol.idRol = :filtro") })
@Table(name = "\"rolpermiso\"")
public class Rolpermiso implements Serializable {
    @Column(name = "habiitado")
    private boolean habiitado;
    @Id
    @Column(name = "idRolPermiso", nullable = false)
    private int idRolPermiso;
    @ManyToOne
    @JoinColumn(name = "Rol_idRol")
    private Rol rol;
    @ManyToOne
    @JoinColumn(name = "Permiso_idPermiso")
    private Permiso permiso;

    /**
     * Constructor de la clase.
     */
    public Rolpermiso() {
    }

    /**
     * Constructor de la clase.
     * @param habiitado Indica si esta habilitado
     * @param idRolPermiso Id del rolpermiso
     * @param permiso Permiso
     * @param rol Rol
     */
    public Rolpermiso(boolean habiitado, int idRolPermiso, Permiso permiso, Rol rol) {
        this.habiitado = habiitado;
        this.idRolPermiso = idRolPermiso;
        this.permiso = permiso;
        this.rol = rol;
    }


    public boolean getHabiitado() {
        return habiitado;
    }

    public void setHabiitado(boolean habiitado) {
        this.habiitado = habiitado;
    }

    public int getIdRolPermiso() {
        return idRolPermiso;
    }

    public void setIdRolPermiso(int idRolPermiso) {
        this.idRolPermiso = idRolPermiso;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Permiso getPermiso() {
        return permiso;
    }

    public void setPermiso(Permiso permiso) {
        this.permiso = permiso;
    }
}
