package javaconsole;

import java.io.IOException;
import java.net.MalformedURLException;
import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.IntrospectionException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.remote.JMXConnector;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class JavaConsole {

    public static void main(String[] args) throws MalformedURLException, IOException, MalformedObjectNameException,
            MBeanException, AttributeNotFoundException, InstanceNotFoundException, ReflectionException, IntrospectionException {
        String host = "localhost";
        int port = 7199;
        String url = "service:jmx:rmi:///jndi/rmi://" + host + ":" + port + "/jmxrmi";
        JMXServiceURL serviceUrl = new JMXServiceURL(url);
        JMXConnector jmxConnector = JMXConnectorFactory.connect(serviceUrl, null);
        try {

            String input = "org.apache.cassandra.metrics:type=CommitLog,name=CompletedTasks";
            MBeanServerConnection mbeanConn = jmxConnector.getMBeanServerConnection();
            ObjectName objnm = ObjectName.getInstance(input);

            MBeanInfo minfo = mbeanConn.getMBeanInfo(objnm);
            MBeanAttributeInfo[] mattrlist = minfo.getAttributes();

            for (MBeanAttributeInfo x : mattrlist) {
                String atnm = x.getName();
                Object val = null;
                try {
                    val = mbeanConn.getAttribute(objnm, atnm);
                } catch (Exception e) {
                    System.out.println(e);
                }
                System.out.println(atnm + " = " + val);
            }

        } catch (Exception e) {
            System.out.println("Mbean not found expec" + e.getMessage());
        } finally {
            jmxConnector.close();
        }
    }
}
