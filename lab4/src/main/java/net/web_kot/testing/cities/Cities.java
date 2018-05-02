package net.web_kot.testing.cities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;

public class Cities {
    
    private HashSet<String> cities = new HashSet<>();
    
    private HashSet<String> used = new HashSet<>();
    private String last = null;
    
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
    
    public char getLastCharacter(String name) {
        char c = Character.toLowerCase(name.charAt(name.length() - 1));
        if(c == 'ь' || c == 'ы' || c == 'й') return Character.toLowerCase(name.charAt(name.length() - 2));
        return c;
    }
    
    public boolean isValidAnswerAfter(String prev, String current) {
        return getLastCharacter(prev) == Character.toLowerCase(current.charAt(0));
    }
    
    public void answer(String city) throws Exception {
        city = city.toLowerCase();
        
        if(used.contains(city)) throw new Exception("This city already answered");
        if(last != null && !isValidAnswerAfter(last, city)) throw new Exception("This city starts from wrong character");
        
        used.add(city);
        last = city;
    }
    
}
