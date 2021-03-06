package me.tomidelucca.methods;

import me.tomidelucca.models.Particle;

import java.util.*;

public class BruteForceMethod {

    public static Map<Particle, Set<Particle>> neighbours(Particle[] particles, double Rc) {

        Map<Particle, Set<Particle>> result = BruteForceMethod.createAndPopulateEmptyMap(particles);

        for(int i = 0; i<particles.length; i++) {
            Particle p = particles[i];
            for(int j = 0; j<particles.length; j++) {
                Particle otherParticle = particles[j];
                if (p.distanceToParticle(otherParticle) <= Rc && !p.equals(otherParticle)) {
                    result.get(p).add(otherParticle);
                    result.get(otherParticle).add(p);
                }
            }
        }

        return result;
    }

    private static Map<Particle, Set<Particle>> createAndPopulateEmptyMap(Particle[] particles) {
        Map<Particle, Set<Particle>> map = new HashMap<>();

        for(int i = 0; i<particles.length; i++) {
            map.put(particles[i], new HashSet<>());
        }

        return map;
    }
}
