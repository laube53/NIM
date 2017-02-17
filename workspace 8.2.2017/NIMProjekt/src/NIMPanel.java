
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

	public class NIMPanel extends JPanel  {
		int[] hoelzchen = {1,2,3,3,2,1}; 
		
	public boolean bildZeichnen;

	public NIMPanel(){
		bildZeichnen = true;
	    addMouseListener(new MouseAdapter() { 
	          public void mouseClicked(MouseEvent me) { 
	        	int x = me.getX();  	// xPixel des Maus-Klicks
	        	int y = me.getY();  	// yPixel des Maus-Klicks
	        	int j0 = (y-10)/100;	// Nr. der Zeile	        	
	        	int i0 = (x-10)/20; 	// Nr. des Hölzchens in Zeile j0
	        	
	        	if (i0<hoelzchen[j0]) hoelzchen[j0]--;	             
	          } 
	        }); 
	}

	public void paint(Graphics g){
	        super.paint(g);
	        
	 if(bildZeichnen){
		String imageSrc ="Streichholz.gif";
		Image img = getToolkit().getImage(imageSrc);
		for (int j=0;j<hoelzchen.length;j++){
			for (int i=0;i<hoelzchen[j];i++)
		       g.drawImage(img, 10+i*20, 10+j*100, null);
		}		
		repaint();
	 }	 
	}
	
	public void addMouseListener(MouseAdapter mouse){
	 super.addMouseListener(mouse);	
	}
	}

