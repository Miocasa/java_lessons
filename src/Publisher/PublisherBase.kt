package Publisher

abstract class PublisherBase(val title: String) :
    Contracable, Editable, Printable, Payable, Declinable {
    private var state: State = State.NEW
    private var price: Double = 0.0
    private val content = StringBuilder() // String

    val text: String get() = content.toString()

    protected fun checkState(vararg allowed: State) {
//        if(st == state)
//            throw IllegalStateException("Not possible in $state state");
        check(state in allowed) { "Not possible in $state state" }
    }

    protected fun checkBoolean() { // for java

    }
    // Contractable
    override fun acceptContract() {
//        if(State.NEW != state)
//            throw IllegalStateException("Not possible in $state state");
        checkState(State.NEW)
        state = State.CONTRACTED
    }

    override fun declineContract() {
        checkState(State.NEW)
        state = State.DECLINED_CONTRACT
    }

    // Editable
    override fun open() {
        checkState(State.CONTRACTED)
        state = State.OPENED
    }

    override fun edit(edited: String) {
        checkState(State.OPENED)
        content.append(edited)
    }

    override fun makeUp() {
        checkState(State.OPENED)
        state = State.IN_LAYOUT
    }

    // Printable
    override fun print() {
        checkState(State.IN_LAYOUT)
        state = State.SENT_TO_PRINT
    }

    // Payable
    override fun setPrice(money: Double) {
        require(money >= 0) { "Price can't be negative: $money" }
        price = money
    }

    override fun pay(money: Double) {
        checkState(State.SENT_TO_PRINT)
        require(money >= price) { "Not enough money: need $price, got $money" }
        state = State.PAID
    }

    // Declinable
    override fun declinePublishing() {
        checkState(State.CONTRACTED, State.OPENED, State.IN_LAYOUT, State.SENT_TO_PRINT)
        state = State.DECLINED_PUBLISHING
    }

    override fun resumePublishing() {
        checkState(State.DECLINED_PUBLISHING)
        state = State.CONTRACTED
    }

    override fun close() {
        checkState(State.PAID)
        state = State.CLOSED
    }

    override fun toString(): String {
        return "${this::class.simpleName}(\"$title\", $state)"
    }
}
