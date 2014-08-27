 /**
  * Clase heredera de CalendarProvider, utilizada para el componente calendario.
  * Representa el proveedor de la cita.
  * @author Roberto Quesada
  * @version 1.0, 26/01/2014
  */

package model;

import java.io.Serializable;

import oracle.adf.view.rich.model.CalendarProvider;

public class CalendarProviderClinica extends CalendarProvider  implements Serializable{

    private String _id = null;
    private String _displayName = null;
    private CalendarProvider.Enabled _enabled = CalendarProvider.Enabled.ENABLED;

    /**
     * Constructor de la clase.
     */
    public CalendarProviderClinica(){
        
    }

    /**
     * Constructor de la clase.
     * @param id Id del proveedor
     * @param displayName Nombre del proveedor
     */
    public CalendarProviderClinica(String id, String displayName){
        _id = id;
        _displayName = displayName;
    }
    
    @Override
    public String getDisplayName(){
        return _displayName;
    }
    
    @Override
    public String getId(){
        return _id;
    }
    
    @Override
    public CalendarProvider.Enabled getEnabled(){
        return _enabled;
    }
    
    @Override
    public void setEnabled(CalendarProvider.Enabled enabled){
        _enabled = enabled;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public void setDisplayName(String _displayName) {
        this._displayName = _displayName;
    }
}
