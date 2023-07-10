package core;

import core.Math.Matrix.IMatrix;
import core.Math.Matrix.InvalidDimensionException;
import core.Math.Matrix.Matrix;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MatrixTest {
    private Matrix matrix;

    private final double[][] matrixValues = {{1.0, 2.0, 3.0}, {4.0, 5.0, 6.0}, {7.0, 8.0, 9.0}};

    @Before
    public void before(){
        matrix = new Matrix();
    }


    @Test
    public void createIdentityMatrix() throws InvalidDimensionException {
        //given
        //when
        matrix.createIdentityMatrix(3);
        //then
        Assert.assertEquals(1.0, matrix.getMatrixValue(0,0), 0.001);
        Assert.assertEquals(0.0, matrix.getMatrixValue(0,1), 0.001);
        Assert.assertEquals(0.0, matrix.getMatrixValue(0,2), 0.001);
        Assert.assertEquals(0.0, matrix.getMatrixValue(1,0), 0.001);
        Assert.assertEquals(1.0, matrix.getMatrixValue(1,1), 0.001);
        Assert.assertEquals(0.0, matrix.getMatrixValue(1,2), 0.001);
        Assert.assertEquals(0.0, matrix.getMatrixValue(2,0), 0.001);
        Assert.assertEquals(0.0, matrix.getMatrixValue(2,1), 0.001);
        Assert.assertEquals(1.0, matrix.getMatrixValue(2,2), 0.001);
    }

    @Test
    public void setMatrixValues() throws InvalidDimensionException {
        //given
        //when
        matrix.setMatrixValues(matrixValues);
        //then
        Assert.assertEquals(matrixValues[0][0], matrix.getMatrixValue(0,0), 0.001);
        Assert.assertEquals(matrixValues[0][1], matrix.getMatrixValue(0,1), 0.001);
        Assert.assertEquals(matrixValues[0][2], matrix.getMatrixValue(0,2), 0.001);
        Assert.assertEquals(matrixValues[1][0], matrix.getMatrixValue(1,0), 0.001);
        Assert.assertEquals(matrixValues[1][1], matrix.getMatrixValue(1,1), 0.001);
        Assert.assertEquals(matrixValues[1][2], matrix.getMatrixValue(1,2), 0.001);
        Assert.assertEquals(matrixValues[2][0], matrix.getMatrixValue(2,0), 0.001);
        Assert.assertEquals(matrixValues[2][1], matrix.getMatrixValue(2,1), 0.001);
        Assert.assertEquals(matrixValues[2][2], matrix.getMatrixValue(2,2), 0.001);
    }

    @Test
    public void setMatrixValue() throws InvalidDimensionException {
        //given
        final double valueToSet = 13.0;
        matrix.setMatrixValues(matrixValues);
        //when
        matrix.setMatrixValue(1,1,valueToSet);
        //then
        Assert.assertEquals(valueToSet, matrix.getMatrixValue(1,1), 0.001);
    }

    @Test
    public void determinant() throws InvalidDimensionException {
        //given
        matrix.setMatrixValues(matrixValues);
        //when
        double determinant = matrix.determinant();
        //then
        Assert.assertEquals(0.0, determinant, 0.001);
    }
    @Test
    public void determinant2() throws InvalidDimensionException {
        //given
        matrix.setMatrixValues(matrixValues);
        matrix.setMatrixValue(2,2, 12.0);
        //when
        double determinant = matrix.determinant();
        //then
        Assert.assertEquals(-9.0, determinant, 0.001);
    }

    @Test
    public void getWidth() throws InvalidDimensionException {
        //given
        double [][] matrixValues2 = {{1.0, 2.0, 3.0}, {4.0, 5.0, 6.0}};
        matrix.setMatrixValues(matrixValues2);
        //when
        int width = matrix.getWidth();
        //then
        Assert.assertEquals(3, width);
    }
    @Test
    public void getHeight() throws InvalidDimensionException {
        //given
        double [][] matrixValues2 = {{1.0, 2.0, 3.0}, {4.0, 5.0, 6.0}};
        matrix.setMatrixValues(matrixValues2);
        //when
        int height = matrix.getHeight();
        //then
        Assert.assertEquals(2, height);
    }
    @Test
    public void toStringTest(){
        matrix.createIdentityMatrix(3);
        String expected = "<table>\n" +
                "<tr>\n" +
                "<td>1.0</td> <td>0.0</td> <td>0.0</td> \n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td>0.0</td> <td>1.0</td> <td>0.0</td> \n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td>0.0</td> <td>0.0</td> <td>1.0</td> \n" +
                "</tr>\n" +
                "</table>";
        Assert.assertEquals(expected, matrix.toString());
    }

}
