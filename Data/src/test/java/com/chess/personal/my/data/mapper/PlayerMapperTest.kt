package com.chess.personal.my.data.mapper

import com.chess.personal.my.data.model.PlayerEntity
import com.chess.personal.my.data.test.factory.PlayerFactory
import com.chess.personal.my.domain.model.Player
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

//@RunWith(JUnit4::class)
//class PlayerMapperTest {

    //private val mapper = PlayerMapper()

   // @Test
//    fun mapFromEntityMapsData() {
//        val entity = PlayerFactory.makePlayerEntity()
//        val model = mapper.mapFromEntity(entity)
//
//        assertEqualData(entity, model)
//    }
//
//    @Test
//    fun mapToEntityMapsData() {
//        val model = PlayerFactory.makePlayer()
//        val entity = mapper.mapToEntity(model)
//
//        assertEqualData(entity, model)
//    }

//    private fun assertEqualData(entity: PlayerEntity,
//                                model: Player) {
//        assertEquals(entity.username, model.username)
//        assertEquals(entity.name, model.name)
//        assertEquals(entity.profileUrl, model.profileUrl)
//        assertEquals(entity.title, model.title)
//        assertEquals(entity.status, model.status)
//        assertEquals(entity.avatar, model.avatar)
//        assertEquals(entity.location, model.location)
//        assertEquals(entity.country, model.country)
//        assertEquals(entity.dateJoined, model.dateJoined)
//        assertEquals(entity.dateLastOnline, model.dateLastOnline)
//        assertEquals(entity.followersCount, model.followersCount)
//        assertEquals(entity.isBookmarked, model.isBookmarked)
//    }

//}