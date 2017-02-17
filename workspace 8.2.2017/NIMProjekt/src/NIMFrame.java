import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class NIMFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NIMFrame frame = new NIMFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	JTextArea taAusgabe;
	JCheckBox CB1A;
	JCheckBox CB2A;
	JCheckBox CB2B; 
	JCheckBox CB3A;
	JCheckBox CB3B;
	JCheckBox CB3C;
	int letzteStellung = 123;
	boolean anfang = true;
	

	int[] alteStellung = {123,122,121,120,113,112,111,110,103,102,101,100,23,22,21,20,13,12,11,10,3,2,1,0};
	int[] neueStellung = {113,22,111,100,111,111,11,10,100,100,100,0,22,21,1,10,10,10,10,0,1,1,0,0};
	boolean[] istGewinnstellung = {false,true,true,true,true,true,false,true,true,true,true,false,true,false,true,true,true,true,true,false,true,true,false,true};
	boolean[] AmGewinnen = {true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,false,false};
 
	
	boolean pruefen (int stellung1, int stellung2) {
		
		if (stellung1 != stellung2){
			anfang = false;
		}
		
		int z1 =stellung1%10;
		stellung1=stellung1/10;
		int y1 =stellung1%10;
		stellung1=stellung1/10;
		int x1 =stellung1%10;
		
		int z2 =stellung2%10;
		stellung2=stellung2/10;
		int y2 =stellung2%10;
		stellung2=stellung2/10;
		int x2 =stellung2%10;
		
		int x = boolToInt(x2<x1);
		int y = boolToInt(y2<y1);
		int z = boolToInt(z2<z1);
		int xyz= x+y+z;
		
		if (xyz != 1)
			taAusgabe.setText("Das war ein falscher Zug!\nDu darfst nur Streichhölzer aus \nEINER Reihe nehmen!");
		
		return anfang || xyz==1  ;		
	}
	
	int boolToInt(boolean b){  //Umwandlung von Boolean in Integer
		if (b) return 1;
		else   return 0;		
	}
	
	int analyse(){                // liest die neue Stellung aus den CheckBoxen aus: Tarun, Batuhan, Shafin, Alexander, Lukas, Muhamet
		int x = boolToInt(CB1A.isSelected());
		int y = boolToInt(CB2A.isSelected())+boolToInt(CB2B.isSelected());
		int z = boolToInt(CB3A.isSelected())+boolToInt(CB3B.isSelected())+boolToInt(CB3C.isSelected());
		return x*100+y*10+z;
	}
	
	
	public void ziehen(int stellung){                 // setzt die CheckBoxen so, dass sie die neue Stellung zeigen: Gülnihal, Abdulrahman, Deniz 	
		int z1 = stellung % 10;
		stellung = stellung/10;
		int y1 = stellung %10;
		stellung = stellung/10;
		int x1 = stellung %10;
		stellung = stellung/10;
		
	    CB3A.setSelected(z1>=1);
	    CB3B.setSelected(z1>=2);
	    CB3C.setSelected(z1>=3);
	    CB2A.setSelected(y1>=1);
	    CB2B.setSelected(y1>=2);
	    CB1A.setSelected(x1>=1);    
		}
	

	
	public int zug(int eineAlteStellung){  // schlägt die neue Stellung mit Hilfe der alten SAtellung nach: ?	
		int n = 0; 
		for (int i=0; i<24; i++){
			if (alteStellung[i] == eineAlteStellung){
				n = neueStellung[i];
					
			if 	(istGewinnstellung[i]){taAusgabe.setText("Du verlierst");}
			else taAusgabe.setText("Du kannst gewinnen");
			
            if 	(!AmGewinnen[i]){taAusgabe.setText("Du hast gewonnen");}
			
			if (!AmGewinnen[i] && istGewinnstellung[i]){taAusgabe.setText("Du hast verloren");}
			
	
			}
			}
			
		anfang = false;
		return n;
	};
	


	/**
	 * Create the frame.
	 */
	public NIMFrame() {
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 850);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		CB1A = new JCheckBox("");
		CB1A.setSelected(true);
		CB1A.setBounds(110, 17, 21, 23);
		contentPane.add(CB1A);
		
		CB2A = new JCheckBox("");
		CB2A.setSelected(true);
		CB2A.setBounds(80, 40, 26, 23);
		contentPane.add(CB2A);
		
		CB2B = new JCheckBox("");
		CB2B.setSelected(true);
		CB2B.setBounds(143, 40, 31, 23);
		contentPane.add(CB2B);
		
		CB3A = new JCheckBox("");
		CB3A.setSelected(true);
		CB3A.setBounds(54, 74, 26, 23);
		contentPane.add(CB3A);
		
		CB3B = new JCheckBox("");
		CB3B.setSelected(true);
		CB3B.setBounds(110, 74, 26, 23);
		contentPane.add(CB3B);
		
		CB3C = new JCheckBox("");
		CB3C.setSelected(true);
		CB3C.setBounds(173, 74, 26, 23);
		contentPane.add(CB3C);
		
		JButton btnComputer = new JButton("Computer");
		btnComputer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    int naechsteStellung;		
				int eineNeueStellung = analyse();
				boolean ok = pruefen(letzteStellung,eineNeueStellung);
				if (ok) {naechsteStellung = zug(eineNeueStellung);
				         ziehen(naechsteStellung);
				         letzteStellung = naechsteStellung;
				}
				
			}
		});
		btnComputer.setBounds(234, 234, 119, 23);
		contentPane.add(btnComputer);
		
		taAusgabe = new JTextArea();
		taAusgabe.setBounds(231, 74, 193, 131);
		contentPane.add(taAusgabe);
		
		JButton btnRestart = new JButton("Restart");
		btnRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ziehen (123);
				taAusgabe.setText("");
				
				
			}
		});
		btnRestart.setBounds(287, 29, 89, 23);
		contentPane.add(btnRestart);
		
		NIMPanel panel = new NIMPanel();
		panel.setBounds(10, 104, 214, 697);
		contentPane.add(panel);
		
	    
	
		
	}
}
