package com.eniskaner.coursecommunicator

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CourseDetailQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CourseVideoQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CourseListQualifierForSignUpScreen

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CourseListQualifierForSignInScreen
