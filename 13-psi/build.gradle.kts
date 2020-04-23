plugins {
    val intellijVersion = "0.4.18"
    id("org.jetbrains.intellij") version intellijVersion
}
intellij {
    version = "2020.1"
    sameSinceUntilBuild = true
    setPlugins("java")
}
tasks.getByName<org.jetbrains.intellij.tasks.PatchPluginXmlTask>("patchPluginXml") {
    changeNotes("""
      <h1>PSI</h1>
      <em>Init</em>""")
    pluginDescription("""PSI Demo""")
}