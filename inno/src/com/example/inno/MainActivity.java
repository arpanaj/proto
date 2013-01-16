package com.example.inno;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

// Add comment1
	private TextView txt;
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			txt.setText(msg.getData().getString("counter"));
		}
	};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = (TextView)findViewById(R.id.txt);
    }

    @Override
    protected void onStart() {
    	super.onStart();
    	// create new thread
    	Thread backgnd = new Thread(new Runnable() {
    		@Override
    		public void run() {
    			for (int i =0; i<10; i++) {
    				try {
    					Thread.sleep(1000);

    					// pass a message to the handler to print counter
    					Bundle b = new Bundle();
    					b.putString("counter", String.valueOf(i));
    					Message msg = new Message();
    					msg.setData(b);
    					handler.sendMessage(msg);

    					// Another way is to pass a runnable to the handler
    					/*handler.post(new Runnable() {
    						@Override
    						public void run() {
    							txt.setText("runnable");
    						}
    					});*/
    				} catch (Exception e) {
    					Log.v("inno", "Exp "+ e.getMessage() );
    				}
    			}
    		}
    	});
    	backgnd.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

}
