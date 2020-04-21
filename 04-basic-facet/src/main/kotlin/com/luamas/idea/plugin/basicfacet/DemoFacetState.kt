package com.luamas.idea.plugin.basicfacet


/**
 * 一个简单的类，用于存储com.luamas.idea.plugin.basicfacet.DemoFacet的状态。
 * 在本例中，它只是一个包含SDK路径的字符串。
 *
 * @author John Hake
 */
class DemoFacetState internal constructor() {
    var myPathToSdk: String? = null
    var demoFacetState: String
        get() = myPathToSdk!!
        set(newPath) {
            myPathToSdk = newPath
        }

    companion object {
        const val DEMO_FACET_INIT_PATH = ""
    }

    init {
        demoFacetState = DEMO_FACET_INIT_PATH
    }
}
