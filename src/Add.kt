import java.io.File

fun add(args: Array<String>) {
    checkDir("vcs")
    val indexTxt = checkFile("vcs//index.txt")
    if (args.size > 1) {
        val file = File(args[1])
        if (file.exists()) {
            indexTxt.appendText("${file.name}\n")
            println("The file '${file.name}' is tracked.")
        } else {
            println("Can't find '${file.name}'.")
        }
    } else {
        val trackedFiles = indexTxt.readLines()
        if (trackedFiles.isEmpty()) {
            println("Add a file to the index.")
        } else {
            println("Tracked files:")
            for (line in trackedFiles) {
                println(line)
            }
        }
    }
}