package me.tomidelucca.ovito;

import java.io.PrintWriter;

import me.tomidelucca.agent.Agent;

public class OvitoFile {
	
	private PrintWriter writer = null;

	public OvitoFile(String path) {      
		try {
			this.writer = new PrintWriter(path, "UTF-8");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
	public void write(int iteration, double L, Agent[] agents) {
        this.writer.println(agents.length + 4);
        this.writer.println(iteration);
        this.writer.println("0.1 0.0 0.0");
        this.writer.println("0.1 0.0 " + L);
        this.writer.println("0.1 " +L +" 0.0");
        this.writer.println("0.1 "+ L +" "+L);
        
		for (int i = 0; i < agents.length; i++)
			this.writer.println(agents[i]); //escribo la nueva posicion de un agente en archivo		
	}
	
	public void closeFile() {
		this.writer.close();
	}
	
    public void createVaFile(double[] Va, int N, String path) {
        PrintWriter writer = null;
		try {
			writer = new PrintWriter(path, "UTF-8");
			for (int i = 0; i < Va.length; i++)
	            writer.println(Va[i]);
			writer.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}     
    }
}
