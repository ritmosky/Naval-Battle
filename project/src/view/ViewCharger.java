package view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;

public class ViewCharger {

	private JFrame frame;
	private JButton chargBttn;
	private JButton accBttn;
	private JList list;

	public ViewCharger() {
		initialize();
	}

	public JFrame getFrame() { return this.frame; }
	
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		
		JLabel lblNewLabel = new JLabel("CHARGER UNE PARTIE");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel, BorderLayout.NORTH);
		
		
		File doss = new File("../../resources/sauvegarde/"); 
	    String[] fichs = new String[15];
		int nb = 0;
		for (File fich : doss.listFiles()) { 
			if(fich.getName().startsWith("save")) {
				fichs[nb] = fich.getName();
				nb++;
			}
		}

		list = new JList(fichs);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		frame.getContentPane().add(list, BorderLayout.CENTER);
		
		
		chargBttn = new JButton("CHARGER");
		frame.getContentPane().add(chargBttn, BorderLayout.EAST);
		if (nb == 0) { chargBttn.setEnabled(false); }
		chargBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				String name = (String)list.getSelectedValue();
				File nouvFich = new File ("../../resources/sauvegarde/" + name);
				if (nouvFich.exists()) {
					System.out.println(nouvFich.getName());
					ViewPartie partie = new ViewPartie(true);
					partie.getFrame().setVisible(true);
					frame.dispose();				
				}
			}
		});
		
		accBttn = new JButton("RETOUR ACCEUIL");
		frame.getContentPane().add(accBttn, BorderLayout.SOUTH);
		accBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				ViewAcceuil acc = new ViewAcceuil();
				acc.getFrame().setVisible(true);
				frame.dispose();
			}
		});
	}

}
