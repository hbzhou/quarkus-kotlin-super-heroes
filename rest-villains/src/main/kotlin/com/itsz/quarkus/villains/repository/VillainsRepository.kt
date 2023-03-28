package com.itsz.quarkus.villains.repository

import com.itsz.quarkus.villains.model.Villain
import io.quarkus.hibernate.orm.panache.PanacheRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class VillainsRepository: PanacheRepository<Villain>