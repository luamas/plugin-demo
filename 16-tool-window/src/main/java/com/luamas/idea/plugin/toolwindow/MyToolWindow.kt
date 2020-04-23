package com.luamas.idea.plugin.toolwindow

import com.intellij.openapi.wm.ToolWindow
import java.util.*
import javax.swing.ImageIcon
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel


class MyToolWindow(toolWindow: ToolWindow) {
    private var refreshToolWindowButton: JButton? = null
    private var hideToolWindowButton: JButton? = null
    private var currentDate: JLabel? = null
    private var currentTime: JLabel? = null
    var timeZone: JLabel? = null
    var myToolWindowContent: JPanel? = null

    fun currentDateTime() {
        // 获取当前日期和时间
        val instance = Calendar.getInstance()
        currentDate!!.text = (instance[Calendar.YEAR]).toString() + "/" + (instance[Calendar.MONTH] + 1) + "/" + instance[Calendar.DAY_OF_MONTH]
        currentDate!!.icon = ImageIcon(javaClass.getResource("/toolWindow/Calendar-icon.png"))
        val min = instance[Calendar.MINUTE]
        val strMin = if (min < 10) "0$min" else min.toString()
        currentTime!!.text = instance[Calendar.HOUR_OF_DAY].toString() + ":" + strMin
        currentTime!!.icon = ImageIcon(javaClass.getResource("/toolWindow/Time-icon.png"))
        // 获取时区
        val gmtOffset = instance[Calendar.ZONE_OFFSET].toLong() // GMT的偏移量(毫秒)
        var strGmtOffset = (gmtOffset / 3600000).toString()
        strGmtOffset = if (gmtOffset > 0) "GMT + $strGmtOffset" else "GMT - $strGmtOffset"
        timeZone!!.text = strGmtOffset
        timeZone!!.icon = ImageIcon(javaClass.getResource("/toolWindow/Time-zone-icon.png"))
    }

    init {
        hideToolWindowButton!!.addActionListener { toolWindow.hide(null) }
        refreshToolWindowButton!!.addActionListener { currentDateTime() }
        currentDateTime()
    }
}
