/*
 * Copyright 2013, TengJianfa , and other individual contributors as indicated
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.free_erp.client.ui.util;

import org.free_erp.client.ui.main.Main;
import org.free_erp.service.clientservice.LocalServiceManager;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 *
 * @author afa
 */
public class SystemConfig 
{
    private String userName;
    private String companyId;
    private File file;
    private SAXReader saxReader;

    public SystemConfig()
    {
        file = new File(LocalServiceManager.getInstallPath() + "/config.xml");
        saxReader = new SAXReader();
       
    }
    public void initSystemConfig()
    {
        if (file.exists())
        {
            try
            {
			Document usersXMLDoc = saxReader.read(file);
			Element rootElement = usersXMLDoc.getRootElement();
           	Iterator subElements = rootElement.elementIterator();
			while (subElements.hasNext())
			{
				Element ele = (Element) subElements.next();
                if (ele.getName().equals("login-company_id"))
                {
                    companyId = ele.getText();
                }
                else if (ele.getName().equals("login-user"))
                {
                    userName = ele.getText();
                }
                else if (ele.getName().equals("log-update-time"))
                {
                    try
                    {
                        int time = Integer.parseInt(ele.getText());
                        if (time < 3000)
                        {
                            time = 3000;
                        }
                        Main.setLogUpdateTime(time);
                    }
                    catch(Exception ex)
                    {
                        ex.printStackTrace();
                    }
                }
                
			}
            
		}   catch (Exception ex)
            {
			ex.printStackTrace();
            }            
        }
    }

    public String getOldCompanyId()
    {
        return this.companyId;
    }

    public String getOldUserName()
    {
         return this.userName;
    }

    public void saveSystemConfig()
    {
        userName = Main.getMainFrame().getUser().getUserName();
        companyId = String.valueOf(Main.getMainFrame().getCompany().getId());
        //other options

        OutputFormat format = OutputFormat.createPrettyPrint(); //设置XML文档输出格式
        format.setEncoding("UTF-8"); //设置XML文档的编码类型
        format.setSuppressDeclaration(true);
        format.setIndent(true); //设置是否缩进
        format.setNewlines(true); //设置是否换行
        try
        {
            if (file.exists())
            {
                file.delete();
            }
            XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(file),format);
            Document document = DocumentHelper.createDocument();
            Element root = document.addElement("config");
            root.addElement("login-company_id").addText(companyId);
            root.addElement("login-user").addText(userName);
            root.addElement("log-update-time").addText(String.valueOf(Main.getLogUpdateTime()));
            xmlWriter.write(document);
            xmlWriter.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
