open class Device(val type: String) {
    open fun getFullInfo(): String = "$type type"
}

open class InputDevice(type: String, val portsNumber: Int) : Device(type) {
    open fun getFullInfo(): String = "$portsNumber ports"
}

fun main() {
    val mouse=InputDevice("Mouse", 1)
    val mouse2=InputDevice("Mouse2", 1)
    mouse.equals




}