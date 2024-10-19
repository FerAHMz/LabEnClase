package com.uvg.labenclase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.uvg.labenclase.ui.MelodyMasterApp
import com.uvg.labenclase.ui.theme.LabEnClaseTheme
import com.uvg.labenclase.viewmodel.MusicViewModel
import com.uvg.labenclase.viewmodel.MusicViewModelFactory
import com.uvg.labenclase.data.db.MusicDatabase
import com.uvg.labenclase.data.repository.MusicRepository

class MainActivity : ComponentActivity() {

    private val viewModel: MusicViewModel by viewModels {
        val songDao = MusicDatabase.getDatabase(application).songDao()
        val artistDao = MusicDatabase.getDatabase(application).artistDao()
        MusicViewModelFactory(MusicRepository(songDao, artistDao))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LabEnClaseTheme {
                // Agregar el log para depuración
                println("Iniciando precarga de datos...")

                // Llamada para precargar los datos si la base de datos está vacía
                viewModel.preloadDataIfEmpty()

                // Iniciar la UI principal de la aplicación
                MelodyMasterApp(viewModel = viewModel)
            }
        }
    }
}


