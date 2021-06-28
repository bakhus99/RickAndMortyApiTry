package com.bakhus.rickandmortyapitry.models

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bakhus.rickandmortyapitry.api.ApiHelper
import com.bakhus.rickandmortyapitry.repostiory.Repository
import kotlinx.coroutines.CoroutineScope

class LocationsPagingSource(
    val repository: Repository
) : PagingSource<Int, LocationData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LocationData> {
        val pageNumber = params.key ?: 1
        val previousKey = if (pageNumber == 1) null else pageNumber - 1
        val pageResult = repository.getLocations(pageNumber)


        return LoadResult.Page(
            data = pageResult.results,
            prevKey = previousKey,
            nextKey = getPageIndexFromNext(pageResult.info.next)
        )
    }

    override fun getRefreshKey(state: PagingState<Int, LocationData>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    private fun getPageIndexFromNext(next: String?): Int? {
        return next?.split("?page=")?.get(1)?.toInt()
    }

}