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
	JPanel panel = new JPanel();
	JButton tirerBttn = new JButton("TIRER");
	JButton deplacerBttn = new JButton("DEPLACER");
	int nbBttnSelect = 0;
	int indNavire;
	
	String[][] grid1;
	String[][] grid2;
	
	Navire navire; 
	JPanel panel_2 = new JPanel();
	JPanel panel_3 = new JPanel();
	String alphCol;
	JButton deplacerHaut = new JButton("HAUT");
	JButton deplacerBas = new JButton("BAS");
	JButton deplacerGauche = new JButton("GAUCHE");
	JButton deplacerDroite = new JButton("DROITE");
	JLabel mssgbox = new JLabel("CLIQUEZ SUR UNE CASE DE NAVIRE POUR EXECUTER UNE ACTION");
	
	public ViewPartie(boolean newPartie){ 
		this.ctrl = new Controller();
		if (newPartie == false) { ctrl.DistribuerNavire(ctrl.getJ1()); }
		
		ctrl.DistribuerNavire(ctrl.getJ2());
		ctrl.setGrid2(ctrl.getJ1(), ctrl.getJ2());	
		
		alphCol = ctrl.getJ1().getGrid1().getAlphaCol();
		grid1 = ctrl.getCurrentJ().getGrid1().getCases();
		grid2 = ctrl.getCurrentJ().getGrid2().getCases();

		initialize(); 
		setGrid1(false);
		setGrid2();

	}

	/*/ RECUPERATION DES DONNEES ET INITIALISATION DE LA GRILLE 1 DU JOUEUR 1
				while ((line = in.readLine())!= null) {
					Navire N = this.lineToNavire(line);
					navires.add(N);
					this.j1.addNavire(N);
					String symbole = N.getSymbole();
					for (String c: N.getCasesNavire()) {
						if (N.getCasesTouchees().contains(c)) { 
							this.j1.getGrid1().setCase("xx", c);
							int indN = this.trouverNavireAvecCoord(this.j1, c);
							this.j1.addCasesNaviresTouches(c, indN);
						}
						else { this.j1.getGrid1().setCase(symbole, c); }
					}
				}*/
	
	public JFrame getFrame() { return this.frmNavalBattle; }
	/**
	 * @wbp.parser.entryPoint
	 */

	
	private void initialize() {
		frmNavalBattle = new JFrame();
		frmNavalBattle.setResizable(false);
		frmNavalBattle.setTitle("NAVAL BATTLE");
		frmNavalBattle.setBounds(100, 100, 852, 502);
		frmNavalBattle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNavalBattle.setLocationRelativeTo(null); 
		frmNavalBattle.setPreferredSize(new Dimension(800,800));
		
		//
		frmNavalBattle.getContentPane().add(panel, BorderLayout.NORTH);
		
		//
		tirerBttn.setEnabled(false);
		panel.add(tirerBttn);
		
		//
		deplacerBttn.setEnabled(false);
		panel.add(deplacerBttn);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		
		//
		deplacerHaut.setEnabled(false);
		panel_2.add(deplacerHaut);
		
		//
		deplacerBas.setEnabled(false);
		panel.add(deplacerBas);
		
		//
		deplacerGauche.setEnabled(false);
		panel.add(deplacerGauche);
		
		//
		deplacerDroite.setEnabled(false);
		panel.add(deplacerDroite);
		deplacerDroite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				ArrayList<String> newCoords =ctrl.getCurrentJ().deplacer(navire, "3");
				if (newCoords == null) {
					// tirer
					mssgbox.setText("DEPLACEMENT IMPOSSIBLE, TIRER SUR UNE CASE ENNEMIE");
					deplacerBttn.setEnabled(false);
					tirerBttn.setEnabled(false);
				}
				navire.setCasesNavire(newCoords);
				ctrl.getCurrentJ().getNavires().set(indNavire, navire);
				System.out.println(newCoords.toString());
				setGrid1(true);
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
		
		//
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
				deplacerBttn.setSelected(true);
				tirerBttn.setSelected(false);
				if (navire != null && navire.getName() == "SOUSMARIN") {
					deplacerHaut.setEnabled(true);
					deplacerBas.setEnabled(true);
					deplacerGauche.setEnabled(true);
					deplacerDroite.setEnabled(true);
				}
				if (navire != null && navire.getDisposition() == "horizontale") {
					deplacerGauche.setEnabled(true);
					deplacerDroite.setEnabled(true);
					deplacerHaut.setEnabled(false);
					deplacerBas.setEnabled(false);
				}
				if (navire != null && navire.getDisposition() == "verticale") {
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
				deplacerHaut.setEnabled(false);
				deplacerBas.setEnabled(false);
				deplacerGauche.setEnabled(false);
				deplacerDroite.setEnabled(false);
				deplacerBttn.setSelected(false);
				tirerBttn.setSelected(true);
				deplacerHaut.setEnabled(false);
				mssgbox.setText("CLIQUER SUR UNE CASE DANS LA GRILLE 2, POUR TIRER");
			}
		});
	}
	
	/*
	public void setGrid() {	
		panel_2.setPreferredSize(new Dimension(400,400));
		panel_2.setBackground(new Color(39, 101, 149));
		frmNavalBattle.getContentPane().add(panel_2, BorderLayout.WEST);
		panel_2.setLayout(new GridLayout(15, 15, 0, 0));
		Color c = Color.WHITE;
		for (int i= 0; i< 15; i++) {
			for (int j= 0; j< 15; j++) {
				char y = alphCol.charAt(i);
				String coord = Character.toString(y) + j;
				String val = grid1[j][i];//ctrl.getJ1().getGrid1().getCase(coord);
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
						tirerBttn.setEnabled(true);
						deplacerBttn.setEnabled(true);
						if (b.isSelected() == true) { b.setSelected(false); nbBttnSelect--;}
						else {  b.setSelected(true); nbBttnSelect++;}
						coordN = coord;
						indNavire = ctrl.trouverNavireAvecCoord(ctrl.getCurrentJ(), coordN);
						navire = ctrl.getCurrentJ().getNavires().get(indNavire);
					}
				});
				panel_2.add(b);
			}
		}
		panel_3.setPreferredSize(new Dimension(400,400));
		panel_3.setBackground(new Color(9, 10, 11));
		frmNavalBattle.getContentPane().add(panel_3, BorderLayout.EAST);
		panel_3.setLayout(new GridLayout(15, 15, 0, 0));
		for (int i= 0; i< 15; i++) {
			//gbc.gridx = i;
			for (int j= 0; j< 15; j++) {
				char y = alphCol.charAt(i);
				String coord = Character.toString(y) + j;
				String val = grid2[j][i];//ctrl.getJ1().getGrid2().getCase(coord);
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
	}*/
	
	
	
	public void setGrid1(boolean moove) {
		panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(400,400));
		panel_2.setBackground(new Color(39, 101, 149));
		frmNavalBattle.getContentPane().add(panel_2, BorderLayout.WEST);
		panel_2.setLayout(new GridLayout(15, 15, 0, 0));
		Color c = Color.WHITE;
		for (int i= 0; i< 15; i++) {
			for (int j= 0; j< 15; j++) {
				char y = alphCol.charAt(i);
				String coordN = Character.toString(y) + j;
				String cc = ctrl.getCurrentJ().getGrid1().xyToString(i, j);
				String val = "";
				if (moove == true) { val = "ii"; }
				else if (moove == false) { val = grid1[i][j]; }	
				
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
						tirerBttn.setEnabled(true);
						deplacerBttn.setEnabled(true);
						if (b.isSelected() == true) { b.setSelected(false); nbBttnSelect--;}
						else {  b.setSelected(true); nbBttnSelect++;}
						indNavire = ctrl.trouverNavireAvecCoord(ctrl.getCurrentJ(), cc);
						navire = ctrl.getCurrentJ().getNavires().get(indNavire);
						System.out.println(coordN + " " + indNavire + " " + navire.getDisposition() + "  " + cc);
					}
				});
				panel_2.add(b);
			}
		}
	}
	
	public void setGrid2() {
		panel_3.setPreferredSize(new Dimension(400,400));
		panel_3.setBackground(new Color(9, 10, 11));
		frmNavalBattle.getContentPane().add(panel_3, BorderLayout.EAST);
		panel_3.setLayout(new GridLayout(15, 15, 0, 0));
		Color c = Color.WHITE;
		for (int i= 0; i< 15; i++) {
			for (int j= 0; j< 15; j++) {
				char y = alphCol.charAt(j);
				String coordN = Character.toString(y) + i;
				String val = grid2[j][i];
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
						tirerBttn.setEnabled(true);
						deplacerBttn.setEnabled(true);
						if (b.isSelected() == true) { b.setSelected(false); nbBttnSelect--;}
						else {  b.setSelected(true); nbBttnSelect++;}
						System.out.println(coordN);
						indNavire = ctrl.trouverNavireAvecCoord(ctrl.getCurrentJ(), coordN);
						//navire = ctrl.getCurrentJ().getNavires().get(indNavire);
					}
				});
				panel_3.add(b);
			}
		}
	}

}
