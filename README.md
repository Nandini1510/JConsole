**Cassandra Server Performance Monitoring using Java Management Extension (JMX) and Jconsole.**  
JMX is a lightweight way to manage any resource such as hardware, networks, storage, operating systems, middleware, and applications.  
You take a resourse and expose the functionality you want to manage using a MBean.  
We get a connection to MbeanServer using rmiconnector and JMXServiceURL.  
After MbeanServer is connected with this reference we access the Mbeans.   
By default all the attributes and values of Mbeans in ‘keyspace=system,columnfamily=IndexInfo’ are listed in the output.  
On the other hand user can input the Mbean name to get the attribute and values list.  





