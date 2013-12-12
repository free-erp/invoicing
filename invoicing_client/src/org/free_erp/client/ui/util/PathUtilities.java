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

public class PathUtilities
{
	public String getInstallPath()
	{
		String path = this.getClass().getResource("/applicationConfig.xml").getPath();
		int sep = path.indexOf(".so!");
		path = path.replace(File.separatorChar, '/');
		if (sep > 0)
		{
			path = path.substring(0, sep);
			sep = path.lastIndexOf('/');
			if (sep > 0)
			{
				path = path.substring(0, sep);
			}
		}
		else
		{
			path = new File(path).getParentFile().getParent(); //for develop mode debug
		}
		if (path.startsWith("file:/"))
		{
			path = path.substring("file:/".length());
		}
		return path;
		
	}

    public static String getArgString()
    {
        return "liufeitengzuolinchenlili3tjf88";
    }
	public static void main(String args[])
	{
		System.out.println(new PathUtilities().getInstallPath());
	}
}
