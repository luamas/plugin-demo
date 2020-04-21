rootProject.name = "plugin-demo"
include("hello-action")
project(":hello-action").projectDir = file("01-hello-action")
include("editor-action")
project(":editor-action").projectDir = file("02-editor-action")