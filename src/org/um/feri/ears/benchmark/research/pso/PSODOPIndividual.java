package org.um.feri.ears.benchmark.research.pso;

import java.util.Arrays;

import org.um.feri.ears.problems.DoubleSolution;
import org.um.feri.ears.problems.StopCriteriaException;
import org.um.feri.ears.problems.Task;
import org.um.feri.ears.util.Util;

public class PSODOPIndividual extends DoubleSolution {
	PSODOPIndividual Pbest;
	double v[];

	public PSODOPIndividual getPbest() {
		return Pbest;
	}

	public void setPbest(PSODOPIndividual Pbest) {
		this.Pbest = Pbest;
	}

	public double[] getV() {
		return v;
	}

	public PSODOPIndividual(Task t) throws StopCriteriaException {
		super(t.getRandomIndividual());
		v = new double[t.getDimensions()];
		double l;
		double r;
		for (int i = 0; i < t.getDimensions(); i++) {
			l = -Math.abs(t.getIntervalRight()[i] - t.getLowerLimit()[i]) / 4;
			r = Math.abs(t.getIntervalRight()[i] - t.getLowerLimit()[i]) / 4;
			v[i] = Util.rnd.nextDouble() * (r - l) + l;
		}
		Pbest = this;
	}

	public PSODOPIndividual(DoubleSolution eval) {
		super(eval);

	}

	@Override
	public String toString() {
		return super.toString() + " v:" + (Arrays.toString(v) + " p:" + Pbest.getEval());
	}

	public PSODOPIndividual update(Task t, double v[]) throws StopCriteriaException {
		double x[] = getNewVariables();
		for (int i = 0; i < x.length; i++) {
			x[i] = t.feasible(x[i] + v[i], i);
		}
		PSODOPIndividual tmp = new PSODOPIndividual(t.eval(x));
		if (t.isFirstBetter(tmp, Pbest)) {
			tmp.Pbest = tmp;
		} else
			tmp.Pbest = Pbest;
		tmp.v = v;
		return tmp;

	}
}