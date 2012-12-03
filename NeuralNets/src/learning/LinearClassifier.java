package learning;

import algo.Algorithms;
import elements.Matrix;
import exceptions.InCompatibleElementException;

public class LinearClassifier
{
	private static Matrix y;
	private static Matrix error;
	private static double n;
	
	public static Matrix train(Matrix wt, Matrix input, Matrix expected)
	{
		try
		{
			y = Algorithms.UnitStep(wt.cross(input));
		}
		catch (InCompatibleElementException e)
		{
			e.printStackTrace();
		}
		
		try
		{
			error = expected.minus(y);
		}
		catch (InCompatibleElementException e)
		{
			e.printStackTrace();
		}
		
		try
		{
			n = (input.max() - input.min()) / input.size();
		}
		catch (InCompatibleElementException e)
		{
			e.printStackTrace();
		}
		
		try
		{
			Matrix temp = error.cross(input.transpose()).dot(n);
			wt = wt.plus(temp);
			
		}
		catch (InCompatibleElementException e)
		{
			e.printStackTrace();
		}
		return (wt);
	}
	
	public static Matrix run(Matrix wt, Matrix input)
	{
		Matrix output = null;
		try
		{
			output = Algorithms.UnitStep(wt.cross(input));
		}
		catch (InCompatibleElementException e)
		{
			e.printStackTrace();
		}
		return (output);
	}
}
