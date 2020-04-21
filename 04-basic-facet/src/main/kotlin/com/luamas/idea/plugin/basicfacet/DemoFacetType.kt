package com.luamas.idea.plugin.basicfacet

import com.intellij.facet.Facet
import com.intellij.facet.FacetType
import com.intellij.facet.FacetTypeId
import com.intellij.openapi.module.Module
import com.intellij.openapi.module.ModuleType
import icons.SdkIcons
import javax.swing.Icon


/**
 * 定义com.luamas.idea.plugin.basicfacet.DemoFacet的类型、id和名称。
 * 提供com.luamas.idea.plugin.basicfacet.DemoFacet和相关配置的创建。
 * 允许将此facet应用于所有模块类型。
 *
 * @author Anna Bulenkova
 */
class DemoFacetType : FacetType<DemoFacet, DemoFacetConfiguration>(
    DEMO_FACET_TYPE_ID,
    FACET_ID,
    FACET_NAME
) {
    override fun createDefaultConfiguration(): DemoFacetConfiguration {
        return DemoFacetConfiguration()
    }

    override fun createFacet(
        module: Module,
        s: String,
        configuration: DemoFacetConfiguration,
        facet: Facet<*>?
    ): DemoFacet? {
        return facet?.let { DemoFacet(this, module, s, configuration, it) }
    }

    override fun isSuitableModuleType(type: ModuleType<*>?): Boolean {
        return true
    }

    override fun getIcon(): Icon? {
        return SdkIcons.Sdk_default_icon
    }

    companion object {
        const val FACET_ID = "DEMO_FACET_ID"
        const val FACET_NAME = "SDK Facet"
        val DEMO_FACET_TYPE_ID = FacetTypeId<DemoFacet>(FACET_ID)
    }
}
