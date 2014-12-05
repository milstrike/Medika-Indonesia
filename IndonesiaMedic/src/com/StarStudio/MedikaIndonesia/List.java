package com.StarStudio.MedikaIndonesia;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.Context;
import android.os.Bundle;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;
import android.widget.AdapterView;


public class List extends ListActivity{
	
	String[] daftar;
	//
	public static final String indek_rs="";
	int tipe=0;
	String cariX="";
	
	@Override
	public void onCreate(Bundle SavedInstanceState){
		final Context context = this;
		super.onCreate(SavedInstanceState);
		DataRS data = new DataRS();
		
		//set
		SharedPreferences sp = getSharedPreferences(indek_rs,0);
		
		//get
		final SharedPreferences.Editor ubah = sp.edit();
		
		//get 
		SharedPreferences s = getSharedPreferences("layanan", 0);
	    tipe = s.getInt("tipe", 0);
	    
	    SharedPreferences ss = getSharedPreferences("layanan", 0);
	    cariX = ss.getString("cari", "x").toString();
		
		//setListAdapter(new ArrayAdapter<String>(this, R.layout.list, daftar));
		switch(tipe){
		case 0: {
				daftar = data.getAllNama();
				setListAdapter(new ArrayAdapter<String>(this,R.layout.list, daftar));
			} break;
		case 1: {
				daftar = data.getAllNamaApotek();
				setListAdapter(new ArrayAdapter<String>(this,R.layout.list, daftar));
			} break;
		case 2: {
				try{
					daftar = data.findRS(cariX);
					//daftar = data.findRS(ss.getString("cari", ""));
					setListAdapter(new ArrayAdapter<String>(this,R.layout.list, daftar));
				} catch(Exception e){
					daftar = data.findRS("x");
					//daftar = data.findRS(ss.getString("cari", ""));
					setListAdapter(new ArrayAdapter<String>(this,R.layout.list, daftar));
				}
			} break;
		case 3: {
			daftar = data.getAllNamaSpesialis();
			setListAdapter(new ArrayAdapter<String>(this,R.layout.list, daftar));
			} break;
		};
		
		ListView list = getListView();
		list.setTextFilterEnabled(true);
		
		final int[] idCari = data.getIdCariArray();
		
		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id){
				
				//final int idCari = data.getIdCari(position);
				//puts
				
				
				if(tipe==2){//jika cari
					ubah.putInt("indek", idCari[position]);
				} else{
					ubah.putInt("indek", position);
				}
				
				//save
				ubah.commit();
				
				Intent intent = new Intent(context, Detail.class);
				startActivity(intent);
			}
		});
		
	}
}

