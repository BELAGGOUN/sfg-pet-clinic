package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.BaseEntity;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractMapService <T extends BaseEntity,ID extends Long> {

    protected Map<Long, T> map = new HashMap<>();

    Set<T> findAll() {
        return new HashSet<>(map.values());
    }
    T findById(ID id)
    {
        return map.get(id);
    }
    T save(T object){
        if(object != null) {
            if(object.getId()== null) {
               object.setId(getNextId());
            }
            map.put(object.getId(),object);
        }else throw new RuntimeException("Heeee The Object Can't Be Null");
        return object;
       }
    void deleteById(ID id)
    {
     map.remove(id);
    }
    void delete(T object)
    {
     map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }
     private  Long getNextId()
     { Long nextId=null;
          //if there is no element on the map we can't get the max from the collection so we catch the
         // execption NoSuchElementException to assign the first value of the ID
        try {
           nextId= Collections.max(map.keySet()) + 1;
        }catch(NoSuchElementException e)
        {
            nextId=1L;
        }
            return nextId;
     }
}