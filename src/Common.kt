import java.io.File

fun checkDir(dirName: String): File {
    val dir = File(dirName)
    if (!dir.exists()) {
        dir.mkdir()
    }
    return dir
}

fun checkFile(fileName: String): File {
    val file = File(fileName)
    if (!file.exists()) {
        file.createNewFile()
    }
    return file
}