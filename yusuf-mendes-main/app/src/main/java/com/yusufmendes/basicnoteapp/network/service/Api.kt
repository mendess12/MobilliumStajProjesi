package com.yusufmendes.basicnoteapp.network.service

import com.yusufmendes.basicnoteapp.model.NoteDetail.AddNote
import com.yusufmendes.basicnoteapp.model.forgotpassword.ForgotPasswordResponse
import com.yusufmendes.basicnoteapp.model.login.LoginResponse
import com.yusufmendes.basicnoteapp.model.note.Note
import com.yusufmendes.basicnoteapp.model.profile.ProfileResponse
import com.yusufmendes.basicnoteapp.model.register.RegisterResponse
import com.yusufmendes.basicnoteapp.model.update.ChangePassword
import com.yusufmendes.basicnoteapp.model.update.UpdateMe
import com.yusufmendes.basicnoteapp.model.update.UpdateNoteData
import retrofit2.Call
import retrofit2.http.*

interface Api {

    //login api islemleri
    @FormUrlEncoded
    @POST("auth/login")
    fun userLogin(
        @Field("email") email: String,
        @Field("password") password: String,
    ): Call<LoginResponse>

    //register api islemleri
    @FormUrlEncoded
    @POST("auth/register")
    fun createUser(
        @Field("full_name") full_name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        //@Body request: RegisterRequest
    ): Call<RegisterResponse>

    //forgot password islemleri
    @FormUrlEncoded
    @POST("auth/forgot-password")
    fun changePassword(
        @Field("email") email: String,
    ): Call<ForgotPasswordResponse>

    //noteList islemleri
    @GET("users/me/notes")
    fun getNote(
        @Query("page") page: Int,
        @Header("Authorization") authHeader: String,
    ): Call<Note>

    //add note islemleri
    @FormUrlEncoded
    @POST("notes")
    fun addNote(
        @Field("title") title: String,
        @Field("note") note: String,
        @Header("Authorization") authHeader: String,
    ): Call<AddNote>

    //update note
    @FormUrlEncoded
    @PUT("notes/{note_id}")
    fun updateNote(
        @Field("title") note_title: String,
        @Field("note") note_detail: String,
        @Path("note_id") note_id: Int,
        @Header("Authorization") authHeader: String,
    ): Call<UpdateNoteData>

    //delete note
    @DELETE("notes/{note_id}")
    fun deleteNote(
        @Path("note_id") noteId: Int,
        @Header("Authorization") authHeader: String,
    ): Call<Boolean>

    //user me islemleri
    @GET("users/me")
    fun getUser(
        @Header("Authorization") authHeader: String,
    ): Call<ProfileResponse>

    //user update me islemleri
    @FormUrlEncoded
    @PUT("users/me")
    fun updateMe(
        @Field("full_name") full_name: String,
        @Field("email") email: String,
        @Header("Authorization") authHeader: String,
    ): Call<UpdateMe>

    //update password
    @FormUrlEncoded
    @PUT("users/me/password")
    fun updatePassword(
        @Field("password") password: String,
        @Field("new_password") new_password: String,
        @Field("new_password_confirmation") new_password_confirmation: String,
        @Header("Authorization") authHeader: String,
    ): Call<ChangePassword>
}
