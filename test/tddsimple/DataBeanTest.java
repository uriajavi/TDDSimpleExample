/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tddsimple;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.io.Serializable;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for a simple bean TDD example.
 * @author jmarturi
 */
public class DataBeanTest{

    static Boolean EVENT_FIRED=false;
    
    @Test
    public void testBeanConstruction() throws Exception {
        DataBean bean=DataBean.class.getConstructor().newInstance();
        assertNotNull("The bean cannot be constructed!!!",
                      bean);
    }

    @Test
    public void testBeanIsSerializable() {
        DataBean bean=new DataBean();
        assertTrue("The bean is not Serializable!!!",
                    bean instanceof Serializable);
    }
    //Test getters/setters
    @Test
    public void testIdProperty() {
        DataBean bean=new DataBean();
        Long id=999999l;
        bean.setId(id);
        assertEquals("Id property is not accesible!!!",
                     id,bean.getId());
    }

    @Test
    public void testNameProperty() {
        DataBean bean=new DataBean();
        String name="some text";
        bean.setName(name);
        assertEquals("Name property is not accesible!!!",
                     name,bean.getName());
    }
    //Test public methods
    @Test
    public void testBeanToString(){
        DataBean bean=new DataBean();
        bean.setId(666l);
        bean.setName("someName");
        assertEquals("String representation is not as expected!!!",
                      "tddsimple.DataBean[id=666,name=someName]",
                      bean.toString());
    }
    @Test
    public void testBeanEquality(){
        DataBean bean=new DataBean();
        bean.setId(666l);
        bean.setName("someName");
        DataBean other=new DataBean();
        other.setId(666l);
        other.setName("someName");
        assertEquals("Beans are not equal!!!",
                      bean,other);
    }
    @Test
    public void testBeanInEquality(){
        DataBean bean=new DataBean();
        bean.setId(666l);
        bean.setName("someName");
        DataBean other=new DataBean();
        other.setId(99l);
        other.setName("someName");
        assertNotEquals("Beans are equal!!!",
                      bean,other);
        other.setId(666l);
        other.setName("otherName");
        assertNotEquals("Beans are equal!!!",
                      bean,other);
    }
    @Test
    public void testBeanHashCode(){
        DataBean bean=new DataBean();
        assertEquals("hashCode are not as expected!!!",0,bean.hashCode());
        Long id=666l;
        bean.setId(id);
        assertEquals("hashCode are not as expected!!!",
                     id.hashCode(),bean.hashCode());
        
    }
    //Test events
    @Test
    public void testPropertyChange(){
        EVENT_FIRED=false;
        DataBean bean=new DataBean();
        bean.addPropertyChangeListener((PropertyChangeEvent evt) -> {
            EVENT_FIRED=true;
        });
        bean.setId(Long.MAX_VALUE);
        assertTrue("Event not fired for id property!!!",EVENT_FIRED);
        EVENT_FIRED=false;
        bean.setName("newName");
        assertTrue("Event not fired for name property!!!",EVENT_FIRED);
    }
    @Test
    public void testVetoablePropertyChange(){
        DataBean bean=new DataBean(99l,"anyName");
        bean.addVetoableChangeListener((PropertyChangeEvent evt) -> {
            if(evt.getPropertyName().equals("id"))
                throw new PropertyVetoException("Change not allowed",evt);
        });
        bean.setName("newName");
        assertEquals("Name not changed!!!","newName",bean.getName());
        bean.setId(Long.MAX_VALUE);
        assertNotEquals("Id change not vetoed!!!"
                ,(Long)Long.MAX_VALUE, bean.getId());
    }
    
}
