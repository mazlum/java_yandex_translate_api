
package translate;

import java.awt.print.Paper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class translate {

	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub
		String trans = yandex_translate("en-tr","hello");
		
		System.out.println(trans);
	}
	
public static String yandex_translate(String yandex_lang,String yandex_text) throws IOException, ParseException{
		
		String yandex_key = "trnsl.1.1.20140404T200757Z.f74ea350ac6726ed.77c2be0d912730209b7f24efe55dbf84f37ca4cb";

		//Yandex'in sağlamış olduğu url tanımlandı.
		String yandex_url = "https://translate.yandex.net/api/v1.5/tr.json/translate?key="+yandex_key+"&lang="+yandex_lang+"&text="+yandex_text;
			
		//Yeni url nesnesi oluşturuldu. Bu nesneye ayndex_url parametre olarak verildi.
		URL url = new URL(yandex_url);
		
		try {
			//URLConnection sınıfı bağlantı HTTP protokolü üzerinden bağlantı yapmayı sağlar. 
			//URLConnection ile bağlantı üzerindne veriler okundu.
			URLConnection httpUrlConnection = url.openConnection();
			
			InputStream inputStream = httpUrlConnection.getInputStream();
			//Alinmis olan InputStream, InputStreamReader sinifina okunmak uzere verilir. BufferedReader ile okuma islemi gerceklestirilir.
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			
			//Satır okundu. Burada dönen satır JSON tipindedir.
			String line=bufferedReader.readLine();
			
			//Dönen değer JSON tipindedir. Fakat readLine() metodu string olarak okur. Bu yüzden bunu JSON tipine dönüştüreceğiz.
			
			//Parçalamak için JSONParser sınıfından nesne oluşturuldu.
			JSONParser parser = new JSONParser();
			
			//Json objesi oluşturularak okunan değer parametre olarak verildi.
			JSONObject jsonObject = (JSONObject) parser.parse(line);;
			
			//Json dizi oluşturularak json içirisindeki "text" anahtar kelimesinin değeri atıldı. 
			JSONArray msg = (JSONArray)jsonObject.get("text");
			
			return msg.get(0).toString();
			
		} catch (Exception e) {
			return e.getMessage();
		}
		 

	}

}

