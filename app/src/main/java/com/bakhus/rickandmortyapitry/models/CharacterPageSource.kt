package com.bakhus.rickandmortyapitry.models

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bakhus.rickandmortyapitry.api.ApiHelper
import com.bakhus.rickandmortyapitry.api.ApiService
import com.bakhus.rickandmortyapitry.repostiory.Repository
import retrofit2.HttpException
import java.io.IOException

private const val UNSPLASH_STARTING_PAGE_INDEX = 1

class CharacterPageSource(
    private val repository: Repository
) : PagingSource<Int, Character>() {

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {

        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        val page: Int = params.key ?: 1

        return try {
            val response = repository.getCharacters(page)
            val character = response.results

            LoadResult.Page(
                data = character,
                prevKey = if (position == UNSPLASH_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = getPageIndexFromNext(response.info.next)
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    private fun getPageIndexFromNext(next: String?): Int? {
        return next?.split("?page=")?.get(1)?.toInt()
    }
}