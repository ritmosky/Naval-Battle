package view;

import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

public class ViewAcceuil {

	private JFrame frmNavalBattle;

	public ViewAcceuil() { initialize(); }
	
	public JFrame getFrame() { return this.frmNavalBattle; }

	private void initialize() {
		frmNavalBattle = new JFrame();
		frmNavalBattle.setResizable(false);
		frmNavalBattle.setBackground(new Color(0, 0, 0));
		frmNavalBattle.setTitle("NAVAL BATTLE");
		frmNavalBattle.setBounds(100, 100, 690, 500);
		frmNavalBattle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNavalBattle.setLocationRelativeTo(null);
		
		JButton nouvPartBttn = new JButton("Nouvelle Partie");

		nouvPartBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				ViewPartie newp = new ViewPartie(false);
				newp.getFrame().setVisible(true);
				frmNavalBattle.dispose();
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon("/Users/taoufiq/Documents/java/Naval-Battle/resources/worldofwarchips.jpg"));
		
		JButton chargPartBttn = new JButton("Charger Partie");
		
		JButton aideBttn = new JButton("Afficher Aide");
		GroupLayout groupLayout = new GroupLayout(frmNavalBattle.getContentPane());
		
		aideBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				ViewAide acc = new ViewAide();
				acc.getFrame().setVisible(true);
				frmNavalBattle.dispose();
			}
		});
		
		
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 690, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(267)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(aideBttn, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
						.addComponent(chargPartBttn)
						.addComponent(nouvPartBttn))
					.addContainerGap(285, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 313, Short.MAX_VALUE)
					.addGap(39)
					.addComponent(nouvPartBttn)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(chargPartBttn, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(aideBttn)
					.addGap(15))
		);
		frmNavalBattle.getContentPane().setLayout(groupLayout);
	}
}
