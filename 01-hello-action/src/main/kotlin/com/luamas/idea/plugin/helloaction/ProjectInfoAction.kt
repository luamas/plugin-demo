package com.luamas.idea.plugin.helloaction

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.LangDataKeys
import com.intellij.openapi.ui.Messages
import org.apache.http.util.TextUtils


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