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

package org.free_erp.extbeans;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

/**
 *
 * @author afa
 */
public class CBaseImageItem   extends JPanel implements MouseInputListener
	{
        private boolean showLine = true;
        private static final Color color = new Color(65, 71, 101);
        private JLabel label;
		public CBaseImageItem(Icon icon, String title)
		{
			this.setSize(icon.getIconWidth() + title.length() * 16, icon.getIconHeight() + 10);
			this.setLayout(null);
			label = new JLabel(title, icon, JLabel.RIGHT);
			this.add(label);
			label.setBounds(0, 0, icon.getIconWidth() + title.length() * 16, icon.getIconHeight() + 10);
			label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);
			label.setForeground(color);
			this.addMouseMotionListener(this);
			this.addMouseListener(this);
			this.setBorder(BorderFactory.createLineBorder(lineColor));
		}

        private static final Color lineColor = new Color(156, 162, 188);
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		public void mouseEntered(MouseEvent e) {
            if (this.showLine)
            {
                this.setBorder(BorderFactory.createLineBorder(Color.blue));
            }
            label.setForeground(Color.blue);
			this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}

		public void mouseExited(MouseEvent e) {
            if (this.showLine)
            {
                this.setBorder(BorderFactory.createLineBorder(lineColor));
            }
            else
            {
                this.setBorder(null);
            }
            label.setForeground(color);
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

		}

		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}
         public void setLineVisible(boolean visible)
        {
             this.showLine = visible;
            if (!visible)
            {
                this.setBorder(null);
            }
        }
	}