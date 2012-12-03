package elements;

import exceptions.InCompatibleElementException;

public class Matrix
{
	private double[][] matrix;
	
	public Matrix(double[][] matrix)
	{
		this.matrix = new double[matrix.length][matrix[0].length];
		for (int i = 0; i < matrix.length; i++)
		{
			for (int j = 0; j < matrix[i].length; j++)
			{
				this.matrix[i][j] = matrix[i][j];
			}
		}
	}
	
	public Matrix(int m, int n)
	{
		this.matrix = new double[m][n];
	}
	
	public Matrix(double[][][] arr)
	{
		
	}
	
	public void set(int i, int j, double val)
	{
		this.matrix[i][j] = val;
	}
	
	public Matrix transpose()
	{
		Matrix retMat = new Matrix(getN(), getM());
		for (int i = 0; i < getM(); i++)
		{
			for (int j = 0; j < getN(); j++)
			{
				retMat.set(j, i, this.getValueAt(i, j));
			}
		}
		return (retMat);
	}
	
	public Matrix cross(Matrix matrix2) throws InCompatibleElementException
	{
		if (this.getN() != matrix2.getM())
		{
			throw new InCompatibleElementException("Matrix 1 (" + this.getM() + "X" + this.getN() + ") and Matrix 2 (" + matrix2.getM() + "X" + matrix2.getN() + ") cannot be cross multiplied");
		}
		double arr[][] = new double[this.getM()][matrix2.getN()];
		
		for (int i = 0; i < arr.length; i++)
		{
			for (int j = 0; j < arr[i].length; j++)
			{
				double val = 0;
				for (int k = 0; k < this.getN(); k++)
				{
					val += this.getValueAt(i, k) * matrix2.getValueAt(k, j);
				}
				arr[i][j] = val;
			}
		}
		
		Matrix retMat = new Matrix(arr);
		return retMat;
	}
	
	public Matrix dot(double val)
	{
		double arr[][] = new double[this.getM()][this.getN()];
		for (int i = 0; i < this.getM(); i++)
		{
			for (int j = 0; j < this.getN(); j++)
			{
				arr[i][j] = this.getValueAt(i, j) * val;
			}
		}
		Matrix retMat = new Matrix(arr);
		return retMat;
	}
	
	public double getValueAt(int i, int j)
	{
		return this.matrix[i][j];
	}
	
	public int getM()
	{
		return this.matrix.length;
	}
	
	public int getN()
	{
		return this.matrix[0].length;
	}
	
	public void setIdentity() throws InCompatibleElementException
	{
		if (this.matrix.length != this.matrix[0].length)
		{
			throw new InCompatibleElementException("Matrix (" + this.getM() + "X" + this.getN() + ") is not a square matrix, cannot set it to identity matrix");
		}
		setZero();
		for (int i = 0; i < this.matrix.length; i++)
		{
			this.matrix[i][i] = 1.0;
		}
	}
	
	public void setZero()
	{
		for (int i = 0; i < this.matrix.length; i++)
		{
			for (int j = 0; j < this.matrix[0].length; j++)
			{
				this.matrix[i][j] = 0;
			}
		}
	}
	
	public double detreminant() throws InCompatibleElementException
	{
		if (!this.isSquare())
		{
			throw new InCompatibleElementException("Matrix (" + this.getM() + "X" + this.getN() + ") is not a square matrix, cannot compute determinant");
		}
		double retVal = 0;
		if (this.getM() > 1)
		{
			for (int i = 0; i < this.matrix.length; i++)
			{
				retVal += coeffMatrix(i, 0).detreminant() * Math.pow(-1, i) * this.getValueAt(i, 0);
			}
		}
		else
		{
			retVal = this.getValueAt(0, 0);
		}
		return (retVal);
	}
	
	private boolean isSquare()
	{
		return this.getM() == this.getN();
	}
	
	private Matrix coeffMatrix(int m, int n)
	{
		Matrix retMat = new Matrix(this.getM() - 1, this.getN() - 1);
		int p = 0, q = 0;
		for (int i = 0; i < this.getM(); i++)
		{
			for (int j = 0; j < this.getN(); j++)
			{
				p = i;
				q = j;
				if ((i == m) || (j == n))
				{
					continue;
				}
				if (i > m)
				{
					p = i - 1;
				}
				if (j > n)
				{
					q = j - 1;
				}
				retMat.set(p, q, this.getValueAt(i, j));
			}
		}
		return retMat;
	}
	
	@Override
	public String toString()
	{
		String retStr = "";
		for (double[] element : this.matrix)
		{
			retStr += "[";
			for (int j = 0; j < this.matrix[0].length; j++)
			{
				retStr += element[j] + "\t";
			}
			retStr += "];\n";
		}
		return "[" + retStr + "]";
	}
	
	public void random(double d)
	{
		for (int i = 0; i < this.getM(); i++)
		{
			for (int j = 0; j < this.getN(); j++)
			{
				this.matrix[i][j] = d * Math.random();
			}
		}
	}
	
	public Matrix plus(Matrix mat) throws InCompatibleElementException
	{
		if ((mat.getM() == this.getM()) && (mat.getN() == this.getN()))
		{
			Matrix retMat = new Matrix(this.getM(), this.getN());
			for (int i = 0; i < this.getM(); i++)
			{
				for (int j = 0; j < this.getN(); j++)
				{
					retMat.set(i, j, this.getValueAt(i, j) + mat.getValueAt(i, j));
				}
			}
			return (retMat);
		}
		throw new InCompatibleElementException("Matrix (" + this.getM() + "X" + this.getN() + ") cannot be added to Matrix (" + mat.getM() + "X" + mat.getN() + ")");
	}
	
	public Matrix minus(Matrix mat) throws InCompatibleElementException
	{
		if ((mat.getM() == this.getM()) && (mat.getN() == this.getN()))
		{
			Matrix retMat = new Matrix(this.getM(), this.getN());
			for (int i = 0; i < this.getM(); i++)
			{
				for (int j = 0; j < this.getN(); j++)
				{
					retMat.set(i, j, this.getValueAt(i, j) - mat.getValueAt(i, j));
				}
			}
			return (retMat);
		}
		throw new InCompatibleElementException("Matrix (" + this.getM() + "X" + this.getN() + ") cannot be added to Matrix (" + mat.getM() + "X" + mat.getN() + ")");
	}
	
	public Matrix ones()
	{
		for (int i = 0; i < this.getM(); i++)
		{
			for (int j = 0; j < this.getN(); j++)
			{
				this.set(i, j, 1);
			}
		}
		return this;
	}
	
	public boolean equals(Matrix mat)
	{
		if ((mat.getM() != this.getM()) || (mat.getN() != this.getN()))
		{
			return false;
		}
		for (int i = 0; i < this.getM(); i++)
		{
			for (int j = 0; j < this.getN(); j++)
			{
				if (this.getValueAt(i, j) != mat.getValueAt(i, j))
				{
					return false;
				}
			}
		}
		return (true);
	}
	
	public double max()
	{
		double retVal = Double.MIN_VALUE;
		for (int i = 0; i < this.getM(); i++)
		{
			for (int j = 0; j < this.getN(); j++)
			{
				if (retVal < this.getValueAt(i, j))
				{
					retVal = this.getValueAt(i, j);
				}
			}
		}
		return retVal;
	}
	
	public double min()
	{
		double retVal = Double.MAX_VALUE;
		for (int i = 0; i < this.getM(); i++)
		{
			for (int j = 0; j < this.getN(); j++)
			{
				if (retVal > this.getValueAt(i, j))
				{
					retVal = this.getValueAt(i, j);
				}
			}
		}
		return retVal;
	}
	
	public int size() throws InCompatibleElementException
	{
		if (this.getM() == 1)
		{
			return this.getN();
		}
		if (this.getN() == 1)
		{
			return this.getM();
		}
		throw new InCompatibleElementException("The Matrix (" + this.getM() + "X" + this.getN() + ") is not row or column matrix");
	}
	
	public double sum()
	{
		double sum = 0;
		for (int i = 0; i < this.getM(); i++)
		{
			for (int j = 0; j < this.getN(); j++)
			{
				sum += this.getValueAt(i, j);
			}
		}
		return sum;
	}
}