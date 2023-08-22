package com.example.airbnb.Service;

import com.example.airbnb.dto.response.HouseResponseForGetAll;
import com.example.airbnb.models.Favorite;
import com.example.airbnb.models.House;
import com.example.airbnb.repositories.FavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteService   {
    private final FavoriteRepository favoriteRepository;


    public Favorite saveFavorite(Favorite favorite) {
            try {
                return favoriteRepository.save(favorite);
            } catch (Exception exception) {
                throw new RuntimeException( exception);
            }
        }


    public void deleteById(Long id) {
        try {
            favoriteRepository.deleteById(id);
        } catch (Exception exception) {
            throw new RuntimeException(  exception);
        }
    }

    public void update(Long id, Favorite favorite) {

    }


    public Favorite findById(Long id) {
        try {
            return favoriteRepository.findById(id).orElseThrow(() -> new RuntimeException(String.valueOf(id)));
        } catch (Exception exception) {
            throw new RuntimeException( exception);
        }
    }
}
