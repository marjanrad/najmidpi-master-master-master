package com.example.najmidpi.server;

import com.example.najmidpi.server.model.PatientInfirmation;
import com.example.najmidpi.server.model.RequestSensor;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("json%20files/data.php")
    Single<Response<Void>> setDataSensor(@Body RequestSensor requestSensor);

    @POST("json%20files/patDATA.php")
    Single<Response<Void>> setPatInformation(@Body PatientInfirmation patientInfirmation);
}
