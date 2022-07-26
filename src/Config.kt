fun config(args: Array<String>) {
    checkDir("vcs")
    val configTxt = checkFile("vcs//config.txt")
    if (args.size > 1) {
        configTxt.writeText(args[1])
        println("The username is ${configTxt.readText()}.")
    } else {
        val fileUserName = configTxt.readText()
        if (fileUserName.isBlank()) {
            println("Please, tell me who you are.")
        } else {
            println("The username is ${fileUserName}.")
        }
    }
}