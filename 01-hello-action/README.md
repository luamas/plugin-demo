# Action基础

## HelloAction 简单示例

```kotlin
class HelloAction : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        // 获取project对象
        val project = event.project
        // 展示一个提示信息
        Messages.showMessageDialog(
            project,
            "第一个自定义插件",
            "欢迎标题",
            Messages.getInformationIcon()
        )
    }
}
```

```xml
<group id="Luamas.HelloActionMenu" text="自定义插件">
            将action增加到主菜单
            <add-to-group group-id="MainMenu" anchor="last"/>
<!--            这里如果类级别覆盖了text字段会优先使用类内部的text内容-->
            <action id="Luamas.HelloActionMenu.Hello" class="com.luamas.idea.plugin.helloaction.HelloAction" text="欢迎" icon="SdkIcons.Sdk_default_icon"/>
        </group>
```


## ProjectInfoAction 获取项目名称以及编辑器内容

```kotlin
class ProjectInfoAction : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        // 获取project对象
        val project = event.project
        // 返回当前编辑器实例。
        val mEditor = event.getData(LangDataKeys.EDITOR)
        // 返回编辑器的选择模型，该模型可用于获取被选择内容的信息。
        // 要查询或更改特定插入符号的选择，应使用插入符号模型接口-CaretModel。
        val model = mEditor?.selectionModel
        // 返回编辑器中选定的文本。
        val selectedText = model?.selectedText?: ""
        Messages.showMessageDialog(
            project,
            "当前项目名称：${project?.name}\n" +
                    "当前被选择的内容是：\n${selectedText}",
            "项目信息",
            Messages.getInformationIcon()
        )
    }
}
```

```xml
<group id="Luamas.HelloActionMenu" text="自定义插件">
            将action增加到主菜单
            <add-to-group group-id="MainMenu" anchor="last"/>
<!--            这里如果类级别覆盖了text字段会优先使用类内部的text内容-->
            <action id="Luamas.HelloActionMenu.ProjectInfo" class="com.luamas.idea.plugin.helloaction.ProjectInfoAction" text="项目信息" icon="SdkIcons.Sdk_default_icon"/>
        </group>
```