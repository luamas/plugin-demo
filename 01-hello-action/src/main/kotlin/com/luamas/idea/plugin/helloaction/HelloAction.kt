package com.luamas.idea.plugin.helloaction

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.Messages

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