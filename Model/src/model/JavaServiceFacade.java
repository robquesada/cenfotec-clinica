package model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class JavaServiceFacade {
    private final EntityManager em;

    public JavaServiceFacade() {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Model-1");
        em = emf.createEntityManager();
    }

    /**
     * All changes that have been made to the managed entities in the
     * persistence context are applied to the database and committed.
     */
    public void commitTransaction() {
        final EntityTransaction entityTransaction = em.getTransaction();
        if (!entityTransaction.isActive()) {
            entityTransaction.begin();
        }
        entityTransaction.commit();
    }

    /**
     * The persistence context is cleared of all managed entities, rolling back
     * all pending changes in the persistence tier.
     */
    public void rollbackTransaction() {
        em.clear();
    }

    /**
     * There is currently no way to ask the EntityManager whether changes are
     * pending in the persistence context.  For now, we just always assume there
     * are pending changes whenever the EntityManager is active.
     *
     * @return true if the EntityManager is open for business.
     */
    public boolean isTransactionDirty() {
        return em.isOpen();
    }

    public Object queryByRange(String jpqlStmt, int firstResult, int maxResults) {
        Query query = em.createQuery(jpqlStmt);
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    public <T> T persistEntity(T entity) {
        em.persist(entity);
        return entity;
    }

    public <T> T mergeEntity(T entity) {
        return em.merge(entity);
    }

    public Presupuesto persistPresupuesto(Presupuesto presupuesto) {
        em.persist(presupuesto);
        return presupuesto;
    }

    public Presupuesto mergePresupuesto(Presupuesto presupuesto) {
        return em.merge(presupuesto);
    }

    public void removePresupuesto(Presupuesto presupuesto) {
        presupuesto = em.find(Presupuesto.class, presupuesto.getIdPresupuesto());
        em.remove(presupuesto);
    }

    /** <code>select o from Presupuesto o</code> */
    public List<Presupuesto> getPresupuestoFindAll() {
        return em.createNamedQuery("Presupuesto.findAll", Presupuesto.class).getResultList();
    }

    /** <code>select o from Presupuesto o where o.habilitado = 1 and (o.idPresupuesto = :filtro)</code> */
    public List<Presupuesto> getPresupuestoFindById(Presupuesto filtro){
        Query q = em.createNamedQuery("Presupuesto.findById", Presupuesto.class);
        q.setParameter("filtro", filtro.getIdPresupuesto());
        return q.getResultList();
    }
    
    /** <code>select o from Presupuesto o where o.habilitado = 1 and (o.cuentapaciente4.idPaciente = :filtro)</code> */
    public List<Presupuesto> getPresupuestoFindByPaciente(Paciente filtro){
        Query q = em.createNamedQuery("Presupuesto.findByPaciente", Presupuesto.class);
        q.setParameter("filtro", filtro.getIdPaciente());
        return q.getResultList();
    }
    
    public Usuario persistUsuario(Usuario usuario) {
        em.persist(usuario);
        return usuario;
    }

    public Usuario mergeUsuario(Usuario usuario) {
        return em.merge(usuario);
    }

    public void removeUsuario(Usuario usuario) {
        usuario = em.find(Usuario.class, usuario.getIdUsuario());
        em.remove(usuario);
    }

    /** <code>select o from Usuario o</code> */
    public List<Usuario> getUsuarioFindAll() {
        return em.createNamedQuery("Usuario.findAll", Usuario.class).getResultList();
    }

    /** <code>select o from Usuario where o.nombre like :filtro or o.primerApellido like :filtro or o.segundoApellido like :filtro</code> */
    public List<Usuario> getUsuarioFindByName(Usuario filtro){
     Query q = em.createNamedQuery("Usuario.findByName", Usuario.class);
     q.setParameter("filtro", "%"+filtro.getNombre()+"%");
     return q.getResultList();
    }
    
    /** <code>select o from Usuario o where o.nombreUsuario = :filtro</code> */
    public List<Usuario> getUsuarioFindByUsrName(String filtro){
     Query q = em.createNamedQuery("Usuario.findByUsrName", Usuario.class);
     q.setParameter("filtro", filtro);
     return q.getResultList();
    }

    public Telefono persistTelefono(Telefono telefono) {
        em.persist(telefono);
        return telefono;
    }

    public Telefono mergeTelefono(Telefono telefono) {
        return em.merge(telefono);
    }

    public void removeTelefono(Telefono telefono) {
        telefono = em.find(Telefono.class, telefono.getIdTelefono());
        em.remove(telefono);
    }

    /** <code>select o from Telefono o</code> */
    public List<Telefono> getTelefonoFindAll() {
        return em.createNamedQuery("Telefono.findAll", Telefono.class).getResultList();
    }

    public Permiso persistPermiso(Permiso permiso) {
        em.persist(permiso);
        return permiso;
    }

    public Permiso mergePermiso(Permiso permiso) {
        return em.merge(permiso);
    }

    public void removePermiso(Permiso permiso) {
        permiso = em.find(Permiso.class, permiso.getIdPermiso());
        em.remove(permiso);
    }

    /** <code>select o from Permiso o</code> */
    public List<Permiso> getPermisoFindAll() {
        return em.createNamedQuery("Permiso.findAll", Permiso.class).getResultList();
    }
    
    /** <code>select o from Permiso o where o.descripcion = :filtro</code> */
    public List<Permiso> getPermisoFindByDesc(String filtro) {
        return em.createNamedQuery("Permiso.findByDesc", Permiso.class).setParameter("filtro", filtro).getResultList();
    }

    public Paciente persistPaciente(Paciente paciente) {
        em.persist(paciente);
        return paciente;
    }

    public Paciente mergePaciente(Paciente paciente) {
        return em.merge(paciente);
    }

    public void removePaciente(Paciente paciente) {
        paciente = em.find(Paciente.class, paciente.getIdPaciente());
        em.remove(paciente);
    }

    /** <code>select o from Paciente o</code> */
    public List<Paciente> getPacienteFindAll() {
        return em.createNamedQuery("Paciente.findAll", Paciente.class).getResultList();
    }

    /** <code>select o from Paciente o where o.nombreProcedimiento like :filtro or o.primerApellido like :filtro or o.segundoApellido like :filtro</code> */
    public List<Paciente> getPacienteFindByName(Paciente filtro){
        Query q = em.createNamedQuery("Paciente.findByName", Paciente.class);
        q.setParameter("filtro", "%"+filtro.getNombrePaciente()+"%");
        return q.getResultList();
    }

    public Bitacoraprocedimiento persistBitacoraprocedimiento(Bitacoraprocedimiento bitacoraprocedimiento) {
        em.persist(bitacoraprocedimiento);
        return bitacoraprocedimiento;
    }

    public Bitacoraprocedimiento mergeBitacoraprocedimiento(Bitacoraprocedimiento bitacoraprocedimiento) {
        return em.merge(bitacoraprocedimiento);
    }

    public void removeBitacoraprocedimiento(Bitacoraprocedimiento bitacoraprocedimiento) {
        bitacoraprocedimiento =
            em.find(Bitacoraprocedimiento.class, bitacoraprocedimiento.getIdBitacoraProcedimiento());
        em.remove(bitacoraprocedimiento);
    }

    /** <code>select o from Bitacoraprocedimiento o where o.habilitado = 1</code> */
    public List<Bitacoraprocedimiento> getBitacoraprocedimientoFindAll() {
        return em.createNamedQuery("Bitacoraprocedimiento.findAll", Bitacoraprocedimiento.class).getResultList();
    }

     /** <code>select o from Bitacoraprocedimiento o where o.cuentapaciente.idPaciente = :filtro</code> */
     public List<Bitacoraprocedimiento> getBitacoraprocedimientoFindByPaciente(Integer filtro) {
         return em.createNamedQuery("Bitacoraprocedimiento.findByPaciente",
                                    Bitacoraprocedimiento.class).setParameter("filtro", filtro).getResultList();
     }

    public Encargadofamilia persistEncargadofamilia(Encargadofamilia encargadofamilia) {
        em.persist(encargadofamilia);
        return encargadofamilia;
    }

    public Encargadofamilia mergeEncargadofamilia(Encargadofamilia encargadofamilia) {
        return em.merge(encargadofamilia);
    }

    public void removeEncargadofamilia(Encargadofamilia encargadofamilia) {
        encargadofamilia = em.find(Encargadofamilia.class, encargadofamilia.getIdEncargadoFamilia());
        em.remove(encargadofamilia);
    }

    /** <code>select o from Encargadofamilia o</code> */
    public List<Encargadofamilia> getEncargadofamiliaFindAll() {
        return em.createNamedQuery("Encargadofamilia.findAll", Encargadofamilia.class).getResultList();
    }

    public Rolpermiso persistRolpermiso(Rolpermiso rolpermiso) {
        em.persist(rolpermiso);
        return rolpermiso;
    }

    public Rolpermiso mergeRolpermiso(Rolpermiso rolpermiso) {
        return em.merge(rolpermiso);
    }

    public void removeRolpermiso(Rolpermiso rolpermiso) {
        rolpermiso = em.find(Rolpermiso.class, rolpermiso.getIdRolPermiso());
        em.remove(rolpermiso);
    }

    /** <code>select o from Rolpermiso o</code> */
    public List<Rolpermiso> getRolpermisoFindAll() {
        return em.createNamedQuery("Rolpermiso.findAll", Rolpermiso.class).getResultList();
    }

    /** <code>select o from Rolpermiso o where o.rol.idRol = :filtro</code> */
    public List<Rolpermiso> getRolpermisoFindByRol(Rol filtro) {
        Query q = em.createNamedQuery("Rolpermiso.findByRol", Rolpermiso.class);
        q.setParameter("filtro", filtro.getIdRol());
        return q.getResultList();
    }

    public Cita persistCita(Cita cita) {
        em.persist(cita);
        return cita;
    }

    public Cita mergeCita(Cita cita) {
        return em.merge(cita);
    }

    public void removeCita(Cita cita) {
        cita = em.find(Cita.class, cita.getIdCita());
        em.remove(cita);
    }

    /** <code>select o from Cita o</code> */
    public List<Cita> getCitaFindAll() {
        return em.createNamedQuery("Cita.findAll", Cita.class).getResultList();
    }

    /** <code>select o from Cita o</code> */
    public List<Cita> getCitaFindAllByTodo() {
        return em.createNamedQuery("Cita.findAllByTodo", Cita.class).getResultList();
    }

    /** <code>select o from Cita o where o.habilitado = 1 and idCita = :filtro</code> */
    public List<Cita> getCitaFindById(String filtro) {
        Query q = em.createNamedQuery("Cita.findById", Cita.class);
        q.setParameter("filtro", filtro);
        return q.getResultList();
    }

    /** <code>select o from Cita o where o.habilitado = 1 and o.usuario1.idUsuario = :filtro</code> */
    public List<Cita> getCitaFindByUsuario(Usuario filtro) {
        Query q = em.createNamedQuery("Cita.findByUsuario", Cita.class);
        q.setParameter("filtro", filtro.getIdUsuario());
        return q.getResultList();
    }

    public Movimiento persistMovimiento(Movimiento movimiento) {
        em.persist(movimiento);
        return movimiento;
    }

    public Movimiento mergeMovimiento(Movimiento movimiento) {
        return em.merge(movimiento);
    }

    public void removeMovimiento(Movimiento movimiento) {
        movimiento = em.find(Movimiento.class, movimiento.getIdMovimiento());
        em.remove(movimiento);
    }

    /** <code>select o from Movimiento o where o.habilitado = 1</code> */
    public List<Movimiento> getMovimientoFindAll() {
        return em.createNamedQuery("Movimiento.findAll", Movimiento.class).getResultList();
    }

     /** <code>select o from Procedimiento o where o.nombreProcedimiento like :filtro</code> */
     public List<Movimiento> getMovimientoFindByCuenta(Cuentapaciente filtro){
         Query q = em.createNamedQuery("Movimiento.findByCuenta", Movimiento.class);
         q.setParameter("filtro", filtro.getIdCuentaPaciente());
         return q.getResultList();
     }

    public Cuestionariosalud persistCuestionariosalud(Cuestionariosalud cuestionariosalud) {
        em.persist(cuestionariosalud);
        return cuestionariosalud;
    }

    public Cuestionariosalud mergeCuestionariosalud(Cuestionariosalud cuestionariosalud) {
        return em.merge(cuestionariosalud);
    }

    public void removeCuestionariosalud(Cuestionariosalud cuestionariosalud) {
        cuestionariosalud = em.find(Cuestionariosalud.class, cuestionariosalud.getIdCuestionarioSalud());
        em.remove(cuestionariosalud);
    }

    /** <code>select o from Cuestionariosalud o</code> */
    public List<Cuestionariosalud> getCuestionariosaludFindAll() {
        return em.createNamedQuery("Cuestionariosalud.findAll", Cuestionariosalud.class).getResultList();
    }

    public Telefonopaciente persistTelefonopaciente(Telefonopaciente telefonopaciente) {
        em.persist(telefonopaciente);
        return telefonopaciente;
    }

    public Telefonopaciente mergeTelefonopaciente(Telefonopaciente telefonopaciente) {
        return em.merge(telefonopaciente);
    }

    public void removeTelefonopaciente(Telefonopaciente telefonopaciente) {
        telefonopaciente = em.find(Telefonopaciente.class, telefonopaciente.getIdTelefonoPaciente());
        em.remove(telefonopaciente);
    }

    /** <code>select o from Telefonopaciente o</code> */
    public List<Telefonopaciente> getTelefonopacienteFindAll() {
        return em.createNamedQuery("Telefonopaciente.findAll", Telefonopaciente.class).getResultList();
    }

    public Lineadetalle persistLineadetalle(Lineadetalle lineadetalle) {
        em.persist(lineadetalle);
        return lineadetalle;
    }

    public Lineadetalle mergeLineadetalle(Lineadetalle lineadetalle) {
        return em.merge(lineadetalle);
    }

    public void removeLineadetalle(Lineadetalle lineadetalle) {
        lineadetalle = em.find(Lineadetalle.class, lineadetalle.getIdlineadetalle());
        em.remove(lineadetalle);
    }

    /** <code>select o from Lineadetalle o</code> */
    public List<Lineadetalle> getLineadetalleFindAll() {
        return em.createNamedQuery("Lineadetalle.findAll", Lineadetalle.class).getResultList();
    }

    /** <code>select o from Lineadetalle o where o.habilitado = 1 and (o.presupuesto.idPresupuesto = :filtro)</code> */
    public List<Lineadetalle> getLineadetalleFindByPresupuesto(Presupuesto filtro) {
        Query q = em.createNamedQuery("Lineadetalle.findByPresupuesto", Lineadetalle.class);
        q.setParameter("filtro", filtro.getIdPresupuesto());
        return q.getResultList();
    }

    public Cuentapaciente persistCuentapaciente(Cuentapaciente cuentapaciente) {
        em.persist(cuentapaciente);
        return cuentapaciente;
    }

    public Cuentapaciente mergeCuentapaciente(Cuentapaciente cuentapaciente) {
        return em.merge(cuentapaciente);
    }

    public void removeCuentapaciente(Cuentapaciente cuentapaciente) {
        cuentapaciente = em.find(Cuentapaciente.class, cuentapaciente.getIdCuentaPaciente());
        em.remove(cuentapaciente);
    }

    /** <code>select o from Cuentapaciente o</code> */
    public List<Cuentapaciente> getCuentapacienteFindAll() {
        return em.createNamedQuery("Cuentapaciente.findAll", Cuentapaciente.class).getResultList();
    }

    public Procedimiento persistProcedimiento(Procedimiento procedimiento) {
        em.persist(procedimiento);
        return procedimiento;
    }

    public Procedimiento mergeProcedimiento(Procedimiento procedimiento) {
        return em.merge(procedimiento);
    }

    public void removeProcedimiento(Procedimiento procedimiento) {
        procedimiento = em.find(Procedimiento.class, procedimiento.getIdProcedimiento());
        em.remove(procedimiento);
    }

    /** <code>select o from Procedimiento o</code> */
    public List<Procedimiento> getProcedimientoFindAll() {
        return em.createNamedQuery("Procedimiento.findAll", Procedimiento.class).getResultList();
    }

    /** <code>select o from Procedimiento o where o.nombreProcedimiento like :filtro</code> */
    public List<Procedimiento> getProcedimientoFindByName(Procedimiento filtro){
        Query q = em.createNamedQuery("Procedimiento.findByName", Procedimiento.class);
        q.setParameter("filtro", "%"+filtro.getNombreProcedimiento()+"%");
        return q.getResultList();
    }

    public Rol persistRol(Rol rol) {
        em.persist(rol);
        return rol;
    }

    public Rol mergeRol(Rol rol) {
        return em.merge(rol);
    }

    public void removeRol(Rol rol) {
        rol = em.find(Rol.class, rol.getIdRol());
        em.remove(rol);
    }

    /** <code>select o from Rol o</code> */
    public List<Rol> getRolFindAll() {
        return em.createNamedQuery("Rol.findAll", Rol.class).getResultList();
    }
}
