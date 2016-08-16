package me.tomidelucca.methods;

import me.tomidelucca.models.Particle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int M = Integer.valueOf(args[0]);
        double Rc = Double.valueOf(args[1]);
        int N = 0;
        double L = 0.0;

        Particle[] particles = null;

        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("resources/input.rawr"));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            if(line != null) {
                N = Integer.valueOf(line);
                L = Double.valueOf(br.readLine());
            }

            particles = new Particle[N];

            int nbis = N;

            while (nbis>0) {
                String[] input = br.readLine().split(" ");
                particles[N-nbis] = new Particle(Double.valueOf(input[1]), Double.valueOf(input[2]), Double.valueOf(input[0]));
                nbis--;
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                br.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        CellIndexMethod.neighbours(particles, L, M, Rc);

    }
}
