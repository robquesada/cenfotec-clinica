 /**
  * Clase heredera de CalendarModel, utilizada para el componente calendario.
  * Contiene las citas que se muestran en el calendario.
  * @author Roberto Quesada
  * @version 1.0, 26/01/2014
  */

package model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

import oracle.adf.view.rich.model.CalendarActivity;
import oracle.adf.view.rich.model.CalendarModel;
import oracle.adf.view.rich.model.CalendarProvider;

public class CalendarModelClinica extends CalendarModel implements Serializable{

    private List<CalendarActivity> listaActividades;

    /**
     * Constructor de la clase.
     */
    public CalendarModelClinica(){
        setListaActividades(new ArrayList());
    }

    /**
     * Constructor de la clase.
     * @param lista Lista de citas asociadas al modelo
     */
    public CalendarModelClinica(List<CalendarActivity> lista){
        setListaActividades(lista);
    }
    
    @Override
    public List<CalendarActivity> getTimeActivities(Date rangeStart, Date rangeEnd, TimeZone timeZone) {
        
        List<CalendarActivity> listaActividadesTime = new ArrayList();
        
        for(CalendarActivity ca : listaActividades){
            if(ca.getTimeType() == CalendarActivity.TimeType.TIME){
                listaActividadesTime.add(ca);
            }
        }
        
        return listaActividadesTime;
    }

    @Override
    public List<CalendarActivity> getAllDayActivities(Date rangeStart, Date rangeEnd, TimeZone timeZone) {
        List<CalendarActivity> listaActividadesAllDay = new ArrayList();
        
        for(CalendarActivity ca : listaActividades){
            if(ca.getTimeType() == CalendarActivity.TimeType.ALLDAY){
                listaActividadesAllDay.add(ca);   
            }
        }
        
        return listaActividadesAllDay;
    }

    @Override
    public CalendarActivity getActivity(String providerId, String activityId, Date rangeStart, Date rangeEnd, TimeZone timeZone) {
        for(CalendarActivity ca : listaActividades){
            System.out.println("ssssssstart "+ca.getStartDate(null));
            System.out.println("eeeeeeeeend "+ca.getEndDate(null));
            if(ca.getId().equals(activityId) &&
               ca.getProvider().getId().equals(providerId)){
                return ca;
            }
        }
        return null;
    }

    public void setListaActividades(List<CalendarActivity> listaActividades) {
        this.listaActividades = listaActividades;
    }

    public List<CalendarActivity> getListaActividades() {
        return listaActividades;
    }
}
