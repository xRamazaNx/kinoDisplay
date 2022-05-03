package ru.kiz.developer.abdulaev.kinodisplay.core

interface Mapper {
    interface MovieMapper<R> {
        fun map(name: String, description: String, avatarUrl: String): R
    }
}