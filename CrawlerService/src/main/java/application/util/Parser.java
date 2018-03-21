package application.util;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class Parser {

	public static JSONObject parseFromXMLToJson(String str) throws JSONException {
		return XML.toJSONObject(str);
	}

}
