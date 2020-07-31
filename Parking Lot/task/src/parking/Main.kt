package parking

import java.util.*

class Car(var reg: String, var color: String) {
    fun getDetails(): String {return "$reg $color" }
}

class ParkingLot(var size: Int) {
    var spaces: MutableList<Car?> = List(size) { null }.toMutableList()

    fun create(size: Int): String {
        this.size = size
        this.spaces = List(size) { null }.toMutableList()
        return "Created a parking lot with $size spots."
    }

    fun park(reg: String, color:String): String {
        if(size==0) {
            return "Sorry, a parking lot has not been created."
        }
        for (i in spaces.indices) {
            if (spaces[i]==null) {
                spaces[i]=Car(reg, color)
                return "$color car parked in spot ${i + 1}."
            }
        }
        return "Sorry, the parking lot is full."
    }
    fun leave(spot: Int): String {
        if(size==0) {
            return "Sorry, a parking lot has not been created."
        }
        if (spaces[spot - 1]!=null) {
            spaces[spot - 1] = null
            return "Spot $spot is free."
        }
        return "No car there!"
    }
    fun status(): String {
        if(size==0) {
            return "Sorry, a parking lot has not been created."
        }
        var found=false
        var statusList: MutableList<String> = mutableListOf<String>()
        for (i in spaces.indices) {
            if (spaces[i]!=null) {
                found=true
                statusList.add("${i + 1} ${spaces[i]?.getDetails()}")
            }
        }
        if (!found){
            return "Parking lot is empty."
        }
        return statusList.joinToString("\n")
    }
    fun spotByColor(color: String): String {
        if(size==0) {
            return "Sorry, a parking lot has not been created."
        }
        var statusList: MutableList<Int> = mutableListOf<Int>()
        for(i:Int in spaces.indices) {
            var car:Car? = spaces[i]
            if (car is Car) {
                if (car.color.toLowerCase() == color.toLowerCase()) {
                    statusList.add(i + 1)
                }
            }
        }
        if (statusList.isEmpty()){
            return "No cars with color $color were found."
        }
        return statusList.joinToString(", ")
    }
    fun regByColor(color: String): String {
        if(size==0) {
            return "Sorry, a parking lot has not been created."
        }
        var statusList: MutableList<String> = mutableListOf<String>()
        for(i:Int in spaces.indices) {
            var car:Car? = spaces[i]
            if (car is Car) {
                if (car.color.toLowerCase() == color.toLowerCase()) {
                    statusList.add(car.reg)
                }
            }
        }
        if (statusList.isEmpty()){
            return "No cars with color $color were found."
        }
        return statusList.joinToString(", ")
    }
    fun spotByReg(reg: String): String {
        if(size==0) {
            return "Sorry, a parking lot has not been created."
        }
        var statusList: MutableList<Int> = mutableListOf<Int>()
        for(i:Int in spaces.indices) {
            var car:Car? = spaces[i]
            if (car is Car) {
                if (car.reg.toLowerCase() == reg.toLowerCase()) {
                    statusList.add(i + 1)
                }
            }
        }
        if (statusList.isEmpty()){
            return "No cars with registration number $reg were found."
        }
        return statusList.joinToString(", ")
    }
}
fun main() {
    var scanner = Scanner(System.`in`)
    var parkingLot:ParkingLot = ParkingLot(0)

    while(scanner.hasNext()) {
        val commandString= scanner.nextLine()
        val command = commandString.split(" ").iterator()
          when (command.next()) {
              "create" -> println(parkingLot.create(command.next().toInt()))
              "park" -> println(parkingLot.park(command.next(), command.next()))
              "leave" -> println(parkingLot.leave(command.next().toInt()))
              "status" -> println(parkingLot.status())
              "spot_by_color" -> println(parkingLot.spotByColor(command.next()))
              "reg_by_color" -> println(parkingLot.regByColor(command.next()))
              "spot_by_reg" -> println(parkingLot.spotByReg(command.next()))
        }
    }
}

