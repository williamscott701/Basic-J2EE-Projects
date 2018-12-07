
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.ResultSet;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class WSConvert {

    public static JSONObject readerToJSON(BufferedReader br) {
        try {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            JSONParser parser = new JSONParser();
            return (JSONObject) parser.parse(sb.toString());
        } catch (IOException | ParseException e) {
            System.out.println(e);
            return null;
        }
    }

    public static JSONArray convertResultSetIntoJSON(ResultSet resultSet) throws Exception {
        JSONArray jsonArray = new JSONArray();
        while (resultSet.next()) {
            int total_rows = resultSet.getMetaData().getColumnCount();
            JSONObject obj = new JSONObject();
            for (int i = 0; i < total_rows; i++) {
                String columnName = resultSet.getMetaData().getColumnLabel(i + 1).toLowerCase();
                Object columnValue = resultSet.getObject(i + 1);
                // if value in DB is null, then we set it to default value
                if (columnValue == null) {
                    columnValue = "null";
                }
                /*
                 Next if block is a hack. In case when in db we have values like price and price1 there's a bug in jdbc - 
                 both this names are getting stored as price in ResulSet. Therefore when we store second column value,
                 we overwrite original value of price. To avoid that, i simply add 1 to be consistent with DB.
                 */
                if (obj.containsKey(columnName)) {
                    columnName += "1";
                }
                obj.put(columnName, columnValue);
            }
            jsonArray.add(obj);
        }
        return jsonArray;
    }
}
