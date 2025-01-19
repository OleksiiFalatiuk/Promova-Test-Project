package com.promovatestproject.features.home.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.common.filehelper.FileHelper
import com.example.common.filehelper.FilesDir
import com.example.common.pagination.remotePagingSource
import com.example.common.response.map
import com.promovatestproject.features.home.data.local.source.HomeLocalDataSource
import com.promovatestproject.features.home.data.remote.source.HomeRemoteDataSource
import com.promovatestproject.features.home.domain.repository.HomeRepository
import com.promovatestproject.features.home.models.domain.FilmDomainModel
import com.promovatestproject.features.home.models.domain.HomeDomainModel
import com.promovatestproject.features.home.models.remote.toHomeDomainModel
import com.promovatestproject.features.models.local.toDomainModel
import com.promovatestproject.features.models.local.toLocalModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeRemoteDataSource: HomeRemoteDataSource,
    private val homeLocalDataSource: HomeLocalDataSource,
    private val okHttpClient: OkHttpClient,
    @FilesDir private val filesDir: File
) : HomeRepository {
    private val homeRemotePagingConfig = PagingConfig(
        pageSize = PAGE_SIZE,
        initialLoadSize = INITIAL_LOAD_SIZE
    )

    override fun getFilms(): Flow<PagingData<HomeDomainModel>> =
        Pager(homeRemotePagingConfig) {
            remotePagingSource { page, _ ->
                homeRemoteDataSource.getFilms(page = page).map { it.toHomeDomainModel() }
            }
        }.flow.map { pagingData ->
            pagingData.map { homeRemoteModel ->
                homeRemoteModel
            }
        }

    override fun getLikedFilmsFlow(): Flow<List<HomeDomainModel>> =
        homeLocalDataSource.getAllFlow().map { localFilms ->
            localFilms.groupBy { it.releaseDate }
                .map { (releaseDate, films) ->
                    HomeDomainModel(
                        releaseDate = releaseDate,
                        films = films.map { film ->
                            val posterPath = FileHelper.getFileByName(
                                filesDir,
                                FILMS_IMAGES_DIRECTORY_NAME,
                                film.id.toString()
                            )

                            film.toDomainModel(posterPath = posterPath?.absolutePath)
                        }
                    )
                }
        }

    override suspend fun insertLikedFilm(film: FilmDomainModel) {
        val filmsImagesDirectory = File(filesDir.absolutePath, FILMS_IMAGES_DIRECTORY_NAME)
        if (!filmsImagesDirectory.exists()) {
            filmsImagesDirectory.mkdir()
        }

        val filmImagePath = filesDir.absolutePath + "/$FILMS_IMAGES_DIRECTORY_NAME/${film.id}"
        film.posterPath?.let { posterPath ->
            downloadImage(
                link = posterPath,
                path = filmImagePath
            )
        }


        homeLocalDataSource.insertFilm(film.toLocalModel())
    }

    override suspend fun deleteLikedFilm(film: FilmDomainModel) {
        FileHelper.deleteFileByName(filesDir, FILMS_IMAGES_DIRECTORY_NAME, film.id.toString())

        homeLocalDataSource.deleteTour(film.toLocalModel())
    }

    private fun downloadImage(link: String, path: String) {
        try {
            val request = Request.Builder()
                .url(link)
                .build()

            okHttpClient.newCall(request).execute().use { response ->
                val fileExtension = link.substringAfterLast('.', "jpg").substringBefore('?')
                val destinationFile = File("$path.$fileExtension")

                response.body?.byteStream()?.use { inputStream ->
                    destinationFile.outputStream().use { outputStream ->
                        inputStream.copyTo(outputStream)
                    }
                }
            }
        } catch (e: Exception) {
            // handle exception
        }
    }

    companion object {
        private const val PAGE_SIZE = 10
        private const val INITIAL_LOAD_SIZE = 10

        private const val FILMS_IMAGES_DIRECTORY_NAME = "films_images"
    }
}