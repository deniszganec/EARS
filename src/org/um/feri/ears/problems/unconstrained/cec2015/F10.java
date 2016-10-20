package org.um.feri.ears.problems.unconstrained.cec2015;

import java.util.List;

public class F10 extends CEC2015 {
	
	public F10(int d) {
		super(d,10);

		name = "F10 Hybrid Function 1";
	}

	@Override
	public double eval(List<Double> ds) {
		double F;
		F = Functions.hf01(ds, numberOfDimensions, OShift, M, SS, 1, 1);
		return F;
	}
	
	public double eval(double x[]) {
		double F;
		F = Functions.hf01(x, numberOfDimensions, OShift, M, SS, 1, 1);
		return F;
	}
}
