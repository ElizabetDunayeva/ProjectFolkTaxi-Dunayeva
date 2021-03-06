package com.netcracker.services;

import com.netcracker.DTO.ChatDto;
import com.netcracker.DTO.MessageDto;
import com.netcracker.entities.Chat;
import com.netcracker.entities.Group;
import com.netcracker.entities.Message;
import com.netcracker.entities.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class MessageMapper extends AbstractMapper<Message, MessageDto> {
    public MessageMapper() {
        super(Message.class, MessageDto.class);
    }

    @Autowired
    private ChatMapper chatmapper;
    @PostConstruct
    public void setupMapper() {
       /* mapper.createTypeMap(Chat.class, ChatDto.class).setPostConverter(context -> {
            LOG.debug("Group in Long");
            ChatDto id_chat = context.getDestination();
            Chat chat = context.getSource();
            id_chat = chatmapper.toDto(chat);
            return context.getDestination();
        });*/

    }



     public List<MessageDto>mapInntoDto(List<Message> entities){

       return entities.stream().map(e -> this.toDto(e)).collect(Collectors.toList());
        //entities.forEach(e -> dtos.add(this.toDto(e)));
    }
  public Page<MessageDto> maoPagetoDto(Pageable pageRequest, Page<Message> source){
        List<MessageDto> dtos = mapInntoDto(source.getContent());
        return new PageImpl<>(dtos,pageRequest,source.getTotalElements());
    }
}
