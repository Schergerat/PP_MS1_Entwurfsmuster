

fun main(args: Array<String>) {

    // 1 Strategien für Temperaturwerte
    println("Aufgabe 1")

    val randomSensor = RandomSensor ( min = 2.0 , max = 8.0) // liefert zufällige Temperaturen zwischen 2.0 und 8.0 Grad
    println ("Random Sensor:")
    repeat (4) {
        println ( randomSensor.getTemperature () )
    }

    val constantSensor = ConstantSensor ( temp = 21.5) // liefert jedes Mal 21.5 Grad
    println ("Constant Sensor:")
    repeat (4) {
        println ( constantSensor.getTemperature () )
    }

    val increasingSensor = IncreasingSensor ( startTemp = 15.0) // fängt bei 15 Grad an und erhöht jedes mal die Temperatur um 0.5 Grad
    println ("Increasing Sensor:")
    repeat (4) {
        println ( increasingSensor.getTemperature () )
    }

    println("--------------------------------------------------------")

    // 2 Strategien verwenden
    println("Aufgabe 2")

    println("Thermometer mit erster Strategie:")
    val thermometer = Thermometer ( sensor = RandomSensor (2.0 , 8.0) ) // Thermometer mit erster Strategie initialisieren
    thermometer.measure (10)

    println("Thermometer mit gewechselter Strategie:")
    thermometer.sensor = IncreasingSensor ( startTemp = 15.0) // Strategie wechseln
    thermometer.measure (10)

    //d) Der Sensor kann dynamisch zur Laufzeit ausgetauscht werden.
    //e) 1. Program to an Interface, 2. Composition over Inheritance, 3. Single Responsibility Principle

    println("--------------------------------------------------------")

    // 3 Sensor dekorieren
    println("Aufgabe 3")

    println("RandomRoundSensor")
    val randomRoundSensor = SensorLogger(RoundValues(RandomSensor(2.0, 5.0))).getTemperature()

    println("LinearSensor")
    val linearSensor = SensorLogger(RoundValues(FahrenheitSensor(IncreasingSensor(20.0)))).getTemperature()

    println("CelsiusLinearSensor")
    val celsiusLinearSensor = SensorLogger(RoundValues(FahrenheitSensor(SensorLogger(IncreasingSensor(20.0))))).getTemperature()

    println("Thermometer 1")
    val t1 = Thermometer(SensorLogger(RoundValues(RandomSensor(2.0, 5.0)))).measure(2)
    println("Thermometer 2")
    val t2 = Thermometer(RoundValues(SensorLogger(RandomSensor(2.0, 5.0)))).measure(2)
    //Die Kompilierung erfolgt von Innen nach Außen

    //d) Wäre auch mit Unterklassen gegangen, aber jede Variation müsste eine eigene Klasse haben.
    //e) Ein Dekorierer braucht eine Strategie um aufgerufen zu werden und er erweitert diese, stellt aber eigenständig keine da.
    //f) 1. Program to an Interface, 2. Open-Closed Principle


    println("--------------------------------------------------------")

    // 4 Beobachten des Thermometers
    println("Aufgabe 4")

    val sensor = SensorLogger(RoundValues(RandomSensor (10.0 , 50.0) ))
    val thermometer2 = Thermometer (sensor)
    val alertObserver = TemperatureAlert (alertTmp = 30.0, alertMsg = " Ganz schön heiß")
    val heatingSystemObserver = HeatingSystemObserver (offThreshold = 23.0, onThreshold = 19.0)
    thermometer2.addObserver ( alertObserver )
    thermometer2.addObserver ( heatingSystemObserver )
    thermometer2.measure (20)

        //d) Ein Push, statt durchgehende pulls; Beobachter können mit bestimmten Parametern auf pushes reagieren;
        //   Beobachter können selbst entscheiden, worauf sie reagieren; Alternativ müssten Dekorierer umgesetzt werden
        //e) 1. Program to an Interface, 2. Open-Closed Principle, 3. Composition over Inheritance

}