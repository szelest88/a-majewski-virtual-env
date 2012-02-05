package edu.dhbw.andar.sample;

import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.app.Activity;
import android.widget.Toast;

import edu.dhbw.andar.ARObject;
import edu.dhbw.andar.util.GraphicsUtil;

public class CustomObject extends ARObject {
	Activity ac;
	public CustomObject(String name, String patternName,
			double markerWidth, double[] markerCenter, boolean red) {
		super(name, patternName, markerWidth, markerCenter);
		float   mat_ambientf[]     = {0.3f, 0.3f, 0.3f, 1.0f};
	//	if(red==false)
//			mat_ambientf[0] = 0.4f;//, 0.4f, 0.4f, 1.0f}/
	//	else
		//	mat_ambientf[0]=0.9f;
		float   mat_flashf[]       = {0f, 0.0f, 0f, 1.0f};
		float   mat_diffusef[]       = {0.4f, 0.4f, 0.4f, 1.0f};
		float   mat_flash_shinyf[] = {50.0f};

		mat_ambient = GraphicsUtil.makeFloatBuffer(mat_ambientf);
		mat_flash = GraphicsUtil.makeFloatBuffer(mat_flashf);
		mat_flash_shiny = GraphicsUtil.makeFloatBuffer(mat_flash_shinyf);
		mat_diffuse = GraphicsUtil.makeFloatBuffer(mat_diffusef);

		stacks = new Stacks();
		
	}
	public CustomObject(String name, String patternName,
			double markerWidth, double[] markerCenter, float[] customColor, boolean red) {
		super(name, patternName, markerWidth, markerCenter);
		float   mat_flash_shinyf[] = {50.0f};

		mat_ambient = GraphicsUtil.makeFloatBuffer(customColor);
		mat_flash = GraphicsUtil.makeFloatBuffer(customColor);
		mat_flash_shiny = GraphicsUtil.makeFloatBuffer(mat_flash_shinyf);
		mat_diffuse = GraphicsUtil.makeFloatBuffer(customColor);


		stacks = new Stacks();
	}
	
	public void reset()
	{
		parsed = new ParsedObject();
		przes=0.0f;
		przes2=0.0f;
		stacks = new Stacks();
		change=1.0f;
		stackNum=0;
		podniesiono_z=0;
		ile=0;
		wolny=false;
		temp=1;
	}
	
	/**
	 * Just a box, imported from the AndAR project.
	 */
	public AgainBox box = new AgainBox();//V
	public ParsedObject parsed = new ParsedObject();
	private FloatBuffer mat_flash;
	private FloatBuffer mat_ambient;
	private FloatBuffer mat_flash_shiny;
	private FloatBuffer mat_diffuse;
	public boolean czerwien; //??????
	public float przes=0.0f;
	public float przes2=0.0f;
	public float change=1.0f; 
	public int stackNum=0;
	public Stacks stacks;
	public int podniesiono_z=0;
	public int ile=0;
	public boolean wolny=false;
	public int temp=1;
	float[] customColorFlash={1,1,1};
	float[] customColorAm={0.96f,0.64f,0.38f};

	/**
	 * Everything drawn here will be drawn directly onto the marker,
	 * as the corresponding translation matrix will already be applied.
	 */
	@Override
	public final void draw(GL10 gl) {
		super.draw(gl);
	
		//float[] customColorDiff={0.96f,0.64f,0.38f};

	
		
		
		//STACKNUM - klikniêty
		if(stackNum!=-1){
	//	if(stacks.stacks.get(stackNum).isEmpty() && wolny==true)
	//	{
	//		//...
	//	}else 
			if(!stacks.stacks.get(stackNum).isEmpty() && temp == -1 && wolny==true)
		{
			
			temp=stacks.stacks.get(stackNum).pop().rozmiar;
			//wolny=false;///////////?
		}else if(wolny==false  && temp!=-1)
		{
			//wolny=true;///////////?
			stacks.push(stackNum, temp);
				temp=-1;
		//	Toast.makeText(null, "UPUSZCZONO", Toast.LENGTH_SHORT).show();
		}		
		}
		//region podstawa
		//gl.glPushMatrix();
		//gl.glScalef(1.3f, 4.0f, 0.2f);
		//box.draw(gl,0.86f,0.54f,0.28f);
		//gl.glPopMatrix();
		//endregion podstawa
		for(int i=0;i<3;i++)
		{		gl.glPushMatrix();
				
				gl.glTranslatef(  0, -60+60*i, 0);
				//region kijki
//				gl.glPushMatrix();
//				gl.glTranslatef(  0, 0, 10);
//				
//				gl.glScalef(0.03f,
//						0.03f, 
//						7.0f);
//				
//				parsed.draw(gl,0.3f,0.3f,0.3f);
//				gl.glPopMatrix();
			//endregion kijki
					for(int j=0;j<stacks.stacks.get(i).size();j++)
						{
						gl.glPushMatrix();
						gl.glTranslatef(  0, 0, 10+10*j);//*j

						//parsed.draw(gl);
						gl.glPushMatrix();
						//box.draw(gl);
						float temp =0.1f*(stacks.stacks.get(i).get(j).rozmiar); 
						float tempInv = 1.0f/temp;
						gl.glScalef(temp,
								temp, 
								0.5f);
						
						box.draw(gl,temp,0.64f,0.38f); //kolor
						gl.glScalef(
								tempInv,
								tempInv, 
								2);
						gl.glPopMatrix();
						gl.glPopMatrix();
						}
			   gl.glPopMatrix();

		}
			}
	
	@Override
	public void init(GL10 gl) {
//		if(change==0.0)
//		{
		mat_ambient = GraphicsUtil.makeFloatBuffer(customColorAm);
		mat_flash = GraphicsUtil.makeFloatBuffer(customColorFlash);
		mat_diffuse = GraphicsUtil.makeFloatBuffer(customColorAm);
//			}else
//		{
//
//			mat_ambient = GraphicsUtil.makeFloatBuffer(customColorAm);
//			mat_flash = GraphicsUtil.makeFloatBuffer(customColorFlash);
//			mat_diffuse = GraphicsUtil.makeFloatBuffer(customColorAm);
//			}		
	gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_SPECULAR,mat_flash);
	gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_SHININESS, mat_flash_shiny);	
	gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_DIFFUSE, mat_diffuse);	
	gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_AMBIENT, mat_ambient);

	}
}
