/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tddsimple;

import java.io.Serializable;

/**
 *
 * @author jmarturi
 */
class DataBean implements Serializable {

    private Long id;
    private String name;
    
    public DataBean(){
        
    }
            
            
    void setId(Long id) {
        this.id=id;
    }

    Long getId() {
        return id;
    }

    void setName(String name) {
        this.name=name;
    }

    String getName() {
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
    
}
