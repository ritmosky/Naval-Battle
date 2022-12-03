package view;


public class vueJeu {
	
	static int l = 14;
	static int c = 14;
	static int longueur = 1000;
	static int largeur = 1000;
	
	public static void main(String[] args){/*
		JFrame Plateau = new JFrame("Battaille Navalle");
		JPanel FenetreGrille = new JPanel();
		JPanel FenetreGrilleEnemy = new JPanel();
		JPanel FenetreMenu = new JPanel();
		JPanel FenetreTitle = new JPanel();
		JLabel LabelTitre = new JLabel("TOUR DE J1");
		JLabel LabelMenu = new JLabel("MENU ACTION");
		
		FenetreMenu.add(LabelMenu);
		FenetreTitle.add(LabelTitr
		e);
		
		Plateau.setSize(longueur-10,largeur-10);
		Plateau.setResizable(true);
		Plateau.setLocationRelativeTo(null); 
		Plateau.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 		
		Plateau.setVisible(true);
		
		FenetreTitle.setPreferredSize(new Dimension(longueur-10, 100));
		FenetreGrille.setPreferredSize(new Dimension(450,450));
		FenetreMenu.setPreferredSize(new Dimension(longueur-10, 100));
		FenetreGrilleEnemy.setPreferredSize(new Dimension(450,450));
		
		FenetreGrilleEnemy.setVisible(true);
		FenetreTitle.setVisible(true);
		FenetreMenu.setVisible(true);
		FenetreGrille.setVisible(true);
		
		FenetreGrille.setLayout(new GridLayout(l,c));

		Plateau.add(FenetreTitle, BorderLayout.NORTH);
		Plateau.add(FenetreMenu);
		
		for (int i= 0; i< l; i++) {
			//gbc.gridx = i;
			for (int j= 0; j< c; j++) {
				//gbc.gridy = j;
				//String coord = grid.xyToString(l,c);
				//String symb = grid.getCase(coord);
				//frame.add(new Button(symb));
				String name = "**";
				Button b = new Button(name);
				//b.setSize(20,20);
				FenetreGrille.add(b);
			}
		}
		
		FenetreGrilleEnemy.setLayout(new GridLayout(l,c));
		
		for (int i= 0; i< l; i++) {
			//gbc.gridx = i;
			for (int j= 0; j< c; j++) {
				//gbc.gridy = j;
				//String coord = grid.xyToString(l,c);
				//String symb = grid.getCase(coord);
				//frame.add(new Button(symb));
				String name = "ss";
				Button b = new Button(name);
				FenetreGrilleEnemy.add(b);
			}
		}
		
		
		FenetreTitle.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLUE));
		FenetreMenu.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLUE));
		FenetreGrille.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLUE));
		FenetreGrilleEnemy.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLUE));

		
		Plateau.add(FenetreGrille, BorderLayout.WEST);
		Plateau.add(FenetreGrilleEnemy);*/
		ViewAcceuil acc = new ViewAcceuil();
		acc.getFrame().setVisible(true);
		}
}	