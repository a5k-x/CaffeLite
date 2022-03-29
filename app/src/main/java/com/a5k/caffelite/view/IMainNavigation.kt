package com.a5k.caffelite.view

interface IMainNavigation {
    fun toSingInFragment()
    fun toSingOutFragment()
    fun toPointsCaffeListFragment()
    fun toListMenu(idPointCaffe:Int)
    fun setTitleActionBar(title:String)
    fun setHomeUp(flag:Boolean)

    fun saveLoginShared(login:String)
    fun getLastAuthLogin():String

}