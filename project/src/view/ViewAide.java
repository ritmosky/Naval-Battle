package view;

import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextPane;

public class ViewAide {

	private JFrame frame;

	public ViewAide() { initialize(); }

	public JFrame getFrame() { return this.frame; }
	 
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("AIDE DE JEU");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel, BorderLayout.NORTH);

		JButton acceuilBttn = new JButton("Retour sur la page d'Acceuil");
		frame.getContentPane().add(acceuilBttn, BorderLayout.SOUTH);
		
		acceuilBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				ViewAcceuil acc = new ViewAcceuil();
				acc.getFrame().setVisible(true);
				frame.dispose();
			}
		});

		JTextPane txtpnDumApudPersas = new JTextPane();
		txtpnDumApudPersas.setText("Dum apud Persas, ut supra narravimus, perfidia regis motus agitat insperatos, et in eois tractibus bella rediviva consurgunt, anno sexto decimo et eo diutius post Nepotiani exitium, saeviens per urbem aeternam urebat cuncta Bellona, ex primordiis minimis ad clades excita luctuosas, quas obliterasset utinam iuge silentium! ne forte paria quandoque temptentur, plus exemplis generalibus nocitura quam delictis.\nHinc ille commotus ut iniusta perferens et indigna praefecti custodiam protectoribus mandaverat fidis. quo conperto Montius tunc quaestor acer quidem sed ad lenitatem propensior, consulens in commune advocatos palatinarum primos scholarum adlocutus est mollius docens nec decere haec fieri nec prodesse addensque vocis obiurgatorio sonu quod si id placeret, post statuas Constantii deiectas super adimenda vita praefecto conveniet securius cogitari.\nQuod opera consulta cogitabatur astute, ut hoc insidiarum genere Galli periret avunculus, ne eum ut praepotens acueret in fiduciam exitiosa coeptantem. verum navata est opera diligens hocque dilato Eusebius praepositus cubiculi missus est Cabillona aurum secum perferens, quo per turbulentos seditionum concitores occultius distributo et tumor consenuit militum et salus est in tuto locata praefecti. deinde cibo abunde perlato castra die praedicto sunt mota.\n");
		frame.getContentPane().add(txtpnDumApudPersas, BorderLayout.CENTER);
	}
}
