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

import com.jdatabeans.beans.data.JDataTable;
import com.jdatabeans.beans.data.table.BetterCellRenderer;
import org.free_erp.service.constants.ServiceConstants;
import java.awt.Component;
import javax.swing.JLabel;

/**
 *
 * @author afa
 */
public class StatusCellRenderer extends BetterCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JDataTable table, Object value, boolean isSelected, boolean hasFocus, int alignmentX, int alignmentY) {
        String v = String.valueOf(value);
        if (value instanceof Integer || value instanceof Long)
        {
            int type = Integer.valueOf(String.valueOf(value));
            if (type == ServiceConstants.DRAFT_STATUS)
            {
                v = ServiceConstants.DRAFT_STRING;//"Œ¥…Û∫À";
            }
            else if (type == ServiceConstants.FORMAL_STATUS)
            {
                v = ServiceConstants.FORMAL_STRING;//"“—…Û∫À";
            }
            else if (type == ServiceConstants.DISCARD_STATUS)
            {
                v = ServiceConstants.DISCARD_STRING;//"∑œ∆˙";
            }
            else
            {
                v = ServiceConstants.UNKNOWN;//"Œ¥∂®“Â◊¥Ã¨";
            }
        }
        else
        {
            v = "Œ¥…Ë÷√";
        }
        
        return super.getTableCellRendererComponent(table, v, isSelected, hasFocus, JLabel.CENTER, alignmentY);
    }

}
