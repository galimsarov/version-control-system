enum class Commands(val command: String, val description: String) {
    CONFIG("config", "Get and set a username."),
    ADD("add", "Add a file to the index."),
    LOG("log", "Show commit logs."),
    COMMIT("commit", "Save changes."),
    CHECKOUT("checkout", "Restore a file.")
}