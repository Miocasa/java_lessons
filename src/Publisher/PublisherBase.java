package Publisher;

abstract class PublisherBase implements Contracable, Editable, Printable, Payable, Declinable {

    String title;
    PublisherBase (String title) {
        this.title = title;
    }

    private State state = State.NEW;
    private double price = 0.0;
    private StringBuilder content = new StringBuilder(); // String

    public String get() {
        return content.toString();
    }

    protected void checkState(State[] allowed) {
        for (State s : allowed) {
            if (s == state) {
                return;
            }
        }
        throw new IllegalStateException(String.format("Not possible in %s state", state));
//        if(st == state)
//            throw IllegalStateException("Not possible in $state state");
//        check(state in allowed) { "Not possible in $state state" }
    }
    protected void checkState(State allowed) {
        if (allowed == state) {
            return;
        }

        throw new IllegalStateException(String.format("Not possible in %s state", state));
    }

//    protected fun checkBoolean() { // for java
//
//    }

    // Contractable
    public void acceptContract() {
//        if(State.NEW != state)
//            throw IllegalStateException("Not possible in $state state");
        checkState(State.NEW);
        state = State.CONTRACTED;
    }

    public void declineContract() {
        checkState(State.NEW);
        state = State.DECLINED_CONTRACT;
    }

    // Editable
    public void open() {
        checkState(State.CONTRACTED);
        state = State.OPENED;
    }

    public void edit(String edited) {
        checkState(State.OPENED);
        content.append(edited);
    }

    public void makeUp() {
        checkState(State.OPENED);
        state = State.IN_LAYOUT;
    }

    // Printable
    public void print() {
        checkState(State.IN_LAYOUT);
        state = State.SENT_TO_PRINT;
    }

    // Payable
    public void setPrice(double money) {
        if (money < 0) {
            throw  new IllegalArgumentException("Price can't be negative: $money");
        }
        price = money;
    }

    public void pay(double money) {
        checkState(State.SENT_TO_PRINT);
        if (money < price) {
            throw new IllegalArgumentException("Not enough money: need $price, got $money");
        }
        state = State.PAID;
    }

    // Declinable
    public void declinePublishing() {
        checkState(new State[] {State.CONTRACTED, State.OPENED, State.IN_LAYOUT, State.SENT_TO_PRINT});
        state = State.DECLINED_PUBLISHING;
    }

    public void resumePublishing() {
        checkState(State.DECLINED_PUBLISHING);
        state = State.CONTRACTED;
    }

    public void close() {
        checkState(State.PAID);
        state = State.CLOSED;
    }

    public String toString() {
        return String.format("%s(\"%s\", %s)", getClass().getSimpleName(), title, state);
    }
}
