package me.tomidelucca.methods;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import me.tomidelucca.file.FileGenerator;
import me.tomidelucca.models.Particle;

public class Main {

    public static void main(String[] args) {

        int M = Integer.valueOf(args[0]);
        double Rc = Double.valueOf(args[1]);
        int N = 0;
        double L = 0.0;

        Particle[] particles = null;

        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("resources/tomi.rawr"));
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

//        long startTime;

//        for(int i = 5; i < L/(Rc + 2*0.5); i=i+1){
//            startTime = System.currentTimeMillis();
//            CellIndexMethod.neighbours(particles, L, i, Rc, false);
//            System.out.println("M= " + i + ", Time= " + (System.currentTimeMillis() - startTime));
//        }

//        startTime = System.currentTimeMillis();
        
        Map<Particle, Set<Particle>> mapParticle = CellIndexMethod.neighbours(particles, L, M, Rc, true);
        Set<Entry<Particle, Set<Particle>>> setParticle = mapParticle.entrySet();
        Entry<Particle, Set<Particle>> e = setParticle.iterator().next(); 
        Particle p = e.getKey();
        Set<Particle> setP = e.getValue();
        List<Particle> listP = new ArrayList<Particle>();
        listP.add(p);
        listP.addAll(setP);
        
        FileGenerator.write(N, (int) L, listP);

//		for (Map.Entry<Particle, Set<Particle>> entry : mapParticle.entrySet())
//		{
//		    System.out.println(entry.getKey() + "/" + entry.getValue());
//		}

//      System.out.println(CellIndexMethod.neighbours(particles, L, M, Rc, false));
//      System.out.println(System.currentTimeMillis() - startTime);
//      startTime = System.currentTimeMillis();
      
//      System.out.println(BruteForceMethod.neighbours(particles, Rc));
//      System.out.println(System.currentTimeMillis()-startTime);
        
//		Graphic2D_2 demo = new Graphic2D_2("SIMULACION", listP, (int) L, (float) L/M);
//        demo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        demo.pack();
//        demo.setLocationRelativeTo(null);
//        demo.setVisible(true);

        //InputGenerator.generateRandomInput(1000, 100, 0.5, "tomi.rawr");
    }

}
