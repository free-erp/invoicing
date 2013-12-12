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

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ApplicationConfig
{
	private Properties properties;

	public ApplicationConfig()
	{
		SAXReader saxReader = new SAXReader();
		this.properties = new Properties();
		try
		{
			//URL filePath = this.getClass().getResource("/applicationConfig.xml");
			//System.out.println("value:" + filePath);//.getFile();
			Document usersXMLDoc = saxReader
					.read(this.getClass().getResourceAsStream("/applicationConfig.xml"));
			/*
							new FileInputStream(new File(getClass().getResource(name).getResource(
							"applicationConfig.xml").getPath())));
							*/
			Element rootElement = usersXMLDoc.getRootElement();
			Iterator subElements = rootElement.elementIterator();
			while (subElements.hasNext())
			{
				Element config = (Element) subElements.next();
				List configElements = config.elements();
				for (int i = 0; i < configElements.size(); i++)
				{
					Element ele = (Element) configElements.get(i);
					this.properties.setProperty(ele.getName(), ele.getText());
				}
			}
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public String getProperty(String key)
	{
		return this.properties.getProperty(key);
	}
}
