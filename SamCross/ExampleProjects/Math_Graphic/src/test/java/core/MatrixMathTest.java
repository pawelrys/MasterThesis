package core;

import core.Math.Matrix.IMatrix;
import core.Math.Matrix.InvalidDimensionException;
import core.Math.Matrix.Matrix;
import core.Math.Matrix.MatrixMath;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MatrixMathTest {
    private MatrixMath matrixMath;

    private Matrix matrix;

    private Matrix matrix2;

    private final double[][] matrixValues = {{1.0, 2.0, 3.0}, {0.0, 1.0, 5.0}, {5.0, 6.0, 0.0}};

    private final double[][] matrixValues2 = {{1.0, 2.0, 3.0}, {4.0, 5.0, 6.0}, {7.0, 8.0, 9.0}};


    @Before
    public void before(){
        matrixMath = new MatrixMath();
        matrix = new Matrix();
        matrix2 = new Matrix();
    }

    @Test
    public void inverseMatrix() throws InvalidDimensionException {
        //given
        matrix.setMatrixValues(matrixValues);
        //when
        IMatrix result = matrixMath.inverseMatrix(matrix);
        //then
        Assert.assertEquals(-6.0, result.getMatrixValue(0,0), 0.001);
        Assert.assertEquals(18.0/5.0, result.getMatrixValue(0,1), 0.001);
        Assert.assertEquals(7.0/5.0, result.getMatrixValue(0,2), 0.001);
        Assert.assertEquals(5.0, result.getMatrixValue(1,0), 0.001);
        Assert.assertEquals(-3.0, result.getMatrixValue(1,1), 0.001);
        Assert.assertEquals(-1.0, result.getMatrixValue(1,2), 0.001);
        Assert.assertEquals(-1.0, result.getMatrixValue(2,0), 0.001);
        Assert.assertEquals(4.0/5.0, result.getMatrixValue(2,1), 0.001);
        Assert.assertEquals(1.0/5.0, result.getMatrixValue(2,2), 0.001);
    }
    @Test
    public void matrixAddition() throws InvalidDimensionException {
        //given
        matrix.setMatrixValues(matrixValues);
        matrix2.setMatrixValues(matrixValues2);
        //when
        IMatrix result = matrixMath.matrixAddition(matrix, matrix2);
        //then
        Assert.assertEquals(2.0, result.getMatrixValue(0,0), 0.001);
        Assert.assertEquals(4.0, result.getMatrixValue(0,1), 0.001);
        Assert.assertEquals(6.0, result.getMatrixValue(0,2), 0.001);
        Assert.assertEquals(4.0, result.getMatrixValue(1,0), 0.001);
        Assert.assertEquals(6.0, result.getMatrixValue(1,1), 0.001);
        Assert.assertEquals(11.0, result.getMatrixValue(1,2), 0.001);
        Assert.assertEquals(12.0, result.getMatrixValue(2,0), 0.001);
        Assert.assertEquals(14.0, result.getMatrixValue(2,1), 0.001);
        Assert.assertEquals(9.0, result.getMatrixValue(2,2), 0.001);
    }
    @Test
    public void matrixMultiplication() throws InvalidDimensionException {
        //given
        matrix.setMatrixValues(matrixValues);
        matrix2.setMatrixValues(matrixValues2);
        //when
        IMatrix result = matrixMath.matrixMultiplication(matrix, matrix2);
        //then
        Assert.assertEquals(30.0, result.getMatrixValue(0,0), 0.001);
        Assert.assertEquals(36.0, result.getMatrixValue(0,1), 0.001);
        Assert.assertEquals(42.0, result.getMatrixValue(0,2), 0.001);
        Assert.assertEquals(39.0, result.getMatrixValue(1,0), 0.001);
        Assert.assertEquals(45.0, result.getMatrixValue(1,1), 0.001);
        Assert.assertEquals(51.0, result.getMatrixValue(1,2), 0.001);
        Assert.assertEquals(29.0, result.getMatrixValue(2,0), 0.001);
        Assert.assertEquals(40.0, result.getMatrixValue(2,1), 0.001);
        Assert.assertEquals(51.0, result.getMatrixValue(2,2), 0.001);
    }

    @Test
    public void matrixSubtracting() throws InvalidDimensionException {
        //given
        matrix.setMatrixValues(matrixValues);
        matrix2.setMatrixValues(matrixValues2);
        //when
        IMatrix result = matrixMath.matrixSubtracting(matrix, matrix2);
        //then
        Assert.assertEquals(0.0, result.getMatrixValue(0,0), 0.001);
        Assert.assertEquals(0.0, result.getMatrixValue(0,1), 0.001);
        Assert.assertEquals(0.0, result.getMatrixValue(0,2), 0.001);
        Assert.assertEquals(-4.0, result.getMatrixValue(1,0), 0.001);
        Assert.assertEquals(-4.0, result.getMatrixValue(1,1), 0.001);
        Assert.assertEquals(-1.0, result.getMatrixValue(1,2), 0.001);
        Assert.assertEquals(-2.0, result.getMatrixValue(2,0), 0.001);
        Assert.assertEquals(-2.0, result.getMatrixValue(2,1), 0.001);
        Assert.assertEquals(-9.0, result.getMatrixValue(2,2), 0.001);
    }

    @Test
    public void matrixTransposition() throws InvalidDimensionException {
        //given
        matrix.setMatrixValues(matrixValues);
        //when
        IMatrix result = matrixMath.matrixTransposition(matrix);
        //then
        Assert.assertEquals(1.0, result.getMatrixValue(0,0), 0.001);
        Assert.assertEquals(0.0, result.getMatrixValue(0,1), 0.001);
        Assert.assertEquals(5.0, result.getMatrixValue(0,2), 0.001);
        Assert.assertEquals(2.0, result.getMatrixValue(1,0), 0.001);
        Assert.assertEquals(1.0, result.getMatrixValue(1,1), 0.001);
        Assert.assertEquals(6.0, result.getMatrixValue(1,2), 0.001);
        Assert.assertEquals(3.0, result.getMatrixValue(2,0), 0.001);
        Assert.assertEquals(5.0, result.getMatrixValue(2,1), 0.001);
        Assert.assertEquals(0.0, result.getMatrixValue(2,2), 0.001);
    }

    @Test
    public void scalarMultiplication() throws InvalidDimensionException {
        //given
        matrix.setMatrixValues(matrixValues);
        final double scalar = 1.5;
        //when
        IMatrix result = matrixMath.scalarMultiplication(matrix,scalar);
        //then
        Assert.assertEquals(1.5, result.getMatrixValue(0,0), 0.001);
        Assert.assertEquals(3.0, result.getMatrixValue(0,1), 0.001);
        Assert.assertEquals(4.5, result.getMatrixValue(0,2), 0.001);
        Assert.assertEquals(0.0, result.getMatrixValue(1,0), 0.001);
        Assert.assertEquals(1.5, result.getMatrixValue(1,1), 0.001);
        Assert.assertEquals(7.5, result.getMatrixValue(1,2), 0.001);
        Assert.assertEquals(7.5, result.getMatrixValue(2,0), 0.001);
        Assert.assertEquals(9.0, result.getMatrixValue(2,1), 0.001);
        Assert.assertEquals(0.0, result.getMatrixValue(2,2), 0.001);
    }
}
