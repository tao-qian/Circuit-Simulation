package circuitsimulation.circuit

import circuitsimulation.circuit.elements.CommonGates
import circuitsimulation.analyzer._

object Demo {
  def main(args: Array[String]) {
    
    val circuit = new Circuit() with CommonGates
    import circuit._
    
    val in1 = new Wire("InputOne",true)
    val in2 = new Wire("Output")
    
    probe(in1)
    probe(in2)
    inverter(in1,in2)
    
    //circuit.start
    
    val analyzer = new SingleRunAnalyzer(circuit)
    analyzer.startSimulation
    
  }
}

