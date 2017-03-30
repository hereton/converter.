
public class UnitConverter {
	public double convert(double input, Unit fromUnit, Unit toUnit) {
		
		return (input * fromUnit.getValue()) / toUnit.getValue();
	}

	public Unit[] getUnits() {
		return Length.values();
	}
}
