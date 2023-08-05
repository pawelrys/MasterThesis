using Math_Graphic.core.math.matrix;

namespace Math_Graphic.Tests.tests;

[TestFixture]
public class MatrixMathTest
{
    private MatrixMath matrixMath;
    private Matrix matrix;
    private Matrix matrix2;


    private double[][] matrixValues;

    private double[][] matrixValues2;


    [SetUp]
    public void TestInitialize()
    {
        matrixMath = new MatrixMath();
        matrix = new Matrix();
        matrix2 = new Matrix();
        matrixValues = new double[][]
            { new double[] { 1.0, 2.0, 3.0 }, new double[] { 0.0, 1.0, 5.0 }, new double[] { 5.0, 6.0, 0.0 } };
        matrixValues2 = new double[][]
            { new double[] { 1.0, 2.0, 3.0 }, new double[] { 4.0, 5.0, 6.0 }, new double[] { 7.0, 8.0, 9.0 } };
    }

    [Test]
    public void InverseMatrix()
    {
        matrix.SetMatrixValues(matrixValues); 
        
        var result = matrixMath.InverseMatrix(matrix);
        
        Assert.That(-6.0, Is.EqualTo(result.GetMatrixValue(0, 0)).Within(0.001));
        Assert.That(18.0 / 5.0, Is.EqualTo(result.GetMatrixValue(0, 1)).Within(0.001));
        Assert.That(7.0 / 5.0, Is.EqualTo(result.GetMatrixValue(0, 2)).Within(0.001));
        Assert.That(5.0, Is.EqualTo(result.GetMatrixValue(1, 0)).Within(0.001));
        Assert.That(-3.0, Is.EqualTo(result.GetMatrixValue(1, 1)).Within(0.001));
        Assert.That(-1.0, Is.EqualTo(result.GetMatrixValue(1, 2)).Within(0.001));
        Assert.That(-1.0, Is.EqualTo(result.GetMatrixValue(2, 0)).Within(0.001));
        Assert.That(4.0 / 5.0, Is.EqualTo(result.GetMatrixValue(2, 1)).Within(0.001));
        Assert.That(1.0 / 5.0, Is.EqualTo(result.GetMatrixValue(2, 2)).Within(0.001));
    }

    [Test]
    public void MatrixAddition()
    {
        matrix.SetMatrixValues(matrixValues);
        matrix2.SetMatrixValues(matrixValues2);
        
        var result = matrixMath.MatrixAddition(matrix, matrix2);

        Assert.That(2.0, Is.EqualTo(result.GetMatrixValue(0, 0)).Within(0.001));
        Assert.That(4.0, Is.EqualTo(result.GetMatrixValue(0, 1)).Within(0.001));
        Assert.That(6.0, Is.EqualTo(result.GetMatrixValue(0, 2)).Within(0.001));
        Assert.That(4.0, Is.EqualTo(result.GetMatrixValue(1, 0)).Within(0.001));
        Assert.That(6.0, Is.EqualTo(result.GetMatrixValue(1, 1)).Within(0.001));
        Assert.That(11.0, Is.EqualTo(result.GetMatrixValue(1, 2)).Within(0.001));
        Assert.That(12.0, Is.EqualTo(result.GetMatrixValue(2, 0)).Within(0.001));
        Assert.That(14.0, Is.EqualTo(result.GetMatrixValue(2, 1)).Within(0.001));
        Assert.That(9.0, Is.EqualTo(result.GetMatrixValue(2, 2)).Within(0.001));
    }

    [Test]
    public void MatrixMultiplication()
    {
        matrix.SetMatrixValues(matrixValues);
        matrix2.SetMatrixValues(matrixValues2);
        
        var result = matrixMath.MatrixMultiplication(matrix, matrix2);

        Assert.That(30.0, Is.EqualTo(result.GetMatrixValue(0, 0)).Within(0.001));
        Assert.That(36.0, Is.EqualTo(result.GetMatrixValue(0, 1)).Within(0.001));
        Assert.That(42.0, Is.EqualTo(result.GetMatrixValue(0, 2)).Within(0.001));
        Assert.That(39.0, Is.EqualTo(result.GetMatrixValue(1, 0)).Within(0.001));
        Assert.That(45.0, Is.EqualTo(result.GetMatrixValue(1, 1)).Within(0.001));
        Assert.That(51.0, Is.EqualTo(result.GetMatrixValue(1, 2)).Within(0.001));
        Assert.That(29.0, Is.EqualTo(result.GetMatrixValue(2, 0)).Within(0.001));
        Assert.That(40.0, Is.EqualTo(result.GetMatrixValue(2, 1)).Within(0.001));
        Assert.That(51.0, Is.EqualTo(result.GetMatrixValue(2, 2)).Within(0.001));
    }

    [Test]
    public void MatrixSubtracting()
    {
        matrix.SetMatrixValues(matrixValues);
        matrix2.SetMatrixValues(matrixValues2);

        var result = matrixMath.MatrixSubtracting(matrix, matrix2);

        Assert.That(0.0, Is.EqualTo(result.GetMatrixValue(0, 0)).Within(0.001));
        Assert.That(0.0, Is.EqualTo(result.GetMatrixValue(0, 1)).Within(0.001));
        Assert.That(0.0, Is.EqualTo(result.GetMatrixValue(0, 2)).Within(0.001));
        Assert.That(-4.0, Is.EqualTo(result.GetMatrixValue(1, 0)).Within(0.001));
        Assert.That(-4.0, Is.EqualTo(result.GetMatrixValue(1, 1)).Within(0.001));
        Assert.That(-1.0, Is.EqualTo(result.GetMatrixValue(1, 2)).Within(0.001));
        Assert.That(-2.0, Is.EqualTo(result.GetMatrixValue(2, 0)).Within(0.001));
        Assert.That(-2.0, Is.EqualTo(result.GetMatrixValue(2, 1)).Within(0.001));
        Assert.That(-9.0, Is.EqualTo(result.GetMatrixValue(2, 2)).Within(0.001));
    }

    [Test]
    public void MatrixTransposition()
    {
        matrix.SetMatrixValues(matrixValues);
        
        var result = matrixMath.MatrixTransposition(matrix);

        Assert.That(1.0, Is.EqualTo(result.GetMatrixValue(0, 0)).Within(0.001));
        Assert.That(0.0, Is.EqualTo(result.GetMatrixValue(0, 1)).Within(0.001));
        Assert.That(5.0, Is.EqualTo(result.GetMatrixValue(0, 2)).Within(0.001));
        Assert.That(2.0, Is.EqualTo(result.GetMatrixValue(1, 0)).Within(0.001));
        Assert.That(1.0, Is.EqualTo(result.GetMatrixValue(1, 1)).Within(0.001));
        Assert.That(6.0, Is.EqualTo(result.GetMatrixValue(1, 2)).Within(0.001));
        Assert.That(3.0, Is.EqualTo(result.GetMatrixValue(2, 0)).Within(0.001));
        Assert.That(5.0, Is.EqualTo(result.GetMatrixValue(2, 1)).Within(0.001));
        Assert.That(0.0, Is.EqualTo(result.GetMatrixValue(2, 2)).Within(0.001));
    }

    [Test]
    public void ScalarMultiplication()
    {
        matrix.SetMatrixValues(matrixValues);
        const double scalar = 1.5;

        var result = matrixMath.ScalarMultiplication(matrix, scalar);

        Assert.That(1.5, Is.EqualTo(result.GetMatrixValue(0, 0)).Within(0.001));
        Assert.That(3.0, Is.EqualTo(result.GetMatrixValue(0, 1)).Within(0.001));
        Assert.That(4.5, Is.EqualTo(result.GetMatrixValue(0, 2)).Within(0.001));
        Assert.That(0.0, Is.EqualTo(result.GetMatrixValue(1, 0)).Within(0.001));
        Assert.That(1.5, Is.EqualTo(result.GetMatrixValue(1, 1)).Within(0.001));
        Assert.That(7.5, Is.EqualTo(result.GetMatrixValue(1, 2)).Within(0.001));
        Assert.That(7.5, Is.EqualTo(result.GetMatrixValue(2, 0)).Within(0.001));
        Assert.That(9.0, Is.EqualTo(result.GetMatrixValue(2, 1)).Within(0.001));
        Assert.That(0.0, Is.EqualTo(result.GetMatrixValue(2, 2)).Within(0.001));
    }
}