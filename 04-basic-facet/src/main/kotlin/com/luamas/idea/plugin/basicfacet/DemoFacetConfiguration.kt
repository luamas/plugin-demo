package com.luamas.idea.plugin.basicfacet

import com.intellij.facet.FacetConfiguration
import com.intellij.facet.ui.FacetEditorContext
import com.intellij.facet.ui.FacetEditorTab
import com.intellij.facet.ui.FacetValidatorsManager
import com.intellij.openapi.components.PersistentStateComponent


/**
 * 提供com.luamas.idea.plugin.basicfacet.DemoFacet的配置类的自定义实现。
 *
 * @author Anna Bulenkova
 * @author John Hake
 */
class DemoFacetConfiguration : FacetConfiguration, PersistentStateComponent<DemoFacetState?> {
    // 管理facet存储的数据。
    private var myFacetState = DemoFacetState()

    /**
     * 持久化facet状态时由IntelliJ平台调用。
     * @return 组件状态。所有属性、公共字段和带注释的字段都被序列化。
     * 只有与默认值（即新实例化类的值）不同的值才会被序列化。空值表
     * 示不存储返回的状态，因此将使用以前存储的状态。
     */
    override fun getState(): DemoFacetState? {
        return myFacetState
    }

    /**
     * 当加载facet的状态时由IntelliJ平台调用。如果在IDEA运行时外部更改了配置文件，则可以多次调用该方法。
     */
    override fun loadState(state: DemoFacetState) {
        myFacetState = state
    }

    /**
     * 为facet创建一组编辑器选项卡，可能每个上下文一个。
     *
     * @param context 在其中添加/删除或修改方面的上下文。
     * @param manager 可用于访问自定义验证器管理器。
     * @return DemoFacetEditorTabs数组。在这种情况下，大小总是1。
     */
    override fun createEditorTabs(
        context: FacetEditorContext,
        manager: FacetValidatorsManager
    ): Array<FacetEditorTab> {
        return arrayOf(
            DemoFacetEditorTab(myFacetState, context, manager)
        )
    }
}
