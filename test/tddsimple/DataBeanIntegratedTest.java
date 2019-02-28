/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tddsimple;

import java.beans.PropertyVetoException;
import java.util.List;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 * Integrated test class for DataBean ans a Business Object that uses it.
 * @author jmarturi
 */
public class DataBeanIntegratedTest {
    
    private static BusinessObject bo;
    private static Random randomIdGenerator;
    
    @BeforeClass
    public static void setUpData(){
        bo=new BusinessObject();
        for(int i=0;i<10;i++)
            bo.addDataBean(new DataBean(i,"name"+1));
        randomIdGenerator=new Random();
    }
    @Test
    public void testGetAllData(){
        List data=bo.getAllData();
        assertNotNull("Data is null!!",data);
        assertNotEquals("There is no data!!!",0,data.size());
    }
    @Test
    public void testAddDataBean() {
        DataBean bean=new DataBean(randomIdGenerator.longs().findAny().getAsLong(),
                                    "someName");
        bo.addDataBean(bean);
        assertTrue("Bean has not been added to data!!!",
                    bo.getAllData().contains(bean));
    }
    @Test
    public void testDeleteDataBean() {
        DataBean bean=new DataBean(randomIdGenerator.longs().findAny().getAsLong(),
                                    "someName");
        bo.addDataBean(bean);
        assertTrue("Bean has not been added to data!!!",
                    bo.getAllData().contains(bean));
        bo.deleteDataBean(bean);
        assertFalse("Bean has not been deleted from data!!!",
                    bo.getAllData().contains(bean));
        
    }
    @Test
    public void testGetDataBeanById(){
        Long id=randomIdGenerator.longs().findAny().getAsLong();
        DataBean bean=new DataBean(id,"someName");
        bo.addDataBean(bean);
        assertTrue("Bean has not been added to data!!!",
                    bo.getAllData().contains(bean));
        assertEquals("Bean with id="+id+" not found!!!",
                    bean,bo.getDataBeanById(id));
        assertNull("Bean with id=-1 found!!!",
                    bo.getDataBeanById(-1l));
    }
    @Test
    public void testUpdateDataBean() throws PropertyVetoException{
        Long id=randomIdGenerator.longs().findAny().getAsLong();
        DataBean bean=new DataBean(id,"someName");
        bo.addDataBean(bean);
        assertTrue("Bean has not been added to data!!!",
                    bo.getAllData().contains(bean));
        Long updatedId=randomIdGenerator.longs().findAny().getAsLong();
        DataBean updatedBean=new DataBean(updatedId,"otherName");
        bo.updateDataBean(id,updatedBean);
        assertEquals("Updated bean with id="+updatedId+" not found!!!",
                    updatedBean,bo.getDataBeanById(updatedId));
        assertNull("Bean with id="+id+"found!!!",bo.getDataBeanById(id));
    }

}
