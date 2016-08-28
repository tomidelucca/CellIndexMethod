package GroupSS.SdS;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Set;

import me.tomidelucca.agent.Agent;
import me.tomidelucca.agent.SelfDrivenParticles;
import me.tomidelucca.models.Particle;

public class Start {

	private final static double DELTA_TIME = 1.0;
	private final static double RANDOM_PERTURBATION = 2.0;

    public static void main(String[] args) {

        int M = Integer.valueOf(args[0]);
        double Rc = Double.valueOf(args[1]);
        int N = 0;
        double L = 0.0;
        int iteration = 30000;
        
        Agent[] agents = null;

        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("resources/tomi.rawr"));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            if(line != null) {
                N = Integer.valueOf(line);
                L = Double.valueOf(br.readLine());
            }

            System.out.println("VALORES N:" + N + " L:" + L + " M:" + M + " Rc:" + Rc);
            System.out.println("VALOR DE DENSIDAD: " + ((double)N/Math.pow(L,2)));
            
            agents = new Agent[N];
            int nbis = N;

            //cargando los agentes
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
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        
        
        while(times < iteration) {    	
            SelfDrivenParticles.move(agents, DELTA_TIME);
            map = SelfDrivenParticles.neighbours(agents, L, M, Rc, true);
        	SelfDrivenParticles.updateAngle(map, RANDOM_PERTURBATION);   
        	
        	//esto lo hago en caso de que las particulas esten fuera de L.
        	SelfDrivenParticles.updatePosition(map, L);
            writer.println(agents.length + 4);
            writer.println(times);
            writer.println("0.1 0.0 0.0");
            writer.println("0.1 0.0 " + L);
            writer.println("0.1 " +L +" 0.0");
            writer.println("0.1 "+ L +" "+L);
            
			for (Map.Entry<Particle, Set<Particle>> entry : map.entrySet())
			{
				//escribo la nueva posicion de un agente
	            writer.println(entry.getKey());
			}
			
        	times++;
        }
        writer.close();

    }
	
}
