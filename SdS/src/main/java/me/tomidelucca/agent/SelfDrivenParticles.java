package me.tomidelucca.agent;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import me.tomidelucca.methods.CellIndexMethod;
import me.tomidelucca.models.Particle;

public class SelfDrivenParticles extends CellIndexMethod {
	
    public static void move(Particle[] particleArray, double delta_t) {
	    for(int i = 0; i < particleArray.length; i++) {
	    	Movement.doMove((Agent) particleArray[i]);
	    }
    }

    //TODO chequear que estos calculos sean correctos.
	public static void updateAngle(Map<Particle, Set<Particle>> map, double n) {
		for(Entry<Particle,Set<Particle>> entry : map.entrySet()) {
		    Particle key = entry.getKey();
		    Set<Particle> value = entry.getValue();
		    
		    double sum_SIN_angles = Math.sin(((Agent) key).getAngle());
		    double sum_COS_angles = Math.cos(((Agent) key).getAngle());
		    double AVG_SIN_angle = 0.0;
		    double AVG_COS_angle = 0.0;
		    
		    double thita_r = 0.0;
		    double delta_thita = 0.0;
    		Random r = new Random();
		    
		    for(Particle p: value) {
		    	sum_SIN_angles += Math.sin(((Agent) p).getAngle());
		    	sum_COS_angles += Math.cos(((Agent) p).getAngle());
		    }
		    
		    AVG_SIN_angle = sum_SIN_angles / (1 + value.size());
		    AVG_COS_angle = sum_COS_angles / (1 + value.size());
		    
		    thita_r = Math.atan2(AVG_SIN_angle, AVG_COS_angle);
		    delta_thita = (-n/2) + ((n/2) - (-n/2)) * r.nextDouble();
		    
		    ((Agent) key).setAngle(thita_r + delta_thita);
		}
	}

	public static void updatePosition(Map<Particle, Set<Particle>> map, double L) {
		Set<Particle> setParticle = map.keySet();
		
		for(Particle p: setParticle) {
			if(p.getPosition().getX() < 0)
				p.getPosition().setX( L + p.getPosition().getX());
			
			if(p.getPosition().getY() < 0)
				p.getPosition().setY( L + p.getPosition().getY());
			
			if(p.getPosition().getX() > L)
				p.getPosition().setX( L - p.getPosition().getX());
			
			if(p.getPosition().getY() > L)
				p.getPosition().setY( L - p.getPosition().getY());
		}
	}
}
