/* JSON API for android appliation */
package com.Biosys.WebserviceComunication;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import org.json.JSONArray;

public class RestAPI {
    private final String urlString = "http://bsmobileservice.somee.com/Handler1.ashx";

    private static String convertStreamToUTF8String(InputStream stream) throws IOException {
	    String result = "";
	    StringBuilder sb = new StringBuilder();
	    try {
            InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
            char[] buffer = new char[4096];
            int readedChars = 0;
            while (readedChars != -1) {
                readedChars = reader.read(buffer);
                if (readedChars > 0)
                   sb.append(buffer, 0, readedChars);
            }
            result = sb.toString();
		} catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }


    private String load(String contents) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");
        conn.setConnectTimeout(60000);
        conn.setDoOutput(true);
        conn.setDoInput(true);
        OutputStreamWriter w = new OutputStreamWriter(conn.getOutputStream());
        w.write(contents);
        w.flush();
        InputStream istream = conn.getInputStream();
        String result = convertStreamToUTF8String(istream);
        return result;
    }


    private Object mapObject(Object o) {
		Object finalValue = null;
		if (o.getClass() == String.class) {
			finalValue = o;
		}
		else if (Number.class.isInstance(o)) {
			finalValue = String.valueOf(o);
		} else if (Date.class.isInstance(o)) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss", new Locale("en", "USA"));
			finalValue = sdf.format((Date)o);
		}
		else if (Collection.class.isInstance(o)) {
			Collection<?> col = (Collection<?>) o;
			JSONArray jarray = new JSONArray();
			for (Object item : col) {
				jarray.put(mapObject(item));
			}
			finalValue = jarray;
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			Method[] methods = o.getClass().getMethods();
			for (Method method : methods) {
				if (method.getDeclaringClass() == o.getClass()
						&& method.getModifiers() == Modifier.PUBLIC
						&& method.getName().startsWith("get")) {
					String key = method.getName().substring(3);
					try {
						Object obj = method.invoke(o, null);
						Object value = mapObject(obj);
						map.put(key, value);
						finalValue = new JSONObject(map);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		}
		return finalValue;
	}

    public JSONObject RegisterUser(int userId,String password) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "RegisterUser");
        p.put("userId",mapObject(userId));
        p.put("password",mapObject(password));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject GetUsersToRegistred() throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "GetUsersToRegistred");
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject GetPositions() throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "GetPositions");
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject GetAllUsers() throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "GetAllUsers");
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject GetAllUserEvents(int userId) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "GetAllUserEvents");
        p.put("userId",mapObject(userId));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject InsertEvent(String checktime,int checktype,int userId,String longitude,String latitude,int tripId) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "InsertEvent");
        p.put("checktime",mapObject(checktime));
        p.put("checktype",mapObject(checktype));
        p.put("userId",mapObject(userId));
        p.put("longitude",mapObject(longitude));
        p.put("latitude",mapObject(latitude));
        p.put("tripId",mapObject(tripId));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject GetAllUserTrips(int userId) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "GetAllUserTrips");
        p.put("userId",mapObject(userId));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject InsertTrip(int userID,String name) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "InsertTrip");
        p.put("userID",mapObject(userID));
        p.put("name",mapObject(name));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject RelesaeUser(int userId) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "RelesaeUser");
        p.put("userId",mapObject(userId));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject SetUserPosition(int userId,int position) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "SetUserPosition");
        p.put("userId",mapObject(userId));
        p.put("position",mapObject(position));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject RemoveTrip(int tripId) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "RemoveTrip");
        p.put("tripId",mapObject(tripId));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject SendMessage(int convId,int senderID,String content) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "SendMessage");
        p.put("convId",mapObject(convId));
        p.put("senderID",mapObject(senderID));
        p.put("content",mapObject(content));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject GetUserMessages(int userId) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "GetUserMessages");
        p.put("userId",mapObject(userId));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject GetUserConversations(int userId) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "GetUserConversations");
        p.put("userId",mapObject(userId));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject GetLastConversation() throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "GetLastConversation");
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject RemoveConversation(int convId) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "RemoveConversation");
        p.put("convId",mapObject(convId));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject GetUserConversationMembers(int userId) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "GetUserConversationMembers");
        p.put("userId",mapObject(userId));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject GetRodeInfos(int userId) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "GetRodeInfos");
        p.put("userId",mapObject(userId));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject InsertMember(int convId,int userId) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "InsertMember");
        p.put("convId",mapObject(convId));
        p.put("userId",mapObject(userId));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject InsertRodeInfo(int userId,int messageId) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "InsertRodeInfo");
        p.put("userId",mapObject(userId));
        p.put("messageId",mapObject(messageId));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject InsertConversation(String topic) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "InsertConversation");
        p.put("topic",mapObject(topic));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }
}


