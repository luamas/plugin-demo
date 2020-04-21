package com.luamas.idea.plugin.basicfacet

import com.intellij.facet.ui.FacetEditorContext
import com.intellij.facet.ui.FacetEditorTab
import com.intellij.facet.ui.FacetValidatorsManager
import com.intellij.openapi.options.ConfigurationException
import com.intellij.openapi.util.Comparing
import org.jetbrains.annotations.Nls
import java.awt.BorderLayout
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField


/**
 * 提供要在facet UI中显示的JPanel。
 * 管理com.luamas.idea.plugin.basicfacet.DemoFacet状态的验证和修改。
 *
 * @author John Hake
 */
class DemoFacetEditorTab(
    /**
     * 只捕获com.luamas.idea.plugin.basicfacet.DemoFacetState，以便在EditorTab中根据用户的更改对其进行更新。
     */
    /**
     * org.intellij.sdk.facet.DemoFacetState对象持久化 org.intellij.sdk.facet.DemoFacet状态。
     */
    private val mySettings: DemoFacetState,
    /**
     * Facet编辑器上下文，可用于获取当前项目模块。
     */
    context: FacetEditorContext,
    /**
     * Facet验证管理器，可用于获取和应用自定义验证器
     */
    validator: FacetValidatorsManager
) :
    FacetEditorTab() {
    private val myPath: JTextField = JTextField(mySettings.demoFacetState)

    /**
     * 提供在首选项Facet UI中显示的JPanel
     *
     * @return JPanel将显示在com.luamas.idea.plugin.basicfacet.DemoFacetEditorTab中。
     */
    override fun createComponent(): JComponent {
        val top = JPanel(BorderLayout())
        top.add(JLabel(FACET_PANEL_PROMPT), BorderLayout.WEST)
        top.add(myPath)
        val facetPanel = JPanel(BorderLayout())
        facetPanel.add(top, BorderLayout.NORTH)
        return facetPanel
    }

    /**
     * @return 要在此编辑器选项卡中显示的此facet的名称。
     */
    @Nls(capitalization = Nls.Capitalization.Title)
    override fun getDisplayName(): String {
        return DemoFacetType.FACET_NAME
    }

    /**
     * 确定在UI中输入的facet状态是否与当前存储的状态不同。当用户更改myPath中的文本时调用。
     *
     * @return 如果从面板的UI返回的状态与存储的facet状态不同，则为`true`
     */
    override fun isModified(): Boolean {
        return !Comparing.equal(mySettings.demoFacetState, myPath.text.trim { it <= ' ' })
    }

    /**
     * 当[.isModified]返回为true时存储新的facet状态（文本）.
     * @throws ConfigurationException 如果发生异常则会抛出[ConfigurationException]
     */
    @Throws(ConfigurationException::class)
    override fun apply() {
        try {
            val newTextContent = myPath.text
            mySettings.demoFacetState = newTextContent
        } catch (e: Exception) {
            throw ConfigurationException(e.toString())
        }
    }

    /**
     * 将当前com.luamas.idea.plugin.basicfacet.DemoFacetState复制到myPath UI元素中。每次需要显示此编辑器选项卡时都会调用此方法。
     */
    override fun reset() {
        myPath.text = mySettings.demoFacetState
    }

    companion object {
        private const val FACET_PANEL_PROMPT = "SDK路径: "
    }

}
