package  core.Math.Matrix;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

/**
 * Created by Michal on 2018-04-08.
 */
public class Matrix implements IMatrix {
    private RealMatrix realMatrix;

    public Matrix(RealMatrix realMatrix) {
        this.realMatrix = realMatrix;
    }
    public Matrix() {
        realMatrix = MatrixUtils.createRealIdentityMatrix(3);
    }

    /** Generuje macierz jednostkową o podarnym rozmiarze
     * MAKSYMALNIE 3X3 po utwoezeniu niech ja wypisze pusta do html
     * Qery ?...&MatReq=createIdentityMatrix&size=WSTAW WARTOSC
     * */

    @Override
    public IMatrix createIMatrix(RealMatrix realMatrix) {
        return new Matrix(realMatrix);
    }

    @Override
    public void createIdentityMatrix(int size){
        realMatrix = MatrixUtils.createRealIdentityMatrix(size);
    }

    /** Oblicza wartość wyznacznika dla kwadratowej macierzy, w przypadku innej mamy wyjątek
     * Qery ?...&MatReq=determinant
     * */
    @Override
    public double determinant() throws InvalidDimensionException{
        if(!realMatrix.isSquare()) throw new InvalidDimensionException();
        return new LUDecomposition(realMatrix).getDeterminant();
    }

    /** Zwraca wartość pola w macierzy
     * Qery ?...&MatReq=getMatrixValue
     * */
    @Override
    public double getMatrixValue(int row, int column){
        return realMatrix.getData()[row][column]; // do sprawdzenia
    }

    /** Ustala wartość pojedyńczej komórki
     * itd ogolna zasada robienia qery ma być taka &MatReq=nazwaFunkcji&nazwaparametru=wartosc ....
     * */
    @Override
    public void setMatrixValue(int row, int column, double value){
        realMatrix.setEntry(row, column, value);
    }

    /** Ustala zawartość macierzy na podstawie tablicy */
    @Override
    public void setMatrixValues(double[][] values) throws InvalidDimensionException{
        realMatrix = MatrixUtils.createRealMatrix(values);
    }

    @Override
    public int getWidth(){
        return realMatrix.getColumnDimension();
    }

    @Override
    public int getHeight(){
        return realMatrix.getRowDimension();
    }

    /** Reprezentacja w formie String macierzy, powinna utrzymywać konwencję wierszy i kolumn
     *  Niech zwraca HTML w postaci
     *  <table>
     *  <tr>
     *      <td>kol01 wiersz 01</td> <td>kol02 wiersz 01</td>
     *  </tr>
     *   <tr>
     *      <td>kol01 wiersz 02</td> <td>kol02 wiersz 02</td>
     *  </tr>
     *  ITD...
     *  </table>
     * */
    @Override
    public String toString(){
        double[][] data = realMatrix.getData();
        int row = data.length;
        int col = data[0].length;
        String result = "<table>";
        for(int i = 0; i < row; i++) {
            result += "\n<tr>\n";
            for (int j = 0; j < col; j++) {
                result += "<td>" + data[i][j] + "</td> ";
            }
            result+= "\n</tr>";
        }
        result += "\n</table>";
        return result;
    }

    @Override
    public RealMatrix getRealMatrix() {
        return realMatrix;
    }
}
