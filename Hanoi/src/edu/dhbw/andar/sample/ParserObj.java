package edu.dhbw.andar.sample;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class ParserObj {
	public int faces;
	public int verticesNum;
	
	public Vertex[] generateVerticesFromFile(InputStreamReader iSR)
	{
		Vertex[] structures;
		Vertex[] vertices;
		int[] indices;
			String line="";
			ArrayList<String> arrayList = new ArrayList<String>();
			try {
				BufferedReader bR = new BufferedReader(iSR);
				//line="";
				while((line=bR.readLine())!=null)
					arrayList.add(line);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for(int i=0;i<arrayList.size();i++){
				String temp=arrayList.get(i);
					if(temp.startsWith("#") && temp.endsWith("faces"))
						faces = Integer.parseInt(temp.split(" ")[1]);
					if(temp.startsWith("#") && temp.endsWith("vertices"))
						verticesNum = Integer.parseInt(temp.split(" ")[1]);
					
			}
			structures = new Vertex[faces*3];
			vertices = new Vertex[verticesNum];
			indices = new int[faces*3];
			int ind=0;
			int ind2=0;
			for(int i=0;i<arrayList.size();i++){ //3,4
				String temp=arrayList.get(i);
					if(temp.startsWith("v "))
							{
								vertices[ind]=new Vertex(
										Float.parseFloat(temp.split(" ")[2]),
										Float.parseFloat(temp.split(" ")[3]),
										Float.parseFloat(temp.split(" ")[4])
										);
										ind++;
							}
					
					if(temp.startsWith("f "))
					{
						indices[ind2]=Integer.parseInt((temp.split(" ")[1]).split("//")[0]);
						ind2++;
						indices[ind2]=Integer.parseInt((temp.split(" ")[2]).split("//")[0]);
						ind2++;
						indices[ind2]=Integer.parseInt((temp.split(" ")[3]).split("//")[0]);
						ind2++;
					}
					
			}
			//5:
			//indices ma faces*3 (192), vertices - 34, structures - faces*3 (192)
			for(int i=0;i<faces*3;i+=3)
			{
				structures[i]=vertices[indices[i]-1];
				structures[i+1]=vertices[indices[i+1]-1];
				structures[i+2]=vertices[indices[i+2]-1];
			}
			 return structures;
		       
	
	}

	public Vertex[] generateNormalsFromFile(InputStreamReader iSR)
	{
		faces=0;
		verticesNum=0;
		Vertex[] structures;

		Vertex[] normals;
		int[] indices;
			String line="";
			ArrayList<String> arrayList = new ArrayList<String>();
			try {
	       	 
				BufferedReader bR = new BufferedReader(iSR);
				while((line=bR.readLine())!=null)
					arrayList.add(line);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for(int i=0;i<arrayList.size();i++){
				String temp=arrayList.get(i);
					if(temp.startsWith("#") && temp.endsWith("faces"))
						faces = Integer.parseInt(temp.split(" ")[1]);
					if(temp.startsWith("#") && temp.endsWith("normals"))
						verticesNum = Integer.parseInt(temp.split(" ")[1]);
					
			}
			structures = new Vertex[faces*3];
			normals = new Vertex[verticesNum];
			indices = new int[faces*3];
			int ind=0;
			int ind2=0;
			for(int i=0;i<arrayList.size();i++){ //3,4
				String temp=arrayList.get(i);
					if(temp.startsWith("vn "))
							{
								normals[ind]=new Vertex(
										Float.parseFloat(temp.split(" ")[1]),
										Float.parseFloat(temp.split(" ")[2]),
										Float.parseFloat(temp.split(" ")[3])
										);
										ind++;
							}
					
					if(temp.startsWith("f "))
					{
						indices[ind2]=Integer.parseInt((temp.split(" ")[1]).split("//")[1]);
						ind2++;
						indices[ind2]=Integer.parseInt((temp.split(" ")[2]).split("//")[1]);
						ind2++;
						indices[ind2]=Integer.parseInt((temp.split(" ")[3]).split("//")[1]);
						ind2++;
					}
					
			}
			//5:
			//indices ma faces*3 (192), vertices - 34, structures - faces*3 (192)... 192 w normalsach
			for(int i=0;i<faces*3;i+=3)
			{
				structures[i]=normals[indices[i]-1];
				structures[i+1]=normals[indices[i+1]-1];
				structures[i+2]=normals[indices[i+2]-1];
			}
			 return structures;
		       
	       
	
	}
	
	public float[] generateFloatsFromVertices(Vertex[] arg)
	{
		float[] ret;
		ret = new float[arg.length*3];
		for(int i=0;i<arg.length*3;i+=3)
		{
			ret[i]=arg[i/3].x;
			ret[i+1]=arg[i/3].y;
			ret[i+2]=arg[i/3].z;	
		}
		return ret;
	}
}
