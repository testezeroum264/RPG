package com.example.demo.Service;


import com.example.demo.Model.ItemMagico;
import com.example.demo.Repository.itemMagicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class itemMagicoService {
    @Autowired
    private itemMagicoRepository itemMagicoRepository;
    public List<ItemMagico> findAll(){
        return itemMagicoRepository.findAll();
    }
    public ItemMagico save(ItemMagico itemMagico){
        return itemMagicoRepository.save(itemMagico);
    }
    public ItemMagico findById(Long id){
        return itemMagicoRepository.findById(id).orElseThrow();
    }
    public void deleteById(Long id){
        itemMagicoRepository.deleteById(id);
    }
}
