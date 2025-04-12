package com.example.demo.Service;

import com.example.demo.Enums.TipoItem;
import com.example.demo.Exceptions.Exceptions;
import com.example.demo.Model.ItemMagico;
import com.example.demo.Model.Personagem;
import com.example.demo.Repository.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class personagemService {
    @Autowired
    private PersonagemRepository personagemRepository;

    @Autowired
    private itemMagicoService itemMagicoService;

    public List<Personagem>findAll(){

        return personagemRepository.findAll().stream().map(personagem -> personagem.AtributosSoma()).collect(Collectors.toList());
    }

    public Personagem save(Personagem personagem) throws Exceptions.ValoresMaiores{
        if(personagem.getDefesa() + personagem.getForca()>10){
            throw new Exceptions.ValoresMaiores();
        }
        return personagemRepository.save(personagem);
    }

    public Personagem findById(int id){
        Personagem personagemSelecionado=personagemRepository.findById((long) id).orElseThrow();
        return personagemSelecionado.AtributosSoma();
    }
    public Personagem atualizarNomeAventureiro(int id, String novoNome){
        Personagem personagemSelecionado=personagemRepository.findById((long)id).orElseThrow();
        personagemSelecionado.setNomeAventureiro(novoNome);
        return personagemRepository.save(personagemSelecionado);
    }

    public void deleteById(Long id){

        personagemRepository.deleteById(id);
    }
    public Personagem adicionarItemMagico(int id, int idItemMagico) throws Exceptions.PossuiAmuleto{
        Personagem personagemSelecionado=personagemRepository.findById((long)id).orElseThrow();
        ItemMagico itemMagico=itemMagicoService.findById((long) idItemMagico);

        if(itemMagico.getTipoItem()== TipoItem.AMULETO && personagemSelecionado.temAmuleto()){
            throw new Exceptions.PossuiAmuleto();
        }
        personagemSelecionado.getItemMagicoList().add(itemMagico);
        System.out.println(personagemSelecionado.toString());
        return personagemRepository.save(personagemSelecionado);
    }

    public List<ItemMagico> listarItensMagicos(int id){
        Personagem personagemSelecionado=personagemRepository.findById((long) id).orElseThrow();
        return personagemSelecionado.getItemMagicoList();
    }

    public Personagem removerItemMagico(int id, int idItemMagico){
        int index=0;
        Personagem personagemSelecionado=personagemRepository.findById((long) id).orElseThrow();
        for(ItemMagico itemMagico : personagemSelecionado.getItemMagicoList()){
            if(itemMagico.getId()==idItemMagico){
                personagemSelecionado.getItemMagicoList().remove(index);

                System.out.println(personagemSelecionado.toString());
                return personagemRepository.save(personagemSelecionado);
            }
            index++;
        }
        return personagemSelecionado;
    }

    public ItemMagico buscarAmuleto(int id){
        Personagem personagemSelecionado=personagemRepository.findById((long) id).orElseThrow();
        for(ItemMagico itemMagico : personagemSelecionado.getItemMagicoList()){
            if(itemMagico.getTipoItem()== TipoItem.AMULETO){
                return itemMagico;
            }
        }
        return new ItemMagico();
    }

}
