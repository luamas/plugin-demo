rootProject.name = "plugin-demo"
include("hello-action")
project(":hello-action").projectDir = file("01-hello-action")
include("basic-action")
project(":basic-action").projectDir = file("02-basic-action")
include("editor-action")
project(":editor-action").projectDir = file("03-editor-action")