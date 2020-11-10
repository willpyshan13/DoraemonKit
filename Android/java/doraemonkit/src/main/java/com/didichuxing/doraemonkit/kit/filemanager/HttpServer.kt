package com.didichuxing.doraemonkit.kit.filemanager

import com.didichuxing.doraemonkit.constant.DoKitConstant
import io.ktor.server.cio.CIO
import io.ktor.server.cio.CIOApplicationEngine
import io.ktor.server.engine.embeddedServer

/**
 * ================================================
 * 作    者：jint（金台）
 * 版    本：1.0
 * 创建日期：2020/6/28-11:02
 * 描    述：
 * 修订历史：
 * ================================================
 */
object HttpServer {
    val server: CIOApplicationEngine by lazy {
        embeddedServer(CIO, port = DoKitConstant.FILE_MANAGER_HTTP_PORT, module = DoKitFileRouter)
    }
}