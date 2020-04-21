plugins {
    val intellijVersion = "0.4.18"
    id("org.jetbrains.intellij") version intellijVersion
}
intellij {
    version = "2019.3.4"
    sameSinceUntilBuild = true
}
tasks.getByName<org.jetbrains.intellij.tasks.PatchPluginXmlTask>("patchPluginXml") {
    changeNotes("""
      <h1>Basic Facet</h1>
      <em>Init</em>""")
    pluginDescription("""Basic Facet Demo""")
}