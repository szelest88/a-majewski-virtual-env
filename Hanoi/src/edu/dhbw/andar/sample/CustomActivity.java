package edu.dhbw.andar.sample;

//import my.pack.R;

import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import edu.dhbw.andar.ARToolkit;
import edu.dhbw.andar.AndARActivity;
import edu.dhbw.andar.exceptions.AndARException;


public class CustomActivity extends AndARActivity{ 
	boolean zmiana=false;//???
	CustomObject someObject; //CustomObject
	ARToolkit artoolkit;

	CustomRenderer renderer;
	boolean does = false;
///////////MENU:
	public boolean onCreateOptionsMenu(Menu menu){

		menu.add(0,1,0,"Reset");

		menu.add(0,2,0,"Wyjœcie");

		return true;

		}
	public boolean onOptionsItemSelected (MenuItem item){

		switch (item.getItemId()){

		case 1: //save
		zmiana=false;
		does=false;
		someObject.reset();
		return true;

	
		case 2 :

			this.finish();

			return true;
		}


		return false;

		}
	public CustomActivity(){
		}
  
    
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		

				
		
		renderer = new CustomRenderer();//optional, may be set to null
		
		super.setNonARRenderer(renderer);//or might be omited
		try {
			//register a object for each marker type
			artoolkit = super.getArtoolkit();

            getSurfaceView().setOnTouchListener(new TouchEventHandler());
            
			// ;)
           
			someObject = new CustomObject
			("test", "barcode.patt", 80.0, new double[]{0,0},false);
			artoolkit.registerARObject(someObject);
		} catch (AndARException ex){
		}
		
		////////////////////////////////////////w¹tek
		new Thread(new Runnable() {
		    public void run() {
		    	//
		    	//it works! Huhu!
		    		//Huhuhuhu!
		    	Looper.prepare(); // the compiler suggested that - it seems it was right.	
		    
		    	while(true){
		    		try {
		    			
					Thread.sleep(500);
					if(someObject!=null){
						int temp = HttpHelper.getContent();

						
						if(temp>-1){
							someObject.ile++;
							someObject.wolny=!someObject.wolny;
							someObject.stackNum = temp;
//							HttpHelper.setNull();
							HttpHelper.setNull(); // z obydwoma tak sobie
						}
					//	else
					//		someObject.wolny=false;

					}
						
//					Toast.makeText(getApplicationContext(), "time", Toast.LENGTH_SHORT).show();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	}
		    }
		  }).start();
		/////////////////////////////////////////		
//		startPreview();//???
	}


	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		Log.e("AndAR EXCEPTION", ex.getMessage());
		finish();
	}
	 boolean change=false;
class TouchEventHandler implements OnTouchListener {
	
	
                  // handles the touch events.
                // the object will either be scaled, translated or rotated, dependen on the
                 // current user selected mode.
                 // @see android.view.View.OnTouchListener#onTouch(android.view.View, android.view.MotionEvent)
          
                @Override
                public boolean onTouch(View v, MotionEvent event) { //o, i to dzia³a :]
                //	
                	someObject.ile++;;
                	 if(event.getAction()==MotionEvent.ACTION_DOWN)
                		 
                		 //przy naciœniêciu wybieramy stos
                	{
                		 someObject.wolny=!someObject.wolny;
                	//	 if(someObject.ile%2==0)
                	//	someObject.change=1.0f-someObject.change;
                		 if(event.getRawX()<=300 && event.getRawX()>150)
            			{
            			//	Toast.makeText(getApplicationContext(), "œrodek"+event.getRawX(), Toast.LENGTH_SHORT).show();
                    		
            				someObject.stackNum=1;	
            			}else if(event.getRawX()<=150)
            			{
            			//	Toast.makeText(getApplicationContext(), "lewo"+event.getRawX(), Toast.LENGTH_SHORT).show();
                    		
            				someObject.stackNum=0;
                			
            			}else{
            			//	Toast.makeText(getApplicationContext(), "prawo"+event.getRawX(), Toast.LENGTH_SHORT).show();
                    		
            				someObject.stackNum=2;
            			}
            	}
              
                	
                	return true;
                    
                }
               
                
        
    }


	
	
	
}
