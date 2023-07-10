package  core.Math.Shapes;

/**
 * Created by Michal on 2018-04-07.
 */
import  core.Comon.Bitmap;
import  core.Math.Shape;

public class Rectangle extends Shape {
    PointXY p1;
    PointXY p2;
    PointXY p3;
    PointXY p4;

    public Rectangle(PointXY p1, PointXY p2, PointXY p3, PointXY p4){
        ShapeType = core.Math.ShapeType.RECTANGLE;
        this.p1 = new PointXY();
        this.p2 = new PointXY();
        this.p3 = new PointXY();
        this.p4 = new PointXY();

        this.p1.X = Math.min(Math.min(p1.X, p2.X), Math.min(p3.X, p4.X));
        this.p1.Y = Math.min(Math.min(p1.Y, p2.Y), Math.min(p3.Y, p4.Y));

        this.p2.X = Math.max(Math.max(p1.X, p2.X), Math.max(p3.X, p4.X));
        this.p2.Y = Math.min(Math.min(p1.Y, p2.Y), Math.min(p3.Y, p4.Y));

        this.p3.X = Math.max(Math.max(p1.X, p2.X), Math.max(p3.X, p4.X));
        this.p3.Y = Math.max(Math.max(p1.Y, p2.Y), Math.max(p3.Y, p4.Y));

        this.p4.X = Math.min(Math.min(p1.X, p2.X), Math.min(p3.X, p4.X));
        this.p4.Y = Math.max(Math.max(p1.Y, p2.Y), Math.max(p3.Y, p4.Y));
    }


    @Override
    public Bitmap DrawMe() {
        int a = p3.X - p1.X;
        int b = p3.Y - p1.Y;
        int array[][] = new int[1000][1000];
        for(int i=0;i<=a;i++){
            for(int j = 0;j<=b;j++){
                array[origin().X+i][origin().Y+j] = 1;
            }
        }
        return new Bitmap(array);
    }

    public PointXY origin(){
        return new PointXY(p1.X, p1.Y);
    }

    @Override
    public int Area()
    {
        int a = p3.X - p1.X;
        int b = p3.Y - p1.Y;
        return (a+1) * (b+1);
    }
}
