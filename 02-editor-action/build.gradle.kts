plugins {
    val intellijVersion = "0.4.18"
    id("org.jetbrains.intellij") version intellijVersion
}
intellij {
    version = "2019.3.4"
}
tasks.getByName<org.jetbrains.intellij.tasks.PatchPluginXmlTask>("patchPluginXml") {
    changeNotes("""
      <h1>Editor Action</h1>
      <em>Init</em>""")
    pluginDescription("""Editor Action Demo""")
}