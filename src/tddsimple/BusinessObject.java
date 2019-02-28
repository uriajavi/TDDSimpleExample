/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tddsimple;

import java.beans.PropertyVetoException;
import java.util.List;
import java.util.Vector;

/**
 * Class for showing a simple business object developed aplying TDD.
 * @author jmarturi
 */
public class BusinessObject {
    
    private final Vector<DataBean> data;
    
    @SuppressWarnings("UseOfObsoleteCollectionType")
    public BusinessObject(){
        data=new Vector();
    }

    public void addDataBean(DataBean bean) {
        if(data!=null) data.add(bean);
    }

    public List getAllData() {
        return data;
    }

    public void deleteDataBean(DataBean bean) {
        if(data!=null) data.remove(bean);
    }

    public DataBean getDataBeanById(Long id) {
        DataBean bean;
        bean=data.stream()
                 .filter(it->it.getId().equals(id)).findFirst()
                 .orElse(null);
        return bean;    
    }

    public void updateDataBean(Long id, DataBean updatedBean) throws PropertyVetoException {
        DataBean bean;
        bean=data.stream()
                 .filter(it->it.getId().equals(id)).findFirst()
                 .orElse(null);
        if(bean!=null){
            bean.setId(updatedBean.getId());
            bean.setName(updatedBean.getName());
        }
    }
    
}
