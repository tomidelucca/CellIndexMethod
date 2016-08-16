package me.tomidelucca.methods;

import me.tomidelucca.models.Particle;
import me.tomidelucca.models.SquareMatrix;

import java.util.List;
import java.util.Map;

public class CellIndexMethod {

    public static Map<Particle, List<Particle>> neighbours(Particle[] particles, double L, int M, double Rc) {

        SquareMatrix matrix = CellIndexMethod.createAndPopulateMatrix(particles, M, L);

        System.out.println(matrix);

        return null;
    }

    private static SquareMatrix createAndPopulateMatrix(Particle[] particles, int M, double L) {

        SquareMatrix matrix = new SquareMatrix(M);
        double cellSize = L/M;

        for(int i = 0; i<particles.length; i++) {
            Particle p = particles[i];
            int x = (int)(p.getPosition().getX()/cellSize);
            int y = (int)(p.getPosition().getY()/cellSize);
            matrix.getElement(x,y).add(p);
        }

        return matrix;
    }


}
