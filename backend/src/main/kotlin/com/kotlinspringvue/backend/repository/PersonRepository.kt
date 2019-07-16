package com.kotlinspringvue.backend.repository

import com.kotlinspringvue.backend.jpa.Person
import org.springframework.stereotype.Repository
import org.springframework.data.repository.CrudRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param

@Repository
interface PersonRepository: CrudRepository<Person, Long> {

}