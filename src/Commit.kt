import java.io.File
import java.security.MessageDigest

fun commit(args: Array<String>) {
    if (args.size > 1) {
        val commitName = getCommitName()
        if (logHasThisCommit(commitName)) {
            println("Nothing to commit.")
        } else {
            checkDir("vcs//commits")
            checkDir("vcs//commits//$commitName")
            copyFilesToCommitDir(commitName)
            writeLogFile(commitName, args)
            println("Changes are committed.")
        }
    } else {
        println("Message was not passed.")
    }
}

fun logHasThisCommit(commitName: String): Boolean {
    val logFile = checkFile("vcs//log.txt")
    val lines = logFile.readLines()
    return if (lines.isNotEmpty()) {
        logFile.readLines()[0].contains(commitName)
    } else {
        false
    }
}

private fun getTrackedFiles() = File("vcs//index.txt").readLines().map { File(it) }

private fun getCommitName(): String {
    var commitInfo = ""
    for (file in getTrackedFiles()) {
        commitInfo += file.readText()
    }
    return hashString(commitInfo)
}

private fun hashString(input: String) = MessageDigest
    .getInstance("SHA-256")
    .digest(input.toByteArray())
    .fold("") { str, it -> str + "%02x".format(it) }

private fun copyFilesToCommitDir(commitDirName: String) {
    for (fileIn in getTrackedFiles()) {
        val fileOutName = "vcs//commits//$commitDirName//${fileIn.name}"
        val fileOut = File(fileOutName)
        if (!fileOut.exists()) {
            fileIn.copyTo(fileOut)
        }
    }
}

private fun writeLogFile(commitName: String, args: Array<String>) {
    val logFile = checkFile("vcs//log.txt")
    val configFile = File("vcs//config.txt")
    val message = args[1]
    val textFromFile = logFile.readText()
    val resultText = "commit $commitName\nAuthor: ${configFile.readText()}\n$message\n\n" + textFromFile
    logFile.writeText(resultText)
}