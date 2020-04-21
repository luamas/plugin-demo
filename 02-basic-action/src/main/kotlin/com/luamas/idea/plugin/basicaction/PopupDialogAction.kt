package com.luamas.idea.plugin.basicaction

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.ui.Messages
import javax.swing.Icon


/**
 * Action类演示如何与IntelliJ平台交互。这个类执行的唯一action是向用户提供一个弹出对话框作为反馈。
 * 通常，这个类是由IntelliJ平台框架基于plugin.xml文件中的声明实例化的。但是当在运行时添加时，
 * 这个类由一个action组实例化。
 */
class PopupDialogAction : AnAction {
    /**
     * IntelliJ平台框架使用此默认构造函数根据plugin.xml声明实例化此类。
     * 仅在PopupDialogAction类中需要，因为重写了第二个构造函数。
     * @see AnAction.AnAction()
     */
    constructor() : super() {}

    /**
     * 此构造函数用于支持动态添加的菜单action。它设置要为菜单项显示的文本、说明。
     * 否则，IntelliJ平台将使用默认的AnAction构造函数。
     * @param text  菜单项显示的文本。
     * @param description  菜单项的说明。
     * @param icon  与菜单项一起使用的图标。
     */
    constructor(text: String?, description: String?, icon: Icon?) : super(text, description, icon) {}

    /**
     * 选择动态action菜单时向用户提供反馈。弹出一个简单的消息对话框。
     * 有关如何使用AnActionEvent访问数据的示例，请参阅psi_demo示例。
     * @param event 选择关联菜单项时接收的事件。
     */
    override fun actionPerformed(event: AnActionEvent) {
        // 使用事件，创建并显示对话框
        val currentProject = event.project
        val dlgMsg = StringBuffer(event.presentation.text + "被选择!")
        val dlgTitle = event.presentation.description
        // 如果在编辑器中选择了某个元素，请添加有关该元素的信息。
        val nav = event.getData(CommonDataKeys.NAVIGATABLE)
        if (nav != null) {
            dlgMsg.append(String.format("\n选定元素: %s ", nav.toString()))
        }
        Messages.showMessageDialog(
            currentProject,
            dlgMsg.toString(),
            dlgTitle,
            Messages.getInformationIcon()
        )
    }

    /**
     * 确定此菜单项是否可用于当前上下文。需要打开项目。
     * @param e 选择关联的组id菜单时接收的事件。
     */
    override fun update(e: AnActionEvent) {
        // 根据项目是否被打开来设置可用性
        val project = e.project
        e.presentation.isEnabledAndVisible = project != null
    }
}
