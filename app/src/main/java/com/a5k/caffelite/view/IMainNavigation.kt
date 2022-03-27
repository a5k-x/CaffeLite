package com.a5k.caffelite.view

interface IMainNavigation {
    fun toSingInFragment()
    fun toSingOutFragment()
    fun setTitleActionBar(title:String)
    fun setHomeUp(flag:Boolean)
}