package com.luamas.idea.plugin.basicfacet

import com.intellij.facet.Facet
import com.intellij.facet.FacetType
import com.intellij.openapi.module.Module


/**
 * Facet示例类. 所有都由超类处理
 * @author Anna Bulenkova
 */
class DemoFacet(
    facetType: FacetType<*, *>,
    module: Module,
    name: String,
    configuration: DemoFacetConfiguration,
    underlyingFacet: Facet<*>
) :
    Facet<DemoFacetConfiguration?>(facetType, module, name, configuration, underlyingFacet)
