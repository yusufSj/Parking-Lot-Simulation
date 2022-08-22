package parking

var condition = true

fun main() {
    val parkingLot = ParkingLot()
    while (condition) {
        val command = readln().split(" ")
        when (command[0]) {
            "exit" -> condition = false
            "create" -> parkingLot.create(command.last().toInt(), true)
            "status" -> parkingLot.status()
            "park" -> parkingLot.park(command[1], command.last())
            "leave" -> parkingLot.leave(command.last().toInt())
            "reg_by_color" -> parkingLot.regByColor(command[1])
            "spot_by_color" -> parkingLot.spotByColor(command[1])
            "spot_by_reg" -> parkingLot.spotByReg(command[1])
        }
    }
}

class ParkingLot {
    private lateinit var spots: Array<String>
    private lateinit var carColors: Array<String>
    private lateinit var carPlates: Array<String>
    private var parkingLotCreated = false

    fun spotByColor(color: String) {
        var showString = ""
        var count = 0
        if (parkingLotCreated) {
            for (i in carColors)
                if (i.lowercase() == color.lowercase())
                    count++
            if (count == 0) {
                println("No cars with color $color were found.")
            } else {
                for (i in spots.indices) {
                    if (carColors[i].uppercase() == color.uppercase()) {
                        showString += (i + 1).toString() + ", "
                    }
                }
                println(showString.dropLast(2))
            }
        } else {
            println("Sorry, a parking lot has not been created.")
        }

    }

    fun spotByReg(reg: String) {
        if (parkingLotCreated) {
            if (!carPlates.contains(reg)) {
                println("No cars with registration number $reg were found.")
            } else {
                println(carPlates.indexOf(reg) + 1)
            }
        } else {
            println("Sorry, a parking lot has not been created.")
        }

    }

    fun regByColor(color: String) {
        var showString = ""
        var count = 0
        if (parkingLotCreated) {
            for (colors in carColors) {
                if (colors.lowercase() == color.lowercase())
                    count++
            }
            if (count == 0)
                println("No cars with color $color were found.")
            else {
                for (i in carColors.indices) {
                    if (carColors[i].uppercase() == color.uppercase()) {
                        showString += (carPlates[i]) + ", "
                    }
                }
                println(showString.dropLast(2))
            }

        } else {
            println("Sorry, a parking lot has not been created.")
        }

    }

    fun status() {
        if (parkingLotCreated) {
            for (i in spots.indices) {
                if (spots[i] == "1") {
                    println("${i + 1} ${carPlates[i]} ${carColors[i]}")
                }
            }
            if (!spots.contains("1")) {
                println("Parking lot is empty.")
            }
        } else {
            println("Sorry, a parking lot has not been created.")
        }
    }

    fun create(size: Int, create: Boolean = false) {
        spots = Array(size) { "0" }
        carColors = Array(size) { " " }
        carPlates = Array(size) { " " }
        if (create) {
            parkingLotCreated = true
            println("Created a parking lot with $size spots.")
        }
    }

    fun park(plaka: String, color: String) {
        if (!parkingLotCreated) {
            println("Sorry, a parking lot has not been created.")
            return
        } else {
            for (i in spots.indices) {
                if (spots[i] == "0") {
                    spots[i] = "1"
                    println("$color car parked in spot ${i + 1}.")
                    carColors[i] = color
                    carPlates[i] = plaka
                    return
                }
            }
        }
        if (!spots.contains("0")) {
            println("Sorry, the parking lot is full.")
        }
    }

    fun leave(spot: Int) {
        if (!parkingLotCreated) {
            println("Sorry, a parking lot has not been created.")
            return
        } else {
            if (spots[spot - 1] == "1") {
                println("Spot $spot is free.")
                spots[spot - 1] = "0"
                carColors[spot - 1] = " "
                carPlates[spot - 1] = " "
            } else {
                println("There is no car in spot $spot.")
            }
        }
    }
}
