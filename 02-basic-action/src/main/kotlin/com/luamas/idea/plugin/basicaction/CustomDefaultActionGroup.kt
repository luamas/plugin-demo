package com.luamas.idea.plugin.basicaction

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.DefaultActionGroup
import icons.SdkIcons


/**
 * 创建包含菜单action的action组。请参见plugin.xml声明。
 * @author Anna Bulenkova
 * @author jhake
 */
class CustomDefaultActionGroup : DefaultActionGroup() {
    /**
     * 由于CustomDefaultActionGroup是从ActionGroup派生的，因此在此上下文中update()确定是否应启用或禁用action组本身。
     * 需要激活编辑器才能启用组功能。
     * @see com.intellij.openapi.actionSystem.AnAction.update
     * @param event  选择关联的组id菜单时接收的事件。
     */
    override fun update(event: AnActionEvent) {
        // 启用/禁用取决于用户是否正在编辑
        val editor = event.getData(CommonDataKeys.EDITOR)
        event.presentation.isEnabled = editor != null
        // 借此机会设置菜单项的图标。
        event.presentation.icon = SdkIcons.Sdk_default_icon
    }
}
