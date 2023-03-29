package com.itsz.quarkus.villains

import io.quarkus.runtime.ShutdownEvent
import io.quarkus.runtime.StartupEvent
import io.quarkus.runtime.configuration.ProfileManager
import org.jboss.logging.Logger
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.event.Observes

@ApplicationScoped
class VillainApplicationLifeCycle {

    private val logger: Logger = Logger.getLogger(VillainApplicationLifeCycle::class.java)

    fun onStart(@Observes ev: StartupEvent?) {
        logger.info(" __     ___ _ _       _             _    ____ ___ ")
        logger.info(" \\ \\   / (_) | | __ _(_)_ __       / \\  |  _ \\_ _|")
        logger.info("  \\ \\ / /| | | |/ _` | | '_ \\     / _ \\ | |_) | | ")
        logger.info("   \\ V / | | | | (_| | | | | |   / ___ \\|  __/| | ")
        logger.info("    \\_/  |_|_|_|\\__,_|_|_| |_|  /_/   \\_\\_|  |___|")
        logger.info("The application VILLAIN is starting with profile " + ProfileManager.QUARKUS_PROFILE_ENV);

    }

    fun onStop(@Observes ev: ShutdownEvent?) {
        logger.info("The application VILLAIN is stopping...")
    }
}