package view;
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Dimension;
import controller.*;
import modele.Navire;


public class ViewPartie {

	private JFrame frmNavalBattle;
	private Controller ctrl;
	
	String coordN = "";
	String coordCible = "";
	JPanel panel = new JPanel();
	JButton tirerBttn = new JButton("TIRER");
	JButton deplacerBttn = new JButton("DEPLACER");
	int indNavire;
	
	String[][] grid1;
	String[][] grid2;
	
	Navire navire; 
	Navire cibleNavire;
	JPanel panel_2 = new JPanel();
	JPanel panel_3 = new JPanel();
	String alphCol;
	JButton deplacerHaut = new JButton("HAUT");
	JButton deplacerBas = new JButton("BAS");
	JButton deplacerGauche = new JButton("GAUCHE");
	JButton deplacerDroite = new JButton("DROITE");
	JLabel mssgbox = new JLabel("CLIQUEZ SUR UNE CASE DE NAVIRE POUR EXECUTER UNE ACTION");
	private final JPanel panel_4 = new JPanel();

	private final JLabel coordLabel = new JLabel("NAVIRE : ");
	private final JLabel cibleLabel = new JLabel("CIBLE : ");
	private final JLabel coordLab = new JLabel("a0");
	private final JLabel cibleLab = new JLabel("a0");
	private final JLabel lblNewLabel_1 = new JLabel("|");
	private final JLabel lblNewLabel_2 = new JLabel("|");
	private final JLabel lblNewLabel_3 = new JLabel("   |   ");
	
	public ViewPartie(boolean newPartie){ 
		this.ctrl = new Controller();
		if (newPartie == true) { ctrl.DistribuerNavire(ctrl.getJ1()); }
		
		ctrl.DistribuerNavire(ctrl.getJ2());
		ctrl.setGrid2(ctrl.getJ1(), ctrl.getJ2());	
		
		alphCol = ctrl.getJ1().getGrid1().getAlphaCol();
		grid1 = ctrl.getCurrentJ().getGrid1().getCases();
		grid2 = ctrl.getCurrentJ().getGrid2().getCases();

		initialize(); 
		deroulement();
		//setGrid1(false);
		//setGrid2(false);
	}
	
	public JFrame getFrame() { return this.frmNavalBattle; }
	/**
	 * @wbp.parser.entryPoint
	 */

	
	private void initialize() {
		frmNavalBattle = new JFrame();
		frmNavalBattle.setResizable(false);
		frmNavalBattle.setTitle("NAVAL BATTLE");
		frmNavalBattle.setBounds(100, 100, 913, 520);
		frmNavalBattle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNavalBattle.setLocationRelativeTo(null); 
		frmNavalBattle.setPreferredSize(new Dimension(800,800));
		frmNavalBattle.getContentPane().add(panel, BorderLayout.NORTH);
		
		panel.add(panel_4);
		panel_4.add(coordLabel);
		panel_4.add(coordLab);
		panel_4.add(lblNewLabel_3);
		panel_4.add(cibleLabel);
		panel_4.add(cibleLab);
		panel.add(lblNewLabel_2);

		tirerBttn.setEnabled(false);
		panel.add(tirerBttn);
		
		deplacerBttn.setEnabled(false);
		panel.add(deplacerBttn);
		panel.add(lblNewLabel_1);
		
		JButton validerBttn = new JButton("VALIDER");
		panel_4.add(validerBttn);
		validerBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				ctrl.switchJ();
				setGrid1(false);
				setGrid2(false);
				mssgbox.setText("TOUR PASSE");
			}
		});

		panel.add(panel_2);
		deplacerHaut.setEnabled(false);
		panel_2.add(deplacerHaut);
		deplacerHaut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				setDeplacement("0");
			}
		});
		
		deplacerBas.setEnabled(false);
		panel.add(deplacerBas);
		deplacerBas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				setDeplacement("1");
			}
		});
		
		deplacerGauche.setEnabled(false);
		panel.add(deplacerGauche);
		deplacerGauche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				setDeplacement("2");

			}
		});
		
		deplacerDroite.setEnabled(false);
		panel.add(deplacerDroite);
		deplacerDroite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				setDeplacement("3");
			}
		});
		
		
		JPanel panel_1 = new JPanel();
		frmNavalBattle.getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		JLabel lblNewLabel = new JLabel("--- TOUR DE J ---");
		lblNewLabel.setBackground(new Color(177, 201, 255));
		lblNewLabel.setForeground(new Color(140, 177, 18));
		panel_1.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel mssg = new JLabel("  MESSAGE :  ");
		mssg.setForeground(new Color(155, 76, 62));
		panel_1.add(mssg);
		panel_1.add(mssgbox);
		JButton quitBttn = new JButton("SAUVEGARDER ET QUITTER");
		panel_1.add(quitBttn);
		quitBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				File doss = new File("../../resources/sauvegarde/"); 
				File f = new File("");
				int nbFich = 0;
				for (File fich : doss.listFiles()) { 
					if(fich.getName().startsWith("save")) {
						nbFich++;
					}
				}

				try {		
					f = new File ("../../resources/sauvegarde/" + "save"+ (nbFich+1) +".txt");
					FileWriter fw = new FileWriter(f.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fw);
					// ECRITURE DANS LE FICHIER
					for (Navire N: ctrl.getJ1().getNavires()) {
						String line = N.getName() + "," + N.getDisposition() + "," + !N.estCoule();
						for (String uneCase: N.getCasesNavire()) {
							line += ",";
							line += uneCase;
							if (ctrl.getJ1().getGrid1().estCaseNavireTouchee(uneCase)) { line += "x"; } 
						}
						line += "\n";
						bw.write(line);
					}	
					bw.close();
				} catch (Exception ex) { System.err.println(ex); }
				
				mssgbox.setText("PARTIE ENREGISTREE DANS => " + f.getName());
				try { TimeUnit.SECONDS.sleep(3); } catch(Exception ex) { System.out.println(ex); }
				
				frmNavalBattle.dispose();
				ViewAcceuil acc = new ViewAcceuil();
				acc.getFrame().setVisible(true);
			}
		});
		
		
		deplacerBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				tirerBttn.setSelected(false);
				deplacerBttn.setSelected(true);
				tirerBttn.setEnabled(false);
				if (navire != null && navire.getName() == "SOUSMARIN") {
					deplacerHaut.setEnabled(true);
					deplacerBas.setEnabled(true);
					deplacerGauche.setEnabled(true);
					deplacerDroite.setEnabled(true);
				}
				else if (navire != null && navire.getDisposition() == "horizontale") {
					deplacerGauche.setEnabled(true);
					deplacerDroite.setEnabled(true);
					deplacerHaut.setEnabled(false);
					deplacerBas.setEnabled(false);
				}
				else if (navire != null && navire.getDisposition() == "verticale") {
					deplacerHaut.setEnabled(true);
					deplacerBas.setEnabled(true);
					deplacerGauche.setEnabled(false);
					deplacerDroite.setEnabled(false);
				}
				mssgbox.setText("CHOISISSEZ UNE DIRECTION, POUR LE DEPLACEMENT");
			}
		});
		
		tirerBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				deplacerBttn.setSelected(false);
				setGrid2(true);
				deplacerHaut.setEnabled(false);
				deplacerBas.setEnabled(false);
				deplacerGauche.setEnabled(false);
				deplacerDroite.setEnabled(false);
				deplacerBttn.setEnabled(false);
				tirerBttn.setSelected(true);
				mssgbox.setText("CLIQUER SUR UNE CASE DANS LA GRILLE 2, POUR TIRER");
			}
		});
	}
	
	
	public void setDeplacement(String s) {
		deplacerHaut.setEnabled(false);
		deplacerDroite.setEnabled(false);
		deplacerGauche.setEnabled(false);
		deplacerBas.setEnabled(false);
		deplacerBttn.setSelected(false);
		deplacerBttn.setEnabled(false);
		tirerBttn.setEnabled(false);
		ArrayList<String> newCoords = ctrl.getCurrentJ().deplacer(navire, s);
		if (newCoords != null) {
			navire.setCasesNavire(newCoords);
			ctrl.getCurrentJ().getNavires().set(indNavire, navire);
			ctrl.getCurrentJ().setGrid1(ctrl.getCurrentJ().getGrid1());
			grid2 = ctrl.getCurrentJ().getGrid2().getCases();
			setGrid1(true);
			if (s.equals("0")) { mssgbox.setText("DEPLACEMENT EFFECTUE EN HAUT"); }
			if (s.equals("1")) { mssgbox.setText("DEPLACEMENT EFFECTUE EN BAS"); }
			if (s.equals("2")) { mssgbox.setText("DEPLACEMENT EFFECTUE A GAUCHE"); }
			if (s.equals("3")) { mssgbox.setText("DEPLACEMENT EFFECTUE A DROITE"); }
			return;
		}
		mssgbox.setText("DEPLACEMENT IMPOSSIBLE, TIRER SUR UNE CASE ENNEMIE");
	}
	
	
	public void setGrid1(boolean moovebool) {
		panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(400,400));
		panel_2.setBackground(new Color(39, 101, 149));
		frmNavalBattle.getContentPane().add(panel_2, BorderLayout.WEST);
		GridLayout grid = new GridLayout(ctrl.nLine, ctrl.nCol, 0, 0);
		panel_2.setLayout(grid);
		ctrl.afficher2Grilles(ctrl.getCurrentJ(),ctrl.getJ2(),null);
		Color c = Color.WHITE;
		
		for (int i = 0; i < ctrl.nLine; i++) {
		    for (int j = 0; j < ctrl.nCol; j++) {
		    	char y = alphCol.charAt(i);
				String coord = Character.toString(y) + j;
				String val = ctrl.getCurrentJ().getGrid1().getCase(coord);
				if (val == "xx") { c = Color.RED;} 
				if (val == "tt") { c = Color.BLUE;} 
				if (val == "oo") { c = Color.GREEN;} 
				if (val == "ss") { c = Color.CYAN;} 
				if (val == "++") { c = Color.PINK;} 
				if (val == "**") { c = Color.YELLOW;} 
				if (val == "  ") { c = Color.WHITE;} 
		        JButton b = new JButton(val);
		        b.setBackground(c);
		        b.addActionListener(new ActionListener() {
		        	public void actionPerformed(ActionEvent e){
						tirerBttn.setSelected(false);
		        		if (b.getText() != "  ") {
		        			tirerBttn.setEnabled(true);
		        			deplacerBttn.setEnabled(true);
		        			if (b.isSelected() == true) { b.setSelected(false);}
		        			else {  b.setSelected(true);}
		        			indNavire = ctrl.trouverNavireAvecCoord(ctrl.getCurrentJ(), coord);
		        			navire = ctrl.getCurrentJ().getNavires().get(indNavire);
		        			System.out.println(coord + " " + indNavire + " " + navire.getDisposition());
		        		}
		        		else {
		        			deplacerDroite.setEnabled(false);
		    				deplacerGauche.setEnabled(false);
		    				deplacerHaut.setEnabled(false);
		    				deplacerBas.setEnabled(false);
		    				tirerBttn.setEnabled(false);
		    				deplacerBttn.setEnabled(false);
		        		}
	        			coordLab.setText(coord);
		        	}
				});
		        
				panel_2.add(b);
		    }
		}
	}
	
	public void setGrid2(boolean tirerbool) {
		panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(400,400));
		panel_3.setBackground(new Color(9, 10, 11));
		frmNavalBattle.getContentPane().add(panel_3, BorderLayout.EAST);
		GridLayout grid = new GridLayout(ctrl.nLine, ctrl.nCol, 0, 0);
		panel_3.setLayout(grid);

		Color c = Color.WHITE;
		for (int i= 0; i< ctrl.nLine; i++) {
			for (int j= 0; j< ctrl.nCol; j++) {
				char yc = alphCol.charAt(j);
				String coordC = Character.toString(yc) + j;
				String val = grid2[j][i];
				if (val == "xx") { c = Color.RED;} 
				if (val == "tt") { c = Color.BLUE;} 
				if (val == "oo") { c = Color.GREEN;} 
				if (val == "ss") { c = Color.CYAN;} 
				if (val == "++") { c = Color.PINK;} 
				if (val == "**") { c = Color.YELLOW;} 
				if (val == "  ") { c = Color.WHITE;} 
				JButton b = new JButton(val);
				String cc = ctrl.getCurrentJ().getGrid2().xyToString(j,i);//Integer.toString(i) + Integer.toString(j);
				b.setBackground(c);
				b.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){
						if (tirerBttn.isSelected() == true) {
							deplacerBttn.setSelected(false);
							cibleLab.setText(cc);
							if (ctrl.getCurrentJ().getGrid2().getCase(cc) == "  ") {
							//if (b.getText() == "  ") {
								b.setText("tt");
							}
							else {
								b.setText("xx");
							}
						}
						mssgbox.setText("TIR EFFECTUE");
						/*int ind = ctrl.trouverNavireAvecCoord(ctrl.getEnnemy(), coordCible);
						if (ind >= 0) {cibleNavire = ctrl.getEnnemy().getNavires().get(ind);}
	        			System.out.println(coordC + " " + ind);*/
					}
				});
				panel_3.add(b);
			}
		}
	}
	
	
	public void deroulement() {
		boolean fini = false;
		/*while (fini == false) {
			setGrid1(false);
			setGrid2(false);
			fini = ctrl.nbCaseNavire == ctrl.getEnnemy().getCasesNaviresTouches().size();
			
			ctrl.switchJ();
		}*/
		setGrid1(false);
		setGrid2(false);
		fini = ctrl.nbCaseNavire == ctrl.getEnnemy().getCasesNaviresTouches().size();
	}

}
