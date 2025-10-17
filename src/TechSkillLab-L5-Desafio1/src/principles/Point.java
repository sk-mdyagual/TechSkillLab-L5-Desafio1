package principles;

class Point {
    //Inmutabilidad
    private final int x;
    private final int y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point translate(int dx, int dy){
        return new Point(x+dx, y+dy);
    }

    @Override
    public String toString() {
        return "(" +
                "x=" + x +
                ", y=" + y +
                ')';
    }

    public static void main(String[] args) {
        Point point1 = new Point(3,3);
        Point point2 = point1.translate(2,1);

        System.out.println(point1);
        System.out.println(point2);
    }

}



