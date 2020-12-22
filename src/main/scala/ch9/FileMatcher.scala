package ch9

object FileMatcher {
  import java.io.File
  private def filesHere: Array[File] = (new File(".")).listFiles()

  def filesEnding(query: String): Array[File] =
//    filesHere.filter(_.getName.endsWith(query))
    fileMatching(query, _ endsWith _)

  def filesContaining(query: String): Array[File] =
//    filesHere.filter(_.getName.contains(query))
    fileMatching(query, _ contains _)

  def filesRegex(query: String): Array[File] =
//    filesHere.filter(_.getName.matches(query))
    fileMatching(query, _ matches _)

  def fileMatching(
      query: String,
      matcher: (String, String) => Boolean
  ): Array[File] = {
    filesHere.filter(file => matcher(file.getName, query))
  }
}
