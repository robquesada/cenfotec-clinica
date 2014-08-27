 /**
  * Rol de un usuario, posee permisos de acceso.
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
@NamedQueries({ @NamedQuery(name = "Rol.findAll", query = "select o from Rol o where o.habilitado = 1"),
                @NamedQuery(name = "Rol.findById", query = "select o from Rol o where o.habilitado = 1 and (o.idRol = :filtro)") })

@Table(name = "\"rol\"")
public class Rol implements Serializable {
    @Column(name = "habilitado")
    private boolean habilitado;
    @Id
    @Column(name = "idRol", nullable = false)
    private int idRol;
    @Column(name = "nombreRol", nullable = false)
    private String nombreRol;
    @OneToMany(mappedBy = "rol", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Rolpermiso> rolpermisoList;
    @OneToMany(mappedBy = "rol1", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Usuario> usuarioList;

    /**
     * Constructor de la clase.
     */
    public Rol() {
        this.habilitado = true;
        this.nombreRol = "";
    }

    /**
     * Constructor de la clase.
     * @param habilitado Indica si está habilitado
     * @param idRol Id del rol
     * @param nombreRol Nombre
     */
    public Rol(boolean habilitado, int idRol, String nombreRol) {
        this.habilitado = habilitado;
        this.idRol = idRol;
        this.nombreRol = nombreRol;
    }

    public boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public List<Rolpermiso> getRolpermisoList() {
        return rolpermisoList;
    }

    public void setRolpermisoList(List<Rolpermiso> rolpermisoList) {
        this.rolpermisoList = rolpermisoList;
    }

    public Rolpermiso addRolpermiso(Rolpermiso rolpermiso) {
        getRolpermisoList().add(rolpermiso);
        rolpermiso.setRol(this);
        return rolpermiso;
    }

    public Rolpermiso removeRolpermiso(Rolpermiso rolpermiso) {
        getRolpermisoList().remove(rolpermiso);
        rolpermiso.setRol(null);
        return rolpermiso;
    }

    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    public Usuario addUsuario(Usuario usuario) {
        getUsuarioList().add(usuario);
        usuario.setRol1(this);
        return usuario;
    }

    public Usuario removeUsuario(Usuario usuario) {
        getUsuarioList().remove(usuario);
        usuario.setRol1(null);
        return usuario;
    }
}
