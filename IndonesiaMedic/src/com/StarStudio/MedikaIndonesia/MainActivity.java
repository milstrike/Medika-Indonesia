package com.StarStudio.MedikaIndonesia;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

import oauth.signpost.OAuth;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.basic.DefaultOAuthProvider;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;




public class MainActivity extends Activity {

	
	private Context context = this;
	
	private GoogleMap googleMap;
	   private LocationManager locationManager;
	   private double lat = -6.886177;
	   private double lng = 107.608249;
	   public String RSn = "";
	   public String ALn = "";
	   
	   public String nomerYangBisaDihubungi = "";
	   
	   private LinearLayout ll, lj, tel, sms, back, leveler2, leveler3, golek;
	   private TextView nmRumahSakit, alRumahSakit, mencari, noHP;
	   
	   
	   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        
        ll = (LinearLayout) findViewById(R.id.level2);
        lj = (LinearLayout) findViewById(R.id.level3);
        tel = (LinearLayout) findViewById(R.id.aksitelepon);
        sms = (LinearLayout) findViewById(R.id.aksisms);
        back = (LinearLayout) findViewById(R.id.kembali);
        leveler2 = (LinearLayout) findViewById(R.id.level2);
        leveler3 = (LinearLayout) findViewById(R.id.level3);
        golek = (LinearLayout) findViewById(R.id.laymencari);
        nmRumahSakit = (TextView) findViewById(R.id.namaRumahSakit);
        alRumahSakit = (TextView) findViewById(R.id.alamatRumahSakit);
        mencari = (TextView) findViewById(R.id.textCari);
        noHP = (TextView) findViewById(R.id.tampilNoTelepon);
        
        PetaPeta();
        daftarRumahSakit();
        
        
        
    }
    
    public void PetaPeta(){
    	try {
        	Toast.makeText(getApplicationContext(), "     Memuat Lokasi...     ", 2000) .show();
           initilizeMap();
            googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
         	googleMap.setMyLocationEnabled(false);
         	googleMap.getUiSettings().setZoomControlsEnabled(true);
         	googleMap.getUiSettings().setMyLocationButtonEnabled(false);
         	googleMap.getUiSettings().setCompassEnabled(true);
         	googleMap.getUiSettings().setRotateGesturesEnabled(true);
         	googleMap.getUiSettings().setZoomGesturesEnabled(true);
         	/* lat = googleMap.getMyLocation().getLatitude();
         	lng = googleMap.getMyLocation().getLongitude(); */
         	
         	locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Criteria criteria = new Criteria();
            String provider = locationManager.getBestProvider(criteria, false);
            Location location = locationManager.getLastKnownLocation(provider);
           /* try{
            	lat = location.getLatitude();
                lng = location.getLongitude();
            }
            catch(Exception e){
            	Toast.makeText(getApplicationContext(), "     Tidak bisa menemukan lokasi Anda...     ", Toast.LENGTH_LONG) .show();
            } */
            MarkerOptions marker;
            
            marker = new MarkerOptions().position(new LatLng(lat, lng)).title("Sasana Budaya Ganesha ITB Bandung");
            marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
            googleMap.addMarker(marker);
            
            final DataRS data = new DataRS();
            for(int i = 0; i < data.getCount(); i++){
            	double x = Double.parseDouble(data.getLatitude(i));
            	double y = Double.parseDouble(data.getLongitude(i));
            	marker = new MarkerOptions().position(new LatLng(x, y)).title(data.getNama(i));
                marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                marker.snippet(String.valueOf(i));
                googleMap.addMarker(marker);
            }
            
            
            
            /*
            //googleMap.clear();
            googleMap.addMarker(marker);
            
            marker = new MarkerOptions().position(new LatLng(lat2, lng2)).title("Rumah Sakit Umum Advent");
            marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
            
            //googleMap.clear();
			googleMap.addMarker(marker);
			
			marker = new MarkerOptions().position(new LatLng(lat3, lng3)).title("Instalasi Gawat Darurat Rumah Sakit Umum Pusat Dr. Hasan Sadikin");
            marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
            
            //googleMap.clear();
			googleMap.addMarker(marker); */
			
			
			
            
         	CameraPosition cameraPosition = new CameraPosition.Builder()
			.target(new LatLng(lat,lng)).zoom(10).build();
         	googleMap.animateCamera(CameraUpdateFactory
			.newCameraPosition(cameraPosition));
         	
         	
         	googleMap.setOnMarkerClickListener(new OnMarkerClickListener()
            {
				@Override
				public boolean onMarkerClick(Marker arg0) {
					int a = Integer.parseInt(arg0.getSnippet());
					String lokasi = data.getNama(a);
					double latA = Double.parseDouble(data.getLatitude(a));
					double lngA = Double.parseDouble(data.getLongitude(a));
					
					double jarak = CalculationByDistance(lat, latA, lng, lngA);
					
					Toast.makeText(MainActivity.this, "Lokasi Anda: " + lokasi +"\nJarak Anda: "+ jarak + " KM",2000).show();					
                   return true;

				}

            }); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public double CalculationByDistance(double lat1, double lat2, double lng1, double lng2) {
        int Radius=6371;//radius of earth in Km         
        double latX = lat1;
        double latY = lat2;
        double lngX = lng1;
        double lngY = lng2;
        double dLat = Math.toRadians(latY-latX);
        double dLon = Math.toRadians(lngY-lngX);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
        Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
        Math.sin(dLon/2) * Math.sin(dLon/2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult= Radius*c;
        double km=valueResult/1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec =  Integer.valueOf(newFormat.format(km));
        double meter=valueResult%1000;
        int meterInDec= Integer.valueOf(newFormat.format(meter));
        Log.i("Radius Value",""+valueResult+"   KM  "+kmInDec+" Meter   "+meterInDec);

        return kmInDec;
     }
    
    
    
    private void initilizeMap() {
        if (googleMap == null) {
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                    R.id.map)).getMap();
            
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Kesalahan! Peta tidak dapat Dimuat", Toast.LENGTH_SHORT)
                        .show();
            }
        }
        
        
        
    }
    
    
    public void daftarRumahSakit(){
    	ListView mainListView;  
    	ArrayAdapter<String> listAdapter;
    	String[] daftar;
    	String indek_rs="";
    	
    	mainListView = (ListView) findViewById( R.id.mainListView ); 
    	
    	final DataRS data = new DataRS();
    	SharedPreferences sp = getSharedPreferences(indek_rs,0);
    	final SharedPreferences.Editor ubah = sp.edit();
    	
    	daftar = data.getAllNamaJarak();
    	ArrayList<String> DataRS = new ArrayList<String>(); 
    	DataRS.addAll( Arrays.asList(daftar) );
    	listAdapter = new ArrayAdapter<String>(this, R.layout.list, DataRS);
    	mainListView.setAdapter( listAdapter );
    	
    	mainListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id){
				
				nmRumahSakit.setText(data.getNama(position));
				alRumahSakit.setText(data.getAlamat(position));
				noHP.setText(data.getNomor(position));
				
				nomerYangBisaDihubungi = data.getNomor(position);
				
				RSn = data.getNama(position);
				ALn = data.getAlamat(position);
				
				googleMap.clear();
				
				MarkerOptions marker;
				
				marker = new MarkerOptions().position(new LatLng(lat, lng)).title("Sasana Budaya Ganesha ITB Bandung");
	            marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
	            googleMap.addMarker(marker);
	            
	            double DataXA = Double.parseDouble(data.getLatitude(position));
	            double DataXB = Double.parseDouble(data.getLongitude(position));

				marker = new MarkerOptions().position(new LatLng(DataXA, DataXB)).title(data.getNama(position));
	            marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
	            googleMap.addMarker(marker);
				
				//puts
				//ubah.putInt("indek", position);
				
				//save
				//ubah.commit();
				
				//Intent intent = new Intent(context, Detail.class);
				//startActivity(intent);
				
				leveler2.setVisibility(View.GONE);
				leveler3.setVisibility(View.VISIBLE);
				
				CameraPosition cameraPosition = new CameraPosition.Builder()
				.target(new LatLng(DataXA,DataXB)).zoom(15).build();
	         	googleMap.animateCamera(CameraUpdateFactory
				.newCameraPosition(cameraPosition));
				
			}
		});

	}
    
    public void Telepon(View V){
    	nomerYangBisaDihubungi = "+6285643325534";
    	Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+nomerYangBisaDihubungi));
		startActivity(intent);
    }
    
    public void returnToHome(View V){
    	leveler2.setVisibility(View.VISIBLE);
    	leveler3.setVisibility(View.GONE);
    	googleMap.clear();
    	PetaPeta();
    	
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
    	
    	SharedPreferences sp = getSharedPreferences("layanan", 0);
   		final SharedPreferences.Editor ganti = sp.edit();
    	
        int id = item.getItemId();
        if (id == R.id.action_hospital) {
        	ganti.putInt("tipe", 0);
		     ganti.commit();
        	Intent intent = new Intent(context, List.class);
			startActivity(intent);
            return true;
        }
        else if(id == R.id.action_specialist){
        	ganti.putInt("tipe", 3);
		     ganti.commit();
        	Intent intent = new Intent(context, List.class);
			startActivity(intent);
            return true;
        }
        else if(id == R.id.action_medicine){
        	ganti.putInt("tipe", 1);
		     ganti.commit();
        	Intent intent = new Intent(context, List.class);
			startActivity(intent);
            return true;
        }
        else if(id == R.id.action_Mencari){
        	golek.setVisibility(View.VISIBLE);
            return true;
        }
        else if(id == R.id.action_about){
        	Intent intent = new Intent(context, About.class);
			startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public void pencarian(View V){
    	
    	SharedPreferences sp = getSharedPreferences("layanan", 0);
   		final SharedPreferences.Editor ganti = sp.edit();
    	
    	if(mencari.getText().toString().length()<2){
    		Toast.makeText(MainActivity.this, "Masukkan minimal dua huruf!",2000).show();
			//nama.setText(" >> cari butuh min. 2 karakter");
		} else {
			//Modifying Editor
		     ganti.putInt("tipe", 2);
		     //ganti.putString("cari", CariIni);
		     ganti.putString("cari", mencari.getText().toString());
		     //Save the Editor value
		     ganti.commit();
		    
		    golek.setVisibility(View.GONE);
			Intent intent = new Intent(context, List.class);
			startActivity(intent);
			//nama.setText(find.getText().toString());
		}
    }
    
    private class SendSMS extends AsyncTask<String, Void, String> {
	    @Override
	    protected String doInBackground(String... urls) {
	    	
	    	OAuthConsumer consumer = new DefaultOAuthConsumer("bestapp149","49KXO");

	        OAuthProvider provider = new DefaultOAuthProvider("http://sandbox.appprime.net/TemanDev/rest/RequestToken/", 
	                                "http://sandbox.appprime.net/TemanDev/rest/AccessToken/", 
	                                "");

	        System.out.println("Fetching request token ... ");
	        String verificationCode = null;
	        String authUrl;
	        
	        try {
	        	
	                authUrl = provider.retrieveRequestToken(consumer, OAuth.OUT_OF_BAND); // get request token
	                System.out.println("Request token =" + consumer.getToken());
	                System.out.println("Token Secret =" + consumer.getTokenSecret());
	                System.out.println("Now visit:\n" + authUrl
	                                + "\n... and grant this app authorization");
	                verificationCode = "1";
	                System.out.println("Fetching access token...");
	                provider.retrieveAccessToken(consumer, verificationCode.trim()); // get access token
	                System.out.println("Access token: " + consumer.getToken());
	                System.out.println("Token secret: " + consumer.getTokenSecret());
	                
	                String inputJSON = "{\"sendSMS\":{\"pinRequestID\":\"1\",\"pinDestAddress\":\"6285878952533\",\"pinMessageBody\":\"["+ RSn +"] Anda mendapatkan Nomer Antrian: 78. Silahkan datang pada pukul 12.00 WIB\",\"pinShortCode\":\"9147\"}}";
	                
	                System.out.println(inputJSON);
	                
	                URL url2 = new URL("http://sandbox.appprime.net/TemanDev/rest/sendSMS/"); // inisialisasi URL API
	                HttpURLConnection connection = (HttpURLConnection)url2.openConnection(); 

	                connection.setRequestMethod("POST"); // inisialiasi method 
	                connection.setRequestProperty("Content-Type", "application/json");  // content-type
	                connection.setDoOutput(true);
	                consumer.setTokenWithSecret(consumer.getToken(), consumer.getTokenSecret()); // input access token dan access token secret
	                consumer.sign(connection); 

	                OutputStreamWriter outputWriter = new OutputStreamWriter(connection.getOutputStream());
	                outputWriter.write(inputJSON); // request message in post body
	                outputWriter.flush();
	                outputWriter.close();

	                BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream())); // get response
	                String line;
	                while ((line = rd.readLine()) != null) { // read response
	                    System.out.println(line); 
	                }
	                connection.connect();
	                
	        } catch (Exception e) {
	        	Log.i("error", e.toString());
	        }
	      return "";
	    }
}
	
	public void SendMessage(View V){
		
		AlertDialog diaBox = AskOption();
    	diaBox.show();

	}
	
	private AlertDialog AskOption()
	 {
	    AlertDialog myQuittingDialogBox =new AlertDialog.Builder(this) 
	        //set message, title, and icon
	        .setTitle("Konfirmasi") 
	        .setMessage("Apakah Anda ingin melakukan booking Antrian di " + RSn + "?") 

	        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {

	            @Override
				public void onClick(DialogInterface dialog, int whichButton) { 
	            	Toast.makeText(getApplicationContext(), " SMS Antrian baru saja dikirimkan... ", 2000) .show();
	        		SendSMS task = new SendSMS();
	        	    task.execute(new String[] { "http://bestapps.id" });
	                dialog.dismiss();
	            }   

	        })



	        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
	            @Override
				public void onClick(DialogInterface dialog, int which) {

	                dialog.dismiss();

	            }
	        })
	        .create();
	        return myQuittingDialogBox;

	    }
    
    
}
