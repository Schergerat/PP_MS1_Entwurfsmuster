import java.math.RoundingMode
import kotlin.random.Random

interface Sensor{
    fun getTemperature(): Double
}



class RandomSensor(val min: Double, val max: Double) : Sensor{
    override fun getTemperature(): Double = Random.nextDouble(min, max)
}

class ConstantSensor(val temp: Double): Sensor{
    override fun getTemperature(): Double = temp
}

class IncreasingSensor(val startTemp: Double): Sensor{
    private var count = 0
    override fun getTemperature(): Double {
        count++
        return startTemp + 0.5 * count
    }
}

class SensorLogger(private val sensor: Sensor): Sensor{
    override fun getTemperature(): Double {
        val temperature = sensor.getTemperature()
        println(temperature)
        return temperature
    }
}

class RoundValues(private val sensor: Sensor): Sensor{
    override fun getTemperature(): Double = sensor.getTemperature().toBigDecimal().setScale(0, RoundingMode.HALF_EVEN).toDouble()
}

class FahrenheitSensor(private val sensor: Sensor): Sensor{
    override fun getTemperature(): Double = sensor.getTemperature() * 9/5 + 32
}
