package com.luamas.idea.plugin.basicinspection

import com.intellij.codeInspection.LocalInspectionTool
import com.intellij.codeInspection.ProblemsHolder


/**
 * @author Anna Bulenkova
 */
class DemoCodeInspection : LocalInspectionTool() {
    /**
     * 此方法被重写以提供自定义访问者访问者访问者不能是递归的，并且必须是线程安全的。
     *
     * @param holder     对象，以便访问者注册发现的问题。
     * @param isOnTheFly 如果在非批处理模式下运行检查，则为true
     * @return DemoInspectionVisitor.
     */
    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): DemoInspectionVisitor {
        return DemoInspectionVisitor()
    }
}

