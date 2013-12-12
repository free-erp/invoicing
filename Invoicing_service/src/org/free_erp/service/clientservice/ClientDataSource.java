package org.free_erp.service.clientservice;


import org.apache.commons.dbcp.BasicDataSource;

public class ClientDataSource extends BasicDataSource
{
    public ClientDataSource()
	{
		super();
        if (LocalServiceManager.getSystemType() == 2)
        {
            this.setDriverClassName("org.apache.derby.jdbc.ClientDriver");
            this.setPassword("liufeitengzuolinchenlili3tjf88");
            this.setUrl(LocalServiceManager.getDatabaseUrl());
            //this.setUrl("jdbc:derby://192.168.1.166:1527/erp");

        }
        else
        {
            this.setDriverClassName("org.apache.derby.jdbc.EmbeddedDriver");
            String dbPath = LocalServiceManager.getInstallPath()+ "/data;dataEncryption=true;bootPassword=bettyisverybeautiful";//Main.getInstallPath()+ "/db/erp";
            this.setUrl("jdbc:derby:" + dbPath);
            this.setUsername("APP");
            this.setPassword(LocalServiceManager.getArgString());
        }
        
        
        //this.setPassword("liufeitengzuolinchenlili3tjf88");
        //        this.setDriverClassName("org.apache.derby.jdbc.ClientDriver");
        //        this.setUrl("jdbc:derby://192.168.1.166:1527/erp");
        //
	}
}
