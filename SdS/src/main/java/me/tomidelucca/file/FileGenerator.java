package me.tomidelucca.file;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import me.tomidelucca.models.Particle;


public class FileGenerator {
	public static void write(int n, int l, List<Particle> listParticle) {
		PrintWriter writer;
		try {
			writer = new PrintWriter("out/resutl.txt", "UTF-8");
			writer.println(n);
			writer.println(l);
			
			for(Particle p: listParticle) {
				writer.println(p.getPosition());
			}
			writer.close();
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
