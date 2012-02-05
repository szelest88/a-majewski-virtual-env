package edu.dhbw.andar.sample;

public class Vertex{
	float x;
	float y;
	float z;
	public Vertex(float x, float y, float z)
	{
		this.x=x;
		this.y=y;
		this.z=z;
	}
	public Vertex()
	{
		
	}
	
	public String toString()
	{
		return ""+x+", "+y+", " +z;
	}
	
	
}