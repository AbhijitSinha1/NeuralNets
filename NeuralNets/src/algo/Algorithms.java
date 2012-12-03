package algo;

import elements.Matrix;
import exceptions.InCompatibleElementException;

public class Algorithms
{
	public static Matrix UnitStep(Matrix mat) throws InCompatibleElementException
	{
		if ((mat.getM() == 1) && (mat.getN() != 1))
		{
			Matrix retMat = new Matrix(1, mat.getN());
			for (int i = 0; i < mat.getN(); i++)
			{
				if (mat.getValueAt(0, i) <= 0)
				{
					retMat.set(0, i, 0);
				}
				else
				{
					retMat.set(0, i, 1);
				}
			}
			return retMat;
			
		}
		if ((mat.getM() != 1) && (mat.getN() == 1))
		{
			Matrix retMat = new Matrix(mat.getM(), 1);
			for (int i = 0; i < mat.getM(); i++)
			{
				if (mat.getValueAt(i, 0) <= 0)
				{
					retMat.set(i, 0, 0);
				}
				else
				{
					retMat.set(i, 0, 1);
				}
			}
			return retMat;
			
		}
		if ((mat.getM() == 1) && (mat.getN() == 1))
		{
			Matrix retMat = new Matrix(1, 1);
			if (mat.getValueAt(0, 0) <= 0)
			{
				retMat.set(0, 0, 0);
			}
			else
			{
				retMat.set(0, 0, 1);
			}
			return (retMat);
		}
		throw new InCompatibleElementException("Check Input");
	}
}
