package net.web_kot.testing.cities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

public class Cities {
    
    private HashSet<String> cities = new HashSet<>();
    
    private HashSet<String> used = new HashSet<>();
    private String last = null;
    private int current = 1;
    
    private int delay = 80000;
    private long lastAnswer;
    
    public static void main(String[] args) throws Exception {
        (new Frame()).setVisible(true);
    }
    
    public Cities() {
        try {
            BufferedReader in = new BufferedReader(new FileReader("cities.txt"));
            String s;
            while((s = in.readLine()) != null) cities.add(s.toLowerCase());
            in.close();
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
        lastAnswer = System.currentTimeMillis();
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
        
        if(System.currentTimeMillis() > lastAnswer + delay) throw new Exception("Time is over");
        if(!cityExists(city)) throw new Exception("This city not found");
        if(last != null && !isValidAnswerAfter(last, city)) throw new Exception("This city starts from wrong character");
        if(used.contains(city)) throw new Exception("This city already answered");
        
        used.add(city);
        last = city;
        
        current = current == 1 ? 2 : 1;
        lastAnswer = System.currentTimeMillis();
    }
    
    public int getCurrentPlayer() {
        return current;
    }
    
    public void setDelay(int value) {
        delay = value;
    }
    
    public int getRemainingTime() {
        int time = (int)((lastAnswer + delay) - System.currentTimeMillis());
        return time >= 0 ? time : 0;
    }
    
}
