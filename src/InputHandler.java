import java.awt.Component;
import java.awt.event.*;

import javax.swing.JPanel;

/**
 * Makes handling input a lot simpler
 */
public class InputHandler implements KeyListener
{

  private boolean[] keys;

        /**
         * Assigns the newly created InputHandler to a Component
         * @param c Component to get input from
         */
        public InputHandler(Component c)
        {
          keys = new boolean[256];
          c.addKeyListener(this);
        }

        /**
         * Checks whether a specific key is down
         * @param keyCode The key to check
         * @return Whether the key is pressed or not
         */
        public boolean isKeyDown(int keyCode)
        {
                if (keyCode > 0 && keyCode < 256)
                {
                        return keys[keyCode];
                }

                return false;
        }

        /**
         * Called when a key is pressed while the component is focused
         * @param e KeyEvent sent by the component
         */
        public void keyPressed(KeyEvent e)
        {
                if (e.getKeyCode() > 0 && e.getKeyCode() < 256)
                {
                        keys[e.getKeyCode()] = true;
                }
        }

        /**
         * Called when a key is released while the component is focused
         * @param e KeyEvent sent by the component
         */
        public void keyReleased(KeyEvent e)
        {
                if (e.getKeyCode() > 0 && e.getKeyCode() < 256)
                {
                        keys[e.getKeyCode()] = false;
                }
        }

        /**
         * Not used
         */
        public void keyTyped(KeyEvent e){}


		public class MouseMotionEvent extends JPanel implements MouseMotionListener {

			private static final long serialVersionUID = 2844373802080978113L;

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		}
		
		public class ButtonListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		}
}