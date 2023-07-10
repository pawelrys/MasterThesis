package  core.Math.Matrix;

/**
 * Created by Michal on 2018-04-08.
 */

/**
 * Reqesty analogicznie jak te opisane w IMatrix
 */
public interface IMatrixMath {
    /** Tworzy macierz odwrotną o ile to możliwe */
    IMatrix	inverseMatrix(IMatrix m1);

    /** Dodawanie macierzy, zwracamy uwage na poprawność rozmiaru macierzy */
    IMatrix	matrixAddition(IMatrix m1, IMatrix m2) throws InvalidDimensionException;

    /** Mnożenie dwóch macierzy, zwracamy uwagę na zgodność rozmiarów */
    IMatrix	matrixMultiplication(IMatrix m1, IMatrix m2) throws InvalidDimensionException;

    /** Odejmowanie macierzy, zwracamy uwage na poprawność rozmiaru macierzy */
    IMatrix	matrixSubtracting(IMatrix m1, IMatrix m2) throws InvalidDimensionException;

    /** Zwraca macierz transponowaną */
    IMatrix	matrixTransposition(IMatrix m1) throws InvalidDimensionException;

    /** Mnożenie macierzy przez skalar */
    IMatrix	scalarMultiplication(IMatrix m1, double scalar);
}