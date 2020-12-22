package ch8

import scala.io.Source

object FindLongLines extends App {
  LongLines.processFile("src/main/scala/ch8/LongLines.scala", 60)
}

object LongLines {
  def processFile(filename: String, width: Int): Unit = {
    val source = Source.fromFile(filename)
    for (line <- source.getLines())
      processLine(filename, width, line)

    def processLine(filename: String, width: Int, line: String): Unit = {
      if (line.length > width)
        println(filename + ": " + line.trim)
    }
  }
}
