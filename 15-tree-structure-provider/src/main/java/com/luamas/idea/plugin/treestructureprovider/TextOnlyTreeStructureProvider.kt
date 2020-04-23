package com.luamas.idea.plugin.treestructureprovider

import com.intellij.ide.projectView.TreeStructureProvider
import com.intellij.ide.projectView.ViewSettings
import com.intellij.ide.projectView.impl.nodes.PsiFileNode
import com.intellij.ide.util.treeView.AbstractTreeNode
import com.intellij.openapi.fileTypes.PlainTextFileType
import java.util.*


class TextOnlyTreeStructureProvider : TreeStructureProvider {
    /**
     * 允许插件修改项目视图中为指定节点显示的子项列表。
     * 此处只保留文本格式文件
     */
    override fun modify(
        parent: AbstractTreeNode<*>,
        children: Collection<AbstractTreeNode<*>>,
        settings: ViewSettings?
    ): Collection<AbstractTreeNode<*>> {
        val nodes = ArrayList<AbstractTreeNode<*>>()
        for (child in children) {
            // 判断是否为文件节点
            if (child is PsiFileNode) {
                val file = child.virtualFile
                if (file != null && !file.isDirectory && file.fileType !is PlainTextFileType) {
                    continue
                }
            }
            nodes.add(child)
        }
        return nodes
    }

    override fun getData(
        selected: Collection<AbstractTreeNode<*>?>,
        dataId: String
    ): Any? {
        return null
    }
}
