import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

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

		Unit[] lengths = Length.values();
		for (Unit unit : lengths) {
			unit1.addItem(unit);
			unit2.addItem(unit);
		}

		EnterListener enterListener = new EnterListener();
		convertButton.addActionListener(enterListener);
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

	class EnterListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			if (isRight) {
				double input = Double.parseDouble(rightField.getText());
				Unit fromUnit = (Unit) unit2.getSelectedItem();
				Unit toUnit = (Unit) unit1.getSelectedItem();
				leftField.setText(uc.convert(input, fromUnit, toUnit) + "");
			} else {
				double input = Double.parseDouble(leftField.getText());
				Unit fromUnit = (Unit) unit1.getSelectedItem();
				Unit toUnit = (Unit) unit2.getSelectedItem();
				rightField.setText(uc.convert(input, fromUnit, toUnit) + "");

			}

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

	public static void main(String[] args) {
		UnitConverter uc = new UnitConverter();
		ConverterUI ui = new ConverterUI(uc);
		ui.run();
	}
}
