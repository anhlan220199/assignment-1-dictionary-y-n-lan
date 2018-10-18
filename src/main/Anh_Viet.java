/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author This PC
 */
public class Anh_Viet extends Dictionary{
    
    //Đọc databas từ tệp E_V.zip
    public Anh_Viet(){
        readFile();
    }
    
    
    String path = "Database/E_V.zip";
    public void readFile() {

        try {
            FileInputStream file = new FileInputStream(path);// Tạo file đọc
            ZipInputStream zipStream = new ZipInputStream(file);// Tạo đối tượng đọc zip từ file  
            ZipEntry entry = zipStream.getNextEntry();// Truy cập vào zip
            BufferedReader reader = new BufferedReader(new InputStreamReader(zipStream)); // ghi dữ liệu cho character

            String line, word, def;
            int wordsNum = 0;
            while ((line = reader.readLine()) != null) {
                
                int index = line.indexOf("<html>");
                int index2 = line.indexOf("<ul>");

                if (index2 != -1 && index > index2) {
                    index = index2;
                }

                if (index != -1) {
                    word = line.substring(0, index);//nối chuỗi từ 0 đến vị trí index

                    word = word.trim();//xóa dấu cách thừa ở trước và sau String
                    
                    keys1.add(word);//add từ 
                    
                    
                    

                    //word = word.toLowerCase();
                    def = line.substring(index);// lấy từ vị trí index đến hết 
                    

                    Word1.put(word, def);// thêm phần tử vào hashMap với word là key, def là meaning

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

        try {
            FileOutputStream file = new FileOutputStream(path);//tạo file ghi từ path
            ZipOutputStream zipStream = new ZipOutputStream(file);// tạo đối tượng ghi zip từ file
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(zipStream)); // Ghi tệp
            zipStream.putNextEntry(new ZipEntry(path.replace("./data/", "").replace("zip", "txt"))); //GHi lên zip

            for (String key : keys1) {
                writer.write(key);
                String def = Word1.get(key);
                if (def != null) {
                    writer.write(Word1.get(key));
                }

                writer.newLine();
            }

            writer.close();

        } catch (FileNotFoundException e) {// Lỗi tìm file
            e.printStackTrace();
        } catch (IOException e) { // Lỗi input out put 
            e.printStackTrace();
        }
    }

}
