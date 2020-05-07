package util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Board;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

//initiate addresses, initiate order of numbers
// and turn numbers into .png
public class ConfigLoader {

    private static String defaultPath = "src/gameProperties/URLs.properties";
    private static ConfigLoader instance;
    boolean hasPanel = false;
    private Configs addresses, panelConfigs;
    private String assetsPath;
    private List<Board> boardConfigs;
    private Gson gson;

    private ConfigLoader(String path) {
        init(path);
    }

    public static ConfigLoader getInstance() {
        return getInstance("default");
    }

    public static ConfigLoader getInstance(String path) {
        if (instance == null) {
            if (path.equals("default")) {
                path = defaultPath;
            }
            instance = new ConfigLoader(path);
        }
        return instance;
    }

    private void init(String path) {
        gson = new Gson();
        panelConfigs = new Configs();
        addresses = new Configs();
        try {
            FileReader reader = new FileReader(path);
            addresses.load(reader);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        loadConfigs();
    }

    private void loadConfigs() {
        for (Map.Entry<Object, Object> entry : addresses.entrySet()) {
            String key = (String) entry.getKey();
            String searchKey = key.toLowerCase();
            String address = (String) entry.getValue();

            if (searchKey.contains("config")) {
                try {
                    FileReader reader = new FileReader(address);

                    if (searchKey.contains("panel")) {
                        panelConfigs.load(reader);
                    } else if (searchKey.contains("board")) {
                        Type type = new TypeToken<List<Board>>() {
                        }.getType();
                        boardConfigs = gson.fromJson(reader, type);
                    } else if (searchKey.contains("asset")) {
                        assetsPath = address;
                    }
                    reader.close();

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }


    public String getAssetsPath() {
        return assetsPath;
    }

    public Configs getPanelConfigs() {
        return panelConfigs;
    }

    public List<Board> getBoardConfigs() {
        return boardConfigs;
    }
}
