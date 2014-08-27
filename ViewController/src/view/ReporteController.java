package view;

import java.io.Serializable;

import java.util.List;

import model.JavaServiceFacade;
import model.Paciente;

 /**
  * Clase (Managed Bean) generada para el control de los reportes.
  * @deprecated No se recomienda su uso. Clase sin completar.
  * @version 1.0, 26/01/2014
  * @author Roberto Quesada
  */
public class ReporteController implements Serializable {
    
    private JavaServiceFacade service;
    private List<Paciente> listaPacientes;
    
    public ReporteController() {
        setService(new JavaServiceFacade());
        setListaPacientes(service.getPacienteFindAll());
    }

    public void setListaPacientes(List<Paciente> listaPacientes) {
        this.listaPacientes = listaPacientes;
    }

    public List<Paciente> getListaPacientes() {
        return listaPacientes;
    }

    public void setService(JavaServiceFacade service) {
        this.service = service;
    }

    public JavaServiceFacade getService() {
        return service;
    }
}
