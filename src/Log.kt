fun log() {
    val logFile = checkFile("vcs//log.txt")
    val lines = logFile.readLines()
    if (lines.isEmpty()) {
        println("No commits yet.")
    } else {
        for (line in lines) {
            println(line)
        }
    }
}