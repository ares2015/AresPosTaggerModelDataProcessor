package spring;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertTrue;

/**
 * Created by Oliver on 8/2/2016.
 */
public class SpringConfigurationTest {

    @Test
    public void testConfiguration(){
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        assertTrue(context != null);
    }
}
