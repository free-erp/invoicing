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
import javax.swing.event.EventListenerList;
import javax.swing.event.MouseInputListener;

/**
 *
 * @author afa
 */
public class CImageItem  extends JPanel implements MouseInputListener
	{
        private String hitInfo;

        public String getHitInfo() {
        return hitInfo;
        }

        public void setHitInfo(String hitInfo) {
        this.hitInfo = hitInfo;
        }
        private static final Color color = new Color(65, 71, 101);
		public CImageItem(Icon icon, String title)
		{
			this.setSize(100, 80);
			this.setLayout(null);
			JLabel label = new JLabel(icon);
			this.add(label);
			label.setBounds((100 - 48) /2 , 5, 48, 48);
			label = new JLabel(title);
			this.add(label);

			label.setHorizontalAlignment(JLabel.CENTER);
			label.setBounds(0, 60, 100, 20);
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
			this.setBorder(BorderFactory.createLineBorder(Color.blue));
			this.setCursor(new Cursor(Cursor.HAND_CURSOR));
            this.fireHelpInfoChanged(new HelpEvent(this, this.hitInfo));
		}

		public void mouseExited(MouseEvent e) {
			this.setBorder(BorderFactory.createLineBorder(lineColor));
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            this.fireHelpInfoChanged(new HelpEvent(this, null));
		}

		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}
        protected EventListenerList hitListeners = new EventListenerList();

        public void addHitListener(HitListener l)
        {
            this.hitListeners.add(HitListener.class, l);
        }

        public void removeHitListener(HitListener l)
        {
            this.hitListeners.remove(HitListener.class, l);
        }
        protected void fireHelpInfoChanged(HelpEvent e)
        {
		
		Object[] listeners = hitListeners.getListenerList();		
		for (int i = listeners.length - 2; i >= 0; i -= 2)
		{

			if (listeners[i] == HitListener.class)

			{

				((HitListener) listeners[i + 1]).helpInfoChanged(e);

			}

		}

	}
        
	}