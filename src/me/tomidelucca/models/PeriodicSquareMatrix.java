package me.tomidelucca.models;

import java.util.List;

public class PeriodicSquareMatrix extends SquareMatrix {

    public PeriodicSquareMatrix(int size) {
        super(size);
    }

    @Override
    public List<Particle> getElement(int x, int y) {
        return matrix.get(Math.floorMod(x, size)).get(Math.floorMod(y, size));
    }
}
