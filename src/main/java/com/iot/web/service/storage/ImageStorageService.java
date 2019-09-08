package com.iot.web.service.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;

@Configuration
public class ImageStorageService implements StorageService {

    private final Path rootLocation;
    @Value("${storage.image.location}")
    private String location = "/home/matio/upload-dir";

    private final Logger LOG = LoggerFactory.getLogger(ImageStorageService.class);

    public ImageStorageService() {
        this.rootLocation = Paths.get(location);
        this.init();
    }

    @Override
    public void store(MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            long copy = Files.copy(file.getInputStream(), this.rootLocation.resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);
            LOG.info("Image {} bytes uploaded       ", copy);
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
    }


    @Override
    public void init() {
        try {
            if(!Files.exists(rootLocation, LinkOption.NOFOLLOW_LINKS)) {
                Files.createDirectories(rootLocation);
            }
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

}
