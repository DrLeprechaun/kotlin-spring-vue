package com.kotlinspringvue.backend.jpa

import javax.persistence.*

@Entity
@Table(name = "roles")
data class Role (

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,

        @Column(name="name")
        val name: String

)