package net.web_kot.testing.cities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;

public class Cities {
    
    private HashSet<String> cities = new HashSet<>();
    
    public Cities() {
        try {
            BufferedReader in = new BufferedReader(new FileReader("cities.txt"));
            String s;
            while((s = in.readLine()) != null) cities.add(s.toLowerCase());
            in.close();
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public boolean cityExists(String name) {
        return cities.contains(name.toLowerCase());
    }
    
}
