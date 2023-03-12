/*
 * Copyright 2018 Google Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.chitanda.music.media.extensions

import android.os.SystemClock
import android.support.v4.media.session.PlaybackStateCompat

/**
 * Useful extension methods for [PlaybackStateCompat].
 */

inline val PlaybackStateCompat.isSeeking
    get() = (state == PlaybackStateCompat.STATE_FAST_FORWARDING) ||
            (state == PlaybackStateCompat.STATE_REWINDING)

inline val PlaybackStateCompat.isPrepared
    get() = (state == PlaybackStateCompat.STATE_BUFFERING) ||
            (state == PlaybackStateCompat.STATE_PLAYING) ||
            (state == PlaybackStateCompat.STATE_PAUSED)

inline val PlaybackSt