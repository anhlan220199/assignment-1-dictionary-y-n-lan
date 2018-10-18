package main;

import java.util.ArrayList;
import java.util.HashMap;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author This PC
 */
public class Dictionary {

    
    //Từ điển Anh-Việt()
    protected ArrayList<String> keys1 = new ArrayList<>();//ghi key 
    protected HashMap<String,String> Word1 = new HashMap<>();// hashMap ghi (từ, nghĩa)
    
    //Từ điển Việt-Anh()
    protected ArrayList<String> keys2 = new ArrayList<>();
    protected HashMap<String,String> Word2 = new HashMap<>();
    
    public ArrayList<String> getKeys1() {
        return keys1;
    }

    public void setKeys1(ArrayList<String> keys1) {
        this.keys1 = keys1;
    }
    
    public HashMap<String, String> getWord1() {
        return Word1;
    }

    public void setWord1(HashMap<String, String> Word1) {
        this.Word1 = Word1;
    }

    public ArrayList<String> getKeys2() {
        return keys2;
    }

    public void setKeys2(ArrayList<String> keys2) {
        this.keys2 = keys2;
    }

    public HashMap<String, String> getWord2() {
        return Word2;
    }
    

    public void setWord2(HashMap<String, String> Word2) {
        this.Word2 = Word2;
    }
}
