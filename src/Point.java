public class Point {
    double _x, _y;
    double _vx, _vy;
    double _ax, _ay;

    Point(double x, double y) {
        this._x = x;
        this._y = y;
    }

    Point(double x, double y, double vx, double vy, double ax, double ay) {
        this._x = x;
        this._y = y;
        this._vx = vx;
        this._vy = vy;
        this._ax = ax;
        this._ay = ay;
    }

    double get_x_at(double t) {
        return _x + _vx * t + _ax * t * t / 2;
    }

    double get_y_at(double t) {
        return _y + _vy * t + _ay * t * t / 2;
    }

    double speed_at(double t) {
        double sx = _vx + _ax * t;
        double sy = _vy + _ay * t;
        return Math.sqrt(sx * sx + sy * sy);
    }


    double acceleration() {
        return Math.sqrt(_ax * _ax + _ay * _ay);
    }

    double distance(Point other, double t) {
        double dx = get_x_at(t) - other.get_x_at(t);
        double dy = get_y_at(t) - other.get_y_at(t);
        return Math.sqrt(dx * dx + dy * dy);
    }

    boolean intersects(Point other) {
        if (_vx * other._vy - _vy * other._vx != 0) {
            return true;
        }

        double dx = other._x - _x;
        double dy = other._y - _y;
        return dx * _vy - dy * _vx == 0;
    }

    Point add(Point o)      { return new Point(_x + o._x, _y + o._y); }
    Point subtract(Point o) { return new Point(_x - o._x, _y - o._y); }
    Point multiply(double k) { return new Point(_x * k, _y * k); }
    Point divide(double k)   { return new Point(_x / k, _y / k); }

    @Override
    public String toString() {
        return String.format( "(%f, %f)",_x, _y);
    }
}
