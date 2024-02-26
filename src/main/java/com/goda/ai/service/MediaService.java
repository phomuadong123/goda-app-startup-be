package com.goda.ai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goda.ai.model.Media;
import com.goda.ai.repository.MediaRepository;

@Service
public class MediaService {
    @Autowired
    private MediaRepository mediaRepository;

    public String saveFile(Media media) {
        mediaRepository.save(media);
        return "save url to dtb successfully";
    }

    public String deleteFile(Media media) {
        mediaRepository.delete(media);
        return "delete url from dtb successfully";
    }

    public String saveAllFile(List<Media> medias){
        mediaRepository.saveAll(medias);
        return "save all url to dtb successfully";
    }
}
