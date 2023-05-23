
interface TemperatureSubject {
    val observers : MutableList <TemperatureObserver>
    fun addObserver (o: TemperatureObserver)
    fun removeObserver (o: TemperatureObserver)
}


class Thermometer(var sensor: Sensor): TemperatureSubject {
    override val observers: MutableList<TemperatureObserver> = mutableListOf()

    override fun addObserver(o: TemperatureObserver) {
        observers.add(o)
    }

    override fun removeObserver(o: TemperatureObserver) {
        observers.remove(o)
    }

    fun measure(times: Int){
        repeat(times){
            val temperature = sensor.getTemperature()
            //println(temperature)
            for (each in observers){
                each.update(temperature)
            }
        }
    }
}