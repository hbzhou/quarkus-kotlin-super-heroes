package com.itsz.quarkus.statistic.websocket

import io.smallrye.mutiny.Multi
import io.smallrye.mutiny.subscription.Cancellable
import org.eclipse.microprofile.reactive.messaging.Channel
import org.jboss.logging.Logger
import java.util.concurrent.CopyOnWriteArrayList
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy
import javax.enterprise.context.ApplicationScoped
import javax.websocket.OnClose
import javax.websocket.OnOpen
import javax.websocket.Session
import javax.websocket.server.ServerEndpoint


@ServerEndpoint("/stats/team")
@ApplicationScoped
class TeamStatsWebSocket(
    @Channel("team-stats")
    val stream: Multi<Double>,
    val logger: Logger
) {

    private val sessions: MutableList<Session> = CopyOnWriteArrayList()
    private lateinit var cancellable: Cancellable

    @OnOpen
    fun onOpen(session: Session) {
        sessions.add(session)
    }

    @OnClose
    fun onClose(session: Session) {
        sessions.remove(session)
    }

    @PostConstruct
    fun subscribe() {
        cancellable = stream.subscribe().with { ratio -> sessions.forEach { write(it, ratio) } }
    }

    @PreDestroy
    fun cleanup() {
        cancellable.cancel()
    }

    private fun write(session: Session, ratio: Double) {
        session.asyncRemote.sendText(ratio.toString()) { result ->
            if (result.exception != null) {
                logger.error("Unable to write message to web socket", result.exception)
            }
        }
    }
}