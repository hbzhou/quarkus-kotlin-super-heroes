package com.itsz.quarkus.villains.service

import com.itsz.quarkus.villains.model.Villain
import com.itsz.quarkus.villains.repository.VillainsRepository
import javax.enterprise.context.ApplicationScoped
import javax.transaction.Transactional
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class VillainDto(

    var id: Long,
    @NotNull
    @Size(min = 3, max = 50)
    val name: String,
    val otherName: String,
    @NotNull
    @Min(1)
    val level: Int,
    val picture: String,
    val powers: String,
)


@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
class VillainService(val villainsRepository: VillainsRepository) {

    @Transactional(Transactional.TxType.SUPPORTS)
    fun findAll(): List<Villain> = villainsRepository.listAll()


    @Transactional(Transactional.TxType.SUPPORTS)
    fun findById(id: Long) = villainsRepository.findById(id)

    fun persist(villainDto: VillainDto) {
        val villain = Villain(
            name = villainDto.name,
            otherName = villainDto.otherName,
            level = villainDto.level,
            picture = villainDto.picture,
            powers = villainDto.powers,
        )
         villainsRepository.persist(villain)
    }

    fun update(villainDto: VillainDto){
        val villain = villainsRepository.findById(villainDto.id)
        villain.name = villainDto.name
        villain.otherName = villainDto.otherName
        villain.level = villainDto.level
        villain.picture = villainDto.picture
        villain.powers = villainDto.powers
        villainsRepository.persist(villain)
    }

    fun delete(id: Long) = villainsRepository.deleteById(id)

}