/**
 * an enumerator contain with unit of length.
 * 
 * @author Totsapon Menkul.
 *
 */
public enum Length implements Unit {
	METER("Meter", 1.0), KILOMETER("Kilometer", 1000), CENTIMETER("Centimeter", 0.01), MILE("Mile",
			1609.344), INCH("Inch", 0.0254), FOOT("Foot", 0.3048), WA("Wa", 2);

	public final String name;
	public final double length;

	private Length(String name, double length) {
		this.name = name;
		this.length = length;
	}

	@Override
	public double getValue() {
		return this.length;
	}

	public String toString() {
		return this.name;
	}

	public static void main(String[] args) {
		System.out.println(Length.METER.toString());
	}
}
