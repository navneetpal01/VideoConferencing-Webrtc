package com.example.video_conferencing.remote.socket



interface SocketEventListener{
    fun onNewMessage(message : MessageModel)
    fun onSocketOpened()
    fun onSocketClosed()
}