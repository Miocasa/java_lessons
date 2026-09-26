public class Line {
    Rational _a;
    Rational _b;
    Rational _c;

    public Line(Rational a, Rational b, Rational c) {
        _a = a;
        _b = b;
        _c = c;
    }

    public Point getIntersectionOX() {
//        if (_a._num == 0) return null;
        if (_a.isZero()) {
            return null;
        }
        Rational x = _c.negate().div(_a);
        return new Point(x, new Rational(0));
    }

    public Point getIntersectionOY() {
        if (_b.isZero()) {
            return null;
        }
        Rational y = _c.negate().div(_b);
        return new Point(new Rational(0), y);
    }

    public boolean isParallelTo(Line other) {
//        Rational det = _a.times(other._b).minus(other._a.times(_b));
//        return det.isZero();
        return _a.times(other._b).minus(other._a.times(_b)).isZero();
    }

    public Point getIntersectionWithLine(Line other) {
//        Rational det = _a.times(other._b).minus(other._a.times(_b));
//        if (det.isZero()) return null;

        Rational det = _a.times(other._b).minus(other._a.times(_b));
        if (det.isZero()) {
            return null;
        }

        Rational detX = _b.times(other._c).minus(other._b.times(_c));
        Rational detY = other._a.times(_c).minus(_a.times(other._c));

        Rational x = detX.div(det);
        Rational y = detY.div(det);

        return new Point(x, y);
    }

    @Override
    public String toString() {
//        return _a + "x + " + _b + "y + " + _c + " = 0";
        return String.format("(%s)*x + (%s)*y + (%s) = 0", _a, _b, _c);
    }
}