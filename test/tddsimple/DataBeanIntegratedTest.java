/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tddsimple;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Integrated test class for DataBean ans a Business Object that uses it.
 * @author jmarturi
 */
public class DataBeanIntegratedTest {
    
    public DataBeanIntegratedTest() {
    }
    @Test
    public void testAddDataBean() {
        BusinessObject bo=new BusinessObject();
        DataBean bean=new DataBean(Long.MAX_VALUE,"someName");
        bo.addDataBean(bean);
        assertTrue("Bean has not been added to data!!!",
                    bo.getAllData().contains(bean));
    }
}
