package com.example.myapplication.dispatcher.movie

import com.efg.valu.sales.repository.local.BaseLocalRepo
import com.example.myapplication.repository.remote.BaseRemoteRepo

class MovieDispatcherImpl(
    override val localRepo: BaseLocalRepo,
    override val remoteRepo: BaseRemoteRepo
) : MovieDispatcher