public class Complex {
    double _a;
    double _bi;

    public Complex(){
        this(0.0, 0.0d);
    }
    public Complex(double a, double bi){
        _a = a; _bi = bi;
    }

    public double get_bi() {
        return _bi;
    }

    public double get_a() {
        return _a;
    }

    public void set_a(double _a) {
        this._a = _a;
    }

    public void set_bi(double _bi) {
        this._bi = _bi;
    }

    public Complex plus(Complex b) { // +
        return new Complex(this._a + b._a, this._bi + b._bi);
    }

    public Complex minus(Complex b) { // -
        return new Complex(this._a - b._a, this._bi - b._bi);
    }

    public Complex times(Complex b) { // *
        double realPart = this._a * b._a - this._bi * b._bi;
        double imagPart = this._a * b._bi + this._bi * b._a;
        return new Complex(realPart, imagPart);
    }


    /**
     *  z1 = a + bi;
     *  z2 = c + di
     *
     *  (a + bi) / (c + di) = [(ac + bd) + (bc - ad)i] / (c^2 + d^2)
     */

    public Complex div(Complex b) {
        double denominator = b._a * b._a + b._bi * b._bi;
        if (denominator == 0) {
            throw new ArithmeticException();
        }
        double realPart = (this._a * b._a + this._bi * b._bi) / denominator;
        double imagPart = (this._bi * b._a - this._a * b._bi) / denominator;
        return new Complex(realPart, imagPart);
    }

    public boolean equals(Object b) {
        if (this == b) return true;
        if (b == null || getClass() != b.getClass()) return false;
        Complex complex = (Complex) b;
        return Double.compare(complex._a, _a) == 0 && Double.compare(complex._bi, _bi) == 0;
    }

    public String toString() {
        if (_bi < 0)  return String.format("%.2f - %.2fi", _a, -_bi);
        return String.format("%.2f + %.2fi", _a, _bi);
    }

//    public Complex& operator += (Complex& val){ в java нету переопределения операторов как в с++ 🙄😤
//
//    }

}
