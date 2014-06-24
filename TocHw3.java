import java.io.*;
import java.net.*;
import org.json.*;
public class Toc_hw3 {

	public static void main(String[] args) throws IOException, JSONException {
		try{
		// TODO Auto-generated method stub
		String url=new String(args[0]);
		String area = new String(args[1]);
		String road = new String(args[2]);
		int year = Integer.parseInt(args[3]);
		URL myurl = new URL(url);
		URLConnection myconnection = myurl.openConnection();
		myconnection.connect();
		BufferedReader BR = new BufferedReader(new InputStreamReader(myurl.openStream(),"UTF-8"));
		String temp;
		StringBuilder builder = new StringBuilder();
		while (( temp = BR.readLine()) != null)
		{
			builder.append( temp );
			//System.out.println(temp);
		}
			
			
	    String json = builder.toString();
		JSONTokener jsontokener = new JSONTokener(json);
		JSONArray jsonarray = new JSONArray(jsontokener);
		int num = 0;
		int price = 0;
		for(int i = 0;i < jsonarray.length();i++)
		{
			JSONObject jsonob = jsonarray.getJSONObject(i);
			//System.out.println(jsonob);
			String getarea;
			getarea=jsonob.getString("鄉鎮市區");
			if(getarea.contains(area))
			{
				//System.out.println("hello");
				String getroad;
				getroad = jsonob.getString("土地區段位置或建物區門牌");
				if(getroad.contains(road))
				{
					int theyear;
					theyear = jsonob.getInt("交易年月");
					theyear /= 100;
					//System.out.println(theyear);
					if(theyear >= year)
					{
						num++;
						int tprice;
						tprice = jsonob.getInt("總價元");
						price = price+tprice;
					}
				}
					
			}
		}
			System.out.println(price/num);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}

}
