package edu.dhbw.andar.sample;


import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import edu.dhbw.andar.util.GraphicsUtil;

public class AgainBox {
        public FloatBuffer box;
        public FloatBuffer normals;
        
        private FloatBuffer mat_flash;
    	private FloatBuffer mat_ambient;
    	private FloatBuffer mat_flash_shiny;
    	private FloatBuffer mat_diffuse;
    	
        public AgainBox() {
                float boxf[] =  {
                                // FRONT
                                -25.0f, -25.0f,  25.0f,
                                 25.0f, -25.0f,  25.0f,
                                -25.0f,  25.0f,  25.0f,
                                 25.0f,  25.0f,  25.0f,
                                // BACK
                                -25.0f, -25.0f, 0.0f,
                                -25.0f,  25.0f, 0.0f,
                                 25.0f, -25.0f, 0.0f,
                                 25.0f,  25.0f, 0.0f,
                                // LEFT
                                -25.0f, -25.0f,  25.0f,
                                -25.0f,  25.0f,  25.0f,
                                -25.0f, -25.0f, 0.0f,
                                -25.0f,  25.0f, 0.0f,
                                // RIGHT
                                 25.0f, -25.0f, 0.0f,
                                 25.0f,  25.0f, 0.0f,
                                 25.0f, -25.0f,  25.0f,
                                 25.0f,  25.0f,  25.0f,
                                // TOP
                                -25.0f,  25.0f,  25.0f,
                                 25.0f,  25.0f,  25.0f,
                                 -25.0f,  25.0f, 0.0f,
                                 25.0f,  25.0f, 0.0f,
                                // BOTTOM
                                -25.0f, -25.0f,  25.0f,
                                -25.0f, -25.0f, 0.0f,
                                 25.0f, -25.0f,  25.0f,
                                 25.0f, -25.0f, 0.0f,
                        };
                float normalsf[] =  {
                                // FRONT
                                0.0f, 0.0f,  1.0f,
                                0.0f, 0.0f,  1.0f,
                                0.0f, 0.0f,  1.0f,
                                0.0f, 0.0f,  1.0f,
                                // BACK
                                0.0f, 0.0f,  -1.0f,
                                0.0f, 0.0f,  -1.0f,
                                0.0f, 0.0f,  -1.0f,
                                0.0f, 0.0f,  -1.0f,
                                // LEFT
                                -1.0f, 0.0f,  0.0f,
                                -1.0f, 0.0f,  0.0f,
                                -1.0f, 0.0f,  0.0f,
                                -1.0f, 0.0f,  0.0f,
                                // RIGHT
                                1.0f, 0.0f,  0.0f,
                                1.0f, 0.0f,  0.0f,
                                1.0f, 0.0f,  0.0f,
                                1.0f, 0.0f,  0.0f,
                                // TOP
                                0.0f, 1.0f,  0.0f,
                                0.0f, 1.0f,  0.0f,
                                0.0f, 1.0f,  0.0f,
                                0.0f, 1.0f,  0.0f,
                                // BOTTOM
                                0.0f, -1.0f,  0.0f,
                                0.0f, -1.0f,  0.0f,
                                0.0f, -1.0f,  0.0f,
                                0.0f, -1.0f,  0.0f,
                        };

                
                box = GraphicsUtil.makeFloatBuffer(boxf);
                normals = GraphicsUtil.makeFloatBuffer(normalsf);
        }


        public final void draw(GL10 gl, float d1, float d2, float d3) {
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

    		
            gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
            gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);

            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, box);
            gl.glNormalPointer(GL10.GL_FLOAT,0, normals);
            for(int i=0;i<6;i++)
            	gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, i*4, 4);
             gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
            gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
        }
}