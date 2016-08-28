package GroupSS.SdS;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import me.tomidelucca.agent.Agent;
import me.tomidelucca.agent.SelfDrivenParticles;
import me.tomidelucca.models.Particle;

public class Start {

	private final static double DELTA_TIME = 1.0;

    public static void main(String[] args) {

        int M = 0;
        double Rc = 0.0;
        int N = 0;
        double L = 0.0;
        int iterations = 0;
        double ETA = 0; //random perturbation
        
        Agent[] agents = null;
        BufferedReader br = null;

        Properties prop = new Properties();
    	InputStream inputFile = null;

    	//Lectura de propiedades
    	try {

    		inputFile = new FileInputStream("config.properties");
    		prop.load(inputFile);

    		iterations = Integer.valueOf(prop.getProperty("iterations"));
    		M = Integer.valueOf(prop.getProperty("M"));
    		Rc = Double.valueOf(prop.getProperty("Rc"));
    		ETA = Double.valueOf(prop.getProperty("eta"));

    	} catch (IOException ex) {
    		ex.printStackTrace();
    	} finally {
    		if (inputFile != null) {
    			try {
    				inputFile.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		}
    	}

    	//Lectura de particulas
        try {
            br = new BufferedReader(new FileReader("resources/tomi.rawr"));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            if(line != null) {
                N = Integer.valueOf(line);
                L = Double.valueOf(br.readLine());
            }
            
            agents = new Agent[N];
            int nbis = N;

            while (nbis>0) {
                String[] input = br.readLine().split(" ");
                agents[N-nbis] = new Agent(Double.valueOf(input[1]), Double.valueOf(input[2]));
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
        
        Map<Particle, Set<Particle>> map = null;
        int times = 0;
        PrintWriter writer = null;
		try {
			writer = new PrintWriter("outputtest.xyz", "UTF-8");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        
        
		System.out.printf("VALORES N:%d L:%.2f M:%d Rc:%.2f\n", N,L,M,Rc);
        System.out.printf("VALOR DE DENSIDAD: %.3f\n", ((double)N/Math.pow(L,2)));
		System.out.printf("DELTA TIEMPO: %.2f\n",DELTA_TIME);
		System.out.printf("ITERACIONES: %d\n", iterations);
		long start = System.currentTimeMillis();

        while(times < iterations) {    	
        	System.out.println("Va: " + SelfDrivenParticles.simulationNormalizedVelocity(agents));
            SelfDrivenParticles.move(agents, DELTA_TIME);
            map = SelfDrivenParticles.neighbours(agents, L, M, Rc, true);
        	SelfDrivenParticles.updateAngle(map, ETA);   
        	SelfDrivenParticles.updatePosition(map, L); //esto lo hago en caso de que las particulas esten fuera del cuadado L.

            writer.println(agents.length + 4);
            writer.println(times);
            writer.println("0.1 0.0 0.0");
            writer.println("0.1 0.0 " + L);
            writer.println("0.1 " +L +" 0.0");
            writer.println("0.1 "+ L +" "+L);
            
			for (Map.Entry<Particle, Set<Particle>> entry : map.entrySet())
	            writer.println(entry.getKey()); //escribo la nueva posicion de un agente en archivo
        	times++;
        	
        }
        writer.close();
        
        System.out.println();
        System.out.println("TIEMPO TOTAL: " + ((System.currentTimeMillis() - start) / 1000) + " segundos");
        System.out.println("PROCESO FINALIZADO.");
    }
	
}
