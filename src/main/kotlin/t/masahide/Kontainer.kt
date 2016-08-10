package t.masahide

private object Kontainer {

    class Container(val provider: () -> Any) {
        val instance: Any by lazy {
            provider.invoke()
        }
    }

    private val container = mutableMapOf<String, Container>()
    fun register(name: String, provider: () -> Any) {
        if (container.contains(name)) {
            throw Exception("$name already exists. call unset")
        }
        container.put(name, Container (provider))
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> getSingleton(name: String): T {
        val containerObj = container[name] ?: throw Exception("you must register $name before call getSingleton")
        return containerObj.instance as T
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> get(name: String): T {
        val containerObj = container[name] ?: throw Exception("you must register $name before call get")
        return containerObj.provider.invoke() as T
    }

    fun unset(name: String) {
        container.remove(name)
    }

    fun clear() {
        container.clear()
    }
}

fun koUnset(name: String) {
    Kontainer.unset(name)
}

fun <T> koInject(name: String): T {
    return Kontainer.get(name)
}

fun <T> koInjectSingleton(name: String): T {
    return Kontainer.getSingleton(name)
}

fun koRegister(name: String, callable: () -> Any) {
    Kontainer.register(name, callable)
}

fun koClear() {
    Kontainer.clear()
}