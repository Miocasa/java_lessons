import java.awt.*;
import java.util.Date;

/**
 * Марка, Модель, Год выпуска, Цвет, Цена, Регистрационный номер.
 */

public class Car {

    String _mark;
    String _model;
    int _release_year;
    String _color;
    long _price;
    String _registration_no;

    public Car() {
    }

    public Car(String mark, String model, int release_year, String color,
               long price, String registration_no){

        _mark = mark;
        _model = model;
        _release_year = release_year;
        _color = color;
        _price = price;
        _registration_no = registration_no;


//        System.arraycopy(account_number, 0, _account_number, 0, account_number.length);

    }

    public String get_mark() {
        return _mark;
    }

    public void set_mark(String _mark) {
        this._mark = _mark;
    }

    public String get_model() {
        return _model;
    }

    public void set_model(String _model) {
        this._model = _model;
    }

    public int get_release_year() {
        return _release_year;
    }

    public void set_release_year(int _release_year) {
        this._release_year = _release_year;
    }

    public String get_color() {
        return _color;
    }

    public void set_color(String _color) {
        this._color = _color;
    }

    public long get_price() {
        return _price;
    }

    public void set_price(long _price) {
        this._price = _price;
    }

    public String get_registration_no() {
        return _registration_no;
    }

    public void set_registration_no(String _registration_no) {
        this._registration_no = _registration_no;
    }
//
//    String _mark;
//    String _model;
//    Date _release_year;
//    Color _color;
//    long _price;
//    String _registration_no;
//    Марка, Модель, Год выпуска, Цвет, Цена, Регистрационный номер.
    public String toString(){
        return String.format("%s %s %s %s | Price: %d | Registration: %s", _color, _mark, _model,  _release_year, _price, _registration_no);

    }

//        for (char ch : _card_uuid){
//            result.append(ch);
//        }
//        for (char ch : _account_number){
//            result.append(ch);
//        }
//    char[] _uuid = new char[16];
//    String _first_name;
//    String _middle_name;
//    String _last_name;
//    String _addr;
//    char[] _card_uuid = new char[16];
//    char[] _account_number = new char[20];
}
