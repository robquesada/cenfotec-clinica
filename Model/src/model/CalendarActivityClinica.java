 /**
  * Clase heredera de CalendarActivity, utilizada para el componente calendario.
  * Representa una cita en el calendario.
  * @author Roberto Quesada
  * @version 1.0, 26/01/2014
  */

package model;

import java.io.Serializable;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import oracle.adf.view.rich.model.CalendarActivity;
import oracle.adf.view.rich.model.CalendarProvider;

public class CalendarActivityClinica extends CalendarActivity implements Serializable{

    private String _id;
    private String _title;
    private Date _startDate;
    private Date _endDate;
    private CalendarProvider _provider;
    private TimeType _timeType;
    
    private Usuario usuario;
    private Paciente paciente;

    /**
     * Constructor de la clase.
     */
    public CalendarActivityClinica(){
    }

    /**
     * Constructor de la clase.
     * @param id Id de la cita
     * @param title Descripción de la cita
     * @param start Fecha de inicio de la cita
     * @param end Fecha final de la cita
     * @param tt Tipo de tiempo
     * @param prov Proveedor de la cita
     */
    public CalendarActivityClinica(String id, String title, Date start, Date end, TimeType tt, CalendarProvider prov){
        _id = id;
        _startDate = start;
        _endDate = end;
        _title = title;
        _provider = prov;
        _timeType = tt;
    }
    
    @Override
    public String getId() {
        return _id;
    }

    @Override
    public Date getStartDate(TimeZone timeZone) {
        return _startDate;
    }

    @Override
    public Date getEndDate(TimeZone timeZone) {
        return _endDate;
    }

    public void setProvider(CalendarProvider provider) {
        this._provider = provider;
    }
    
    @Override
    public CalendarProvider getProvider() {
        return _provider;
    }
    
    @Override
    public String getTitle() {
        return _title;
    }
    
    @Override
    public CalendarActivity.TimeType getTimeType() {
        return _timeType;
    }
    
    @Override
    public void setTimeType(CalendarActivity.TimeType _timeType) {
        this._timeType = _timeType;
    }
    
    public void setId(String _id) {
        this._id = _id;
    }

    public void setTitle(String _title) {
        this._title = _title;
    }

    public void setStartDate(Date _startDate) {
        this._startDate = _startDate;
    }

    public void setEndDate(Date _endDate) {
        this._endDate = _endDate;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Paciente getPaciente() {
        return paciente;
    }
}
