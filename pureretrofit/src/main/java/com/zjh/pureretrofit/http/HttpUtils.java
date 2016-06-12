package com.zjh.pureretrofit.http;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.telephony.TelephonyManager;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

/**
 * Created by zjh on 2016/6/10.
 *
 */
public class HttpUtils {

	public static boolean isNetworkAvailable(Context context) {
		if (context == null) {
			throw new NullPointerException("context can't be null!");
		}

		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static String getVersionName(Context context) {
		PackageManager packageManager = context.getPackageManager();
		try {
			PackageInfo packinfo = packageManager.getPackageInfo(
					context.getPackageName(), 0);
			return packinfo.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int getVersionCode(Context context) {
		PackageManager packageManager = context.getPackageManager();
		try {
			PackageInfo packinfo = packageManager.getPackageInfo(
					context.getPackageName(), 0);
			return packinfo.versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static File getCachePath(Context context) {
		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED)) {
			return context.getExternalCacheDir();
		} else {
			return context.getCacheDir();
		}
	}

	public static String genDeviceId(Context context) {
		String device_id = null;
		TelephonyManager telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		device_id = telephonyManager.getDeviceId();
		if (device_id != null && device_id.length() > 0
				&& !device_id.equals("000000000000000")) {
			return device_id;
		}

		String serialno = null;
		try {
			Class<?> c = Class.forName("android.os.SystemProperties");
			Method get = c.getMethod("get", String.class, String.class);
			serialno = (String) (get.invoke(c, "ro.serialno", "unknown"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (serialno != null && serialno.length() > 0
				&& !serialno.equals("0000000000000000")) {
			return serialno;
		}

		return UUID.randomUUID().toString().toUpperCase(Locale.US)
				.replaceAll("", "");
	}

	public static String formatAddress(String address) {
		if (address == null || address.length() == 0) {
			return "";
		}

		return address.replaceAll("([\\W]+)Road([\\W]+|$)", "$1Rd$2")
				.replaceAll("([\\W]+)Street([\\W]+|$)", "$1St$2")
				.replaceAll("([\\W]+)Crescent([\\W]+|$)", "$1Cr$2")
				.replaceAll("([\\W]+)Lane([\\W]+|$)", "$1LN$2")
				.replaceAll("([\\W]+)Court([\\W]+|$)", "$1Ct$2")
				.replaceAll("([\\W]+)Place([\\W]+|$)", "$1PL$2")
				.replaceAll("([\\W]+)Terrace([\\W]+|$)", "$1Ter$2")
				.replaceAll("([\\W]+)Drive([\\W]+|$)", "$1Dr$2")
				.replaceAll("([\\W]+)Boulevard([\\W]+|$)", "$1Blvd$2")
				.replaceAll("([\\W]+)Avenue([\\W]+|$)", "$1Ave$2")
				.replaceAll("([\\W]+)Highway([\\W]+|$)", "$1Hwy$2")
				.replaceAll("([\\W]+)Freeway([\\W]+|$)", "$1Fwy$2")
				.replaceAll("([\\W]+)Expressway([\\W]+|$)", "$1Expy$2")
				.replaceAll("([\\W]+)Parkway([\\W]+|$)", "$1Pkwy$2")
				.replaceAll("([\\W]+)Throughway([\\W]+|$)", "$1Trwy$2")
				.replaceAll("([\\W]+)East([\\W]+|$)", "$1E$2")
				.replaceAll("([\\W]+)West([\\W]+|$)", "$1W$2")
				.replaceAll("([\\W]+)South([\\W]+|$)", "$1S$2")
				.replaceAll("([\\W]+)North([\\W]+|$)", "$1N$2")
				.replaceAll("[\\d]+-([\\d]+)", "$1");
	}

	public static int isSupportGoogle(Context context) {
		final PackageManager packageManager = context.getPackageManager();
		final List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
		final Set<String> set = new HashSet<String>();

		if (pinfo != null) {
			for (int i = 0; i < pinfo.size(); i++) {
				PackageInfo packageInfo = pinfo.get(i);
				set.add(packageInfo.packageName);
			}
		}

		if (!set.contains("com.google.android.gms")) {// google play services
			return 1;
		} else if (!set.contains("com.android.vending")) {// google play
			return 2;
		} else if (!set.contains("com.google.android.apps.maps")) {// google map
			return 3;
		}

		return 0;
	}
}
