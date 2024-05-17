package com.example.video_conferencing.remote.socket

import com.example.video_conferencing.VideoConferencingApplication
import javax.inject.Inject


class SocketEventSender @Inject constructor(
    private val socketClient: SocketClient
) {

    private var username = VideoConferencingApplication.username

    fun storeUser() {
        socketClient.sendMessageToSocket(
            MessageModel(type = SocketEvents.StoreUser, name = username)
        )
    }

    fun createRoom(roomName: String) {
        socketClient.sendMessageToSocket(
            MessageModel(type = SocketEvents.CreateRoom, data = roomName, name = username)
        )
    }

    fun joinRoom(roomName: String) {
        socketClient.sendMessageToSocket(
            MessageModel(type = SocketEvents.LeaveAllRooms, name = username)
        )
    }

    fun startCall(target: String) {
        socketClient.sendMessageToSocket(
            MessageModel(type = SocketEvents.StartCall, name = username, target = target)
        )
    }
}