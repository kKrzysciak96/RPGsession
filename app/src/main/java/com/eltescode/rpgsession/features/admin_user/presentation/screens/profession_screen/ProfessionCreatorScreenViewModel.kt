package com.eltescode.rpgsession.features.admin_user.presentation.screens.profession_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eltescode.rpgsession.features.admin_user.data.repository.umki
import com.eltescode.rpgsession.features.admin_user.domain.use_case.AdminUseCases
import com.eltescode.rpgsession.features.profession.domain.model.MainProfile
import com.eltescode.rpgsession.features.profession.domain.model.Profession
import com.eltescode.rpgsession.features.profession.domain.model.SecondaryProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfessionCreatorScreenViewModel @Inject constructor(private val adminUserUseCases: AdminUseCases) :
    ViewModel() {

//    init {
//        viewModelScope.launch {
//            umki.forEach {
//                adminUserUseCases.createNewProfessionUseCase(
//                    Profession(
//                        professionName = it,
//                        mainProfile = MainProfile(),
//                        secondaryProfile = SecondaryProfile(),
//                        skills = "",
//                        trappings = "",
//                        professionEntries = "",
//                        professionExits = ""
//                    )
//                )
//            }
//        }
//    }

}