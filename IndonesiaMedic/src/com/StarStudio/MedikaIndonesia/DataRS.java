package com.StarStudio.MedikaIndonesia;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.util.Log;

public class DataRS {
	
	private String[][] data;
	private String[][] dataApotek;
	private String[][] dataSpesialis;
	int[] idCari = new int[20];
	private double lat = -6.886177;
	private double lng = 107.608249;
	private double lat2 = 0.0;
	private double lng2 = 0.0;
	private double jarak = 0.0;
	
	//konstruktor
	DataRS(){
		/*
		 * format data statics rumah sakit:
		 * [0] nama
		 * [1] alamat
		 * [2] situs
		 * [3] kontak
		 * [4] lat lokasi map
		 * [5] long
		 * gara2 mutung :v
		 */
		data = new String[][] {
			{ // data 0 = rumah sakit
				"Rumah Sakit Umum Advent",
				"Jalan Cihampelas No.161 40131 Indonesia",
				"rsadvent-bandung.com",
				"+62 22 2034386",
				"-6.892078",
				" 107.604004",
			},{
				"Instalasi Gawat Darurat Rumah Sakit Umum Pusat Dr. Hasan Sadikin",
				"Kota Bandung, Jawa Barat, Indonesia",
				"",
				"",
				"-6.896769", "107.598064"
			},{
				"Rumah Sakit Umum Sariningsih",
				"Jalan Llre Martadinata No.11 40116 Indonesia",
				"",
				"",
				"-6.906656", "107.610899"
			},{
				"Rumah Sakit Santo Yusup",
				"No.7 Kota Bandung, Jawa Barat, Indonesia",
				"",
				"+62 22 7208172",
				"-6.906685", "107.643074"
			},{
				"Rumah Sakit Umum Kebonjati",
				"Jalan Kebonjati No. 152 Jawa Barat 40181 Indonesia",
				"",
				"+62 22 6014058",
				"-6.915976", "107.596361"
			},{
				"Rumah Sakit Umum Bungsu",
				"Jalan Veteran No.6 Bandung Jawa Barat 40112, Indonesia",
				"",
				"+62 22 4231550",
				"-6.918748", "107.613468"
			},{
				"Rumah Sakit Muhammadiyah",
				"Jalan Kh. Ahmad Dahlan No.53 40264 Indonesia",
				"",
				"",
				"-6.933632", "107.623173"
			},{
				"Rumah Sakit Immanuel",
				"Jl.Kopo No.161 Jawa Barat 40234 Indonesia",
				"rsimmanuel.com",
				"+62 22 5201656",
				"-6.935671", "107.596549"
			},{
				"Rumah Sakit Umum Avisena",
				"JL Melong, No. 170, Cijerah 40534 Indonesia",
				"",
				"+62 22 6000830",
				"-6.922948", "107.562938"
			},{
				"Rumah Sakit Umum Pindad",
				"Jalan Gatot Subroto No.517 40284 Indonesia",
				"",
				"+62 22 7309807",
				"-6.939706", "107.646847"
			},{
				"Rumah Sakit Umum Al Islam",
				"Jl. Soekarno Hatta No. 644 Indonesia",
				"rsalislam.com",
				"+62 22 7562046",
				"-6.939972", "107.668878"
			},{
				"Rumah Sakit Umum Ujung Berung",
				"Jalan Rumah Sakit No.22 40612 Indonesia",
				"rsudkotabandung.web.id",
				"+62 22 7800017",
				"-6.915625", "107.698683"
			},{
				"Rumah Sakit Umum Bina Sehat",
				"Jalan Dayeuhkolot Raya Indonesia",
				"",
				"+62 22 5207964",
				"-6.985883", "107.624793"
			},{
				"Rumah Sakit Umum Daerah (RSUD) AL Ihsan",
				"JL. Ki Astramanggala, Baleendah, Bandung, Jawa Barat, Indonesia",
				"rsudalihsan.jabarprov.go.id",
				"+62 22 5940872",
				"-7.008001", "107.623808"
			}
		};
		
		/*
		 * format data static APOTEK:
		 * [0] nama
		 * [1] alamat
		 * [2] kontak
		 * [3] lat lokasi map
		 * [4] long
		 * gara2 mutung :v
		 */
		
		dataApotek = new String[][] {
			{ // data apotek
				"Kimia Farma 43",
				"Jalan Buah Batu No. 259, Jawa Barat 40264",
				"(022) 7305019",
				"-6.945422", "107.631175"
			},{
				"Apotek Kimia Farma Antapani",
				"Jalan Purwakarta, Antapani, Bandung, Jawa Barat 40291",
				"(022) 7232689",
				"-6.917304", "107.657392"
			},{
				"Apotek K-24",
				"Jl. Kopo Sayati No. 103C, Margasuka Babakan, Ciparay, Jawa Barat 40124",
				"(022) 5419065",
				"-6.970076", "107.574042"
			},{
				"Apotek Kimia Farma",
				"Jl. Ir. H. Djuanda No. 69, Bandung, Jawa Barat 40161",
				"(022) 4203331",
				"-6.900324", "107.612069"
			},{
				"Apotek",
				"JL Cihampelas, 160",
				"(022) 2001148",
				"-6.897463", "107.604350"
			},{
				"Apotek Kimia Farma Pasir Kaliki",
				"JL. Pasir Kaliki, No. 235. ",
				"(022) 2039228",
				"-6.908190", "107.597999"
			},{
				"Apotek Alpha Farma",
				"JL. Semar, No. 25, 40172. ",
				"(022) 6015439",
				"-6.907199", "107.596699"
			},{
				"Apotek Duta Farma",
				"Jalan Pasirkaliki No.115, 40172",
				"",
				"-6.906988", "107.597524"
			},{
				"Apotek Kimia Farma Pasir Kaliki",
				"JL. Pasir Kaliki, No. 235",
				"(022) 2039228",
				"-6.908197", "107.598014"
			},{
				"Vita Farma Apotek",
				"Jalan Pasirkaliki No.171, 40171",
				"(022) 6012556",
				"-6.906207", "107.598155"
			},{
				"APOTEK ABDI FARMA",
				"Jalan Pajajaran, Bandung, Jawa Barat 40173",
				"",
				"-6.907240", "107.599458"
			},{
				"Apotek Padjadjaran",
				"JL. DR. Rajiman, No. 9",
				"(022) 4216565",
				"-6.903773", "107.598679"
			},{
				"Atm Bri Apotek Kimia Farma",
				"Jalan Cihampelas, Bandung, Jawa Barat 40171",
				"(022) 4201596",
				"-6.905700", "107.603924"
			},{
				"Apotek Pajajaran",
				"Jalan Doktor Rajiman No.9, Bandung, Jawa Barat 40171",
				"(022) 4216565",
				"-6.903753", "107.598907"
			},{
				"Apotek Padjadjaran",
				"JL. DR. Rajiman, No. 9",
				"(022) 4216565",
				"-6.903773", "107.598679"
			},{
				"Apotek",
				"JL Cihampelas, 160. ",
				"(022) 2001148.",
				"-6.897462", "107.604352"
			},{
				"Apotek Kimia Farma Venus",
				"Jl. Venus Raya No. 27 Metro, Margahayu Raya, Jawa Barat 40286",
				"",
				"-6.944379", "107.661246"
			},{
				"Apotek Sari",
				"Jalan Babakan Sari, Kiaracondong, Bandung, Jawa Barat 40283",
				"",
				"-6.922652", "107.653007"
			},{
				"Apotek Kimia Farma KHA. Dahlan",
				"Jalan Kh. Ahmad Dahlan, Bandung Jawa Barat 40262",
				"",
				"-6.926542", "107.620042"
			},{
				"Apotek Aa",
				"Jalan Ciledug, Garut Kota, Garut, Jawa Barat 44114",
				"",
				"-7.217121", "107.906405",
			},{
				"Apotek AA",
				"Kinar Jasa, Jalan Jenderal Ahmad Yani No.580, 40282",
				"",
				"-6.905819", "107.649058",
			},{
				"Apotek AA",
				"Dipati Ukur, Cicalengka, Bandung, Jawa Barat 40395",
				"",
				"-6.988084", "107.840320",
			},{
				"Bandung",
				"Jl. Jend. A. Yani No. 678 Cicaheum Kiaracondong Bandung Jawa Barat",
				"(022) 7271942",
				"-6.911087", "107.647277",
			},{
				"Jalan ABC",
				"Sumur Bandung, Bandung, Jawa Barat 40111",
				"",
				"-6.918800", "107.605852",
			},{
				"Apotek 21",
				"Jalan Pasir Koja No.21, Bandung, Jawa Barat 40242",
				"",
				"-6.927138", "107.601978",
			},{
				"Abadi II Apotik",
				"JL. Kopo 329, Bandung, 40234",
				"(022) 5204312",
				"-6.945580", "107.589701",
			},{
				"Apotik Ace Parma",
				"Jalan Sukamenak 41",
				"(022) 5425488",
				"-6.971722", "107.576019"
			}
		};
		
		/*
		 * format data static SPESIALIS:
		 * [0] nama
		 * [1] alamat
		 * [2] kontak
		 * [3] lat lokasi map
		 * [4] long
		 * gara2 mutung :v
		 */
		
		dataSpesialis = new String[][] {
			{ // data rumah sakit spesialis
				"RS Mata Cicendo",
				"Jalan Cicendo No.4, Bandung, Jawa Barat 40117",
				"",
				"-6.910035", "107.604618",
			},{
				"Rumah Sakit Santo Borromeus (Hospital)",
				"Jalan Ir. Haji Juanda, Bandung, Jawa Barat 40132,",
				"(022) 2552000",
				"-6.894181", "107.613734",
			},{
				"Rumah Sakit Paru Dr. Haji A Rotinsulu",
				"Jalan Bukit Jarian No.40, 40141",
				"",
				"-6.878109", "107.606433",
			},{
				"Rumah Sakit Khusus Gigi dan Mulut",
				"Jalan Laks. Laut RE. Martadinata No. 45, Jawa Barat 40115",
				"",
				"-6.906119", "107.614103",
			},{
				"RSKB Halmahera Siaga",
				"Jl.L.L.R.E.Martadinata No.28, Jawa Barat 40115",
				"",
				"-6.906794", "107.615815",
			},{
				"Rumah Sakit Immanuel Bandung",
				"JL. Kopo, No.161, 40232",
				"(022) 5201656",
				"-6.942691", "107.592023",
			},{
				"Rumah Sakit Umum Advent",
				"Jalan Cihampelas No.161 40131",
				"",
				"-6.892070", "107.603992",
			},{
				"Melinda Hospital",
				"JL. Pajajaran 46",
				"(022) 4222788",
				"-6.906684", "107.603299",
			}
		};
	}
	
	//-------------------- get RS -------------------- //
	
	public String getNama(int i){
		return data[i][0];
	}
	
	public String getAlamat(int i){
		return data[i][1];
	}
	
	public String getSitus(int i){
		return data[i][2];
	}
	
	public String getNomor(int i){
		return data[i][3];
	}
	
	public String getLokasi(int i){
		return data[i][4]+", "+data[i][5];
	}
	
	public String getLatitude(int i){
		return data[i][4];
	}
	
	public String getLongitude(int i){
		return data[i][5];
	}
	
	public String[] getAllNama(){
		int n = getCount();
		String[] daftar_nama = new String[n];
		
		for(int i=0; i<n; i++){
			daftar_nama[i]=data[i][0];
		}
		
		return daftar_nama;
	}
	
	public String[] getAllNamaJarak(){
		int n = getCount();
		String[] daftar_nama = new String[n];
		
		for(int i=0; i<n; i++){
			lat2 = Double.parseDouble(getLatitude(i));
			lng2 = Double.parseDouble(getLongitude(i));
			jarak = CalculationByDistance(lat, lat2, lng, lng2);
			String jaraks = String.valueOf(jarak);
			
			daftar_nama[i]=data[i][0]+" (" + jaraks +" km)";
		}
		
		return daftar_nama;
	}
	
	public int getCount() {
		return data.length;
	}
	
	// -------------------- get Apotek -------------------- //

	public String getNamaApotek(int i){
		return dataApotek[i][0];
	}
	
	public String getAlamatApotek(int i){
		return dataApotek[i][1];
	}
	
	public String getNomorApotek(int i){
		return dataApotek[i][2];
	}
	
	public String getLokasiApotek(int i){
		return dataApotek[i][3]+", "+dataApotek[i][4];
	}
	
	public String getLatitudeApotek(int i){
		return dataApotek[i][3];
	}
	
	public String getLongitudeApotek(int i){
		return dataApotek[i][4];
	}
	
	public String[] getAllNamaApotek(){
		int n = getCountApotek();
		String[] daftar_nama = new String[n];
		
		for(int i=0; i<n; i++){
			daftar_nama[i]=dataApotek[i][0];
		}
		
		return daftar_nama;
	}
	
	public int getCountApotek() {
		return dataApotek.length;
	}
	
	// -------------------- get Spesialis -------------------- //
	
	public String getNamaSpesialis(int i){
		return dataSpesialis[i][0];
	}
	
	public String getAlamatSpesialis(int i){
		return dataSpesialis[i][1];
	}
	
	public String getNomorSpesialis(int i){
		return dataSpesialis[i][2];
	}
	
	public String getLokasiSpesialis(int i){
		return dataSpesialis[i][3]+", "+dataSpesialis[i][4];
	}
	
	public String getLatitudeSpesialis(int i){
		return dataSpesialis[i][3];
	}
	
	public String getLongitudeSpesialis(int i){
		return dataSpesialis[i][4];
	}
	
	public String[] getAllNamaSpesialis(){
		int n = getCountSpesialis();
		String[] daftar_nama = new String[n];
		
		for(int i=0; i<n; i++){
			daftar_nama[i]=dataSpesialis[i][0];
		}
		
		return daftar_nama;
	}
	
	public int getCountSpesialis() {
		return dataSpesialis.length;
	}
	
	// ---------------------- aneh-aneh (rasah dinggo sik) ---------------- //
	public String[] findRS(String text){
		int n = getCount();
		int x=0;
		//int[] list;
				
		for(int i=0; i<n; i++){
			String patternString1 = data[i][0].toLowerCase();
			
			Pattern pattern = Pattern.compile(text.toLowerCase());
			Matcher matcher = pattern.matcher(patternString1);
			
			/*
			if(matcher.lookingAt()){
				this.idCari[x] = i;
				x++;
			}
			
			*/
			while(matcher.find()) {
				//list[x] = i;
				this.idCari[x] = i;
				if(x!=i)
					x++;
			}
			
			/*
			Matcher m = Pattern.compile("your regular expression here")
			     .matcher(yourStringHere);
			 while (m.find()) {
			   allMatches.add(m.group());
			 }
			 */
		}
		
		String[] daftar_nama = new String[x];
		
		for(int j=0; j<x; j++){
			daftar_nama[j] = data[idCari[j]][0];
			//daftar_nama[j] = idCari[j]+"";
		}
				
		return daftar_nama;
	}
	
	public int getCountCari(){
		return idCari.length;
	}
	
	public int getIdCari(int i){
		return idCari[i];
	}
	
	public int[] getIdCariArray(){
		return idCari;
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
	
}

