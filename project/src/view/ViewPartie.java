package view;
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Dimension;
import controller.*;
import javax.swing.ImageIcon;
import javax.swing.Icon;

public class ViewPartie {

	private JFrame frmNavalBattle;
	private Controller ctrl;

	public ViewPartie(boolean newPartie){ 
		this.ctrl = new Controller();
		if (newPartie == true) { initialize(); }
		else if (newPartie == false) { initialize(); newPartie();}
	}
	
	
	public JFrame getFrame() { return this.frmNavalBattle; }
	/**
	 * @wbp.parser.entryPoint
	 */

	
	private void initialize() {
		ctrl.DistribuerNavire(ctrl.getJ1());
		ctrl.DistribuerNavire(ctrl.getJ2());
		ctrl.setGrid2(ctrl.getJ1(), ctrl.getJ2());	
		
		frmNavalBattle = new JFrame();
		frmNavalBattle.setResizable(false);
		frmNavalBattle.setTitle("NAVAL BATTLE");
		frmNavalBattle.setBounds(100, 100, 852, 502);
		frmNavalBattle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNavalBattle.setLocationRelativeTo(null); 
		frmNavalBattle.setPreferredSize(new Dimension(800,800));
		
		JPanel panel = new JPanel();
		frmNavalBattle.getContentPane().add(panel, BorderLayout.NORTH);
		
		JButton tirerBttn = new JButton("TIRER");
		panel.add(tirerBttn);
		
		JButton deplacerBttn = new JButton("DEPLACER");
		panel.add(deplacerBttn);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		
		JButton deplacerHaut = new JButton("HAUT");
		panel_2.add(deplacerHaut);
		
		
		JButton deplacerBas = new JButton("BAS");
		panel.add(deplacerBas);
		
		JButton deplacerGauche = new JButton("GAUCHE");
		panel.add(deplacerGauche);
		
		JButton deplacerDroite = new JButton("DROITE");
		panel.add(deplacerDroite);
		
		JPanel panel_1 = new JPanel();
		frmNavalBattle.getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		JLabel lblNewLabel = new JLabel("--- TOUR DE J ---");
		lblNewLabel.setBackground(new Color(177, 201, 255));
		lblNewLabel.setForeground(new Color(140, 177, 18));
		panel_1.add(lblNewLabel);
		//panel_4.add(btnNewButton);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel mssg = new JLabel("  MESSAGE :  ");
		mssg.setForeground(new Color(155, 76, 62));
		panel_1.add(mssg);
		
		JLabel lblNewLabel_1 = new JLabel("SELECTIONNER LA CASE D'UN NAVIRE                ");
		panel_1.add(lblNewLabel_1);
		
		JButton quitBttn = new JButton("SAUVEGARDER ET QUITTER");
		panel_1.add(quitBttn);
		
		
		
		quitBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				ViewAcceuil acc = new ViewAcceuil();
				acc.getFrame().setVisible(true);
				frmNavalBattle.dispose();
			}
		});
	}
	
	public void newPartie() {
		ctrl.DistribuerNavire(ctrl.getJ1());
		ctrl.DistribuerNavire(ctrl.getJ2());
		ctrl.setGrid2(ctrl.getJ1(), ctrl.getJ2());		
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(400,400));
		panel_2.setBackground(new Color(39, 101, 149));
		frmNavalBattle.getContentPane().add(panel_2, BorderLayout.WEST);
		panel_2.setLayout(new GridLayout(15, 15, 0, 0));
		String alphCol = ctrl.getJ1().getGrid1().getAlphaCol();
		
		Color c = Color.RED;
		for (int i= 0; i< 15; i++) {
			for (int j= 0; j< 15; j++) {
				char y = alphCol.charAt(i);
				String coord = Character.toString(y) + j;
				String val = ctrl.getJ1().getGrid1().getCase(coord);
				if (val == "xx") { c = Color.RED;} 
				if (val == "tt") { c = Color.BLUE;} 
				if (val == "oo") { c = Color.GREEN;} 
				if (val == "ss") { c = Color.CYAN;} 
				if (val == "++") { c = Color.PINK;} 
				if (val == "**") { c = Color.YELLOW;} 
				JButton b = new JButton(val);
				b.setBackground(c);
				b.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){
						System.out.println("ligne : "  + " colonne: " + coord + val);
					}
				});
				panel_2.add(b);
			}
		}
	
		JPanel panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(400,400));
		panel_3.setBackground(new Color(9, 10, 11));
		frmNavalBattle.getContentPane().add(panel_3, BorderLayout.EAST);
		panel_3.setLayout(new GridLayout(15, 15, 0, 0));
		for (int i= 0; i< 15; i++) {
			//gbc.gridx = i;
			for (int j= 0; j< 15; j++) {
				char y = alphCol.charAt(i);
				String coord = Character.toString(y) + j;
				String val = ctrl.getJ1().getGrid2().getCase(coord);
				JButton b = new JButton(val);
				if (val == "xx") { c = Color.RED;} 
				if (val == "tt") { c = Color.BLUE;} 
				if (val == "oo") { c = Color.GREEN;} 
				if (val == "ss") { c = Color.CYAN;} 
				if (val == "++") { c = Color.PINK;} 
				if (val == "**") { c = Color.YELLOW;} 
				b.setBackground(c);
				panel_3.add(b);
			}
		}
	}

}
