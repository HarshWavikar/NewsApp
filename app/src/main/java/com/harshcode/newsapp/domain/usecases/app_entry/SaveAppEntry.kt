package com.harshcode.newsapp.domain.usecases.app_entry

import com.harshcode.newsapp.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager : LocalUserManager
) {
    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }
}