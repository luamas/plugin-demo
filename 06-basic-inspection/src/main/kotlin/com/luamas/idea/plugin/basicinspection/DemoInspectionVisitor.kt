package com.luamas.idea.plugin.basicinspection

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiElementVisitor
import com.intellij.psi.PsiPlainTextFile


/**
 * @author Anna Bulenkova
 */
class DemoInspectionVisitor : PsiElementVisitor() {
    override fun visitElement(element: PsiElement) {
        super.visitElement(element)
    }

    override fun visitPlainTextFile(file: PsiPlainTextFile) {
        super.visitPlainTextFile(file)
    }
}
