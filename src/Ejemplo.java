
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;

public class Ejemplo {
    public static <JSONObject> void main(String[] args) throws IOException {

        String json = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/users.json"));

            String linea;
            while ((linea = br.readLine()) != null) {
                json += linea;
            }

            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();

        // Array de users
        final User[] users = gson.fromJson(json, User[].class);

        for (User user : users) {
            System.out.println(user);
        }

    }
}
