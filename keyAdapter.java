// just a start for now




import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;




private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!Direction.EAST)) {
                Direction.WEST = true;
                Direction.NORTH = false;
                Direction.SOUTH = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!Direction.WEST)) {
                Direction.EAST = true;
                Direction.NORTH = false;
                Direction.SOUTH = false;
            }

            if ((key == KeyEvent.VK_UP) && (!Direction.SOUTH)) {
                Direction.NORTH = true;
                Direction.EAST = false;
                Direction.WEST = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!Direction.NORTH)) {
                Direction.SOUTH = true;
                Direction.EAST = false;
                Direction.WEST = false;
            }
