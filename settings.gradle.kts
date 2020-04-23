rootProject.name = "plugin-demo"
file(".")?.listFiles()?.filter {
    val arr = it.name.split("-")
    if (arr.size>1){
        try {
            arr[0].toInt()
            true
        }catch (_: NumberFormatException){
            false
        }
    } else false

}?.filter { it.isDirectory }?.forEach {
    val projectName = it.name.substring(it.name.split("-")[0].length+1)
//    println(projectName)
    include(projectName)
    project(":$projectName").projectDir = it
}