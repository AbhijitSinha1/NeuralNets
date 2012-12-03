package elements;

import exceptions.InCompatibleElementException;

public class Vector
{
	boolean row = true;
	private double[] vector;
	
	private Vector(double[] vector)
	{
		this.vector = new double[vector.length];
		for (int i = 0; i < vector.length; i++)
		{
			this.vector[i] = vector[i];
		}
	}
	
	public Vector(int m, boolean isRow)
	{
		this.vector = new double[m];
		this.row = isRow;
	}
	
	public Vector(double[] vector, boolean isRow)
	{
		for (int i = 0; i < vector.length; i++)
		{
			this.vector[i] = vector[i];
		}
		this.row = isRow;
	}
	
	public void set(int i, double val)
	{
		this.vector[i] = val;
	}
	
	public Vector transpose()
	{
		this.row = !this.row;
		return this;
	}
	
	public double getValueAt(int i)
	{
		return this.vector[i];
	}
	
	public int size()
	{
		return this.vector.length;
	}
	
	public Vector cross(Vector vec) throws InCompatibleElementException
	{
		if ((vec.size() != this.size()) || (vec.isRow() && !this.isRow()) || (!vec.isRow() && this.isRow()))
		{
			throw new InCompatibleElementException("Vector 1 (" + this.size() + ") is " + (this.isRow() ? "Row" : "Column") + " Vector and Vector 2 (" + vec.size() + ") is " + (this.isRow() ? "Row" : "Column") + " Vector");
		}
		double arr[] = new double[vec.size()];
		for (int i = 0; i < arr.length; i++)
		{
			arr[i] = vec.getValueAt(i) * this.getValueAt(i);
		}
		Vector retVec = new Vector(arr);
		return (retVec);
	}
	
	public Vector dot(double val)
	{
		double arr[] = new double[this.size()];
		for (int i = 0; i < this.size(); i++)
		{
			arr[i] = this.getValueAt(i) * val;
		}
		Vector retVec = new Vector(arr);
		return (retVec);
	}
	
	private boolean isRow()
	{
		return this.row;
	}
	
}
