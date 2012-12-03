package learning;

import algo.Algorithms;
import elements.Matrix;
import exceptions.InCompatibleElementException;

public class CircularClassifier
{
	private static Matrix y;
	private static Matrix error;
	private static double n;
	private static Matrix newInput;
	
	public static Matrix train(Matrix wt, Matrix input, Matrix expected)
	{
		try
		{
			newInput = new Matrix(input.size(), 1);
		}
		catch (InCompatibleElementException e1)
		{
		}
		try
		{
			for (int i = 0; i < input.size(); i++)
			{
				newInput.set(i, 0, input.getValueAt(i, 0) * input.getValueAt(i, 0));
			}
		}
		catch (InCompatibleElementException e)
		{
		}
		try
		{
			y = Algorithms.UnitStep(wt.cross(newInput));
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
			n = (newInput.max() - newInput.min()) / newInput.size();
		}
		catch (InCompatibleElementException e)
		{
			e.printStackTrace();
		}
		
		try
		{
			Matrix temp = error.cross(newInput.transpose()).dot(n);
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
			newInput = new Matrix(input.size(), 1);
		}
		catch (InCompatibleElementException e1)
		{
		}
		try
		{
			for (int i = 0; i < input.size(); i++)
			{
				newInput.set(i, 0, input.getValueAt(i, 0) * input.getValueAt(i, 0));
			}
		}
		catch (InCompatibleElementException e)
		{
		}
		try
		{
			output = Algorithms.UnitStep(wt.cross(newInput));
		}
		catch (InCompatibleElementException e)
		{
			e.printStackTrace();
		}
		return (output);
	}
}
