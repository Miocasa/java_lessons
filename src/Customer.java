/**
 * id, Фамилия, Имя, Отчество,
 * Адрес, Номер кредитной карточки, Номер банковского счета
 */

public class Customer {

    int     _id;
    String  _first_name;
    String  _middle_name;
    String  _last_name;
    String  _addr;
    long    _card_uuid;
    long    _account_number;

    public Customer() {
    }
    public Customer(int uuid, String first_name, String middle_name, String last_name,
                    String addr, long card_uuid, long account_number){

        _id = uuid;
        _first_name = first_name;
        _middle_name = middle_name;
        _last_name = last_name;
        _addr = addr;
        _card_uuid = card_uuid;
//        System.arraycopy(card_uuid, 0, _card_uuid, 0, card_uuid.length);
        _account_number = account_number;
//        System.arraycopy(account_number, 0, _account_number, 0, account_number.length);

    }

    public void setId(int id){
        _id = id;
    }
    public void setLastName(String first_name){
        _first_name = first_name;
    }
    public void setFirstName(String middle_name){
        _middle_name = middle_name;
    }
    public void setMiddleName(String last_name){
        _last_name = last_name;
    }
    public void setAddress(String addr){
        _addr = addr;
    }
    public void setCardNumber(long card_uuid){
        _card_uuid = card_uuid;
    }
    public void setAccountNumber(long account_number){
        _account_number = account_number;
    }
    public int getId(){
        return _id;
    }
    public String getFirstName(){
        return _first_name;
    }
    public String getMiddleName(){
        return _middle_name;
    }
    public String getLastName(){
        return _last_name;
    }
    public String getAddress(){
        return _addr;
    }
    public long getCardNumber(){
        return _card_uuid;
    }
    public long getAccountNumber(){
        return _account_number;
    }

    public String toString(){
        return String.format("Id: %d | %s %s %s | Address: %s | Card id: %d | Bank Account number: %d", _id, _first_name, _middle_name, _last_name, _addr, _card_uuid, _account_number);

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
