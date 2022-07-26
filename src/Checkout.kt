import java.io.File

fun checkout(args: Array<String>) {
    if (args.size > 1) {
        val commitsDir = checkDir("vcs//commits")
        val dirs = commitsDir.list()
        for (dir in dirs!!) {
            if (args[1] == dir) {
                val dirFrom = checkDir("vcs//commits//$dir")
                for (fileName in dirFrom.list()!!) {
                    val fileFrom = File("vcs//commits//$dir//$fileName")
                    val fileTo = File(fileName)
                    fileFrom.copyTo(fileTo, true)
                }
                println("Switched to commit $dir.")
                return
            }
        }
        println("Commit does not exist.")
    } else {
        println("Commit id was not passed.")
    }
}