package edu.dhbw.andar.sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;


import android.os.Environment;

import edu.dhbw.andar.util.GraphicsUtil;

public class ParsedObject {
	 public FloatBuffer box;
     public FloatBuffer normals;
     ParserObj p;
     public ParsedObject(){

         p = new ParserObj();
         InputStream iS;
         String fileName=Environment.getExternalStorageDirectory().getAbsolutePath()+"/krazek.obj";
         File f = new File(fileName);
			
			try {
				iS = new FileInputStream(f);
			
			 // am.close();

			InputStreamReader iSR = new InputStreamReader(iS);

			Vertex[] tab= p.generateVerticesFromFile(iSR);
			float[] boxf2 = p.generateFloatsFromVertices(tab);
			p = new ParserObj();
			try {
				iS = new FileInputStream(f);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//am.close();
			iSR = new InputStreamReader(iS);
			Vertex[] tab2 = p.generateNormalsFromFile(iSR);

			float[] normalsf2 = p.generateFloatsFromVertices(tab2);
			
             
             box = GraphicsUtil.makeFloatBuffer(boxf2);
             normals = GraphicsUtil.makeFloatBuffer(normalsf2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
     }

    private FloatBuffer mat_flash;
 	private FloatBuffer mat_ambient;
 	private FloatBuffer mat_flash_shiny;
 	private FloatBuffer mat_diffuse;
     
     public final void draw(GL10 gl, float d1, float d2, float d3) {
     	////////////kolory
    		float[] customColorFlash={1,1,1};
    		float[] customColorAm={d1,d2,d3};

    		float[] customColorDiff={d1,d2,d3};
   // 		if(change==0.0)
   // 			{
    			mat_ambient = GraphicsUtil.makeFloatBuffer(customColorAm);
    			mat_flash = GraphicsUtil.makeFloatBuffer(customColorFlash);

    			mat_flash_shiny = GraphicsUtil.makeFloatBuffer(customColorFlash);
    			mat_diffuse = GraphicsUtil.makeFloatBuffer(customColorDiff);
    			
    		gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_SPECULAR,mat_flash);
    		gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_SHININESS, mat_flash_shiny);	
    		gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_DIFFUSE, mat_diffuse);	
    		gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_AMBIENT, mat_ambient);

    	
    		///////////// kolory
         gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
         gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);

         gl.glVertexPointer(3, GL10.GL_FLOAT, 0, box);
         gl.glNormalPointer(GL10.GL_FLOAT,0, normals);
         for(int i=0;i<p.verticesNum/3;i++)
         	gl.glDrawArrays(GL10.GL_TRIANGLES, i*3, 3);
         gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
         gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
     }
}
