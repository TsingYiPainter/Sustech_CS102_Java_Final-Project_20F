package components;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class BasicComponent extends JComponent {
    public BasicComponent() {
        initial();
    }

    private void initial(){
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int counter=0;
                if(e.getButton()==1){
                    counter++;
                    onMouseLeftClicked();
                }
                /*if(e.getButton()==3){
                    counter++;
                   onMouseRightClicked();
                }*/
            }
            public void mousePressed(MouseEvent e){
                super.mousePressed(e);
                if (e.getButton()==3){
                    onMouseRightPressed();
                }
            }
            public void mouseReleased(MouseEvent e){
                super.mouseReleased(e);
                if(e.getButton()==3){
                    onMouseRightReleased();
                }
            }
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                onMouseEnter();
            }
            public void mouseExited(MouseEvent e){
                super.mouseExited(e);
                onMouseExited();
            }

        });

    }

    protected abstract void onMouseExited();

    protected abstract void onMouseEnter();

    protected abstract void onMouseRightReleased();

    protected abstract void onMouseRightPressed();

    /**
     * invoke this method when mouse left clicked
     */
    public abstract void onMouseLeftClicked();

    /**
     * invoke this method when mouse right clicked
     */

}
