package t.masahide

object Kontainer {

    class Container(val provider : ()->Any){
        val obj : Any by lazy {
            provider.invoke()
        }

    }
    private val container = mutableMapOf<String,Container>()

    fun put(name:String,any: ()->Any){
        if(container.contains(name)){
            throw Exception("$name already exists")
        }
        container.put(name, Container { any })
    }


    @Suppress("UNCHECKED_CAST")
    fun <T> get( name:String ): T {

        val containerObj = container[name] ?: throw Exception("you must pu $name object before call get")

        return containerObj.obj as T

    }

    @Suppress("UNCHECKED_CAST")
    fun <T> remakeInstance(name : String) : T{
        val provider = container[name]?.provider ?: throw Exception("you must put $name object before call remakeInstance")
        remove(name)
        put(name,provider)
        return get(name)
    }


    fun remove(name:String){
        container.remove(name)
    }

    fun clear(){
        container.clear()
    }

}
