import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * User Interface for Length converter
 * 
 * @author Totsapon Menkul
 *
 */
public class ConverterUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private UnitConverter uc;
	private JButton convertButton, clearButton;
	private JTextField leftField, rightField;
	private JComboBox<Unit> unit1, unit2;
	private JLabel equalLabel;
	private JRadioButton LtoR, RtoL;
	private ButtonGroup group;
	private static boolean isRight = false;

	public ConverterUI(UnitConverter uc) {
		this.uc = uc;
		this.setTitle("Length Converter");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initComponents();
	}

	/**
	 * create components
	 */
	public void initComponents() {
		JPanel panel = new JPanel();
		JPanel downPanel = new JPanel();
		convertButton = new JButton("Convert");
		clearButton = new JButton("Clear !!");
		leftField = new JTextField(7);
		rightField = new JTextField(7);
		equalLabel = new JLabel("=");
		unit1 = new JComboBox<Unit>();
		unit2 = new JComboBox<Unit>();
		LtoR = new JRadioButton("Left to right");
		RtoL = new JRadioButton("Right to left");
		group = new ButtonGroup();
		group.add(LtoR);
		group.add(RtoL);

		// set default
		LtoR.setSelected(true);
		rightField.setEditable(false);

		Unit[] lengths = Length.values();
		for (Unit unit : lengths) {
			unit1.addItem(unit);
			unit2.addItem(unit);
		}

		EnterListener enterListener = new EnterListener();
		convertButton.addActionListener(enterListener);
		leftField.addKeyListener(enterListener);
		rightField.addKeyListener(enterListener);
		ClearListener clearListener = new ClearListener();
		clearButton.addActionListener(clearListener);
		PickListener pickListener = new PickListener();
		LtoR.addActionListener(pickListener);
		RtoL.addActionListener(pickListener);

		panel.add(leftField);
		panel.add(unit1);
		panel.add(equalLabel);
		panel.add(rightField);
		panel.add(unit2);
		panel.add(convertButton);
		panel.add(clearButton);

		downPanel.add(LtoR);
		downPanel.add(RtoL);

		this.add(panel);
		this.add(downPanel, BorderLayout.SOUTH);
		this.pack();

	}

	class EnterListener implements ActionListener, KeyListener {
		@Override
		public void actionPerformed(ActionEvent event) {

			try {
				rightField.setForeground(null);
				leftField.setForeground(null);
				if (isRight) {
					double input = Double.parseDouble(rightField.getText().trim());
					Unit fromUnit = (Unit) unit2.getSelectedItem();
					Unit toUnit = (Unit) unit1.getSelectedItem();
					leftField.setText(uc.convert(input, fromUnit, toUnit) + "");
				} else {
					double input = Double.parseDouble(leftField.getText().trim());
					Unit fromUnit = (Unit) unit1.getSelectedItem();
					Unit toUnit = (Unit) unit2.getSelectedItem();
					rightField.setText(uc.convert(input, fromUnit, toUnit) + "");
				}
			} catch (NumberFormatException e) {
				if (isRight) {
					rightField.setForeground(Color.red);
				} else {
					leftField.setForeground(Color.red);
				}
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent event) {
			if (event.getKeyCode() == KeyEvent.VK_ENTER) {
				actionPerformed(null);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}

	}

	class PickListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			if (RtoL.isSelected()) {
				LtoR.setSelected(false);
				leftField.setEditable(false);
				rightField.setEditable(true);
				isRight = true;

			}
			if (LtoR.isSelected()) {
				RtoL.setSelected(false);
				leftField.setEditable(true);
				rightField.setEditable(false);
				isRight = false;
			}
		}
	}

	class ClearListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			leftField.setText("");
			rightField.setText("");
		}
	}

	public void run() {
		this.setVisible(true);
	}
}
