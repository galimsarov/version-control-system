const val TAB_SIZE = 12

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        printHelp()
    } else {
        if (args[0] == "--help") {
            printHelp()
        } else {
            when (args[0]) {
                Commands.CONFIG.command -> config(args)
                Commands.ADD.command -> add(args)
                Commands.LOG.command -> log()
                Commands.COMMIT.command -> commit(args)
                Commands.CHECKOUT.command -> checkout(args)
                else -> println("'${args[0]}' is not a SVCS command.")
            }
        }
    }
}

fun printHelp() {
    println("These are SVCS commands:")
    for (command in Commands.values()) {
        println("${command.command}${" ".repeat(TAB_SIZE - command.command.length)}${command.description}")
    }
}