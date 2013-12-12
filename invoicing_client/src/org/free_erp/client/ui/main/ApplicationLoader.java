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
package org.free_erp.client.ui.main;

import com.jdatabeans.util.MessageBox;

class ApplicationLoader extends ThreadGroup
    {
        ApplicationLoader()
        {
            super("erp");
        }

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            String errorInfo;
            String errors = "\n�쳣����:" + e.getClass().getName();
            int count = 0;
            for(StackTraceElement ele:e.getStackTrace())
            {
                String s = "\n" + ele.getClassName() + "." +  ele.getMethodName() + "() line:" + ele.getLineNumber();
                errors += s;
                count++;
                if (count >= 10)
                {
                    break;
                }
            }

            if (e instanceof NullPointerException)
            {
                errorInfo = "ϵͳ���������ص��쳣���������������������\n�뽫����Ϣ������������Ա,лл��" + errors;
            }
            else
            {
                errorInfo = "ϵͳ�����쳣���������������������\n�뽫����Ϣ������������Ա,лл��" + errors;
            }
            MessageBox.showErrorDialog(Main.getMainFrame(), errorInfo);
          
        }
    }