package view;
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.Dimension;
import controller.*;
import modele.Grille;
import modele.Navire;

/** 
 * La classe implémente la vue concernant le lancement d'une nouvelle partie ou d'une partie chargée.
 * @author OUEDRAOGO Taoufiq
 * @author FONDELOT Timothee
 * @author NSONGO David
 * @author TAKOUGNADI Junior
 */
public class ViewPartie {

	private JFrame frmNavalBattle;
	private Controller ctrl;
	
	String coordN = "";
	String coordCible = "";
	JPanel panel = new JPanel();
	JButton tirerBttn = new JButton("TIRER");
	JButton deplacerBttn = new JButton("DEPLACER");
	int indNavire;
	int indCibleN;
	Navire cibleN;
	Navire navire; 
	boolean fini = false;

	
	JButton[][] grid1;
	JButton[][] grid22;
	String[][] grid2;
	
	JPanel gridPanel = new JPanel();
	JPanel panel_3 = new JPanel();
	String alphCol;
	JButton deplacerHaut = new JButton("HAUT");
	JButton deplacerBas = new JButton("BAS");
	JButton deplacerGauche = new JButton("GAUCHE");
	JButton deplacerDroite = new JButton("DROITE");
	JButton tourBttn = new JButton("FINI");
	JLabel mssgbox = new JLabel("CLIQUEZ SUR UNE CASE DE NAVIRE POUR EXECUTER UNE ACTION");
	private final JPanel panel_4 = new JPanel();

	private final JLabel coordLabel = new JLabel("NAVIRE : ");
	private final JLabel cibleLabel = new JLabel("CIBLE : ");
	private final JLabel coordLab = new JLabel("");
	private final JLabel cibleLab = new JLabel("");
	private final JLabel lblNewLabel_1 = new JLabel("|");
	private final JLabel lblNewLabel_2 = new JLabel("|");
	private final JLabel lblNewLabel_3 = new JLabel("   |   ");
	
	/*
	 * Constructeur
	 */
	public ViewPartie(boolean newPartie, String nameSave){ 
		this.ctrl = new Controller();
		this.grid1 = new JButton[ctrl.nLine][ctrl.nCol];
		this.grid22 = new JButton[ctrl.nLine][ctrl.nCol];
		this.grid2 = new String[ctrl.nLine][ctrl.nCol];
		// NOUVELLE PARTIE
		if (newPartie == true) { 
			ctrl.DistribuerNavire(ctrl.getJ1()); 
			ctrl.DistribuerNavire(ctrl.getJ2());
		}
		
		// CHARGER PARTIE
		if (newPartie == false) { ctrl.chargerPartie(false, nameSave); }
		ctrl.setGrid2(ctrl.getCurrentJ(), ctrl.getEnnemy());	
		alphCol = ctrl.getJ1().getGrid1().getAlphaCol();
		//grid1 = ctrl.getCurrentJ().getGrid1().getCases();
		grid2 = ctrl.getCurrentJ().getGrid2().getCases();

		initialize(); 
		setGrid1();
		setGrid2();
	}
	
	public JFrame getFrame() { return this.frmNavalBattle; }
	/**
	 * @wbp.parser.entryPoint
	 */

	/** 
	 * La fonction permet d'initialiser la vue.
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
		
		panel_3.setEnabled(false);
		panel_4.add(tourBttn);
		tourBttn.setEnabled(false);
		// POUR PASSER AU TOUR DE ORDI
		tourBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){	
				tourBttn.setEnabled(false);
				tourBttn.setSelected(false);
				passerTour();
				tourOrdi();
			}
		});

		
		panel.add(gridPanel);
		panel.add(deplacerHaut);
		
		deplacerHaut.setEnabled(false);
		panel.add(deplacerHaut);
		deplacerHaut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				setDeplacement("0");
				setGrid1();
				setGrid2();
			}
		});
		
		deplacerBas.setEnabled(false);
		panel.add(deplacerBas);
		deplacerBas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				setDeplacement("1");
				setGrid1();
				setGrid2();
			}
		});
		
		deplacerGauche.setEnabled(false);
		panel.add(deplacerGauche);
		deplacerGauche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				setDeplacement("2");
				setGrid1();
				setGrid2();
			}
		});
		
		deplacerDroite.setEnabled(false);
		panel.add(deplacerDroite);
		deplacerDroite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				setDeplacement("3");
				setGrid1();
				setGrid2();
			}
		});
		
		JPanel panel_1 = new JPanel();
		frmNavalBattle.getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		JLabel mssg = new JLabel("  MESSAGE :  ");
		mssg.setForeground(new Color(155, 76, 62));
		panel_1.add(mssg);
		panel_1.add(mssgbox);
		JButton quitBttn = new JButton("SAUVEGARDER ET QUITTER");
		panel_1.add(quitBttn);
		quitBttn.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e){
				frmNavalBattle.dispose();
				ctrl.sauvegarderPartie();
				ViewAcceuil acc = new ViewAcceuil();
				acc.getFrame().setVisible(true);
			}
		});
		
		deplacerBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				panel_3.setEnabled(false);
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
				panel_3.setEnabled(true);
				deplacerBttn.setSelected(false);
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
	
	/** 
	 * La fonction permet d'effectuer le tour de l'ordinateur
	 */
	public void tourOrdi() {
		boolean deplacementFait;
		mssgbox.setText("TOUR ORDI");
		Random randP = new Random();
		int indNv = randP.nextInt(ctrl.getCurrentJ().getNavires().size());
		Navire n = ctrl.getCurrentJ().getNavires().get(indNv);

		System.out.println("\n------\nNavire = "+indNv);
		System.out.println("Joueur : "+ctrl.getCurrentJ().getName());

		// ACTION ALEATOIRE
		int action = 1;//randP.nextInt(2);
		if (action == 0) { 
			int dir = randP.nextInt(4);
			ArrayList<String> newCoords = ctrl.getCurrentJ().deplacer(n, Integer.toString(dir));
			int indCo = randP.nextInt(n.getCasesNavire().size());
			
			System.out.println("Case : "+ n.getCasesNavire().get(indCo));
			System.out.println("Case Touche : "+ n.getCasesTouchees().toString());
			System.out.println("Direction : "+dir + " 0haut, 1bas, 2gauche, 3droite");
			System.out.println("newCoords : " + newCoords);

			if (newCoords == null) {
				System.out.println("newCoords null ordi -> tir");
				deplacementFait = false;
				//passerTour();
			}
			else {
				n.setCasesNavire(newCoords);
				ctrl.getCurrentJ().getNavires().set(indNv, n);
				ctrl.getCurrentJ().setGrid1(ctrl.getCurrentJ().getGrid1());
				grid2 = ctrl.getCurrentJ().getGrid2().getCases();
				tourBttn.setEnabled(true);
				mssgbox.setText("TOUR ORDI FINI");
				passerTour();
				mssgbox.setText("TOUR ORDI FINI");
			}
		}
		if (action == 1) { 
			int indCib = randP.nextInt(ctrl.getCurrentJ().getGrid2().caseNom.size());
			String cib = ctrl.getCurrentJ().getGrid2().caseNom.get(indCib);
			int indNCib = ctrl.trouverNavireAvecCoord(ctrl.getEnnemy(), cib);
			System.out.println("------\ncible = "+ cib);
			if (indNCib > -1) { System.out.println("Navire cible case touchees = "+ ctrl.getEnnemy().getNavires().get(indNCib).getCasesTouchees().toString()); }

			
			ctrl.tirerGraphique(ctrl.getCurrentJ(), ctrl.getEnnemy(), indNv, cib);
			finTir();
			ctrl.setGrid2(ctrl.getCurrentJ(), ctrl.getEnnemy());
			passerTour();
		}		
	}

	
	/** 
	 * La fonction permet d'effectuer le déplacement par le joueur.
	 */
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
			tourBttn.setEnabled(true);
			if (s.equals("0")) { mssgbox.setText("DEPLACEMENT EFFECTUE EN HAUT, CLIQUEZ SUR FINI"); }
			if (s.equals("1")) { mssgbox.setText("DEPLACEMENT EFFECTUE EN BAS, CLIQUEZ SUR FINI"); }
			if (s.equals("2")) { mssgbox.setText("DEPLACEMENT EFFECTUE A GAUCHE, CLIQUEZ SUR FINI"); }
			if (s.equals("3")) { mssgbox.setText("DEPLACEMENT EFFECTUE A DROITE, CLIQUEZ SUR FINI"); }
			return;
		}
		mssgbox.setText("DEPLACEMENT IMPOSSIBLE, TIRER SUR UNE CASE ENNEMIE");
		tirerBttn.setEnabled(true);
	}
	
	/** 
	 * La fonction permet d'initialiser la vue de la grille1.
	 */
	public void setGrid1() {
		gridPanel.removeAll();
		gridPanel = new JPanel();
		gridPanel.setPreferredSize(new Dimension(400,400));
		gridPanel.setBackground(new Color(39, 101, 149));
		frmNavalBattle.getContentPane().add(gridPanel, BorderLayout.WEST);

		GridLayout grid = new GridLayout(ctrl.nLine, ctrl.nCol, 0, 0);
		gridPanel.setLayout(grid);
		Color c = Color.WHITE;

		for (int i = 0; i < ctrl.nLine; i++) {
			for (int j = 0; j < ctrl.nCol; j++) {
				char y = alphCol.charAt(i);
				String coord = Character.toString(y) + j;
				String val = ctrl.getCurrentJ().getGrid1().getCase(coord);
				if (val == "xx") { c = Color.RED;} 
				if (val == "tt") { c = Color.BLACK;} 
				if (val == "oo") { c = Color.GREEN;} 
				if (val == "ss") { c = Color.ORANGE;} 
				if (val == "++") { c = Color.MAGENTA;} 
				if (val == "**") { c = Color.BLUE;} 
				if (val == "  ") { c = Color.WHITE;} 
				grid1[j][i] = new JButton(val);
				grid1[j][i].setForeground(c);

				grid1[j][i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){
						tirerBttn.setSelected(false);
						if (val != "  ") {
							tirerBttn.setEnabled(true);
							deplacerBttn.setEnabled(true);
							indNavire = ctrl.trouverNavireAvecCoord(ctrl.getCurrentJ(), coord);
							if (indNavire > -1) {
								navire = ctrl.getCurrentJ().getNavires().get(indNavire);
								coordLab.setText(coord);
								mssgbox.setText("CHOISISSEZ UNE ACTION");
							}
							else { mssgbox.setText("CHOISISSEZ UN NAVIRE"); }
						}
						else {
							deplacerDroite.setEnabled(false);
							deplacerGauche.setEnabled(false);
							deplacerHaut.setEnabled(false);
							deplacerBas.setEnabled(false);
							tirerBttn.setEnabled(false);
							deplacerBttn.setEnabled(false);
						}
					}
				});	 
				gridPanel.add(grid1[j][i]);
			}
		}
	}
		
	
	/** 
	 * La fonction permet d'initialiser la vue de la grille2.
	 */
	public void setGrid2() {
		panel_3.removeAll();
		panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(400,400));
		panel_3.setBackground(new Color(9, 10, 11));
		frmNavalBattle.getContentPane().add(panel_3, BorderLayout.EAST);
		
		GridLayout grid = new GridLayout(ctrl.nLine, ctrl.nCol, 0, 0);
		panel_3.setLayout(grid);
		Color c = Color.WHITE;

		for (int i= 0; i< ctrl.nLine; i++) {
			for (int j= 0; j< ctrl.nCol; j++) {
				//char yc = alphCol.charAt(j);
				//String coordC = Character.toString(yc) + j;
				String val = grid2[j][i];
				if (val == "xx") { c = Color.RED;} 
				if (val == "tt") { c = Color.BLACK;} 
				if (val == "oo") { c = Color.GREEN;} 
				if (val == "ss") { c = Color.ORANGE;} 
				if (val == "++") { c = Color.MAGENTA;} 
				if (val == "**") { c = Color.BLUE;} 
				if (val == "  ") { c = Color.WHITE;} 
				
				String cc = ctrl.getCurrentJ().getGrid2().xyToString(j,i);
				if (!val.equals("tt") && !val.equals("xx")) {
					grid22[j][i] = new JButton("  ");
				}
				else { grid22[j][i] = new JButton(val); }
				
				grid22[j][i].setBackground(c);
				grid22[j][i].setForeground(c);

				int y = j;
				int x = i;
				grid22[j][i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){
						if (tirerBttn.isSelected() == true) {
							deplacerBttn.setSelected(false);
							cibleLab.setText(cc);
							if (ctrl.getCurrentJ().getGrid2().getCase(cc) == "  ") {
								mssgbox.setText("TIR LOUPE");
								grid22[y][x].setText("tt");
							}
							else {
								grid22[y][x].setText("xx");
							}
						}
						ctrl.tirerGraphique(ctrl.getCurrentJ(), ctrl.getEnnemy(), indNavire, cc);
						tourBttn.setEnabled(true);
						finTir(); 
						tirerBttn.setEnabled(false);
						
						// PREMIER TIR DESTROYER
						if (ctrl.getCurrentJ().getNavires().get(indNavire).getName() == "DESTROYER" && !ctrl.getCurrentJ().getNavires().get(indNavire).getTirFusee()) {
							if (!ctrl.getCurrentJ().getName().equals("ordi")) {
								ctrl.getCurrentJ().getNavires().get(indNavire).tirFusee();
								fusee(cc);
							}
						}
					}
				});
				panel_3.add(grid22[j][i]);
			}
		}
	}

	/** 
	 * La fonction permet d'effectuer le premier tir de fusée du Destroyer.
	 */
	public void fusee(String coordCible) {
		Grille grid2J = ctrl.getCurrentJ().getGrid2();
		int[] tabxy = grid2J.stringToXY(coordCible); 
		ArrayList<String> casesEclairees = new ArrayList<>();
		for (int x= tabxy[0]; x< tabxy[0]+4; x++) {
			for (int y= tabxy[1]; y< tabxy[1]+4; y++) {
				if (x<0 || y<0 || y>=ctrl.nLine || x>=ctrl.nCol) { continue; }
				String c = grid2J.xyToString(x, y);
				casesEclairees.add(c);
			}
		}
		
		mssgbox.setText("AFFICHAGE FUSEE ECLAIRANTE PENDANT 5 SECONDES");
		frmNavalBattle.setEnabled(false);
		
		JDialog fenFin = new JDialog();
		fenFin.setPreferredSize(new Dimension(400,400));
		fenFin.setBounds(100, 100, 400, 400);
		fenFin.setLocationRelativeTo(null); 
		fenFin.setVisible(true);
		fenFin.setResizable(false);
		fenFin.setTitle("AFFICHAGE FUSEE ECLAIRANTE PENDANT 5 SEC");

		JPanel pan = new JPanel(new GridLayout(ctrl.nLine, ctrl.nCol, 0, 0));
		fenFin.getContentPane().add(pan, BorderLayout.CENTER);
		pan.setPreferredSize(new Dimension(300,300));
		pan.setVisible(true);
		for (int i= 0; i< ctrl.nLine; i++) {
			for (int j= 0; j< ctrl.nCol; j++) {
				String cc = ctrl.getCurrentJ().getGrid2().xyToString(j,i);
				JButton b = new JButton(""); 
				if (cc.equals(casesEclairees.get(0))) { b = new JButton("tt"); }
				else if (casesEclairees.contains(cc) && grid2[j][i] != "  ") {
					b = new JButton("ff");
				}
				pan.add(b);
			}
		}
		
		mssgbox.setText("FIN AFFICHAGE FUSEE ECLAIRANTE");
		fenFin.setTitle("FIN FUSEE ECLAIRANTE PENDANT 5 SEC");
		//try { TimeUnit.SECONDS.sleep(3);} catch(Exception e) {System.out.println(e); 
		frmNavalBattle.setEnabled(true);
	}

	/** 
	 * La fonction permet d'initialiser la vue après un tir.
	 */
	public void finTir() {
		if (ctrl.getCurrentJ().getName()!="ordi") { setGrid2(); setGrid1();}
		if (ctrl.getCurrentJ().getName()!="ordi") {mssgbox.setText("TIR EFFECTUE, CLIQUEZ SUR FINI");}
		fini = ctrl.nbCaseNavire == ctrl.getEnnemy().getCasesNaviresTouches().size();
		System.out.println("NB CASES TOUCHEE PAR "+ctrl.getCurrentJ().getName() + " : "+ ctrl.getEnnemy().getCasesNaviresTouches().size());
		
		if (fini) {
			JDialog fenFin = new JDialog();
			fenFin.setPreferredSize(new Dimension(500,100));
			fenFin.setBounds(100, 100, 300, 100);
			fenFin.setLocationRelativeTo(null); 
			fenFin.setVisible(true);
			fenFin.setResizable(false);
			frmNavalBattle.dispose();

			JLabel aff = new JLabel("!!! FIN PARTIE, GAGNANT = "+ctrl.getCurrentJ().getName() + " !!!");
			fenFin.getContentPane().add(aff, BorderLayout.CENTER);
			aff.setVisible(true);
		
			JButton accBttn = new JButton("RETOUR ACCEUIL");
			fenFin.getContentPane().add(accBttn, BorderLayout.SOUTH);
			accBttn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e){
					ViewAcceuil acc = new ViewAcceuil();
					acc.getFrame().setVisible(true);
					fenFin.dispose();
				}
			});
		}
	}
	
	/** 
	 * La fonction permet de passer le tour à l'adversaire.
	 */
	public void passerTour() {
		ctrl.setCurrentJ(ctrl.getEnnemy());
		mssgbox.setText("TOUR PASSE");
		ctrl.setGrid2(ctrl.getCurrentJ(), ctrl.getEnnemy());	
		grid2 = ctrl.getCurrentJ().getGrid2().getCases();
		mssgbox.setText("TOUR PASSE");
		tourBttn.setEnabled(true);
		deplacerBttn.setEnabled(false);
		tirerBttn.setEnabled(false);
		tourBttn.setSelected(false);
		deplacerBttn.setSelected(false);
		indNavire = -1;
		indCibleN = 1;
		cibleN = null;
		navire = null; 
		if (!ctrl.getCurrentJ().getName().equals("ordi")) {
			coordLab.setText("");
			cibleLab.setText("");
			tourBttn.setEnabled(false);
			deplacerBttn.setSelected(false);
			System.out.println(ctrl.getCurrentJ().getName());
			setGrid1();
			setGrid2();
		}
		else {
			coordLab.setText("xx");
			cibleLab.setText("xx");
			tourBttn.setEnabled(false);
			gridPanel.removeAll();
			panel_3.removeAll();
		}
	}
}
