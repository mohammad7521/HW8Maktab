package repositories;
import models.Models;

public interface BaseRepository<T> {

    //add
    public int add(T t);


    //show info
    public T showInfo(int id);




}
