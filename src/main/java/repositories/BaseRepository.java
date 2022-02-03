package repositories;
import models.Models;

public interface BaseRepository<T extends Models> {

    //show info
    public T showAll(T t);



    //add
    public boolean add(T t);


    //update
    public boolean update(T t);


    //remove
    public boolean remove(T t);


}
