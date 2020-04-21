package com.luamas.idea.plugin.basicaction

import com.intellij.openapi.actionSystem.ActionGroup
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import icons.SdkIcons


/**
 * 演示如何在plugin.xml中静态地向菜单添加action组，然后在运行时在该组中创建菜单项。
 * 有关DynamicActionGroup的声明，请参见plugin.xml，并注意组声明不包含action。
 * DynamicActionGroup基于ActionGroup，因为菜单子项是根据规则而不仅仅是位置约束来确定的。
 *
 * @author Anna Bulenkova
 * @see ActionGroup
 */
class DynamicActionGroup : ActionGroup() {
    /**
     * 返回组的菜单action数组。
     *
     * @param  e 选择关联的组id菜单时接收的事件。
     * @return AnAction[] 一个AnAction实例，在本例中包含PopupDialogAction类的单个实例。
     */
    override fun getChildren(e: AnActionEvent?): Array<AnAction> {
        return arrayOf(
            PopupDialogAction(
                "运行时添加Action",
                "动态Action示例",
                SdkIcons.Sdk_default_icon
            )
        )
    }
}
