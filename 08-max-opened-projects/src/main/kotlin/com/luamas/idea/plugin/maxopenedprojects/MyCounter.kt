package com.luamas.idea.plugin.maxopenedprojects


class MyCounter(
    /**
     * 当前数量
     */
    private var count: Int = 0,
    /**
     * 设置允许的最大打开项目数。
     */
    val maxCount: Int = 3
    ) {
    fun increaseCounter(): Int {
        count++
        return if (count > maxCount) -1 else count
    }

    fun decreaseCounter(): Int {
        count--
        return if (count > maxCount) -1 else count
    }
}
