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

import com.jdatabeans.util.PaintTools;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author afa
 */
public class NavigatePicPane extends JPanel{

    private static Image backImage;
    
    private JPanel centerPanel;
    public NavigatePicPane(JPanel centerPanel)
        {
            try
            {
                if (backImage == null)
                {
                    backImage = ImageIO.read(getClass().getResource("/resources/main/desktop.png"));
                }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
            this.centerPanel = centerPanel;
            this.setLayout(null);
            //this.setOpaque(false);
            //this.setBackground(Color.white);
//            item = new ModuleItem("≤‚ ‘", new ImageIcon(getClass().getResource("/resources/edit.gif")));
//            this.add(item);
            //item.setBounds(10, 10, 120, 100);//, WIDTH, WIDTH, WIDTH);
            
            this.add(centerPanel);
            centerPanel.setBounds(10, 10, 700, 480);
             
        }

        public void setBounds(int x, int y, int width, int height)
        {
            super.setBounds(x, y, width, height);
            centerPanel.setLocation((width - centerPanel.getWidth()) /2 , (height - centerPanel.getHeight())/2);
        }

 


//        ModuleItem item;
//        private void relocated()
//        {
//            Rectangle rect = this.getBounds();
//            if (item != null)
//            {
//                item.setLocation(rect.width / 2, rect.height - 100);
//            }
//        }
        
        protected void paintComponent(Graphics g) {
            Rectangle rect = this.getBounds();
            if (backImage != null)
            {
                PaintTools.fillImageBackground(g, new Rectangle(0, 0, rect.width, rect.height), backImage, 0);
            }

        }

}
