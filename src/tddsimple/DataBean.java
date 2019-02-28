/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tddsimple;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import java.io.Serializable;

/**
 *
 * @author jmarturi
 */
public class DataBean implements Serializable {

    private Long id;
    private String name;
    private PropertyChangeSupport changes;
    private VetoableChangeSupport vetos;
    
    public DataBean(){
        changes =new PropertyChangeSupport(this);
        vetos =new VetoableChangeSupport(this);
    }

    public DataBean(long id, String name) {
        changes =new PropertyChangeSupport(this);
        vetos =new VetoableChangeSupport(this);
        this.id=id;
        this.name=name;
    }
           
    public void setId(Long id) throws PropertyVetoException {
        vetos.fireVetoableChange("id", this.id, id);
        changes.firePropertyChange("id", this.id, id);
        this.id=id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        changes.firePropertyChange("name",this.name,name);
        this.name=name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "tddsimple.DataBean[id=" + id + ",name="+ name +"]";
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof DataBean)) {
            return false;
        }
        DataBean other = (DataBean) object;
        if ((this.id == null && other.id != null) || 
            (this.id != null && !this.id.equals(other.id))||
            (this.name == null && other.name != null) || 
            (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener) {
        changes.addPropertyChangeListener(propertyChangeListener);
    }

    public void addVetoableChangeListener(VetoableChangeListener vetoableChangeListener) {
        vetos.addVetoableChangeListener(vetoableChangeListener);
    }
    
}
