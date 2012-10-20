 package seniordesign.wifi;

import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WifiApp extends Activity {
	
	public static TextView textview1;
	public static Button doscan;
	WifiManager wifi;
	BroadcastReceiver receiver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_app);
        
        /* set up the UI */
        textview1 = (TextView) findViewById(R.id.textView1);
        doscan = (Button) findViewById(R.id.button1);
        
        /* set up the WIFI */
        wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        
        /* register the Broadcast Receiver */
        if (receiver == null)
        	receiver = new WiFiScanReceiver(this);
        
        registerReceiver(receiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
    }
    
    @Override
    public void onDestroy()
    {
    	unregisterReceiver(receiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_wifi_app, menu);
        return true;
    }
    
    public static void changeText(String msg)
    {
    	textview1.setText(msg);
    }

    public void startScan(View v)
    {
    	wifi.startScan();
    	changeText("WIFI Scan Started...");
    }
}
