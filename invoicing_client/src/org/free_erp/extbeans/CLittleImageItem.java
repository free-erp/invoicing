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
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.event.EventListenerList;
import javax.swing.event.MouseInputListener;

/**
 *
 * @author afa
 */
public class CLittleImageItem extends JPanel implements MouseInputListener
{
    private static final Color color = new Color(65, 71, 101);
    private String hitInfo;

    public String getHitInfo() {
        return hitInfo;
    }

    public void setHitInfo(String hitInfo) {
        this.hitInfo = hitInfo;
    }


    private static Image image;
		public CLittleImageItem()
		{
			this.setSize(30, 48);
			this.setLayout(null);
            this.addMouseListener(this);
            if (image == null)
            {
                try
                {
                    image = ImageIO.read(this.getClass().getResource("/resources/png/items/search_little.png"));
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }
		}
        
        protected void paintComponent(Graphics g) {
        Rectangle rect = this.getBounds();
        g.setColor(lineColor);
        g.drawLine(0, 0, rect.width - 1, 0);
        g.drawLine(rect.width - 1, 0, rect.width - 1, rect.height - 1);
        g.drawLine(0, rect.height - 1, rect.width - 1, rect.height - 1);
        g.drawImage(image, 3, 18, null);
        g.setColor(color);
        g.drawString("²éÑ¯", 2, 16);

    }
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
			this.setCursor(new Cursor(Cursor.HAND_CURSOR));
            this.fireHelpInfoChanged(new HelpEvent(this, this.hitInfo));
		}

		public void mouseExited(MouseEvent e) {
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            this.fireHelpInfoChanged(new HelpEvent(this, null));

		}

		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

        private static final Color lineColor = new Color(156, 162, 188);
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
