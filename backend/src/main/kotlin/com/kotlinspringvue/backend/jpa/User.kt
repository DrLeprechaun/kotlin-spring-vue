package com.kotlinspringvue.backend.jpa

import javax.persistence.*

@Entity
@Table(name = "users")
data class User (

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long? = 0,

        @Column(name="username")
        var username: String?=null,

        @Column(name="first_name")
        var firstName: String?=null,

        @Column(name="last_name")
        var lastName: String?=null,

        @Column(name="email")
        var email: String?=null,

        @Column(name="password")
        var password: String?=null,

        @Column(name="enabled")
        var enabled: Boolean = false,

        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(
                name = "users_roles",
                joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
        )
        var roles: Collection<Role>? = null
)