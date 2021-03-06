package ru.imagestestingapp

sealed interface Event {
    class SampleEvent(val stringData: String, val longData: Long) : Event

    class ImagesListSelected(val images: List<String>) : Event
}