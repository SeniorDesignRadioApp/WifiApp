package seniordesign.wifi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.util.Log;


public class WiFiScanReceiver extends BroadcastReceiver {

	WifiApp wifiapp;
	private final static String TAG = "WifiApp";
	
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
		ScanResult channel_info[] = new ScanResult[12];
		
		for (int i = 1; i < 12; i++)
		{
			channel_info[i] = null;
		}
		
		str += "all channels\n";
		
		while (it.hasNext())
		{
			sr = it.next();
			str += channels.indexOf(Integer.valueOf(sr.frequency)) + " " + sr.level + " " + sr.BSSID + "\n";
			int channel = channels.indexOf(Integer.valueOf(sr.frequency));

			if (channel_info[channel] == null)
			{
				channel_info[channel] = sr;
			}
			else
			{
				if (channel_info[channel].level < sr.level)
				{
					channel_info[channel] = sr;
				}
			}
		}
		
		str += "\n\nfinal results\n";
		
		for (int i = 1; i < 12; i++)
		{
			if (channel_info[i] != null)
			{
				tmp = channels.indexOf(Integer.valueOf(channel_info[i].frequency)) + " " + channel_info[i].level + " " + channel_info[i].BSSID + "\n";
				str += tmp;
			}
		}
		
		wifiapp.changeText(str);
	}
}
