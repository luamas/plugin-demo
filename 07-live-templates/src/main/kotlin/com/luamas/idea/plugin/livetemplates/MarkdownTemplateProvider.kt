package com.luamas.idea.plugin.livetemplates

import com.intellij.codeInsight.template.impl.DefaultLiveTemplatesProvider

class MarkdownTemplateProvider : DefaultLiveTemplatesProvider {
    override fun getDefaultLiveTemplateFiles(): Array<String> {
        return arrayOf("liveTemplates/Markdown")
    }

    override fun getHiddenLiveTemplateFiles(): Array<String>? {
        return null
    }
}