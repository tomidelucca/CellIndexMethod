package me.tomidelucca.models;

import java.util.ArrayList;
import java.util.List;

public class SquareMatrix {

    private List<List<List<Particle>>> matrix;
    private int size;

    public SquareMatrix(int size) {
        this.size = size;
        matrix = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            matrix.add(new ArrayList<>(size));
            for (int j = 0; j < size; j++) {
                matrix.get(i).add(j, new ArrayList<>());
            }
        }
    }

    public List<Particle> getElement(int x, int y) {
        return matrix.get(x).get(y);
    }

    @Override
    public String toString() {
        return "SquareMatrix{" +
                "matrix=" + matrix +
                ", size=" + size +
                '}';
    }
}
