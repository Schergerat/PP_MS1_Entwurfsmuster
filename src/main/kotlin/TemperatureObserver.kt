

interface TemperatureObserver {
    fun update(tmp: Double)
}

class TemperatureAlert(private val alertTmp: Double, private val alertMsg: String): TemperatureObserver{
    override fun update(tmp: Double) {
        if (tmp > alertTmp)
            println(alertMsg)
    }
}

class HeatingSystemObserver(private val offThreshold: Double, private val onThreshold: Double): TemperatureObserver{
    private var lastFiveTemperatures = mutableListOf<Double>()

    override fun update(tmp: Double) {
            lastFiveTemperatures.add(tmp)
            if(lastFiveTemperatures.size == 5){
                if (lastFiveTemperatures.average() > offThreshold) println("Heizung aus")
                else if (lastFiveTemperatures.average() < onThreshold) println("Heizung an")
                lastFiveTemperatures = mutableListOf()
            }
    }
}