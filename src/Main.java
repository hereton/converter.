/**
 * launch the program.
 * 
 * @author Totsapon Menkul.
 *
 */
public class Main {
	public static void main(String[] args) {
		UnitConverter uc = new UnitConverter();
		ConverterUI ui = new ConverterUI(uc);
		ui.run();
	}
}
