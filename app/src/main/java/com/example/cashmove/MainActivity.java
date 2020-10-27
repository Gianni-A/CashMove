package com.example.cashmove;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {
	private ProgressBar p1;
    TextView cargando;
	private int pStatus = 0;
	private long avanza = 0;
	private Handler mHandler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		p1 = (ProgressBar)findViewById(R.id.progressBar1);
		p1.setMax(100);
		p1.setProgress(0);
		avanza = 0;
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(pStatus < 100){
					pStatus = trabaja();
					for(int i = 0; i<100; i++){
						try{
							//Aqui
							Thread.sleep(2);
						}catch(InterruptedException e){
							e.printStackTrace();
						}
						
					}
					
					mHandler.post(new Runnable() {
						
						@Override
						public void run() {
							p1.setProgress(pStatus);
							
						}
					});
				}
				
			}
		}).start();
		
		
		
	}
	


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public int trabaja(){
		while(avanza <=1000000){
			avanza++;
			if(avanza == 100000){
				return 10;
			}else if( avanza == 200000){
				return 20;
			}else if(avanza == 300000){
				return 30;
			}else if(avanza == 400000){
				return 40;
			}else if(avanza == 500000){
				/*long[] pattern = {0,500,50,500,50,500,50,500};
				Vibrator vibra = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
				vibra.vibrate(pattern, -1);*/
				
				return 70;
			}
			
		}
		Intent paso = new Intent(getApplicationContext(),Movimiento.class);
		startActivity(paso);
		return 100;
		
	}
	
	

}
