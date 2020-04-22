package com.luamas.idea.plugin.livetemplates

import com.intellij.codeInsight.template.TemplateContextType
import com.intellij.psi.PsiFile

open class MarkdownContext protected constructor() : TemplateContextType("MARKDOWN", "Markdown") {
    override fun isInContext(file: PsiFile, offset: Int): Boolean {
        // 忽略掉后缀大小写
        return file.name.endsWith(".md",true)
    }
}