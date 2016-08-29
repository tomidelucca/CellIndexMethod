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
import me.tomidelucca.file.FileAgentsReader;
import me.tomidelucca.file.FileProperties;
import me.tomidelucca.models.Particle;
import me.tomidelucca.ovito.OvitoFile;

public class Start {

	private final static double DELTA_TIME = 1.0;

    public static void main(String[] args) {

    	OvitoFile ovitoFile = new OvitoFile("out/result.xyz");
        FileAgentsReader far = new FileAgentsReader("input/40_7.xyz");
        FileProperties fp = new FileProperties();
        Map<Particle, Set<Particle>> map = null;
        
    	int M = 0;
        double Rc = 0.0;
        int N = 0;
        double L = 0.0;
        int iterations = 0;
        double ETA = 0; //random perturbation
        Agent[] agents = null;
        double[] Va = null;

    	//Parametros del config.properties
        iterations = fp.getIterations();
        M = fp.getM();
        Rc = fp.getRc();
        ETA = fp.getETA();
        
    	//Lectura de particulas de archivo
        agents = far.read();
        N = far.getN();
        L = far.getL();
        Va = new double[iterations];
        
		System.out.printf("VALORES N:%d L:%.2f M:%d Rc:%.2f\n", N,L,M,Rc);
        System.out.printf("VALOR DE DENSIDAD: %.3f\n", ((double)N/Math.pow(L,2)));
		System.out.printf("DELTA TIEMPO: %.2f\n",DELTA_TIME);
		System.out.printf("ITERACIONES: %d\n", iterations);
        

		
		for(double e = 0.0; e <= 5.0; e += 0.25) {
			long start = System.currentTimeMillis();
			int times = 0;
			ETA = e;
			agents = far.read();			
			ovitoFile = new OvitoFile("out/eta_"+ETA+".xyz");
			System.out.println("PROCESO CON ETA: " + ETA);
			while(times < iterations) {    	
	            SelfDrivenParticles.move(agents, DELTA_TIME);
	            map = SelfDrivenParticles.neighbours(agents, L, M, Rc, true);
	        	SelfDrivenParticles.updateAngle(map, ETA);   
	        	SelfDrivenParticles.updatePosition(map, L); //esto lo hago en caso de que las particulas esten fuera del cuadado L.
	        	Va[times] = SelfDrivenParticles.simulationNormalizedVelocity(agents);
	        	
	        	ovitoFile.write(times, L, agents);	
	        	times++;
	        }
	        ovitoFile.createVaFile(Va, N, "Va/" + N + "_eta" + ETA+".txt");        
			ovitoFile.closeFile();
			
	        System.out.println();
	        System.out.println("TIEMPO TOTAL: " + ((System.currentTimeMillis() - start) / 1000) + " segundos");
	        System.out.println("PROCESO FINALIZADO.");
		}


		

    }
}
