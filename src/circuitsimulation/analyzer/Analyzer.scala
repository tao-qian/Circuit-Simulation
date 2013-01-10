package circuitsimulation.analyzer

import circuitsimulation.circuit.ParallelSimulation._
import circuitsimulation.circuit._
import scala.actors.Actor
import Actor._

abstract class Analyzer(circuit:Circuit) extends Actor{
  
  var count:Int = 0
  circuit.setAnalyzer(this)
  start()
  
  protected def reactToFinishedSimulation()
  protected def log(l:Log)
  
  def act()
  {
    loop
    {
      react
      {
        case l:Log =>
          log(l)
        case Finished =>
          //Check back if the previous simulation is not done yet
          if(circuit.getClockState != Actor.State.Terminated)
            self ! Finished
          reactToFinishedSimulation()
      }
    }
  }
  
  def startSimulation()
  {
    circuit.start()
  }
}