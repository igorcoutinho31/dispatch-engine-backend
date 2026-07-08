package com.example.demo.service;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixElementStatus;
import com.google.maps.model.GeocodingResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GoogleMapsService {

    private final GeoApiContext context;

    public GoogleMapsService(@Value("${google.maps.api-key}") String apiKey) {
        this.context = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();
    }

    public double[] getCoordinatesFromAddress(String address) {
        try {
            GeocodingResult[] results = GeocodingApi.geocode(context, address).await();

            if (results != null && results.length > 0) {
                double lat = results[0].geometry.location.lat;
                double lng = results[0].geometry.location.lng;

                return new double[]{lat, lng};
            }
        } catch (ApiException | InterruptedException | IOException e) {
            System.out.println("Erro ao buscar coordenadas no Google: " + e.getMessage());
        }

        return new double[]{-23.6536, -46.5274};
    }

    public double getDistanceInKm(String originAddress, String destinationAddress) {
        try {
            DistanceMatrix matrix = DistanceMatrixApi.getDistanceMatrix(context,
                    new String[]{originAddress},
                    new String[]{destinationAddress}).await();

            if (matrix.rows.length > 0 && matrix.rows[0].elements.length > 0) {
                var element = matrix.rows[0].elements[0];

                if (element.status == DistanceMatrixElementStatus.OK) {
                    long distanceInMeters = element.distance.inMeters;

                    return distanceInMeters / 1000.0;
                }
            }
        } catch (ApiException | InterruptedException | IOException e) { // A chave que faltava foi inserida antes deste catch!
            System.out.println("Erro ao calcular a distância no Google: " + e.getMessage());
        }

        return 5.5;
    }
}
