package com.shariar99.demoapps.Service.Network;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.shariar99.demoapps.Service.Model.Product;

@Database(entities = {Product.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase
{
    public abstract ProductDao ProductDao();
}