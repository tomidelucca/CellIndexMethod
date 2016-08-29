package me.tomidelucca.methods;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;

public class InputGenerator {

    public static void generateRandomInput(int N, double L, double maxRadius, String path) {
        PrintWriter writer = null;
        Random rand = new Random();

        try {
            writer = new PrintWriter(path, "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        writer.println(N);
        writer.println(L);

        for(int i = 0; i<N; i++) {
            double x = ((double)rand.nextInt((int) (L*100)))/100;
            double y = ((double)rand.nextInt((int) (L*100)))/100;;
            double radius = ((double)rand.nextInt((int) (maxRadius*100)))/100;
            writer.println(radius + " " + x + " " + y);
        }

        writer.close();
    }

    public static void main(String[] args){
    	generateRandomInput(40, 7, 1, "input/40_7.xyz");
    	generateRandomInput(100, 7, 1, "input/100_7.xyz");
    	generateRandomInput(400, 7, 1, "input/400_7.xyz");
    	generateRandomInput(4000, 7, 1, "input/4000_7.xyz");
    	generateRandomInput(10000, 7, 1, "input/10000_7.xyz");
    }
}
