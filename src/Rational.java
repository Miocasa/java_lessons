public class Rational {
    long _num;
    long _den;

    public Rational(long num) {
        _num = num;
        _den = 1;
    }

    public Rational(long num, long den) {
        if (den == 0) {
            throw new ArithmeticException();
        }
        if (den < 0) {
            num = -num;
            den = -den;
        }
        long g = gcd(Math.abs(num), den);
        _num = num / g;
        _den = den / g;
    }

    private static long gcd(long a, long b) {
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    public Rational plus(Rational r) {
        return new Rational(_num * r._den + r._num * _den, _den * r._den);
    }

    public Rational minus(Rational r) {
        return new Rational(_num * r._den - r._num * _den, _den * r._den);
    }

    public Rational times(Rational r) {
        return new Rational(_num * r._num, _den * r._den);
    }

    public Rational div(Rational r) {
        return new Rational(_num * r._den, _den * r._num);
    }

    public Rational negate() {
        return new Rational(-_num, _den);
    }

    public boolean isZero() {
        return _num == 0;
    }

    public boolean equals(Rational r) {
        return _num == r._num && _den == r._den;
    }

    @Override
    public String toString() {
        if (_den == 1) return String.valueOf(_num);
        return _num + "/" + _den;
    }
}
