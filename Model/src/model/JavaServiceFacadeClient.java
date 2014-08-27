package model;

import java.util.List;

public class JavaServiceFacadeClient {
    public static void main(String[] args) {
        try {
            final JavaServiceFacade javaServiceFacade = new JavaServiceFacade();
            for (Presupuesto presupuesto : (List<Presupuesto>) javaServiceFacade.getPresupuestoFindAll()) {
                printPresupuesto(presupuesto);
            }
            for (Usuario usuario : (List<Usuario>) javaServiceFacade.getUsuarioFindAll()) {
                printUsuario(usuario);
            }
            for (Telefono telefono : (List<Telefono>) javaServiceFacade.getTelefonoFindAll()) {
                printTelefono(telefono);
            }
            for (Permiso permiso : (List<Permiso>) javaServiceFacade.getPermisoFindAll()) {
                printPermiso(permiso);
            }
            for (Paciente paciente : (List<Paciente>) javaServiceFacade.getPacienteFindAll()) {
                printPaciente(paciente);
            }
            for (Bitacoraprocedimiento bitacoraprocedimiento :
                 (List<Bitacoraprocedimiento>) javaServiceFacade.getBitacoraprocedimientoFindAll()) {
                printBitacoraprocedimiento(bitacoraprocedimiento);
            }
            for (Encargadofamilia encargadofamilia :
                 (List<Encargadofamilia>) javaServiceFacade.getEncargadofamiliaFindAll()) {
                printEncargadofamilia(encargadofamilia);
            }
            for (Rolpermiso rolpermiso : (List<Rolpermiso>) javaServiceFacade.getRolpermisoFindAll()) {
                printRolpermiso(rolpermiso);
            }
            for (Cita cita : (List<Cita>) javaServiceFacade.getCitaFindAll()) {
                printCita(cita);
            }
            for (Movimiento movimiento : (List<Movimiento>) javaServiceFacade.getMovimientoFindAll()) {
                printMovimiento(movimiento);
            }
            for (Cuestionariosalud cuestionariosalud :
                 (List<Cuestionariosalud>) javaServiceFacade.getCuestionariosaludFindAll()) {
                printCuestionariosalud(cuestionariosalud);
            }
            for (Telefonopaciente telefonopaciente :
                 (List<Telefonopaciente>) javaServiceFacade.getTelefonopacienteFindAll()) {
                printTelefonopaciente(telefonopaciente);
            }
            for (Lineadetalle lineadetalle : (List<Lineadetalle>) javaServiceFacade.getLineadetalleFindAll()) {
                printLineadetalle(lineadetalle);
            }
            for (Cuentapaciente cuentapaciente : (List<Cuentapaciente>) javaServiceFacade.getCuentapacienteFindAll()) {
                printCuentapaciente(cuentapaciente);
            }
            for (Procedimiento procedimiento : (List<Procedimiento>) javaServiceFacade.getProcedimientoFindAll()) {
                printProcedimiento(procedimiento);
            }
            for (Rol rol : (List<Rol>) javaServiceFacade.getRolFindAll()) {
                printRol(rol);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void printPresupuesto(Presupuesto presupuesto) {
        System.out.println("fechaCreacion = " + presupuesto.getFechaCreacion());
        System.out.println("habilitado = " + presupuesto.getHabilitado());
        System.out.println("idPresupuesto = " + presupuesto.getIdPresupuesto());
        System.out.println("monto = " + presupuesto.getMonto());
        System.out.println("usuario = " + presupuesto.getUsuario());
        System.out.println("lineadetalleList1 = " + presupuesto.getLineadetalleList1());
        System.out.println("cuentapaciente4 = " + presupuesto.getCuentapaciente4());
    }

    private static void printUsuario(Usuario usuario) {
        System.out.println("contrasenna = " + usuario.getContrasenna());
        System.out.println("correoElectronico = " + usuario.getCorreoElectronico());
        System.out.println("especialidad = " + usuario.getEspecialidad());
        System.out.println("habilitado = " + usuario.getHabilitado());
        System.out.println("idUsuario = " + usuario.getIdUsuario());
        System.out.println("nombre = " + usuario.getNombre());
        System.out.println("nombreUsuario = " + usuario.getNombreUsuario());
        System.out.println("primerApellido = " + usuario.getPrimerApellido());
        System.out.println("segundoApellido = " + usuario.getSegundoApellido());
        System.out.println("presupuestoList = " + usuario.getPresupuestoList());
        System.out.println("citaList = " + usuario.getCitaList());
        System.out.println("rol1 = " + usuario.getRol1());
        System.out.println("bitacoraprocedimientoList2 = " + usuario.getBitacoraprocedimientoList2());
    }

    private static void printTelefono(Telefono telefono) {
        System.out.println("habilitado = " + telefono.getHabilitado());
        System.out.println("idTelefono = " + telefono.getIdTelefono());
        System.out.println("numeroTelefono = " + telefono.getNumeroTelefono());
        System.out.println("telefonopacienteList1 = " + telefono.getTelefonopacienteList1());
    }

    private static void printPermiso(Permiso permiso) {
        System.out.println("descripcion = " + permiso.getDescripcion());
        System.out.println("idPermiso = " + permiso.getIdPermiso());
        System.out.println("nombrePermiso = " + permiso.getNombrePermiso());
        System.out.println("rolpermisoList1 = " + permiso.getRolpermisoList1());
    }

    private static void printPaciente(Paciente paciente) {
        System.out.println("comentario = " + paciente.getComentario());
        System.out.println("correoElectronico = " + paciente.getCorreoElectronico());
        System.out.println("fechaIngreso = " + paciente.getFechaIngreso());
        System.out.println("fechaNacimiento = " + paciente.getFechaNacimiento());
        System.out.println("habilitado = " + paciente.getHabilitado());
        System.out.println("idPaciente = " + paciente.getIdPaciente());
        System.out.println("identificacionPaciente = " + paciente.getIdentificacionPaciente());
        System.out.println("lugarResidencia = " + paciente.getLugarResidencia());
        System.out.println("nombrePaciente = " + paciente.getNombrePaciente());
        System.out.println("numeroExpediente = " + paciente.getNumeroExpediente());
        System.out.println("porcentajeDescuento = " + paciente.getPorcentajeDescuento());
        System.out.println("primerApellido = " + paciente.getPrimerApellido());
        System.out.println("segundoApellido = " + paciente.getSegundoApellido());
        System.out.println("sexo = " + paciente.getSexo());
        System.out.println("bitacoraprocedimientoList1 = " + paciente.getBitacoraprocedimientoList1());
        System.out.println("cuentapaciente = " + paciente.getCuentapaciente());
        System.out.println("encargadofamiliaList = " + paciente.getEncargadofamiliaList());
        System.out.println("telefonopacienteList = " + paciente.getTelefonopacienteList());
        System.out.println("citaList1 = " + paciente.getCitaList1());
        System.out.println("presupuestoList1 = " + paciente.getPresupuestoList1());
        System.out.println("cuestionariosalud = " + paciente.getCuestionariosalud());
    }

    private static void printBitacoraprocedimiento(Bitacoraprocedimiento bitacoraprocedimiento) {
        System.out.println("caraDiente = " + bitacoraprocedimiento.getCaraDiente());
        System.out.println("estado = " + bitacoraprocedimiento.getEstado());
        System.out.println("fecha = " + bitacoraprocedimiento.getFecha());
        System.out.println("habilitado = " + bitacoraprocedimiento.getHabilitado());
        System.out.println("idBitacoraProcedimiento = " + bitacoraprocedimiento.getIdBitacoraProcedimiento());
        System.out.println("numeroDiente = " + bitacoraprocedimiento.getNumeroDiente());
        System.out.println("bitacoraprocedimiento = " + bitacoraprocedimiento.getBitacoraprocedimiento());
        System.out.println("cuentapaciente = " + bitacoraprocedimiento.getCuentapaciente());
        System.out.println("usuario2 = " + bitacoraprocedimiento.getUsuario2());
    }

    private static void printEncargadofamilia(Encargadofamilia encargadofamilia) {
        System.out.println("habilitado = " + encargadofamilia.getHabilitado());
        System.out.println("idEncargadoFamilia = " + encargadofamilia.getIdEncargadoFamilia());
        System.out.println("nombreFamiliar = " + encargadofamilia.getNombreFamiliar());
        System.out.println("parentezco = " + encargadofamilia.getParentezco());
        System.out.println("telefonoFamiliar = " + encargadofamilia.getTelefonoFamiliar());
        System.out.println("cuentapaciente1 = " + encargadofamilia.getCuentapaciente1());
    }

    private static void printRolpermiso(Rolpermiso rolpermiso) {
        System.out.println("habiitado = " + rolpermiso.getHabiitado());
        System.out.println("idRolPermiso = " + rolpermiso.getIdRolPermiso());
        System.out.println("rol = " + rolpermiso.getRol());
        System.out.println("permiso = " + rolpermiso.getPermiso());
    }

    private static void printCita(Cita cita) {
        System.out.println("proveedor = " + cita.getProveedor());
        System.out.println("descripcion = " + cita.getDescripcion());
        System.out.println("estado = " + cita.getEstado());
        System.out.println("fechaFin = " + cita.getFechaFin());
        System.out.println("fechaInicio = " + cita.getFechaInicio());
        System.out.println("habilitado = " + cita.getHabilitado());
        System.out.println("idCita = " + cita.getIdCita());
        System.out.println("usuario1 = " + cita.getUsuario1());
        System.out.println("cuentapaciente3 = " + cita.getCuentapaciente3());
    }

    private static void printMovimiento(Movimiento movimiento) {
        System.out.println("fechaMovimiento = " + movimiento.getFechaMovimiento());
        System.out.println("habilitado = " + movimiento.getHabilitado());
        System.out.println("idMovimiento = " + movimiento.getIdMovimiento());
        System.out.println("monto = " + movimiento.getMonto());
        System.out.println("nombreMovimiento = " + movimiento.getNombreMovimiento());
        System.out.println("cuentapaciente1 = " + movimiento.getCuentapaciente1());
    }

    private static void printCuestionariosalud(Cuestionariosalud cuestionariosalud) {
        System.out.println("cuestionario = " + cuestionariosalud.getCuestionario());
        System.out.println("idCuestionarioSalud = " + cuestionariosalud.getIdCuestionarioSalud());
        System.out.println("cuentapacienteList1 = " + cuestionariosalud.getCuentapacienteList1());
    }

    private static void printTelefonopaciente(Telefonopaciente telefonopaciente) {
        System.out.println("idTelefonoPaciente = " + telefonopaciente.getIdTelefonoPaciente());
        System.out.println("telefono = " + telefonopaciente.getTelefono());
        System.out.println("cuentapaciente2 = " + telefonopaciente.getCuentapaciente2());
    }

    private static void printLineadetalle(Lineadetalle lineadetalle) {
        System.out.println("caraDiente = " + lineadetalle.getCaraDiente());
        System.out.println("estadoProcedimiento = " + lineadetalle.getEstadoProcedimiento());
        System.out.println("habilitado = " + lineadetalle.getHabilitado());
        System.out.println("idlineadetalle = " + lineadetalle.getIdlineadetalle());
        System.out.println("numeroDiente = " + lineadetalle.getNumeroDiente());
        System.out.println("bitacoraprocedimiento1 = " + lineadetalle.getBitacoraprocedimiento1());
        System.out.println("presupuesto = " + lineadetalle.getPresupuesto());
    }

    private static void printCuentapaciente(Cuentapaciente cuentapaciente) {
        System.out.println("codigo = " + cuentapaciente.getCodigo());
        System.out.println("idCuentaPaciente = " + cuentapaciente.getIdCuentaPaciente());
        System.out.println("saldo = " + cuentapaciente.getSaldo());
        System.out.println("cuentapacienteList = " + cuentapaciente.getCuentapacienteList());
        System.out.println("movimientoList = " + cuentapaciente.getMovimientoList());
    }

    private static void printProcedimiento(Procedimiento procedimiento) {
        System.out.println("habilitado = " + procedimiento.getHabilitado());
        System.out.println("idProcedimiento = " + procedimiento.getIdProcedimiento());
        System.out.println("nombreProcedimiento = " + procedimiento.getNombreProcedimiento());
        System.out.println("precio = " + procedimiento.getPrecio());
        System.out.println("bitacoraprocedimientoList = " + procedimiento.getBitacoraprocedimientoList());
        System.out.println("lineadetalleList = " + procedimiento.getLineadetalleList());
    }

    private static void printRol(Rol rol) {
        System.out.println("habilitado = " + rol.getHabilitado());
        System.out.println("idRol = " + rol.getIdRol());
        System.out.println("nombreRol = " + rol.getNombreRol());
        System.out.println("rolpermisoList = " + rol.getRolpermisoList());
        System.out.println("usuarioList = " + rol.getUsuarioList());
    }
}
