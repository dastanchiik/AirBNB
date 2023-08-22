package com.example.airbnb.api;

import com.example.airbnb.Service.FavoriteService;
import com.example.airbnb.models.Favorite;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FavoriteApi {
    private final FavoriteService favoriteService;
}
