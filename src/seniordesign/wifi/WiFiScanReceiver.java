package seniordesign.wifi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;


public class WiFiScanReceiver extends BroadcastReceiver {

	WifiApp wifiapp;
	
	private final static ArrayList<Integer> channels = new ArrayList<Integer> (Arrays.asList(0, 2412, 2417, 2422, 2427, 2432, 2437, 2442, 2447, 2452, 2457, 2462));
	
	public WiFiScanReceiver(WifiApp wifiapp)
	{
		super();
		this.wifiapp = wifiapp;
	}

	@Override
	public void onReceive(Context context, Intent intent)
	{
		List <ScanResult> results = wifiapp.wifi.getScanResults();
		String str = "";
		String tmp = "";
		ScanResult sr;
		Iterator<ScanResult> it = results.iterator();
		
		while (it.hasNext())
		{
			sr = it.next();
			tmp = channels.indexOf(Integer.valueOf(sr.frequency)) + " " + sr.level + " " + sr.SSID + "\n";
			str += tmp;
		}
		
		wifiapp.changeText(str);
		
	}
}
