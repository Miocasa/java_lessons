public class Point {
    Rational _x;
    Rational _y;

    public Point(Rational x, Rational y) {
        _x = x;
        _y = y;
    }

    @Override
    public String toString() {
        return "(" + _x + "; " + _y + ")";
    }
}
