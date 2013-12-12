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

package org.free_erp.client.ui.core;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author afa
 */
public class CModulePicPane extends JPanel
{
    public CModulePicPane()
    {
        this.setLayout(null);
        this.setSize(700, 480);
        this.setOpaque(false);        
    }

    public void addModuleItem(ModuleItem item, int x, int y)
    {
        this.add(item);
        item.setBounds(x, y, item.getWidth(), item.getHeight());
    }

    public void addArrow(JLabel arrow, int x, int y, int width, int height)
    {
        this.add(arrow);
        arrow.setBounds(x, y, width, height);

    }


}
