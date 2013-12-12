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

import java.security.MessageDigest;

/**
 *
 * @author TengJianfa mobile:086-13003311398 qq:575633370 www.free-erp.com
 */
public class Md51
{
    public static String byteArrayToHexString(byte abyte0[])
    {
        StringBuffer stringbuffer = new StringBuffer();
        for(int i = 0; i < abyte0.length; i++)
            stringbuffer.append(byteToHexString(abyte0[i]));

        return stringbuffer.toString();
    }

    private static String byteToHexString(byte byte0)
    {
        int i = byte0;
        if(i < 0)
            i = 256 + i;
        int j = i / 16;
        int k = i % 16;
        return hexDigits[j] + hexDigits[k];
    }

    public static String md5Encode(String s)
    {
        String s1 = null;
        try
        {
            s1 = new String(s);
            MessageDigest messagedigest = MessageDigest.getInstance("MD5");
            s1 = byteArrayToHexString(messagedigest.digest(s1.getBytes()));
        }
        catch(Exception ex)
        {
            
        }
        return s1;
    }

    public static void main(String args[])
    {
        System.out.println(md5Encode("admin"));
    }

    private static final String hexDigits[] = {
        "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
        "a", "b", "c", "d", "e", "f"
    };
}
