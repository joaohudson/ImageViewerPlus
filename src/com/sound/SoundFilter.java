/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sound;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import javax.sound.sampled.AudioInputStream;


/**
 *
 * @author João Hudson
 */
public class SoundFilter {
        
    public static void main(String[] args)
    {
        try{
            float[] teste = new float[]{1.5f, 3.7f};
            teste = readTest("missile.wav");
            writeText("carai.txt", teste);
        }
        catch(Exception e){
            System.err.println("Deu merda");
            e.printStackTrace();
        }
    }
    
    private static void writeText(String pathName, float[] data) throws Exception
    {
        File file = new File(pathName);
        PrintWriter w = new PrintWriter(file);
        
        for(float v : data)
        {
            w.println(v + "\n");
        }
        
        w.close();
    }
    
    private static float[] readTest(String pathName) throws Exception
    {
        float[] data = null;
        byte[] fullData;
        byte[] element = new byte[4];
        
        File file = new File(pathName);
        FileInputStream fileStream = new FileInputStream(file);
        fullData = new byte[fileStream.available()];
        data = new float[fullData.length / 4];
        fileStream.read(fullData);
        
        for(int i = 0; i < fullData.length; i += 4)
        {
            element[0] = fullData[i];
            element[1] = fullData[i + 1];
            element[2] = fullData[i + 2];
            element[3] = fullData[i + 3];
            
            data[i / 4] = ByteBuffer.wrap(element).getFloat();
        }
        
        return data;
    }
}
