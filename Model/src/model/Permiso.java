 /**
  * Permiso de acceso, se asigna a un rol.
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
@NamedQueries({ @NamedQuery(name = "Permiso.findAll", query = "select o from Permiso o"),
                @NamedQuery(name = "Permiso.findByDesc", query = "select o from Permiso o where o.descripcion = :filtro") })                
@Table(name = "\"permiso\"")
public class Permiso implements Serializable {
    @Column(name = "descripcion")
    private String descripcion;
    @Id
    @Column(name = "idPermiso", nullable = false)
    private int idPermiso;
    @Column(name = "nombrePermiso")
    private String nombrePermiso;
    @OneToMany(mappedBy = "permiso", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Rolpermiso> rolpermisoList1;

    /**
     * Constructor de la clase.
     */
    public Permiso() {
        this.descripcion = "";
        this.nombrePermiso = "";
    }

    /**
     * Constructor de la clase.
     * @param descripcion Descripción
     * @param idPermiso Id del permiso
     * @param nombrePermiso Nombre del permiso
     */
    public Permiso(String descripcion, int idPermiso, String nombrePermiso) {
        this.descripcion = descripcion;
        this.idPermiso = idPermiso;
        this.nombrePermiso = nombrePermiso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(int idPermiso) {
        this.idPermiso = idPermiso;
    }

    public String getNombrePermiso() {
        return nombrePermiso;
    }

    public void setNombrePermiso(String nombrePermiso) {
        this.nombrePermiso = nombrePermiso;
    }

    public List<Rolpermiso> getRolpermisoList1() {
        return rolpermisoList1;
    }

    public void setRolpermisoList1(List<Rolpermiso> rolpermisoList1) {
        this.rolpermisoList1 = rolpermisoList1;
    }

    public Rolpermiso addRolpermiso(Rolpermiso rolpermiso) {
        getRolpermisoList1().add(rolpermiso);
        rolpermiso.setPermiso(this);
        return rolpermiso;
    }

    public Rolpermiso removeRolpermiso(Rolpermiso rolpermiso) {
        getRolpermisoList1().remove(rolpermiso);
        rolpermiso.setPermiso(null);
        return rolpermiso;
    }
}
