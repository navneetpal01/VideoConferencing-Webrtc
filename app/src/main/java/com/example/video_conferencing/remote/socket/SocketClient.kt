package com.example.video_conferencing.remote.socket

import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.net.URI
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SocketClient @Inject constructor(
    private val gson: Gson
) {


    private var socketEventListener: SocketEventListener? = null
    fun setListener(messageInterface: SocketEventListener) {
        this.socketEventListener = messageInterface
    }

    fun onStop() {
        socketEventListener = null
        //This method likely waits (blocks) until the connection is fully closed before continuing
        //Even though you're not using the specific exception information, runCatching still captures
        // any exceptions that might occur during the webSocket?.closeBlocking() call.
        // This prevents the application from crashing due to unexpected errors related to closing the websocket connection
        runCatching{ webSocket?.closeBlocking() }
    }

    private var webSocket: WebSocketClient? = null

    init {
        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
            initSocket()
        }
    }


    private fun initSocket() {
        //anonymous inner class that inherits from WebSocketClient
        webSocket = object : WebSocketClient(URI("ws://164.92.142.251:4000")) {
            override fun onOpen(handshakedata: ServerHandshake?) {
                socketEventListener?.onSocketOpened()
            }

            override fun onMessage(message: String?) {
                try {
                    socketEventListener?.onNewMessage(
                        gson.fromJson(
                            message,
                            MessageModel::class.java
                        )
                    )
                } catch (e: Exception) {
                }
            }

            override fun onClose(code: Int, reason: String?, remote: Boolean) {
                socketEventListener?.onSocketClosed()
            }

            override fun onError(ex: Exception?) {
            }
        }
        webSocket?.connect()
    }


    fun sendMessageToSocket(messageModel: MessageModel) {
        runCatching {
            webSocket?.send(Gson().toJson(messageModel))
        }
    }

}
