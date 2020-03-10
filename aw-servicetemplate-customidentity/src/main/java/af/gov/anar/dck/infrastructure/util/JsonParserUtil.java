package af.gov.anar.dck.infrastructure.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;


@Component
public class JsonParserUtil {

	private ObjectMapper mapper = new ObjectMapper();

	public boolean isValid(String jsonString) {
		try {
			new JSONObject(jsonString);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isValidArray(String jsonString) {
		try {
			new JSONArray(jsonString);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public JSONObject parse(String jsonString) {
		try {
			JSONObject json = new JSONObject(jsonString);
			return json;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public JSONArray parseAsArray(String jsonString) {
		try {
			JSONArray json = new JSONArray(jsonString);
			return json;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String convertToString(JSONObject jsonObj) {
//		#TODO
		return null;
	}
	
	public String parseJsonObjToXFormValue(Object obj) {
		//if obj is array then it should be converted to space seperated string
		StringJoiner strJoiner = new StringJoiner(" ");
		
		if(obj instanceof JSONArray) {
			JSONArray array =  (JSONArray) obj;
			array.forEach(item -> {
				strJoiner.add(item.toString());
			});
			
	    } else {
	    	strJoiner.add(obj.toString());
	    }
		
		return strJoiner.toString();
	}

	public Map<String, Object> jsonToMap(JSONObject json) throws JSONException {
        Map<String, Object> retMap = new HashMap<String, Object>();

        if(json != JSONObject.NULL) {
            retMap = toMap(json);
        }
        return retMap;
    }

    private Map<String, Object> toMap(JSONObject object) throws JSONException {
        Map<String, Object> map = new HashMap<String, Object>();

        Iterator<String> keysItr = object.keys();
        while(keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);

            if(value instanceof JSONArray) {
                value = toList((JSONArray) value);
            }

            else if(value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            map.put(key, value);
        }
        return map;
    }

    private List<Object> toList(JSONArray array) throws JSONException {
        List<Object> list = new ArrayList<Object>();
        for(int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if(value instanceof JSONArray) {
                value = toList((JSONArray) value);
            }

            else if(value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            list.add(value);
        }
        return list;
	}
	

	public Map<String, String> convertJsonStringToMap(String jsonString) throws IOException
	{
		Map<String,String> map =mapper.readValue(jsonString, Map.class);
		return map;
	}
  
	public static String jsonToXml(String jsonContent)
    {
        //converting json to xml
        String xmlData= XML.toString(jsonContent);
        System.out.println(xmlData);
        return xmlData;
    }
}
