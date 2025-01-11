package com.modulo10.juandev.mrsunshisjournal.ui.listeners


import com.modulo10.juandev.mrsunshisjournal.data.db.model.NotificationsEntity

interface NewNotificationListener {
    fun onNewNotification(noti: NotificationsEntity)
}