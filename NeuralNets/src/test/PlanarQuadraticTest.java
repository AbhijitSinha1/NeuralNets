package test;

import learning.QuadraticClassifier;
import elements.Matrix;
import exceptions.InCompatibleElementException;

public class PlanarQuadraticTest
{
	Matrix x = new Matrix(3, 1);
	Matrix t = new Matrix(1, 1);
	Matrix v = new Matrix(1, 4);
	private static final int NUM = 1000;
	private static final int NUM_RUN = 100000;
	private int falseCount = 0;
	
	public static void main(String[] args)
	{
		new PlanarQuadraticTest().start();
	}
	
	private void start()
	{
		this.v.random(1);
		for (int i = 0; i < PlanarQuadraticTest.NUM; i++)
		{
			this.x.random(2);
			this.x.set(0, 0, 1);
			if ((this.x.getValueAt(1, 0) * this.x.getValueAt(1, 0)) < this.x.getValueAt(2, 0))
			{
				this.t.set(0, 0, 0);
			}
			else
			{
				this.t.set(0, 0, 1);
			}
			this.v = QuadraticClassifier.train(this.v, this.x, this.t);
		}
		double[][] arr = new double[100][100];
		for (int i = 0; i < (NUM_RUN); i++)
		{
			this.x.random(2);
			this.x.set(0, 0, 1);
			if ((this.x.getValueAt(1, 0) * this.x.getValueAt(1, 0)) < this.x.getValueAt(2, 0))
			{
				this.t.set(0, 0, 0);
			}
			else
			{
				this.t.set(0, 0, 1);
			}
			Matrix output = QuadraticClassifier.run(this.v, this.x);
			try
			{
				arr[(int) (50 * this.x.getValueAt(1, 0))][(int) (50 * this.x.getValueAt(2, 0))] = output.detreminant() == 1 ? 255 : 0;
			}
			catch (InCompatibleElementException e)
			{
				e.printStackTrace();
			}
			if (!output.equals(this.t))
			{
				this.falseCount++;
			}
		}
		Matrix m = new Matrix(arr);
		System.out.println(m);
		System.out.println(((this.falseCount * 100) / (double) (PlanarQuadraticTest.NUM_RUN)) + "% error");
	}
}
