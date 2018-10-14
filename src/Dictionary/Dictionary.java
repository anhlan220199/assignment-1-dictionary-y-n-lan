package Dictionary;


import java.io.BufferedReader;

import java.io.FileNotFoundException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import java.io.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ID
 */
public class Dictionary {
    protected ArrayList<String> keys = new ArrayList();
    protected HashMap<String,String> Word = new HashMap<>();
    String link = "FileDatabase/E_V.zip";
    public Dictionary(){
        readFile();
    }
    public HashMap getWord(){
        return Word;
    }
    public ArrayList getKeys(){
        return keys;
    }
    
    //Đọc database từ tệp E_V.txt
    public void readFile() {
        FileInputStream file = null;
        ZipInputStream zipStream = null;
        ZipEntry entry = null;
        BufferedReader reader = null;

        try {
            file = new FileInputStream(link);       //Lấy đường dẫn
            zipStream = new ZipInputStream(file);   //Đọc file zip
            entry = zipStream.getNextEntry();       //Đọc zip

            reader = new BufferedReader(new InputStreamReader(zipStream));

            String line, word, Meaning;
            int wordsNum = 0;       //Số lượng từ đọc được
            while ((line = reader.readLine()) != null) {
                //System.out.printf("%s\n----------------------\n", line);
                int index = line.indexOf("<html>");
                int index2 = line.indexOf("<ul>");

                if (index2 != -1 && index > index2) {
                    index = index2;
                }

                if (index != -1) {
                    word = line.substring(0, index);

                    word = word.trim();
                    keys.add(word);

                    //word = word.toLowerCase();
                    Meaning = line.substring(index);
                    //Meaning = "<html>" + Meaning + "</html>";

                    Word.put(word, Meaning);

                    wordsNum++;
                }
            }
            reader.close();

            System.out.println(wordsNum + " words");


        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {   
            e.printStackTrace();

        }


    }
    
    
    //Viết lại file txt sau khi thêm, xóa hoặc sửa.
    public void reloadFile(){
        FileOutputStream file = null;
        ZipOutputStream zipStream = null;
        BufferedWriter writer = null;

        try {
            file = new FileOutputStream("link");
            zipStream = new ZipOutputStream(file);
            writer = new BufferedWriter(new OutputStreamWriter(zipStream));
            zipStream.putNextEntry(new ZipEntry(link.replace("./data/", "").replace("zip", "txt")));

            for (String key : keys) {   //Chạy từ đầu đến hết mảng keys
                writer.write(key);
                String Meaning = Word.get(key);
                if (Meaning != null) {
                    writer.write(Word.get(key));    //Viết lại meaning của key trong HashMap vào tệp ban đầu
                }

                writer.newLine();   //Viết cách dòng
            }

            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
}
