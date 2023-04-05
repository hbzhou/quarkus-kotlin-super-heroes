package com.itsz.quarkus.statistic.websocket

import com.fasterxml.jackson.databind.ObjectMapper
import com.itsz.quarkus.statistic.service.Score
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


@ServerEndpoint("/stats/winners")
@ApplicationScoped
class TopWinnerWebSocket(
    val mapper: ObjectMapper,
    val logger: Logger,
    @Channel("winner-stats") val winners: Multi<List<Score>>
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
        cancellable = winners.map { mapper.writeValueAsString(it) }
            .subscribe().with { serialized -> sessions.forEach { write(it, serialized) } }
    }

    @PreDestroy
    fun cleanup() {
        cancellable.cancel()
    }

    private fun write(session: Session, serialized: String) {
        session.asyncRemote.sendText(serialized) { result ->
            if (result.exception != null) {
                logger.error("Unable to write message to web socket", result.exception)
            }
        }
    }
}