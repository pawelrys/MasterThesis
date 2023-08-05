using Math_Graphic.core.math.matrix;

namespace Math_Graphic.Tests.tests;

[TestFixture]
public class MatrixTest
{
    private Matrix matrix;

    private double[][] matrixValues =
        { new double[] { 1.0, 2.0, 3.0 }, new double[] { 0.0, 1.0, 5.0 }, new double[] { 5.0, 6.0, 0.0 } };

    [SetUp]
    public void Before()
    {
        matrix = new Matrix();
    }


    [Test]
    public void CreateIdentityMatrix()
    {
        matrix.CreateIdentityMatrix(3);

        Assert.That(1.0, Is.EqualTo(matrix.GetMatrixValue(0, 0)).Within(0.001));
        Assert.That(0.0, Is.EqualTo(matrix.GetMatrixValue(0, 1)).Within(0.001));
        Assert.That(0.0, Is.EqualTo(matrix.GetMatrixValue(0, 2)).Within(0.001));
        Assert.That(0.0, Is.EqualTo(matrix.GetMatrixValue(1, 0)).Within(0.001));
        Assert.That(1.0, Is.EqualTo(matrix.GetMatrixValue(1, 1)).Within(0.001));
        Assert.That(0.0, Is.EqualTo(matrix.GetMatrixValue(1, 2)).Within(0.001));
        Assert.That(0.0, Is.EqualTo(matrix.GetMatrixValue(2, 0)).Within(0.001));
        Assert.That(0.0, Is.EqualTo(matrix.GetMatrixValue(2, 1)).Within(0.001));
        Assert.That(1.0, Is.EqualTo(matrix.GetMatrixValue(2, 2)).Within(0.001));
    }

    [Test]
    public void SetMatrixValues()
    {
        matrix.SetMatrixValues(matrixValues);

        Assert.That(matrixValues[0][0], Is.EqualTo(matrix.GetMatrixValue(0, 0)).Within(0.001));
        Assert.That(matrixValues[0][1], Is.EqualTo(matrix.GetMatrixValue(0, 1)).Within(0.001));
        Assert.That(matrixValues[0][2], Is.EqualTo(matrix.GetMatrixValue(0, 2)).Within(0.001));
        Assert.That(matrixValues[1][0], Is.EqualTo(matrix.GetMatrixValue(1, 0)).Within(0.001));
        Assert.That(matrixValues[1][1], Is.EqualTo(matrix.GetMatrixValue(1, 1)).Within(0.001));
        Assert.That(matrixValues[1][2], Is.EqualTo(matrix.GetMatrixValue(1, 2)).Within(0.001));
        Assert.That(matrixValues[2][0], Is.EqualTo(matrix.GetMatrixValue(2, 0)).Within(0.001));
        Assert.That(matrixValues[2][1], Is.EqualTo(matrix.GetMatrixValue(2, 1)).Within(0.001));
        Assert.That(matrixValues[2][2], Is.EqualTo(matrix.GetMatrixValue(2, 2)).Within(0.001));
    }

    [Test]
    public void SetMatrixValue()
    {
        const double valueToSet = 13.0;
        matrix.SetMatrixValues(matrixValues);

        matrix.SetMatrixValue(1, 1, valueToSet);

        Assert.That(valueToSet, Is.EqualTo(matrix.GetMatrixValue(1, 1)).Within(0.001));
    }

    [Test]
    public void Determinant() {
        matrix.SetMatrixValues(matrixValues);

        var determinant = matrix.Determinant();

        Assert.That(5.0, Is.EqualTo(determinant).Within(0.001));
    }
    
    [Test]
    public void Determinant2() {
        matrix.SetMatrixValues(matrixValues);
        matrix.SetMatrixValue(2,2, 12.0);

        var determinant = matrix.Determinant();

        Assert.That(17, Is.EqualTo(determinant).Within(0.001));
    }

    [Test]
    public void GetWidth()
    {
        double[][] matrixValues2 = { new double[] { 1.0, 2.0, 3.0 }, new double[] { 4.0, 5.0, 6.0 } };
        matrix.SetMatrixValues(matrixValues2);

        var width = matrix.GetWidth();

        Assert.That(3, Is.EqualTo(width));
    }

    [Test]
    public void GetHeight()
    {
        double[][] matrixValues2 = { new double[] { 1.0, 2.0, 3.0 }, new double[] { 4.0, 5.0, 6.0 } };
        
        matrix.SetMatrixValues(matrixValues2);

        Assert.That(2, Is.EqualTo(matrix.GetHeight()));
    }

    [Test]
    public void ToStringTest()
    {
        matrix.CreateIdentityMatrix(3);
        var expected = "<table>\n" +
                       "<tr>\n" +
                       "<td>1,0</td> <td>0,0</td> <td>0,0</td> \n" +
                       "</tr>\n" +
                       "<tr>\n" +
                       "<td>0,0</td> <td>1,0</td> <td>0,0</td> \n" +
                       "</tr>\n" +
                       "<tr>\n" +
                       "<td>0,0</td> <td>0,0</td> <td>1,0</td> \n" +
                       "</tr>\n" +
                       "</table>";
        Assert.That(expected, Is.EqualTo(matrix.ToString()));
    }
}