package com.codebook.petfacts

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Cat

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Dog