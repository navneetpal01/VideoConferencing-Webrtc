package com.example.video_conferencing.remote.socket




data class MessageModel(
    val type : SocketEvents,
    val name : String? = null,
    val target : String? = null,
    val data : Any? = null
)