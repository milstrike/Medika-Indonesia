package com.StarStudio.MedikaIndonesia;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

public class Detail extends Activity {
	Button telpon, web, maps;
	TextView tipe, nama, alamat, situs, kontak, lokasi, lat, longi;
	String nama2, no, url, location;
	int i = 0;
	public final String key="";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);
		
		DataRS data = new DataRS();
		
		//set
		SharedPreferences sp = getSharedPreferences(key,0);
		
		//get indek
		SharedPreferences s = getSharedPreferences(List.indek_rs,0);
		i = s.getInt("indek", 0);
		
		telpon = (Button) findViewById(R.id.hubungi);
		web = (Button) findViewById(R.id.kunjungi);
		maps = (Button) findViewById(R.id.lihat);
		
		tipe = (TextView) findViewById(R.id.tipe);
		nama = (TextView) findViewById(R.id.nama2);
		alamat = (TextView) findViewById(R.id.alamat);
		//situs = (TextView) findViewById(R.id.situs);
		//kontak = (TextView) findViewById(R.id.nomor);
		//lokasi = (TextView) findViewById(R.id.lokasi);
		lat = (TextView) findViewById(R.id.lat);
		longi = (TextView) findViewById(R.id.longi);
				
		nama2 = data.getNama(i);
		no = data.getNomor(i);
		url = data.getSitus(i);
		location = data.getLokasi(i);
		
		tipe.setText("Rumah Sakit");
		nama.setText(nama2);
		alamat.setText(data.getAlamat(i));
		web.setText("   "+url+"   ");
		telpon.setText("   "+no+"   ");
		maps.setText("   "+location+"   ");
		
		lat.setText(data.getLatitude(i));
		longi.setText(data.getLongitude(i));
		
		no="+62 85643325534";
		location = "https://maps.google.com/maps?g="+location;
		
	}
	
	public void Telepon(View V){
		no="+62 85643325534";
		Toast.makeText(getApplicationContext(), 
				"Notifikasi Medika Indonesia:\n" +
				"Memanggil: "+ nama2,
				Toast.LENGTH_LONG).show();
		Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+no));
		startActivity(intent);
	}
	
	public void location(View V){
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(location));
		startActivity(intent);
	}
	
	public void infoDetail(View V){
		location = "https://maps.google.com/maps?g="+location;
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse("http://"+url));
		startActivity(intent);
	}
}
