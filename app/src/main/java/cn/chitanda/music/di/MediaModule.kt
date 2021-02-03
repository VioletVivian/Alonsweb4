package cn.chitanda.music.di

import android.content.ComponentName
import android.content.Context
import cn.chitanda.music.media.MusicService
import cn.chitanda.music.media.MusicSource
import cn.chitanda.music.media.MusicSourceImp
import cn.chitanda.music.media.connect.MusicServiceConnection
import cn.chitanda.music.repository.SongsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.Sin