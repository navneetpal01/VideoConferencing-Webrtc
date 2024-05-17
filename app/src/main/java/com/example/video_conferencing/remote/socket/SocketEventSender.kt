package com.example.video_conferencing.remote.socket

import javax.inject.Inject


class SocketEventSender @Inject constructor(
    private val socketClient : SocketClient
){

}