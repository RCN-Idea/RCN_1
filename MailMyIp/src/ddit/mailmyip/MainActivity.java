package ddit.mailmyip;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Calendar;
import java.util.Enumeration;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	/**
	 * is called when app launches
	 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //display conent for user
        setContentView(R.layout.activity_main);
        
        
        
       
        
    }
    public void onFocusChange(View v, boolean hasFocus){
    	
    	Log.i("focus is: ",v.findFocus().toString());
    }
    
    

    /**
     * This is called from "normal" buttons
     */
    public void onClick(View v) {
    	
    	final EditText edittxt1 = (EditText) findViewById(R.id.input_mail);
    	
    	
        final int id = v.getId();
        Log.i("Buttonpressed: ",v.toString());
        switch (id) {
        
        case R.id.but_time: 
        	edittxt1.setText("time: "+getTime());
        	//Log.i("info: "+getIP());
            break;
        
        case R.id.but_address: 
        	Log.i("run progress:", "button press");
			//edittxt1.setText("Address: "+getIP());
        	getIP();
            break;

        }
    }

    /**
     * This Creates the 3dot menu 
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    /**
     * this catches menuitem clicks in the 3 dot menu
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	Intent intentSettings = new Intent(this,Settings.class);
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_settings:
            	this.startActivity(intentSettings);
                return true;
            case R.id.menu_exit:
            	finish();
            	System.exit(0);
            	return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    /**
     * just for fun it reads system time and prints to user
     * @return
     */
    public String getTime() {
    	String time =java.text.DateFormat.getTimeInstance().format(Calendar.getInstance().getTime());
    	return time;   
     }
    
    /**
     * reads interace adresses
     */
    public void getIP(){
    
    	try {
			Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
			//checkPermission(android.permission.ACCESS_NETWORK_STATE,this , this);
			
			
			for (Enumeration<NetworkInterface> e = networkInterfaces; e.hasMoreElements();) {
			  NetworkInterface networkInterface = e.nextElement();
			  Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
			  for (Enumeration<InetAddress> eAddresses = inetAddresses; eAddresses.hasMoreElements();) {
			    InetAddress address = eAddresses.nextElement();
			    if (!address.isLoopbackAddress()) {
			      System.out.println("Adress: " + address.toString());
			    }
			  }
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
    }
    
}
