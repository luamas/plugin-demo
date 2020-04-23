package com.luamas.idea.plugin.psi

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.ui.Messages
import com.intellij.psi.JavaRecursiveElementVisitor
import com.intellij.psi.PsiLocalVariable
import com.intellij.psi.PsiMethod
import com.intellij.psi.util.PsiTreeUtil


class PsiNavigationDemoAction : AnAction() {
    override fun actionPerformed(anActionEvent: AnActionEvent) {
        val editor = anActionEvent.getData(CommonDataKeys.EDITOR)
        val psiFile = anActionEvent.getData(CommonDataKeys.PSI_FILE)
        if (editor == null || psiFile == null) return
        val offset = editor.caretModel.offset
        val infoBuilder = StringBuilder()
        val element = psiFile.findElementAt(offset)
        infoBuilder.append("插入符号处的元素: ").append(element).append("\n")
        if (element != null) {
            val containingMethod = PsiTreeUtil.getParentOfType(element, PsiMethod::class.java)
            infoBuilder.append("包含方法: ").append(containingMethod?.name ?: "无").append("\n")
            if (containingMethod != null) {
                val containingClass = containingMethod.containingClass
                infoBuilder.append("包含类: ").append(if (containingClass != null) containingClass.name else "无").append("\n")
                infoBuilder.append("局部变量:\n")
                containingMethod.accept(object : JavaRecursiveElementVisitor() {
                    override fun visitLocalVariable(variable: PsiLocalVariable) {
                        super.visitLocalVariable(variable)
                        infoBuilder.append(variable.name).append("\n")
                    }
                })
            }
        }
        Messages.showMessageDialog(anActionEvent.project, infoBuilder.toString(), "PSI信息", null)
    }

    override fun update(e: AnActionEvent) {
        val editor = e.getData(CommonDataKeys.EDITOR)
        val psiFile = e.getData(CommonDataKeys.PSI_FILE)
        e.presentation.isEnabled = editor != null && psiFile != null
    }
}
