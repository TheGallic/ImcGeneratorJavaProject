package com.daviddu64;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ImcCalculator extends JFrame {

	private JPanel contentPane;
	private JTextField tfd_size;
	private JTextField tfd_weight;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImcCalculator frame = new ImcCalculator();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ImcCalculator() {
		setFont(new Font("Arial", Font.PLAIN, 14));
		setResizable(false);
		setTitle("Calcualateur IMC");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setFont(new Font("Arial", Font.PLAIN, 14));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		tfd_size = new JTextField();
		tfd_size.setFont(new Font("Arial", Font.PLAIN, 14));
		tfd_size.setBounds(10, 11, 86, 32);
		contentPane.add(tfd_size);
		tfd_size.setColumns(10);

		JLabel lbl_Size = new JLabel("Votre Taille en CM");
		lbl_Size.setFont(new Font("Arial", Font.PLAIN, 14));
		lbl_Size.setLabelFor(tfd_size);
		lbl_Size.setBounds(106, 12, 137, 28);
		contentPane.add(lbl_Size);

		tfd_weight = new JTextField();
		tfd_weight.setFont(new Font("Arial", Font.PLAIN, 14));
		tfd_weight.setBounds(10, 54, 86, 32);
		contentPane.add(tfd_weight);
		tfd_weight.setColumns(10);

		JLabel lbl_weight = new JLabel("Votre poid en KG");
		lbl_weight.setLabelFor(tfd_weight);
		lbl_weight.setFont(new Font("Arial", Font.PLAIN, 14));
		lbl_weight.setBounds(106, 51, 124, 35);
		contentPane.add(lbl_weight);

		JButton btn_calculate = new JButton("Calculer l'IMC");

		btn_calculate.setFont(new Font("Arial", Font.PLAIN, 14));
		btn_calculate.setBounds(10, 97, 220, 32);
		contentPane.add(btn_calculate);

		JLabel lb_resultat = new JLabel("En attente...");
		lb_resultat.setHorizontalAlignment(SwingConstants.CENTER);
		lb_resultat.setForeground(new Color(0, 128, 0));
		lb_resultat.setFont(new Font("Arial", Font.BOLD, 18));
		lb_resultat.setBounds(10, 154, 414, 32);
		contentPane.add(lb_resultat);

		JLabel lbl_imc = new JLabel("IMC: 0");
		lbl_imc.setHorizontalTextPosition(SwingConstants.CENTER);
		lbl_imc.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_imc.setFont(new Font("Arial", Font.BOLD, 18));
		lbl_imc.setBounds(253, 12, 171, 74);
		contentPane.add(lbl_imc);

		btn_calculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// On verifie si les champs sont vide est si c'est un chiffre
				String str = tfd_size.getText();
				String str1 = tfd_weight.getText();
				boolean isNumeric = str.matches("[+-]?\\d*(\\.\\d+)?");
				boolean isNumeric1 = str1.matches("[+-]?\\d*(\\.\\d+)?");
				if (isNumeric == false || isNumeric1 == false || str.isEmpty() == true || str1.isEmpty() == true) {

					JOptionPane.showMessageDialog(null, "ECrivez un chiffre!!");
				} else {
					int poids = Integer.valueOf(tfd_weight.getText());
					int taille = Integer.valueOf(tfd_size.getText());
					double imc = 0;
					imc = (double) ((poids * 10000.0) / (taille * taille));
					DecimalFormat df = new DecimalFormat("##.#");
					String finalImc = df.format(imc);
					lbl_imc.setText("IMC: " + finalImc);
					// On affiche différents résultats suivant l'indice de masse corporelle
					if (imc < 18.5) {
						lb_resultat.setText("Insuffisance pondérale (maigreur)");
						lb_resultat.setForeground(Color.red);
					} else if (imc > 18.5 && imc < 25) {
						lb_resultat.setText("Corpulence normale");
						lb_resultat.setForeground(Color.GREEN);
					} else if (imc > 25 && imc < 30) {
						lb_resultat.setText("Surpoids");
						lb_resultat.setForeground(Color.orange);
					} else if (imc > 30 && imc < 35) {
						lb_resultat.setText("Obésité modérée");
						lb_resultat.setForeground(Color.orange);
					} else if (imc > 35 && imc < 40) {
						lb_resultat.setText("Obésité sévère");
						lb_resultat.setForeground(Color.red);
					} else if (imc > 40) {
						lb_resultat.setText("Obésité morbide ou massive");
						lb_resultat.setForeground(Color.red);
					}
				}

			}
		});
	}
}
