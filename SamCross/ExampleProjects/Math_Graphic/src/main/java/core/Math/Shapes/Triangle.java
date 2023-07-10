package  core.Math.Shapes;

/**
 * Created by Michal on 2018-04-07.
 */
import  core.Comon.Bitmap;
import  core.Math.Shape;
import core.Math.ShapeType;

public class Triangle extends Shape {
    PointXY p1;
    PointXY p2;
    PointXY p3;
    int area = -1;
    public Triangle( PointXY p1, PointXY p2, PointXY p3){
        ShapeType = core.Math.ShapeType.TRIANGLE;

        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }


    public boolean pointInTriangle(int x, int y)
    {
        int s = p1.Y * p3.X - p1.X * p3.Y + (p3.Y - p1.Y) * x + (p1.X - p3.X) * y;
        int t = p1.X * p2.Y - p1.Y * p2.X + (p1.Y - p2.Y) * x + (p2.X - p1.X) * y;



        int A = -p2.Y * p3.X + p1.Y * (p3.X - p2.X) + p1.X * (p2.Y - p3.Y) + p2.X * p3.Y;
        if (A < 1)
        {
            s = -s;
            t = -t;
            A = -A;
        }
        return s >= 0 && t >= 0 && (s + t) <= A;
    }




    @Override
    public Bitmap DrawMe() {
        area = 0;
        int sizeX = Math.max(Math.max(p1.X, p2.X), p3.X) - Math.min(Math.min(p1.X, p2.X), p3.X);
        int sizeY = Math.max(Math.max(p1.Y, p2.Y), p3.Y) - Math.min(Math.min(p1.Y, p2.Y), p3.Y);
//        System.out.println("sizeX: " + sizeX);
//        System.out.println("sizeY: " + sizeY);
        int array[][] = new int[1000][1000];
        for(int i=0; i<=sizeX; i++){
            for(int j=0; j<=sizeY; j++) {
                if(pointInTriangle(i, j)){
                    array[origin().X+i][origin().X+j] = 1;
                    area++;
                }
            }
        }
        return new Bitmap(array);
    }

    public PointXY origin(){
        int x1 = Math.min(Math.min(p1.X, p2.X), p3.X);
        int y1 = Math.min(Math.min(p1.Y, p2.Y), p3.Y);
        return new PointXY(x1, y1);
    }

    @Override
    public int Area() {
        if(area == -1) DrawMe();
        /*double a = Math.sqrt((p1.X - p2.X)*(p1.X - p2.X) + (p1.Y - p2.Y)*(p1.Y - p2.Y));
        double b = Math.sqrt((p1.X - p3.X)*(p1.X - p3.X) + (p1.Y - p3.Y)*(p1.Y - p3.Y));
        double c = Math.sqrt((p3.X - p2.X)*(p3.X - p2.X) + (p3.Y - p2.Y)*(p3.Y - p2.Y));
        double p = (a+b+c)/2;
        return (int)Math.sqrt(p*(p-a)*(p-b)*(p-c));*/
        return area;
    }
}
